package com.gempire.client.screen.warppad;

import com.gempire.Gempire;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.WarpRequest;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.systems.warping.WarpSelectionMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class WarpSelectionScreen extends AbstractContainerScreen<WarpSelectionMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Gempire.MODID, "textures/gui/warp_selection.png");
    private List<WarpButton> warpButtons = new ArrayList<>();
    private float scrollOffset;
    private int startButton;
    private int extraButtons;
    private boolean scrolling;
    public WarpSelectionScreen(WarpSelectionMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }
    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.titleLabelY = 6;
        List<WarpPadInfo> warpPads = this.menu.getWarpPads();
        for(WarpPadInfo info : warpPads) {
            if(!info.getPos().equals(this.menu.getPos())) {
                WarpButton warpButton = new WarpButton(this.leftPos + 7, this.topPos, 144, 23, Component.literal(info.getName()), button -> {
                    ModPacketHandler.INSTANCE.sendToServer(new WarpRequest(this.menu.getPos(), info.getPos()));
                    onClose();
                });
                this.addWidget(warpButton);
                warpButtons.add(warpButton);
            }
        }
        extraButtons = warpButtons.size() - 6;
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
            int x = this.leftPos + 157;
            int y = this.topPos + 18;
            if(mouseX >= x && mouseX <= x + 12 && mouseY >= y && mouseY <= y + 138) {
                scrolling = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }
    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, BACKGROUND);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        int x = this.leftPos + 157;
        int y = this.topPos + 18 + (int)(123 * scrollOffset);
        int extraButtons = warpButtons.size() - 6;
        blit(stack, x, y, 232 + (extraButtons > 0 ? 0 : 12), 0, 12, 15);
    }
    private void renderButtons(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        for(int i = 0; i < warpButtons.size(); i++) {
            int index = i - startButton;
            if(index >= 0 && index < 6) {
                WarpButton button = warpButtons.get(i);
                button.y = this.topPos + 18 + 23 * index;
                RenderSystem.setShaderTexture(0, BACKGROUND);
                button.render(stack, mouseX, mouseY, partialTicks);
            }
        }
    }
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        renderButtons(stack, partialTicks, mouseX, mouseY);
        this.renderTooltip(stack, mouseX, mouseY);
    }
}
