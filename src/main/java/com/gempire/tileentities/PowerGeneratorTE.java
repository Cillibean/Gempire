package com.gempire.tileentities;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PowerGeneratorTE extends PowerProviderTE implements IPowerGenerator {

    public PowerGeneratorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        setupBattery(1000);
    }

    @Override
    public TileEntity getTE() {
        return this;
    }

    @Override
    public boolean isSource() {
        return true;
    }
}
