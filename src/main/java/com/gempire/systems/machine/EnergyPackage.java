package com.gempire.systems.machine;

import com.sun.corba.se.impl.logging.POASystemException;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.system.CallbackI;

import java.util.UUID;

public class EnergyPackage {
    BlockPos position;
    UUID identifier;
    boolean remove;

    public EnergyPackage(boolean remove){
        this(BlockPos.ZERO, remove);
    }

    public EnergyPackage(BlockPos position){
        this.position = position;
        this.identifier = UUID.randomUUID();
        this.remove = false;
    }
    public EnergyPackage(BlockPos position, boolean remove){
        this.position = position;
        this.identifier = UUID.randomUUID();
        this.remove = remove;
    }

    public EnergyPackage(BlockPos position, UUID identifier){
        this.position = position;
        this.identifier = identifier;
        this.remove = false;
    }

    public EnergyPackage(BlockPos position, UUID identifier, boolean remove){
        this.position = position;
        this.identifier = identifier;
        this.remove = remove;
    }

    public static EnergyPackage DEFAULT(){
        return new EnergyPackage(BlockPos.ZERO);
    }

    public BlockPos getPosition() { 
        return position;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public boolean getState() {
        return remove;
    }

    public static CompoundNBT NBTFromPackage(EnergyPackage PACKAGE){
        CompoundNBT tag = new CompoundNBT();
        tag.putUniqueId("identifier", PACKAGE.getIdentifier());
        int[] position = new int[3];
        position[0] = PACKAGE.getPosition().getX();
        position[1] = PACKAGE.getPosition().getY();
        position[2] = PACKAGE.getPosition().getZ();
        tag.putIntArray("position", position);
        tag.putBoolean("remove", PACKAGE.getState());
        return tag;
    }

    public static EnergyPackage PackageFromNBT(CompoundNBT tag){
        UUID identifier = tag.getUniqueId("identifier");
        BlockPos position = new BlockPos(
                tag.getIntArray("position")[0],
                tag.getIntArray("position")[1],
                tag.getIntArray("position")[2]);
        boolean remove = tag.getBoolean("remove");
        return new EnergyPackage(position, identifier, remove);
    }
}
