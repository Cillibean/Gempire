package com.gempire.tileentities;

import com.gempire.init.ModTE;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import net.minecraft.tileentity.TileEntityType;

public class WireTE extends PowerConductorTE{

    public WireTE() {
        super(ModTE.WIRE_TE.get());
        setupInitialSockets(this);
        setupSocket(0, Socket.POWER_IN(MachineSide.BOTTOM), this);
        setupSocket(1, Socket.POWER_IN(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_IN(MachineSide.BACK), this);
        setupSocket(3, Socket.POWER_IN(MachineSide.FRONT), this);
        setupSocket(4, Socket.POWER_IN(MachineSide.LEFT), this);
        setupSocket(5, Socket.POWER_IN(MachineSide.RIGHT), this);
    }
}
