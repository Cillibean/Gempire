package com.gempire.proxy;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelTest;
import com.gempire.client.entity.render.*;
import com.gempire.client.screen.*;
import com.gempire.client.ter.ShellTER;
import com.gempire.entities.TestEntity;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.init.ModEntities;
import com.gempire.init.ModTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.opengl.NVPathRenderingSharedEdge;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        //RenderingRegistry.registerEntityRenderingHandler(ModEntities.TEST.get(), RenderTestEntity::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PEBBLE.get(), RenderPebble::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MICA.get(), RenderMica::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SHALE.get(), RenderShale::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NACRE.get(), RenderNacre::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.RUBY.get(), RenderRuby::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SAPPHIRE.get(), RenderSapphire::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.QUARTZ.get(), RenderQuartz::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.JASPER.get(), RenderJasper::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.AGATE.get(), RenderAgate::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.TOPAZ.get(), RenderTopaz::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.OBSIDIAN.get(), RenderObsidian::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PEARL.get(), RenderPearl::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NEPHRITE.get(), RenderNephrite::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPODUMENE.get(), RenderSpodumene::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ZIRCON.get(), RenderZircon::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ICE_SHARD.get(), (manager) -> new RenderIceShard(manager, Minecraft.getInstance().getItemRenderer()));

        ScreenManager.registerFactory(ModContainers.TANK_CONTAINER.get(), TankScreen::new);
        ScreenManager.registerFactory(ModContainers.INJECTOR_CONTAINER.get(), InjectorScreen::new);
        ScreenManager.registerFactory(ModContainers.GEM_UI_CONTAINER.get(), GemUIScreen::new);
        ScreenManager.registerFactory(ModContainers.SHELL_CONTAINER.get(), ShellScreen::new);
        ScreenManager.registerFactory(ModContainers.PEARL_UI_CONTAINER.get(), PearlUIScreen::new);
        ScreenManager.registerFactory(ModContainers.ZIRCON_UI_CONTAINER.get(), ZirconUIScreen::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.POWER_CRYSTAL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.SHELL_BLOCK.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.ORANGE_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.YELLOW_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.LIME_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.PINK_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.GRAY_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.CYAN_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.PURPLE_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLUE_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BROWN_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.RED_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLACK_CHROMA_CRYSTAL.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(ModBlocks.ICE_SPIKE.get(), RenderType.getCutout());

        ClientRegistry.bindTileEntityRenderer(ModTE.SHELL_TE.get(), ShellTER::new);
    }
}
