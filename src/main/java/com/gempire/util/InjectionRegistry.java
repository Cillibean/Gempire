package com.gempire.util;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashMap;

public class InjectionRegistry {
    public static ArrayList<GemInfo> listBasic = new ArrayList<>();

    public static ArrayList<GemInfo> list = new ArrayList<>();
    public static HashMap<Block, CruxInfo> blockList = new HashMap<>();
    public static void setMap() {
        list.clear();
        listBasic.clear();

        //tier 1
        listBasic.add(new GemInfo(new int[]{0, 10, 35, 0, 5, 0}, 1f, "agate", Items.DRAGON_BREATH));
        listBasic.add(new GemInfo(new int[]{10, 0, 10, 30, 0, 0}, 1.25f, "bismuth", Items.NETHERITE_SWORD));
        listBasic.add(new GemInfo(new int[]{0, 10, 25, 10, 0, 0}, 1.35f, "jasper", Items.BLAZE_ROD));
        listBasic.add(new GemInfo(new int[]{0, 0, 35, 0, 0, 15}, 0.65f, "lapis_lazuli", Items.NAUTILUS_SHELL));
        listBasic.add(new GemInfo(new int[]{0, 0, 19, 18, 3, 0}, 1.4f, "peridot", Items.EXPERIENCE_BOTTLE));
        listBasic.add(new GemInfo(new int[]{10, 10, 25, 0, 0, 0}, 1.35f, "quartz", Items.NETHER_STAR));
        listBasic.add(new GemInfo(new int[]{10, 0, 16, 4, 0, 0}, 1.6f, "ruby", Items.NETHERITE_INGOT));
        listBasic.add(new GemInfo(new int[]{10, 0, 15, 15, 0, 0}, 1.6f, "rutile", Items.FIRE_CHARGE));
        listBasic.add(new GemInfo(new int[]{30, 0, 30, 0, 0, 0}, 1.25f, "spinel", Items.NETHER_WART));
        listBasic.add(new GemInfo(new int[]{0, 0, 40, 10, 0, 0}, 1.25f, "topaz", Items.GOLD_BLOCK));
        listBasic.add(new GemInfo(new int[]{20, 10, 15, 0, 0, 0}, 0.95f, "tourmaline", Items.ENCHANTED_GOLDEN_APPLE));

        //tier 2
        list.add(new GemInfo(new int[]{0, 10, 35, 0, 5, 0}, 1f, "agate", Items.DRAGON_BREATH));
        list.add(new GemInfo(new int[]{0, 10, 35, 0, 0, 5}, 0.75f, "aquamarine", Items.GHAST_TEAR));
        list.add(new GemInfo(new int[]{10, 0, 10, 30, 0, 0}, 1.25f, "bismuth", Items.NETHERITE_SWORD));
        list.add(new GemInfo(new int[]{15, 0, 25, 0, 0, 10}, 1.25f, "bixbite", ModItems.PRISMATIC_SHEARS.get()));
        list.add(new GemInfo(new int[]{0, 10, 50, 10, 0, 0}, 0.95f, "emerald", Items.EMERALD_BLOCK));
        list.add(new GemInfo(new int[]{10, 14, 46, 10, 0, 0}, 1f, "garnet", Items.WITHER_SKELETON_SKULL));
        list.add(new GemInfo(new int[]{0, 10, 25, 10, 0, 0}, 1.35f, "jasper", Items.BLAZE_ROD));
        list.add(new GemInfo(new int[]{0, 0, 35, 0, 0, 15}, 0.65f, "lapis_lazuli", Items.NAUTILUS_SHELL));
        list.add(new GemInfo(new int[]{0, 0, 25, 0, 15, 0}, -0.3f, "larimar", Items.BLUE_ICE));
        list.add(new GemInfo(new int[]{10, 10, 30, 0, 0, 0}, 0.65f, "morganite", Items.PHANTOM_MEMBRANE));
        list.add(new GemInfo(new int[]{10, 0, 30, 10, 0, 0}, 0.95f, "nephrite", Items.WITHER_ROSE));
        list.add(new GemInfo(new int[]{0, 10, 8, 0, 12, 0}, 1.75f, "obsidian", Items.END_CRYSTAL));
        list.add(new GemInfo(new int[]{0, 0, 19, 18, 3, 0}, 1.4f, "peridot", Items.EXPERIENCE_BOTTLE));
        list.add(new GemInfo(new int[]{10, 10, 25, 0, 0, 0}, 1.35f, "quartz", Items.NETHER_STAR));
        list.add(new GemInfo(new int[]{10, 0, 16, 4, 0, 0}, 1.6f, "ruby", Items.NETHERITE_INGOT));
        list.add(new GemInfo(new int[]{10, 0, 15, 15, 0, 0}, 1.6f, "rutile", Items.FIRE_CHARGE));
        list.add(new GemInfo(new int[]{0, 10, 42, 8, 0, 0}, 0.35f, "sapphire", Items.ENDER_EYE));
        list.add(new GemInfo(new int[]{30, 0, 30, 0, 0, 0}, 1.25f, "spinel", Items.NETHER_WART));
        list.add(new GemInfo(new int[]{14, 0, 16, 0, 0, 10}, 0.65f, "spodumene", Items.HEART_OF_THE_SEA));
        list.add(new GemInfo(new int[]{0, 0, 40, 10, 0, 0}, 1.25f, "topaz", Items.GOLD_BLOCK));
        list.add(new GemInfo(new int[]{20, 10, 15, 0, 0, 0}, 0.95f, "tourmaline", Items.ENCHANTED_GOLDEN_APPLE));
        list.add(new GemInfo(new int[]{0, 30, 40, 0, 0, 0}, 0.75f, "zircon", Items.TOTEM_OF_UNDYING));

        //0 - organic
        //1 - mystical
        //2 - crystalline
        //3 - metallic
        //4 - igneous
        //5 - moisture
        blockList.put(Blocks.ROOTED_DIRT, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.ROOTED_DIRT, "rooted_dirt"));
        blockList.put(Blocks.MUD, new CruxInfo(new int[]{0, 0, 0, 0, 0, 1}, Blocks.MUD, "mud"));
        blockList.put(Blocks.CLAY, new CruxInfo(new int[]{0, 0, 0, 0, 0, 2}, Blocks.CLAY, "clay"));
        blockList.put(Blocks.MOSS_BLOCK, new CruxInfo(new int[]{2, 0, 0, 0, 0, 0}, Blocks.MOSS_BLOCK, "moss_block"));
        blockList.put(Blocks.DRIPSTONE_BLOCK, new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.DRIPSTONE_BLOCK, "dripstone_block"));
        blockList.put(Blocks.BLACKSTONE, new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.BLACKSTONE, "blackstone"));
        blockList.put(Blocks.TUFF, new CruxInfo(new int[]{0, 0, 0, 0, 2, 0}, Blocks.TUFF, "tuff"));
        blockList.put(Blocks.PRISMARINE, new CruxInfo(new int[]{0, 0, 0, 0, 0, 3}, Blocks.PRISMARINE, "prismarine"));
        blockList.put(Blocks.MAGMA_BLOCK, new CruxInfo(new int[]{0, 0, 0, 0, 3, 0}, Blocks.MAGMA_BLOCK, "magma_block"));
        blockList.put(Blocks.OBSIDIAN, new CruxInfo(new int[]{0, 0, 1, 0, 3, 0}, Blocks.OBSIDIAN, "obsidian"));
        blockList.put(Blocks.CRYING_OBSIDIAN, new CruxInfo(new int[]{0, 1, 1, 0, 5, 0}, Blocks.CRYING_OBSIDIAN, "crying_obsidian"));
        blockList.put(Blocks.SOUL_SAND, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SOUL_SAND, "soul_sand"));
        blockList.put(Blocks.BONE_BLOCK, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.BONE_BLOCK, "bone_block"));
        blockList.put(Blocks.BASALT, new CruxInfo(new int[]{0, 0, 0, 0, 4, 0}, Blocks.BASALT, "basalt"));
        blockList.put(Blocks.COAL_ORE, new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.COAL_ORE, "coal_ore"));
        blockList.put(Blocks.DEEPSLATE_COAL_ORE, new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.DEEPSLATE_COAL_ORE, "deepslate_coal_ore"));
        blockList.put(Blocks.COAL_BLOCK, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.COAL_BLOCK, "coal_block"));
        blockList.put(Blocks.IRON_ORE, new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.IRON_ORE, "iron_ore"));
        blockList.put(Blocks.DEEPSLATE_IRON_ORE, new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.DEEPSLATE_IRON_ORE, "deepslate_iron_ore"));
        blockList.put(Blocks.RAW_IRON_BLOCK, new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.RAW_IRON_BLOCK, "raw_iron_block"));
        blockList.put(Blocks.IRON_BLOCK, new CruxInfo(new int[]{0, 0, 0, 4, 0, 0}, Blocks.IRON_BLOCK, "iron_block"));
        blockList.put(Blocks.GOLD_ORE, new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.GOLD_ORE, "gold_ore"));
        blockList.put(Blocks.DEEPSLATE_GOLD_ORE, new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.DEEPSLATE_GOLD_ORE, "deepslate_gold_ore"));
        blockList.put(Blocks.RAW_GOLD_BLOCK, new CruxInfo(new int[]{0, 0, 0, 4, 0, 0}, Blocks.RAW_GOLD_BLOCK, "raw_gold_block"));
        blockList.put(Blocks.GOLD_BLOCK, new CruxInfo(new int[]{0, 0, 0, 5, 0, 0}, Blocks.GOLD_BLOCK, "gold_block"));
        blockList.put(Blocks.COPPER_ORE, new CruxInfo(new int[]{0, 0, 0, 1, 0, 0}, Blocks.COPPER_ORE, "copper_ore"));
        blockList.put(Blocks.DEEPSLATE_COPPER_ORE, new CruxInfo(new int[]{0, 0, 0, 1, 0, 0}, Blocks.DEEPSLATE_COPPER_ORE, "deepslate_copper_ore"));
        blockList.put(Blocks.RAW_COPPER_BLOCK, new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.RAW_COPPER_BLOCK, "raw_copper_block"));
        blockList.put(Blocks.COPPER_BLOCK, new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.COPPER_BLOCK, "copper_block"));
        blockList.put(ModBlocks.TUNGSTEN_ORE.get(), new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, ModBlocks.TUNGSTEN_ORE.get(), "tungsten_ore"));
        blockList.put(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), "deepslate_tungsten_ore"));
        blockList.put(ModBlocks.RAW_TUNGSTEN_BLOCK.get(), new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, ModBlocks.RAW_TUNGSTEN_BLOCK.get(), "raw_tungsten_block"));
        blockList.put(ModBlocks.TUNGSTEN_BLOCK.get(), new CruxInfo(new int[]{0, 0, 0, 4, 0, 0}, ModBlocks.TUNGSTEN_BLOCK.get(), "tungsten_block"));
        blockList.put(ModBlocks.PRISMATIC_BLOCK.get(), new CruxInfo(new int[]{0, 0, 1, 4, 0, 0}, ModBlocks.PRISMATIC_BLOCK.get(), "prismatic_block"));
        blockList.put(Blocks.REDSTONE_ORE, new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.REDSTONE_ORE, "redstone_ore"));
        blockList.put(Blocks.DEEPSLATE_REDSTONE_ORE, new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.DEEPSLATE_REDSTONE_ORE, "deepslate_redstone_ore"));
        blockList.put(Blocks.REDSTONE_BLOCK, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.REDSTONE_BLOCK, "redstone_block"));
        blockList.put(Blocks.EMERALD_ORE, new CruxInfo(new int[]{0, 0, 3, 0, 0, 0}, Blocks.EMERALD_ORE, "emerald_ore"));
        blockList.put(Blocks.DEEPSLATE_EMERALD_ORE, new CruxInfo(new int[]{0, 0, 3, 0, 0, 0}, Blocks.DEEPSLATE_EMERALD_ORE, "deepslate_emerald_ore"));
        blockList.put(Blocks.EMERALD_BLOCK, new CruxInfo(new int[]{0, 0, 4, 0, 0, 0}, Blocks.EMERALD_BLOCK, "emerald_block"));
        blockList.put(Blocks.LAPIS_ORE, new CruxInfo(new int[]{0, 2, 2, 0, 0, 0}, Blocks.LAPIS_ORE, "lapis_ore"));
        blockList.put(Blocks.DEEPSLATE_LAPIS_ORE, new CruxInfo(new int[]{0, 2, 2, 0, 0, 0}, Blocks.DEEPSLATE_LAPIS_ORE, "deepslate_lapis_ore"));
        blockList.put(Blocks.LAPIS_BLOCK, new CruxInfo(new int[]{0, 2, 3, 0, 0, 0}, Blocks.LAPIS_BLOCK, "lapis_block"));
        blockList.put(Blocks.DIAMOND_ORE, new CruxInfo(new int[]{0, 0, 5, 0, 0, 0}, Blocks.DIAMOND_ORE, "diamond_ore"));
        blockList.put(Blocks.DEEPSLATE_DIAMOND_ORE, new CruxInfo(new int[]{0, 0, 5, 0, 0, 0}, Blocks.DEEPSLATE_DIAMOND_ORE, "deepslate_diamond_ore"));
        blockList.put(Blocks.DIAMOND_BLOCK, new CruxInfo(new int[]{0, 0, 6, 0, 0, 0}, Blocks.DIAMOND_BLOCK, "diamond_block"));
        blockList.put(Blocks.NETHER_QUARTZ_ORE, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.NETHER_QUARTZ_ORE, "nether_quartz_ore"));
        blockList.put(Blocks.QUARTZ_BLOCK, new CruxInfo(new int[]{0, 0, 3, 0, 0, 0}, Blocks.QUARTZ_BLOCK, "quartz_block"));
        blockList.put(Blocks.ANCIENT_DEBRIS, new CruxInfo(new int[]{0, 2, 4, 0, 0, 0}, Blocks.ANCIENT_DEBRIS, "ancient_debris"));
        blockList.put(Blocks.NETHERITE_BLOCK, new CruxInfo(new int[]{0, 2, 6, 0, 0, 0}, Blocks.NETHERITE_BLOCK, "netherite_block"));
        blockList.put(Blocks.GLOWSTONE, new CruxInfo(new int[]{0, 0, 3, 0, 0, 0}, Blocks.GLOWSTONE, "glowstone"));
        blockList.put(Blocks.AMETHYST_BLOCK, new CruxInfo(new int[]{0, 0, 3, 0, 0, 0}, Blocks.AMETHYST_BLOCK, "amethyst_block"));
        blockList.put(Blocks.BUDDING_AMETHYST, new CruxInfo(new int[]{0, 0, 5, 0, 0, 0}, Blocks.BUDDING_AMETHYST, "budding_amethyst"));
        blockList.put(Blocks.AMETHYST_CLUSTER, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.AMETHYST_CLUSTER, "amethyst_cluster"));
        blockList.put(Blocks.SMALL_AMETHYST_BUD, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.SMALL_AMETHYST_BUD, "small_amethyst_bud"));
        blockList.put(Blocks.MEDIUM_AMETHYST_BUD, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.MEDIUM_AMETHYST_BUD, "medium_amethyst_bud"));
        blockList.put(Blocks.LARGE_AMETHYST_BUD, new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.LARGE_AMETHYST_BUD, "large_amethyst_bud"));
        blockList.put(Blocks.OAK_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.OAK_LOG, "oak_log"));
        blockList.put(Blocks.DARK_OAK_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.DARK_OAK_LOG, "dark_oak_log"));
        blockList.put(Blocks.BIRCH_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.BIRCH_LOG, "birch_log"));
        blockList.put(Blocks.SPRUCE_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.SPRUCE_LOG, "spruce_log"));
        blockList.put(Blocks.JUNGLE_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.JUNGLE_LOG, "jungle_log"));
        blockList.put(Blocks.ACACIA_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.ACACIA_LOG, "acacia_log"));
        blockList.put(Blocks.MANGROVE_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.MANGROVE_LOG, "mangrove_log"));
        blockList.put(Blocks.CHERRY_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.CHERRY_LOG, "cherry_log"));
        blockList.put(Blocks.STRIPPED_OAK_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_OAK_LOG, "stripped_oak_log"));
        blockList.put(Blocks.STRIPPED_DARK_OAK_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_DARK_OAK_LOG, "stripped_dark_oak_log"));
        blockList.put(Blocks.STRIPPED_BIRCH_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_BIRCH_LOG, "stripped_birch_log"));
        blockList.put(Blocks.STRIPPED_SPRUCE_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_SPRUCE_LOG, "stripped_spruce_log"));
        blockList.put(Blocks.STRIPPED_JUNGLE_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_JUNGLE_LOG, "stripped_jungle_log"));
        blockList.put(Blocks.STRIPPED_ACACIA_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_ACACIA_LOG, "stripped_acacia_log"));
        blockList.put(Blocks.STRIPPED_MANGROVE_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_MANGROVE_LOG, "stripped_mangrove_log"));
        blockList.put(Blocks.STRIPPED_CHERRY_LOG, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_CHERRY_LOG, "stripped_cherry_log"));
        blockList.put(Blocks.OAK_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.OAK_WOOD, "oak_wood"));
        blockList.put(Blocks.DARK_OAK_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.DARK_OAK_WOOD, "dark_oak_wood"));
        blockList.put(Blocks.BIRCH_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.BIRCH_WOOD, "birch_wood"));
        blockList.put(Blocks.SPRUCE_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.SPRUCE_WOOD, "spruce_wood"));
        blockList.put(Blocks.JUNGLE_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.JUNGLE_WOOD, "jungle_wood"));
        blockList.put(Blocks.ACACIA_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.ACACIA_WOOD, "acacia_wood"));
        blockList.put(Blocks.MANGROVE_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.MANGROVE_WOOD, "mangrove_wood"));
        blockList.put(Blocks.CHERRY_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.CHERRY_WOOD, "cherry_log"));
        blockList.put(Blocks.STRIPPED_OAK_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_OAK_WOOD, "stripped_oak_wood"));
        blockList.put(Blocks.STRIPPED_DARK_OAK_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_DARK_OAK_WOOD, "stripped_dark_oak_wood"));
        blockList.put(Blocks.STRIPPED_BIRCH_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_BIRCH_WOOD, "stripped_birch_wood"));
        blockList.put(Blocks.STRIPPED_SPRUCE_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_SPRUCE_WOOD, "stripped_spruce_wood"));
        blockList.put(Blocks.STRIPPED_JUNGLE_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_JUNGLE_WOOD, "stripped_jungle_wood"));
        blockList.put(Blocks.STRIPPED_ACACIA_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_ACACIA_WOOD, "stripped_acacia_wood"));
        blockList.put(Blocks.STRIPPED_MANGROVE_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_MANGROVE_WOOD, "stripped_mangrove_wood"));
        blockList.put(Blocks.STRIPPED_CHERRY_WOOD, new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.STRIPPED_CHERRY_WOOD, "stripped_cherry_wood"));
        blockList.put(Blocks.OAK_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.OAK_LEAVES, "oak_leaves"));
        blockList.put(Blocks.DARK_OAK_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.DARK_OAK_LEAVES, "dark_oak_leaves"));
        blockList.put(Blocks.BIRCH_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.BIRCH_LEAVES, "birch_leaves"));
        blockList.put(Blocks.SPRUCE_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.SPRUCE_LEAVES, "spruce_leaves"));
        blockList.put(Blocks.JUNGLE_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.JUNGLE_LEAVES, "jungle_leaves"));
        blockList.put(Blocks.ACACIA_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.ACACIA_LEAVES, "acacia_leaves"));
        blockList.put(Blocks.MANGROVE_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.MANGROVE_LEAVES, "mangrove_leaves"));
        blockList.put(Blocks.CHERRY_LEAVES, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.CHERRY_LEAVES, "cherry_leaves"));
        blockList.put(Blocks.CRIMSON_STEM, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.CRIMSON_STEM, "crimson_stem"));
        blockList.put(Blocks.STRIPPED_CRIMSON_STEM, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.STRIPPED_CRIMSON_STEM, "stripped_crimson_stem"));
        blockList.put(Blocks.CRIMSON_HYPHAE, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.CRIMSON_HYPHAE, "crimson_hyphae"));
        blockList.put(Blocks.STRIPPED_CRIMSON_HYPHAE, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.STRIPPED_CRIMSON_HYPHAE, "stripped_crimson_hyphae"));
        blockList.put(Blocks.NETHER_WART_BLOCK, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.NETHER_WART_BLOCK, "nether_wart_block"));
        blockList.put(Blocks.WARPED_STEM, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.WARPED_STEM, "warped_stem"));
        blockList.put(Blocks.STRIPPED_WARPED_STEM, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.STRIPPED_WARPED_STEM, "stripped_warped_stem"));
        blockList.put(Blocks.WARPED_HYPHAE, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.WARPED_HYPHAE, "warped_hyphae"));
        blockList.put(Blocks.STRIPPED_WARPED_HYPHAE, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.STRIPPED_WARPED_HYPHAE, "stripped_warped_hyphae"));
        blockList.put(Blocks.WARPED_WART_BLOCK, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.WARPED_WART_BLOCK, "warped_hyphae"));
        blockList.put(Blocks.MUSHROOM_STEM, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.MUSHROOM_STEM, "mushroom_stem"));
        blockList.put(Blocks.BROWN_MUSHROOM_BLOCK, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.BROWN_MUSHROOM_BLOCK, "brown_mushroom_block"));
        blockList.put(Blocks.RED_MUSHROOM_BLOCK, new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.RED_MUSHROOM_BLOCK, "red_mushroom_block"));
        blockList.put(Blocks.SEA_PICKLE, new CruxInfo(new int[]{2, 0, 0, 0, 0, 3}, Blocks.SEA_PICKLE, "sea_pickle"));
        blockList.put(Blocks.KELP, new CruxInfo(new int[]{2, 0, 0, 0, 0, 3}, Blocks.KELP, "kelp"));
        blockList.put(Blocks.DRIED_KELP_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 4}, Blocks.DRIED_KELP_BLOCK, "dried_kelp_block"));
        blockList.put(Blocks.SPONGE, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.SPONGE, "sponge"));
        blockList.put(Blocks.WET_SPONGE, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.WET_SPONGE, "wet_sponge"));
        blockList.put(Blocks.BRAIN_CORAL_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.BRAIN_CORAL_BLOCK, "brain_coral_block"));
        blockList.put(Blocks.BUBBLE_CORAL_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.BUBBLE_CORAL_BLOCK, "bubble_coral_block"));
        blockList.put(Blocks.FIRE_CORAL_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.FIRE_CORAL_BLOCK, "fire_coral_block"));
        blockList.put(Blocks.HORN_CORAL_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.HORN_CORAL_BLOCK, "horn_coral_block"));
        blockList.put(Blocks.TUBE_CORAL_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 5}, Blocks.TUBE_CORAL_BLOCK, "tube_coral_block"));
        blockList.put(Blocks.BRAIN_CORAL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.BRAIN_CORAL_FAN, "brain_coral_fan"));
        blockList.put(Blocks.BUBBLE_CORAL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.BUBBLE_CORAL_FAN, "bubble_coral_fan"));
        blockList.put(Blocks.FIRE_CORAL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.FIRE_CORAL_FAN, "fire_coral_fan"));
        blockList.put(Blocks.HORN_CORAL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.HORN_CORAL_FAN, "horn_coral_fan"));
        blockList.put(Blocks.TUBE_CORAL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.TUBE_CORAL_FAN, "tube_coral_fan"));
        blockList.put(Blocks.BRAIN_CORAL_WALL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.BRAIN_CORAL_WALL_FAN, "brain_coral_wall_fan"));
        blockList.put(Blocks.BUBBLE_CORAL_WALL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.BUBBLE_CORAL_WALL_FAN, "bubble_coral_wall_fan"));
        blockList.put(Blocks.FIRE_CORAL_WALL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.FIRE_CORAL_WALL_FAN, "fire_coral_wall_fan"));
        blockList.put(Blocks.HORN_CORAL_WALL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.HORN_CORAL_WALL_FAN, "horn_coral_wall_fan"));
        blockList.put(Blocks.TUBE_CORAL_WALL_FAN, new CruxInfo(new int[]{1, 0, 0, 0, 0, 2}, Blocks.TUBE_CORAL_WALL_FAN, "tube_coral_wall_fan"));
        blockList.put(Blocks.MELON, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.MELON, "melon"));
        blockList.put(Blocks.PUMPKIN, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.PUMPKIN, "pumpkin"));
        blockList.put(Blocks.CARVED_PUMPKIN, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.CARVED_PUMPKIN, "carved_pumpkin"));
        blockList.put(Blocks.HAY_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.HAY_BLOCK, "hay_block"));
        blockList.put(Blocks.BEEHIVE, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.BEEHIVE, "beehive"));
        blockList.put(Blocks.HONEYCOMB_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.HONEYCOMB_BLOCK, "honeycomb_block"));
        blockList.put(Blocks.HONEY_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.HONEY_BLOCK, "honey_block"));
        blockList.put(Blocks.SLIME_BLOCK, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.SLIME_BLOCK, "slime_block"));
        blockList.put(Blocks.OCHRE_FROGLIGHT, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.OCHRE_FROGLIGHT, "ochre_froglight"));
        blockList.put(Blocks.VERDANT_FROGLIGHT, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.VERDANT_FROGLIGHT, "verdant_froglight"));
        blockList.put(Blocks.PEARLESCENT_FROGLIGHT, new CruxInfo(new int[]{3, 0, 0, 0, 0, 0}, Blocks.PEARLESCENT_FROGLIGHT, "pearlescent_froglight"));
        blockList.put(Blocks.SCULK, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SCULK, "sculk"));
        blockList.put(Blocks.SCULK_CATALYST, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SCULK_CATALYST, "sculk_catalyst"));
        blockList.put(Blocks.SCULK_SENSOR, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SCULK_SENSOR, "sculk_sensor"));
        blockList.put(Blocks.SCULK_SHRIEKER, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SCULK_SHRIEKER, "sculk_shrieker"));
        blockList.put(Blocks.SCULK_VEIN, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SCULK_VEIN, "sculk_vein"));
        blockList.put(Blocks.MUDDY_MANGROVE_ROOTS, new CruxInfo(new int[]{4, 0, 0, 0, 0, 1}, Blocks.MUDDY_MANGROVE_ROOTS, "muddy_mangrove_roots"));
        blockList.put(Blocks.CONDUIT, new CruxInfo(new int[]{0, 4, 0, 0, 0, 6}, Blocks.CONDUIT, "conduit"));
        blockList.put(ModBlocks.RED_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.RED_CHROMA_CRYSTAL.get(), "red_chroma_crystal"));
        blockList.put(ModBlocks.ORANGE_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.ORANGE_CHROMA_CRYSTAL.get(), "orange_chroma_crystal"));
        blockList.put(ModBlocks.YELLOW_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.YELLOW_CHROMA_CRYSTAL.get(), "yellow_chroma_crystal"));
        blockList.put(ModBlocks.LIME_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.LIME_CHROMA_CRYSTAL.get(), "lime_chroma_crystal"));
        blockList.put(ModBlocks.GREEN_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.GREEN_CHROMA_CRYSTAL.get(), "green_chroma_crystal"));
        blockList.put(ModBlocks.CYAN_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.CYAN_CHROMA_CRYSTAL.get(), "cyan_chroma_crystal"));
        blockList.put(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(), "light_blue_chroma_crystal"));
        blockList.put(ModBlocks.BLUE_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.BLUE_CHROMA_CRYSTAL.get(), "blue_chroma_crystal"));
        blockList.put(ModBlocks.PURPLE_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.PURPLE_CHROMA_CRYSTAL.get(), "purple_chroma_crystal"));
        blockList.put(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(), "magenta_chroma_crystal"));
        blockList.put(ModBlocks.PINK_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.PINK_CHROMA_CRYSTAL.get(), "pink_chroma_crystal"));
        blockList.put(ModBlocks.WHITE_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.WHITE_CHROMA_CRYSTAL.get(), "white_chroma_crystal"));
        blockList.put(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(), "light_gray_chroma_crystal"));
        blockList.put(ModBlocks.GRAY_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.GRAY_CHROMA_CRYSTAL.get(), "gray_chroma_crystal"));
        blockList.put(ModBlocks.BLACK_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.BLACK_CHROMA_CRYSTAL.get(), "black_chroma_crystal"));
        blockList.put(ModBlocks.BROWN_CHROMA_CRYSTAL.get(), new CruxInfo(new int[]{0, 3, 4, 0, 0, 0}, ModBlocks.BROWN_CHROMA_CRYSTAL.get(), "brown_chroma_crystal"));
        blockList.put(Blocks.BEACON, new CruxInfo(new int[]{0, 5, 0, 0, 0, 0}, Blocks.BEACON, "beacon"));
        blockList.put(Blocks.LODESTONE, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.LODESTONE, "lodestone"));
        blockList.put(Blocks.RESPAWN_ANCHOR, new CruxInfo(new int[]{0, 4, 1, 0, 2, 0}, Blocks.RESPAWN_ANCHOR, "respawn_anchor"));
        blockList.put(Blocks.REINFORCED_DEEPSLATE, new CruxInfo(new int[]{0, 6, 0, 0, 0, 0}, Blocks.REINFORCED_DEEPSLATE, "reinforced_deepslate"));
        blockList.put(Blocks.BOOKSHELF, new CruxInfo(new int[]{0, 1, 0, 0, 0, 0}, Blocks.BOOKSHELF, "bookshelf"));
        blockList.put(Blocks.CHISELED_BOOKSHELF, new CruxInfo(new int[]{0, 1, 0, 0, 0, 0}, Blocks.CHISELED_BOOKSHELF, "chiseled_bookshelf"));
        blockList.put(Blocks.BREWING_STAND, new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.BREWING_STAND, "brewing_stand"));
        blockList.put(Blocks.ENCHANTING_TABLE, new CruxInfo(new int[]{0, 4, 0, 0, 0, 0}, Blocks.ENCHANTING_TABLE, "enchanting_table"));
        blockList.put(Blocks.ENDER_CHEST, new CruxInfo(new int[]{0, 3, 0, 0, 0, 0}, Blocks.ENDER_CHEST, "ender_chest"));
        blockList.put(Blocks.MOSSY_COBBLESTONE, new CruxInfo(new int[]{1, 0, 1, 0, 0, 0}, Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone"));
        blockList.put(Blocks.MOSSY_STONE_BRICKS, new CruxInfo(new int[]{1, 0, 1, 0, 0, 0}, Blocks.MOSSY_STONE_BRICKS, "mossy_stone_bricks"));
        blockList.put(Blocks.INFESTED_MOSSY_STONE_BRICKS, new CruxInfo(new int[]{1, 0, 1, 0, 0, 0}, Blocks.INFESTED_MOSSY_STONE_BRICKS, "infested_mossy_stone_bricks"));
        blockList.put(Blocks.PACKED_MUD, new CruxInfo(new int[]{0, 0, 0, 0, 0, 2}, Blocks.PACKED_MUD, "packed_mud"));
        blockList.put(Blocks.SEA_LANTERN, new CruxInfo(new int[]{0, 2, 0, 0, 0, 4}, Blocks.SEA_LANTERN, "sea_lantern"));
        blockList.put(Blocks.GILDED_BLACKSTONE, new CruxInfo(new int[]{0, 3, 0, 2, 0, 0}, Blocks.GILDED_BLACKSTONE, "gilded_blackstone"));
        blockList.put(Blocks.NETHER_GOLD_ORE, new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.NETHER_GOLD_ORE, "nether_gold_ore"));
        blockList.put(Blocks.PURPUR_BLOCK, new CruxInfo(new int[]{0, 1, 3, 0, 0, 0}, Blocks.PURPUR_BLOCK, "purpur_block"));
        blockList.put(Blocks.PURPUR_PILLAR, new CruxInfo(new int[]{0, 1, 3, 0, 0, 0}, Blocks.PURPUR_PILLAR, "purpur_pillar"));
    }
}
