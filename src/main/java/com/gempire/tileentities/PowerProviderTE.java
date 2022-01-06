package com.gempire.tileentities;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerProvider;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PowerProviderTE extends SixWayInterfaceTE implements IPowerProvider {
    float voltage;
    Battery battery;

    public PowerProviderTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public float getVoltage() {
        return voltage;
    }

    @Override
    public void combineVoltage(float inVoltage) {
        voltage += inVoltage;
    }

    @Override
    public void setVoltage(float inVoltage) {
        voltage = inVoltage;
    }

    @Override
    public boolean isSource() {
        return false;
    }

    @Override
    public Battery getBattery() {
        return battery;
    }

    @Override
    public void setupBattery(float maxCapacity) {
        battery = new Battery(maxCapacity);
    }

    @Override
    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        ReadPoweredMachine(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        WritePoweredMachine(compound);
        return super.write(compound);
    }
}
