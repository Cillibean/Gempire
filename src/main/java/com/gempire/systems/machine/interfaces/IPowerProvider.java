package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.SocketType;
import net.minecraft.nbt.CompoundNBT;
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
    default void WritePoweredMachine(CompoundNBT nbt){
        nbt.put("battery", Battery.WriteBattery(getBattery()));
    }
    default void ReadPoweredMachine(CompoundNBT nbt){
        if(nbt.contains("battery")) setBattery(Battery.GetBatteryFromNBT(nbt.getCompound("battery")));
    }
    default ArrayList<Direction> getPowerSocketDirections(){
        ArrayList<Direction> directions = getPowerSocketDirections();
        for(Socket socket : getSockets()){
            if(socket.isChargeable() || socket.getType() == SocketType.POWER){
                directions.add(Direction.byIndex(socket.getSide().id));
            }
        }
        return directions;
    }
}
