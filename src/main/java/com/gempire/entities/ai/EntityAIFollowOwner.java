package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class EntityAIFollowOwner extends Goal {
    public EntityGem follower;
    public LivingEntity owner;
    public double speed;

    public EntityAIFollowOwner(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean shouldExecute() {
        List<PlayerEntity> list = this.follower.world.<PlayerEntity>getEntitiesWithinAABB(PlayerEntity.class, this.follower.getBoundingBox().grow(20.0D, 10.0D, 20.0D));
        double maxDistance = Double.MAX_VALUE;
        for (PlayerEntity entity : list) {
            if (!entity.isSpectator() || !entity.isInvisible() && this.follower.isOwner(entity)) {
                double newDistance = entity.getDistanceSq(this.follower);
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.owner = entity;
                }
            }
        }
        return this.owner != null && follower.getMovementType() == 2 && this.follower.getDistanceSq(this.owner) > Math.pow(3, 2);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.owner != null && !this.follower.getNavigator().noPath() && this.follower.getMovementType() == 2 && this.follower.getDistanceSq(this.owner) > Math.pow(7, 2);
    }

    @Override
    public void startExecuting(){
        super.startExecuting();
        this.follower.setPathPriority(PathNodeType.WATER, 0);
        this.follower.getNavigator().tryMoveToXYZ(this.owner.getPosX(), this.owner.getPosY(), this.owner.getPosZ(), this.speed);
        if(this.follower.getDistanceSq(this.owner) > Math.pow(16, 2)){
            this.follower.setPosition(this.owner.getPosX(), this.owner.getPosY(), this.owner.getPosZ());
        }
    }

    @Override
    public void resetTask() {
        this.owner = null;
        this.follower.getNavigator().clearPath();
        this.follower.setPathPriority(PathNodeType.WATER, 0);
    }
}
