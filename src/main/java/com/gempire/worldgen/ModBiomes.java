package com.gempire.worldgen;

import com.gempire.Gempire;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> STRAWBERRY_FIELDS = register("strawberry_fields");
    public static final ResourceKey<Biome> PURPLE_KINDERGARTEN = register("purple_kindergarten");
    public static final ResourceKey<Biome> RED_KINDERGARTEN = register("red_kindergarten");
    public static final ResourceKey<Biome> BLUE_KINDERGARTEN = register("blue_kindergarten");
    public static final ResourceKey<Biome> GREY_KINDERGARTEN = register("grey_kindergarten");
    public static final ResourceKey<Biome> YELLOW_KINDERGARTEN = register("yellow_kindergarten");
    public static final ResourceKey<Biome> MASK_ISLAND = register("mask_island");

    public static final ResourceKey<Biome> DISTANT_FOREST = register("distant_forest");
    public static final ResourceKey<Biome> PECULIAR_PRECIPICE = register("peculiar_precipice");
    public static final ResourceKey<Biome> ABNORMAL_BEACH = register("abnormal_beach");
    public static final ResourceKey<Biome> UNEARTHLY_JUNGLE = register("unearthly_jungle");
    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Gempire.MODID, name));
    }
}
