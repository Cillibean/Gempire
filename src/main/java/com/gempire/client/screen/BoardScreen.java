package com.gempire.client.screen;

import com.gempire.container.BoardContainer;
import com.gempire.container.IncubatorContainer;
import com.gempire.tileentities.BoardTE;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;


public class BoardScreen extends AbstractContainerScreen<BoardContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/mission_board.png");
    private int extraButtons;
    private boolean scrolling;
    private float scrollOffset;
    private int startButton;

    public BoardScreen(BoardContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 178;
        this.imageHeight = 165;
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

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if(scrolling && extraButtons > 0) {
            int y = this.topPos + 18;
            scrollOffset = Mth.clamp((float)(mouseY - y - 7.5F) / 123, 0, 1);
            startButton = Math.max((int)(scrollOffset * extraButtons + 0.5F), 0);
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if(extraButtons > 0) {
            float f = (float)delta / (float)extraButtons;
            scrollOffset = Mth.clamp(scrollOffset - f, 0, 1);
            startButton = Math.max((int)(scrollOffset * extraButtons + 0.5F), 0);
        }
        return true;
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        scrolling = false;
        if(extraButtons > 0) {
            int x = this.leftPos + 83;
            int y = this.topPos + 6;
            if(mouseX >= x && mouseX <= x + 12 && mouseY >= y && mouseY <= y + 130) {
                scrolling = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
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
