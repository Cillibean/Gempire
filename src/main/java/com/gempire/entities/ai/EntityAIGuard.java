package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.List;

public class EntityAIGuard extends Goal {
    public EntityGem follower;
    public double speed;

    public EntityAIGuard(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        return follower.getMovementType() == 0 && this.follower.distanceToSqr(follower.GUARD_POS.getX(), follower.GUARD_POS.getY(), follower.GUARD_POS.getZ()) > .25f;
    }

    @Override
    public boolean canContinueToUse() {
        return !this.follower.getNavigation().isDone() && this.follower.getMovementType() == 0 && this.follower.distanceToSqr(follower.GUARD_POS.getX(), follower.GUARD_POS.getY(), follower.GUARD_POS.getZ()) > .25f;
    }

    @Override
    public void start(){
        super.start();
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.follower.getNavigation().moveTo(follower.GUARD_POS.getX(), follower.GUARD_POS.getY(), follower.GUARD_POS.getZ(), this.speed);
    }

    @Override
    public void stop() {
        this.follower.getNavigation().stop();
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
    }
}
