package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.fluids.NewFluidTest;
import com.gempire.items.ItemGem;
import com.gempire.util.GemPlacements;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {

    public static void init() {
        // attach DeferredRegister to the event bus
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //ModFluids.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTE.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //TODO: TEST
        NewFluidTest fluidtest = new NewFluidTest();
    }

}
