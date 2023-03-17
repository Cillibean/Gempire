package com.gempire.client.screen;

import com.gempire.container.ZirconUIContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.init.ModEnchants;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestEnchant;
import com.gempire.networking.C2SRequestUpdateGemName;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
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
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;

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
        this.nameBox = new EditBox(this.font, i + 102, j + 11, 101, 12,Component.translatable("Sussy"));
        this.nameBox.setBordered(false);
        this.nameBox.setVisible(true);
        String name = this.menu.gem.customName() ? this.menu.gem.getCustomName().getString() : this.menu.gem.getNickname().getString();
        this.nameBox.setValue(name);
        this.nameBox.setFocus(true);
        addRenderableWidget(this.nameBox);
        this.setInitialFocus(this.nameBox);

        /*this.addButton(new ImageButton(this.guiLeft + 19, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.LEFT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), false));
        }));

        this.addButton(new ImageButton(this.guiLeft + 180, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.RIGHT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), true));
        }));*/

        addRenderableWidget(new ImageButton(this.leftPos + 90, this.topPos + 55, 30, 10, 0, 0, 0, ZirconUIScreen.ENCHANT_BUTTON,
                30, 10, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestEnchant(this.menu.gem.getId()));
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
        GUIUtilities.setup(GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight, 224, 208);
        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        this.font.draw(matrixStack, ZirconUIScreen.getEnchantStringFromLapisCount(this.menu.gem),
                i + 45, j + 29, 4210752);
        if(this.menu.gem.getItem(1).canApplyAtEnchantingTable(ModEnchants.VANILLA_ENCHANTMENTS.get(this.menu.gem.getEnchantPage()))) {
            GUIUtilities.setup(XP_ORB);
            this.blit(matrixStack, x + 45, y + 64, 0, 0, 11, 11, 11 ,11);
            int xp = this.getXP(this.getDiscountFromStack(this.menu.gem.getItem(2))) < 0 ? 0 : this.getXP(this.getDiscountFromStack(this.menu.gem.getItem(2)));
            this.font.draw(matrixStack, Component.translatable(xp + "XP"),
                    i + 59, j + 66, 0x88FF00);
        }
    }

    public int getXP(int discount){
        int level = getEnchantLevelFromLapisCount(this.menu.gem);
        int returnv = level > 0 ? 255 * level : 255;
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
        int maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        int lapisCount = gem.getItem(0).getCount();
        int level = (int) Math.ceil(lapisCount * maxEnchant / 32);
        return level;
    }

    public static String getEnchantStringFromLapisCount(EntityZircon gem){
        int maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        int level = ZirconUIScreen.getEnchantLevelFromLapisCount(gem);
        if(level >= maxEnchant) {
            return Component.translatable(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                    ZirconUIScreen.getNumeralsFromLevel(maxEnchant);
        }
        else if(level >= 1 && level < maxEnchant){
            return Component.translatable(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                    ZirconUIScreen.getNumeralsFromLevel(level);
        }
        else{
            return Component.translatable(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString();
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

    public static void renderEntityInInventory(int p_98851_, int p_98852_, int p_98853_, float p_98854_, float p_98855_, LivingEntity p_98856_) {
        float f = (float)Math.atan((double)(p_98854_ / 40.0F));
        float f1 = (float)Math.atan((double)(p_98855_ / 40.0F));
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate((double)p_98851_, (double)p_98852_, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)p_98853_, (float)p_98853_, (float)p_98853_);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = p_98856_.yBodyRot;
        float f3 = p_98856_.getYRot();
        float f4 = p_98856_.getXRot();
        float f5 = p_98856_.yHeadRotO;
        float f6 = p_98856_.yHeadRot;
        p_98856_.yBodyRot = 180.0F + f * 20.0F;
        p_98856_.setYRot(180.0F + f * 40.0F);
        p_98856_.setXRot(-f1 * 20.0F);
        p_98856_.yHeadRot = p_98856_.getYRot();
        p_98856_.yHeadRotO = p_98856_.getYRot();
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(p_98856_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        p_98856_.yBodyRot = f2;
        p_98856_.setYRot(f3);
        p_98856_.setXRot(f4);
        p_98856_.yHeadRotO = f5;
        p_98856_.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }


    public void drawStats(PoseStack stack, int x, int y){
        Component health = Component.translatable("screens.gempire.health");
        Component damage = Component.translatable("screens.gempire.damage");
        this.font.draw(stack, Component.translatable(health.getString() + ": " + (int)this.menu.gem.getHealth() + " / " + (int)this.menu.gem.getMaxHealth()), x + 12, y + 98, 4210752);
        this.font.draw(stack, Component.translatable(damage.getString() + ": " + (int)this.menu.gem.getAttributeBaseValue(Attributes.ATTACK_DAMAGE)), x + 12, y + 110, 4210752);
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
