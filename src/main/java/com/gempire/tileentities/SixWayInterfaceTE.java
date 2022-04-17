package com.gempire.tileentities;

import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.interfaces.ISixWayInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;

public class SixWayInterfaceTE extends TileEntity implements ISixWayInterface {
    ArrayList<Socket> SOCKETS = new ArrayList<>();

    public SixWayInterfaceTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ArrayList<Socket> getSockets() {
        return SOCKETS;
    }

    @Override
    public TileEntity getTE() {
        return this;
    }
}
