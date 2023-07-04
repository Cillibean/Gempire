package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.datagen.loot.ModBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
        /*generator.addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                (DataProvider.Factory<ModLootTableProvider>) output -> new ModLootTableProvider(
                        output,
                        // Specify registry names of tables that are required to generate, or can leave empty
                        Collections.emptySet(),
                        // Sub providers which generate the loot
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTables::new,
                                // Loot table generator for the 'empty' param set
                                LootContextParamSets.EMPTY
                        ))
                )
        );*/
    }
}
