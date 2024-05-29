package com.gempire.init;


import com.gempire.Gempire;
import com.gempire.worldgen.feature.DesolateKelpFeature;
import com.gempire.worldgen.feature.DesolateSeagrassFeature;
import com.gempire.worldgen.feature.DirectionalBlockFeature;
import com.gempire.worldgen.feature.configuration.DirectionalBlockFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Gempire.MODID);

    public final static RegistryObject<Feature<DirectionalBlockFeatureConfiguration>> DIRECTIONAL_BLOCK_FEATURE = FEATURES.register("directional_block",
            () -> new DirectionalBlockFeature(DirectionalBlockFeatureConfiguration.CODEC));

    public final static RegistryObject<Feature<NoneFeatureConfiguration>> DESOLATE_KELP = FEATURES.register("desolate_kelp",
            () -> new DesolateKelpFeature(NoneFeatureConfiguration.CODEC));

    public final static RegistryObject<Feature<ProbabilityFeatureConfiguration>> DESOLATE_SEAGRASS = FEATURES.register("desolate_seagrass",
            () -> new DesolateSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));
}
