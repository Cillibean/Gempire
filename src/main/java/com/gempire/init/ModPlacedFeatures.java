package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> TUNGSTEN_PLACED_KEY = createKey("tungsten_placed");
    public static final ResourceKey<PlacedFeature> THULITE_PLACED_KEY = createKey("thulite_placed");
    public static final ResourceKey<PlacedFeature> ANATASE_PLACED_KEY = createKey("anatase_placed");
    public static final ResourceKey<PlacedFeature> ELECTRUM_PLACED_KEY = createKey("electrum_placed");
    public static final ResourceKey<PlacedFeature> PLATINUM_PLACED_KEY = createKey("platinum_placed");
    public static final ResourceKey<PlacedFeature> PYRITE_PLACED_KEY = createKey("pyrite_placed");
    public static final ResourceKey<PlacedFeature> CRYSTAL_PLACED_KEY = createKey("crystal_placed");

    public static final ResourceKey<PlacedFeature> DISTANT_PLACED_KEY = createKey("kaleidoscope_placed");

    public static final ResourceKey<PlacedFeature> BERRY_PLACED_KEY = createKey("shock_berry_placed");



    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, TUNGSTEN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TUNGSTEN_ORE_KEY),
                commonOrePlacement(12, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, THULITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.THULITE_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ANATASE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ANATASE_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ELECTRUM_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ELECTRUM_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, PLATINUM_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLATINUM_ORE_KEY),
                commonOrePlacement(7, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, PYRITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PYRITE_ORE_KEY),
                commonOrePlacement(12, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        /*
        register(context, CRYSTAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYSTAL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.CRYSTAL_SAPLING.get()));

        register(context, DISTANT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DISTANT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.DISTANT_SAPLING.get()));*/
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Gempire.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
