package com.gempire.tileentities;

import com.gempire.systems.injection.GemFormation;
import com.gempire.systems.machine.EnergyPackage;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import com.gempire.util.Debug;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class PowerConductorTE extends PowerProviderTE implements IPowerConductor, ITickableTileEntity {
    EnergyPackage CURRENT_PACKAGE = EnergyPackage.DEFAULT();
    ArrayList<EnergyPackage> STORED_PACKAGES = new ArrayList<>();
    boolean CHANGED = false;
    boolean CONDUCT = false;
    int ticks = 0;

    public static final int MAX_STORE_SIZE = 10;

    public PowerConductorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ArrayList<EnergyPackage> getStoredPackages() {
        return STORED_PACKAGES;
    }

    @Override
    public void setCurrentPackage(EnergyPackage NEW_PACKAGE) {
        if(CURRENT_PACKAGE != EnergyPackage.DEFAULT()){
            STORED_PACKAGES.add(CURRENT_PACKAGE);
            cleanupStoredPackages();
        }
        CURRENT_PACKAGE = NEW_PACKAGE;
        CHANGED = true;
        markDirty();
        Debug.OnReceivePackage(this);
    }

    @Override
    public void receivePackage(EnergyPackage PACKAGE) {
        for(EnergyPackage pack : STORED_PACKAGES){
            if(pack.getIdentifier().equals(PACKAGE.getIdentifier())){
                rejectPackage(PACKAGE);
            }
            else{
                setCurrentPackage(PACKAGE);
            }
        }
    }

    @Override
    public void tryConductPackage(EnergyPackage PACKAGE) {
        if(CHANGED) conductPackage(PACKAGE); markDirty();
    }

    @Override
    public void conductPackage(EnergyPackage PACKAGE) {
        for(int i = 0; i < 6; i++){
            if(world.getTileEntity(GemFormation.DirectionFromFacing(i)) instanceof IPowerConductor){
                IPowerConductor outlet = (IPowerConductor) world.getTileEntity(GemFormation.DirectionFromFacing(i));
                outlet.receivePackage(PACKAGE);
                CHANGED = false;
            }
        }
    }

    @Override
    public void sendPackage(EnergyPackage PACKAGE, BlockPos pos) {
        if(world.getTileEntity(pos) instanceof IPowerConductor){
            IPowerConductor outlet = (IPowerConductor) world.getTileEntity(pos);
            outlet.receivePackage(PACKAGE);
            CHANGED = false;
            markDirty();
        }
    }

    @Override
    public void cleanupStoredPackages() {
        if(STORED_PACKAGES.size() > PowerConductorTE.MAX_STORE_SIZE){
            STORED_PACKAGES.remove(0);
            STORED_PACKAGES.trimToSize();
            markDirty();
        }
    }

    @Override
    public void tick() {
        if(CONDUCT){
            tryConductPackage(CURRENT_PACKAGE);
            CONDUCT = false;
        }
        else{
            if(ticks > conductThreshold()){
                CONDUCT = true;
            }
        }
        ticks++;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if(nbt.contains("currentPackage") && nbt.contains("storedPackages")) {
            ListNBT listNBT = nbt.getList("storedPackages", 10);
            for(int i = 0; i < listNBT.size(); i++){
                STORED_PACKAGES.add(EnergyPackage.PackageFromNBT(listNBT.getCompound(i)));
            }

            CompoundNBT tag = (CompoundNBT) nbt.get("currentPackage");
            CURRENT_PACKAGE = EnergyPackage.PackageFromNBT(tag);
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT listNBT = new ListNBT();
        for(int i = 0; i < STORED_PACKAGES.size(); i++){
            listNBT.add(i, EnergyPackage.NBTFromPackage(STORED_PACKAGES.get(i)));
        }
        compound.put("storedPackages", listNBT);
        compound.put("currentPackage", EnergyPackage.NBTFromPackage(CURRENT_PACKAGE));
        return super.write(compound);
    }
}
