package com.gempire.systems.machine.interfaces;

import com.gempire.systems.machine.EnergyPackage;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.SocketType;
import com.gempire.tileentities.PowerConductorTE;
import com.gempire.tileentities.PowerGeneratorTE;
import com.gempire.util.Debug;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface IPowerGenerator extends IPowerProvider {
    default void emitPackage(boolean remove, IPowerGenerator generator, TileEntity te){
        for(Socket socket : generator.getSockets()){
            if(socket.getType().equals(SocketType.POWER)){
                if(!socket.isIO()){
                    BlockPos nextPos = te.getPos().add(Direction.byIndex(socket.getSide().id).getDirectionVec());
                    if(te.getWorld().getTileEntity(nextPos) instanceof IPowerConductor){
                        TileEntity tile = te.getWorld().getTileEntity(nextPos);
                        IPowerConductor conductor = (IPowerConductor) tile;
                        conductor.receivePackage(generator.generatePackage(remove, generator, te), conductor, tile);
                    }
                }
            }
        }
    }
    default EnergyPackage generatePackage(boolean remove, IPowerGenerator generator, TileEntity te){
        if(!remove) {
            return new EnergyPackage(te.getPos());
        }
        else {
            System.out.println("[DEBUG] REMOVE PACKET DETECTED");
            return new EnergyPackage(true);
        }
    }
}
