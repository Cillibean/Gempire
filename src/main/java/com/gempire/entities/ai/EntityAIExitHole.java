package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpodumene;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.List;

public class EntityAIExitHole extends Goal {
    public EntityGem follower;
    public double speed;

    public EntityAIExitHole(EntityGem entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        List<LivingEntity> list = this.follower.level().<LivingEntity>getEntitiesOfClass(LivingEntity.class, this.follower.getBoundingBox().inflate(24.0D, 10.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        return follower.justEmerged();
    }

    @Override
    public boolean canContinueToUse() {
        return /*!follower.getOwned() &&*/ !follower.level().getBlockState(this.follower.getOnPos().offset(0, follower.exitHoleSize(),0)).is(Blocks.AIR);
    }

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void tick() {
        BlockPos currentPos = follower.getOnPos();
        if (follower.level().getBlockState(currentPos.offset(1, 0, 0)).is(Blocks.AIR)) {
            while (!follower.level().getBlockState(currentPos.offset(0, follower.exitHoleSize(),0)).is(Blocks.AIR)) {
                this.follower.moveTo(currentPos.offset(1, 0, 0).getCenter());
                currentPos = currentPos.offset(1, 0, 0);
            }
        } else if (follower.level().getBlockState(currentPos.offset(-1, 0, 0)).is(Blocks.AIR)) {
            while (!follower.level().getBlockState(currentPos.offset(0, follower.exitHoleSize(),0)).is(Blocks.AIR)) {
                this.follower.moveTo(currentPos.offset(-1, 0, 0).getCenter());
                currentPos = currentPos.offset(-1, 0, 0);
            }
        } else if (follower.level().getBlockState(currentPos.offset(0, 0, 1)).is(Blocks.AIR)) {
            while (!follower.level().getBlockState(currentPos.offset(0, follower.exitHoleSize(),0)).is(Blocks.AIR)) {
                this.follower.moveTo(currentPos.offset(0, 0, 1).getCenter());
                currentPos = currentPos.offset(0, 0, 1);
            }
        } else if (follower.level().getBlockState(currentPos.offset(0, 0, -1)).is(Blocks.AIR)) {
            while (!follower.level().getBlockState(currentPos.offset(0, follower.exitHoleSize(),0)).is(Blocks.AIR)) {
                this.follower.moveTo(currentPos.offset(0, 0, -1).getCenter());
                currentPos = currentPos.offset(0, 0, -1);
            }
        }
        if (follower.level().getBlockState(currentPos.offset(0, follower.exitHoleSize(),0)).is(Blocks.AIR)) stop();
        super.tick();
    }

    @Override
    public void stop() {
        follower.emerged = false;
        this.follower.getNavigation().stop();
        if(!(this.follower instanceof EntitySpodumene)) {
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        }
    }
}
