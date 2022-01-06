package com.gempire.systems.machine.interfaces;

import net.minecraft.particles.ParticleTypes;

public interface IPowerConsumer extends IPowerConductor {
    default void usePower(){
        if(getBattery().getCharge() >= minimumUnitPower()) {
            System.out.println("[DEBUG] USING POWER - CURRENT CHARGE: " + getBattery().getCharge());
            getBattery().dischargeBattery(minimumUnitPower());
            System.out.println("[DEBUG] USING POWER - POST DISCHARGE: " + getBattery().getCharge());
        }
    }

    default float minimumUnitPower(){
        return 1f;
    }

    default boolean isPowered(){
        return getBattery().getCharge() >= minimumUnitPower();
    }
}