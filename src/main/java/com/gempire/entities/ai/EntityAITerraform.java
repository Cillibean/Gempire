package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityLapis;
import com.gempire.entities.gems.EntityTourmaline;
import com.gempire.init.ModItems;
import com.gempire.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class EntityAITerraform extends Goal {
    public EntityLapis follower;
    public BlockPos target;
    public BlockPos startPos;
    public double speed;
    public ChunkPos chunk;
    public ChunkPos chunk2;
    public ChunkPos chunk3;
    public EntityAITerraform(EntityLapis entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        return this.follower.getMovementType() == 0 && follower.isTerraforming();
    }

    @Override
    public boolean canContinueToUse() {
        return this.follower.getMovementType() == 0 && this.follower.isTerraforming();
    }

    @Override
    public void start() {
        super.start();
        //doInventoryOrganise();
        //if (!follower.level().isClientSide) {
        ItemStack stack = follower.getItemBySlot(EquipmentSlot.MAINHAND);
        String tier = "";
        if (stack.is(ItemTags.PICKAXES)) {
            if (stack.is(Items.STONE_PICKAXE)) {
                tier = "stone";
            } else if (stack.is(Items.IRON_PICKAXE)) {
                tier = "iron";
            } else if (stack.is(Items.GOLDEN_PICKAXE)) {
                tier = "golden";
            } else if (stack.is(Items.DIAMOND_PICKAXE)) {
                tier = "diamond";
            } else if (stack.is(Items.NETHERITE_PICKAXE)) {
                tier = "netherite";
            } else if (stack.is(ModItems.PRISMATIC_PICKAXE.get())) {
                tier = "prismatic";
            }
        }
        chunk = follower.level().getChunkAt(this.follower.getOnPos().offset(-16, 0,0)).getPos();
        chunk2 = follower.level().getChunkAt(this.follower.getOnPos()).getPos();
        chunk3 = follower.level().getChunkAt(this.follower.getOnPos().offset(16, 0,0)).getPos();
        this.startPos = follower.getOnPos();
        ArrayList<BlockPos> list = new ArrayList<>();
        ArrayList<BlockPos> list2 = new ArrayList<>();
        ArrayList<BlockPos> list3 = new ArrayList<>();
        int yTop = (int) follower.getY()-1;
        Level level = follower.level();
        for (int x = 0; x < 48; x++) {
            for (int y = 0; y < 39; y++) {
                for (int z = 0; z < 16; z++) {
                    if (x < 16) {
                        list.add(chunk.getBlockAt(x, (int) follower.getY() -y + 3, z));
                    } else if (x < 32) {
                        list.add(chunk2.getBlockAt(x - 16, (int) follower.getY() -y + 3, z));
                    } else {
                        list.add(chunk3.getBlockAt(x - 32, (int) follower.getY() -y + 3, z));
                    }
                }
            }
        }

        for (BlockPos pos : list) {
            if (level.getBlockState(pos) != Blocks.AIR.defaultBlockState()) {
                if (pos.getY() > yTop) {
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                } else {
                    list3.add(pos);
                }
            } else {
                list3.add(pos);
            }
        }

        list.clear();

        for (BlockPos pos : list3) {
            BlockPos posInChunk;
            if (pos.getX() - chunk.getWorldPosition().getX() > 31) {
                posInChunk = pos.offset(-chunk3.getWorldPosition().getX(), 0, -chunk3.getWorldPosition().getZ());
            } else if (pos.getX() - chunk.getWorldPosition().getX() > 15) {
                posInChunk = pos.offset(-chunk2.getWorldPosition().getX(), 0, -chunk2.getWorldPosition().getZ());
            } else {
                posInChunk = pos.offset(-chunk.getWorldPosition().getX(), 0, -chunk.getWorldPosition().getZ());
            }
            if (posInChunk.getZ() < 13 && posInChunk.getZ() > 2) {
                if (posInChunk.getY() > yTop-6) {
                    list2.add(pos);
                    if (isMineable(pos, tier)) {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        follower.addToInventoryList(getDrops(level.getBlockState(pos), (ServerLevel) level, pos));
                    } else {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
            if (!list2.contains(pos)) {
                list.add(pos);
            }
        }

        list3.clear();

        for (BlockPos pos : list) {
            BlockPos posInChunk;
            System.out.println(pos.getX() - chunk.getWorldPosition().getX());
            if (pos.getX() - chunk.getWorldPosition().getX() > 31) {
                posInChunk = pos.offset(-chunk3.getWorldPosition().getX(), 0, -chunk3.getWorldPosition().getZ());
            } else if (pos.getX() - chunk.getWorldPosition().getX() > 15) {
                posInChunk = pos.offset(-chunk2.getWorldPosition().getX(), 0, -chunk2.getWorldPosition().getZ());
            } else {
                posInChunk = pos.offset(-chunk.getWorldPosition().getX(), 0, -chunk.getWorldPosition().getZ());
            }
            if (posInChunk.getZ() < 12 && posInChunk.getZ() > 3) {
                if (pos.getY() > yTop-16) {
                    list2.add(pos);
                    if (isMineable(pos, tier)) {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        follower.addToInventoryList(getDrops(level.getBlockState(pos), (ServerLevel) level, pos));;
                    } else {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
            if (!list2.contains(pos)) {
                list3.add(pos);
            }
        }

        list.clear();

        for (BlockPos pos : list3) {
            BlockPos posInChunk;
            System.out.println(pos.getX() - chunk.getWorldPosition().getX());
            if (pos.getX() - chunk.getWorldPosition().getX() > 31) {
                posInChunk = pos.offset(-chunk3.getWorldPosition().getX(), 0, -chunk3.getWorldPosition().getZ());
            } else if (pos.getX() - chunk.getWorldPosition().getX() > 15) {
                posInChunk = pos.offset(-chunk2.getWorldPosition().getX(), 0, -chunk2.getWorldPosition().getZ());
            } else {
                posInChunk = pos.offset(-chunk.getWorldPosition().getX(), 0, -chunk.getWorldPosition().getZ());
            }
            if (posInChunk.getZ() < 11 && posInChunk.getZ() > 4) {
                if (pos.getY() > yTop-26) {
                    list2.add(pos);
                    if (isMineable(pos, tier)) {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        follower.addToInventoryList(getDrops(level.getBlockState(pos), (ServerLevel) level, pos));;
                    } else {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
            if (!list2.contains(pos)) {
                list.add(pos);
            }
        }

        for (BlockPos pos : list) {
            BlockPos posInChunk;
            System.out.println(pos.getX() - chunk.getWorldPosition().getX());
            if (pos.getX() - chunk.getWorldPosition().getX() > 31) {
                posInChunk = pos.offset(-chunk3.getWorldPosition().getX(), 0, -chunk3.getWorldPosition().getZ());
            } else if (pos.getX() - chunk.getWorldPosition().getX() > 15) {
                posInChunk = pos.offset(-chunk2.getWorldPosition().getX(), 0, -chunk2.getWorldPosition().getZ());
            } else {
                posInChunk = pos.offset(-chunk.getWorldPosition().getX(), 0, -chunk.getWorldPosition().getZ());
            }
            if (posInChunk.getZ() < 10 && posInChunk.getZ() > 5) {
                if (pos.getY() > yTop-36) {
                    list2.add(pos);
                    if (isMineable(pos, tier)) {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        follower.addToInventoryList(getDrops(level.getBlockState(pos), (ServerLevel) level, pos));;
                    } else {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
            if (!list2.contains(pos)) {
                list3.add(pos);
            }
        }

        target = null;
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
    }

    public boolean isMineable(BlockPos pos, String tier) {
        Level level = follower.level();
        BlockState state = level.getBlockState(pos);
        if (state.is(Tags.Blocks.ORES)) {
            if (level.getBlockState(pos).is(BlockTags.NEEDS_STONE_TOOL)) {
                if (tier == "stone" || tier == "iron" || tier == "gold" || tier == "diamond"
                        || tier == "netherite" || tier == "prismatic") {
                    return true;
                }
                return false;
            } else if (level.getBlockState(pos).is(BlockTags.NEEDS_IRON_TOOL)) {
                if (tier == "iron" || tier == "gold" || tier == "diamond"
                        || tier == "netherite" || tier == "prismatic") {
                    return true;
                }
                return false;
            } else if (level.getBlockState(pos).is(Tags.Blocks.NEEDS_GOLD_TOOL)) {
                if (tier == "gold" || tier == "diamond" || tier == "netherite"
                        || tier == "prismatic") {
                    return true;
                }
                return false;
            } else if (level.getBlockState(pos).is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                if (tier == "diamond" || tier == "netherite" || tier == "prismatic") {
                    return true;
                }
                return false;
            } else if (level.getBlockState(pos).is(Tags.Blocks.NEEDS_NETHERITE_TOOL)) {
                if (tier == "netherite" || tier == "prismatic") {
                    return true;
                }
                return false;
            } else if (level.getBlockState(pos).is(ModTags.Blocks.NEEDS_PRISMATIC_TOOL)) {
                if (tier == "prismatic") {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void stop() {
        this.target = null;
        this.follower.getNavigation().stop();
        this.follower.setTerraforming(false);
        super.stop();
    }


    public static List<ItemStack> getDrops(BlockState p_49870_, ServerLevel p_49871_, BlockPos p_49872_) {
        LootParams.Builder lootparams$builder = (new LootParams.Builder(p_49871_)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p_49872_)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
        return p_49870_.getDrops(lootparams$builder);
    }
}