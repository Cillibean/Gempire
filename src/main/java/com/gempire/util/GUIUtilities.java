package com.gempire.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class GUIUtilities {

    public static void drawBackground(PoseStack matrices, AbstractContainerScreen<?> screen, ResourceLocation background) {
        setup(background);
        //screen.blit(matrices, screen.getGuiLeft(), screen.getGuiTop(), 0, 0, screen.width, screen.height);
    }

    public static void bindTexture(ResourceLocation texture) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
    }

    public static void setup(ResourceLocation texture, float red, float green, float blue, float alpha) {
        bindTexture(texture);
        RenderSystem.setShaderColor(red, green, blue, alpha);
    }

    public static void setup(ResourceLocation texture) {
        setup(texture, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
