package com.gempire.client.screen.warppad;

import com.gempire.Gempire;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.util.List;

public class WarpButton extends ExtendedButton {
    private Component text;
    public BlockPos blockPos;

    private static final ResourceLocation BUTTON = new ResourceLocation(Gempire.MODID, "textures/gui/warp_button.png");
    private static final ResourceLocation BUTTON_HIGHLIGHTED = new ResourceLocation(Gempire.MODID, "textures/gui/warp_button_highlighted.png");
    public WarpButton(int x, int y, int width, int height, Component text, BlockPos pos, OnPress handler) {
        super(x, y, width, height, text, handler);
        this.text = text;
        this.blockPos = pos;
    }
    @Override
    public void renderWidget(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        int x = this.getX();
        int y = this.getY();
        if (this.isFocused()) {
            stack.blit(BUTTON_HIGHLIGHTED, x, y, 0, 100, 144, 23);
        } else {
            stack.blit(BUTTON, x, y, 0, 100, 144, 23);
        }
        Component pos = Component.literal(blockPos.getX() +", " +blockPos.getY() +", " +blockPos.getZ());
        int x2 = x + (144 - font.width(text)) / 2;
        int x3 = x + (144 - font.width(pos)) / 2;
        int y2 = y + (13 - font.lineHeight) / 2;
        int y3 = y + (33 - font.lineHeight) / 2;
        stack.drawString(font, text, x2, y2, 0xFFFFFF);
        stack.drawString(font, pos, x3, y3, 0xFFFFFF);
    }
}
