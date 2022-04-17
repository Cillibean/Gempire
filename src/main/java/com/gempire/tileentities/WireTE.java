package com.gempire.tileentities;

import com.gempire.init.ModTE;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.tileentity.TileEntityType;

public class WireTE extends PowerConductorTE implements TickableBlockEntity {
    public WireTE() {
        super(ModTE.WIRE_TE.get());
        setupBattery(10);
        setupInitialSockets(this);
        setupSocket(0, Socket.POWER_IN(MachineSide.BOTTOM), this);
        setupSocket(1, Socket.POWER_IN(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_IN(MachineSide.BACK), this);
        setupSocket(3, Socket.POWER_IN(MachineSide.FRONT), this);
        setupSocket(4, Socket.POWER_IN(MachineSide.LEFT), this);
        setupSocket(5, Socket.POWER_IN(MachineSide.RIGHT), this);
    }

    @Override
    public void tick() {
        ConductorTick();
    }

    @Override
    public float getBandwidth() {
        return 3;
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
