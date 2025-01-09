package com.gempire.init;

import com.gempire.fluids.ModFluidTypes;
import com.google.common.eventbus.EventBus;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Locale;

import static net.minecraftforge.fluids.FluidInteractionRegistry.addInteraction;

public class RegistryHandler {

    public static void init() {
        // attach DeferredRegister to the event bus
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModSounds.SOUNDS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModFluids.FLUIDS.register(bus);
        ModFluids.FLUID_TYPES.register(bus);
        ModFluidTypes.register(bus);
        ModTE.TILE_ENTITIES.register(bus);
        ModContainers.CONTAINERS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModFeatures.FEATURES.register(bus);
        ModEffects.MOB_EFFECTS.register(bus);
        ModEnchants.ENCHANTS.register(bus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(bus);
        ModPotions.POTIONS.register(bus);
        ModStructures.DEFERRED_REGISTRY_STRUCTURE.register(bus);
        ModStructures.STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER.register(bus);
        ModParticles.PARTICLE_TYPES.register(bus);
        ModBannerPatterns.BANNER_PATTERNS.register(bus);
    }
}
