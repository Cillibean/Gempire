package com.gempire.client.screen;

import com.gempire.container.GemUIContainer;
import com.gempire.container.PearlUIContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;


public class PearlUIScreen extends AbstractContainerScreen<PearlUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/pearl_ui.png");
    public static final ResourceLocation LEFT = new ResourceLocation("gempire:textures/gui/left.png");
    public static final ResourceLocation RIGHT = new ResourceLocation("gempire:textures/gui/right.png");
    public static final ResourceLocation LEFT_L = new ResourceLocation("gempire:textures/gui/left_light.png");
    public static final ResourceLocation RIGHT_L = new ResourceLocation("gempire:textures/gui/right_light.png");

    public EditBox nameBox;

    public PearlUIScreen(PearlUIContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 350;
        this.imageHeight = 240;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        this.nameBox = new EditBox(this.font, x + 74, y + 9, 101, 12, new TextComponent("Sussy"));
        this.nameBox.setBordered(true);
        this.nameBox.setVisible(true);
        String name = this.menu.gem.customName() ? this.menu.gem.getCustomName().getString() : this.menu.gem.getDisplayName().getString();
        this.nameBox.setValue(name);
        this.nameBox.setFocus(true);
        this.children.add(this.nameBox);
        this.setInitialFocus(this.nameBox);

        this.addButton(new ImageButton(this.leftPos + 241, this.topPos + 11, 11, 9, 0, 0, 0, PearlUIScreen.LEFT,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.menu.gem.getId(), false));
        }));

        this.addButton(new ImageButton(this.leftPos + 256, this.topPos + 11, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.menu.gem.getId(), true));
        }));

        this.addButton(new Button(this.leftPos + 10, this.topPos + 108, 83, 20, new TranslatableComponent("screens.gempire.poof"),
                (button) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPoof(this.menu.gem.getId()));
            this.onClose();
        }));

        //CUSTOMIZATION STUFF

        this.addButton(new ImageButton(this.leftPos + 122, this.topPos + 35, 11, 9, 0, 0, 0, PearlUIScreen.LEFT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestHairChange(this.menu.gem.getId(), false));
        }));
        this.addButton(new ImageButton(this.leftPos + 137, this.topPos + 35, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestHairChange(this.menu.gem.getId(), true));
        }));

        this.addButton(new ImageButton(this.leftPos + 139, this.topPos + 53, 11, 9, 0, 0, 0, PearlUIScreen.LEFT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestOutfitChange(this.menu.gem.getId(), false));
        }));
        this.addButton(new ImageButton(this.leftPos + 154, this.topPos + 53, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestOutfitChange(this.menu.gem.getId(), true));
        }));

        this.addButton(new ImageButton(this.leftPos + 138, this.topPos + 71, 11, 9, 0, 0, 0, PearlUIScreen.LEFT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestInsigniaChange(this.menu.gem.getId(), false));
        }));
        this.addButton(new ImageButton(this.leftPos + 153, this.topPos + 71, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestInsigniaChange(this.menu.gem.getId(), true));
        }));
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
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(PearlUIScreen.GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight, 352, 240);

        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);

        drawEntityOnScreen(i + 38, j + 76, 26, (float)(i + 37) - mouseX, (float)(j + 43) - mouseY, this.menu.gem);

        drawStats(matrixStack, i, j);

        int ddOffsetHair = this.menu.gem.getHairVariant() > 8 ? -3 : 0;
        int ddOffsetOutfit = this.menu.gem.getOutfitVariant() > 9 ? -3 : 0;
        int ddOffsetInsignia = this.menu.gem.getInsigniaVariant() > 9 ? -3 : 0;

        this.font.draw(matrixStack, new TextComponent("Page: " + (this.menu.gem.getPage() + 1)),
                i + 190, j + 11, 4210752);
        this.font.draw(matrixStack, new TextComponent("Hair:"),
                i + 77, j + 36, 0xFFFFFF);
        this.font.draw(matrixStack, new TextComponent("Uniform:"),
                i + 77, j + 54, 0xFFFFFF);
        this.font.draw(matrixStack, new TextComponent("Insignia:"),
                i + 77, j + 72, 0xFFFFFF);
        this.font.draw(matrixStack, new TextComponent("" + (this.menu.gem.getHairVariant() + 1)),
                i + 107 + ddOffsetHair, j + 37, 0xFFFFFF);
        this.font.draw(matrixStack, new TextComponent("" + (this.menu.gem.getOutfitVariant() + 1)),
                i + 124 + ddOffsetOutfit, j + 55, 0xFFFFFF);
        this.font.draw(matrixStack, new TextComponent("" + (this.menu.gem.getInsigniaVariant() + 1)),
                i + 123 + ddOffsetInsignia, j + 73, 0xFFFFFF);
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

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity livingEntity) {
        float f = (float)Math.atan((double)(mouseX / 40.0F));
        float f1 = (float)Math.atan((double)(mouseY / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)posX, (float)posY, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        PoseStack matrixstack = new PoseStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        matrixstack.mulPose(quaternion);
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.yRot;
        float f4 = livingEntity.xRot;
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;
        livingEntity.yBodyRot = 180 + f * 20.0F;
        livingEntity.yRot = 180 + f * 40.0F;
        livingEntity.xRot = -f1 * 20.0F;
        livingEntity.yHeadRot = livingEntity.yRot;
        livingEntity.yHeadRotO = livingEntity.yRot;
        EntityRenderDispatcher entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        MultiBufferSource.BufferSource irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(livingEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F,
                    matrixstack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.endBatch();
        entityrenderermanager.setRenderShadow(true);
        livingEntity.yBodyRot = f2;
        livingEntity.yRot = f3;
        livingEntity.xRot = f4;
        livingEntity.yHeadRotO = f5;
        livingEntity.yHeadRot = f6;
        RenderSystem.popMatrix();
    }


    public void drawStats(PoseStack stack, int x, int y){
        Component health = new TranslatableComponent("screens.gempire.health");
        this.font.draw(stack, new TextComponent(health.getString() + ": " + (int)this.menu.gem.getHealth()
                + " / " + (int)this.menu.gem.getMaxHealth()), x + 10, y + 97, 4210752);
    }

    public void drawAbilityList(PoseStack stack, int x, int y){
        ArrayList<Ability> powers1 = this.menu.gem.findAbilities(this.menu.gem.getAbilites());
        ArrayList<Ability> powers = new ArrayList<>();
        for(Ability ability : powers1){
            if(!(ability instanceof AbilityZilch)){
                powers.add(ability);
            }
        }
        for(int i = 0; i < powers.size(); i++){
            Component text = powers.get(i).getName();
            this.font.draw(stack, text, x, y + i * 9, 4210752);
        }
    }
}
