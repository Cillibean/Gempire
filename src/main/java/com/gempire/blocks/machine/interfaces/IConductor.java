package com.gempire.blocks.machine.interfaces;

import com.gempire.tileentities.PowerConductorTE;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public interface IConductor {
    default PowerConductorTE getConductor(BlockPos pos, IBlockReader world){
        return (PowerConductorTE) world.getTileEntity(pos);
    }
}
