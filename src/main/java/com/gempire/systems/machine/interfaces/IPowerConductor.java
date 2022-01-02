package com.gempire.systems.machine.interfaces;

import com.gempire.systems.injection.GemFormation;
import com.gempire.systems.machine.EnergyPackage;
import com.gempire.tileentities.PowerConductorTE;
import com.gempire.util.Debug;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface IPowerConductor extends IPowerProvider {
    ArrayList<EnergyPackage> getStoredPackages();
    EnergyPackage getCurrentPackage();
    default void setCurrentPackage(EnergyPackage NEW_PACKAGE, IPowerConductor conductor, TileEntity te){
        if(conductor.getCurrentPackage() != EnergyPackage.DEFAULT()){
            conductor.getStoredPackages().add(conductor.getCurrentPackage());
            conductor.cleanupStoredPackages(conductor, te);
        }
        conductor.setCP(NEW_PACKAGE);
        conductor.setChanged(true);
        te.markDirty();
        Debug.OnReceivePackage(te);
    }
    default void tryConductPackage(EnergyPackage PACKAGE, IPowerConductor conductor, TileEntity te){
        if(conductor.getConduct() && PACKAGE != EnergyPackage.DEFAULT()) conductor.conductPackage(PACKAGE, conductor, te); te.markDirty();
    }
    default void conductPackage(EnergyPackage PACKAGE, IPowerConductor conductor, TileEntity te){
        for(int i = 0; i < 6; i++){
            if(te.getWorld().getTileEntity(te.getPos().add(GemFormation.DirectionFromFacing(i))) instanceof IPowerConductor){
                IPowerConductor outlet = (IPowerConductor) te.getWorld().getTileEntity(te.getPos().add(GemFormation.DirectionFromFacing(i)));
                outlet.receivePackage(PACKAGE, outlet, te);
                conductor.setChanged(false);
            }
        }
    }
    default void sendPackage(EnergyPackage PACKAGE, BlockPos pos, IPowerConductor conductor, TileEntity te){
        if(te.getWorld().getTileEntity(pos) instanceof IPowerConductor){
            IPowerConductor outlet = (IPowerConductor) te.getWorld().getTileEntity(pos);
            outlet.receivePackage(PACKAGE, conductor, te);
            conductor.setChanged(false);
            te.markDirty();
        }
    }
    default void receivePackage(EnergyPackage PACKAGE, IPowerConductor conductor, TileEntity te){
        System.out.println("[DEBUG]" + PACKAGE.getIdentifier());
        if(conductor.getStoredPackages().size() == 0){
            conductor.setCurrentPackage(PACKAGE, conductor, te);
        }
        else {
            boolean flag = false;
            for (EnergyPackage pack : conductor.getStoredPackages()) {
                if (pack.getIdentifier().equals(PACKAGE.getIdentifier())) {
                    conductor.rejectPackage(PACKAGE, conductor, te);
                    System.out.println("[DEBUG] FLAGGED ERROR");
                    flag = true;
                }
            }
            if(!flag){
                conductor.setCurrentPackage(PACKAGE, conductor, te);
            }
        }
    }
    default void cleanupStoredPackages(IPowerConductor conductor, TileEntity te){
        if(conductor.getStoredPackages().size() > PowerConductorTE.MAX_STORE_SIZE){
            conductor.getStoredPackages().remove(0);
            conductor.getStoredPackages().trimToSize();
            te.markDirty();
        }
    }
    default void TickTE(IPowerConductor conductor, TileEntity te){
        if(conductor.getConduct() && conductor.getChanged()){
            conductor.tryConductPackage(conductor.getCurrentPackage(), conductor, te);
            conductor.setConduct(false);
        }
        else{
            if(conductor.getTicks() > conductor.conductThreshold()){
                conductor.setConduct(true);
            }
        }
        conductor.addTicks();
    }
    default void ReadConductor(BlockState state, CompoundNBT nbt, IPowerConductor conductor, TileEntity te){
        if(nbt.contains("currentPackage") && nbt.contains("storedPackages") && nbt.contains("changed") && nbt.contains("conduct")) {
            ListNBT listNBT = nbt.getList("storedPackages", 10);
            for(int i = 0; i < listNBT.size(); i++){
                conductor.getStoredPackages().add(EnergyPackage.PackageFromNBT(listNBT.getCompound(i)));
            }

            CompoundNBT tag = (CompoundNBT) nbt.get("currentPackage");
            conductor.setCurrentPackage(EnergyPackage.PackageFromNBT(tag), conductor, te);

            conductor.setChanged(nbt.getBoolean("changed"));
            conductor.setConduct(nbt.getBoolean("conduct"));
        }
    }
    default CompoundNBT WriteConductor(CompoundNBT compound, IPowerConductor conductor, TileEntity te){
        ListNBT listNBT = new ListNBT();
        for(int i = 0; i < conductor.getStoredPackages().size(); i++){
            listNBT.add(i, EnergyPackage.NBTFromPackage(conductor.getStoredPackages().get(i)));
        }
        compound.put("storedPackages", listNBT);
        compound.put("currentPackage", EnergyPackage.NBTFromPackage(conductor.getCurrentPackage()));
        compound.putBoolean("changed", conductor.getChanged());
        compound.putBoolean("conduct", conductor.getConduct());
        return compound;
    }
    default void rejectPackage(EnergyPackage PACKAGE, IPowerConductor conductor, TileEntity te){
        Debug.OnRejectPackage(te);
    }
    void setChanged(boolean value);
    boolean getChanged();
    void setConduct(boolean value);
    boolean getConduct();
    void setCP(EnergyPackage PACKAGE);
    int getTicks();
    void addTicks();
    default int conductThreshold(){
        return 5;
    }
}
