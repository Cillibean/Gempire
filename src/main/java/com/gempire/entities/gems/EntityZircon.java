package com.gempire.entities.gems;

import com.gempire.client.screen.ZirconUIScreen;
import com.gempire.container.GemUIContainer;
import com.gempire.container.ZirconUIContainer;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.ai.EntityAIAreaAbility;
import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.ModEnchants;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityZircon extends EntityVaryingGem {
    public static final int NUMBER_OF_SLOTS = 3;
    public NonNullList<ItemStack> zirconItems = NonNullList.withSize(EntityZircon.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public static DataParameter<Integer> ENCHANT_PAGE = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public int ENCHANTMENT_PAGES = 0;

    public EntityZircon(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.dataManager.register(EntityZircon.ENCHANT_PAGE, 0);
        this.ENCHANTMENT_PAGES = ModEnchants.VANILLA_ENCHANTMENTS.size();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new SwimGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, MobEntity.class, 6.0F, 1.0D, 1.2D, (mob)->{
            return mob instanceof IMob;
        }));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("page", this.getEnchantPage());
        ItemStackHelper.saveAllItems(compound, this.zirconItems);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.setEnchantPage(compound.getInt("page"));
        ItemStackHelper.loadAllItems(compound, this.zirconItems);
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR,
                GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY
        };
    }

    @Override
    public int generateHairVariant() {
        return this.rand.nextInt(3);
    }

    @Override
    public boolean UsesUniqueNames() {
        return false;
    }

    @Override
    public String NameFromColor(byte i) {
        return "zircon";
    }

    public String NameFromColor() {
        return "zircon";
    }

    public int generateOutfitVariant() {
        return this.rand.nextInt(4);
    }

    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY, Abilities.LUCK, Abilities.STERN, Abilities.ESSENCE_BREWER
        };
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[]{

        };
    }

    @Override
    public boolean generateIsEmotional() {
        return true;
    }

    @Override
    public byte EmotionThreshold() {
        return 50;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean isImmuneToFire(){
        return false;
    }

    public boolean hasSkinColorVariant(){
        return true;
    }

    public int[] NeglectedColors() {
        return new int[]{
                16, 17
        };
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public int getLuck() {
        return 6;
    }

    @Override
    public boolean hasVisorCosmeticOnly() {
        return true;
    }

    public void CyclePageForward(){
        if(this.getEnchantPage() == this.ENCHANTMENT_PAGES - 1){
            this.setEnchantPage(0);
        }
        else{
            this.setEnchantPage(this.getEnchantPage() + 1);
        }
    }

    public void CyclePageBackwards(){
        if(this.getEnchantPage() == 0){
            this.setEnchantPage(this.ENCHANTMENT_PAGES - 1);
        }
        else{
            this.setEnchantPage(this.getEnchantPage() - 1);
        }
    }

    public void setEnchantPage(int page){
        this.dataManager.set(EntityZircon.ENCHANT_PAGE, page);
    }

    public int getEnchantPage(){
        return this.dataManager.get(EntityZircon.ENCHANT_PAGE);
    }

    public void beginEnchant(){
        ItemStack lapis = this.getStackInSlot(0);
        ItemStack tool = this.getStackInSlot(1);
        int level = EntityZircon.getEnchantLevelFromLapisCount(lapis.getCount(), this);
        int xp = this.currentPlayer.isCreative() ? 0 : this.getXP(this.getDiscountFromStack(this.getStackInSlot(2))) < 0 ? 0 : this.getXP(this.getDiscountFromStack(this.getStackInSlot(2)));
        if(this.currentPlayer.experienceTotal >= xp) {
            if (tool.isEnchantable()) {
                if(level >= 1) {
                    if (tool.canApplyAtEnchantingTable(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()))) {
                        tool.addEnchantment(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()), level);
                        this.setInventorySlotContents(0, ItemStack.EMPTY);
                        this.setInventorySlotContents(2, ItemStack.EMPTY);
                        EntityShale.decreaseExp(this.currentPlayer, xp);
                    }
                    else{
                        this.currentPlayer.sendMessage(new TranslationTextComponent("messages.gempire.entity.enchant_not_apply"), UUID.randomUUID());
                        return;
                    }
                }
                else{
                    this.currentPlayer.sendMessage(new TranslationTextComponent("messages.gempire.entity.need_lapis"), UUID.randomUUID());
                    return;
                }
            }
            else{
                this.currentPlayer.sendMessage(new TranslationTextComponent("messages.gempire.entity.cant_enchant"), UUID.randomUUID());
                return;
            }
        }
        else{
            this.currentPlayer.sendMessage(new TranslationTextComponent("messages.gempire.entity.player_need_xp"), UUID.randomUUID());
            return;
        }
    }

    public int getXP(int discount){
        int level = getEnchantLevelFromLapisCount(this.getStackInSlot(0).getCount(), this);
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

    public static int getEnchantLevelFromLapisCount(int count, EntityZircon gem){
        int maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        int level = (int) Math.ceil(count * maxEnchant / 32);
        if(level >= maxEnchant){
            level = maxEnchant;
        }
        return level;
    }

    /*

    INVENTORY STUFF

    */

    public NonNullList<ItemStack> getZirconItems(){
        return this.zirconItems;
    }

    @Override
    public int getSizeInventory() {
        return EntityZircon.NUMBER_OF_SLOTS;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.getZirconItems().get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.getZirconItems(), index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.getZirconItems().set(index, stack);
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ZirconUIContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    @Override
    public boolean canOpenInventoryByDefault() {
        return true;
    }
}
