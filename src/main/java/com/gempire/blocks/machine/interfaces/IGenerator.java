package com.gempire.blocks.machine.interfaces;

import com.gempire.tileentities.PowerConductorTE;
import com.gempire.tileentities.PowerGeneratorTE;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public interface IGenerator {
    default PowerGeneratorTE getGenerator(BlockPos pos, IBlockReader world){
        return (PowerGeneratorTE) world.getTileEntity(pos);
    }
}
