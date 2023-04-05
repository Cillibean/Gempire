package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.UUID;

public class EntityAiAssignGems extends Goal {
    public EntityGem thisGem;
    public double speedIn;
    public LivingEntity gemToTame;
    public EntityAiAssignGems(EntityGem entityIn, double speedIn) {
        this.speedIn = speedIn;
        this.thisGem = entityIn;
    }


    @Override
    public boolean canUse() {
        List<LivingEntity> list = this.thisGem.level.getEntitiesOfClass(LivingEntity.class, this.thisGem.getBoundingBox().inflate(24.0D, 10.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        for (LivingEntity entity : list) {
            if (entity instanceof EntityGem) {
                if (!((EntityGem) entity).getOwned()) {
                    double newDistance = entity.distanceToSqr(this.thisGem);
                    if (newDistance <= maxDistance) {
                        maxDistance = newDistance;
                        this.gemToTame = entity;
                    }
                }
            }
        }
        return this.gemToTame != null && this.thisGem.distanceToSqr(this.gemToTame) > Math.pow(3, 2);
    }

    @Override
    public boolean canContinueToUse() {
        return this.gemToTame != null && this.thisGem.distanceToSqr(this.gemToTame) > Math.pow(3, 2);
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Going to Tame");
        this.thisGem.getNavigation().moveTo(this.gemToTame.getX(), this.gemToTame.getY(), this.gemToTame.getZ(), this.speedIn);
        if (this.thisGem.distanceToSqr(gemToTame) < Math.pow(2, 1))
        {
            if (gemToTame instanceof EntityGem) {
                for (UUID uuid : thisGem.OWNERS) {
                    ((EntityGem) gemToTame).OWNERS.add(uuid);
                }
            }
            System.out.println("TAME");
        }
    }

    @Override
    public void stop() {
        this.gemToTame = null;
        this.thisGem.getNavigation().stop();
    }
}
