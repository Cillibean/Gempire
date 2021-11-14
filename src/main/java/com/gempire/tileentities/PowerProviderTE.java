package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PowerProviderTE extends SixWayInterfaceTE implements IPowerProvider {

    public PowerProviderTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
