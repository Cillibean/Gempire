package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;

import java.util.ArrayList;

public interface ISixWayInterface {
    ArrayList<Socket> SOCKETS = new ArrayList<>();

    default void setupInitialSockets(){
        for(int i = 0; i < 6; i++){
            SOCKETS.add(Socket.GENERIC(MachineSide.getSide(i)));
        }
    }

    default void setupSocket(int ID, Socket socket) {
        if(ID >= SOCKETS.size() ||  ID < 0) throw new ArrayIndexOutOfBoundsException();
        SOCKETS.set(ID, socket);
    }
}
