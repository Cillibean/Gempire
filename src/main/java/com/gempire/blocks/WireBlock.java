package com.gempire.blocks;

import com.gempire.blocks.markers.IPowerMarker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;

public class WireBlock extends SixWayConnectorBlock {

    public WireBlock(float apothem, Properties properties) {
        super(apothem, properties);
    }

    @Override
    public boolean typeMarker(Block predicate) {
        return predicate instanceof IPowerMarker;
    }
}
