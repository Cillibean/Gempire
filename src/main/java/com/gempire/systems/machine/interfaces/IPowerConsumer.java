package com.gempire.systems.machine.interfaces;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.systems.machine.EnergyPackage;
import com.gempire.tileentities.PowerConductorTE;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface IPowerConsumer extends IPowerConductor {
    ArrayList<BlockPos> getGenerators();
    default void removeGenerator(IPowerConsumer power, TileEntity te, int ID){
        power.getGenerators().remove(ID);
        power.getGenerators().trimToSize();
        te.markDirty();
    }
    default void removeGenerator(IPowerConsumer power, TileEntity te, BlockPos pos){
        power.getGenerators().remove(pos);
        power.getGenerators().trimToSize();
        te.markDirty();
    }
    default void DrainPower(IPowerConsumer consumer, TileEntity te){
        if(consumer.getGenerators().size() > 0) te.getWorld().addParticle(ParticleTypes.EXPLOSION, te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), 0, 0, 0);
    }
    default void addGenerator(EnergyPackage PACKAGE, IPowerConsumer consumer, TileEntity te){
        consumer.getGenerators().add(PACKAGE.getPosition());
    }
    default void ReadConsumer(BlockState state, CompoundNBT nbt, IPowerConsumer consumer, TileEntity te){
        if(nbt.contains("generators")) {
            ListNBT listNBT = nbt.getList("generators", 10);
            for(int i = 0; i < listNBT.size(); i++){
                int[] pos = nbt.getIntArray("position");
                consumer.getGenerators().add(new BlockPos(pos[0],pos[1],pos[2]));
            }
        }
    }
    default CompoundNBT WriteConsumer(CompoundNBT compound, IPowerConsumer consumer, TileEntity te){
        ListNBT listNBT = new ListNBT();
        for(int i = 0; i < consumer.getGenerators().size(); i++){
            CompoundNBT nbt = new CompoundNBT();
            int[] pos = new int[3];
            pos[0] = consumer.getGenerators().get(i).getX();
            pos[1] = consumer.getGenerators().get(i).getY();
            pos[2] = consumer.getGenerators().get(i).getZ();
            nbt.putIntArray("position", pos);
            listNBT.add(i, nbt);
        }
        compound.put("generators", listNBT);
        return compound;
    }

    @Override
    default void receivePackage(EnergyPackage PACKAGE, IPowerConductor conductor, TileEntity te) {
        if(conductor.getStoredPackages().size() == 0){
            conductor.setCurrentPackage(PACKAGE, conductor, te);
            if(conductor instanceof IPowerConsumer) receivePackageGenerator(PACKAGE, (IPowerConsumer)conductor, te);
        }
        else {
            boolean flag = false;
            for (EnergyPackage pack : conductor.getStoredPackages()) {
                if (pack.getIdentifier().equals(PACKAGE.getIdentifier())) {
                    conductor.rejectPackage(PACKAGE, conductor, te);
                    flag = true;
                }
            }
            if(!flag){
                conductor.setCurrentPackage(PACKAGE, conductor, te);
                if(conductor instanceof IPowerConsumer) receivePackageGenerator(PACKAGE, (IPowerConsumer)conductor, te);
            }
        }
    }
    default void receivePackageGenerator(EnergyPackage PACKAGE, IPowerConsumer consumer, TileEntity te){
        if(PACKAGE.getState()){
            for(int i = 0; i < consumer.getGenerators().size(); i++){
                if(PACKAGE.getPosition().equals(consumer.getGenerators().get(i))){
                    consumer.getGenerators().remove(i);
                    System.out.println("[DEBUG] REMOVED GENERATOR");
                    break;
                }
            }
        }
    }
}