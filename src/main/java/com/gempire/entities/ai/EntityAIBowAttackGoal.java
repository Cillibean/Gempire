package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;

import java.util.EnumSet;

public class EntityAIBowAttackGoal extends Goal {
    public EntityGem gem;
    public final double speedModifier;
    public int attackIntervalMin;
    public final float attackRadiusSqr;
    public int attackTime = -1;
    public int seeTime;
    public boolean strafingClockwise;
    public boolean strafingBackwards;
    public int strafingTime = -1;

    public EntityAIBowAttackGoal(EntityGem gem, double p_25793_, int p_25794_, float p_25795_) {
        this.gem = gem;
        this.speedModifier = p_25793_;
        this.attackIntervalMin = p_25794_;
        this.attackRadiusSqr = p_25795_ * p_25795_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setMinAttackInterval(int p_25798_) {
        this.attackIntervalMin = p_25798_;
    }

    public boolean canUse() {
        return this.gem.getTarget() == null ? false : this.isHoldingBow();
    }

    protected boolean isHoldingBow() {
        return this.gem.isHolding(is -> is.getItem() instanceof BowItem);
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.gem.getNavigation().isDone()) && this.isHoldingBow();
    }

    public void start() {
        super.start();
    }

    public void stop() {
        super.stop();
        this.seeTime = 0;
        this.attackTime = -1;
        this.gem.stopUsingItem();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.gem.getTarget();
        if (livingentity != null) {
            double d0 = this.gem.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            boolean flag = this.gem.getSensing().hasLineOfSight(livingentity);
            boolean flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }

            if (flag) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                this.gem.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.gem.getNavigation().moveTo(livingentity, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.gem.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.gem.getRandom().nextFloat() < 0.3D) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }

                this.gem.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.gem.lookAt(livingentity, 30.0F, 30.0F);
            } else {
                this.gem.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            }

            if (this.gem.isUsingItem()) {
                if (!flag && this.seeTime < -60) {
                    this.gem.stopUsingItem();
                } else if (flag) {
                    int i = this.gem.getTicksUsingItem();
                    if (i >= 20) {
                        this.gem.stopUsingItem();
                        this.gem.performRangedAttack(livingentity, BowItem.getPowerForTime(i));
                        this.attackTime = this.attackIntervalMin;
                    }
                }
            } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                this.gem.startUsingItem(ProjectileUtil.getWeaponHoldingHand(this.gem, item -> item instanceof BowItem));
            }

        }
    }
}
