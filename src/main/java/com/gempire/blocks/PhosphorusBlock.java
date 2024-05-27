package com.gempire.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;

public class PhosphorusBlock extends Block {

    public static final BooleanProperty LIT;

    public PhosphorusBlock(BlockBehaviour.Properties p_55657_) {
        super(p_55657_);
        this.registerDefaultState((BlockState)this.defaultBlockState().setValue(LIT, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_55659_) {
        return (BlockState)this.defaultBlockState().setValue(LIT, p_55659_.getLevel().hasNeighborSignal(p_55659_.getClickedPos()));
    }

    public void neighborChanged(BlockState p_55666_, Level p_55667_, BlockPos p_55668_, Block p_55669_, BlockPos p_55670_, boolean p_55671_) {
        if (!p_55667_.isClientSide) {
            boolean $$6 = (Boolean)p_55666_.getValue(LIT);
            if ($$6 != p_55667_.hasNeighborSignal(p_55668_)) {
                if ($$6) {
                    p_55667_.scheduleTick(p_55668_, this, 4);
                } else {
                    p_55667_.setBlock(p_55668_, (BlockState)p_55666_.cycle(LIT), 2);
                }
            }

        }
    }

    public void tick(BlockState p_221937_, ServerLevel p_221938_, BlockPos p_221939_, RandomSource p_221940_) {
        if ((Boolean)p_221937_.getValue(LIT) && !p_221938_.hasNeighborSignal(p_221939_)) {
            p_221938_.setBlock(p_221939_, (BlockState)p_221937_.cycle(LIT), 2);
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(new Property[]{LIT});
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
