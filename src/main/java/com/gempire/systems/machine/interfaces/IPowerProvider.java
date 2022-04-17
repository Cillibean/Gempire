package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.SocketType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Direction;

import java.util.ArrayList;

public interface IPowerProvider extends ISixWayInterface {
    float getVoltage();
    void combineVoltage(float inVoltage);
    void setVoltage(float inVoltage);
    boolean isSource();
    Battery getBattery();
    void setupBattery(float maxCapacity);
    void setBattery(Battery battery);
    default float getBandwidth(){
        return 1f;
    }
    default void WritePoweredMachine(CompoundTag nbt){
        nbt.put("battery", Battery.WriteBattery(getBattery()));
    }
    default void ReadPoweredMachine(CompoundTag nbt){
        if(nbt.contains("battery")) setBattery(Battery.GetBatteryFromNBT(nbt.getCompound("battery")));
    }
}
