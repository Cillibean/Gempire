package com.gempire.blocks;

import com.gempire.init.ModTE;
import com.gempire.tileentities.WhiteAltarTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class WhiteAltarBlock extends DirectionalBlock implements EntityBlock  {
    protected static final VoxelShape FACING_NS_FLOOR = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);
    protected static final VoxelShape FACING_EW_FLOOR = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);

    public WhiteAltarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return true;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
            return FACING_NS_FLOOR;
        } else {
            return FACING_EW_FLOOR;
        }
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        if (state.getBlock() == this) { //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            if (direction == Direction.UP) {
                if (worldIn.getBlockState(pos.below()) != Blocks.AIR.defaultBlockState()) {
                    return super.canSurvive(state, worldIn, pos);
                }
            }
            if(direction == Direction.DOWN){
                if(worldIn.getBlockState(pos.above()) != Blocks.AIR.defaultBlockState()){
                    return super.canSurvive(state, worldIn, pos);
                }
            }
            if(direction == Direction.NORTH){
                if(worldIn.getBlockState(pos.south()) != Blocks.AIR.defaultBlockState()){
                    return super.canSurvive(state, worldIn, pos);
                }
            }
            if(direction == Direction.EAST){
                if(worldIn.getBlockState(pos.west()) != Blocks.AIR.defaultBlockState()){
                    return super.canSurvive(state, worldIn, pos);
                }
            }
            if(direction == Direction.SOUTH){
                if(worldIn.getBlockState(pos.north()) != Blocks.AIR.defaultBlockState()){
                    return super.canSurvive(state, worldIn, pos);
                }
            }
            if(direction == Direction.WEST){
                if(worldIn.getBlockState(pos.east()) != Blocks.AIR.defaultBlockState()){
                    return super.canSurvive(state, worldIn, pos);
                }
            }
        }
        return false;
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
            return FACING_NS_FLOOR;
        } else {
            return FACING_EW_FLOOR;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WhiteAltarTE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return p_152182_ == ModTE.WHITE_ALTAR_TE.get() ? WhiteAltarTE::tick : null;
    }
}
