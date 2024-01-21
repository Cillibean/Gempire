package com.gempire.util;

import net.minecraft.world.level.block.Block;

public class CruxInfo {
    public int[] resources;
    public Block block;
    public String name;

    public CruxInfo(int[] resources, Block block, String name) {
        this.resources = resources;
        this.block = block;
        this.name = name;
    }

    public int[] getResources() {
        return resources;
    }

    public Block getBlock() {
        return block;
    }

    public String getName() {
        return name;
    }
}
