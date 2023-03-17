package com.gempire.tileentities;

import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PowerProviderTE extends SixWayInterfaceTE implements IPowerProvider {
    float voltage;
    Battery battery;

    public PowerProviderTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn,pos,state);
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
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ReadPoweredMachine(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        WritePoweredMachine(compound);
        super.saveAdditional(compound);
    }

    @Override
    public BlockEntity getTE() {
        return this;
    }
}
