package com.gempire.items;

import net.minecraft.world.item.Item;

import net.minecraft.world.item.Item.Properties;

public class ItemChroma extends Item {
    public int color;

    public ItemChroma(Properties properties, int color) {
        super(properties);
        this.color = color;
    }
}
