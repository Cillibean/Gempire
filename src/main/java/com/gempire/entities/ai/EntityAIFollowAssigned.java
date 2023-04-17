package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpodumene;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.List;

public class EntityAIFollowAssigned extends Goal {
    public EntityGem follower;
    public EntityGem toFollow;
    public double speed;

    public EntityAIFollowAssigned(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        List<EntityGem> list = this.follower.level.<EntityGem>getEntitiesOfClass(EntityGem.class, this.follower.getBoundingBox().inflate(24.0D, 10.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        if (!follower.getRebelled()) {
            for (EntityGem entity : list) {
                if (!entity.isSpectator() || !entity.isInvisible() && this.follower.ASSIGNED_ID != null) {
                    if (this.follower.ASSIGNED_ID.equals(entity.getUUID())) {
                        double newDistance = entity.distanceToSqr(this.follower);
                        if (newDistance <= maxDistance) {
                            maxDistance = newDistance;
                            this.toFollow = entity;
                        }
                    }
                }
            }
        }
        return this.toFollow != null && follower.getMovementType() == 3 && this.follower.distanceToSqr(this.toFollow) > Math.pow(3, 2);
    }

    @Override
    public boolean canContinueToUse() {
        return this.toFollow != null && !this.follower.getNavigation().isDone() && this.follower.getMovementType() == 3 && this.follower.distanceToSqr(this.toFollow) > Math.pow(7, 2);
    }

    @Override
    public void start(){
        super.start();
        if(!(this.follower instanceof EntitySpodumene)){
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        }
        this.follower.getNavigation().moveTo(this.toFollow.getX(), this.toFollow.getY(), this.toFollow.getZ(), this.speed);
        if(this.follower.distanceToSqr(this.toFollow) > Math.pow(12, 2)){
            this.follower.setPos(this.toFollow.getX(), this.toFollow.getY(), this.toFollow.getZ());
        }
    }

    @Override
    public void stop() {
        this.toFollow = null;
        this.follower.getNavigation().stop();
        if(!(this.follower instanceof EntitySpodumene)) {
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        }
    }
}
