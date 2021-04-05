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

    public static GemConditions RUBY_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 1.2f;
        float gemTemperatureMax = 2f;
        Item primer = Items.NETHERITE_INGOT;
        Fluid[] essences = new Fluid[]{
                ModFluids.PINK_ESSENCE.get()
        };
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.MAGMA_BLOCK.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHERRACK.getDefaultState(), 1, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHERITE_BLOCK.getDefaultState(), 10, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.NETHER_GOLD_ORE.getDefaultState(), 4, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS.getDefaultState(), 7, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions SAPPHIRE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        float gemTemperatureMin = 0f;
        float gemTemperatureMax = .7f;
        Item primer = Items.ENDER_EYE;
        Fluid[] essences = new Fluid[]{
                ModFluids.BLUE_ESSENCE.get(), ModFluids.WHITE_ESSENCE.get()
        };
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.ICE.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.PACKED_ICE.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.SNOW_BLOCK.getDefaultState(), 5, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.IRON_ORE.getDefaultState(), 3, CruxType.MINERAL, gemTemperatureMin, gemTemperatureMax));
        gemCruxes.add(new Crux(Blocks.DIAMOND_BLOCK.getDefaultState(), 10, CruxType.INORGANIC, gemTemperatureMin, gemTemperatureMax));
        return new GemConditions(gemCruxes, essences, primer, 1, gemTemperatureMin, gemTemperatureMax);
    }

    public static final GemConditions PEBBLE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        Fluid[] essences = new Fluid[]{
                ModFluids.PINK_ESSENCE.get()
        };
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC));
        return new GemConditions(gemCruxes, essences, 1);
    }

    public static final GemConditions MICA_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        Fluid[] essences = new Fluid[]{
                ModFluids.YELLOW_ESSENCE.get()
        };
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC));
        return new GemConditions(gemCruxes, essences, 1);
    }

    public static final GemConditions SHALE_CONDITIONS(){
        ArrayList<Crux> gemCruxes = new ArrayList<>();
        Fluid[] essences = new Fluid[]{
                ModFluids.BLUE_ESSENCE.get()
        };
        gemCruxes.add(new Crux(Blocks.STONE.getDefaultState(), 2, CruxType.INORGANIC));
        return new GemConditions(gemCruxes, essences, 1);
    }
}
