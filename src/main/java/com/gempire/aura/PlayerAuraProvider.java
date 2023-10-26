package com.gempire.aura;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.level.NoteBlockEvent;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerAuraProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerAura> PLAYER_AURA = CapabilityManager.get(new CapabilityToken<PlayerAura>() {});

    private PlayerAura aura = null;
    private  final LazyOptional<PlayerAura> optional = LazyOptional.of(this::createPlayerAura);

    private PlayerAura createPlayerAura() {
        if (this.aura == null) {
            this.aura = new PlayerAura();
        }

        return this.aura;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_AURA) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerAura().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerAura().loadNBTData(nbt);
    }
}
