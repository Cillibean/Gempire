package com.gempire.util;

import com.gempire.blocks.GalaxyWarpBlock;
import com.gempire.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class DesolateTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;
    public static boolean placePad = true;
    public DesolateTeleporter(BlockPos pos, boolean insideDim, boolean placePad) {
        thisPos = pos;
        insideDimension = insideDim;
        this.placePad = placePad;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);

        BlockPos destinationPos = new BlockPos(thisPos.getX(), insideDimension ? thisPos.getY() : 61, thisPos.getZ());

        int tries = 0;
        while (destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR &&
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
                destinationWorld.getBlockState(destinationPos.above()).getBlock() != Blocks.AIR &&
                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25)) {
            destinationPos = destinationPos.above(3);
            tries++;
        }

        entity.setPos(destinationPos.getX(), destinationPos.getY()+3, destinationPos.getZ());

        if ((insideDimension || entity.getBlockStateOn() != ModBlocks.GALAXY_WARP.get().defaultBlockState()) && placePad) {
            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.betweenClosed(destinationPos.below(10).west(10),
                    destinationPos.above(10).east(10))) {
                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof GalaxyWarpBlock) {
                    doSetBlock = false;
                    break;
                }
            }

            if (doSetBlock) {
                BlockPos portalPos = entity.getOnPos();
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        destinationWorld.setBlock(portalPos.offset(x, 0, z), ModBlocks.POLISHED_SELENITE.get().defaultBlockState(),3);
                    }
                }
                destinationWorld.setBlock(portalPos.offset(2, 0, 0), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.WEST),3);
                destinationWorld.setBlock(portalPos.offset(2, 0, 1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.EAST).setValue(StairBlock.SHAPE, StairsShape.OUTER_LEFT),3);
                destinationWorld.setBlock(portalPos.offset(2, 0, -1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.EAST).setValue(StairBlock.SHAPE, StairsShape.OUTER_RIGHT),3);

                destinationWorld.setBlock(portalPos.offset(-2, 0, 0), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.EAST),3);
                destinationWorld.setBlock(portalPos.offset(-2, 0, 1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.SHAPE, StairsShape.OUTER_LEFT),3);
                destinationWorld.setBlock(portalPos.offset(-2, 0, -1), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH).setValue(StairBlock.SHAPE, StairsShape.OUTER_RIGHT),3);

                destinationWorld.setBlock(portalPos.offset(0, 0, 2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH),3);
                destinationWorld.setBlock(portalPos.offset(1, 0, 2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.WEST).setValue(StairBlock.SHAPE, StairsShape.OUTER_LEFT),3);
                destinationWorld.setBlock(portalPos.offset(-1, 0, 2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH).setValue(StairBlock.SHAPE, StairsShape.OUTER_RIGHT),3);

                destinationWorld.setBlock(portalPos.offset(0, 0, -2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH),3);
                destinationWorld.setBlock(portalPos.offset(1, 0, -2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.SHAPE, StairsShape.OUTER_LEFT),3);
                destinationWorld.setBlock(portalPos.offset(-1, 0, -2), ModBlocks.POLISHED_SELENITE_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.SHAPE, StairsShape.OUTER_LEFT),3);

                destinationWorld.setBlock(portalPos, ModBlocks.GALAXY_WARP.get().defaultBlockState(),3);
            }
        }

        return entity;
    }
}
