package com.gempire.blocks;

import com.gempire.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.KelpPlantBlock;

public class DesolateKelpPlantBlock extends KelpPlantBlock {
    public DesolateKelpPlantBlock(Properties p_54300_) {
        super(p_54300_);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.COBALT_KELP.get();
    }
}
