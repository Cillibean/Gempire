package com.gempire.blocks;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TestFluidBlock extends LiquidBlock {

    public TestFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }
}
