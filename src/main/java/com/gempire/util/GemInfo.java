package com.gempire.util;

public class GemInfo {
    public int[] resources;
    public float temp;
    public String name;

    public GemInfo(int[] resources, float temp, String name) {
        this.resources = resources;
        this.temp = temp;
        this.name = name;
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
}
