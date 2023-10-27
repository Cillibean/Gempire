package com.gempire.aura;

import com.gempire.Gempire;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.awt.*;

public class AuraOverlay {
    private static final ResourceLocation PINK_OVERLAY = new ResourceLocation(Gempire.MODID, "textures/misc/pink_aura_overlay.png");
    private static final ResourceLocation YELLOW_OVERLAY = new ResourceLocation(Gempire.MODID, "textures/misc/yellow_aura_overlay.png");
    private static final ResourceLocation BLUE_OVERLAY = new ResourceLocation(Gempire.MODID, "textures/misc/blue_aura_overlay.png");
    private static final ResourceLocation WHITE_OVERLAY = new ResourceLocation(Gempire.MODID, "textures/misc/white_aura_overlay.png");


    public static final IGuiOverlay HUD_AURA = (((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();

        if (ClientAuraData.getPlayerAura() == 1) {
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderTexture(0, PINK_OVERLAY);
            GuiComponent.blit(poseStack, 0, 0, 0, 0, 2000, 2000, 2000, 2000);
        } else if (ClientAuraData.getPlayerAura() == 2) {
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderTexture(0, YELLOW_OVERLAY);
            GuiComponent.blit(poseStack, 0, 0, 0, 0, 2000, 2000, 2000, 2000);
        } else if (ClientAuraData.getPlayerAura() == 3) {
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderTexture(0, BLUE_OVERLAY);
            GuiComponent.blit(poseStack, 0, 0, 0, 0, 2000, 2000, 2000, 2000);
        } else if (ClientAuraData.getPlayerAura() == 4) {
            //RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            //RenderSystem.setShaderTexture(0, WHITE_OVERLAY);
            //GuiComponent.blit(poseStack, 0, 0, 0, 0, 2000, 2000, 2000, 2000);
        }
    }));
}
