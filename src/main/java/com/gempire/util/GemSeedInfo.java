package com.gempire.util;

import net.minecraft.world.item.Item;

public class GemSeedInfo {
    public int[] resources;
    public float temp;
    public int chroma;

    public GemSeedInfo(int[] resources, float temp, int chroma) {
        this.resources = resources;
        this.temp = temp;
        this.chroma = chroma;
    }

    public int[] getResources() {
        return resources;
    }

    public int getChroma() {
        return chroma;
    }
    public float getTemp() {
        return temp;
    }

    public void setResources(int[] value) {
        resources = value;
    }

    public void setChroma(int value) {
        chroma = value;
    }

    public void setTemp(float value) {
        temp = value;
    }
}


