package com.gempire.blocks;

import com.gempire.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrownBlock extends DirectionalBlock {

    protected static final VoxelShape FACING_NS_FLOOR = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    protected static final VoxelShape FACING_EW_FLOOR = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public CrownBlock(Properties properties) {
        super(properties);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return true;
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
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

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide && player.getMainHandItem().is(ModItems.PRISMATIC_SHEARS.get())) {
            System.out.println("pickaxe");
            popResource(level, pos, new ItemStack(ModItems.EMPRESS_STAR.get()));
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }

        return super.use(state, level, pos, player, hand, result);
    }
}
