package com.gempire.init;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {

    public static void init() {
        // attach DeferredRegister to the event bus
        MushItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MushBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MushTE.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
