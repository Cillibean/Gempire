package com.gempire.worldgen;

import com.gempire.Gempire;
import com.gempire.init.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Biomes;
import terrablender.api.RegionType;

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

    public static void bootstrap(BootstapContext<Biome> context) {

    }

    public static void globalDesolateGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome peculiarPrecipice(BootstapContext<Biome> context) {
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalDesolateGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(12440534)
                        .waterFogColor(13293272)
                        .skyColor(13090728)
                        .grassColorOverride(12111496)
                        .foliageColorOverride(11124871)
                        .fogColor(14274228)
                        //.backgroundMusic(Musics.createGameMusic((Holder<SoundEvent>) ModSounds.REMNANTS.get()))
                        .build())
                .build();
    }

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Gempire.MODID, name));
    }
}
