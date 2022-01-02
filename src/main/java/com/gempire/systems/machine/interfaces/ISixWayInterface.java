package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.tileentities.SixWayInterfaceTE;

import java.util.ArrayList;

public interface ISixWayInterface {
    default void setupInitialSockets(ISixWayInterface swi){
        for(int i = 0; i < 6; i++){
            swi.getSockets().add(Socket.GENERIC(MachineSide.getSide(i)));
        }
    }
    default void setupSocket(int ID, Socket socket, ISixWayInterface swi){
        if(ID >= swi.getSockets().size() ||  ID < 0) throw new ArrayIndexOutOfBoundsException();
        swi.getSockets().set(ID, socket);
    }
    ArrayList<Socket> getSockets();
}
