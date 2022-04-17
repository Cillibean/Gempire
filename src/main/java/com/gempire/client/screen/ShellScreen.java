package com.gempire.client.screen;

import com.gempire.container.InjectorContainer;
import com.gempire.container.ShellContainer;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestDumpFluidsInjector;
import com.gempire.networking.C2SRequestInject;
import com.gempire.networking.C2SRequestUpdateInjectorValves;
import com.gempire.systems.machine.gui.EnergyMeter;
import com.gempire.systems.machine.gui.MeterSize;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;


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
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        EnergyMeter.RenderBattery(this, matrixStack, menu.shell, menu.shell, x + 8, y + 4, MeterSize.NORMAL);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int x1, int y1) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        //EnergyMeter.RenderBattery(this, matrixStack, container.shell, container.shell, x + 8, y + 4, MeterSize.NORMAL);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.minecraft.getTextureManager().bind(ShellScreen.GUI);
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        if(this.menu.shell.essenceMarker){
            this.minecraft.getTextureManager().bind(ShellScreen.LIGHT);
            this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        }
        //EnergyMeter.RenderBattery(this, matrixStack, container.shell, container.shell, x + 8, y + 4, MeterSize.NORMAL);
    }
}
