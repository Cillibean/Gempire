package com.gempire.tileentities;

import com.gempire.init.ModTE;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.util.Debug;
import net.minecraft.tileentity.ITickableTileEntity;

public class PowerCrystalTE extends PowerGeneratorTE implements ITickableTileEntity {
    int ticks = 0;
    public PowerCrystalTE() {
        super(ModTE.POWER_CRYSTAL_TE.get());
        setupInitialSockets(this);
        setupSocket(0, Socket.POWER_OUT(MachineSide.BOTTOM), this);
        setupSocket(1, Socket.POWER_OUT(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_OUT(MachineSide.BACK), this);
        setupSocket(3, Socket.POWER_OUT(MachineSide.FRONT), this);
        setupSocket(4, Socket.POWER_OUT(MachineSide.LEFT), this);
        setupSocket(5, Socket.POWER_OUT(MachineSide.RIGHT), this);
    }

    @Override
    public void tick() {
        /*if(ticks > 5){
            emitPackage(false, this,this);
            ticks=0;
        }
        ticks++;*/
    }
}
