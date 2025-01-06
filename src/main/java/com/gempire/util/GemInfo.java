package com.gempire.util;

import net.minecraft.world.item.Item;

public class GemInfo {
    public int[] resources;
    public float temp;
    public String name;
    public Item primer;

    public GemInfo(int[] resources, float temp, String name, Item primer) {
        this.resources = resources;
        this.temp = temp;
        this.name = name;
        this.primer = primer;
    }

    public int[] getResources() {
        return resources;
    }

    public float getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }

    public Item getPrimer() {
        return primer;
    }
}
