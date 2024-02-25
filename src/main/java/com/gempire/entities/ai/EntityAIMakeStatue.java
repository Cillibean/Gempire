package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityLarimar;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class EntityAIMakeStatue extends Goal {
    public EntityLarimar follower;
    public BlockPos target;
    public double speed;
    public int statueTier;

    public EntityAIMakeStatue(EntityLarimar entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        if (follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem) {
            BlockPos hopper = BlockPos.ZERO;
            boolean found = false;
            for (int x = -4; x < 5; x++) {
                for (int y = -2; y < 3; y++) {
                    for (int z = -4; z < 5; z++) {
                        if (!found) {
                            if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == ModBlocks.PRIMED_ICE.get()) {
                                hopper = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                                System.out.println("Ice Found");
                                statueTier = 0;
                            } else if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == ModBlocks.PRIMED_PACKED_ICE.get()) {
                                hopper = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                                System.out.println("Packed Found");
                                statueTier = 1;
                            } else if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == ModBlocks.PRIMED_BLUE_ICE.get()) {
                                hopper = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                                System.out.println("Blue Found");
                                statueTier = 2;
                            } else if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == ModBlocks.PRIMED_DRAINED_ICE.get()) {
                                hopper = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                                System.out.println("Drained Found");
                                statueTier = 3;
                            }
                        }
                    }
                }
            }
            if (found) {
                double maxDistance = Double.MAX_VALUE;
                double newDistance = this.follower.distanceToSqr(hopper.getX(), hopper.getY(), hopper.getZ());
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.target = hopper;
                }
            }
        }
        return this.target != null && this.target != BlockPos.ZERO && follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem;
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
            if (statueTier == 0) {
                if(this.follower.level().getBlockState(this.target).getBlock() == ModBlocks.PRIMED_ICE.get()) {
                    if (this.follower.level().getBlockState(this.target.above()).getBlock() == ModBlocks.PRIMED_ICE.get()) {
                        this.follower.level().explode(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Level.ExplosionInteraction.NONE);
                        this.follower.level().setBlockAndUpdate(this.target, ModBlocks.PRIMED_ICE_STATUE.get().defaultBlockState());
                        this.follower.level().setBlockAndUpdate(this.target.above(), Blocks.AIR.defaultBlockState());
                        follower.getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, follower, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    }
                }
            } else if (statueTier == 1) {
                if(this.follower.level().getBlockState(this.target).getBlock() == ModBlocks.PRIMED_PACKED_ICE.get()) {
                    if (this.follower.level().getBlockState(this.target.above()).getBlock() == ModBlocks.PRIMED_PACKED_ICE.get()) {
                        this.follower.level().explode(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Level.ExplosionInteraction.NONE);
                        this.follower.level().setBlockAndUpdate(this.target, ModBlocks.PRIMED_PACKED_ICE_STATUE.get().defaultBlockState());
                        this.follower.level().setBlockAndUpdate(this.target.above(), Blocks.AIR.defaultBlockState());
                        follower.getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, follower, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    }
                }
            } else if (statueTier == 2) {
                if(this.follower.level().getBlockState(this.target).getBlock() == ModBlocks.PRIMED_BLUE_ICE.get()) {
                    if (this.follower.level().getBlockState(this.target.above()).getBlock() == ModBlocks.PRIMED_BLUE_ICE.get()) {
                        this.follower.level().explode(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Level.ExplosionInteraction.NONE);
                        this.follower.level().setBlockAndUpdate(this.target, ModBlocks.PRIMED_BLUE_ICE_STATUE.get().defaultBlockState());
                        this.follower.level().setBlockAndUpdate(this.target.above(), Blocks.AIR.defaultBlockState());
                        follower.getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, follower, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    }
                }
            } else if (statueTier == 3) {
                if(this.follower.level().getBlockState(this.target).getBlock() == ModBlocks.PRIMED_DRAINED_ICE.get()) {
                    if (this.follower.level().getBlockState(this.target.above()).getBlock() == ModBlocks.PRIMED_DRAINED_ICE.get()) {
                        this.follower.level().explode(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Level.ExplosionInteraction.NONE);
                        this.follower.level().setBlockAndUpdate(this.target, ModBlocks.PRIMED_DRAINED_ICE_STATUE.get().defaultBlockState());
                        this.follower.level().setBlockAndUpdate(this.target.above(), Blocks.AIR.defaultBlockState());
                        follower.getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, follower, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    }
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
