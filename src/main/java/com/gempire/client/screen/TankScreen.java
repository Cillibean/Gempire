package com.gempire.client.screen;

import com.gempire.container.TankContainer;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestDumpFluidsInjector;
import com.gempire.networking.C2SRequestDumpFluidsTank;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;


public class TankScreen extends AbstractContainerScreen<TankContainer> {
    public static final ResourceLocation TANK_GUI = new ResourceLocation("gempire:textures/gui/tank_gui.png");
    public static final ResourceLocation TANK_FOREGROUND = new ResourceLocation("gempire:textures/gui/tank_foreground.png");
    public static final ResourceLocation DUMP_BUTTON = new ResourceLocation("gempire:textures/gui/dump_button.png");

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        addRenderableWidget(new ImageButton(x + 79, y + 32, 39, 12, 0, 0, 0, TankScreen.DUMP_BUTTON, 39, 12, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestDumpFluidsTank(this.menu.tank.getBlockPos()));
        }));
    }

    public TankScreen(TankContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 160;
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {

    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        GUIUtilities.setup(TANK_GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        /*FluidStack fluid = this.menu.tank.getFluid();
        if(fluid.getFluid() != Fluids.EMPTY)
        {
            ResourceLocation stillLocation = fluid.getFluid().getFluidType(this.menu.tank.getFluid());
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillLocation);
            ResourceLocation spriteLocation = sprite.getName();
            GUIUtilities.setup(new ResourceLocation(spriteLocation.getNamespace(), "textures/" + spriteLocation.getPath() + ".png"));
            int x2 = 58 + x;
            int y2 = 9 + y;
            int fluidStored = 57 * fluid.getAmount() / 4000;
            if(fluid.getFluid() == Fluids.WATER){
                int color = FluidStack.EMPTY.getAmount();
                float r = ((color & 16711680) >> 16) / 255f;
                float g = ((color & 65280) >> 8) / 255f;
                float b = ((color & 255) >> 0) / 255f;
                RenderSystem.setShaderColor(r, g, b, 1);
            }
            this.blit(matrixStack, x2, y2 + (57 - fluidStored), (int)sprite.getU0(), (int)sprite.getV0(), 15, fluidStored);
        }*/
        GUIUtilities.setup(TANK_FOREGROUND);
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageWidth);
    }
}
