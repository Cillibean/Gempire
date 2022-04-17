package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.Battery;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public interface IPoweredItem {
    Battery getBattery();
    void setupBattery(float maxCapacity);
    void setBattery(Battery battery);
    default float getBandwidth(){
        return 1f;
    }
    default void WritePowered(CompoundTag nbt){
        nbt.put("battery", Battery.WriteBattery(getBattery()));
    }
    default boolean ReadPowered(CompoundTag nbt){
        if(nbt.contains("battery")){
            setBattery(Battery.GetBatteryFromNBT(nbt.getCompound("battery")));
            return true;
        }
        return false;
    }
    default float powerToConsumePerUse(){
        return 100;
    }
    default void use(ItemStack stack){
        System.out.println("[DEBUG] POWER USED");
        getBattery().dischargeBattery(powerToConsumePerUse());
        System.out.println("[DEBUG] " + getBattery().getCharge());
        //stack.getItem().updateItemStackNBT(stack.getTag());
    }
    default boolean isPowered(){
        return getBattery().getCharge() >= powerToConsumePerUse();
    }
}
