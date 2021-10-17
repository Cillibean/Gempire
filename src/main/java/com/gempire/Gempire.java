package com.gempire;

import com.gempire.container.GemUIContainer;
import com.gempire.container.ShellContainer;
import com.gempire.entities.TestEntity;
import com.gempire.entities.gems.*;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityNacre;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.*;
import com.gempire.proxy.ClientProxy;
import com.google.common.eventbus.Subscribe;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Gempire.MODID)
public class Gempire
{
    public static final String MODID = "gempire";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static MinecraftServer server;

    public Gempire() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::onClientSetup);

        RegistryHandler.init();
        // Register ourselves for server and other game events we are interested in
        //MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, ClientProxy::onClientSetup);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModFeatures::addOres);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        //MinecraftForge.EVENT_BUS.register(CommonProxy.class);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.TEST.get(), TestEntity.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.PEBBLE.get(), EntityPebble.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.MICA.get(), EntityMica.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.SHALE.get(), EntityShale.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.NACRE.get(), EntityNacre.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.RUBY.get(), EntityRuby.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.SAPPHIRE.get(), EntitySapphire.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.QUARTZ.get(), EntityQuartz.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.JASPER.get(), EntityJasper.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.AGATE.get(), EntityAgate.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.TOPAZ.get(), EntityTopaz.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.OBSIDIAN.get(), EntityObsidian.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.PEARL.get(), EntityPearl.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.NEPHRITE.get(), EntityNephrite.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.SPODUMENE.get(), EntitySpodumene.setCustomAttributes().create());
        });
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.ZIRCON.get(), EntityZircon.setCustomAttributes().create());
        });
        ModPacketHandler.registerPackets();
        ModEntities.setVanillaGems();
        ModEntities.registerCruxes();
        ModFluids.registerFluidBuckets();
        ModAbilities.registerAbilities();
        ModEnchants.registerVanillaEnchantments();
        ModEnchants.registerItemDiscounts();
    }

    @SuppressWarnings("warning")
    @SubscribeEvent
    public void onLootTablesLoad(LootTableLoadEvent event) {
        // Test: /loot give @p loot minecraft:chests/end_city_treasure
        if (event.getName().equals(new ResourceLocation("minecraft", "chests/abandoned_mineshaft"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/underwater_ruin_big"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/pillager_outpost"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/end_city_treasure"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/stronghold_library"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/village_armorer"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/village_toolsmith"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/ruined_portal"))
                || event.getName().equals(new ResourceLocation("minecraft", "chests/desert_pyramid"))
        ) {
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/essence"))).build());
        }
    }

    @SubscribeEvent
    public void onCommandRegister(final RegisterCommandsEvent event){
        ModCommands.registerCommands(event);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("gempire", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        server = event.getServer();
        LOGGER.info("HELLO from server starting");
    }


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
