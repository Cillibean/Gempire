package com.gempire.tileentities;

import com.gempire.init.ModTE;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PedistalTE extends BlockEntity {
    public UUID owner;
    public PedistalTE(BlockPos pos, BlockState state) {
        super(ModTE.PEDISTAL_TE.get(), pos, state);
    }
    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        if (compound.contains("owner")) compound.putUUID("owner", this.owner);
        super.saveAdditional(compound);
    }
    @Override
    public void load(@NotNull CompoundTag nbt) {
        if (nbt.size() != 0) this.owner = nbt.getUUID("owner");
        super.load(nbt);
    }
}
