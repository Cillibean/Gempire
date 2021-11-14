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
    public void setupInitialSockets() {
        for(int i = 0; i < 6; i++){
            SOCKETS.add(Socket.GENERIC(MachineSide.getSide(i)));
        }
    }

    @Override
    public void setupSocket(int ID, Socket socket) {
        if(ID >= SOCKETS.size() ||  ID < 0) throw new ArrayIndexOutOfBoundsException();
        SOCKETS.set(ID, socket);
    }

    @Override
    public ArrayList<Socket> getSockets() {
        return SOCKETS;
    }
}
