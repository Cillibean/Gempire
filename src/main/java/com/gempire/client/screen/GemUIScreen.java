package com.gempire.client.screen;

import com.gempire.container.GemUIContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.RequestPoof;
import com.gempire.networking.C2SRequestUpdateGemName;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Quaternionf;

import java.util.ArrayList;


public class GemUIScreen extends AbstractContainerScreen<GemUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/base.png");
    public EditBox nameBox;

    public GemUIScreen(GemUIContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 197;
        this.imageHeight = 250;
    }

    @Override
    protected void init() {
        //name box
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.nameBox = new EditBox(this.font, x + 27, y + 4, 144, 12, Component.translatable("Sussy"));
        this.nameBox.setBordered(true);
        this.nameBox.setVisible(true);
        String name = this.menu.gem.getName().getString();
        this.nameBox.setValue(name);
        //this.nameBox.setFocus(true);
        //poof button
        addRenderableWidget(this.nameBox);
        //this.setInitialFocus(this.nameBox);
        int i = this.menu.gem.getId();
        Screen screen = this;
        Button.Builder builder = new Button.Builder(Component.translatable("screens.gempire.poof"), button -> {
            ModPacketHandler.INSTANCE.sendToServer(new RequestPoof(i));
            screen.onClose();
        });
        builder.bounds(this.leftPos + 10, this.topPos + 123, 83, 20);
        addRenderableWidget(builder.build());
    }

    @Override
    protected void renderLabels(GuiGraphics matrixStack, int x, int y) {
        this.drawAbilityList(matrixStack, 104, 97);
    }

    @Override
    public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        GUIUtilities.setup(GemUIScreen.GUI);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;

        matrixStack.blit(GUI, x, y, 0, 0, this.imageWidth, this.imageHeight, 197, 250);

        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        int scale = 0;
        float boundingy = (float) this.menu.gem.getBoundingBox().getYsize();
        if (boundingy > 2.4) {
            scale = 17;
        } else if (boundingy > 1.8) {
            scale = 25;
        } else {
            scale = 30;
        }
        renderEntityInInventory(i + 135, j + 85, scale, (float)(i + 135) - mouseX, (float)(j + 75) - mouseY, this.menu.gem);
        drawStats(matrixStack, i, j);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(this.nameBox.isFocused()) {
            this.nameBox.keyPressed(keyCode, scanCode, modifiers);
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateGemName(this.nameBox.getValue(), this.menu.gem.getId()));
            return true;
        }
        else{
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.nameBox.mouseClicked(mouseX, mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public static void renderEntityInInventory(int leftpos, int topPos, int scale, float mouseX, float mouseY, LivingEntity entity) {
        float f = (float)Math.atan((double)(mouseX / 40.0F));
        float f1 = (float)Math.atan((double)(mouseY / 40.0F));
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate((double)leftpos, (double)topPos, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)scale, (float)scale, (float)scale);
        Quaternionf quaternion = Axis.ZP.rotationDegrees(180.0F);
        Quaternionf quaternion1 = Axis.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = entity.yBodyRot;
        float f3 = entity.getYRot();
        float f4 = entity.getXRot();
        float f5 = entity.yHeadRotO;
        float f6 = entity.yHeadRot;
        entity.yBodyRot = 180.0F + f * 20.0F;
        entity.setYRot(180.0F + f * 40.0F);
        entity.setXRot(-f1 * 20.0F);
        entity.yHeadRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conjugate();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        entity.yBodyRot = f2;
        entity.setYRot(f3);
        entity.setXRot(f4);
        entity.yHeadRotO = f5;
        entity.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }


    public void drawStats(GuiGraphics stack, int x, int y){
        Component health = Component.translatable("screens.gempire.health");
        Component damage = Component.translatable("screens.gempire.damage");
        stack.drawString(font, Component.translatable(health.getString() + ": " + (int)this.menu.gem.getHealth() + " / " + (int)this.menu.gem.getMaxHealth()), x + 7, y + 98, 4210752);
        stack.drawString(font,Component.translatable(damage.getString() + ": " + (int)this.menu.gem.getAttributeBaseValue(Attributes.ATTACK_DAMAGE)), x + 7, y + 110, 4210752);
    }

    public void drawAbilityList(GuiGraphics stack, int x, int y){
        ArrayList<Ability> powers1 = this.menu.gem.findAbilities(this.menu.gem.getAbilities());
        ArrayList<Ability> powers = new ArrayList<>();
        for(Ability ability : powers1){
            if(!(ability instanceof AbilityZilch)){
                powers.add(ability);
            }
        }
        for(int i = 0; i < powers.size(); i++){
            Component text = powers.get(i).getName();
            stack.drawString(font, text, x, y + i * 9, 4210752);
        }
    }
}
