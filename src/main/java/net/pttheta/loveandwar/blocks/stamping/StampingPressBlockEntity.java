package net.pttheta.loveandwar.blocks.stamping;

import com.google.common.collect.ImmutableMap;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.pttheta.loveandwar.item.ModItems;
import net.pttheta.loveandwar.recipe.ModRecipes;
import net.pttheta.loveandwar.recipe.stamping.StampingRecipe;
import net.pttheta.loveandwar.blocks.stamping.StampingBehaviour.StampingBehaviourSpecifics;
import net.pttheta.loveandwar.util.ModTags;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class StampingPressBlockEntity extends KineticBlockEntity implements StampingBehaviourSpecifics {
    private final ItemStackHandler templateHolder = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private int ticksSinceSwap = 0;

    public ItemStack getRenderStack() {
        return templateHolder.getStackInSlot(0);
    }
    private static final Object stampingRecipesKey = new Object();
    public StampingBehaviour stampingBehaviour;
    private int tracksCreated;
    public StampingPressBlockEntity(BlockEntityType<? extends StampingPressBlockEntity> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        stampingBehaviour = new StampingBehaviour(this);
        behaviours.add(stampingBehaviour);

       registerAwardables(behaviours, AllAdvancements.PRESS,
                AllAdvancements.TRACK_CRAFTING);
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return new AABB(worldPosition).expandTowards(0, -1.5, 0)
                .expandTowards(0, 1, 0);
    }

    public void onItemDrawn(ItemStack result) {
        //award(AllAdvancements.PRESS);
        if (AllTags.AllBlockTags.TRACKS.matches(result))
            tracksCreated += result.getCount();
        if (tracksCreated >= 1000) {
            award(AllAdvancements.TRACK_CRAFTING);
            tracksCreated = 0;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        dropTemplate();
    }

    public void dropTemplate(){
        SimpleContainer inventory = new SimpleContainer(templateHolder.getSlots());
        for(int i = 0; i < templateHolder.getSlots(); i++) {
            inventory.setItem(i, templateHolder.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public void templateInteract(ItemStack item, Player player, InteractionHand hand){
        boolean isTemplate = item.is(ModTags.Items.STAMPING_TEMPLATE);
        

        if(ticksSinceSwap < 5) return;
        if(player.isShiftKeyDown()){
            //dropTemplate();
            player.setItemInHand(hand,templateHolder.getStackInSlot(0));
            templateHolder.setStackInSlot(0,item);
            ticksSinceSwap = 0;
        } else if(isTemplate){
            player.setItemInHand(hand,templateHolder.getStackInSlot(0));
            templateHolder.setStackInSlot(0,new ItemStack(item.getItem(),1));
            ticksSinceSwap = 0;
        }
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        if (getBehaviour(AdvancementBehaviour.TYPE).isOwnerPresent())
            compound.putInt("TracksCreated", tracksCreated);
        compound.put("template",templateHolder.serializeNBT());
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        tracksCreated = compound.getInt("TracksCreated");
        templateHolder.deserializeNBT(compound.getCompound("template"));
    }

    public StampingBehaviour getStampingBehaviour() {
        return stampingBehaviour;
    }

    public boolean tryProcessInWorld(ItemEntity itemEntity, boolean simulate) {
        ItemStack item = itemEntity.getItem();
        Optional<StampingRecipe> recipe = getRecipes(item,templateHolder.getStackInSlot(0));
        if (!recipe.isPresent())
            return false;
        if (simulate)
            return true;
        if(templateHolder.getStackInSlot(0).isEmpty()) return false;
        ItemStack itemCreated = ItemStack.EMPTY;
        stampingBehaviour.particleItems.add(item);
        if (canProcessInBulk() || item.getCount() == 1) {
            RecipeApplier.applyRecipeOn(itemEntity, recipe.get());
            itemCreated = itemEntity.getItem()
                    .copy();
        } else {
            for (ItemStack result : RecipeApplier.applyRecipeOn(level, ItemHandlerHelper.copyStackWithSize(item, 1),
                    recipe.get())) {
                if (itemCreated.isEmpty())
                    itemCreated = result.copy();
                ItemEntity created =
                        new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), result);
                created.setDefaultPickUpDelay();
                created.setDeltaMovement(VecHelper.offsetRandomly(Vec3.ZERO, level.random, .05f));
                level.addFreshEntity(created);
            }
            item.shrink(1);
        }

        if (!itemCreated.isEmpty())
            onItemDrawn(itemCreated);
        return true;
    }

    @Override
    public boolean tryProcessOnBelt(TransportedItemStack input, List<ItemStack> outputList, boolean simulate) {
        Optional<StampingRecipe> recipe = getRecipes(input.stack,templateHolder.getStackInSlot(0));
        if (!recipe.isPresent())
            return false;
        if (simulate)
            return true;
        if(templateHolder.getStackInSlot(0).isEmpty()) return false;
        stampingBehaviour.particleItems.add(input.stack);
        List<ItemStack> outputs = RecipeApplier.applyRecipeOn(level,
                canProcessInBulk() ? input.stack : ItemHandlerHelper.copyStackWithSize(input.stack, 1), recipe.get());

        for (ItemStack created : outputs) {
            if (!created.isEmpty()) {
                onItemDrawn(created);
                break;
            }
        }

        outputList.addAll(outputs);
        return true;
    }

    @Override
    public boolean canProcessInBulk() {
        return AllConfigs.server().recipes.bulkPressing.get();
    }

    @Override
    public void onStampingCompleted() {

    }

    public static <C extends Container> boolean canCompress(Recipe<C> recipe) {
        if (!(recipe instanceof CraftingRecipe) || !AllConfigs.server().recipes.allowShapedSquareInPress.get())
            return false;
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        return (ingredients.size() == 4 || ingredients.size() == 9) && ItemHelper.matchAllIngredients(ingredients);
    }

    @Override
    public int getParticleAmount() {
        return 15;
    }
    @Override
    public float getKineticSpeed() {
        return getSpeed();
    }

    @Override
    public void setTemplate(ItemStack stack) {
        templateHolder.setStackInSlot(0,stack);
    }

    @Override
    public void tick() {
        super.tick();
        ticksSinceSwap++;
    }


    private static final RecipeWrapper pressingInv = new RecipeWrapper(new ItemStackHandler(1));
    public Optional<StampingRecipe> getRecipe(ItemStack item,ItemStack template) {
        Optional<StampingRecipe> assemblyRecipe =
                SequencedAssemblyRecipe.getRecipe(level, item, ModRecipes.STAMPING_TYPE.get(), StampingRecipe.class);
        if (assemblyRecipe.isPresent())
            return assemblyRecipe;

        pressingInv.setItem(0, item);
        pressingInv.setItem(1,templateHolder.getStackInSlot(0));
        return find(pressingInv, level);
    }

    public Optional<StampingRecipe> getRecipes(ItemStack item,ItemStack template) {
        Stream<StampingRecipe> assemblyRecipe =
                SequencedAssemblyRecipe.getRecipes(level, item, ModRecipes.STAMPING_TYPE.get(), StampingRecipe.class);

        List<StampingRecipe> recipes = getLevel().getRecipeManager().getAllRecipesFor(ModRecipes.STAMPING_TYPE.get());
        for( StampingRecipe recipe : recipes)
        {
            ItemStack tem = recipe.getTemplate();
            Ingredient input = recipe.getIngredient();
            boolean test = input.test(item);
            if(tem.getItem() == template.getItem() && recipe.getIngredient().test(item)){
                return Optional.ofNullable(recipe);
            }
        }

        if(assemblyRecipe.count() > 0)
        {
            for( StampingRecipe recipe : assemblyRecipe.toList())
            {
                if(recipe.getTemplate() == template){
                    return Optional.ofNullable(recipe);
                }
            }
        }

        pressingInv.setItem(0, item);
        return find(pressingInv, level);
    }

    public Optional<StampingRecipe> find(RecipeWrapper inv, Level world) {
        var sequenced = SequencedAssemblyRecipe.getRecipe(level, inv.getItem(0), ModRecipes.STAMPING_TYPE.get(), StampingRecipe.class);
        if(sequenced.isPresent()) {
            return sequenced;
        }
        return world.getRecipeManager().getRecipeFor(ModRecipes.STAMPING_TYPE.get(), inv, world);
    }

    public <C extends Container, T extends Recipe<C>> Optional<StampingRecipe> getRecipeFor(RecipeType<T> pRecipeType, C pInventory, Level pLevel,ItemStack template) {
        List<StampingRecipe> recipes = getLevel().getRecipeManager().getAllRecipesFor(ModRecipes.STAMPING_TYPE.get());
        for( StampingRecipe recipe : recipes)
        {
            if(recipe.getTemplate() == template){
                return Optional.ofNullable(recipe);
            }
        }

        return null;
    }

    //@Override
    //protected boolean isRunning() {
        //return drawingBehaviour.running;
    //}

    protected Object getRecipeCacheKey() {
        return stampingRecipesKey;
    }

}
