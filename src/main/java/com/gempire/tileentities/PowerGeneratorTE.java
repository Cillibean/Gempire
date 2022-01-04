package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerGenerator;
import net.minecraft.tileentity.TileEntityType;

public class PowerGeneratorTE extends PowerProviderTE implements IPowerGenerator {

    public PowerGeneratorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
