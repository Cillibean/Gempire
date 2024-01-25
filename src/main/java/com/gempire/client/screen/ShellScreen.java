package com.gempire.client.screen;

import com.gempire.container.ShellContainer;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;


public class ShellScreen extends AbstractContainerScreen<ShellContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/base_shell.png");
    public static final ResourceLocation LIGHT = new ResourceLocation("gempire:textures/gui/shell_light.png");

    public ShellScreen(ShellContainer screenContainer, Inventory inv, Component titleIn) {
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
        //EnergyMeter.RenderBattery(this, matrixStack, container.shell, container.shell, x + 8, y + 4, MeterSize.NORMAL);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        GUIUtilities.setup(GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        matrixStack.blit(GUI, x, y, 0, 0, this.imageWidth, this.imageHeight);
        //EnergyMeter.RenderBattery(this, matrixStack, container.shell, container.shell, x + 8, y + 4, MeterSize.NORMAL);
    }
}
