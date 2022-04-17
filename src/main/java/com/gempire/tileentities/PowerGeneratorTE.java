package com.gempire.tileentities;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PowerGeneratorTE extends PowerProviderTE implements IPowerGenerator {

    public PowerGeneratorTE(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        setupBattery(1000);
    }

    @Override
    public BlockEntity getTE() {
        return this;
    }

    @Override
    public boolean isSource() {
        return true;
    }
}
