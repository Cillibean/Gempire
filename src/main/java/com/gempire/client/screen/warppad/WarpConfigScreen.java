package com.gempire.client.screen.warppad;

import com.gempire.Gempire;
import com.gempire.systems.warping.WarpConfigMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.glfw.GLFW;

public class WarpConfigScreen extends AbstractContainerScreen<WarpConfigMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Gempire.MODID, "textures/gui/warp_config.png");
    private EditBox name;
    public WarpConfigScreen(WarpConfigMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageHeight = 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.titleLabelY = 6;
        EditBox name = new EditBox(this.font, this.leftPos + 24, this.topPos + 22, 104, 12, Component.translatable("container.warppads.warp_config.hint"));
        name.setCanLoseFocus(true);
        name.setTextColor(-1);
        name.setTextColorUneditable(-1);
        name.setBordered(false);
        name.setMaxLength(50);
        String currentName = menu.getInfo().getName();
        name.setValue(currentName);
        if(currentName.length() <= 0) {
            this.setInitialFocus(name);
        }
        this.addWidget(name);
        this.name = name;
    }
    @Override
    public void onClose() {
        menu.saveName(name.getValue());
        super.onClose();
    }
    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 16777215);
        //this.font.draw(stack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int x = this.leftPos;
        int y = this.topPos;
        blit(stack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.name.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == 256) {
            onClose();
        } else if (keyCode == GLFW.GLFW_KEY_ENTER) {
            onClose();
        }
        return this.name.keyPressed(keyCode, scanCode, modifiers) || this.name.canConsumeInput() || super.keyPressed(keyCode, scanCode, modifiers);
    }
}
