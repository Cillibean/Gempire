package com.gempire.client.screen;

import com.gempire.container.ZirconUIContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.init.ModEnchants;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestEnchant;
import com.gempire.networking.RequestPoof;
import com.gempire.networking.C2SRequestUpdateGemName;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import org.joml.Quaternionf;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;


public class ZirconUIScreen extends AbstractContainerScreen<ZirconUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/zircon_menu.png");
    public static final ResourceLocation LEFT = new ResourceLocation("gempire:textures/gui/zircon_arrow_left.png");
    public static final ResourceLocation RIGHT = new ResourceLocation("gempire:textures/gui/zircon_arrow_right.png");
    public static final ResourceLocation ENCHANT_BUTTON = new ResourceLocation("gempire:textures/gui/enchant_button.png");
    public static final ResourceLocation XP_ORB = new ResourceLocation("gempire:textures/gui/xp_orb.png");
    public EditBox nameBox;

    public ZirconUIScreen(ZirconUIContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 210;
        this.imageHeight = 208;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;
        this.nameBox = new EditBox(this.font, i + 15, j + 11, 110, 14,Component.translatable("Sussy"));
        this.nameBox.setBordered(false);
        this.nameBox.setVisible(true);
        String name = this.menu.gem.getName().getString();
        this.nameBox.setValue(name);
        //this.nameBox.setFocus(true);
        addRenderableWidget(this.nameBox);
        //this.setInitialFocus(this.nameBox);

        /*this.addButton(new ImageButton(this.guiLeft + 19, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.LEFT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new PageChange(this.container.gem.getEntityId(), false));
        }));

        this.addButton(new ImageButton(this.guiLeft + 180, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.RIGHT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new PageChange(this.container.gem.getEntityId(), true));
        }));*/
        int n = this.menu.gem.getId();
        Screen screen = this;
        Button.Builder builder = new Button.Builder(Component.translatable("screens.gempire.poof"), button -> {
            ModPacketHandler.INSTANCE.sendToServer(new RequestPoof(n));
            screen.onClose();
        });
        builder.bounds(this.leftPos + 138, this.topPos + 81, 65, 20);
        addRenderableWidget(builder.build());
        addRenderableWidget(new ImageButton(this.leftPos + 57, this.topPos + 57, 30, 10, 0, 0, 0, ZirconUIScreen.ENCHANT_BUTTON,
                31, 10, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestEnchant(this.menu.gem.getId()));
        }));
    }

    @Override
    protected void renderLabels(GuiGraphics matrixStack, int x, int y) {

    }

    @Override
    public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        GUIUtilities.setup(GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;
        matrixStack.blit(GUI, x, y, 0, 0, this.imageWidth, this.imageHeight, 224, 208);
        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        matrixStack.drawString(font, ZirconUIScreen.getEnchantStringFromLapisCount(this.menu.gem),
                i + 15, j + 29, 4210752);
        if (this.menu.gem.isPrimary()) {
            if(this.menu.gem.getItem(1).canApplyAtEnchantingTable(ModEnchants.GEMPIRE_ENCHANTMENTS.get(this.menu.gem.getEnchantPage()))) {
                GUIUtilities.setup(XP_ORB);
                matrixStack.blit(GUI, x + 14, y + 56, 0, 0, 11, 11, 11 ,11);
                int xp = Math.max(this.getXP(this.getDiscountFromStack(this.menu.gem.getItem(2))), 0);
                matrixStack.drawString(font, Component.translatable(xp + "XP"),
                        i + 26, j + 58, 0x88FF00);
            }
        } else {
            if(this.menu.gem.getItem(1).canApplyAtEnchantingTable(ModEnchants.VANILLA_ENCHANTMENTS.get(this.menu.gem.getEnchantPage()))) {
                GUIUtilities.setup(XP_ORB);
                matrixStack.blit(GUI, x + 14, y + 56, 0, 0, 11, 11, 11 ,11);
                int xp = Math.max(this.getXP(this.getDiscountFromStack(this.menu.gem.getItem(2))), 0);
                matrixStack.drawString(font, Component.translatable(xp + "XP"),
                        i + 26, j + 58, 0x88FF00);
            }
        }
        renderEntityInInventory(i + 170, j + 72, 23, (float)(i + 170) - mouseX, (float)(j + 25) - mouseY, this.menu.gem);
    }

    public int getXP(int discount){
        int level = getEnchantLevelFromLapisCount(this.menu.gem);
        int returnv = level > 0 ? 75 * level : 75;
        return returnv - discount;
    }

    public int getDiscountFromStack(ItemStack stack){
        if(stack.isEnchanted()){
            return 100;
        }
        if(ModEnchants.POWERFUL_ITEMS_DISCOUNT.get(stack.getItem()) != null){
            return ModEnchants.POWERFUL_ITEMS_DISCOUNT.get(stack.getItem()) * stack.getCount();
        }
        return 0;
    }

    //0x88FF00

    public static int getEnchantLevelFromLapisCount(EntityZircon gem){
        int maxEnchant = 0;
        if (gem.isPrimary()) {
            maxEnchant = ModEnchants.GEMPIRE_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        } else {
            maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        }
        int lapisCount = gem.getItem(0).getCount();
        return (int) Math.ceil(lapisCount * maxEnchant / 32);
    }

    public static String getEnchantStringFromLapisCount(EntityZircon gem){
        int maxEnchant = 0;
        if(gem.isPrimary()) {
            maxEnchant = ModEnchants.GEMPIRE_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        } else {
            maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        }
        int level = ZirconUIScreen.getEnchantLevelFromLapisCount(gem);
        if (gem.isPrimary()) {
            if (level >= maxEnchant) {
                return Component.translatable(ModEnchants.GEMPIRE_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                        ZirconUIScreen.getNumeralsFromLevel(maxEnchant);
            } else if (level >= 1 && level < maxEnchant) {
                return Component.translatable(ModEnchants.GEMPIRE_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                        ZirconUIScreen.getNumeralsFromLevel(level);
            } else {
                return Component.translatable(ModEnchants.GEMPIRE_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString();
            }
        } else {
            if (level >= maxEnchant) {
                return Component.translatable(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                        ZirconUIScreen.getNumeralsFromLevel(maxEnchant);
            } else if (level >= 1 && level < maxEnchant) {
                return Component.translatable(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                        ZirconUIScreen.getNumeralsFromLevel(level);
            } else {
                return Component.translatable(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString();
            }
        }
    }

    public static String getNumeralsFromLevel(int level){
        switch (level){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            default:
                return "";
        }
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if(this.nameBox.isFocused()) {
            this.nameBox.keyReleased(keyCode, scanCode, modifiers);
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestUpdateGemName(this.nameBox.getValue(), this.menu.gem.getId()));
            return true;
        }
        else{
            return super.keyReleased(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(this.nameBox.isFocused()) {
            if (keyCode == GLFW.GLFW_KEY_ENTER) {
                nameBox.setFocused(false);
            }
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
                nameBox.setFocused(false);
            }
            this.nameBox.keyPressed(keyCode, scanCode, modifiers);
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
        stack.drawString(font, Component.translatable(health.getString() + ": " + (int)this.menu.gem.getHealth() + " / " + (int)this.menu.gem.getMaxHealth()), x + 12, y + 98, 4210752, false);
        stack.drawString(font, Component.translatable(damage.getString() + ": " + (int)this.menu.gem.getAttributeBaseValue(Attributes.ATTACK_DAMAGE)), x + 12, y + 110, 4210752, false);
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
            stack.drawString(font, text, x, y + i * 9, 4210752, false);
        }
    }
}
