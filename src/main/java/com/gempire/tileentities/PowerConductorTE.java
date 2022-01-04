package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerConductor;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PowerConductorTE extends PowerProviderTE implements IPowerConductor {

    public PowerConductorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }


    @Override
    public TileEntity getTE() {
        return this;
    }

    @Override
    public IPowerConductor getThisConductor() {
        return this;
    }
}
