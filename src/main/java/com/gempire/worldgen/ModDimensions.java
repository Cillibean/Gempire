package com.gempire.worldgen;

import com.gempire.Gempire;
import com.ibm.icu.impl.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {

    public static final ResourceKey<LevelStem> DESOLATE_LANDS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Gempire.MODID, "desolate_lands"));

    public static final ResourceKey<Level> DESOLATE_LANDS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Gempire.MODID, "desolate_lands"));

    public static final ResourceKey<DimensionType> DESOLATE_LANDS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Gempire.MODID, "desolate_lands_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(DESOLATE_LANDS_TYPE, new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                true,
                -64,
                512,
                512,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0f,
                new DimensionType.MonsterSettings(false, false,
                        ConstantInt.of(0), 0)
        ));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        /*NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(
                                        Climate.Parameter.span(0, 1),//temp
                                        Climate.Parameter.span(0, 1),//humid
                                        Climate.Parameter.span(0, 1),//contin
                                        Climate.Parameter.span(0, 1),//eros
                                        Climate.Parameter.span(0, 1),//dep
                                        Climate.Parameter.span(0, 1),//weird
                                        0.0F), biomeRegistry.getOrThrow(ModBiomes.PECULIAR_PRECIPICE)),
                                Pair.of(Climate.parameters(
                                        Climate.Parameter.span(0, 1),//temp
                                        Climate.Parameter.span(0, 1),//humid
                                        Climate.Parameter.span(0, 1),//contin
                                        Climate.Parameter.span(0, 1),//eros
                                        Climate.Parameter.span(0, 1),//dep
                                        Climate.Parameter.span(0, 1),//weird
                                        0.0F), biomeRegistry.getOrThrow(ModBiomes.DISTANT_FOREST)),
                                Pair.of(Climate.parameters(
                                        Climate.Parameter.span(0, 1),//temp
                                        Climate.Parameter.span(0, 1),//humid
                                        Climate.Parameter.span(0, 1),//contin
                                        Climate.Parameter.span(0, 1),//eros
                                        Climate.Parameter.span(0, 1),//dep
                                        Climate.Parameter.span(0, 1),//weird
                                        0.0F), biomeRegistry.getOrThrow(ModBiomes.ABNORMAL_BEACH)),
                                Pair.of(Climate.parameters(
                                        Climate.Parameter.span(0, 1),//temp
                                        Climate.Parameter.span(0, 1),//humid
                                        Climate.Parameter.span(0, 1),//contin
                                        Climate.Parameter.span(0, 1),//eros
                                        Climate.Parameter.span(0, 1),//dep
                                        Climate.Parameter.span(0, 1),//weird
                                        0.0F), biomeRegistry.getOrThrow(ModBiomes.UNEARTHLY_JUNGLE))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
*/
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.DESOLATE_LANDS_TYPE), wrappedChunkGenerator);

        context.register(DESOLATE_LANDS_KEY, stem);
    }
}
