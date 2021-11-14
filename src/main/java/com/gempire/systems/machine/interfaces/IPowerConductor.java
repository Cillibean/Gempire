package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.EnergyPackage;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface IPowerConductor extends IPowerProvider {
    ArrayList<EnergyPackage> getStoredPackages();
    void setCurrentPackage(EnergyPackage NEW_PACKAGE);
    void tryConductPackage(EnergyPackage PACKAGE);
    void conductPackage(EnergyPackage PACKAGE);
    void sendPackage(EnergyPackage PACKAGE, BlockPos pos);
    void receivePackage(EnergyPackage PACKAGE);
    void cleanupStoredPackages();
    default void rejectPackage(EnergyPackage PACKAGE){

    }
    default int conductThreshold(){
        return 5;
    }
}
