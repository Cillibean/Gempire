package com.gempire.client.screen;

import com.gempire.container.GemUIContainer;
import com.gempire.container.PearlUIContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class PearlUIScreen extends ContainerScreen<PearlUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/pearl_ui.png");
    public static final ResourceLocation LEFT = new ResourceLocation("gempire:textures/gui/left.png");
    public static final ResourceLocation RIGHT = new ResourceLocation("gempire:textures/gui/right.png");
    public static final ResourceLocation LEFT_L = new ResourceLocation("gempire:textures/gui/left_light.png");
    public static final ResourceLocation RIGHT_L = new ResourceLocation("gempire:textures/gui/right_light.png");

    public TextFieldWidget nameBox;

    public PearlUIScreen(PearlUIContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 350;
        this.ySize = 240;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        this.nameBox = new TextFieldWidget(this.font, x + 74, y + 9, 101, 12, new StringTextComponent("Sussy"));
        this.nameBox.setEnableBackgroundDrawing(true);
        this.nameBox.setVisible(true);
        String name = this.container.gem.customName() ? this.container.gem.getCustomName().getString() : this.container.gem.getDisplayName().getString();
        this.nameBox.setText(name);
        this.nameBox.setFocused2(true);
        this.children.add(this.nameBox);
        this.setFocusedDefault(this.nameBox);

        this.addButton(new ImageButton(this.guiLeft + 241, this.guiTop + 11, 11, 9, 0, 0, 0, PearlUIScreen.LEFT,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), false));
        }));

        this.addButton(new ImageButton(this.guiLeft + 256, this.guiTop + 11, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), true));
        }));

        this.addButton(new Button(this.guiLeft + 10, this.guiTop + 108, 83, 20, new TranslationTextComponent("screens.gempire.poof"),
                (button) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPoof(this.container.gem.getEntityId()));
            this.closeScreen();
        }));

        //CUSTOMIZATION STUFF

        this.addButton(new ImageButton(this.guiLeft + 122, this.guiTop + 35, 11, 9, 0, 0, 0, PearlUIScreen.LEFT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestHairChange(this.container.gem.getEntityId(), false));
        }));
        this.addButton(new ImageButton(this.guiLeft + 137, this.guiTop + 35, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestHairChange(this.container.gem.getEntityId(), true));
        }));

        this.addButton(new ImageButton(this.guiLeft + 139, this.guiTop + 53, 11, 9, 0, 0, 0, PearlUIScreen.LEFT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestOutfitChange(this.container.gem.getEntityId(), false));
        }));
        this.addButton(new ImageButton(this.guiLeft + 154, this.guiTop + 53, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestOutfitChange(this.container.gem.getEntityId(), true));
        }));

        this.addButton(new ImageButton(this.guiLeft + 138, this.guiTop + 71, 11, 9, 0, 0, 0, PearlUIScreen.LEFT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestInsigniaChange(this.container.gem.getEntityId(), false));
        }));
        this.addButton(new ImageButton(this.guiLeft + 153, this.guiTop + 71, 11, 9, 0, 0, 0, PearlUIScreen.RIGHT_L,
                11, 9, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestInsigniaChange(this.container.gem.getEntityId(), true));
        }));
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
        this.minecraft.getTextureManager().bindTexture(PearlUIScreen.GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize, 352, 240);

        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);

        drawEntityOnScreen(i + 38, j + 76, 26, (float)(i + 37) - mouseX, (float)(j + 43) - mouseY, this.container.gem);

        drawStats(matrixStack, i, j);

        int ddOffsetHair = this.container.gem.getHairVariant() > 8 ? -3 : 0;
        int ddOffsetOutfit = this.container.gem.getOutfitVariant() > 9 ? -3 : 0;
        int ddOffsetInsignia = this.container.gem.getInsigniaVariant() > 9 ? -3 : 0;

        this.font.func_243248_b(matrixStack, new StringTextComponent("Page: " + (this.container.gem.getPage() + 1)),
                i + 190, j + 11, 4210752);
        this.font.func_243248_b(matrixStack, new StringTextComponent("Hair:"),
                i + 77, j + 36, 0xFFFFFF);
        this.font.func_243248_b(matrixStack, new StringTextComponent("Uniform:"),
                i + 77, j + 54, 0xFFFFFF);
        this.font.func_243248_b(matrixStack, new StringTextComponent("Insignia:"),
                i + 77, j + 72, 0xFFFFFF);
        this.font.func_243248_b(matrixStack, new StringTextComponent("" + (this.container.gem.getHairVariant() + 1)),
                i + 107 + ddOffsetHair, j + 37, 0xFFFFFF);
        this.font.func_243248_b(matrixStack, new StringTextComponent("" + (this.container.gem.getOutfitVariant() + 1)),
                i + 124 + ddOffsetOutfit, j + 55, 0xFFFFFF);
        this.font.func_243248_b(matrixStack, new StringTextComponent("" + (this.container.gem.getInsigniaVariant() + 1)),
                i + 123 + ddOffsetInsignia, j + 73, 0xFFFFFF);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(this.nameBox.isFocused()) {
            this.nameBox.keyPressed(keyCode, scanCode, modifiers);
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateGemName(this.nameBox.getText(), this.container.gem.getEntityId()));
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
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.multiply(quaternion1);
        matrixstack.rotate(quaternion);
        float f2 = livingEntity.renderYawOffset;
        float f3 = livingEntity.rotationYaw;
        float f4 = livingEntity.rotationPitch;
        float f5 = livingEntity.prevRotationYawHead;
        float f6 = livingEntity.rotationYawHead;
        livingEntity.renderYawOffset = 180 + f * 20.0F;
        livingEntity.rotationYaw = 180 + f * 40.0F;
        livingEntity.rotationPitch = -f1 * 20.0F;
        livingEntity.rotationYawHead = livingEntity.rotationYaw;
        livingEntity.prevRotationYawHead = livingEntity.rotationYaw;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
        quaternion1.conjugate();
        entityrenderermanager.setCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.renderEntityStatic(livingEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F,
                    matrixstack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.finish();
        entityrenderermanager.setRenderShadow(true);
        livingEntity.renderYawOffset = f2;
        livingEntity.rotationYaw = f3;
        livingEntity.rotationPitch = f4;
        livingEntity.prevRotationYawHead = f5;
        livingEntity.rotationYawHead = f6;
        RenderSystem.popMatrix();
    }


    public void drawStats(MatrixStack stack, int x, int y){
        ITextComponent health = new TranslationTextComponent("screens.gempire.health");
        this.font.func_243248_b(stack, new StringTextComponent(health.getString() + ": " + (int)this.container.gem.getHealth()
                + " / " + (int)this.container.gem.getMaxHealth()), x + 10, y + 97, 4210752);
    }

    public void drawAbilityList(MatrixStack stack, int x, int y){
        ArrayList<Ability> powers1 = this.container.gem.findAbilities(this.container.gem.getAbilites());
        ArrayList<Ability> powers = new ArrayList<>();
        for(Ability ability : powers1){
            if(!(ability instanceof AbilityZilch)){
                powers.add(ability);
            }
        }
        for(int i = 0; i < powers.size(); i++){
            ITextComponent text = powers.get(i).getName();
            this.font.func_243248_b(stack, text, x, y + i * 9, 4210752);
        }
    }
}
