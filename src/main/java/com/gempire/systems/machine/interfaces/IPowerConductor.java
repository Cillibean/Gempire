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
                if(otherConductor.getVoltage() > getVoltage() || otherConductor.isSource()){
                    return true;
                }
            }
        }
        return false;
    }

    default IPowerProvider getHighestSurroundingPowerProvider(){
        IPowerProvider powerProvider = null;
        float p = getHighestSurroundingPower();
        for(Direction direction : Direction.values()){
            BlockPos otherConductorPosition = getTE().getPos().offset(direction);
            if(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerProvider){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getWorld().getTileEntity(otherConductorPosition);
                if(otherConductor.getBattery().getCharge() == p){
                    powerProvider = otherConductor;
                }
            }
        }
        return powerProvider;
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

    default float getHighestSurroundingPower(){
        float p = 0;
        for(Direction direction : Direction.values()){
            BlockPos otherConductorPosition = getTE().getPos().offset(direction);
            if(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerProvider){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getWorld().getTileEntity(otherConductorPosition);
                p = otherConductor.getBattery().getCharge() > p ? otherConductor.getBattery().getCharge() : p;
            }
        }
        return p;
    }

    default void adjustToSurroundingConductors(){
        if (isAllowedToExist()) {
            //VOLTAGE STUFF
            if(!isSource()) {
                float voltageToSet = getHighestSurroundingVoltage() - getResistance();
                if (voltageToSet > 0) {
                    setVoltage(voltageToSet);
                } else {
                    setVoltage(0);
                }
            }
            //VOLTAGE STUFF

            //POWER STUFF
            float powerToSet = getHighestSurroundingPower() > 0 ? getBandwidth() : 0;
            IPowerProvider provider = getHighestSurroundingPowerProvider();
            receivePower(powerToSet, provider);
            //POWER STUFF
        } else {
            setVoltage(0);
            getBattery().setCharge(0);
        }
    }

    default float getResistance(){
        return 1f;
    }

    default void receivePower(float amount, IPowerProvider provider){
        if(getVoltage() <= 0){
            getBattery().setCharge(0);
        }
        else{
            if(getBattery().getCharge() < getBattery().getMaxCapacity()) {
                getBattery().chargeBattery(amount);
                provider.getBattery().dischargeBattery(amount);
            }
        }
    }

    default void ConductorTick(){
        if(getTicks() > drawTicks()){
            adjustToSurroundingConductors();
            setTicks(0);
        }
        addTick();
    }

    TileEntity getTE();

    default int drawTicks(){
        return 5;
    }

    int getTicks();
    void addTick();
    void setTicks(int ticks);
}
