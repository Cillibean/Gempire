package com.gempire.tileentities;

import com.gempire.systems.machine.EnergyPackage;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;

public class PowerConductorTE extends PowerProviderTE implements IPowerConductor, ITickableTileEntity {
    public EnergyPackage CURRENT_PACKAGE = EnergyPackage.DEFAULT();
    public ArrayList<EnergyPackage> STORED_PACKAGES = new ArrayList<>();
    boolean CHANGED = false;
    boolean CONDUCT = false;
    int tiks = 0;

    public static final int MAX_STORE_SIZE = 10;

    public PowerConductorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ArrayList<EnergyPackage> getStoredPackages() {
        return STORED_PACKAGES;
    }

    @Override
    public EnergyPackage getCurrentPackage() {
        return CURRENT_PACKAGE;
    }

    @Override
    public void setChanged(boolean value) {
        CHANGED = value;
    }

    @Override
    public boolean getChanged() {
        return CHANGED;
    }

    @Override
    public void setConduct(boolean value) {
        CONDUCT = value;
    }

    @Override
    public boolean getConduct() {
        return CONDUCT;
    }

    @Override
    public void setCP(EnergyPackage PACKAGE) {
        CURRENT_PACKAGE = PACKAGE;
    }

    @Override
    public int getTicks() {
        return tiks;
    }

    @Override
    public void addTicks() {
        tiks++;
    }

    @Override
    public void tick() {
        TickTE(this, this);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        ReadConductor(state, nbt, this, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        WriteConductor(compound, this, this);
        return compound;
    }
}
