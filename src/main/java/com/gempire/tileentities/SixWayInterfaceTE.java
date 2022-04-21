package com.gempire.tileentities;

import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.interfaces.ISixWayInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class SixWayInterfaceTE extends BlockEntity implements ISixWayInterface {
    ArrayList<Socket> SOCKETS = new ArrayList<>();

    public SixWayInterfaceTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn,pos,state);
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
