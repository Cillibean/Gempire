package com.gempire.proxy;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelTest;
import com.gempire.client.entity.render.RenderPebble;
import com.gempire.client.entity.render.RenderTestEntity;
import com.gempire.entities.TestEntity;
import com.gempire.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.TEST.get(), RenderTestEntity::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PEBBLE.get(), RenderPebble::new);
    }

    /*@SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }*/
}
