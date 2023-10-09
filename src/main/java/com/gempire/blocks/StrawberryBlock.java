package com.gempire.blocks;

import com.gempire.init.ModBlocks;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StemGrownBlock;

public class StrawberryBlock extends StemGrownBlock {

    public StrawberryBlock(Properties p_57058_) {
        super(p_57058_);
    }

    public StemBlock getStem() {
        return (StemBlock) ModBlocks.STRAWBERRY_STEM.get();
    }

    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock) ModBlocks.ATTACHED_STRAWBERRY_STEM.get();
    }
}
