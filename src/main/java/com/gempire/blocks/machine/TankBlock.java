package com.gempire.blocks.machine;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModTE;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public class TankBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public TankBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(HALF, DoubleBlockHalf.LOWER).setValue(FACING, Direction.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockPos drillPos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below().below() : pos.below();
            BlockPos crystalPos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.above() : pos.above().above();
            if (worldIn.getBlockState(drillPos).getBlock() == ModBlocks.DRILL_BLOCK.get() && worldIn.getBlockState(crystalPos).getBlock() instanceof PowerCrystalBlock) {
                BlockEntity te = worldIn.getBlockEntity(drillPos);
                if (te instanceof InjectorTE) {
                    NetworkHooks.openScreen((ServerPlayer) player, (InjectorTE) te, drillPos);
                    return InteractionResult.CONSUME;
                } return InteractionResult.PASS;
            } return InteractionResult.PASS;
        }
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
        return doubleblockhalf == DoubleBlockHalf.LOWER && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        if (!worldIn.isClientSide && player.isCreative()) {
            TankBlock.removeBottomHalf(worldIn, pos, state, player);
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }
    public static void removeBottomHalf(Level world, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                world.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }

    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        if (blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(FACING, context.getHorizontalDirection().getOpposite());
        } else {
            return null;
        }
    }

    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        builder.add(FACING);
    }

    @Override
    public void destroy(LevelAccessor world, BlockPos pos, BlockState state) {
        super.destroy(world, pos, state);
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.LOWER) {
            BlockPos blockpos = pos.above();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER) {
                world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            }
        }
        else{
            BlockPos blockpos = pos.below();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            }
        }
    }
}