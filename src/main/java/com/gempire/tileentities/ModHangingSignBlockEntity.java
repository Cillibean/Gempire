package com.gempire.tileentities;

import com.gempire.init.ModTE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntity extends SignBlockEntity {
    public ModHangingSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModTE.MOD_HANGING_SIGN.get(), blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModTE.MOD_HANGING_SIGN.get();
    }
}
