package com.gempire.blocks;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class SixWayConnectorBlock extends SixWayBlock {

    public SixWayConnectorBlock(float apothem, Properties properties) {
        super(apothem, properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, Boolean.valueOf(false))
                .with(EAST, Boolean.valueOf(false))
                .with(SOUTH, Boolean.valueOf(false))
                .with(WEST, Boolean.valueOf(false))
                .with(UP, Boolean.valueOf(false))
                .with(DOWN, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.makeConnections(context.getWorld(), context.getPos());
    }

    public BlockState makeConnections(IBlockReader blockReader, BlockPos pos) {
        Block block = blockReader.getBlockState(pos.down()).getBlock();
        Block block1 = blockReader.getBlockState(pos.up()).getBlock();
        Block block2 = blockReader.getBlockState(pos.north()).getBlock();
        Block block3 = blockReader.getBlockState(pos.east()).getBlock();
        Block block4 = blockReader.getBlockState(pos.south()).getBlock();
        Block block5 = blockReader.getBlockState(pos.west()).getBlock();
        return this.getDefaultState()
                .with(DOWN, block == this || block instanceof IPowerMarker)
                .with(UP, block1 == this || block1 instanceof IPowerMarker)
                .with(NORTH, block2 == this || block2 instanceof IPowerMarker)
                .with(EAST, block3 == this || block3 instanceof IPowerMarker)
                .with(SOUTH, block4 == this || block4 instanceof IPowerMarker)
                .with(WEST, block5 == this || block5 instanceof IPowerMarker);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.makeConnections(worldIn, currentPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = Block.makeCuboidShape(5,5,5,11,11,11);
        VoxelShape sideLeft = state.get(WEST) ? Block.makeCuboidShape(0,5,5,5,11,11) : shape;
        VoxelShape sideRight = state.get(EAST) ? Block.makeCuboidShape(11,5,5,16,11,11) : shape;
        VoxelShape sideNorth = state.get(NORTH) ? Block.makeCuboidShape(5,5,0,11,11,5) : shape;
        VoxelShape sideSouth = state.get(SOUTH) ? Block.makeCuboidShape(5,5,11,11,11,16) : shape;
        VoxelShape sideUp = state.get(UP) ? Block.makeCuboidShape(5,11,5,11,16,11) : shape;
        VoxelShape sideDown = state.get(DOWN) ? Block.makeCuboidShape(5,1,5,11,5,11) : shape;
        VoxelShape finalShape1 = VoxelShapes.or(shape,sideLeft,sideDown,sideRight, sideNorth, sideSouth,sideUp);
        return finalShape1;
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
}
