package com.gempire.items;

import net.minecraft.world.item.Item;

public class ItemGemBase extends Item {
    public ItemGemBase(Properties p) {
        super(p.fireResistant().stacksTo(1));
    }
}
