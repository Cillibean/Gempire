package com.gempire.entities.ai;

import com.gempire.entities.bosses.base.EntityAlabasterEmpress;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;

import java.util.EnumSet;

public class EmpressLaserGoal extends Goal {
    private final EntityAlabasterEmpress empress;
    private int attackTime;

    public EmpressLaserGoal(EntityAlabasterEmpress p_32871_) {
        this.empress = p_32871_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.empress.getTarget();
        return livingentity != null && livingentity.isAlive() && empress.beaming;
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse() && this.empress.getTarget() != null && empress.beaming;
    }

    public void start() {
        this.attackTime = -10;
        this.empress.getNavigation().stop();
        LivingEntity livingentity = this.empress.getTarget();
        if (livingentity != null) {
            this.empress.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
        }

        this.empress.hasImpulse = true;
    }

    public void stop() {
        this.empress.setActiveAttackTarget(0);
        this.empress.setTarget((LivingEntity)null);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.empress.getTarget();
        if (livingentity != null) {
            this.empress.getNavigation().stop();
            this.empress.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
            if (!this.empress.hasLineOfSight(livingentity)) {
                this.empress.setTarget((LivingEntity)null);
            } else {
                ++this.attackTime;
                if (this.attackTime == 0) {
                    this.empress.setActiveAttackTarget(livingentity.getId());
                    if (!this.empress.isSilent()) {
                        this.empress.level().broadcastEntityEvent(this.empress, (byte)21);
                    }
                } else if (this.attackTime >= this.empress.getAttackDuration()) {
                    float f = 1.0F;
                    if (this.empress.level().getDifficulty() == Difficulty.HARD) {
                        f += 2.0F;
                    }

                    livingentity.hurt(this.empress.damageSources().indirectMagic(this.empress, this.empress), f);
                    livingentity.hurt(this.empress.damageSources().mobAttack(this.empress), (float)this.empress.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    this.empress.setTarget((LivingEntity)null);
                }

                super.tick();
            }
        }
    }
}
