package com.gempire.tileentities;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PowerProviderTE extends SixWayInterfaceTE implements IPowerProvider {
    float voltage;
    Battery battery;

    public PowerProviderTE(BlockEntityType<?> tileEntityTypeIn) {
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
    public void load(BlockState state, CompoundTag nbt) {
        super.load(state, nbt);
        ReadPoweredMachine(nbt);
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        WritePoweredMachine(compound);
        return super.save(compound);
    }

    @Override
    public BlockEntity getTE() {
        return this;
    }
}
