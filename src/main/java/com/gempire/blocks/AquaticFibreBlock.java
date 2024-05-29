package com.gempire.blocks;

import com.gempire.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class AquaticFibreBlock extends SeagrassBlock {
    public AquaticFibreBlock(Properties p_154496_) {
        super(p_154496_);
    }

    public void performBonemeal(ServerLevel p_222423_, RandomSource p_222424_, BlockPos p_222425_, BlockState p_222426_) {
        BlockState blockstate = ModBlocks.TALL_AQUATIC_FIBRE.get().defaultBlockState();
        BlockState blockstate1 = (BlockState)blockstate.setValue(TallAquaticFibreBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos blockpos = p_222425_.above();
        if (p_222423_.getBlockState(blockpos).is(Blocks.WATER)) {
            p_222423_.setBlock(p_222425_, blockstate, 2);
            p_222423_.setBlock(blockpos, blockstate1, 2);
        }

    }
}
