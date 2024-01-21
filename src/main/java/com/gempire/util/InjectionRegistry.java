package com.gempire.util;

import com.gempire.init.ModBlocks;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;

public class InjectionRegistry {
    public static ArrayList<GemInfo> list = new ArrayList<>();
    public static ArrayList<CruxInfo> blockList = new ArrayList<>();
    public static void setMap() {
        list.add(new GemInfo(new int[]{10, 5, 5, 5, 5}, 0, "agate"));
        list.add(new GemInfo(new int[]{5, 10, 10, 5, 5}, 0, "aquamarine"));
        list.add(new GemInfo(new int[]{5, 5, 4, 5, 5}, 0, "bismuth"));
        list.add(new GemInfo(new int[]{2, 5, 4, 5, 5}, 0, "bixbite"));
        list.add(new GemInfo(new int[]{1, 5, 4, 5, 5}, 0, "emerald"));
        list.add(new GemInfo(new int[]{4, 5, 4, 5, 5}, 0, "garnet"));
        list.add(new GemInfo(new int[]{8, 5, 4, 5, 5}, 0, "jasper"));
        list.add(new GemInfo(new int[]{4, 5, 4, 5, 5}, 0, "lapis_lazuli"));
        list.add(new GemInfo(new int[]{3, 5, 4, 5, 5}, 0, "larimar"));
        list.add(new GemInfo(new int[]{7, 5, 4, 5, 5}, 0, "morganite"));
        list.add(new GemInfo(new int[]{9, 5, 4, 5, 5}, 0, "nephrite"));
        list.add(new GemInfo(new int[]{8, 5, 4, 5, 5}, 0, "obsidian"));
        list.add(new GemInfo(new int[]{3, 5, 4, 5, 5}, 0, "peridot"));
        list.add(new GemInfo(new int[]{4, 5, 4, 5, 5}, 0, "quartz"));
        list.add(new GemInfo(new int[]{1, 5, 4, 5, 5}, 0, "ruby"));
        list.add(new GemInfo(new int[]{5, 5, 4, 5, 5}, 0, "rutile"));
        list.add(new GemInfo(new int[]{7, 5, 4, 5, 5}, 0, "sapphire"));
        list.add(new GemInfo(new int[]{6, 5, 4, 5, 5}, 0, "spinel"));
        list.add(new GemInfo(new int[]{4, 5, 4, 5, 5}, 0, "spodumene"));
        list.add(new GemInfo(new int[]{3, 5, 4, 5, 5}, 0, "topaz"));
        list.add(new GemInfo(new int[]{7, 5, 4, 5, 5}, 0, "tourmaline"));
        list.add(new GemInfo(new int[]{9, 5, 4, 5, 5}, 0, "zircon"));
        //0 - organic
        //1 - mystical
        //2 - crystalline
        //3 - metallic
        //4 - igneous
        //5 - moisture
        blockList.add(new CruxInfo(new int[]{1, 0, 0, 0, 0, 0}, Blocks.ROOTED_DIRT, "rooted_dirt"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 0, 0, 1}, Blocks.MUD, "mud"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 0, 0, 2}, Blocks.CLAY, "clay"));
        blockList.add(new CruxInfo(new int[]{2, 0, 0, 0, 0, 0}, Blocks.MOSS_BLOCK, "moss_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.GRANITE, "granite"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.DIORITE, "diorite"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.CALCITE, "calcite"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.DRIPSTONE_BLOCK, "dripstone_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.BLACKSTONE, "blackstone"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 1, 0}, Blocks.ANDESITE, "andesite"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 0, 2, 0}, Blocks.TUFF, "tuff"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 0, 0, 3}, Blocks.PRISMARINE, "prismarine"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 0, 3, 0}, Blocks.MAGMA_BLOCK, "magma_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 3, 0}, Blocks.OBSIDIAN, "obsidian"));
        blockList.add(new CruxInfo(new int[]{0, 1, 1, 0, 5, 0}, Blocks.CRYING_OBSIDIAN, "crying_obsidian"));
        blockList.add(new CruxInfo(new int[]{0, 2, 0, 0, 0, 0}, Blocks.SOUL_SAND, "soul_sand"));
        blockList.add(new CruxInfo(new int[]{4, 0, 0, 0, 0, 0}, Blocks.BONE_BLOCK, "bone_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 0, 4, 0}, Blocks.BASALT, "basalt"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.COAL_ORE, "coal_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 1, 0, 0, 0}, Blocks.DEEPSLATE_COAL_ORE, "deepslate_coal_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 2, 0, 0, 0}, Blocks.COAL_BLOCK, "coal_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.IRON_ORE, "iron_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.DEEPSLATE_IRON_ORE, "deepslate_iron_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.RAW_IRON_BLOCK, "raw_iron_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 4, 0, 0}, Blocks.IRON_BLOCK, "iron_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.GOLD_ORE, "gold_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.DEEPSLATE_GOLD_ORE, "deepslate_gold_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 4, 0, 0}, Blocks.RAW_GOLD_BLOCK, "raw_gold_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 5, 0, 0}, Blocks.GOLD_BLOCK, "gold_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 1, 0, 0}, Blocks.COPPER_ORE, "copper_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 1, 0, 0}, Blocks.DEEPSLATE_COPPER_ORE, "deepslate_copper_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, Blocks.RAW_COPPER_BLOCK, "raw_copper_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, Blocks.COPPER_BLOCK, "copper_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, ModBlocks.TUNGSTEN_ORE.get(), "tungsten_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 2, 0, 0}, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), "deepslate_tungsten_ore"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 3, 0, 0}, ModBlocks.RAW_TUNGSTEN_BLOCK.get(), "raw_tungsten_block"));
        blockList.add(new CruxInfo(new int[]{0, 0, 0, 4, 0, 0}, ModBlocks.TUNGSTEN_BLOCK.get(), "tungsten_block"));
    }
}
