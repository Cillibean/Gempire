package com.gempire;

import com.gempire.entities.gems.*;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityNacre;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.server.MinecraftServer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.server.ServerStartingEvent;
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

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::EntityAttributes);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);

        RegistryHandler.init();
        // Register ourselves for server and other game events we are interested in
        //MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, ClientProxy::onClientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());


        //MinecraftForge.EVENT_BUS.register(CommonProxy.class);
    }

    public void EntityAttributes(final EntityAttributeCreationEvent event){
        event.put(ModEntities.PEBBLE.get(), EntityPebble.registerAttributes().build());

        event.put(ModEntities.MICA.get(), EntityMica.registerAttributes().build());

        event.put(ModEntities.SHALE.get(), EntityShale.registerAttributes().build());

        event.put(ModEntities.NACRE.get(), EntityNacre.registerAttributes().build());

        event.put(ModEntities.RUBY.get(), EntityRuby.registerAttributes().build());

        event.put(ModEntities.SAPPHIRE.get(), EntitySapphire.registerAttributes().build());

        event.put(ModEntities.QUARTZ.get(), EntityQuartz.registerAttributes().build());

        event.put(ModEntities.JASPER.get(), EntityJasper.registerAttributes().build());

        event.put(ModEntities.AGATE.get(), EntityAgate.registerAttributes().build());

        event.put(ModEntities.TOPAZ.get(), EntityTopaz.registerAttributes().build());

        event.put(ModEntities.OBSIDIAN.get(), EntityObsidian.registerAttributes().build());

        event.put(ModEntities.PEARL.get(), EntityPearl.registerAttributes().build());

        event.put(ModEntities.NEPHRITE.get(), EntityNephrite.registerAttributes().build());

        event.put(ModEntities.SPODUMENE.get(), EntitySpodumene.registerAttributes().build());

        event.put(ModEntities.ZIRCON.get(), EntityZircon.registerAttributes().build());

        event.put(ModEntities.AQUAMARINE.get(), EntityAquamarine.registerAttributes().build());

        event.put(ModEntities.BISMUTH.get(), EntityBismuth.registerAttributes().build());

        event.put(ModEntities.BIXBITE.get(), EntityBixbite.registerAttributes().build());

        event.put(ModEntities.GARNET.get(), EntityGarnet.registerAttributes().build());

        event.put(ModEntities.EMERALD.get(), EntityEmerald.registerAttributes().build());

        event.put(ModEntities.LAPIS.get(), EntityLapis.registerAttributes().build());

        event.put(ModEntities.LARIMAR.get(), EntityLarimar.registerAttributes().build());

        event.put(ModEntities.MORGANITE.get(), EntityMorganite.registerAttributes().build());

        event.put(ModEntities.PERIDOT.get(), EntityPeridot.registerAttributes().build());

        event.put(ModEntities.RUTILE.get(), EntityRutile.registerAttributes().build());

        event.put(ModEntities.SPINEL.get(), EntitySpinel.registerAttributes().build());

        event.put(ModEntities.TOURMALINE.get(), EntityTourmaline.registerAttributes().build());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModPacketHandler.registerPackets();
        ModEntities.setVanillaGems();
        ModEntities.registerCruxes();
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
            event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(MODID, "chests/essence"))).build());
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
    public void onServerStarting(ServerStartingEvent event) {
        server = event.getServer();
        LOGGER.info("HELLO from server starting");
    }
}
