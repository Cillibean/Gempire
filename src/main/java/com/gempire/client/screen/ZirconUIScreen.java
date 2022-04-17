package com.gempire.client.screen;

import com.gempire.container.GemUIContainer;
import com.gempire.container.ZirconUIContainer;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.init.ModEnchants;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SRequestEnchant;
import com.gempire.networking.C2SRequestPageChange;
import com.gempire.networking.C2SRequestPoof;
import com.gempire.networking.C2SRequestUpdateGemName;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
        this.nameBox = new EditBox(this.font, i + 102, j + 11, 101, 12, new TextComponent("Sussy"));
        this.nameBox.setBordered(false);
        this.nameBox.setVisible(true);
        String name = this.menu.gem.customName() ? this.menu.gem.getCustomName().getString() : this.menu.gem.getNickname().getString();
        this.nameBox.setValue(name);
        this.nameBox.setFocus(true);
        this.children.add(this.nameBox);
        this.setInitialFocus(this.nameBox);

        /*this.addButton(new ImageButton(this.guiLeft + 19, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.LEFT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), false));
        }));

        this.addButton(new ImageButton(this.guiLeft + 180, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.RIGHT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), true));
        }));*/

        this.addButton(new ImageButton(this.leftPos + 90, this.topPos + 55, 30, 10, 0, 0, 0, ZirconUIScreen.ENCHANT_BUTTON,
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
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(ZirconUIScreen.GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight, 224, 208);
        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        this.font.draw(matrixStack, ZirconUIScreen.getEnchantStringFromLapisCount(this.menu.gem),
                i + 45, j + 29, 4210752);
        if(this.menu.gem.getItem(1).canApplyAtEnchantingTable(ModEnchants.VANILLA_ENCHANTMENTS.get(this.menu.gem.getEnchantPage()))) {
            this.minecraft.getTextureManager().bind(ZirconUIScreen.XP_ORB);
            this.blit(matrixStack, x + 45, y + 64, 0, 0, 11, 11, 11 ,11);
            int xp = this.getXP(this.getDiscountFromStack(this.menu.gem.getItem(2))) < 0 ? 0 : this.getXP(this.getDiscountFromStack(this.menu.gem.getItem(2)));
            this.font.draw(matrixStack, new TextComponent(xp + "XP"),
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

    public static TextComponent getEnchantStringFromLapisCount(EntityZircon gem){
        int maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        int level = ZirconUIScreen.getEnchantLevelFromLapisCount(gem);
        if(level >= maxEnchant) {
            return new TextComponent(new TranslatableComponent(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                    ZirconUIScreen.getNumeralsFromLevel(maxEnchant));
        }
        else if(level >= 1 && level < maxEnchant){
            return new TextComponent(new TranslatableComponent(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString() + " " +
                    ZirconUIScreen.getNumeralsFromLevel(level));
        }
        else{
            return new TextComponent(new TranslatableComponent(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getDescriptionId()).getString());
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
