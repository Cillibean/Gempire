package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityTourmaline;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class EntityAIBuildFarm extends Goal {
    public EntityTourmaline follower;
    public BlockPos target;
    public BlockPos startPos;
    public double speed;
    public ChunkPos chunk;
    public EntityAIBuildFarm(EntityTourmaline entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        return this.follower.getMovementType() == 0 && follower.isBuilding();
    }

    @Override
    public boolean canContinueToUse() {
        return this.follower.getMovementType() == 0 && this.follower.isBuilding();
    }

    @Override
    public void start() {
        //doInventoryOrganise();
        //if (!follower.level().isClientSide) {
        chunk = follower.level().getChunkAt(this.follower.getOnPos()).getPos();
        this.startPos = follower.getOnPos();
        super.start();
        ArrayList<BlockPos> list = new ArrayList<>();
        ArrayList<BlockPos> list2 = new ArrayList<>();
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 5; y++) {
                for (int z = 0; z < 16; z++) {
                    list.add(chunk.getBlockAt(x, (int) (this.follower.getY() - 2 + y), z));
                }
            }
        }
        int yFarm = (int) follower.getY()-1;
        Level level = follower.level();
        for (BlockPos pos : list) {
            BlockState state = level.getBlockState(pos);
            if (state == Blocks.AIR.defaultBlockState()) {
                if (pos.getY() <= yFarm) {
                    level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
                } else {
                   list2.add(pos);
                }
            } else if (state == Blocks.DIRT.defaultBlockState() || state == Blocks.GRASS_BLOCK.defaultBlockState()) {
                if (pos.getY() < yFarm) list2.add(pos);
                else if (pos.getY() != yFarm) {
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    ArrayList<ItemStack> listInv = new ArrayList<>();
                    listInv.add(Items.DIRT.getDefaultInstance());
                    follower.addToInventoryList(listInv);
                }
            } else if (state == Blocks.GRASS.defaultBlockState()) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
            if (!list2.contains(pos)) {
                BlockPos posInChunk = pos.offset(-chunk.getWorldPosition().getX(), 0, -chunk.getWorldPosition().getZ());
                if (posInChunk.getY() == yFarm) {
                    if (posInChunk.equals(new BlockPos(4, pos.getY(), 4)) || posInChunk.equals(new BlockPos(11, pos.getY(), 4)) || posInChunk.equals(new BlockPos(4, pos.getY(), 11)) || posInChunk.equals(new BlockPos(11, pos.getY(), 11))) {
                        level.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
                        follower.consumeItemCheck(Items.WATER_BUCKET, 2);
                    } else if ((posInChunk.getX() == 0 || posInChunk.getZ() == 0) && (posInChunk.getX() == 15 || posInChunk.getZ() == 15)) {
                        level.setBlockAndUpdate(pos, Blocks.OAK_LOG.defaultBlockState());
                        follower.consumeItemCheck(Items.OAK_LOG, 1);
                    } else if (posInChunk.getX() == 0 || posInChunk.getZ() == 0 || posInChunk.getX() == 15 || posInChunk.getZ() == 15 &&
                            !((posInChunk.getX() == 0 || posInChunk.getZ() == 0) && (posInChunk.getX() == 15 || posInChunk.getZ() == 15))) {
                        level.setBlockAndUpdate(pos, Blocks.OAK_PLANKS.defaultBlockState());
                        follower.consumeItemCheck(Items.OAK_PLANKS, 1);
                    } else {
                        level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
                    }
                }
            }
        }
        target = null;
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        ArrayList<ItemStack> list3 = new ArrayList<>();
        list3.add(follower.getItemBySlot(EquipmentSlot.MAINHAND));
        follower.addToInventoryList(list3);
        follower.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public void stop() {
        this.target = null;
        this.follower.getNavigation().stop();
        this.follower.setBuilding(false);
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