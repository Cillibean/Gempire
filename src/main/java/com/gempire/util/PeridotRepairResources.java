package com.gempire.util;

import com.gempire.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashMap;

public class PeridotRepairResources {
    public static ArrayList<Item> list = new ArrayList<>();

    public static HashMap<Item, Integer> map = new HashMap<>();

    public static void register() {
        list.clear();
        map.clear();
        list.add(ModItems.SELENITE_CHUNK.get());
        list.add(ModItems.TUNGSTEN_INGOT.get());
        list.add(ModItems.TUNGSTEN_BLOCK.get());
        list.add(Items.IRON_INGOT);
        map.put(ModItems.SELENITE_CHUNK.get(), 16);
        map.put(ModItems.TUNGSTEN_INGOT.get(), 32);
        map.put(ModItems.TUNGSTEN_BLOCK.get(), 4);
        map.put(Items.IRON_INGOT, 32);
    }
}
