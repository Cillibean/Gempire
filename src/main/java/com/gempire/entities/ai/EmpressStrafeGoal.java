package com.gempire.entities.ai;

import com.gempire.entities.bosses.base.EntityAlabasterEmpress;
import com.gempire.entities.bosses.base.EntityFuchsiaPaladin;
import com.gempire.entities.projectiles.LifeLeechOrb;
import com.gempire.entities.projectiles.WhiteAttackEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class EmpressStrafeGoal extends Goal {
    public EntityAlabasterEmpress gem;
    public final double speedModifier;

    public EmpressStrafeGoal(EntityAlabasterEmpress gem, double p_25793_) {
        this.gem = gem;
        this.speedModifier = p_25793_;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        return this.gem.getTarget() != null && gem.shooting;
    }
    public boolean canContinueToUse() {
        return (this.canUse() || !this.gem.getNavigation().isDone());
    }

    public void start() {
        super.start();
    }

    public void stop() {
        super.stop();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.gem.getTarget();
        if (livingentity != null) {
            this.gem.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            if (!gem.beaming) {
                WhiteAttackEntity acidSpit = new WhiteAttackEntity(this.gem.level(), this.gem);
                acidSpit.setPos(gem.getX(), gem.getY()+3, gem.getZ());
                double d4 = livingentity.getEyeY() - (double) 1.1F;
                double d1 = livingentity.getX() - this.gem.getX();
                double d2 = d4 - acidSpit.getY();
                double d3 = livingentity.getZ() - this.gem.getZ();
                float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
                acidSpit.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
                this.gem.level().addFreshEntity(acidSpit);
                gem.shooting = false;
                gem.orbcooldown = 100;
                this.stop();
            }
        }
    }
}
