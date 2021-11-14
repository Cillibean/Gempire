package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerConsumer;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;

public class PowerConsumerTE extends PowerConductorTE implements IPowerConsumer {
    ArrayList<IPowerGenerator> GENERATORS = new ArrayList<>();

    public PowerConsumerTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ArrayList<IPowerGenerator> getGenerators() {
        return GENERATORS;
    }
}
