package com.gempire.systems.injection;

import com.gempire.util.CruxType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class Crux {
    public Block block;
    public float weight;
    public CruxType type;
    public float temperature = 1;

    public Crux(BlockState block, float weight, CruxType type){
        this.block = block.getBlock();
        this.weight = weight;
        this.type = type;
    }

    public Crux(BlockState block, float weight, CruxType type, float temperature){
        this.block = block.getBlock();
        this.weight = weight;
        this.type = type;
        this.temperature = temperature;
    }

    public Crux(Block block, float weight, CruxType type){
        this.block = block;
        this.weight = weight;
        this.type = type;
    }

    public Crux(Block block, float weight, CruxType type, float temperature){
        this.block = block;
        this.weight = weight;
        this.type = type;
        this.temperature = temperature;
    }
}
