package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityTourmaline;
import com.gempire.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class EntityAIDoFarm extends Goal {
        public EntityTourmaline follower;
        public BlockPos target;
        public double speed;

        public EntityAIDoFarm(EntityTourmaline entityIn, double speedIn) {
            this.follower = entityIn;
            this.speed = speedIn;
        }

        @Override
        public boolean canUse() {
            BlockPos hopper = BlockPos.ZERO;
            boolean found = false;
            for(int x = -4; x < 5; x++){
                for(int y = -2; y < 3; y++){
                    for(int z = -4; z < 5; z++){
                        if(!found) {
                            if (this.follower.level.getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == Blocks.HOPPER) {
                                hopper = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                                System.out.println("Hopper Found");
                            }
                        }
                    }
                }
            }
            if(found){
                double maxDistance = Double.MAX_VALUE;
                double newDistance = this.follower.distanceToSqr(hopper.getX(), hopper.getY(), hopper.getZ());
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.target = hopper;
                }
            }
            return this.target != null && this.target != BlockPos.ZERO;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && !this.follower.getNavigation().isDone() &&
                    this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) > Math.pow(4, 2);
        }

        @Override
        public void start(){
            super.start();
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
            this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
            if(this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4){
                if(this.follower.level.getBlockState(this.target).getBlock() == Blocks.HOPPER){
                    if(this.follower.level.getBlockState(this.target.north()).getBlock() == Blocks.IRON_BARS){
                        this.follower.level.explode(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Explosion.BlockInteraction.NONE);
                        this.follower.level.setBlockAndUpdate(this.target, ModBlocks.DRILL_BLOCK.get().defaultBlockState());
                        this.follower.level.setBlockAndUpdate(this.target.north(), Blocks.AIR.defaultBlockState());
                        this.follower.level.setBlockAndUpdate(this.target.south(), Blocks.AIR.defaultBlockState());
                        this.follower.level.setBlockAndUpdate(this.target.west(), Blocks.AIR.defaultBlockState());
                        this.follower.level.setBlockAndUpdate(this.target.east(), Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

        @Override
        public void stop() {
            this.target = null;
            this.follower.getNavigation().stop();
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        }
    }
