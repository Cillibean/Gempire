package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PowerProviderTE extends SixWayInterfaceTE implements IPowerProvider {
    public float voltage;

    public PowerProviderTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public float getVoltage() {
        return voltage;
    }

    @Override
    public void combineVoltage(float inVoltage) {
        voltage += inVoltage;
    }

    @Override
    public void setVoltage(float inVoltage) {
        voltage = inVoltage;
    }

    @Override
    public boolean isSource() {
        return false;
    }
}
