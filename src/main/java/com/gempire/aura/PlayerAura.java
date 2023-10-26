package com.gempire.aura;

import net.minecraft.nbt.CompoundTag;

public class PlayerAura {
    private int aura;

    public int getAura() {
        return aura;
    }

    public void setAura(int setaura) {
        aura = setaura;
    }

    public void copyFrom(PlayerAura source) {
        this.aura = source.aura;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("aura", aura);

    }

    public void loadNBTData(CompoundTag nbt) {
        aura = nbt.getInt("aura");
    }
}
