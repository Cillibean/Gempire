package com.gempire.client.screen;

import com.gempire.container.InjectorContainer;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.OpenTank;
import com.gempire.networking.PageChange;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;


public class InjectorScreen extends AbstractContainerScreen<InjectorContainer> {
    public static ResourceLocation INJECTOR_GUI = null;
    public static final ResourceLocation FLUID_GUI = new ResourceLocation("gempire:textures/gui/injector_gui_fluid.png");
    public static final ResourceLocation FLUID_LINES = new ResourceLocation("gempire:textures/gui/fluid_lines.png");
    public static final ResourceLocation TANK_OPEN = new ResourceLocation("gempire:textures/gui/tank_open.png");
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_button.png");
    public static final ResourceLocation HALO_GUI_BLUE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_blue.png");
    public static final ResourceLocation HALO_GUI_YELLOW = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_yellow.png");
    public static final ResourceLocation HALO_GUI_WHITE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_white.png");

    public InjectorScreen(InjectorContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 136;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int xPink = x + 10;
        int yPink = y + 29;
        int xBlue = x + 18;
        int yBlue = y + 29;
        int xYellow = x + 26;
        int yYellow = y + 29;
        int xWhite = x + 34;
        int yWhite = y + 29;
        addRenderableWidget(new ImageButton(xPink, yPink, 4, 17, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new OpenTank(0, this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(xBlue, yBlue, 4, 17, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new OpenTank(1, this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(xYellow, yYellow, 4, 17, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new OpenTank(2, this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(xWhite, yWhite, 4, 17, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new OpenTank(3, this.menu.injector.getBlockPos()));
        }));
    }



    @Override
    public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics matrixStack, int x, int y) {

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        if (this.menu.injector.getLevel().getBlockState(this.menu.injector.getBlockPos().above().above()).getBlock() == ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get()) {
            INJECTOR_GUI = new ResourceLocation("gempire:textures/gui/injector_tier_2.png");
        } else {
            INJECTOR_GUI = new ResourceLocation("gempire:textures/gui/injector_tier_1.png");
        }
        GUIUtilities.setup(INJECTOR_GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        matrixStack.blit(INJECTOR_GUI, x, y, 0, 0, this.imageWidth, this.imageHeight);
        FluidStack pinkFluid = this.menu.injector.getTankFromValue(0).getFluid();
        FluidStack blueFluid = this.menu.injector.getTankFromValue(1).getFluid();
        FluidStack yellowFluid = this.menu.injector.getTankFromValue(2).getFluid();
        FluidStack whiteFluid = this.menu.injector.getTankFromValue(3).getFluid();
        int xPink = x + 10;
        int yPink = y + 28;
        int xBlue = x + 18;
        int yBlue = y + 28;
        int xYellow = x + 26;
        int yYellow = y + 28;
        int xWhite = x + 34;
        int yWhite = y + 28;
        if(pinkFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int fluidStored = 17 * pinkFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0xFF7FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(FLUID_GUI, xPink, yPink + (17 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(blueFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int fluidStored = 17 * blueFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0x697FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(FLUID_GUI, xBlue, yBlue + (17 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(yellowFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int fluidStored = 17 * yellowFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0xFFFC84;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(FLUID_GUI, xYellow, yYellow + (17 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(whiteFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int fluidStored = 17 * whiteFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0xD8D8D8;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(FLUID_GUI, xWhite, yWhite + (17 - fluidStored), 0, 0, 4, fluidStored);
        }
        if (menu.injector.pinkOpen) {
            GUIUtilities.setup(TANK_OPEN);
            int color = 0xFF7FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(TANK_OPEN, xPink+1, yPink+20, 0, 0, 2, 2);
        }
        if (menu.injector.blueOpen) {
            GUIUtilities.setup(TANK_OPEN);
            int color = 0x697FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(TANK_OPEN, xBlue+1, yBlue+20, 0, 0, 2, 2);
        }
        if (menu.injector.yellowOpen) {
            GUIUtilities.setup(TANK_OPEN);
            int color = 0xFFFC84;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(TANK_OPEN, xYellow+1, yYellow+20, 0, 0, 2, 2);
        }
        if (menu.injector.whiteOpen) {
            GUIUtilities.setup(TANK_OPEN);
            int color = 0xD8D8D8;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            matrixStack.blit(TANK_OPEN, xWhite+1, yWhite+20, 0, 0, 2, 2);
        }
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        GUIUtilities.setup(FLUID_LINES);
        matrixStack.blit(FLUID_LINES, xPink, yPink, 0, 0, 28, 17);
    }
}
