package com.gempire.systems.injection;

import com.gempire.util.CruxType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class Crux {
    public Block block;
    public float weight;
    public CruxType type;
    public float temperatureMin = -1f;
    public float temperatureMax = 2f;

    public Crux(BlockState block, float weight, CruxType type){
        this.block = block.getBlock();
        this.weight = weight;
        this.type = type;
    }

    public Crux(BlockState block, float weight, CruxType type, float temperatureMin, float temperatureMax){
        this.block = block.getBlock();
        this.weight = weight;
        this.type = type;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public Crux(Block block, float weight, CruxType type){
        this.block = block;
        this.weight = weight;
        this.type = type;
    }

    public Crux(Block block, float weight, CruxType type, float temperatureMin, float temperatureMax){
        this.block = block;
        this.weight = weight;
        this.type = type;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }
}
