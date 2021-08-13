package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathNodeType;

import java.util.List;

public class EntityAIGuard extends Goal {
    public EntityGem follower;
    public double speed;

    public EntityAIGuard(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean shouldExecute() {
        return follower.getMovementType() == 0 && this.follower.getDistanceSq(this.follower.GUARD_POS[0], this.follower.GUARD_POS[1], this.follower.GUARD_POS[2]) > .25f;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.follower.getNavigator().noPath() && this.follower.getMovementType() == 0 && this.follower.getDistanceSq(this.follower.GUARD_POS[0], this.follower.GUARD_POS[1], this.follower.GUARD_POS[2]) > .25f;
    }

    @Override
    public void startExecuting(){
        super.startExecuting();
        this.follower.setPathPriority(PathNodeType.WATER, 0);
        this.follower.getNavigator().tryMoveToXYZ(this.follower.GUARD_POS[0], this.follower.GUARD_POS[1], this.follower.GUARD_POS[2], this.speed);
    }

    @Override
    public void resetTask() {
        this.follower.getNavigator().clearPath();
        this.follower.setPathPriority(PathNodeType.WATER, 0);
    }
}
