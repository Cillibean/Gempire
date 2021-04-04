package com.gempire.client.screen;

import com.gempire.container.TankContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class TankScreen extends ContainerScreen<TankContainer> {
    public static final ResourceLocation TANK_GUI = new ResourceLocation("gempire:textures/gui/tank_gui.png");
    public static final ResourceLocation TANK_FOREGROUND = new ResourceLocation("gempire:textures/gui/tank_foreground.png");

    public TankScreen(TankContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 201;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(TankScreen.TANK_GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
        FluidStack fluid = this.container.tank.getFluid();
        if(fluid.getFluid() != Fluids.EMPTY)
        {
            ResourceLocation stillLocation = fluid.getFluid().getAttributes().getFlowingTexture(this.container.tank.getFluid());
            TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(stillLocation);
            ResourceLocation spriteLocation = sprite.getName();
            this.minecraft.getTextureManager().bindTexture(new ResourceLocation(spriteLocation.getNamespace(), "textures/" + spriteLocation.getPath() + ".png"));
            int x2 = 152 + (this.width - this.xSize) / 2;
            int y2 = 4 + (this.height - this.ySize) / 2;
            int fluidStored = 54 * fluid.getAmount() / 8000;
            if(fluid.getFluid() == Fluids.WATER){
                int color = Fluids.WATER.getAttributes().getColor();
                float r = ((color & 16711680) >> 16) / 255f;
                float g = ((color & 65280) >> 8) / 255f;
                float b = ((color & 255) >> 0) / 255f;
                RenderSystem.color4f(r, g, b, 1);
            }
            this.blit(matrixStack, x2, y2 + (54 - fluidStored), (int)sprite.getMinU(), (int)sprite.getMinV(), 16, fluidStored);
        }
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(TankScreen.TANK_FOREGROUND);
        int x3 = (this.width - this.xSize) / 2;
        int y3 = (this.height - this.ySize) / 2;
        this.blit(matrixStack, x3, y3, 0, 0, this.xSize, this.xSize);
    }
}
