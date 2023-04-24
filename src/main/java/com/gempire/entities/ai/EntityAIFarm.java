package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityTourmaline;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityAIFarm extends Goal {
    public EntityTourmaline follower;
    public BlockPos target;
    public double speed;
    public EntityAIFarm(EntityTourmaline entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }
    @Override
    public boolean canUse() {
        BlockPos crop = BlockPos.ZERO;
        boolean found = false;
            for (int x = -4; x < 5; x++) {
                for (int y = -2; y < 3; y++) {
                    for (int z = -4; z < 5; z++) {
                        if (!found) {
                            if (this.follower.level.getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() instanceof CropBlock) {
                                crop = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                            } else if (this.follower.level.getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == Blocks.FARMLAND && this.follower.level.getBlockState(this.follower.blockPosition().offset(x, y + 1, z)).getBlock() == Blocks.AIR) {
                                crop = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                            }
                        }
                    }
                }
            }
            if (found) {
                double maxDistance = Double.MAX_VALUE;
                double newDistance = this.follower.distanceToSqr(crop.getX(), crop.getY(), crop.getZ());
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.target = crop;
                }
            }
        return this.target != null && this.target != BlockPos.ZERO && this.follower.getMovementType() == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && !this.follower.getNavigation().isDone() &&
                this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) > Math.pow(4, 2) && this.follower.getMovementType() == 0;
    }

    @Override
    public void start(){
        super.start();
        AABB aabb = follower.getBoundingBox().inflate(16.0D);
        Stream<BlockState> blocks = follower.level.getBlockStates(aabb);
        List<BlockState> blockStates = blocks.toList();
        for (BlockState block : blockStates) {
            if (this.follower.level.getBlockState(this.target) == block) {
                if (block.getBlock() == Blocks.FARMLAND) {
                    if (this.follower.level.getBlockState(this.target.above()).getBlock() == Blocks.AIR) {
                        if (this.follower.itemCheck(Items.WHEAT_SEEDS)) {
                            this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                            if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                                if (this.follower.consumeItemCheck(Items.WHEAT_SEEDS)) {
                                    this.follower.level.setBlockAndUpdate(target.above(), Blocks.WHEAT.defaultBlockState());
                                }
                            }
                        } else if (this.follower.itemCheck(Items.BEETROOT_SEEDS)) {
                            this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                            if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                                if (this.follower.consumeItemCheck(Items.BEETROOT_SEEDS)) {
                                    this.follower.level.setBlockAndUpdate(target.above(), Blocks.BEETROOTS.defaultBlockState());
                                }
                            }
                        } else if (this.follower.itemCheck(Items.CARROT)) {
                            this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                            if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                                if (this.follower.consumeItemCheck(Items.CARROT)) {
                                    this.follower.level.setBlockAndUpdate(target.above(), Blocks.CARROTS.defaultBlockState());
                                }
                            }
                        } else if (this.follower.itemCheck(Items.POTATO)) {
                            this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                            if (this.follower.consumeItemCheck(Items.POTATO)) {
                                this.follower.level.setBlockAndUpdate(target.above(), Blocks.POTATOES.defaultBlockState());
                            }
                        }
                    }
                }
                if (block.getBlock() instanceof CropBlock) {
                    if (((CropBlock) (this.follower.level.getBlockState(this.target).getBlock())).isMaxAge(this.follower.level.getBlockState(this.target))) {
                        this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                        if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                            //this.follower.level.getBlockState(this.target).getBlock().dropResources(this.follower.level.getBlockState(this.target), this.follower.level, this.target);
                            System.out.println("break attempt");
                            this.follower.level.setBlockAndUpdate(target, Blocks.AIR.defaultBlockState());
                        }
                    } else if (follower.consumeItemCheck(Items.BONE_MEAL) ) {
                        System.out.println("grow attempt");
                        this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                        if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                            ((CropBlock) (this.follower.level.getBlockState(this.target).getBlock())).performBonemeal((ServerLevel) follower.level, this.follower.getRandom(), target, this.follower.level.getBlockState(this.target));
                        }
                    }
                }
            }
            this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        }
    }

    @Override
    public void stop() {
        this.target = null;
        this.follower.getNavigation().stop();
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
    }
}