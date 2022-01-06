package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.Battery;
import net.minecraft.nbt.CompoundNBT;

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
    default void WritePoweredMachine(CompoundNBT nbt){
        nbt.put("battery", Battery.WriteBattery(getBattery()));
    }
    default void ReadPoweredMachine(CompoundNBT nbt){
        if(nbt.contains("battery")) setBattery(Battery.GetBatteryFromNBT(nbt.getCompound("battery")));
    }
}
