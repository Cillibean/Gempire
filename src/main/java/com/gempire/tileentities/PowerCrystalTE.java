package com.gempire.tileentities;

import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.init.ModTE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class PowerCrystalTE extends BlockEntity {

    public PowerCrystalTE(BlockPos pos, BlockState state) {
        super(ModTE.POWER_CRYSTAL_TE.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {

    }

}
