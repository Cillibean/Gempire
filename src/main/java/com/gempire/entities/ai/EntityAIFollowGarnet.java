package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpodumene;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.List;
import java.util.UUID;

public class EntityAIFollowGarnet extends Goal {
    public EntityGem follower;
    public EntityGem toFollow;
    public double speed;

    public EntityAIFollowGarnet(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        List<EntityGem> list = this.follower.level().getEntitiesOfClass(EntityGem.class, this.follower.getBoundingBox().inflate(7.0D, 5.0D, 7.0D));
        double maxDistance = Double.MAX_VALUE;
        if (!follower.getRebelled() && this.follower.getSludgeAmount() < 5 && follower.followingGarnet) {
                for (EntityGem entity : list) {
                    if (entity != follower) {
                        for (UUID uuid : entity.OWNERS) {
                            if (this.follower.isOwner(uuid)) {
                                if (!entity.isSpectator() || !entity.isInvisible() && follower.flocksTo(entity)) {
                                    double newDistance = entity.distanceToSqr(this.follower);
                                    if (newDistance <= maxDistance) {
                                        maxDistance = newDistance;
                                        this.toFollow = entity;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        //System.out.println("boolean "+(this.toFollow != null && follower.getMovementType() == 1 && follower.followingGarnet));
        //if (this.toFollow != null) System.out.println("distance bool "+(this.follower.distanceToSqr(this.toFollow) > Math.pow(3, 2)));
        return this.toFollow != null && follower.getMovementType() == 1 && follower.followingGarnet && follower.followCooldown == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.toFollow != null && !this.follower.getNavigation().isDone() && this.follower.getMovementType() == 1 && this.follower.distanceToSqr(this.toFollow) > Math.pow(7, 2) && follower.followingGarnet && follower.followCooldown == 0;
    }

    @Override
    public void start(){
        super.start();
        if(!(this.follower instanceof EntitySpodumene)){
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        }
        this.follower.getNavigation().moveTo(this.toFollow.getX(), this.toFollow.getY(), this.toFollow.getZ(), this.speed);
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
