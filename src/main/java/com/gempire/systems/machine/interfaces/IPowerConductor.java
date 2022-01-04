package com.gempire.systems.machine.interfaces;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface IPowerConductor extends IPowerProvider {
    default boolean isAllowedToExist(){
        for(Direction direction : Direction.values()){
            BlockPos otherConductorPosition = getTE().getPos().offset(direction);
            if(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerProvider){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getWorld().getTileEntity(otherConductorPosition);
                if(otherConductor.getVoltage() > getThisConductor().getVoltage() || otherConductor.isSource()){
                    return true;
                }
            }
        }
        return false;
    }

    default float getHighestSurroundingVoltage(){
        float v = 0;
        for(Direction direction : Direction.values()){
            BlockPos otherConductorPosition = getTE().getPos().offset(direction);
            if(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerProvider){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getWorld().getTileEntity(otherConductorPosition);
                v = otherConductor.getVoltage() > v ? otherConductor.getVoltage() : v;
            }
        }
        return v;
    }

    default void adjustToSurroundingConductors(){
        if (isAllowedToExist()) {
            float voltageToSet = getHighestSurroundingVoltage() - getResistance();
            if(getVoltage() - voltageToSet < 0) {
                getThisConductor().setVoltage(voltageToSet);
            }
            else{
                getThisConductor().setVoltage(0);
            }
        } else {
            getThisConductor().setVoltage(0);
        }
    }

    default float getResistance(){
        return 1f;
    }

    TileEntity getTE();

    IPowerConductor getThisConductor();
}
