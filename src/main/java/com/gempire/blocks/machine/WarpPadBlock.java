package com.gempire.blocks.machine;

import com.gempire.init.ModTE;
import com.gempire.systems.warping.*;
import com.gempire.tileentities.WarpPadTE;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class WarpPadBlock extends BaseEntityBlock {
    public WarpPadBlock(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof WarpPadTE warpPad) {
            if(level instanceof ServerLevel serverLevel && player instanceof ServerPlayer serverPlayer) {
                WarpPadInfoHolder holder = WarpPadData.get(serverLevel);
                WarpPadInfo info = holder.getNewWarpPad(pos);
                MenuProvider provider = WarpConfigMenu.getMenuProvider(info, warpPad.getItemStackHandler());
                NetworkHooks.openScreen(serverPlayer, provider, info::write);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof WarpPadTE warpPad) {
                warpPad.handleScheduledTick(level, pos);
            }
        }
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WarpPadTE(pos, state);
    }
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
        super.onRemove(state, level, pos, newState, moving);
        if(level instanceof ServerLevel serverLevel) {
            WarpPadData.get(serverLevel).removeWarpPad(pos);
        }
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? createTickerHelper(type, ModTE.WARP_PAD_TE.get(), WarpPadTE::animateTick) : null;
    }
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}