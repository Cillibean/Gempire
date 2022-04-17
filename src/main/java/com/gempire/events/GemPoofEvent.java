package com.gempire.events;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class GemPoofEvent extends Event {
    public EntityGem gem;
    public BlockPos pos;
    public DamageSource source;

    public GemPoofEvent(EntityGem gem, BlockPos pos, DamageSource source){
        this.gem = gem;
        this.pos = pos;
        this.source = source;
    }
}
