package com.gempire.tileentities;

import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.interfaces.ISixWayInterface;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;

public class SixWayInterfaceTE extends BlockEntity implements ISixWayInterface {
    ArrayList<Socket> SOCKETS = new ArrayList<>();

    public SixWayInterfaceTE(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ArrayList<Socket> getSockets() {
        return SOCKETS;
    }

    @Override
    public BlockEntity getTE() {
        return this;
    }
}
