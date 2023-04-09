package com.gempire.entities.gems;

import com.gempire.container.GemUIContainer;
import com.gempire.container.ZirconUIContainer;
import com.gempire.entities.ai.EntityAIFollowAssigned;
import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.ModEnchants;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.item.ItemStack;

public class EntityZircon extends EntityVaryingGem {
    public static final int NUMBER_OF_SLOTS = 3;
    public NonNullList<ItemStack> zirconItems = NonNullList.withSize(EntityZircon.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public static final EntityDataAccessor<Integer> ENCHANT_PAGE = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> ENCHANT_MIN = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public int ENCHANTMENT_PAGES = 0;

    public EntityZircon(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.entityData.define(EntityZircon.ENCHANT_PAGE, 0);
        this.entityData.define(EntityZircon.ENCHANT_MIN, 0);
        this.ENCHANTMENT_PAGES = ModEnchants.VANILLA_ENCHANTMENTS.size();
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }
    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_PLING;
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new FloatGoal(this));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Mob.class, 6.0F, 1.0D, 1.2D, (mob)-> mob.getClassification(true)== MobCategory.MONSTER));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("page", this.getEnchantPage());
        compound.putInt("min", this.getEnchantMin());
        ContainerHelper.saveAllItems(compound, this.zirconItems);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.setEnchantPage(compound.getInt("page"));
        this.setEnchantMin(compound.getInt("min"));
        ContainerHelper.loadAllItems(compound, this.zirconItems);
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.FOREHEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_SHOULDER, GemPlacements.RIGHT_SHOULDER,
                GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.BELLY,
                GemPlacements.LEFT_KNEE, GemPlacements.RIGHT_KNEE, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_ANKLE,
                GemPlacements.RIGHT_ANKLE, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM, GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH
        };
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(3);
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
        return this.random.nextInt(4);
    }

    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY, Abilities.STERN
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
        return 15;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean fireImmune(){
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
            return;
        }
        else if(this.getEnchantPage() == this.getEnchantMin() + 3){
            this.setEnchantPage(0);
        }
        else{
            this.setEnchantPage(this.getEnchantPage() + 1);
        }
    }

    public void CyclePageBackwards(){
        if(this.getEnchantPage() == 0){
            this.setEnchantPage(this.getEnchantMin() + 3);
            return;
        }
        else if(this.getEnchantPage() == this.getEnchantMin()){
            this.setEnchantPage(this.getEnchantMin() + 3);
        }
        else{
            this.setEnchantPage(this.getEnchantPage() - 1);
        }
    }

    public void setEnchantPage(int page){
        this.entityData.set(EntityZircon.ENCHANT_PAGE, page);
    }

    public int getEnchantPage(){
        return this.entityData.get(EntityZircon.ENCHANT_PAGE);
    }

    public void setEnchantMin(int page){
        this.entityData.set(EntityZircon.ENCHANT_MIN, page);
    }

    public int getEnchantMin(){
        return this.entityData.get(EntityZircon.ENCHANT_PAGE);
    }

    public void beginEnchant(){
        ItemStack lapis = this.getItem(0);
        ItemStack tool = this.getItem(1);
        int level = EntityZircon.getEnchantLevelFromLapisCount(lapis.getCount(), this);
        int xp = this.currentPlayer.isCreative() ? 0 : Math.max(this.getXP(this.getDiscountFromStack(this.getItem(2))), 0);
        if(this.currentPlayer.totalExperience >= xp) {
            if (tool.isEnchantable()) {
                if(level >= 1) {
                    if (tool.canApplyAtEnchantingTable(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()))) {
                        tool.enchant(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()), level);
                        this.setItem(0, ItemStack.EMPTY);
                        this.setItem(2, ItemStack.EMPTY);
                        decreaseExp(this.currentPlayer, xp);
                    }
                    else{
                        this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.enchant_not_apply"));
                        return;
                    }
                }
                else{
                    this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.need_lapis"));
                    return;
                }
            }
            else{
                this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.cant_enchant"));
                return;
            }
        }
        else{
            this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.player_need_xp"));
            return;
        }
    }

    public int getXP(int discount){
        int level = getEnchantLevelFromLapisCount(this.getItem(0).getCount(), this);
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
    public int getContainerSize() {
        return EntityZircon.NUMBER_OF_SLOTS;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.getZirconItems().get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.getZirconItems(), index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return null;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.getZirconItems().set(index, stack);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new ZirconUIContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    @Override
    public boolean canOpenInventoryByDefault() {
        return true;
    }
}
