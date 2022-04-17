package com.gempire.tileentities;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PowerConductorTE extends PowerProviderTE implements IPowerConductor {

    public PowerConductorTE(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public BlockEntity getTE() {
        return this;
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
