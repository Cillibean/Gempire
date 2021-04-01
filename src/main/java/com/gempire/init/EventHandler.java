package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    @SubscribeEvent
    public void OnRenderFluidOverlay(RenderBlockOverlayEvent event){

    }
}
