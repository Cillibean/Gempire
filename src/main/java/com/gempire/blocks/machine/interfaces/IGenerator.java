package com.gempire.blocks.machine.interfaces;

import com.gempire.systems.machine.interfaces.IPowerGenerator;
import com.gempire.tileentities.PowerConductorTE;
import com.gempire.tileentities.PowerGeneratorTE;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public interface IGenerator {
    default IPowerGenerator getGenerator(BlockPos pos, IBlockReader world){
        return (IPowerGenerator) world.getTileEntity(pos);
    }
    default TileEntity getTE(BlockPos pos, IBlockReader world){
        return world.getTileEntity(pos);
    }
}
