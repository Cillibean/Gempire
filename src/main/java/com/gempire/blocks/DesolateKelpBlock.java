package com.gempire.blocks;

import com.gempire.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;

public class DesolateKelpBlock extends KelpBlock {
    public DesolateKelpBlock(Properties p_54300_) {
        super(p_54300_);
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.COBALT_KELP_PLANT.get();
    }
}
