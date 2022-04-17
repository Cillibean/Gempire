package com.gempire.blocks.machine;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import com.gempire.tileentities.WireTE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WireBlock extends SixWayConnectorBlock implements IPowerMarker, EntityBlock {

    public WireBlock(float apothem, Properties properties) {
        super(apothem, properties);
    }

    @Override
    public boolean facingMarker(BlockPos direction) {
        return true;
    }

    @Override
    public boolean typeMarker(Block predicate) {
        return predicate instanceof IPowerMarker;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new WireTE();
    }
}
