package com.gempire.items;

import net.minecraft.world.item.Item;

public class ItemShard extends Item {
    public int color;

    public ItemShard(Properties properties, int color) {
        super(properties);
        this.color = color;
    }
}
