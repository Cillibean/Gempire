package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpodumene;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.List;

public class EntityAIExitHole extends Goal {
    public EntityGem follower;
    public LivingEntity toFollow;
    public double speed;

    public EntityAIExitHole(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        List<LivingEntity> list = this.follower.level().<LivingEntity>getEntitiesOfClass(LivingEntity.class, this.follower.getBoundingBox().inflate(24.0D, 10.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        if (!follower.getRebelled()) {
            if (this.follower.getSludgeAmount() < 5) {

            }
        }
        return this.toFollow != null && follower.getMovementType() == 2 && this.follower.distanceToSqr(this.toFollow) > Math.pow(3, 2);
    }

    @Override
    public boolean canContinueToUse() {
        return this.toFollow != null && !this.follower.getNavigation().isDone() && this.follower.getMovementType() == 2 && this.follower.distanceToSqr(this.toFollow) > Math.pow(7, 2);
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
