package com.gempire.init;

import com.gempire.systems.injection.Crux;
import com.gempire.systems.injection.GemConditions;
import com.gempire.util.CruxType;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class ModCruxes {

    public static final GemConditions JASPER_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = 2f;
        Item primer = Items.BLAZE_ROD;
        String essences = "yellow-white";
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.QUARTZ_BLOCK.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }

    /*public static final GemConditions LARIMAR_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = -0.5f;
        float gemTemperatureMax = 0.1f;
        Item primer = Items.BLUE_ICE;
        String essences = "blue";
        gemCruxes.add(new Crux(Blocks.ICE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SNOW_BLOCK.getDefaultState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PACKED_ICE.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions PERIDOT_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.8f;
        Item primer = Items.EXPERIENCE_BOTTLE;
        String essences = "blue-yellow";
        gemCruxes.add(new Crux(Blocks.GRAVEL.getDefaultState(), 1, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_BLOCK.getDefaultState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }*/

    public static final GemConditions QUARTZ_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = 2f;
        Item primer = Items.NETHER_STAR;
        String essences = "pink-white";
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.QUARTZ_BLOCK.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions RUBY_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.2f;
        float gemTemperatureMax = 2f;
        Item primer = Items.NETHERITE_INGOT;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.NETHERRACK.getDefaultState(), 1, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_GOLD_ORE.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.getDefaultState(), 7, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHERITE_BLOCK.getDefaultState(), 10, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }

    /*public static final GemConditions RUTILE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.6f;
        float gemTemperatureMax = 1f;
        Item primer = Items.FIRE_CHARGE;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.REDSTONE_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.REDSTONE_BLOCK.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RED_SANDSTONE.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GLOWSTONE.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }*/

    public static final GemConditions AGATE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.DRAGON_BREATH;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.QUARTZ_BLOCK.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    /*public static final GemConditions AQUAMARINE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 1f;
        Item primer = Items.GHAST_TEAR;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.GRAVEL.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PRISMARINE.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SOUL_SAND.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions BISMUTH_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.NETHERITE_SWORD;
        String essences = "pink-blue";
        gemCruxes.add(new Crux(Blocks.GOLD_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_BLOCK.getDefaultState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions BIXBITE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.BONE_BLOCK;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.REDSTONE_BLOCK.getDefaultState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions LAPIS_LAZULI_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 0.8f;
        Item primer = Items.NAUTILUS_SHELL;
        String essences = "blue";
        gemCruxes.add(new Crux(Blocks.PRISMARINE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.LAPIS_ORE.getDefaultState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DARK_PRISMARINE.getDefaultState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.LAPIS_BLOCK.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions NEPHRITE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.95f;
        float gemTemperatureMax = 1f;
        Item primer = Items.WITHER_ROSE;
        String essences = "yellow";
        gemCruxes.add(new Crux(Blocks.SAND.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.HONEY_BLOCK.getDefaultState(), 3, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.WHITE_CONCRETE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.getDefaultState(), 5, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }*/

    public static final GemConditions SAPPHIRE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = 0.7f;
        Item primer = Items.ENDER_EYE;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ICE.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PACKED_ICE.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SNOW_BLOCK.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_BLOCK.getDefaultState(), 10, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    /*public static final GemConditions SPODUMENE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 0.8f;
        Item primer = Items.HEART_OF_THE_SEA;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.BONE_BLOCK.getDefaultState(), 3, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.getDefaultState(), 4, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.BRAIN_CORAL.getDefaultState(), 6, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GLOWSTONE.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions TOPAZ_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.GOLD_BLOCK;
        String essences = "yellow";
        gemCruxes.add(new Crux(Blocks.COAL_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SAND.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANDESITE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_ORE.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_BLOCK.getDefaultState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions TOURMALINE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.95f;
        float gemTemperatureMax = 1f;
        Item primer = Items.ENCHANTED_GOLDEN_APPLE;
        String essences = "white";
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.getDefaultState(), 4, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MYCELIUM.getDefaultState(), 5, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 2, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions EMERALD_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.95f;
        float gemTemperatureMax = 1f;
        Item primer = Items.EMERALD_BLOCK;
        String essences = "blue";
        gemCruxes.add(new Crux(Blocks.GRAVEL.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.EMERALD_ORE.getDefaultState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.EMERALD_BLOCK.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 3, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions GARNET_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.5f;
        float gemTemperatureMax = 2f;
        Item primer = Items.WITHER_SKELETON_SKULL;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.DIORITE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.getDefaultState(), 4, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANDESITE.getDefaultState(), 4, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.BONE_BLOCK.getDefaultState(), 6, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.END_STONE.getDefaultState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 3, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions MORGANITE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 0.8f;
        Item primer = Items.PHANTOM_MEMBRANE;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.GRAVEL.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANDESITE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SOUL_SAND.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 3, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions OBSIDIAN_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.5f;
        float gemTemperatureMax = 2f;
        Item primer = Items.END_CRYSTAL;
        String essences = "white";
        gemCruxes.add(new Crux(Blocks.OBSIDIAN.getDefaultState(), 8, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 3, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions SPINEL_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.NETHER_WART;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.GRAVEL.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.getDefaultState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.getDefaultState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 4, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_BLOCK.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.getDefaultState(), 6, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 3, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions ZIRCON_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 1f;
        Item primer = Items.TOTEM_OF_UNDYING;
        String essences = "blue";
        gemCruxes.add(new Crux(Blocks.ENCHANTING_TABLE.getDefaultState(), 3, CruxType.OTHER, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.BOOKSHELF.getDefaultState(), 4, CruxType.OTHER, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_ORE.getDefaultState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.getDefaultState(), 7, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_BLOCK.getDefaultState(), 10, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 3, gemTemperatureMin, gemTemperatureMax);
    }*/
}
