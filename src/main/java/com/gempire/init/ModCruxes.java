package com.gempire.init;

import com.gempire.systems.injection.Crux;
import com.gempire.systems.injection.GemConditions;
import com.gempire.util.CruxType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;

public class ModCruxes {
    private static final int stoneCruxValue = 1;
    public static GemConditions JASPER_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = 2f;
        Item primer = Items.BLAZE_ROD;
        String essences = "yellow-white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.TERRACOTTA.defaultBlockState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RED_SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.AMETHYST_BLOCK.defaultBlockState(), 9, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.QUARTZ_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions LARIMAR_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = -0.5f;
        float gemTemperatureMax = 0.1f;
        Item primer = Items.BLUE_ICE;
        String essences = "blue";

        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ICE.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SNOW_BLOCK.defaultBlockState(), 6, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PACKED_ICE.defaultBlockState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions PERIDOT_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.8f;
        Item primer = Items.EXPERIENCE_BOTTLE;
        String essences = "blue-yellow";

        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRAVEL.defaultBlockState(), 1, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions QUARTZ_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = 2f;
        Item primer = Items.NETHER_STAR;
        String essences = "pink-white";

        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.AMETHYST_BLOCK.defaultBlockState(), 9, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.QUARTZ_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions RUBY_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.2f;
        float gemTemperatureMax = 2f;
        Item primer = Items.NETHERITE_INGOT;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.NETHERRACK.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RED_SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_GOLD_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.defaultBlockState(), 16, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHERITE_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions RUTILE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.2f;
        float gemTemperatureMax = 2f;
        Item primer = Items.FIRE_CHARGE;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RED_SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.REDSTONE_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.REDSTONE_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RED_SANDSTONE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GLOWSTONE.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions AGATE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.DRAGON_BREATH;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.AMETHYST_BLOCK.defaultBlockState(), 9, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.QUARTZ_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions AQUAMARINE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 1f;
        Item primer = Items.GHAST_TEAR;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRAVEL.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PRISMARINE.defaultBlockState(), 7, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SOUL_SAND.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions BISMUTH_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.NETHERITE_SWORD;
        String essences = "pink-blue";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions BIXBITE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.BONE_BLOCK;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RED_SANDSTONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.REDSTONE_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions LAPIS_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 0.8f;
        Item primer = Items.NAUTILUS_SHELL;
        String essences = "blue";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PRISMARINE.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.LAPIS_ORE.defaultBlockState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DARK_PRISMARINE.defaultBlockState(), 9, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.LAPIS_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions NEPHRITE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.95f;
        float gemTemperatureMax = 1f;
        Item primer = Items.WITHER_ROSE;
        String essences = "blue-yellow";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SAND.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.HONEY_BLOCK.defaultBlockState(), 16, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.WHITE_CONCRETE.defaultBlockState(), 3, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.defaultBlockState(), 16, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions SAPPHIRE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = 0.7f;
        Item primer = Items.ENDER_EYE;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ICE.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PACKED_ICE.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SNOW_BLOCK.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions SPODUMENE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 0.8f;
        Item primer = Items.HEART_OF_THE_SEA;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.BONE_BLOCK.defaultBlockState(), 6, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.defaultBlockState(), 16, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.BRAIN_CORAL.defaultBlockState(), 6, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GLOWSTONE.defaultBlockState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions TOPAZ_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.GOLD_BLOCK;
        String essences = "yellow";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COAL_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SAND.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANDESITE.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_ORE.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.RAW_GOLD_BLOCK.defaultBlockState(), 5, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GOLD_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions TOURMALINE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.95f;
        float gemTemperatureMax = 1f;
        Item primer = Items.ENCHANTED_GOLDEN_APPLE;
        String essences = "white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.defaultBlockState(), 16, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MYCELIUM.defaultBlockState(), 9, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MOSS_BLOCK.defaultBlockState(), 4, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions EMERALD_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.95f;
        float gemTemperatureMax = 1f;
        Item primer = Items.EMERALD_BLOCK;
        String essences = "blue-yellow";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRAVEL.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.defaultBlockState(), 6, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.EMERALD_ORE.defaultBlockState(), 10, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.EMERALD_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions GARNET_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.0f;
        float gemTemperatureMax = 2f;
        Item primer = Items.WITHER_SKELETON_SKULL;
        String essences = "pink-yellow-white";
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.defaultBlockState(), 16, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 1, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SCULK.defaultBlockState(), 8, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.BONE_BLOCK.defaultBlockState(), 6, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.END_STONE.defaultBlockState(), 8, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions MORGANITE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 0.8f;
        Item primer = Items.PHANTOM_MEMBRANE;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRAVEL.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.CLAY.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANDESITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SOUL_SAND.defaultBlockState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions OBSIDIAN_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.5f;
        float gemTemperatureMax = 2f;
        Item primer = Items.END_CRYSTAL;
        String essences = "white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.OBSIDIAN.defaultBlockState(), 8, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions SPINEL_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1f;
        float gemTemperatureMax = 1.5f;
        Item primer = Items.NETHER_WART;
        String essences = "pink";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRAVEL.defaultBlockState(), stoneCruxValue, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.defaultBlockState(), 6, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.defaultBlockState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.defaultBlockState(), 4, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.COPPER_ORE.defaultBlockState(), 2, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_BLOCK.defaultBlockState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SLIME_BLOCK.defaultBlockState(), 16, CruxType.ORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }

    public static GemConditions ZIRCON_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0.5f;
        float gemTemperatureMax = 1f;
        Item primer = Items.TOTEM_OF_UNDYING;
        String essences = "blue-white";
        gemCruxes.add(new Crux(Blocks.STONE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.GRANITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIORITE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DEEPSLATE.defaultBlockState(), stoneCruxValue, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_ORE.defaultBlockState(), 10, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.defaultBlockState(), 16, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_BLOCK.defaultBlockState(), 16, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, gemTemperatureMin, gemTemperatureMax);
    }
}
