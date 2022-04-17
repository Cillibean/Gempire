package com.gempire.systems.machine.interfaces;

import net.minecraft.world.level.block.entity.BlockEntity;

public interface IPowerGenerator extends IPowerProvider {
    default void generatePower(){
        getBattery().chargeBattery(powerRating());
    }

    @Override
    default boolean isSource(){
        return true;
    }

    default float powerRating(){
        return 1;
    }

    BlockEntity getTE();
}
