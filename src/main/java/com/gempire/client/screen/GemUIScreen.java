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
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;


public class GemUIScreen extends ContainerScreen<GemUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/base.png");
    public static final ResourceLocation ARROW = new ResourceLocation("gempire:textures/gui/arrow_down.png");
    public TextFieldWidget nameBox;

    public GemUIScreen(GemUIContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 272;
        this.ySize = 250;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.nameBox = new TextFieldWidget(this.font, x + 87, y + 69, 101, 12, new StringTextComponent("Sussy"));
        this.nameBox.setEnableBackgroundDrawing(true);
        this.nameBox.setVisible(true);
        String name = this.container.gem.customName() ? this.container.gem.getCustomName().getString() : this.container.gem.getNickname().getString();
        this.nameBox.setText(name);
        this.nameBox.setFocused2(true);

        this.children.add(this.nameBox);
        this.setFocusedDefault(this.nameBox);
        this.addButton(new Button(this.guiLeft + 27, this.guiTop + 123, 83, 20, new TranslationTextComponent("screens.gempire.poof"), (button) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPoof(this.container.gem.getEntityId()));
            this.closeScreen();
        }));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        this.drawAbilityList(matrixStack, 148, 98);
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
        this.minecraft.getTextureManager().bindTexture(GemUIScreen.GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize, 272, 250);
        this.minecraft.getTextureManager().bindTexture(GemUIScreen.ARROW);
        this.blit(matrixStack, i + 181, j + (31), 9, this.container.gem.getBrewProgress(), 0, 0, 9, this.container.gem.getBrewProgress(), 9, 11);
        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        //System.out.println("Progress: " + this.container.brewProgress.get());
        int scale = this.container.gem.getBoundingBox().getYSize() > 1.8f ? 25 : 30;
        drawEntityOnScreen(i + 228, j + 76, scale, (float)(i + 229) - mouseX, (float)(j + 46) - mouseY, this.container.gem);
        drawStats(matrixStack, i, j);
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
        ITextComponent damage = new TranslationTextComponent("screens.gempire.damage");
        this.font.func_243248_b(stack, new StringTextComponent(health.getString() + ": " + (int)this.container.gem.getHealth() + " / " + (int)this.container.gem.getMaxHealth()), x + 12, y + 98, 4210752);
        this.font.func_243248_b(stack, new StringTextComponent(damage.getString() + ": " + (int)this.container.gem.getBaseAttributeValue(Attributes.ATTACK_DAMAGE)), x + 12, y + 110, 4210752);
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
