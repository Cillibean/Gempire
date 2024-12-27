package com.gempire.entities.ai;

import com.gempire.entities.bosses.base.EntityCobaltGuardian;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class EntityAIGuardianDash extends Goal {
    public EntityCobaltGuardian follower;
    public Player target;
    public double speed;
    public int tick;

    public EntityAIGuardianDash(EntityCobaltGuardian entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        if (follower.getTarget() instanceof Player) {
            target = (Player) follower.getTarget();
        }
        return this.target != null && follower.getDashing() && this.follower.distanceToSqr(this.target) > Math.pow(3, 2);
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.follower.getDashing() && this.follower.distanceToSqr(this.target) > Math.pow(7, 2) && follower.dashCooldown == 0;
    }

    @Override
    public void start(){
        super.start();
        if (!follower.level().isClientSide) follower.triggerAnim("misc_controller", "dash");
        tick = 12;
    }

    @Override
    public void tick() {
        super.tick();
        this.follower.getLookControl().setLookAt(this.target.getX(), this.follower.getEyeY(), this.target.getZ());
        System.out.println(tick);
        if (tick <= 0) {
            this.follower.setPos(this.target.getX(), this.target.getY(), this.target.getZ());
            follower.isDashing = false;
            follower.dashCooldown = 200;
            stop();
        } else tick--;
    }

    @Override
    public void stop() {
        this.target = null;
        this.follower.getNavigation().stop();
    }
}
