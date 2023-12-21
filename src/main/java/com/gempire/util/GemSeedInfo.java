package com.gempire.util;

public class GemSeedInfo {
    public int[] resources;
    public float quality;
    public int chroma;

    public GemSeedInfo(int[] resources, float quality, int chroma) {
        this.resources = resources;
        this.quality = quality;
        this.chroma = chroma;
    }


    public float getQuality() {
        return quality;
    }

    public int[] getResources() {
        return resources;
    }

    public int getChroma() {
        return chroma;
    }

    public void setQuality(float value) {
        quality = value;
    }

    public void setResources(int[] value) {
        resources = value;
    }

    public void setChroma(int value) {
        chroma = value;
    }
}


