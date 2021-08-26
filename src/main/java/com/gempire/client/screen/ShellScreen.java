package com.gempire.client.screen;

import com.gempire.container.InjectorContainer;
import com.gempire.container.ShellContainer;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestDumpFluidsInjector;
import com.gempire.networking.C2SRequestInject;
import com.gempire.networking.C2SRequestUpdateInjectorValves;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class ShellScreen extends ContainerScreen<ShellContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/base_shell.png");
    public static final ResourceLocation LIGHT = new ResourceLocation("gempire:textures/gui/shell_light.png");

    public ShellScreen(ShellContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 176;
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
        this.minecraft.getTextureManager().bindTexture(ShellScreen.GUI);
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
        if(this.container.shell.waterlogged){
            this.minecraft.getTextureManager().bindTexture(ShellScreen.LIGHT);
            this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
        }
    }
}
