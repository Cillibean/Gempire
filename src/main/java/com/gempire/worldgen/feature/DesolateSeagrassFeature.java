package com.gempire.worldgen.feature;

import com.gempire.blocks.TallAquaticFibreBlock;
import com.gempire.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class DesolateSeagrassFeature extends Feature<ProbabilityFeatureConfiguration> {
    public DesolateSeagrassFeature(Codec<ProbabilityFeatureConfiguration> p_66768_) {
        super(p_66768_);
    }

    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> p_160318_) {
        boolean $$1 = false;
        RandomSource $$2 = p_160318_.random();
        WorldGenLevel $$3 = p_160318_.level();
        BlockPos $$4 = p_160318_.origin();
        ProbabilityFeatureConfiguration $$5 = (ProbabilityFeatureConfiguration)p_160318_.config();
        int $$6 = $$2.nextInt(8) - $$2.nextInt(8);
        int $$7 = $$2.nextInt(8) - $$2.nextInt(8);
        int $$8 = $$3.getHeight(Heightmap.Types.OCEAN_FLOOR, $$4.getX() + $$6, $$4.getZ() + $$7);
        BlockPos $$9 = new BlockPos($$4.getX() + $$6, $$8, $$4.getZ() + $$7);
        if ($$3.getBlockState($$9).is(Blocks.WATER)) {
            boolean $$10 = $$2.nextDouble() < (double)$$5.probability;
            BlockState $$11 = $$10 ? ModBlocks.TALL_AQUATIC_FIBRE.get().defaultBlockState() : ModBlocks.AQUATIC_FIBRE.get().defaultBlockState();
            if ($$11.canSurvive($$3, $$9)) {
                if ($$10) {
                    BlockState $$12 = (BlockState)$$11.setValue(TallAquaticFibreBlock.HALF, DoubleBlockHalf.UPPER);
                    BlockPos $$13 = $$9.above();
                    if ($$3.getBlockState($$13).is(Blocks.WATER)) {
                        $$3.setBlock($$9, $$11, 2);
                        $$3.setBlock($$13, $$12, 2);
                    }
                } else {
                    $$3.setBlock($$9, $$11, 2);
                }

                $$1 = true;
            }
        }

        return $$1;
    }
}
