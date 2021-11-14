package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.EnergyPackage;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.SocketType;

import java.util.ArrayList;

public interface IPowerGenerator extends IPowerProvider {
    void emitPackage();
    EnergyPackage generatePackage();
}
