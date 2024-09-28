package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bosses.base.EntityCobaltGuardian;
import com.gempire.entities.projectiles.GuardianProjectileEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;

import java.util.EnumSet;

public class GuardianStrafeGoal extends Goal {
    public EntityCobaltGuardian gem;
    public final double speedModifier;
    public int attackIntervalMin;
    public final float attackRadiusSqr;
    public int attackTime = -1;
    public int seeTime;
    public boolean strafingClockwise;
    public boolean strafingBackwards;
    public int strafingTime = -1;

    public GuardianStrafeGoal(EntityCobaltGuardian gem, double p_25793_, int p_25794_, float p_25795_) {
        this.gem = gem;
        this.speedModifier = p_25793_;
        this.attackIntervalMin = p_25794_;
        this.attackRadiusSqr = p_25795_ * p_25795_;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        return this.gem.getTarget() != null;
    }
    public boolean canContinueToUse() {
        return (this.canUse() || !this.gem.getNavigation().isDone());
    }

    public void start() {
        super.start();
    }

    public void stop() {
        super.stop();
        this.seeTime = 0;
        this.attackTime = -1;
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

            attackTime--;

            if (!gem.isDashing && attackTime<=0) {
                GuardianProjectileEntity acidSpit = new GuardianProjectileEntity(this.gem.level(), this.gem);
                double d4 = livingentity.getEyeY() - (double) 1.1F;
                double d1 = livingentity.getX() - this.gem.getX();
                double d2 = d4 - acidSpit.getY();
                double d3 = livingentity.getZ() - this.gem.getZ();
                float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
                acidSpit.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
                this.gem.level().addFreshEntity(acidSpit);
                this.attackTime = this.attackIntervalMin;
            }
        }
    }
}
