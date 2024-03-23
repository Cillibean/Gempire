package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.items.*;
import com.gempire.items.tools.*;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Gempire.MODID);

    /*public static final RegistryObject<Item> WARP_SEED = ITEMS.register("warp_seed", () ->
            new ItemWarpSeed(new Item.Properties()REJUVENATOR.stacksTo(1)));

    public static final RegistryObject<Item> WARP_CORE = ITEMS.register("warp_core", () ->
            new BlockItem(
                    ModBlocks.WARP_CORE.get(),
                    new Item.Properties().tab(ModItemGroup.BLOCKS)
            )
    );

    public static final RegistryObject<Item> WARP_PAD = ITEMS.register("warp_pad", () ->
            new BlockItem(
                    ModBlocks.WARP_PAD.get(),
                    new Item.Properties().tab(ModItemGroup.BLOCKS)
            )

    );
/*
    public static final RegistryObject<Item> CRYSTAL_CHEST = ITEMS.register("crystal_chest", () ->
            new BlockItem(
                    ModBlocks.CRYSTAL_CHEST.get(),
                    new Item.Properties().tab(ModItemGroup.BLOCKS)
            )
    );*/

    public static final RegistryObject<Item> RED_LATTICE = ITEMS.register("red_lattice", () ->
            new BlockItem(ModBlocks.RED_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> ORANGE_LATTICE = ITEMS.register("orange_lattice", () ->
            new BlockItem(ModBlocks.ORANGE_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> YELLOW_LATTICE = ITEMS.register("yellow_lattice", () ->
            new BlockItem(ModBlocks.YELLOW_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIME_LATTICE = ITEMS.register("lime_lattice", () ->
            new BlockItem(ModBlocks.LIME_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> GREEN_LATTICE = ITEMS.register("green_lattice", () ->
            new BlockItem(ModBlocks.GREEN_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> CYAN_LATTICE = ITEMS.register("cyan_lattice", () ->
            new BlockItem(ModBlocks.CYAN_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_BLUE_LATTICE = ITEMS.register("light_blue_lattice", () ->
            new BlockItem(ModBlocks.LIGHT_BLUE_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLUE_LATTICE = ITEMS.register("blue_lattice", () ->
            new BlockItem(ModBlocks.BLUE_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> PURPLE_LATTICE = ITEMS.register("purple_lattice", () ->
            new BlockItem(ModBlocks.PURPLE_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> MAGENTA_LATTICE = ITEMS.register("magenta_lattice", () ->
            new BlockItem(ModBlocks.MAGENTA_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> PINK_LATTICE = ITEMS.register("pink_lattice", () ->
            new BlockItem(ModBlocks.PINK_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> WHITE_LATTICE = ITEMS.register("white_lattice", () ->
            new BlockItem(ModBlocks.WHITE_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_GRAY_LATTICE = ITEMS.register("light_gray_lattice", () ->
            new BlockItem(ModBlocks.LIGHT_GRAY_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> GRAY_LATTICE = ITEMS.register("gray_lattice", () ->
            new BlockItem(ModBlocks.GRAY_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLACK_LATTICE = ITEMS.register("black_lattice", () ->
            new BlockItem(ModBlocks.BLACK_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> BROWN_LATTICE = ITEMS.register("brown_lattice", () ->
            new BlockItem(ModBlocks.BROWN_LATTICE.get(), new Item.Properties()));

    public static final RegistryObject<Item> RED_DIAMOND_GLASS = ITEMS.register("red_diamond_glass", () ->
            new BlockItem(ModBlocks.RED_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> ORANGE_DIAMOND_GLASS = ITEMS.register("orange_diamond_glass", () ->
            new BlockItem(ModBlocks.ORANGE_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> YELLOW_DIAMOND_GLASS = ITEMS.register("yellow_diamond_glass", () ->
            new BlockItem(ModBlocks.YELLOW_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIME_DIAMOND_GLASS = ITEMS.register("lime_diamond_glass", () ->
            new BlockItem(ModBlocks.LIME_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> GREEN_DIAMOND_GLASS = ITEMS.register("green_diamond_glass", () ->
            new BlockItem(ModBlocks.GREEN_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> CYAN_DIAMOND_GLASS = ITEMS.register("cyan_diamond_glass", () ->
            new BlockItem(ModBlocks.CYAN_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_BLUE_DIAMOND_GLASS = ITEMS.register("light_blue_diamond_glass", () ->
            new BlockItem(ModBlocks.LIGHT_BLUE_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLUE_DIAMOND_GLASS = ITEMS.register("blue_diamond_glass", () ->
            new BlockItem(ModBlocks.BLUE_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> PURPLE_DIAMOND_GLASS = ITEMS.register("purple_diamond_glass", () ->
            new BlockItem(ModBlocks.PURPLE_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> MAGENTA_DIAMOND_GLASS = ITEMS.register("magenta_diamond_glass", () ->
            new BlockItem(ModBlocks.MAGENTA_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> PINK_DIAMOND_GLASS = ITEMS.register("pink_diamond_glass", () ->
            new BlockItem(ModBlocks.PINK_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> WHITE_DIAMOND_GLASS = ITEMS.register("white_diamond_glass", () ->
            new BlockItem(ModBlocks.WHITE_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_GRAY_DIAMOND_GLASS = ITEMS.register("light_gray_diamond_glass", () ->
            new BlockItem(ModBlocks.LIGHT_GRAY_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> GRAY_DIAMOND_GLASS = ITEMS.register("gray_diamond_glass", () ->
            new BlockItem(ModBlocks.GRAY_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLACK_DIAMOND_GLASS = ITEMS.register("black_diamond_glass", () ->
            new BlockItem(ModBlocks.BLACK_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> BROWN_DIAMOND_GLASS = ITEMS.register("brown_diamond_glass", () ->
            new BlockItem(ModBlocks.BROWN_DIAMOND_GLASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> PRIMED_ICE = ITEMS.register("primed_ice", () ->
            new BlockItem(
                    ModBlocks.PRIMED_ICE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PRIMED_PACKED_ICE = ITEMS.register("primed_packed_ice", () ->
            new BlockItem(
                    ModBlocks.PRIMED_PACKED_ICE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PRIMED_BLUE_ICE = ITEMS.register("primed_blue_ice", () ->
            new BlockItem(
                    ModBlocks.PRIMED_BLUE_ICE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PRIMED_DRAINED_ICE = ITEMS.register("primed_drained_ice", () ->
            new BlockItem(
                    ModBlocks.PRIMED_DRAINED_ICE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> STRAWBERRY_BLOCK = ITEMS.register("strawberry_block", () ->
            new BlockItem(
                    ModBlocks.STRAWBERRY_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CONGEALED_WHITE_ESSENCE_BLOCK = ITEMS.register("congealed_white_essence_block", () ->
            new BlockItem(
                    ModBlocks.CONGEALED_WHITE_ESSENCE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CONGEALED_PINK_ESSENCE_BLOCK = ITEMS.register("congealed_pink_essence_block", () ->
            new BlockItem(
                    ModBlocks.CONGEALED_PINK_ESSENCE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CONGEALED_BLUE_ESSENCE_BLOCK = ITEMS.register("congealed_blue_essence_block", () ->
            new BlockItem(
                    ModBlocks.CONGEALED_BLUE_ESSENCE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CONGEALED_YELLOW_ESSENCE_BLOCK = ITEMS.register("congealed_yellow_essence_block", () ->
            new BlockItem(
                    ModBlocks.CONGEALED_YELLOW_ESSENCE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PURIFIED_PRISMATIC_GLASS = ITEMS.register("purified_prismatic_glass.json", () ->
            new BlockItem(
                    ModBlocks.PURIFIED_PRISMATIC_GLASS.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PYRITE_BLOCK = ITEMS.register("pyrite_block", () ->
            new BlockItem(
                    ModBlocks.PYRITE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> GEODE_CRYSTAL_BLOCK = ITEMS.register("geode_crystal_block", () ->
            new BlockItem(
                    ModBlocks.GEODE_CRYSTAL_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> TUNGSTEN_BLOCK = ITEMS.register("tungsten_block", () ->
            new BlockItem(
                    ModBlocks.TUNGSTEN_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> RAW_TUNGSTEN_BLOCK = ITEMS.register("raw_tungsten_block", () ->
            new BlockItem(
                    ModBlocks.RAW_TUNGSTEN_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> TUNGSTEN_ORE = ITEMS.register("tungsten_ore", () ->
            new BlockItem(
                    ModBlocks.TUNGSTEN_ORE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DEEPSLATE_TUNGSTEN_ORE = ITEMS.register("deepslate_tungsten_ore", () ->
            new BlockItem(
                    ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> THULITE_BLOCK = ITEMS.register("thulite_block", () ->
            new BlockItem(
                    ModBlocks.THULITE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> RAW_THULITE_BLOCK = ITEMS.register("raw_thulite_block", () ->
            new BlockItem(
                    ModBlocks.RAW_THULITE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> THULITE_ORE = ITEMS.register("thulite_ore", () ->
            new BlockItem(
                    ModBlocks.THULITE_ORE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> ELECTRUM_BLOCK = ITEMS.register("electrum_block", () ->
            new BlockItem(
                    ModBlocks.ELECTRUM_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> RAW_ELECTRUM_BLOCK = ITEMS.register("raw_electrum_block", () ->
            new BlockItem(
                    ModBlocks.RAW_ELECTRUM_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> ELECTRUM_ORE = ITEMS.register("electrum_ore", () ->
            new BlockItem(
                    ModBlocks.ELECTRUM_ORE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> ANATASE_BLOCK = ITEMS.register("anatase_block", () ->
            new BlockItem(
                    ModBlocks.ANATASE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> RAW_ANATASE_BLOCK = ITEMS.register("raw_anatase_block", () ->
            new BlockItem(
                    ModBlocks.RAW_ANATASE_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> ANATASE_ORE = ITEMS.register("anatase_ore", () ->
            new BlockItem(
                    ModBlocks.ANATASE_ORE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PLATINUM_BLOCK = ITEMS.register("platinum_block", () ->
            new BlockItem(
                    ModBlocks.PLATINUM_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> RAW_PLATINUM_BLOCK = ITEMS.register("raw_platinum_block", () ->
            new BlockItem(
                    ModBlocks.RAW_PLATINUM_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> PLATINUM_ORE = ITEMS.register("platinum_ore", () ->
            new BlockItem(
                    ModBlocks.PLATINUM_ORE.get(),
                    new Item.Properties()
            )
    );


    public static final RegistryObject<Item> PALADIN_HELMET = ITEMS.register("paladin_helmet",
            () -> new PaladinArmorItem(ModArmorMaterials.PALADIN, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PALADIN_CHESTPLATE = ITEMS.register("paladin_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PALADIN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PALADIN_LEGGINGS = ITEMS.register("paladin_leggings",
            () -> new ArmorItem(ModArmorMaterials.PALADIN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PALADIN_BOOTS = ITEMS.register("paladin_boots",
            () -> new ArmorItem(ModArmorMaterials.PALADIN, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> GUARDIAN_HELMET = ITEMS.register("guardian_helmet",
            () -> new GuardianArmorItem(ModArmorMaterials.GUARDIAN, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> GUARDIAN_CHESTPLATE = ITEMS.register("guardian_chestplate",
            () -> new GuardianArmorItem(ModArmorMaterials.GUARDIAN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> GUARDIAN_LEGGINGS = ITEMS.register("guardian_leggings",
            () -> new GuardianArmorItem(ModArmorMaterials.GUARDIAN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> GUARDIAN_BOOTS = ITEMS.register("guardian_boots",
            () -> new GuardianArmorItem(ModArmorMaterials.GUARDIAN, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> HUNTRESS_HELMET = ITEMS.register("huntress_helmet",
            () -> new HuntressArmorItem(ModArmorMaterials.HUNTRESS, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> HUNTRESS_CHESTPLATE = ITEMS.register("huntress_chestplate",
            () -> new ArmorItem(ModArmorMaterials.HUNTRESS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> HUNTRESS_LEGGINGS = ITEMS.register("huntress_leggings",
            () -> new ArmorItem(ModArmorMaterials.HUNTRESS, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> HUNTRESS_BOOTS = ITEMS.register("huntress_boots",
            () -> new ArmorItem(ModArmorMaterials.HUNTRESS, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> EMPRESS_HELMET = ITEMS.register("empress_helmet",
            () -> new EmpressArmorItem(ModArmorMaterials.EMPRESS, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> EMPRESS_CHESTPLATE = ITEMS.register("empress_chestplate",
            () -> new ArmorItem(ModArmorMaterials.EMPRESS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> EMPRESS_LEGGINGS = ITEMS.register("empress_leggings",
            () -> new ArmorItem(ModArmorMaterials.EMPRESS, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> EMPRESS_BOOTS = ITEMS.register("empress_boots",
            () -> new ArmorItem(ModArmorMaterials.EMPRESS, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> THULITE_HELMET = ITEMS.register("thulite_helmet",
            () -> new ThuliteArmorItem(ModArmorMaterials.THULITE, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> THULITE_CHESTPLATE = ITEMS.register("thulite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.THULITE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> THULITE_LEGGINGS = ITEMS.register("thulite_leggings",
            () -> new ArmorItem(ModArmorMaterials.THULITE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> THULITE_BOOTS = ITEMS.register("thulite_boots",
            () -> new ArmorItem(ModArmorMaterials.THULITE, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ANATASE_HELMET = ITEMS.register("anatase_helmet",
            () -> new AnataseArmorItem(ModArmorMaterials.ANATASE, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ANATASE_CHESTPLATE = ITEMS.register("anatase_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ANATASE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ANATASE_LEGGINGS = ITEMS.register("anatase_leggings",
            () -> new ArmorItem(ModArmorMaterials.ANATASE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ANATASE_BOOTS = ITEMS.register("anatase_boots",
            () -> new ArmorItem(ModArmorMaterials.ANATASE, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ELECTRUM_HELMET = ITEMS.register("electrum_helmet",
            () -> new ElectrumArmorItem(ModArmorMaterials.ELECTRUM, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ELECTRUM_CHESTPLATE = ITEMS.register("electrum_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ELECTRUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ELECTRUM_LEGGINGS = ITEMS.register("electrum_leggings",
            () -> new ArmorItem(ModArmorMaterials.ELECTRUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> ELECTRUM_BOOTS = ITEMS.register("electrum_boots",
            () -> new ArmorItem(ModArmorMaterials.ELECTRUM, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PLATINUM_HELMET = ITEMS.register("platinum_helmet",
            () -> new PlatinumArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = ITEMS.register("platinum_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PLATINUM_LEGGINGS = ITEMS.register("platinum_leggings",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PLATINUM_BOOTS = ITEMS.register("platinum_boots",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));



    public static final RegistryObject<Item> HUNTRESS_SWORD = ITEMS.register("huntress_sword",
            () -> new ItemHuntressSword(ModTiers.SHARD, 4, -2.4f,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PALADIN_AXE = ITEMS.register("paladin_axe",
            () -> new ItemPaladinAxe(ModTiers.SHARD, 6, -3f,
                    new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> GUARDIAN_SHIELD = ITEMS.register("guardian_shield",
            () -> new ItemGuardianShield(new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> EMPRESS_BOW = ITEMS.register("empress_bow",
            () -> new ItemEmpressBow(new Item.Properties().fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> PRISMATIC_SWORD = ITEMS.register("prismatic_sword",
            () -> new SwordItem(ModTiers.PRISMATIC, 4, -2.4f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PRISMATIC_PICKAXE = ITEMS.register("prismatic_pickaxe",
            () -> new PickaxeItem(ModTiers.PRISMATIC, 1, -2.8f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PRISMATIC_SHOVEL = ITEMS.register("prismatic_shovel",
            () -> new ShovelItem(ModTiers.PRISMATIC, 2, -3f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PRISMATIC_AXE = ITEMS.register("prismatic_axe",
            () -> new AxeItem(ModTiers.PRISMATIC, 6, -3f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PRISMATIC_HOE = ITEMS.register("prismatic_hoe",
            () -> new HoeItem(ModTiers.PRISMATIC, -4, 0f,
                    new Item.Properties().fireResistant()));


    /*
    public static final RegistryObject<Item> INACTIVE_PALADIN_GEM = ITEMS.register("inactive_paladin_gem", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> INACTIVE_GUARDIAN_GEM = ITEMS.register("inactive_guardian_gem", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> INACTIVE_HUNTRESS_GEM = ITEMS.register("inactive_huntress_gem", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> INACTIVE_EMPRESS_GEM = ITEMS.register("inactive_empress_gem", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );*/

    public static final RegistryObject<Item> PALADIN_FLOWER = ITEMS.register("paladin_flower", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> GUARDIAN_TEAR = ITEMS.register("guardian_tear", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> HUNTRESS_DAGGER = ITEMS.register("huntress_dagger", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> EMPRESS_STAR = ITEMS.register("empress_star", () ->
            new ItemBossGem(new Item.Properties().stacksTo(1))
    );
/*
    public static final RegistryObject<Item> IRIDESCENT_FLOWER = ITEMS.register("iridescent_flower", () ->
            new ItemAltarUpgrade(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> MIRRORED_TEAR = ITEMS.register("mirrored_tear", () ->
            new ItemAltarUpgrade(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> GILDED_DAGGER = ITEMS.register("gilded_dagger", () ->
            new ItemAltarUpgrade(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> PRISMATIC_STAR = ITEMS.register("prismatic_star", () ->
            new ItemAltarUpgrade(new Item.Properties().stacksTo(1))
    );
*/
    public static final RegistryObject<Item> PRISMATIC_GLASS = ITEMS.register("prismatic_glass", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PRISMATIC_FLASK = ITEMS.register("prismatic_flask", () ->
            new Item(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> PRISMATIC_SHEARS = ITEMS.register("prismatic_shears", () ->
            new Item(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> SHOCK_BERRY = ITEMS.register("shock_berry", () ->
            new ItemNameBlockItem(ModBlocks.SHOCK_BERRY_BUSH.get(), new Item.Properties().stacksTo(64).food(ModFoods.SHOCK_BERRY))
    );

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () ->
            new ItemNameBlockItem(ModBlocks.STRAWBERRY_STEM.get(), new Item.Properties().stacksTo(64).food(ModFoods.STRAWBERRY))
    );

    /*
    public static final RegistryObject<Item> FUSION_STICK = ITEMS.register("fusion_stick", () ->
            new ItemFusionStick(new Item.Properties().stacksTo(1))
    );*/

    public static final RegistryObject<Item> SLUDGE_GLOB = ITEMS.register("sludge_glob", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> THULITE_NUGGET = ITEMS.register("thulite_nugget", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> THULITE_INGOT = ITEMS.register("thulite_ingot", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> RAW_THULITE = ITEMS.register("raw_thulite", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ANATASE_NUGGET = ITEMS.register("anatase_nugget", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ANATASE_INGOT = ITEMS.register("anatase_ingot", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> RAW_ANATASE = ITEMS.register("raw_anatase", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ELECTRUM_NUGGET = ITEMS.register("electrum_nugget", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> RAW_ELECTRUM = ITEMS.register("raw_electrum", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PRISMATIC_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("prismatic_upgrade_smithing_template", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GEM_ALLOY = ITEMS.register("gem_alloy", () ->
            new Item(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CRYSTAL_LOG = ITEMS.register("crystal_log", () ->
            new BlockItem(
                    ModBlocks.CRYSTAL_LOG.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> STRIPPED_CRYSTAL_LOG = ITEMS.register("stripped_crystal_log", () ->
            new BlockItem(
                    ModBlocks.STRIPPED_CRYSTAL_LOG.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CRYSTAL_WOOD = ITEMS.register("crystal_wood", () ->
            new BlockItem(
                    ModBlocks.CRYSTAL_WOOD.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> STRIPPED_CRYSTAL_WOOD = ITEMS.register("stripped_crystal_wood", () ->
            new BlockItem(
                    ModBlocks.STRIPPED_CRYSTAL_WOOD.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CRYSTAL_LEAVES = ITEMS.register("crystal_leaves", () ->
            new BlockItem(
                    ModBlocks.CRYSTAL_LEAVES.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> CRYSTAL_SAPLING = ITEMS.register("crystal_sapling", () ->
            new BlockItem(
                    ModBlocks.CRYSTAL_SAPLING.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> BLUE_CRYSTAL_APPLE = ITEMS.register("blue_crystal_apple", () ->
            new Item(new Item.Properties().food(ModFoods.CRYSTAL_APPLE))
    );

    public static final RegistryObject<Item> PURPLE_CRYSTAL_APPLE = ITEMS.register("purple_crystal_apple", () ->
            new Item(new Item.Properties().food(ModFoods.CRYSTAL_APPLE))
    );

    public static final RegistryObject<Item> PINK_CRYSTAL_APPLE = ITEMS.register("pink_crystal_apple", () ->
            new Item(new Item.Properties().food(ModFoods.CRYSTAL_APPLE))
    );

    public static final RegistryObject<Item> DISTANT_LOG = ITEMS.register("distant_log", () ->
            new BlockItem(
                    ModBlocks.DISTANT_LOG.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> STRIPPED_DISTANT_LOG = ITEMS.register("stripped_distant_log", () ->
            new BlockItem(
                    ModBlocks.STRIPPED_DISTANT_LOG.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DISTANT_WOOD = ITEMS.register("distant_wood", () ->
            new BlockItem(
                    ModBlocks.DISTANT_WOOD.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> STRIPPED_DISTANT_WOOD = ITEMS.register("stripped_distant_wood", () ->
            new BlockItem(
                    ModBlocks.STRIPPED_DISTANT_WOOD.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DISTANT_LEAVES = ITEMS.register("distant_leaves", () ->
            new BlockItem(
                    ModBlocks.DISTANT_LEAVES.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DISTANT_SAPLING = ITEMS.register("distant_sapling", () ->
            new BlockItem(
                    ModBlocks.DISTANT_SAPLING.get(),
                    new Item.Properties()
            )
    );


    public static final RegistryObject<Item> PINK_ESSENCE_BUCKET = ITEMS.register("pink_essence_bucket", () ->
            new BucketItem(
                    ModFluids.PINK_ESSENCE.get(),
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLUE_ESSENCE_BUCKET = ITEMS.register("blue_essence_bucket", () ->
            new BucketItem(
                    ModFluids.BLUE_ESSENCE.get(),
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> YELLOW_ESSENCE_BUCKET = ITEMS.register("yellow_essence_bucket", () ->
            new BucketItem(
                    ModFluids.YELLOW_ESSENCE.get(),
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHITE_ESSENCE_BUCKET = ITEMS.register("white_essence_bucket", () ->
            new BucketItem(
                    ModFluids.WHITE_ESSENCE.get(),
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PEDISTAL = ITEMS.register("pedistal", () ->
            new BlockItem(
                    ModBlocks.PEDISTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> WHITE_CHROMA_CRYSTAL = ITEMS.register("white_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.WHITE_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> ORANGE_CHROMA_CRYSTAL = ITEMS.register("orange_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.ORANGE_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> MAGENTA_CHROMA_CRYSTAL = ITEMS.register("magenta_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> LIGHT_BLUE_CHROMA_CRYSTAL = ITEMS.register("light_blue_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> YELLOW_CHROMA_CRYSTAL = ITEMS.register("yellow_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.YELLOW_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> LIME_CHROMA_CRYSTAL = ITEMS.register("lime_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.LIME_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> PINK_CHROMA_CRYSTAL = ITEMS.register("pink_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.PINK_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> GRAY_CHROMA_CRYSTAL = ITEMS.register("gray_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.GRAY_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> LIGHT_GRAY_CHROMA_CRYSTAL = ITEMS.register("light_gray_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CYAN_CHROMA_CRYSTAL = ITEMS.register("cyan_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.CYAN_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> PURPLE_CHROMA_CRYSTAL = ITEMS.register("purple_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.PURPLE_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> BLUE_CHROMA_CRYSTAL = ITEMS.register("blue_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.BLUE_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> BROWN_CHROMA_CRYSTAL = ITEMS.register("brown_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.BROWN_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> GREEN_CHROMA_CRYSTAL = ITEMS.register("green_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.GREEN_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> RED_CHROMA_CRYSTAL = ITEMS.register("red_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.RED_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> BLACK_CHROMA_CRYSTAL = ITEMS.register("black_chroma_crystal", () ->
            new BlockItem(
                    ModBlocks.BLACK_CHROMA_CRYSTAL.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> ICE_SPIKE = ITEMS.register("ice_spike", () ->
            new BlockItem(
                    ModBlocks.ICE_SPIKE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_BLUE_SOIL = ITEMS.register("drained_blue_soil", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_SOIL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_SAND = ITEMS.register("drained_blue_sand", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_SAND.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE = ITEMS.register("drained_blue_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_2 = ITEMS.register("drained_blue_stone_2", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_2.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BANDED_BLUE_STONE = ITEMS.register("drained_blue_stone_bands", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BANDED_BLUE_STONE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_GREY_SOIL = ITEMS.register("drained_grey_soil", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_SOIL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_SAND = ITEMS.register("drained_grey_sand", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_SAND.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE = ITEMS.register("drained_grey_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_2 = ITEMS.register("drained_grey_stone_2", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_2.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BANDED_GREY_STONE = ITEMS.register("drained_grey_stone_bands", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BANDED_GREY_STONE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_PURPLE_SOIL = ITEMS.register("drained_purple_soil", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_SOIL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_SAND = ITEMS.register("drained_purple_sand", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_SAND.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE = ITEMS.register("drained_purple_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_2 = ITEMS.register("drained_purple_stone_2", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_2.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BANDED_PURPLE_STONE = ITEMS.register("drained_purple_stone_bands", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BANDED_PURPLE_STONE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_RED_SAND = ITEMS.register("drained_red_sand", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_SAND.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_SOIL = ITEMS.register("drained_red_soil", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_SOIL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE = ITEMS.register("drained_red_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_2 = ITEMS.register("drained_red_stone_2", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_2.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BANDED_RED_STONE = ITEMS.register("drained_red_stone_bands", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BANDED_RED_STONE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_SAND = ITEMS.register("drained_sand", () ->
            new BlockItem(
                    ModBlocks.DRAINED_SAND.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_SOIL = ITEMS.register("drained_yellow_soil", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_SOIL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE = ITEMS.register("drained_yellow_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_2 = ITEMS.register("drained_yellow_stone_2", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_2.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BANDED_YELLOW_STONE = ITEMS.register("drained_yellow_stone_bands", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BANDED_YELLOW_STONE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_LOG = ITEMS.register("drained_log", () ->
            new BlockItem(
                    ModBlocks.DRAINED_LOG.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_LOG_CRACKED = ITEMS.register("drained_log_cracked", () ->
            new BlockItem(
                    ModBlocks.DRAINED_LOG_CRACKED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_ICE = ITEMS.register("drained_ice", () ->
            new BlockItem(
                    ModBlocks.DRAINED_ICE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE = ITEMS.register("drained_blue_polished_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_DARK = ITEMS.register("drained_blue_polished_stone_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_DARK_SLAB = ITEMS.register("drained_blue_polished_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_DARK_STAIRS = ITEMS.register("drained_blue_polished_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_LIGHT = ITEMS.register("drained_blue_polished_stone_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_LIGHT_SLAB = ITEMS.register("drained_blue_polished_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_LIGHT_STAIRS = ITEMS.register("drained_blue_polished_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_SLAB = ITEMS.register("drained_blue_polished_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_POLISHED_STONE_STAIRS = ITEMS.register("drained_blue_polished_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_POLISHED_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_DARK_SLAB = ITEMS.register("drained_blue_stone_brick_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_DARK_STAIRS = ITEMS.register("drained_blue_stone_brick_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_DARK_WALL = ITEMS.register("drained_blue_stone_brick_dark_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_LIGHT_SLAB = ITEMS.register("drained_blue_stone_brick_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_LIGHT_STAIRS = ITEMS.register("drained_blue_stone_brick_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_LIGHT_WALL = ITEMS.register("drained_blue_stone_brick_light_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_SLAB = ITEMS.register("drained_blue_stone_brick_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_STAIRS = ITEMS.register("drained_blue_stone_brick_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICK_WALL = ITEMS.register("drained_blue_stone_brick_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS = ITEMS.register("drained_blue_stone_bricks", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICKS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS_LIGHT = ITEMS.register("drained_blue_stone_bricks_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICKS_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS_DARK = ITEMS.register("drained_blue_stone_bricks_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICKS_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_SLAB = ITEMS.register("drained_blue_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_DARK_SLAB = ITEMS.register("drained_blue_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_LIGHT_SLAB = ITEMS.register("drained_blue_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_STAIRS = ITEMS.register("drained_blue_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_LIGHT_STAIRS = ITEMS.register("drained_blue_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_BLUE_STONE_DARK_STAIRS = ITEMS.register("drained_blue_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS_CHISELED = ITEMS.register("drained_blue_stone_bricks_chiseled", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICKS_CHISELED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS_CRACKED = ITEMS.register("drained_blue_stone_bricks_cracked", () ->
            new BlockItem(
            ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED.get(),
            new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS_CRACKED_LIGHT = ITEMS.register("drained_blue_stone_bricks_cracked_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_STONE_BRICKS_CRACKED_DARK = ITEMS.register("drained_blue_stone_bricks_cracked_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_GLAZED_TILE = ITEMS.register("drained_blue_glazed_tile", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_GLAZED_TILE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_GLASS = ITEMS.register("drained_blue_glass", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_GLASS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_BLUE_GLASS_PANE = ITEMS.register("drained_blue_glass_pane", () ->
            new BlockItem(
                    ModBlocks.DRAINED_BLUE_GLASS_PANE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE = ITEMS.register("drained_red_polished_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_DARK = ITEMS.register("drained_red_polished_stone_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_DARK_SLAB = ITEMS.register("drained_red_polished_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_DARK_STAIRS = ITEMS.register("drained_red_polished_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_LIGHT = ITEMS.register("drained_red_polished_stone_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_LIGHT_SLAB = ITEMS.register("drained_red_polished_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_LIGHT_STAIRS = ITEMS.register("drained_red_polished_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_SLAB = ITEMS.register("drained_red_polished_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_POLISHED_STONE_STAIRS = ITEMS.register("drained_red_polished_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_POLISHED_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_DARK_SLAB = ITEMS.register("drained_red_stone_brick_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_DARK_STAIRS = ITEMS.register("drained_red_stone_brick_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_DARK_WALL = ITEMS.register("drained_red_stone_brick_dark_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_DARK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_LIGHT_SLAB = ITEMS.register("drained_red_stone_brick_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_LIGHT_STAIRS = ITEMS.register("drained_red_stone_brick_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_LIGHT_WALL = ITEMS.register("drained_red_stone_brick_light_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_SLAB = ITEMS.register("drained_red_stone_brick_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_STAIRS = ITEMS.register("drained_red_stone_brick_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICK_WALL = ITEMS.register("drained_red_stone_brick_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS = ITEMS.register("drained_red_stone_bricks", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS_LIGHT = ITEMS.register("drained_red_stone_bricks_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS_DARK = ITEMS.register("drained_red_stone_bricks_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_SLAB = ITEMS.register("drained_red_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_DARK_SLAB = ITEMS.register("drained_red_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_LIGHT_SLAB = ITEMS.register("drained_red_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_STAIRS = ITEMS.register("drained_red_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_LIGHT_STAIRS = ITEMS.register("drained_red_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_RED_STONE_DARK_STAIRS = ITEMS.register("drained_red_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS_CHISELED = ITEMS.register("drained_red_stone_bricks_chiseled", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS_CHISELED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS_CRACKED = ITEMS.register("drained_red_stone_bricks_cracked", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS_CRACKED_LIGHT = ITEMS.register("drained_red_stone_bricks_cracked_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_STONE_BRICKS_CRACKED_DARK = ITEMS.register("drained_red_stone_bricks_cracked_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_GLAZED_TILE = ITEMS.register("drained_red_glazed_tile", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_GLAZED_TILE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_GLASS = ITEMS.register("drained_red_glass", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_GLASS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_RED_GLASS_PANE = ITEMS.register("drained_red_glass_pane", () ->
            new BlockItem(
                    ModBlocks.DRAINED_RED_GLASS_PANE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE = ITEMS.register("drained_yellow_polished_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_DARK = ITEMS.register("drained_yellow_polished_stone_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_DARK_SLAB = ITEMS.register("drained_yellow_polished_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_DARK_STAIRS = ITEMS.register("drained_yellow_polished_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_LIGHT = ITEMS.register("drained_yellow_polished_stone_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_LIGHT_SLAB = ITEMS.register("drained_yellow_polished_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_LIGHT_STAIRS = ITEMS.register("drained_yellow_polished_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_SLAB = ITEMS.register("drained_yellow_polished_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_POLISHED_STONE_STAIRS = ITEMS.register("drained_yellow_polished_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_POLISHED_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_DARK_SLAB = ITEMS.register("drained_yellow_stone_brick_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_DARK_STAIRS = ITEMS.register("drained_yellow_stone_brick_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_DARK_WALL = ITEMS.register("drained_yellow_stone_brick_dark_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_LIGHT_SLAB = ITEMS.register("drained_yellow_stone_brick_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_LIGHT_STAIRS = ITEMS.register("drained_yellow_stone_brick_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_LIGHT_WALL = ITEMS.register("drained_yellow_stone_brick_light_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_SLAB = ITEMS.register("drained_yellow_stone_brick_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_STAIRS = ITEMS.register("drained_yellow_stone_brick_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICK_WALL = ITEMS.register("drained_yellow_stone_brick_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS = ITEMS.register("drained_yellow_stone_bricks", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS_LIGHT = ITEMS.register("drained_yellow_stone_bricks_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS_DARK = ITEMS.register("drained_yellow_stone_bricks_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_SLAB = ITEMS.register("drained_yellow_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_DARK_SLAB = ITEMS.register("drained_yellow_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_LIGHT_SLAB = ITEMS.register("drained_yellow_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_STAIRS = ITEMS.register("drained_yellow_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_LIGHT_STAIRS = ITEMS.register("drained_yellow_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_DARK_STAIRS = ITEMS.register("drained_yellow_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS_CHISELED = ITEMS.register("drained_yellow_stone_bricks_chiseled", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CHISELED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS_CRACKED = ITEMS.register("drained_yellow_stone_bricks_cracked", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS_CRACKED_LIGHT = ITEMS.register("drained_yellow_stone_bricks_cracked_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_STONE_BRICKS_CRACKED_DARK = ITEMS.register("drained_yellow_stone_bricks_cracked_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_GLAZED_TILE = ITEMS.register("drained_yellow_glazed_tile", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_GLAZED_TILE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_GLASS = ITEMS.register("drained_yellow_glass", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_GLASS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_YELLOW_GLASS_PANE = ITEMS.register("drained_yellow_glass_pane", () ->
            new BlockItem(
                    ModBlocks.DRAINED_YELLOW_GLASS_PANE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE = ITEMS.register("drained_purple_polished_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_DARK = ITEMS.register("drained_purple_polished_stone_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_DARK_SLAB = ITEMS.register("drained_purple_polished_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_DARK_STAIRS = ITEMS.register("drained_purple_polished_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_LIGHT = ITEMS.register("drained_purple_polished_stone_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_LIGHT_SLAB = ITEMS.register("drained_purple_polished_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_LIGHT_STAIRS = ITEMS.register("drained_purple_polished_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_SLAB = ITEMS.register("drained_purple_polished_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_POLISHED_STONE_STAIRS = ITEMS.register("drained_purple_polished_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_POLISHED_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_DARK_SLAB = ITEMS.register("drained_purple_stone_brick_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_DARK_STAIRS = ITEMS.register("drained_purple_stone_brick_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_DARK_WALL = ITEMS.register("drained_purple_stone_brick_dark_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_LIGHT_SLAB = ITEMS.register("drained_purple_stone_brick_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_LIGHT_STAIRS = ITEMS.register("drained_purple_stone_brick_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_LIGHT_WALL = ITEMS.register("drained_purple_stone_brick_light_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_SLAB = ITEMS.register("drained_purple_stone_brick_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_STAIRS = ITEMS.register("drained_purple_stone_brick_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICK_WALL = ITEMS.register("drained_purple_stone_brick_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS = ITEMS.register("drained_purple_stone_bricks", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS_LIGHT = ITEMS.register("drained_purple_stone_bricks_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS_DARK = ITEMS.register("drained_purple_stone_bricks_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_SLAB = ITEMS.register("drained_purple_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_DARK_SLAB = ITEMS.register("drained_purple_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_LIGHT_SLAB = ITEMS.register("drained_purple_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_STAIRS = ITEMS.register("drained_purple_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_LIGHT_STAIRS = ITEMS.register("drained_purple_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_DARK_STAIRS = ITEMS.register("drained_purple_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS_CHISELED = ITEMS.register("drained_purple_stone_bricks_chiseled", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CHISELED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS_CRACKED = ITEMS.register("drained_purple_stone_bricks_cracked", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS_CRACKED_LIGHT = ITEMS.register("drained_purple_stone_bricks_cracked_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_STONE_BRICKS_CRACKED_DARK = ITEMS.register("drained_purple_stone_bricks_cracked_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_GLAZED_TILE = ITEMS.register("drained_purple_glazed_tile", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_GLAZED_TILE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_GLASS = ITEMS.register("drained_purple_glass", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_GLASS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_PURPLE_GLASS_PANE = ITEMS.register("drained_purple_glass_pane", () ->
            new BlockItem(
                    ModBlocks.DRAINED_PURPLE_GLASS_PANE.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE = ITEMS.register("drained_grey_polished_stone", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_DARK = ITEMS.register("drained_grey_polished_stone_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_DARK_SLAB = ITEMS.register("drained_grey_polished_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_DARK_STAIRS = ITEMS.register("drained_grey_polished_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_LIGHT = ITEMS.register("drained_grey_polished_stone_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_LIGHT_SLAB = ITEMS.register("drained_grey_polished_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_LIGHT_STAIRS = ITEMS.register("drained_grey_polished_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_SLAB = ITEMS.register("drained_grey_polished_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_POLISHED_STONE_STAIRS = ITEMS.register("drained_grey_polished_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_POLISHED_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_DARK_SLAB = ITEMS.register("drained_grey_stone_brick_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_DARK_STAIRS = ITEMS.register("drained_grey_stone_brick_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_DARK_WALL = ITEMS.register("drained_grey_stone_brick_dark_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_LIGHT_SLAB = ITEMS.register("drained_grey_stone_brick_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_LIGHT_STAIRS = ITEMS.register("drained_grey_stone_brick_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_LIGHT_WALL = ITEMS.register("drained_grey_stone_brick_light_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_SLAB = ITEMS.register("drained_grey_stone_brick_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_STAIRS = ITEMS.register("drained_grey_stone_brick_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICK_WALL = ITEMS.register("drained_grey_stone_brick_wall", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICK_WALL.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS = ITEMS.register("drained_grey_stone_bricks", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS_LIGHT = ITEMS.register("drained_grey_stone_bricks_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS_DARK = ITEMS.register("drained_grey_stone_bricks_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_SLAB = ITEMS.register("drained_grey_stone_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_DARK_SLAB = ITEMS.register("drained_grey_stone_dark_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_DARK_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_LIGHT_SLAB = ITEMS.register("drained_grey_stone_light_slab", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_LIGHT_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_STAIRS = ITEMS.register("drained_grey_stone_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_LIGHT_STAIRS = ITEMS.register("drained_grey_stone_light_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_LIGHT_STAIRS.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> DRAINED_GREY_STONE_DARK_STAIRS = ITEMS.register("drained_grey_stone_dark_stairs", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_DARK_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS_CHISELED = ITEMS.register("drained_grey_stone_bricks_chiseled", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS_CHISELED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS_CRACKED = ITEMS.register("drained_grey_stone_bricks_cracked", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS_CRACKED_LIGHT = ITEMS.register("drained_grey_stone_bricks_cracked_light", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED_LIGHT.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_STONE_BRICKS_CRACKED_DARK = ITEMS.register("drained_grey_stone_bricks_cracked_dark", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED_DARK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_GLAZED_TILE = ITEMS.register("drained_grey_glazed_tile", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_GLAZED_TILE.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_GLASS = ITEMS.register("drained_grey_glass", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_GLASS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRAINED_GREY_GLASS_PANE = ITEMS.register("drained_grey_glass_pane", () ->
            new BlockItem(
                    ModBlocks.DRAINED_GREY_GLASS_PANE.get(),
                    new Item.Properties()
            )
    );

    /*public static final RegistryObject<Item> GEM_SEED_BLOCK_ITEM = ITEMS.register("gem_seed_block", () ->
            new BlockItem(
                    ModBlocks.GEM_SEED_BLOCK.get(),
                    new Item.Properties().group(ModItemGroup.BLOCKS)
            )
    );*/
    public static final RegistryObject<Item> POWER_CRYSTAL_BLOCK_ITEM = ITEMS.register("power_crystal_block", () ->
            new BlockItem(
                    ModBlocks.POWER_CRYSTAL_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> POWER_CRYSTAL_BLOCK_TIER_2_ITEM = ITEMS.register("power_crystal_block_tier_2", () ->
            new BlockItem(
                    ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> TANK_BLOCK_ITEM = ITEMS.register("tank_block", () ->
            new BlockItem(
                    ModBlocks.TANK_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> DRILL_BLOCK_ITEM = ITEMS.register("drill_block", () ->
            new BlockItem(
                    ModBlocks.DRILL_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> SHELL_BLOCK_ITEM = ITEMS.register("shell_block", () ->
            new BlockItem(
                    ModBlocks.SHELL_BLOCK.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> INCUBATOR_BLOCK_ITEM = ITEMS.register("incubator_block", () ->
            new BlockItem(
                    ModBlocks.INCUBATOR_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> PRISMATIC_BLOCK = ITEMS.register("prismatic_block", () ->
            new BlockItem(
                    ModBlocks.PRISMATIC_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> RUINED_MARBLE_BLOCK = ITEMS.register("ruined_marble_block", () ->
            new BlockItem(
                    ModBlocks.RUINED_MARBLE_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> RUINED_MARBLE_BRICK = ITEMS.register("ruined_marble_brick", () ->
            new BlockItem(
                    ModBlocks.RUINED_MARBLE_BRICK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> RUINED_MARBLE_PILLAR = ITEMS.register("ruined_marble_pillar", () ->
            new BlockItem(
                    ModBlocks.RUINED_MARBLE_PILLAR.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CHISELED_RUINED_MARBLE_BLOCK = ITEMS.register("chiseled_ruined_marble_block", () ->
            new BlockItem(
                    ModBlocks.CHISELED_RUINED_MARBLE_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> SMOOTH_RUINED_MARBLE_BLOCK = ITEMS.register("smooth_ruined_marble_block", () ->
            new BlockItem(
                    ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> SMOOTH_RUINED_MARBLE_SLAB = ITEMS.register("smooth_ruined_marble_slab", () ->
            new BlockItem(
                    ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> RUINED_MARBLE_SLAB = ITEMS.register("ruined_marble_slab", () ->
            new BlockItem(
                    ModBlocks.RUINED_MARBLE_SLAB.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> SMOOTH_RUINED_MARBLE_STAIRS = ITEMS.register("smooth_ruined_marble_stairs", () ->
            new BlockItem(
                    ModBlocks.SMOOTH_RUINED_MARBLE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> RUINED_MARBLE_STAIRS = ITEMS.register("ruined_marble_stairs", () ->
            new BlockItem(
                    ModBlocks.RUINED_MARBLE_STAIRS.get(),
                    new Item.Properties()
            )
    );
    /*public static final RegistryObject<Item> CRYSTALLINE_LOG = ITEMS.register("crystalline_log", () ->
            new BlockItem(
                    ModBlocks.CRYSTALLINE_LOG.get(),
                    new Item.Properties().tab(ModItemGroup.BLOCKS)
            )
    );*/

    public static final RegistryObject<Item> REMNANTS_MUSIC_DISC = ITEMS.register("remnants_music_disc", () ->
            new RecordItem(8, ModSounds.REMNANTS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5620));

    public static final RegistryObject<Item> FRAGMENTS_MUSIC_DISC = ITEMS.register("fragments_music_disc", () ->
            new RecordItem(8, ModSounds.FRAGMENTS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3560));
    public static final RegistryObject<Item> GILDED_LAPIS = ITEMS.register("gilded_lapis", () ->
            new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PRIME_BOOST = ITEMS.register("prime_boost", () ->
            new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> PRIMED_SLATE = ITEMS.register("primed_slate", () ->
            new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> SLATE = ITEMS.register("slate", () ->
            new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> SPODUMENE_PIECE = ITEMS.register("spodumene_piece", () ->
            new Item(new Item.Properties().stacksTo(64).food(ModFoods.SPODUMENE_PIECE)));
    public static final RegistryObject<Item> ICE_SHARD = ITEMS.register("ice_shard", () ->
            new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ACID_SPIT = ITEMS.register("acid_spit", () ->
            new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WATER_ORB = ITEMS.register("water_orb", () ->
            new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GEM_SLUSH_BUCKET = ITEMS.register("gem_slush_bucket", () ->
            new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CONGEALED_PINK_ESSENCE = ITEMS.register("congealed_pink_essence", () ->
            new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CONGEALED_YELLOW_ESSENCE = ITEMS.register("congealed_yellow_essence", () ->
            new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CONGEALED_BLUE_ESSENCE = ITEMS.register("congealed_blue_essence", () ->
            new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CONGEALED_WHITE_ESSENCE = ITEMS.register("congealed_white_essence", () ->
            new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> INJECTOR_PANEL = ITEMS.register("injector_panel", () ->
            new ItemInjectorPanel(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GEM_SCRAP = ITEMS.register("gem_scrap", () ->
            new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PRISMATIC_INGOT = ITEMS.register("prismatic_ingot", () ->
            new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> GEM_WHISTLE = ITEMS.register("gem_whistle", () ->
            new ItemWhistle(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CONFRACTOR = ITEMS.register("confractor", () ->
            new ItemShatterer(new Item.Properties().stacksTo(1).durability(20)));

    public static final RegistryObject<Item> CONFRACTOR_BODY = ITEMS.register("confractor_body", () ->
            new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CONFRACTOR_TIP = ITEMS.register("confractor_tip", () ->
            new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CHROMA_CATALYST = ITEMS.register("chroma_catalyst", () ->
            new ItemNameBlockItem(ModBlocks.CHROMA_CLUSTER_CROP.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> WHITE_DESTABILIZER = ITEMS.register("white_destabilizer", () ->
            new ItemDestabilizer(new Item.Properties().stacksTo(1).durability(25)));
    public static final RegistryObject<Item> YELLOW_DESTABILIZER = ITEMS.register("yellow_destabilizer", () ->
            new ItemDestabilizer(new Item.Properties().stacksTo(1).durability(25)));
    public static final RegistryObject<Item> BLUE_DESTABILIZER = ITEMS.register("blue_destabilizer", () ->
            new ItemDestabilizer(new Item.Properties().stacksTo(1).durability(25)));
    public static final RegistryObject<Item> PINK_DESTABILIZER = ITEMS.register("pink_destabilizer", () ->
            new ItemDestabilizer(new Item.Properties().stacksTo(1).durability(25)));

   public static final RegistryObject<Item> PINK_REJUVENATOR = ITEMS.register("pink_rejuvenator", () ->
            new ItemPinkRejuvenator(new Item.Properties().stacksTo(1).durability(20)));
    public static final RegistryObject<Item> YELLOW_REJUVENATOR = ITEMS.register("yellow_rejuvenator", () ->
            new ItemYellowRejuvenator(new Item.Properties().stacksTo(1).durability(25)));
    public static final RegistryObject<Item> WHITE_REJUVENATOR = ITEMS.register("white_rejuvenator", () ->
            new ItemWhiteRejuvenator(new Item.Properties().stacksTo(1).durability(25)));
    public static final RegistryObject<Item> BLUE_REJUVENATOR = ITEMS.register("blue_rejuvenator", () ->
            new ItemBlueRejuvenator(new Item.Properties().stacksTo(1).durability(15)));

    public static final RegistryObject<Item> REBEL_STICK = ITEMS.register("rebel_stick", () ->
            new ItemRebelStick(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SHARE_CONTRACT = ITEMS.register("share_contract", () ->
            new ItemShareContract(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TRANSFER_CONTRACT = ITEMS.register("transfer_contract", () ->
            new ItemTransferContract(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WHITE_CHROMA = ITEMS.register("white_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 0));
    public static final RegistryObject<Item> ORANGE_CHROMA = ITEMS.register("orange_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 1));
    public static final RegistryObject<Item> MAGENTA_CHROMA = ITEMS.register("magenta_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 2));
    public static final RegistryObject<Item> LIGHT_BLUE_CHROMA = ITEMS.register("light_blue_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 3));
    public static final RegistryObject<Item> YELLOW_CHROMA = ITEMS.register("yellow_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 4));
    public static final RegistryObject<Item> LIME_CHROMA = ITEMS.register("lime_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 5));
    public static final RegistryObject<Item> PINK_CHROMA = ITEMS.register("pink_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 6));
    public static final RegistryObject<Item> GRAY_CHROMA = ITEMS.register("gray_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 7));
    public static final RegistryObject<Item> LIGHT_GRAY_CHROMA = ITEMS.register("light_gray_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 8));
    public static final RegistryObject<Item> CYAN_CHROMA = ITEMS.register("cyan_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 9));
    public static final RegistryObject<Item> PURPLE_CHROMA = ITEMS.register("purple_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 10));
    public static final RegistryObject<Item> BLUE_CHROMA = ITEMS.register("blue_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 11));
    public static final RegistryObject<Item> BROWN_CHROMA = ITEMS.register("brown_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 12));
    public static final RegistryObject<Item> GREEN_CHROMA = ITEMS.register("green_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 13));
    public static final RegistryObject<Item> RED_CHROMA = ITEMS.register("red_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 14));
    public static final RegistryObject<Item> BLACK_CHROMA = ITEMS.register("black_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 15));
    public static final RegistryObject<Item> SPECIAL_CHROMA = ITEMS.register("special_chroma", () ->
            new ItemChroma(new Item.Properties().stacksTo(64), 16));


    public static final RegistryObject<Item> FUCHSIA_SHARDS = ITEMS.register("fuchsia_shards", () ->
            new ItemBossShard(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> AMBER_SHARDS = ITEMS.register("amber_shards", () ->
            new ItemBossShard(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> COBALT_SHARDS = ITEMS.register("cobalt_shards", () ->
            new ItemBossShard(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> ALABASTER_SHARDS = ITEMS.register("alabaster_shards", () ->
            new ItemBossShard(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> WHITE_SHARDS = ITEMS.register("white_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 0));
    public static final RegistryObject<Item> ORANGE_SHARDS = ITEMS.register("orange_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 1));
    public static final RegistryObject<Item> MAGENTA_SHARDS = ITEMS.register("magenta_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 2));
    public static final RegistryObject<Item> LIGHT_BLUE_SHARDS = ITEMS.register("light_blue_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 3));
    public static final RegistryObject<Item> YELLOW_SHARDS = ITEMS.register("yellow_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 4));
    public static final RegistryObject<Item> LIME_SHARDS = ITEMS.register("lime_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 5));
    public static final RegistryObject<Item> PINK_SHARDS = ITEMS.register("pink_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 6));
    public static final RegistryObject<Item> GRAY_SHARDS = ITEMS.register("gray_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 7));
    public static final RegistryObject<Item> LIGHT_GRAY_SHARDS = ITEMS.register("light_gray_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 8));
    public static final RegistryObject<Item> CYAN_SHARDS = ITEMS.register("cyan_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 9));
    public static final RegistryObject<Item> PURPLE_SHARDS = ITEMS.register("purple_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 10));
    public static final RegistryObject<Item> BLUE_SHARDS = ITEMS.register("blue_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 11));
    public static final RegistryObject<Item> BROWN_SHARDS = ITEMS.register("brown_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 12));
    public static final RegistryObject<Item> GREEN_SHARDS = ITEMS.register("green_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 13));
    public static final RegistryObject<Item> RED_SHARDS = ITEMS.register("red_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 14));
    public static final RegistryObject<Item> BLACK_SHARDS = ITEMS.register("black_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 15));
    public static final RegistryObject<Item> SPECIAL_SHARDS = ITEMS.register("special_shards", () ->
            new ItemShard(new Item.Properties().stacksTo(64), 16));

    public static final RegistryObject<Item> INACTIVE_AGATE_BASE = ITEMS.register("inactive_agate_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_AQUAMARINE_BASE = ITEMS.register("inactive_aquamarine_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_BISMUTH_BASE = ITEMS.register("inactive_bismuth_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_BIXBITE_BASE = ITEMS.register("inactive_bixbite_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_EMERALD_BASE = ITEMS.register("inactive_emerald_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_GARNET_BASE = ITEMS.register("inactive_garnet_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_JASPER_BASE = ITEMS.register("inactive_jasper_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_LAPIS_BASE = ITEMS.register("inactive_lapis_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_LARIMAR_BASE = ITEMS.register("inactive_larimar_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_MORGANITE_BASE = ITEMS.register("inactive_morganite_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_NEPHRITE_BASE = ITEMS.register("inactive_nephrite_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_OBSIDIAN_BASE = ITEMS.register("inactive_obsidian_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_PERIDOT_BASE = ITEMS.register("inactive_peridot_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_QUARTZ_BASE = ITEMS.register("inactive_quartz_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_RUBY_BASE = ITEMS.register("inactive_ruby_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_RUTILE_BASE = ITEMS.register("inactive_rutile_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_SAPPHIRE_BASE = ITEMS.register("inactive_sapphire_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_SPINEL_BASE = ITEMS.register("inactive_spinel_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_SPODUMENE_BASE = ITEMS.register("inactive_spodumene_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_TOPAZ_BASE = ITEMS.register("inactive_topaz_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_TOURMALINE_BASE = ITEMS.register("inactive_tourmaline_base", () ->
            new ItemGemBase(new Item.Properties()));

    public static final RegistryObject<Item> INACTIVE_ZIRCON_BASE = ITEMS.register("inactive_zircon_base", () ->
            new ItemGemBase(new Item.Properties()));



    public static final RegistryObject<Item> WHITE_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ORANGE_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MAGENTA_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_BLUE_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> YELLOW_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIME_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PINK_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GRAY_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_GRAY_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CYAN_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PURPLE_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BROWN_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GREEN_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RUBY_GEM = ITEMS.register("ruby_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLACK_SAPPHIRE_GEM = ITEMS.register("sapphire_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> MILKY_QUARTZ_QUARTZ_GEM = ITEMS.register("quartz_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CHERT_QUARTZ_GEM = ITEMS.register("quartz_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CHERRY_QUARTZ_QUARTZ_GEM = ITEMS.register("quartz_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_AVENTURINE_QUARTZ_GEM = ITEMS.register("quartz_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CITRINE_QUARTZ_GEM = ITEMS.register("quartz_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PRASIOLITE_QUARTZ_GEM = ITEMS.register("quartz_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ROSE_QUARTZ_QUARTZ_GEM = ITEMS.register("quartz_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> FLINT_QUARTZ_GEM = ITEMS.register("quartz_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> SMOKY_QUARTZ_QUARTZ_GEM = ITEMS.register("quartz_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CHALCEDONY_QUARTZ_GEM = ITEMS.register("quartz_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> AMETHYST_QUARTZ_GEM = ITEMS.register("quartz_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> DUMORTIERITE_QUARTZ_QUARTZ_GEM = ITEMS.register("quartz_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> TIGERS_EYE_QUARTZ_GEM = ITEMS.register("quartz_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> HELIOTROPE_QUARTZ_GEM = ITEMS.register("quartz_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CARNELIAN_QUARTZ_GEM = ITEMS.register("quartz_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ONYX_QUARTZ_GEM = ITEMS.register("quartz_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ANGEL_AURA_QUARTZ_QUARTZ_GEM = ITEMS.register("quartz_gem_16", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LACE_AMETHYST_QUARTZ_GEM = ITEMS.register("quartz_gem_17", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> ZEBRA_JASPER_GEM = ITEMS.register("jasper_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RED_STRIPED_JASPER_GEM = ITEMS.register("jasper_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RIPPLE_JASPER_GEM = ITEMS.register("jasper_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> IMPERIAL_JASPER_GEM = ITEMS.register("jasper_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GOLDEN_JASPER_GEM = ITEMS.register("jasper_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RAINFOREST_JASPER_GEM = ITEMS.register("jasper_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MOOKAITE_JASPER_GEM = ITEMS.register("jasper_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MATRIX_JASPER_GEM = ITEMS.register("jasper_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PICASSO_JASPER_GEM = ITEMS.register("jasper_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> OCEAN_JASPER_GEM = ITEMS.register("jasper_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ROYAL_PLUME_JASPER_GEM = ITEMS.register("jasper_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_SNAKESKIN_JASPER_GEM = ITEMS.register("jasper_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BIGGS_JASPER_GEM = ITEMS.register("jasper_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> KAMBABA_JASPER_GEM = ITEMS.register("jasper_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> FLAME_JASPER_GEM = ITEMS.register("jasper_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLACK_JASPER_GEM = ITEMS.register("jasper_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BUTTERFLY_JASPER_GEM = ITEMS.register("jasper_gem_16", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> DENDRITIC_AGATE_GEM = ITEMS.register("agate_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> WINGATE_PASS_PLUME_AGATE_GEM = ITEMS.register("agate_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ROSE_AGATE_GEM = ITEMS.register("agate_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> HOLLY_BLUE_AGATE_GEM = ITEMS.register("agate_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> TAWNY_AGATE_GEM = ITEMS.register("agate_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> DRAGONS_VEIN_AGATE_GEM = ITEMS.register("agate_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> SAKURA_AGATE_GEM = ITEMS.register("agate_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BOTSWANA_AGATE_GEM = ITEMS.register("agate_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> WATER_AGATE_GEM = ITEMS.register("agate_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_LACE_AGATE_GEM = ITEMS.register("agate_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GRAPE_AGATE_GEM = ITEMS.register("agate_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ELLENSBURG_BLUE_AGATE_GEM = ITEMS.register("agate_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> TURRITELLA_AGATE_GEM = ITEMS.register("agate_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> TREE_AGATE_GEM = ITEMS.register("agate_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LAKE_SUPERIOR_AGATE_GEM = ITEMS.register("agate_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ORCA_AGATE_GEM = ITEMS.register("agate_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> IRIS_AGATE_GEM = ITEMS.register("agate_gem_16", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> WHITE_PEARL_GEM = ITEMS.register("pearl_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ORANGE_PEARL_GEM = ITEMS.register("pearl_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MAGENTA_PEARL_GEM = ITEMS.register("pearl_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_BLUE_PEARL_GEM = ITEMS.register("pearl_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> YELLOW_PEARL_GEM = ITEMS.register("pearl_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIME_PEARL_GEM = ITEMS.register("pearl_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PINK_PEARL_GEM = ITEMS.register("pearl_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GRAY_PEARL_GEM = ITEMS.register("pearl_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_GRAY_PEARL_GEM = ITEMS.register("pearl_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CYAN_PEARL_GEM = ITEMS.register("pearl_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PURPLE_PEARL_GEM = ITEMS.register("pearl_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_PEARL_GEM = ITEMS.register("pearl_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BROWN_PEARL_GEM = ITEMS.register("pearl_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GREEN_PEARL_GEM = ITEMS.register("pearl_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RED_PEARL_GEM = ITEMS.register("pearl_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLACK_PEARL_GEM = ITEMS.register("pearl_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> WHITE_SPINEL_GEM = ITEMS.register("spinel_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ORANGE_SPINEL_GEM = ITEMS.register("spinel_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MAGENTA_SPINEL_GEM = ITEMS.register("spinel_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_BLUE_SPINEL_GEM = ITEMS.register("spinel_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> YELLOW_SPINEL_GEM = ITEMS.register("spinel_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIME_SPINEL_GEM = ITEMS.register("spinel_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PINK_SPINEL_GEM = ITEMS.register("spinel_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GRAY_SPINEL_GEM = ITEMS.register("spinel_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_GRAY_SPINEL_GEM = ITEMS.register("spinel_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CYAN_SPINEL_GEM = ITEMS.register("spinel_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PURPLE_SPINEL_GEM = ITEMS.register("spinel_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_SPINEL_GEM = ITEMS.register("spinel_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BROWN_SPINEL_GEM = ITEMS.register("spinel_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GREEN_SPINEL_GEM = ITEMS.register("spinel_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RED_SPINEL_GEM = ITEMS.register("spinel_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLACK_SPINEL_GEM = ITEMS.register("spinel_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> ACHROITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PUMPKIN_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MAGENTA_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> OLENITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CANARY_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> VERDELITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ROSSMANITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ADACHIITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> POVONDRAITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PARAIBA_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> SIBERITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> INDICOLITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> DRAVITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CONGO_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RUBELLITE_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> SCHORL_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> WATERMELON_TOURMALINE_GEM = ITEMS.register("tourmaline_gem_16", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> WHITE_ZIRCON_GEM = ITEMS.register("zircon_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ORANGE_ZIRCON_GEM = ITEMS.register("zircon_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MAGENTA_ZIRCON_GEM = ITEMS.register("zircon_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_BLUE_ZIRCON_GEM = ITEMS.register("zircon_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> YELLOW_ZIRCON_GEM = ITEMS.register("zircon_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIME_ZIRCON_GEM = ITEMS.register("zircon_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PINK_ZIRCON_GEM = ITEMS.register("zircon_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GRAY_ZIRCON_GEM = ITEMS.register("zircon_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LIGHT_GRAY_ZIRCON_GEM = ITEMS.register("zircon_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> CYAN_ZIRCON_GEM = ITEMS.register("zircon_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PURPLE_ZIRCON_GEM = ITEMS.register("zircon_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_ZIRCON_GEM = ITEMS.register("zircon_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BROWN_ZIRCON_GEM = ITEMS.register("zircon_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GREEN_ZIRCON_GEM = ITEMS.register("zircon_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RED_ZIRCON_GEM = ITEMS.register("zircon_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLACK_ZIRCON_GEM = ITEMS.register("zircon_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> AQUAMARINE_GEM = ITEMS.register("aquamarine_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BISMUTH_GEM = ITEMS.register("bismuth_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BIXBITE_GEM = ITEMS.register("bixbite_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> EMERALD_GEM = ITEMS.register("emerald_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LAPIS_GEM = ITEMS.register("lapis_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> LARIMAR_GEM = ITEMS.register("larimar_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MORGANITE_GEM = ITEMS.register("morganite_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MICA_GEM = ITEMS.register("mica_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> NEPHRITE_GEM = ITEMS.register("nephrite_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> OBSIDIAN_GEM = ITEMS.register("obsidian_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PEBBLE_GEM = ITEMS.register("pebble_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PERIDOT_GEM = ITEMS.register("peridot_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RUTILE_GEM = ITEMS.register("rutile_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> SHALE_GEM = ITEMS.register("shale_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> NACRE_GEM = ITEMS.register("nacre_gem", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> LEUCO_GARNET_GEM = ITEMS.register("garnet_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> HESSONITE_GARNET_GEM = ITEMS.register("garnet_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> RHODOLITE_GARNET_GEM = ITEMS.register("garnet_gem_2", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> UMBALITE_GARNET_GEM = ITEMS.register("garnet_gem_3", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> TOPAZOLITE_GARNET_GEM = ITEMS.register("garnet_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> DEMANTOID_GARNET_GEM = ITEMS.register("garnet_gem_5", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> IMPERIAL_GARNET_GEM = ITEMS.register("garnet_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> KIMZEYITE_GARNET_GEM = ITEMS.register("garnet_gem_7", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> KATOITE_GARNET_GEM = ITEMS.register("garnet_gem_8", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BEKILY_GARNET_GEM = ITEMS.register("garnet_gem_9", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GRAPE_GARNET_GEM = ITEMS.register("garnet_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_GARNET_GEM = ITEMS.register("garnet_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> ANDALUSITE_GARNET_GEM = ITEMS.register("garnet_gem_12", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> GROSSULARITE_GARNET_GEM = ITEMS.register("garnet_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PYROPE_GARNET_GEM = ITEMS.register("garnet_gem_14", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> MELANITE_GARNET_GEM = ITEMS.register("garnet_gem_15", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> IRIDESCENT_ANDRADITE_GARNET_GEM = ITEMS.register("garnet_gem_16", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> SPODUMENE_SPODUMENE_GEM = ITEMS.register("spodumene_gem_1", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> TRIPHANE_SPODUMENE_GEM = ITEMS.register("spodumene_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> KUNZITE_SPODUMENE_GEM = ITEMS.register("spodumene_gem_10", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_SPODUMENE_SPODUMENE_GEM = ITEMS.register("spodumene_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> HIDDENITE_SPODUMENE_GEM = ITEMS.register("spodumene_gem_13", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));

    public static final RegistryObject<Item> WHITE_TOPAZ_GEM = ITEMS.register("topaz_gem_0", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> YELLOW_TOPAZ_GEM = ITEMS.register("topaz_gem_4", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> BLUE_TOPAZ_GEM = ITEMS.register("topaz_gem_11", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
    public static final RegistryObject<Item> PINK_TOPAZ_GEM = ITEMS.register("topaz_gem_6", () ->
            new ItemGem(new Item.Properties(), Gempire.MODID));
}
