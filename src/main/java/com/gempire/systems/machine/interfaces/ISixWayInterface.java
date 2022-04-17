package com.gempire.systems.machine.interfaces;

import com.gempire.blocks.machine.ShellBlock;
import com.gempire.blocks.machine.WireBlock;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.SocketType;
import com.gempire.tileentities.SixWayInterfaceTE;
import com.gempire.tileentities.WireTE;
import jdk.nashorn.tools.Shell;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import java.util.ArrayList;

public interface ISixWayInterface {
    default void setupInitialSockets(ISixWayInterface swi){
        for(int i = 0; i < 6; i++){
            swi.getSockets().add(Socket.GENERIC(MachineSide.getSide(i)));
        }
    }
    default void setupSocket(int ID, Socket socket, ISixWayInterface swi){
        if(ID >= swi.getSockets().size() ||  ID < 0) throw new ArrayIndexOutOfBoundsException();
        swi.getSockets().set(ID, socket);
    }
    ArrayList<Socket> getSockets();
    default ArrayList<Direction> getPowerSocketDirections(){
        ArrayList<Direction> directions = new ArrayList<>();
        for(Socket socket : getSockets()){
            if(socket.isChargeable() || socket.getType() == SocketType.POWER){
                boolean doTheThing = !(this instanceof WireTE);
                if(doTheThing){
                    directions.add(Direction.byIndex(ShellBlock.getAdjustedDirectionValue(ShellBlock.getDirectionFacingValue(getTE()), socket.getSide().id)));
                }
                else{
                    directions.add(Direction.byIndex(socket.getSide().id));
                }
            }
        }
        return directions;
    }
    TileEntity getTE();
}
