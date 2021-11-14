package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;

import java.util.ArrayList;

public interface ISixWayInterface {

    void setupInitialSockets();

    void setupSocket(int ID, Socket socket);

    ArrayList<Socket> getSockets();
}
