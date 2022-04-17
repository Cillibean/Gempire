package com.gempire.events;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.core.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class GemFormEvent extends Event {
    public EntityGem gem;
    public BlockPos pos;

    public GemFormEvent(EntityGem gem, BlockPos pos){
        this.gem = gem;
        this.pos = pos;
    }
}
