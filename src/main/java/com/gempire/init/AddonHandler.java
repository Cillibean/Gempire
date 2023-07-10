package com.gempire.init;

import com.gempire.container.InjectorContainer;
import com.gempire.systems.injection.GemConditions;
import com.gempire.systems.injection.GemFormation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddonHandler {
    public static HashMap<String, Class> ADDON_ENTITY_REGISTRIES = new HashMap<>();
    public static HashMap<String, Class> ENTITY_ADDON_ENTITY_REGISTRIES = new HashMap<>();
    public static ArrayList<String> VANILLA_GEMS = new ArrayList<>();

    public void createInjectableAddonGem(String name, String modid, Class entityClass, Item gemitem, GemConditions conditions, Item primer, boolean tier1, boolean tier2, boolean nethert1, boolean nethert2, boolean endt1, boolean endt2) {
        ModEntities.CRUXTOGEM.put(name, conditions);
        if (tier1) GemFormation.POSSIBLE_GEMS_TIER_1.add(name);
        if (tier2) GemFormation.POSSIBLE_GEMS_TIER_2.add(name);
        if (nethert1) GemFormation.NETHER_POSSIBLE_GEMS_TIER_1.add(name);
        if (nethert2) GemFormation.NETHER_POSSIBLE_GEMS_TIER_2.add(name);
        if (endt1) GemFormation.END_POSSIBLE_GEMS_TIER_1.add(name);
        if (endt2) GemFormation.END_POSSIBLE_GEMS_TIER_2.add(name);
        if (primer != null) InjectorContainer.primer.add(primer);
        ModItemProperties.makeGem(gemitem);
        ENTITY_ADDON_ENTITY_REGISTRIES.put(name, entityClass);
        ADDON_ENTITY_REGISTRIES.put(modid, entityClass);
    }

    public void createAddonGem(String name, String modid, Class entityClass, Item gemitem) {
        ModItemProperties.makeGem(gemitem);
        ENTITY_ADDON_ENTITY_REGISTRIES.put(name, entityClass);
        ADDON_ENTITY_REGISTRIES.put(modid, entityClass);
    }
}
