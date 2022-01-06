package com.gempire.tileentities;

import com.gempire.systems.machine.interfaces.IPowerConsumer;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class PowerConsumerTE extends PowerConductorTE implements IPowerConsumer{

    public PowerConsumerTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        setupBattery(1000);
    }

    int drawTicks = 0;

    @Override
    public int getTicks() {
        return drawTicks;
    }

    @Override
    public void addTick() {
        drawTicks++;
    }

    @Override
    public void setTicks(int ticks) {
        drawTicks = ticks;
    }
}
