package com.gempire.client.screen;

import com.gempire.container.GemUIContainer;
import com.gempire.container.TankContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestDumpFluidsTank;
import com.gempire.networking.C2SRequestInject;
import com.gempire.networking.C2SRequestPoof;
import com.gempire.networking.C2SRequestUpdateGemName;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;


public class GemUIScreen extends AbstractContainerScreen<GemUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/base.png");
    public static final ResourceLocation ARROW = new ResourceLocation("gempire:textures/gui/arrow_down.png");
    public EditBox nameBox;

    public GemUIScreen(GemUIContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 272;
        this.imageHeight = 250;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.nameBox = new EditBox(this.font, x + 87, y + 69, 101, 12, new TextComponent("Sussy"));
        this.nameBox.setBordered(true);
        this.nameBox.setVisible(true);
        String name = this.menu.gem.customName() ? this.menu.gem.getCustomName().getString() : this.menu.gem.getNickname().getString();
        this.nameBox.setValue(name);
        this.nameBox.setFocus(true);

        this.children.add(this.nameBox);
        this.setInitialFocus(this.nameBox);
        this.addButton(new Button(this.leftPos + 27, this.topPos + 123, 83, 20, new TranslatableComponent("screens.gempire.poof"), (button) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPoof(this.menu.gem.getId()));
            this.onClose();
        }));
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {
        this.drawAbilityList(matrixStack, 148, 98);
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
        this.minecraft.getTextureManager().bind(GemUIScreen.GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight, 272, 250);
        this.minecraft.getTextureManager().bind(GemUIScreen.ARROW);
        this.blit(matrixStack, i + 181, j + (31), 9, this.menu.gem.getBrewProgress(), 0, 0, 9, this.menu.gem.getBrewProgress(), 9, 11);
        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        //System.out.println("Progress: " + this.container.brewProgress.get());
        int scale = this.menu.gem.getBoundingBox().getYsize() > 1.8f ? 25 : 30;
        drawEntityOnScreen(i + 228, j + 76, scale, (float)(i + 229) - mouseX, (float)(j + 46) - mouseY, this.menu.gem);
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
        Component damage = new TranslatableComponent("screens.gempire.damage");
        this.font.draw(stack, new TextComponent(health.getString() + ": " + (int)this.menu.gem.getHealth() + " / " + (int)this.menu.gem.getMaxHealth()), x + 12, y + 98, 4210752);
        this.font.draw(stack, new TextComponent(damage.getString() + ": " + (int)this.menu.gem.getAttributeBaseValue(Attributes.ATTACK_DAMAGE)), x + 12, y + 110, 4210752);
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
