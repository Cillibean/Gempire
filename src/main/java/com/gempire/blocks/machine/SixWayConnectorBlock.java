package com.gempire.blocks.machine;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.init.ModBlocks;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public abstract class SixWayConnectorBlock extends PipeBlock {

    public SixWayConnectorBlock(float apothem, Properties properties) {
        super(apothem, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, Boolean.valueOf(false))
                .setValue(EAST, Boolean.valueOf(false))
                .setValue(SOUTH, Boolean.valueOf(false))
                .setValue(WEST, Boolean.valueOf(false))
                .setValue(UP, Boolean.valueOf(false))
                .setValue(DOWN, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.makeConnections(context.getLevel(), context.getClickedPos());
    }

    public abstract boolean facingMarker(BlockPos direction);
    public abstract boolean typeMarker(Block predicate);

    public BlockState makeConnections(BlockGetter blockReader, BlockPos pos) {
        Block block = blockReader.getBlockState(pos.below()).getBlock();
        Block block1 = blockReader.getBlockState(pos.above()).getBlock();
        Block block2 = blockReader.getBlockState(pos.north()).getBlock();
        Block block3 = blockReader.getBlockState(pos.east()).getBlock();
        Block block4 = blockReader.getBlockState(pos.south()).getBlock();
        Block block5 = blockReader.getBlockState(pos.west()).getBlock();
        return this.defaultBlockState()
                .setValue(DOWN, block == this || typeMarker(block) && facingMarker(pos.below()))
                .setValue(UP, block1 == this || typeMarker(block1) && facingMarker(pos.above()))
                .setValue(NORTH, block2 == this || typeMarker(block2) && facingMarker(pos.north()))
                .setValue(EAST, block3 == this || typeMarker(block3) && facingMarker(pos.east()))
                .setValue(SOUTH, block4 == this || typeMarker(block4) && facingMarker(pos.south()))
                .setValue(WEST, block5 == this || typeMarker(block5) && facingMarker(pos.west()));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.makeConnections(worldIn, currentPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Block.box(5,5,5,11,11,11);
        VoxelShape sideLeft = state.getValue(WEST) ? Block.box(0,5,5,5,11,11) : shape;
        VoxelShape sideRight = state.getValue(EAST) ? Block.box(11,5,5,16,11,11) : shape;
        VoxelShape sideNorth = state.getValue(NORTH) ? Block.box(5,5,0,11,11,5) : shape;
        VoxelShape sideSouth = state.getValue(SOUTH) ? Block.box(5,5,11,11,11,16) : shape;
        VoxelShape sideUp = state.getValue(UP) ? Block.box(5,11,5,11,16,11) : shape;
        VoxelShape sideDown = state.getValue(DOWN) ? Block.box(5,1,5,11,5,11) : shape;
        VoxelShape finalShape1 = Shapes.or(shape,sideLeft,sideDown,sideRight, sideNorth, sideSouth,sideUp);
        return finalShape1;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return this.getShape(state, reader, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
}
