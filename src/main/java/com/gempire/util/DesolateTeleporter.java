package com.gempire.util;

import com.gempire.blocks.GalaxyWarpBlock;
import com.gempire.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class DesolateTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public DesolateTeleporter(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        int y = 61;

        if (!insideDimension) {
            y = thisPos.getY();
        }

        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
                (destinationWorld.getBlockState(destinationPos.above()).getBlock()  != Blocks.AIR) &&
                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25)) {
            destinationPos = destinationPos.above(3);
            tries++;
        }

        entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if (insideDimension) {
            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.betweenClosed(destinationPos.below(10).west(10),
                    destinationPos.above(10).east(10))) {
                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof GalaxyWarpBlock) {
                    doSetBlock = false;
                    break;
                }
            }

            if (doSetBlock) {
                destinationPos = destinationPos.offset(0, 2, 0);
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        destinationWorld.setBlock(destinationPos.offset(x, 0, z), ModBlocks.POLISHED_SELENITE.get().defaultBlockState(), 3);
                    }
                }
                destinationWorld.setBlock(destinationPos.offset(2, 0, 0), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.WEST), 3);
                destinationWorld.setBlockAndUpdate(destinationPos.offset(2, 0, 1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH));
                destinationWorld.setBlockAndUpdate(destinationPos.offset(2, 0, -1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH));

                destinationWorld.setBlock(destinationPos.offset(-2, 0, 0), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.EAST), 3);
                destinationWorld.setBlockAndUpdate(destinationPos.offset(-2, 0, 1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH));
                destinationWorld.setBlockAndUpdate(destinationPos.offset(-2, 0, -1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH));

                destinationWorld.setBlock(destinationPos.offset(0, 0, 2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH), 3);
                destinationWorld.setBlockAndUpdate(destinationPos.offset(1, 0, 2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.WEST));
                destinationWorld.setBlockAndUpdate(destinationPos.offset(-1, 0, 2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.EAST));

                destinationWorld.setBlock(destinationPos.offset(0, 0, -2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH), 3);
                destinationWorld.setBlockAndUpdate(destinationPos.offset(1, 0, -2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.WEST));
                destinationWorld.setBlockAndUpdate(destinationPos.offset(-1, 0, -2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.EAST));

                destinationWorld.setBlock(destinationPos, ModBlocks.GALAXY_WARP.get().defaultBlockState(), 3);
            }
        }

        return entity;
    }
}
