package com.gempire.init;


import com.gempire.Gempire;
import com.gempire.worldgen.feature.DirectionalBlockFeature;
import com.gempire.worldgen.feature.configuration.DirectionalBlockFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Gempire.MODID);

    public final static RegistryObject<Feature<DirectionalBlockFeatureConfiguration>> DIRECTIONAL_BLOCK_FEATURE = FEATURES.register("directional_block",
            () -> new DirectionalBlockFeature(DirectionalBlockFeatureConfiguration.CODEC));
}
