package com.gempire.events;

import com.gempire.entities.bases.EntityGem;
import com.gempire.tileentities.GemSeedTE;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class InjectEvent extends Event {
    public GemSeedTE seed;
    public BlockPos pos;

    public InjectEvent(GemSeedTE seed, BlockPos pos){
        this.seed = seed;
        this.pos = pos;
    }

    @Override
    public boolean isCancelable() {
        return false;
    }
}
