package com.gempire.blocks;

import com.gempire.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Iterator;

public class PinculeBlock extends CactusBlock {
    public PinculeBlock(Properties p_51136_) {
        super(p_51136_);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return plantable.getPlant(world, pos.relative(facing)).getBlock() == ModBlocks.PINCULE.get();
    }
}
