package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityTourmaline;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EntityAIFarm extends Goal {
    public EntityTourmaline follower;
    public BlockPos target;
    public BlockPos startPos;
    public double speed;
    public ChunkPos chunk;
    public EntityAIFarm(EntityTourmaline entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        target = null;
        chunk = follower.level().getChunkAt(follower.getOnPos()).getPos();
        BlockPos crop = BlockPos.ZERO;
        boolean found = false;
        //List<BlockState> list = follower.level().getBlockStates(follower.getBoundingBox().inflate(16, 2, 16)).toList();
        while (!found) {
            for (int x = -15; x < 16; x++) {
                for (int y = -2; y < 3; y++) {
                    for (int z = -15; z < 16; z++) {
                        if (follower.level().getChunkAt(follower.blockPosition().offset(x, y, z)).getPos() == chunk && !found) {
                            if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() instanceof CropBlock && ((CropBlock) (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock())).isMaxAge(this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)))) {
                                crop = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                            } else if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == Blocks.FARMLAND && this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y + 1, z)).getBlock() == Blocks.AIR) {
                                crop = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                            }
                        }
                    }
                }
            }
            if (!found) {
                break;
            }
        }
        double maxDistance = Double.MAX_VALUE;
        double newDistance = this.follower.distanceToSqr(crop.getX(), crop.getY(), crop.getZ());
        if (newDistance <= maxDistance) {
            maxDistance = newDistance;
            this.target = crop;
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
        doInventoryOrganise();
        this.startPos = follower.getOnPos();
        super.start();
        BlockState block = follower.level().getBlockState(target);
        if (block.getBlock() == Blocks.FARMLAND) {
            if (this.follower.level().getBlockState(this.target.above()).getBlock() == Blocks.AIR) {
                if (follower.getCrops().contains("wheat") && this.follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.WHEAT_SEEDS) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        ItemStack stack = follower.getItemBySlot(EquipmentSlot.MAINHAND).copy();
                        stack.shrink(1);
                        this.follower.setItemSlot(EquipmentSlot.MAINHAND, stack);
                        this.follower.level().setBlockAndUpdate(target.above(), Blocks.WHEAT.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                } else if (follower.getCrops().contains("beetroot") && this.follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.BEETROOT_SEEDS) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        ItemStack stack = follower.getItemBySlot(EquipmentSlot.MAINHAND).copy();
                        stack.shrink(1);
                        this.follower.setItemSlot(EquipmentSlot.MAINHAND, stack);
                        this.follower.level().setBlockAndUpdate(target.above(), Blocks.BEETROOTS.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                } else if (follower.getCrops().contains("carrot")&&this.follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.CARROT) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        ItemStack stack = follower.getItemBySlot(EquipmentSlot.MAINHAND).copy();
                        stack.shrink(1);
                        this.follower.setItemSlot(EquipmentSlot.MAINHAND, stack);
                        this.follower.level().setBlockAndUpdate(target.above(), Blocks.CARROTS.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                } else if (follower.getCrops().contains("potato") && this.follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.POTATO) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    ItemStack stack = follower.getItemBySlot(EquipmentSlot.MAINHAND).copy();
                    stack.shrink(1);
                    this.follower.setItemSlot(EquipmentSlot.MAINHAND, stack);
                    this.follower.level().setBlockAndUpdate(target.above(), Blocks.POTATOES.defaultBlockState());
                    target = null;
                    this.stop();
                }
            } else {
                target = null;
            }
        } if (block.getBlock() instanceof CropBlock) {
            if (((CropBlock) (this.follower.level().getBlockState(this.target).getBlock())).isMaxAge(this.follower.level().getBlockState(this.target))) {
                if (block.getBlock() == Blocks.POTATOES && follower.getCrops().contains("potato")) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        List<ItemStack> stack = getDrops(this.follower.level().getBlockState(this.target), (ServerLevel) this.follower.level(), this.target);
                        System.out.println(stack);
                        follower.addToInventoryList(stack);
                        this.follower.level().setBlockAndUpdate(target, Blocks.AIR.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                } else if (block.getBlock() == Blocks.BEETROOTS && follower.getCrops().contains("beetroot")) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        List<ItemStack> stack = getDrops(this.follower.level().getBlockState(this.target), (ServerLevel) this.follower.level(), this.target);
                        System.out.println(stack);
                        follower.addToInventoryList(stack);
                        this.follower.level().setBlockAndUpdate(target, Blocks.AIR.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                } else if (block.getBlock() == Blocks.WHEAT && follower.getCrops().contains("wheat")) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        List<ItemStack> stack = getDrops(this.follower.level().getBlockState(this.target), (ServerLevel) this.follower.level(), this.target);
                        System.out.println(stack);
                        follower.addToInventoryList(stack);
                        this.follower.level().setBlockAndUpdate(target, Blocks.AIR.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                } else if (block.getBlock() == Blocks.CARROTS && follower.getCrops().contains("carrot")) {
                    this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
                    if (this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4) {
                        List<ItemStack> stack = getDrops(this.follower.level().getBlockState(this.target), (ServerLevel) this.follower.level(), this.target);
                        System.out.println(stack);
                        follower.addToInventoryList(stack);
                        this.follower.level().setBlockAndUpdate(target, Blocks.AIR.defaultBlockState());
                        target = null;
                        this.stop();
                    }
                }
            }
        }
        target = null;
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
    }

    @Override
    public void stop() {
        this.target = null;
        this.follower.getNavigation().stop();
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
    }

    public void doInventoryOrganise() {
        ItemStack hand = follower.getItemBySlot(EquipmentSlot.MAINHAND);
        if (hand.getItem() == Items.CARROT) {
            if (hand.getCount() < 64) {
                for (int i=0; i < 64-hand.getCount(); i++) {
                    follower.consumeItemCheck(Items.CARROT, 1);
                }
            }
        } else if (hand.getItem() == Items.WHEAT_SEEDS) {
            if (hand.getCount() < 64) {
                for (int i=0; i < 64-hand.getCount(); i++) {
                    follower.consumeItemCheck(Items.WHEAT_SEEDS, 1);
                }
            }
        } else if (hand.getItem() == Items.BEETROOT_SEEDS) {
            if (hand.getCount() < 64) {
                int required = 64-hand.getCount();
                for (int i=0; i < required; i++) {
                    follower.consumeItemCheck(Items.BEETROOT_SEEDS, 1);
                }
            }
        } else if (hand.getItem() == Items.POTATO) {
            if (hand.getCount() < 64) {
                int required = 64-hand.getCount();
                for (int i=0; i < required; i++) {
                    follower.consumeItemCheck(Items.POTATO, 1);
                }
            }
        }
    }

    public static List<ItemStack> getDrops(BlockState p_49870_, ServerLevel p_49871_, BlockPos p_49872_) {
        LootParams.Builder lootparams$builder = (new LootParams.Builder(p_49871_)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p_49872_)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
        return p_49870_.getDrops(lootparams$builder);
    }
}