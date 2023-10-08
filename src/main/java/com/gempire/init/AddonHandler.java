package com.gempire.init;

import com.gempire.container.InjectorContainer;
import com.gempire.systems.injection.GemConditions;
import com.gempire.systems.injection.GemFormation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddonHandler {
    public static HashMap<String, Class> ENTITY_ADDON_ENTITY_REGISTRIES = new HashMap<>();
    public static HashMap<String, Class> ENTITY_ADDON_ITEM_REGISTRIES = new HashMap<>();
    public static ArrayList<String> VANILLA_GEMS = new ArrayList<>();

    public static HashMap<String, Boolean> colour = new HashMap<>();
    public static HashMap<String, Boolean> addon = new HashMap<>();
    public static HashMap<String, Integer> time = new HashMap<>();
    public static HashMap<String, ArrayList<Integer>> essenceRequired = new HashMap<>();

    public static HashMap<String, HashMap<Item, Integer>> blocks = new HashMap<>();
    public static ArrayList<Item> blockList = new ArrayList<>();

    public void createIncubatedAddonGem(String name, Item gemItem, Class entityClass, Class itemClass, boolean colour1, int time1, ArrayList<Integer> essence, HashMap<Item, Integer> cruxMap) {
        ENTITY_ADDON_ITEM_REGISTRIES.put(name, itemClass);
        ENTITY_ADDON_ENTITY_REGISTRIES.put(name, entityClass);
        ModItemProperties.makeGem(gemItem);
        addon.put(name, true);
        colour.put(name, colour1);
        time.put(name, time1);
        essenceRequired.put(name, essence);
        blocks.put(name, cruxMap);
        blockList.addAll(cruxMap.keySet());
    }

    public HashMap<Item, Integer> createCruxMap(Item item1, Item item2, Item item3, Item item4, int i, int i2, int i3, int i4) {
        HashMap<Item, Integer> map = new HashMap<>();
        map.put(item1, i);
        map.put(item2, i2);
        map.put(item3, i3);
        map.put(item4, i4);
        return map;
    }

    public HashMap<Item, Integer> createCruxMap(Item item1, Item item2, Item item3, int i, int i2, int i3) {
        HashMap<Item, Integer> map = new HashMap<>();
        map.put(item1, i);
        map.put(item2, i2);
        map.put(item3, i3);
        return map;
    }

    public HashMap<Item, Integer> createCruxMap(Item item1, Item item2, int i, int i2) {
        HashMap<Item, Integer> map = new HashMap<>();
        map.put(item1, i);
        map.put(item2, i2);
        return map;
    }


    /*public void createInjectableAddonGem(String name, String modid, Class entityClass, Item gemitem, GemConditions conditions, Item primer, boolean tier1, boolean tier2, boolean nethert1, boolean nethert2, boolean endt1, boolean endt2) {
        ModEntities.CRUXTOGEM.put(name, conditions);
        if (tier1) GemFormation.POSSIBLE_GEMS_TIER_1.add(name);
        if (tier2) GemFormation.POSSIBLE_GEMS_TIER_2.add(name);
        if (nethert1) GemFormation.NETHER_POSSIBLE_GEMS_TIER_1.add(name);
        if (nethert2) GemFormation.NETHER_POSSIBLE_GEMS_TIER_2.add(name);
        if (endt1) GemFormation.END_POSSIBLE_GEMS_TIER_1.add(name);
        if (endt2) GemFormation.END_POSSIBLE_GEMS_TIER_2.add(name);
        if (primer != null) InjectorContainer.primer.add(primer);

        HashMap<String, String> map = new HashMap<>();
        map.put(name, modid);
        //ENTITY_ADDON_ENTITY_REGISTRIES.put(map, entityClass);
        ENTITY_ADDON_ENTITY_REGISTRIES.put(name, entityClass);
    }

    public void createAddonGem(String name, String modid, Class entityClass, Item gemitem) {
        ModItemProperties.makeGem(gemitem);
        HashMap<String, String> map = new HashMap<>();
        map.put(name, modid);
        //ENTITY_ADDON_ENTITY_REGISTRIES.put(map, entityClass);
        ENTITY_ADDON_ENTITY_REGISTRIES.put(name, entityClass);
    }*/
}
