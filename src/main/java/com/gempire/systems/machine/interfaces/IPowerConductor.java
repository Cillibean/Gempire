package com.gempire.systems.machine.interfaces;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface IPowerConductor extends IPowerProvider {
    default boolean isAllowedToExist(){
        if(this instanceof IPowerConsumer){
            return true;
        }
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
            if(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerProvider && !(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerConsumer)){
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
            if(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerProvider && !(getTE().getWorld().getTileEntity(otherConductorPosition) instanceof IPowerConsumer)){
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
            IPowerProvider provider = getHighestSurroundingPowerProvider();
            if(!(provider instanceof IPowerConsumer) && provider != null) {
                float powerToSet = 0;
                if(getHighestSurroundingPower() <= 0){
                    powerToSet = 0;
                }
                else{
                    powerToSet = getHighestSurroundingPower() > getBandwidth() ? getBandwidth() : getHighestSurroundingPower();
                }
                receivePower(powerToSet, provider);
            }
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
            getTE().getWorld().notifyBlockUpdate(getTE().getPos(), getTE().getBlockState(), getTE().getBlockState(), 2);
            getTE().markDirty();
        }
        else{
            if(getBattery().getCharge() < getBattery().getMaxCapacity()) {
                getBattery().chargeBattery(amount);
                provider.getBattery().dischargeBattery(amount);
                getTE().getWorld().notifyBlockUpdate(getTE().getPos(), getTE().getBlockState(), getTE().getBlockState(), 2);
                getTE().markDirty();
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
