package com.gempire.client.screen;

import com.gempire.container.InjectorContainer;
import com.gempire.container.TankContainer;
import com.gempire.init.ModFluids;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestInject;
import com.gempire.networking.C2SRequestUpdateInjectorValves;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.network.play.client.CUpdateCommandBlockPacket;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.CommandBlockLogic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class InjectorScreen extends ContainerScreen<InjectorContainer> {
    public static final ResourceLocation INJECTOR_GUI = new ResourceLocation("gempire:textures/gui/injector_gui.png");
    public static final ResourceLocation FLUID_GUI = new ResourceLocation("gempire:textures/gui/injector_gui_fluid.png");
    public static final ResourceLocation HALO_GUI_PINK = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_pink.png");
    public static final ResourceLocation HALO_GUI_BLUE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_blue.png");
    public static final ResourceLocation HALO_GUI_YELLOW = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_yellow.png");
    public static final ResourceLocation HALO_GUI_WHITE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_overlay_white.png");
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("gempire:textures/gui/injector_gui_fluid_button.png");
    public static final ResourceLocation INJECT_BUTTON_TEXTURE = new ResourceLocation("gempire:textures/gui/inject_button.png");

    public InjectorScreen(InjectorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 165;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.addButton(new ImageButton(x + 83, y + 58, 51, 12, 0, 0, 0, InjectorScreen.INJECT_BUTTON_TEXTURE, 51, 12, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestInject(this.container.injector.getPos()));
        }));
        this.addButton(new ImageButton(x + 101, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("pink", this.container.injector.getPos()));
        }));
        this.addButton(new ImageButton(x + 109, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14,(p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("blue", this.container.injector.getPos()));
        }));
        this.addButton(new ImageButton(x + 117, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14,(p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("yellow", this.container.injector.getPos()));
        }));
        this.addButton(new ImageButton(x + 125, y + 39, 6, 14, 0, 0, 0, InjectorScreen.BUTTON_TEXTURE, 6, 14,(p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateInjectorValves("white", this.container.injector.getPos()));
        }));
    }



    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.minecraft.getTextureManager().bindTexture(InjectorScreen.INJECTOR_GUI);
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
        FluidStack pinkFluid = this.container.injector.getTankFromValue(0).getFluid();
        FluidStack blueFluid = this.container.injector.getTankFromValue(1).getFluid();
        FluidStack yellowFluid = this.container.injector.getTankFromValue(2).getFluid();
        FluidStack whiteFluid = this.container.injector.getTankFromValue(3).getFluid();
        if(pinkFluid.getFluid() != Fluids.EMPTY)
        {
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.FLUID_GUI);
            int xPink = x + 102;
            int yPink = y + 40;
            int fluidStored = 12 * pinkFluid.getAmount() / this.container.injector.getCapacity();
            int color = 0xFF7FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.color4f(r, g, b, 1);
            this.blit(matrixStack, xPink, yPink + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(blueFluid.getFluid() != Fluids.EMPTY)
        {
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.FLUID_GUI);
            int xBlue = x + 110;
            int yBlue = y + 40;
            int fluidStored = 12 * blueFluid.getAmount() / this.container.injector.getCapacity();
            int color = 0x697FFF;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.color4f(r, g, b, 1);
            this.blit(matrixStack, xBlue, yBlue + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(yellowFluid.getFluid() != Fluids.EMPTY)
        {
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.FLUID_GUI);
            int xYellow = x + 118;
            int yYellow = y + 40;
            int fluidStored = 12 * yellowFluid.getAmount() / this.container.injector.getCapacity();
            int color = 0xFFFC84;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.color4f(r, g, b, 1);
            this.blit(matrixStack, xYellow, yYellow + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        if(whiteFluid.getFluid() != Fluids.EMPTY)
        {
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.FLUID_GUI);
            int xWhite = x + 126;
            int yWhite = y + 40;
            int fluidStored = 12 * whiteFluid.getAmount() / this.container.injector.getCapacity();
            int color = 0xD8D8D8;
            float r = ((color & 16711680) >> 16) / 255f;
            float g = ((color & 65280) >> 8) / 255f;
            float b = ((color & 255) >> 0) / 255f;
            RenderSystem.color4f(r, g, b, 1);
            this.blit(matrixStack, xWhite, yWhite + (12 - fluidStored), 0, 0, 4, fluidStored);
        }
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        if(this.container.injector.pinkOpen){
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.HALO_GUI_PINK);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        if(this.container.injector.blueOpen){
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.HALO_GUI_BLUE);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        if(this.container.injector.yellowOpen){
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.HALO_GUI_YELLOW);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
        if(this.container.injector.whiteOpen){
            this.minecraft.getTextureManager().bindTexture(InjectorScreen.HALO_GUI_WHITE);
            this.blit(matrixStack, x, y, 0, 0, this.width, this.height);
        }
    }
}
