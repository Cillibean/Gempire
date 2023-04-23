package com.gempire.entities.ai;

import com.gempire.entities.gems.EntitySpinel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;

public class EntityAIFollowSpinel extends Goal {
    public Mob follower;
    public LivingEntity toFollow;
    public double speed;

    public EntityAIFollowSpinel(Mob entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        List<LivingEntity> list = this.follower.level.<LivingEntity>getEntitiesOfClass(LivingEntity.class, this.follower.getBoundingBox().inflate(24.0D, 10.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        for (LivingEntity entity : list) {
            if (entity instanceof EntitySpinel) {
                double newDistance = entity.distanceToSqr(this.follower);
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.toFollow = entity;
                }
            }
        }
        return this.toFollow != null && this.follower.distanceToSqr(this.toFollow) > Math.pow(3, 2);
    }

    @Override
    public boolean canContinueToUse() {
        return this.toFollow != null && !this.follower.getNavigation().isDone() &&  this.follower.distanceToSqr(this.toFollow) > Math.pow(7, 2);
    }

    @Override
    public void start(){
        super.start();
        this.follower.getNavigation().moveTo(this.toFollow.getX(), this.toFollow.getY(), this.toFollow.getZ(), this.speed);
    }

    @Override
    public void stop() {
        this.toFollow = null;
        this.follower.getNavigation().stop();
    }
}
