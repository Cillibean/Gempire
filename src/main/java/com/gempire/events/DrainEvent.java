package com.gempire.events;

import com.gempire.tileentities.GemSeedTE;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrainEvent extends Event {
    public ArrayList<BlockPos> positions;

    public DrainEvent(ArrayList<BlockPos> pos){
        this.positions = pos;
    }
}
