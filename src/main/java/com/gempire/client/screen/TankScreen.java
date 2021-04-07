package com.gempire.client.screen;

import com.gempire.container.TankContainer;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestDumpFluidsInjector;
import com.gempire.networking.C2SRequestDumpFluidsTank;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class TankScreen extends ContainerScreen<TankContainer> {
    public static final ResourceLocation TANK_GUI = new ResourceLocation("gempire:textures/gui/tank_gui.png");
    public static final ResourceLocation TANK_FOREGROUND = new ResourceLocation("gempire:textures/gui/tank_foreground.png");
    public static final ResourceLocation DUMP_BUTTON = new ResourceLocation("gempire:textures/gui/dump_button.png");

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.addButton(new ImageButton(x + 79, y + 32, 39, 12, 0, 0, 0, TankScreen.DUMP_BUTTON, 39, 12, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestDumpFluidsTank(this.container.tank.getPos()));
        }));
    }

    public TankScreen(TankContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 160;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {

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
            int x2 = 58 + x;
            int y2 = 9 + y;
            int fluidStored = 57 * fluid.getAmount() / 4000;
            if(fluid.getFluid() == Fluids.WATER){
                int color = Fluids.WATER.getAttributes().getColor();
                float r = ((color & 16711680) >> 16) / 255f;
                float g = ((color & 65280) >> 8) / 255f;
                float b = ((color & 255) >> 0) / 255f;
                RenderSystem.color4f(r, g, b, 1);
            }
            this.blit(matrixStack, x2, y2 + (57 - fluidStored), (int)sprite.getMinU(), (int)sprite.getMinV(), 15, fluidStored);
        }
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(TankScreen.TANK_FOREGROUND);
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.xSize);
    }
}
