package com.gempire.blocks;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.PowerConductorTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class WireBlock extends SixWayConnectorBlock {

    public WireBlock(float apothem, Properties properties) {
        super(apothem, properties);
    }

    @Override
    public boolean typeMarker(Block predicate) {
        return predicate instanceof IPowerMarker;
    }

    /*@Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PowerConductorTE();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }*/
}
