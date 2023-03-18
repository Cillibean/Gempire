package com.gempire.blocks.machine;

import com.gempire.init.ModFluids;
import com.gempire.init.ModTE;
import com.gempire.tileentities.ShellTE;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public class ShellBlock extends BaseEntityBlock implements SimpleWaterloggedBlock, EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);
    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");

    public ShellBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(STAGE, 0));
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if(!worldIn.isClientSide()){
            BlockEntity te = worldIn.getBlockEntity(pos);
            if(te instanceof ShellTE){
                NetworkHooks.openScreen((ServerPlayer) player, (ShellTE)te, pos);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(3D, 0D, 3D, 12D, 12D, 12D);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, false).setValue(STAGE, 0);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(WATERLOGGED).add(STAGE);
    }

    /*@Override
    public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return !state.getValue(BlockStateProperties.WATERLOGGED) && fluidIn == ModFluids.WHITE_ESSENCE.get();
    }*/

    @Override
    public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.getValue(BlockStateProperties.WATERLOGGED)) {
            if (!worldIn.isClientSide()) {
                worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
                worldIn.scheduleTick(pos, fluidStateIn.getType(), fluidStateIn.getType().getTickDelay(worldIn));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(worldIn.isClientSide){
            return;
        }
        if(!(newState.getBlock() instanceof ShellBlock)){
            worldIn.removeBlockEntity(pos);
        }
    }

    public static int getDirectionFacingValue(BlockEntity te){
        BlockState block = te.getLevel().getBlockEntity(te.getBlockPos()).getBlockState();
        switch(block.getValue(FACING)){
            case EAST:
                return 5;
            case NORTH:
                return 2;
            case WEST:
                return 4;
            case SOUTH:
                return 3;
            default:
                return 5;
        }
    }

    public static Direction getDirectionFromValue(int value){
        switch(value){
            case 5:
                return Direction.EAST;
            case 2:
                return Direction.NORTH;
            case 4:
                return Direction.WEST;
            case 3:
                return Direction.SOUTH;
            default:
                return Direction.EAST;
        }
    }

    public static int getAdjustedDirectionValue(int facing, int svalue){
        if (facing == 3){
            return svalue;
        }
        else if (facing == 5){
            switch (svalue){
                case 5:
                    return 2;
                case 2:
                    return 4;
                case 4:
                    return 3;
                case 3:
                    return 5;
                default:
                    return 2;
            }
        }
        else if (facing == 2){
            switch (svalue){
                case 5:
                    return 4;
                case 2:
                    return 3;
                case 4:
                    return 5;
                case 3:
                    return 2;
                default:
                    return 4;
            }
        }
        else if (facing == 4){
            switch (svalue){
                case 5:
                    return 3;
                case 2:
                    return 5;
                case 4:
                    return 2;
                case 3:
                    return 4;
                default:
                    return 3;
            }
        }
        return 2;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShellTE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return p_152182_ == ModTE.SHELL_TE.get() ? ShellTE::tick : null;
    }
}
