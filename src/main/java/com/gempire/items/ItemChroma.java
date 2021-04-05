package com.gempire.items;

import net.minecraft.item.Item;

public class ItemChroma extends Item {
    public int color;

    public ItemChroma(Properties properties, int color) {
        super(properties);
        this.color = color;
    }
}
