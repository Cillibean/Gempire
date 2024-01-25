package com.gempire.client.screen;

import com.gempire.container.IncubatorContainer;
import com.gempire.container.ShellContainer;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class IncubatorScreen extends AbstractContainerScreen<IncubatorContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/base_incubator.png");
    public IncubatorScreen(IncubatorContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 176;
    }

    @Override
    public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
    }

    @Override
    protected void renderLabels(GuiGraphics matrixStack, int x1, int y1) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        GUIUtilities.setup(GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        matrixStack.blit(GUI, x, y, 0, 0, this.imageWidth, this.imageHeight);
        if (menu.isCrafting()) {
            matrixStack.blit(GUI, x + 80, y + 35, 177, 0, menu.getScaledProgress(), 16);
        }
    }
}
