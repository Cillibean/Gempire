package com.gempire.client.screen;

import com.gempire.container.InjectorContainer;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestDumpFluidsInjector;
import com.gempire.networking.C2SRequestInject;
import com.gempire.networking.C2SRequestUpdateInjectorValves;
import com.gempire.systems.machine.gui.EnergyMeter;
import com.gempire.systems.machine.gui.MeterSize;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;


public class InjectorScreen extends AbstractContainerScreen<InjectorContainer> {
    public static final ResourceLocation INJECTOR_GUI = new ResourceLocation("gempire:textures/gui/injector_gui.png");
    public static final ResourceLocation FLUID_GUI = new ResourceLocation("gempire:textures/gui/injector_gui_fluid.png");
    public static final ResourceLocation HALO_GUI_PINK = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_pink.png");
    public static final ResourceLocation HALO_GUI_BLUE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_blue.png");
    public static final ResourceLocation HALO_GUI_YELLOW = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_yellow.png");
    public static final ResourceLocation HALO_GUI_WHITE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_white.png");
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_button.png");
    public static final ResourceLocation INJECT_BUTTON_TEXTURE = new ResourceLocation("gempire:textures/gui/inject_button.png");
    public static final ResourceLocation DUMP_BUTTON = new ResourceLocation("gempire:textures/gui/injector_dump_button.png");

    public InjectorScreen(InjectorContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 165;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        addRenderableWidget(new ImageButton(x + 83, y + 58, 51, 12, 0, 0, 0, InjectorScreen.INJECT_BUTTON_TEXTURE, 51, 12, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestInject(this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(x + 133, y + 38, 7, 20, 0, 0, 0, InjectorScreen.DUMP_BUTTON, 7, 20, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestDumpFluidsInjector(this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(x + 101, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("pink", this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(x + 109, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14,(p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("blue", this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(x + 117, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14,(p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("yellow", this.menu.injector.getBlockPos()));
        }));
        addRenderableWidget(new ImageButton(x + 125, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14,(p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("white", this.menu.injector.getBlockPos()));
        }));
    }



    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        GUIUtilities.setup(INJECTOR_GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        FluidStack pinkFluid = this.menu.injector.getTankFromValue(0).getFluid();
        FluidStack blueFluid = this.menu.injector.getTankFromValue(1).getFluid();
        FluidStack yellowFluid = this.menu.injector.getTankFromValue(2).getFluid();
        FluidStack whiteFluid = this.menu.injector.getTankFromValue(3).getFluid();
        if(pinkFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int xPink = x + 102;
            int yPink = y + 40;
            int fluidStored = 12 * pinkFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0xFF7FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            this.blit(matrixStack, xPink, yPink + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(blueFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int xBlue = x + 110;
            int yBlue = y + 40;
            int fluidStored = 12 * blueFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0x697FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            this.blit(matrixStack, xBlue, yBlue + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(yellowFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int xYellow = x + 118;
            int yYellow = y + 40;
            int fluidStored = 12 * yellowFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0xFFFC84;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            this.blit(matrixStack, xYellow, yYellow + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(whiteFluid.getFluid() != Fluids.EMPTY)
        {
            GUIUtilities.setup(FLUID_GUI);
            int xWhite = x + 126;
            int yWhite = y + 40;
            int fluidStored = 12 * whiteFluid.getAmount() / this.menu.injector.getCapacity();
            int color = 0xD8D8D8;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.setShaderColor(r, g, b, 1);
            this.blit(matrixStack, xWhite, yWhite + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        if(this.menu.injector.pinkOpen){
            GUIUtilities.setup(HALO_GUI_PINK);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        if(this.menu.injector.blueOpen){
            GUIUtilities.setup(HALO_GUI_BLUE);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        if(this.menu.injector.yellowOpen){
            GUIUtilities.setup(HALO_GUI_YELLOW);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        if(this.menu.injector.whiteOpen){
            GUIUtilities.setup(HALO_GUI_WHITE);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        EnergyMeter.RenderBattery(this, matrixStack, menu.injector, menu.injector, x + 97, y + 32, MeterSize.INJECTOR);
    }
}
