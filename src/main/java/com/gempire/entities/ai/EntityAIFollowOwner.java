package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpodumene;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;

import java.util.List;
import java.util.UUID;

public class EntityAIFollowOwner extends Goal {
    public EntityGem follower;
    public LivingEntity toFollow;
    public double speed;

    public EntityAIFollowOwner(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean shouldExecute() {
        List<LivingEntity> list = this.follower.world.<LivingEntity>getEntitiesWithinAABB(LivingEntity.class, this.follower.getBoundingBox().grow(24.0D, 10.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        for (LivingEntity entity : list) {
            if (!entity.isSpectator() || !entity.isInvisible() && this.follower.isOwner(entity) && this.follower.FOLLOW_ID != null) {
                if(this.follower.FOLLOW_ID.equals(entity.getUniqueID())) {
                    double newDistance = entity.getDistanceSq(this.follower);
                    if (newDistance <= maxDistance) {
                        maxDistance = newDistance;
                        this.toFollow = entity;
                    }
                }
            }
        }
        return this.toFollow != null && follower.getMovementType() == 2 && this.follower.getDistanceSq(this.toFollow) > Math.pow(3, 2);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.toFollow != null && !this.follower.getNavigator().noPath() && this.follower.getMovementType() == 2 && this.follower.getDistanceSq(this.toFollow) > Math.pow(7, 2);
    }

    @Override
    public void startExecuting(){
        super.startExecuting();
        if(!(this.follower instanceof EntitySpodumene)){
            this.follower.setPathPriority(PathNodeType.WATER, 0);
        }
        this.follower.getNavigator().tryMoveToXYZ(this.toFollow.getPosX(), this.toFollow.getPosY(), this.toFollow.getPosZ(), this.speed);
        if(this.follower.getDistanceSq(this.toFollow) > Math.pow(12, 2)){
            this.follower.setPosition(this.toFollow.getPosX(), this.toFollow.getPosY(), this.toFollow.getPosZ());
        }
    }

    @Override
    public void resetTask() {
        this.toFollow = null;
        this.follower.getNavigator().clearPath();
        if(!(this.follower instanceof EntitySpodumene)) {
            this.follower.setPathPriority(PathNodeType.WATER, 0);
        }
    }
}
