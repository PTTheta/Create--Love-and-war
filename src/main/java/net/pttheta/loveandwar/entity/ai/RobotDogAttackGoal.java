package net.pttheta.loveandwar.entity.ai;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.pttheta.loveandwar.entity.ShotProjectile;
import net.pttheta.loveandwar.entity.custom.RobotdogEntity;
import net.pttheta.loveandwar.sound.ModSounds;

import java.util.function.Predicate;

public class RobotDogAttackGoal extends MeleeAttackGoal {
    private final RobotdogEntity entity;
    private int attackDelay = 4;
    private int ticksUntilNextAttack = 12;
    private boolean shouldCountTillNextAttack = false;
    private long lastCanUseCheck;
    private Path path;
    private final double speedModifier;
    public RobotDogAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.entity = ((RobotdogEntity) pMob);
        this.speedModifier = pSpeedModifier;
    }

    @Override
    public boolean canUse() {
        long i = this.mob.level().getGameTime();
        if (i - this.lastCanUseCheck < 20L) {
            return false;
        } else {
            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                this.path = this.mob.getNavigation().createPath(livingentity, entity.hasTurret()?10:0);
                if (this.path != null) {
                    return true;
                } else if(entity.hasTurret()){
                    path = null;
                    return true;
                } else {
                    return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }
            }
        }
    }

    @Override
    public void start() {
        super.start();
        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        this.mob.setAggressive(true);
        this.ticksUntilNextAttack = 0;
        attackDelay = 4;
        ticksUntilNextAttack = 12;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {

        if(entity.hasTurret()) {
            if(entity.hasTurretAmmo()) {
                projectileAttack(pEnemy, (float) pDistToEnemySqr);
                return;
            }
        }
        if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    public void projectileAttack(LivingEntity pTarget, float pDistanceFactor){
        if(entity.projectileAttackTick <= 0){
            performRangedAttack(pTarget,pDistanceFactor);
            entity.projectileAttackTick = 10;
        }

    }

    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {

        //entity.level().addFreshEntity(abstractarrow);
        boolean fireball = true;
        if(fireball) {
            Vec3 vec3 = this.entity.getViewVector(1.0F);
            double d4 = pTarget.getX() - this.entity.getX();
            double d5 = pTarget.getY(0.5D) - this.entity.getY(0.5D);
            double d6 = pTarget.getZ() - this.entity.getZ();
            double d7 = 2;
            ShotProjectile smallfireball = new ShotProjectile(this.entity.level(), this.entity, 0, 0, 0);
            smallfireball.setPos(smallfireball.getX(), this.entity.getY(1) + 0.25D, smallfireball.getZ());
            smallfireball.shoot((float)d4,(float)d5,(float)d6,5f,0);
            smallfireball.owner = this.entity.getOwner();
            this.entity.level().addFreshEntity(smallfireball);
            this.playSound(ModSounds.GUNSHOT.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        this.entity.ConsumeAmmo();
    }

    public RandomSource getRandom() {
        return entity.getRandom();
    }
    public void playSound(SoundEvent pSound, float pVolume, float pPitch) {
        entity.level().playSound((Player)null,  entity.getX(),  entity.getY(),  entity.getZ(), pSound,  entity.getSoundSource(), pVolume, pPitch);

    }

    public ItemStack getProjectile(ItemStack pShootable) {
        if (pShootable.getItem() instanceof ProjectileWeaponItem) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem)pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack itemstack = ProjectileWeaponItem.getHeldProjectile(entity, predicate);
            return net.minecraftforge.common.ForgeHooks.getProjectile(entity, pShootable, itemstack.isEmpty() ? new ItemStack(Items.ARROW) : itemstack);
        } else {
            return net.minecraftforge.common.ForgeHooks.getProjectile(entity, pShootable, ItemStack.EMPTY);
        }
    }

    protected AbstractArrow getArrow(ItemStack pArrowStack, float pVelocity) {
        return ProjectileUtil.getMobArrow(entity, pArrowStack, pVelocity);
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }


    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    @Override
    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
        if(entity.hasTurret()){
            return 10;
        } else return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + pAttackTarget.getBbWidth());
    }
}
