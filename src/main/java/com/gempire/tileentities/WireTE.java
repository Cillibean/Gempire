package com.gempire.tileentities;

import com.gempire.init.ModTE;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WireTE extends PowerConductorTE  {
    public WireTE(BlockPos pos, BlockState state) {
        super(ModTE.WIRE_TE.get(),pos,state);
        setupBattery(10);
        setupInitialSockets(this);
        setupSocket(0, Socket.POWER_IN(MachineSide.BOTTOM), this);
        setupSocket(1, Socket.POWER_IN(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_IN(MachineSide.BACK), this);
        setupSocket(3, Socket.POWER_IN(MachineSide.FRONT), this);
        setupSocket(4, Socket.POWER_IN(MachineSide.LEFT), this);
        setupSocket(5, Socket.POWER_IN(MachineSide.RIGHT), this);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        WireTE te = (WireTE)be;
        if(!level.isClientSide()){
            te.ConductorTick();
        }
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
