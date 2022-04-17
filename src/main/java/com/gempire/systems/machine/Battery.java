package com.gempire.systems.machine;

import net.minecraft.nbt.CompoundTag;

public class Battery {
    float maxCapacity;
    float charge;

    public Battery(float maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public Battery(float maxCapacity, float charge){
        this.maxCapacity = maxCapacity;
        this.charge = charge;
    }

    public float getMaxCapacity(){
        return maxCapacity;
    }

    public float getCharge(){
        return charge;
    }

    public void setCharge(float capacity){
        charge = capacity;
    }

    public void chargeBattery(float amount){
        if(getCharge() + amount <= getMaxCapacity()) {
            charge += amount;
        }
        else{
            setCharge(getMaxCapacity());
        }
    }

    public void dischargeBattery(float amount){
        if(getCharge() - amount >= 0){
            charge -= amount;
        }
        else{
            setCharge(0);
        }
    }

    public static CompoundTag WriteBattery(Battery battery){
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("capacity", battery.getMaxCapacity());
        nbt.putFloat("charge", battery.getCharge());
        return nbt;
    }

    public static Battery GetBatteryFromNBT(CompoundTag nbt){
        float maxBattery = nbt.getFloat("capacity");
        float charge = nbt.getFloat("charge");
        return new Battery(maxBattery, charge);
    }
}
