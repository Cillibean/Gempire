package com.gempire.init;

import com.gempire.fluids.ModFluidTypes;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Locale;

public class RegistryHandler {

    public static void init() {
        // attach DeferredRegister to the event bus
        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModFluids.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModFluids.FLUID_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModFluidTypes.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTE.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModContainers.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModFeatures.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEffects.MOB_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModPlacedFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
