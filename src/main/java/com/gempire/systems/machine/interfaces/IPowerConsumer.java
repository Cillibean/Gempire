package com.gempire.systems.machine.interfaces;


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

    @Override
    default void receivePower(float amount, IPowerProvider provider) {
        if (getBattery().getCharge() < getBattery().getMaxCapacity()) {
            getBattery().chargeBattery(amount);
            provider.getBattery().dischargeBattery(amount);
        }
    }
}