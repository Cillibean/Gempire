package com.gempire.init;

import com.gempire.fluids.ModFluidTypes;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Locale;

import static net.minecraftforge.fluids.FluidInteractionRegistry.addInteraction;

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
        ModEnchants.ENCHANTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
