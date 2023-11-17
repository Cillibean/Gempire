package com.gempire.blocks.machine;

import com.gempire.init.ModTE;
import com.gempire.tileentities.BoardTE;
import com.gempire.tileentities.IncubatorTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BoardBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BoardBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(4D, 0D, 0.0D, 12D, 16D, 16D);
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide){
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if(te instanceof BoardTE){
                NetworkHooks.openScreen((ServerPlayer) player, (BoardTE)te, pos);
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.PASS;
            }
        }
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
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = worldIn.getBlockEntity(pos);
            if (blockentity instanceof Container) {
                Containers.dropContents(worldIn, pos, (Container)blockentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    public static int getDirectionFacingValue(BlockEntity te){
        BlockState block = te.getLevel().getBlockEntity(te.getBlockPos()).getBlockState();
        return switch (block.getValue(FACING)) {
            case EAST -> 5;
            case NORTH -> 2;
            case WEST -> 4;
            case SOUTH -> 3;
            default -> 5;
        };
    }

    public static Direction getDirectionFromValue(int value){
        return switch (value) {
            case 5 -> Direction.EAST;
            case 2 -> Direction.NORTH;
            case 4 -> Direction.WEST;
            case 3 -> Direction.SOUTH;
            default -> Direction.EAST;
        };
    }

    public static int getAdjustedDirectionValue(int facing, int svalue){
        if (facing == 3){
            return svalue;
        }
        else if (facing == 5){
            return switch (svalue) {
                case 5 -> 2;
                case 2 -> 4;
                case 4 -> 3;
                case 3 -> 5;
                default -> 2;
            };
        }
        else if (facing == 2){
            return switch (svalue) {
                case 5 -> 4;
                case 2 -> 3;
                case 4 -> 5;
                case 3 -> 2;
                default -> 4;
            };
        }
        else if (facing == 4){
            return switch (svalue) {
                case 5 -> 3;
                case 2 -> 5;
                case 4 -> 2;
                case 3 -> 4;
                default -> 3;
            };
        }
        return 2;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BoardTE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return p_152182_ == ModTE.BOARD_TE.get() ? BoardTE::tick : null;
    }

    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.IGNORE;
    }
}
