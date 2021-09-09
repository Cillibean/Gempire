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
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;


public class ZirconUIScreen extends ContainerScreen<ZirconUIContainer> {
    public static final ResourceLocation GUI = new ResourceLocation("gempire:textures/gui/zircon_menu.png");
    public static final ResourceLocation LEFT = new ResourceLocation("gempire:textures/gui/zircon_arrow_left.png");
    public static final ResourceLocation RIGHT = new ResourceLocation("gempire:textures/gui/zircon_arrow_right.png");
    public static final ResourceLocation ENCHANT_BUTTON = new ResourceLocation("gempire:textures/gui/enchant_button.png");
    public static final ResourceLocation XP_ORB = new ResourceLocation("gempire:textures/gui/xp_orb.png");
    public TextFieldWidget nameBox;

    public ZirconUIScreen(ZirconUIContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 210;
        this.ySize = 208;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int i = this.guiLeft;
        int j = this.guiTop;
        this.nameBox = new TextFieldWidget(this.font, i + 102, j + 11, 101, 12, new StringTextComponent("Sussy"));
        this.nameBox.setEnableBackgroundDrawing(false);
        this.nameBox.setVisible(true);
        String name = this.container.gem.customName() ? this.container.gem.getCustomName().getString() : this.container.gem.getNickname().getString();
        this.nameBox.setText(name);
        this.nameBox.setFocused2(true);
        this.children.add(this.nameBox);
        this.setFocusedDefault(this.nameBox);

        this.addButton(new ImageButton(this.guiLeft + 19, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.LEFT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), false));
        }));

        this.addButton(new ImageButton(this.guiLeft + 180, this.guiTop + 41, 11, 21, 0, 0, 0, ZirconUIScreen.RIGHT,
                11, 21, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestPageChange(this.container.gem.getEntityId(), true));
        }));

        this.addButton(new ImageButton(this.guiLeft + 90, this.guiTop + 55, 30, 10, 0, 0, 0, ZirconUIScreen.ENCHANT_BUTTON,
                30, 10, (p_213029_1_) -> {
            ModPacketHandler.INSTANCE.sendToServer(new C2SRequestEnchant(this.container.gem.getEntityId()));
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
        this.minecraft.getTextureManager().bindTexture(ZirconUIScreen.GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize, 224, 208);
        this.nameBox.render(matrixStack, mouseX, mouseY, partialTicks);
        this.font.func_243248_b(matrixStack, ZirconUIScreen.getEnchantStringFromLapisCount(this.container.gem),
                i + 45, j + 29, 4210752);
        if(this.container.gem.getStackInSlot(1).canApplyAtEnchantingTable(ModEnchants.VANILLA_ENCHANTMENTS.get(this.container.gem.getEnchantPage()))) {
            this.minecraft.getTextureManager().bindTexture(ZirconUIScreen.XP_ORB);
            this.blit(matrixStack, x + 45, y + 64, 0, 0, 11, 11, 11 ,11);
            int xp = this.getXP(this.getDiscountFromStack(this.container.gem.getStackInSlot(2))) < 0 ? 0 : this.getXP(this.getDiscountFromStack(this.container.gem.getStackInSlot(2)));
            this.font.func_243248_b(matrixStack, new StringTextComponent(xp + "XP"),
                    i + 59, j + 66, 0x88FF00);
        }
    }

    public int getXP(int discount){
        int level = getEnchantLevelFromLapisCount(this.container.gem);
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
        int lapisCount = gem.getStackInSlot(0).getCount();
        int level = (int) Math.ceil(lapisCount * maxEnchant / 32);
        return level;
    }

    public static StringTextComponent getEnchantStringFromLapisCount(EntityZircon gem){
        int maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        int level = ZirconUIScreen.getEnchantLevelFromLapisCount(gem);
        if(level >= maxEnchant) {
            return new StringTextComponent(new TranslationTextComponent(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getName()).getString() + " " +
                    ZirconUIScreen.getNumeralsFromLevel(maxEnchant));
        }
        else if(level >= 1 && level < maxEnchant){
            return new StringTextComponent(new TranslationTextComponent(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getName()).getString() + " " +
                    ZirconUIScreen.getNumeralsFromLevel(level));
        }
        else{
            return new StringTextComponent(new TranslationTextComponent(ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getName()).getString());
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
