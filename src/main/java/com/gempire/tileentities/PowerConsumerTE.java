package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerConsumer;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class PowerConsumerTE extends PowerConductorTE implements IPowerConsumer {
    public ArrayList<BlockPos> GENERATORS = new ArrayList<>();

    public PowerConsumerTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ArrayList<BlockPos> getGenerators() {
        return GENERATORS;
    }
}
