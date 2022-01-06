package com.gempire.blocks.machine;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import com.gempire.tileentities.WireTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class WireBlock extends SixWayConnectorBlock implements IPowerMarker {

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

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WireTE();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
