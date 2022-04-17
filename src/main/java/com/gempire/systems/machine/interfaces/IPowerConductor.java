package com.gempire.systems.machine.interfaces;

import com.gempire.tileentities.ShellTE;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;

public interface IPowerConductor extends IPowerProvider {
    default boolean isAllowedToExist(){
        if(this instanceof IPowerConsumer){
            return true;
        }
        for(Direction direction : getPowerSocketDirections()){
            BlockPos otherConductorPosition = getTE().getBlockPos().relative(direction);
            if(getTE().getLevel().getBlockEntity(otherConductorPosition) instanceof IPowerProvider){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getLevel().getBlockEntity(otherConductorPosition);
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
        for(Direction direction : getPowerSocketDirections()){
            if(this instanceof ShellTE) {
                System.out.println(direction);
            }
            BlockPos otherConductorPosition = getTE().getBlockPos().relative(direction);
            if(getTE().getLevel().getBlockEntity(otherConductorPosition) instanceof IPowerProvider){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getLevel().getBlockEntity(otherConductorPosition);
                if(otherConductor.getBattery().getCharge() == p){
                    powerProvider = otherConductor;
                }
            }
        }
        return powerProvider;
    }

    default float getHighestSurroundingVoltage(){
        float v = 0;
        for(Direction direction : getPowerSocketDirections()){
            BlockPos otherConductorPosition = getTE().getBlockPos().relative(direction);
            if(getTE().getLevel().getBlockEntity(otherConductorPosition) instanceof IPowerProvider && !(getTE().getLevel().getBlockEntity(otherConductorPosition) instanceof IPowerConsumer)){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getLevel().getBlockEntity(otherConductorPosition);
                v = otherConductor.getVoltage() > v ? otherConductor.getVoltage() : v;
            }
        }
        return v;
    }

    default float getHighestSurroundingPower(){
        float p = 0;
        for(Direction direction : getPowerSocketDirections()){
            BlockPos otherConductorPosition = getTE().getBlockPos().relative(direction);
            if(getTE().getLevel().getBlockEntity(otherConductorPosition) instanceof IPowerProvider && !(getTE().getLevel().getBlockEntity(otherConductorPosition) instanceof IPowerConsumer)){
                IPowerProvider otherConductor = (IPowerProvider) getTE().getLevel().getBlockEntity(otherConductorPosition);
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
        if(amount <= 0){
            return;
        }
        if(getVoltage() <= 0){
            getBattery().setCharge(0);
            getTE().getLevel().sendBlockUpdated(getTE().getBlockPos(), getTE().getBlockState(), getTE().getBlockState(), 2);
            getTE().setChanged();
        }
        else{
            if(getBattery().getCharge() < getBattery().getMaxCapacity()) {
                getBattery().chargeBattery(amount);
                provider.getBattery().dischargeBattery(amount);
                getTE().getLevel().sendBlockUpdated(getTE().getBlockPos(), getTE().getBlockState(), getTE().getBlockState(), 2);
                getTE().setChanged();
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

    BlockEntity getTE();

    default int drawTicks(){
        return 5;
    }

    int getTicks();
    void addTick();
    void setTicks(int ticks);
}
