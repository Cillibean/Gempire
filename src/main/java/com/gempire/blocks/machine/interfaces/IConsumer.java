package com.gempire.blocks.machine.interfaces;

import com.gempire.systems.machine.interfaces.IPowerConductor;
import com.gempire.systems.machine.interfaces.IPowerConsumer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public interface IConsumer {
    default IPowerConsumer getConductor(BlockPos pos, IBlockReader world){
        return (IPowerConsumer) world.getTileEntity(pos);
    }
}
