package net.pttheta.loveandwar.entity.custom;

import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.pttheta.loveandwar.entity.ai.RobotDogAttackGoal;
import net.pttheta.loveandwar.item.ModItems;
import net.pttheta.loveandwar.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RobotdogEntity extends TamableAnimal implements NeutralMob, ContainerListener {
    public static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ID_TURRET = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> PAINTJOB = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_ID_ARMOR = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ID_SCANNER = SynchedEntityData.defineId(RobotdogEntity.class, EntityDataSerializers.BOOLEAN);


    public static final TagKey<Item> bulletTag = ItemTags.create(new ResourceLocation("flansmod", "pistol_bullet"));
    public static final TagKey<Item> casingTag = ItemTags.create(new ResourceLocation("createloveandwar", "bullet_casing"));
    public SimpleContainer dogInventory;
    public RobotdogEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        //this.createInventory();

        initDogInventory();
    }

    public float scannerAngle = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public int projectileAttackTick = 0;

    public final AnimationState sitAnimationState = new AnimationState();

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID persistentAngerTarget;
    protected SimpleContainer inventory;
    private net.minecraftforge.common.util.LazyOptional<?> itemHandler = null;


    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            setupAnimationStates();
        } else {
            projectileAttackTick--;
        }

        scannerAngle+=0.5f;

    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0 && !this.isInSittingPose()){
            this.idleAnimationTimeout = this.random.nextInt(30)+60;
            if(!idleAnimationState.isStarted())
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            idleAnimationState.stop();
            attackAnimationTimeout = 16; // Length in ticks of your animation
            attackAnimationState.start(tickCount);
            this.setSpeed(0);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
            this.setSpeed(1);
        }

        if(this.isInSittingPose()){
            sitAnimationState.start(tickCount);
            this.idleAnimationState.stop();
        } else {
            sitAnimationState.stop();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick*6f,1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f,0.25f);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
        attackAnimationTimeout = 16; // Length in ticks of your animation
        attackAnimationState.start(0);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_ID_CHEST, false);
        this.entityData.define(DATA_ID_TURRET, false);
        this.entityData.define(PAINTJOB, 0);
        this.entityData.define(DATA_ID_ARMOR, false);
        this.entityData.define(DATA_ID_SCANNER, false);
    }

    public void openGUI(Player playerEntity) {
        if (!this.level().isClientSide && (!this.hasPassenger(playerEntity))) {
            NetworkHooks.openScreen((ServerPlayer) playerEntity, new MenuProvider() {
                @Override
                public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
                    return new DispenserMenu(p_createMenu_1_, p_createMenu_2_, dogInventory);
                }

                @Override
                public Component getDisplayName() {
                    return Component.literal("Storage");
                }
            });
        }
    }

    private void initDogInventory() {
        SimpleContainer animalchest = this.dogInventory;
        this.dogInventory = new SimpleContainer(9) {
            public void stopOpen(Player player) {
                RobotdogEntity.this.resetSlots();
            }

            public boolean stillValid(Player player) {
                return RobotdogEntity.this.isAlive() && !RobotdogEntity.this.isInsidePortal;
            }
        };
        dogInventory.addListener(this);
        if (animalchest != null) {
            int i = Math.min(animalchest.getContainerSize(), this.dogInventory.getContainerSize());
            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = animalchest.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.dogInventory.setItem(j, itemstack.copy());
                }
            }
            resetSlots();
        }

    }

    public void resetSlots() {
        if (!this.level().isClientSide) {
            for (int i = 0; i < this.dogInventory.getContainerSize(); ++i) {
                ItemStack stack = this.dogInventory.getItem(i);
                if (!stack.isEmpty()) {

                }
            }

            updateClientInventory();
        }
    }

    private void updateClientInventory() {
        if (!this.level().isClientSide) {
            for (int i = 0; i < 9; i++) {
                //AlexsMobs.sendMSGToAll(new MessageKangarooInventorySync(this.getId(), i, kangarooInventory.getItem(i)));
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new RobotDogAttackGoal(this,1.0D,true));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class,2f));

        this.goalSelector.addGoal(1,new TemptGoal(this,1.2D, Ingredient.of(Items.REDSTONE_BLOCK),false));
        this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new RobotdogEntity.SearchForItemsGoal());
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));

        this.goalSelector.addGoal(6,new WaterAvoidingRandomStrollGoal(this,1.1D));

        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, FlyingMob.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        //super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED,0.4D)
                .add(Attributes.FOLLOW_RANGE,24D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 5f);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    public boolean isFood(ItemStack pStack) {
        Item item = pStack.getItem();
        return item == Items.IRON_INGOT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return ModSounds.ROBOT_DOG_AMBIENT.get();
        } else if (this.random.nextInt(3) == 0) {
            return this.isTame() && this.getHealth() < 10.0F ? SoundEvents.WOLF_WHINE : SoundEvents.WOLF_PANT;
        } else {
            return SoundEvents.WOLF_AMBIENT;
        }
        //return ModSounds.ROBOT_DOG_AMBIENT.get();
    }


    @Nullable
    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(ModSounds.SERVO.get(), 0.08F, 1.0F);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_DAMAGE;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        }else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {

                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else if (!this.hasChest() && itemstack.is(Items.CHEST)) {

                this.equipChest(pPlayer, itemstack);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }else if (this.hasChest() && !this.hasTurret() && itemstack.is(ModItems.TURRET_UPGRADE.get())) {

                this.equipTurret(pPlayer, itemstack);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            } else if (!this.hasArmor() && itemstack.is(ModItems.ARMOR_UPGRADE.get())) {

                this.setArmor(true);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }else if (this.hasChest() && !this.hasScanner() && itemstack.is(ModItems.SCANNER_UPGRADE.get())) {

                this.setScanner(true);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }else if(item instanceof DyeItem || item == ModItems.ROBOT_DOG_VALKYRIE_SKIN.get()|| item == ModItems.ROBOT_DOG_BUBBLEGUM_SKIN.get()|| item == ModItems.ROBOT_DOG_RACING_SKIN.get()){

                SetPaintFromDye(item);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);

                if(interactionresult != InteractionResult.SUCCESS) {
                    if (pPlayer.isShiftKeyDown() && this.isOwnedBy(pPlayer) && hasChest()) {
                        if (!this.isBaby()) {
                            this.openGUI(pPlayer);
                            this.ejectPassengers();
                        }
                        return InteractionResult.SUCCESS;
                    }
                }

                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                } else {
                    return interactionresult;
                }
            }
        } else if (itemstack.is(Items.REDSTONE_BLOCK) && !this.isAngry()) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.tame(pPlayer);
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    public void SetPaintFromDye(Item item){ //yes there's a better way to do this but consider I am stupid
        //0 = yellow
        //1 = red
        //2 = white
        //3 = light grey
        //4 = grey
        //5 = black
        //6 = brown
        //7 = orange
        //8 = lime

        if(item == Items.YELLOW_DYE){
            setPaintjob(0);
        } else if(item == Items.RED_DYE){
            setPaintjob(1);
        }else if(item == Items.WHITE_DYE){
            setPaintjob(2);
        }else if(item == Items.LIGHT_GRAY_DYE){
            setPaintjob(3);
        }else if(item == Items.GRAY_DYE){
            setPaintjob(4);
        }else if(item == Items.BLACK_DYE){
            setPaintjob(5);
        }else if(item == Items.BROWN_DYE){
            setPaintjob(6);
        }else if(item == Items.ORANGE_DYE){
            setPaintjob(7);
        }else if(item == Items.LIME_DYE){
            setPaintjob(8);
        }else if(item == Items.GREEN_DYE){
            setPaintjob(9);
        }else if(item == Items.CYAN_DYE){
            setPaintjob(10);
        }else if(item == Items.LIGHT_BLUE_DYE){
            setPaintjob(11);
        }else if(item == Items.BLUE_DYE){
            setPaintjob(12);
        }else if(item == Items.PURPLE_DYE){
            setPaintjob(13);
        }else if(item == Items.MAGENTA_DYE){
            setPaintjob(14);
        }else if(item == Items.PINK_DYE){
            setPaintjob(15);
        }
        else if(item == ModItems.ROBOT_DOG_VALKYRIE_SKIN.get()){
            setPaintjob(16);
        }
        else if(item == ModItems.ROBOT_DOG_BUBBLEGUM_SKIN.get()){
            setPaintjob(17);
        }
        else if(item == ModItems.ROBOT_DOG_RACING_SKIN.get()){
            setPaintjob(18);
        }
    }

    public void setTame(boolean pTamed) {
        super.setTame(pTamed);
        if (pTamed) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            Entity entity = pSource.getEntity();
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
            }

            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                pAmount = (pAmount + 1.0F) / 2.0F;
            }

            return super.hurt(pSource, pAmount);
        }
    }

    @Override
    public void updatePersistentAnger(ServerLevel pServerLevel, boolean pUpdateAnger) {
        LivingEntity livingentity = this.getTarget();
        UUID uuid = this.getPersistentAngerTarget();
        if ((livingentity == null || livingentity.isDeadOrDying()) && uuid != null && pServerLevel.getEntity(uuid) instanceof Mob) {
            this.stopBeingAngry();
        } else {
            if (livingentity != null && !Objects.equals(uuid, livingentity.getUUID())) {
                this.setPersistentAngerTarget(livingentity.getUUID());
                this.startPersistentAngerTimer();
            }

            if (this.getRemainingPersistentAngerTime() > 0 && (livingentity == null || livingentity.getType() != EntityType.PLAYER || !pUpdateAnger)) {
                this.setRemainingPersistentAngerTime(this.getRemainingPersistentAngerTime() - 1);
                if (this.getRemainingPersistentAngerTime() == 0) {
                    this.stopBeingAngry();
                }
            }
        }
    }

    public boolean hasChest() {
        return this.entityData.get(DATA_ID_CHEST);
    }

    public void setChest(boolean pChested) {
        this.entityData.set(DATA_ID_CHEST, pChested);
    }

    public boolean hasArmor() {
        return this.entityData.get(DATA_ID_ARMOR);
    }

    public void setArmor(boolean pChested) {
        this.entityData.set(DATA_ID_ARMOR, pChested);
        if(pChested)
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
        else
            this.getAttribute(Attributes.ARMOR).setBaseValue(0.0D);
    }

    public int getPaintjob() {
        return this.entityData.get(PAINTJOB);
    }

    public void setPaintjob(int id) {
        this.entityData.set(PAINTJOB, id);
    }

    public boolean hasTurret() {
        return this.entityData.get(DATA_ID_TURRET);
    }

    public boolean hasTurretAmmo(){

        for(int i = 0; i < dogInventory.getContainerSize(); i++){
            ItemStack s = dogInventory.getItem(i);
            if(s.is(bulletTag)){
                return true;
            }
        }
        return false;
    }

    public void ConsumeAmmo(){
        for(int i = 0; i < dogInventory.getContainerSize(); i++){
            ItemStack s = dogInventory.getItem(i);
            if(s.is(bulletTag)){
                s.shrink(1);

                ItemStack casing = new ItemStack(ModItems.PISTOL_CASING.get(),1);
                ItemEntity created =
                        new ItemEntity(level(), getX(), getY(), getZ(), casing);


                created.setDefaultPickUpDelay();
                created.setDefaultPickUpDelay();
                created.setDeltaMovement(VecHelper.offsetRandomly(new Vec3(0,0.5f,0), level().random, .05f));
                level().addFreshEntity(created);
                return;
            }
        }
    }

    public void setTurret(boolean turret) {
        this.entityData.set(DATA_ID_TURRET, turret);
    }

    public boolean hasScanner() {
        return this.entityData.get(DATA_ID_SCANNER);
    }

    public void setScanner(boolean turret) {
        this.entityData.set(DATA_ID_SCANNER, turret);
    }



    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasChest()) {
            if (!this.level().isClientSide) {
                this.spawnAtLocation(Blocks.CHEST);
            }
            for (int i = 0; i < dogInventory.getContainerSize(); i++) {
                this.spawnAtLocation(dogInventory.getItem(i));
            }
            dogInventory.clearContent();

            this.setChest(false);
        }
    }


    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("robotdog_chest", this.hasChest());
        pCompound.putBoolean("robotdog_turret", this.hasTurret());
        pCompound.putInt("robotdog_paint", this.getPaintjob());
        pCompound.putBoolean("robotdog_armor", this.hasArmor());
        pCompound.putBoolean("robotdog_scanner", this.hasScanner());
        if (this.hasChest()) {
            ListTag nbttaglist = new ListTag();
            for (int i = 0; i < this.dogInventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.dogInventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag CompoundNBT = new CompoundTag();
                    CompoundNBT.putByte("Slot", (byte) i);
                    itemstack.save(CompoundNBT);
                    nbttaglist.add(CompoundNBT);
                }
            }
            pCompound.put("Items", nbttaglist);
        }

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setChest(pCompound.getBoolean("robotdog_chest"));
        this.setTurret(pCompound.getBoolean("robotdog_turret"));
        this.setPaintjob(pCompound.getInt("robotdog_paint"));
        this.setArmor(pCompound.getBoolean("robotdog_armor"));
        this.setScanner(pCompound.getBoolean("robotdog_scanner"));
        if (this.hasChest()) {
            if (dogInventory != null) {
                ListTag nbttaglist = pCompound.getList("Items", 10);
                this.initDogInventory();
                for (int i = 0; i < nbttaglist.size(); ++i) {
                    CompoundTag CompoundNBT = nbttaglist.getCompound(i);
                    int j = CompoundNBT.getByte("Slot") & 255;
                    this.dogInventory.setItem(j, ItemStack.of(CompoundNBT));
                }
            } else {
                ListTag nbttaglist = pCompound.getList("Items", 10);
                this.initDogInventory();
                for (int i = 0; i < nbttaglist.size(); ++i) {
                    CompoundTag CompoundNBT = nbttaglist.getCompound(i);
                    int j = CompoundNBT.getByte("Slot") & 255;
                    this.initDogInventory();
                    this.dogInventory.setItem(j, ItemStack.of(CompoundNBT));
                }
            }
            resetSlots();
        }

    }

    public SlotAccess getSlot(int pSlot) {
        return pSlot == 499 ? new SlotAccess() {
            public ItemStack get() {
                return RobotdogEntity.this.hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
            }

            public boolean set(ItemStack p_149485_) {
                if (p_149485_.isEmpty()) {
                    if (RobotdogEntity.this.hasChest()) {
                        RobotdogEntity.this.setChest(false);
                    }

                    return true;
                } else if (p_149485_.is(Items.CHEST)) {
                    if (!RobotdogEntity.this.hasChest()) {
                        RobotdogEntity.this.setChest(true);
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } : super.getSlot(pSlot);
    }

    private void equipChest(Player pPlayer, ItemStack pChestStack) {
        this.setChest(true);
        //this.playChestEquipsSound();
        if (!pPlayer.getAbilities().instabuild) {
            pChestStack.shrink(1);
        }
    }

    private void equipTurret(Player pPlayer, ItemStack pChestStack) {
        this.setTurret(true);
        //this.playChestEquipsSound();
    }

    @Override
    public void containerChanged(Container pContainer) {

    }
    protected void pickUpItem(ItemEntity pItemEntity) {
        if(!hasScanner()) return;
        ItemStack itemstack = pItemEntity.getItem();
        if (this.dogInventory.canAddItem(itemstack)) {
            int i = itemstack.getCount();
            //this.onItemPickup(pItemEntity);
            //this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
            //this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
            dogInventory.addItem(itemstack);
            //this.take(pItemEntity, itemstack.getCount());
            pItemEntity.discard();
        }
    }

    public boolean canTakeItem(ItemStack pItemstack) {
        return hasScanner();
    }
    class SearchForItemsGoal extends Goal {
        public SearchForItemsGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (!RobotdogEntity.this.hasScanner()) {
                return false;
            } else if (RobotdogEntity.this.getTarget() == null) {
                    List<ItemEntity> list = RobotdogEntity.this.level().getEntitiesOfClass(ItemEntity.class, RobotdogEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
                return !list.isEmpty();
            } else {
                return false;
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            List<ItemEntity> list = RobotdogEntity.this.level().getEntitiesOfClass(ItemEntity.class, RobotdogEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));

            ArrayList<ItemEntity> bulletList = new ArrayList<ItemEntity>();

            for(ItemEntity e : list){
                if(e.getItem().is(casingTag)){
                    bulletList.add(e);
                }
            }

            //ItemStack itemstack = RobotdogEntity.this.dogInventory.canAddItem(RobotdogEntity);
            if (!bulletList.isEmpty()) {
                if(RobotdogEntity.this.dogInventory.canAddItem(bulletList.get(0).getItem())) {
                    RobotdogEntity.this.getNavigation().moveTo(bulletList.get(0), (double) 1.2F);

                    if(distanceToSqr(bulletList.get(0)) <= 2){
                        RobotdogEntity.this.pickUpItem(bulletList.get(0));
                    }
                }
            }

        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            List<ItemEntity> list = RobotdogEntity.this.level().getEntitiesOfClass(ItemEntity.class, RobotdogEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (!list.isEmpty()) {
                if(RobotdogEntity.this.dogInventory.canAddItem(list.get(0).getItem())) {
                    RobotdogEntity.this.getNavigation().moveTo(list.get(0), (double) 1.2F);
                }
            }

        }
    }
}
