package com.gempire.systems.machine;

import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class EnergyPackage {
    BlockPos position;
    UUID identifier;

    public EnergyPackage(BlockPos position){
        this.position = position;
        this.identifier = UUID.randomUUID();
    }

    public BlockPos getPosition() { 
        return position;
    }

    public UUID getIdentifier() {
        return identifier;
    }
}
