package com.gempire.entities.gems;

import com.gempire.config.GempireServerConfigs;
import com.gempire.container.ZirconUIContainer;
import com.gempire.entities.abilities.AbilityStern;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.init.ModEnchants;
import com.gempire.util.GemPlacements;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

import java.util.ArrayList;
import java.util.Map;

public class EntityZircon extends EntityVaryingGem {
    public static final int NUMBER_OF_SLOTS = 3;
    public NonNullList<ItemStack> zirconItems = NonNullList.withSize(EntityZircon.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public static final EntityDataAccessor<Integer> ENCHANT_PAGE = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> ENCHANT_MIN = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Boolean> ENCHANT_PAGE_DEFINED = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);

    public int ENCHANTMENT_PAGES = 0;

    public EntityZircon(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.entityData.define(EntityZircon.ENCHANT_PAGE, 0);
        this.entityData.define(EntityZircon.ENCHANT_MIN, 0);
        this.entityData.define(EntityZircon.ENCHANT_PAGE_DEFINED, false);
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
        return SoundEvents.NOTE_BLOCK_PLING.get();
    }

    @Override
    public Float baseXScale() {
        return 1.2F;
    }

    @Override
    public Float baseYScale() {
        return 1.2F;
    }

    @Override
    public Float baseZScale() {
        return 1.2F;
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new FloatGoal(this));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIStay(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Mob.class, 6.0F, 1.0D, 1.2D, (mob)-> mob.getClassification(true)== MobCategory.MONSTER));
        this.goalSelector.addGoal(1, new EntityAISludged(this, 0.6));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkBothSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 1, false, false, this::checkSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityGem.class, 6.0F, 1.0D, 1.2D, this::checkElseSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityCrawler.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityShambler.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityAbomination.class, 6.0F, 1.0D, 1.2D));    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("page", this.getEnchantPage());
        compound.putInt("min", this.getEnchantMin());
        compound.putBoolean("pagedefined", this.getEnchantPageDefined());
        ContainerHelper.saveAllItems(compound, this.zirconItems);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.setEnchantPage(compound.getInt("page"));
        this.setEnchantMin(compound.getInt("min"));
        this.setEnchantPageDefined(compound.getBoolean("pagedefined"));
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

    public int generateRebelInsigniaVariant() {
        return this.getRebelOutfitVariant();
    }

    public ArrayList<Ability> possibleAbilities(){
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityZilch());
        arrayList.add(new AbilityStern());
        return arrayList;
    }

    public ArrayList<Ability> definiteAbilities() {
        return new ArrayList<>();
    }

    @Override
    public boolean generateIsEmotional() {
        return GempireServerConfigs.OUTBURSTS.get();
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
    public int generateVisorVariant() {
        return this.random.nextInt(3);
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

    public int generateEnchantPage(){
        if (!this.isPrimary()) {
            return this.random.nextInt(ModEnchants.VANILLA_ENCHANTMENTS.size());
        } else {
            return this.random.nextInt(ModEnchants.GEMPIRE_ENCHANTMENTS.size());
        }
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
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(tool);
        int level = EntityZircon.getEnchantLevelFromLapisCount(lapis.getCount(), this);
        int xp = this.currentPlayer.isCreative() ? 0 : Math.max(this.getXP(this.getDiscountFromStack(this.getItem(2))), 0);
        Item item = tool.getItem();
        Enchantment enchantment = ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage());
        System.out.println(currentPlayer.totalExperience);
        System.out.println(xp);
        if(this.currentPlayer.totalExperience >= xp) {
            if (!tool.isEmpty()) {
                if (tool.getEnchantmentLevel(enchantment) == 0) {
                    if (tool.isEnchantable() || item instanceof BookItem || item instanceof EnchantedBookItem || enchantment.canEnchant(tool)) {
                        if (level >= 1) {
                            if (item instanceof BookItem) {
                                if (!this.isPrimary()) {
                                    this.setItem(1, Items.ENCHANTED_BOOK.getDefaultInstance());
                                    EnchantedBookItem.addEnchantment(this.getItem(1), new EnchantmentInstance(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()), level));
                                    this.setItem(0, ItemStack.EMPTY);
                                    this.setItem(2, ItemStack.EMPTY);
                                    decreaseExp(this.currentPlayer, xp, items.get(1), true);
                                } else {
                                    this.setItem(1, Items.ENCHANTED_BOOK.getDefaultInstance());
                                    EnchantedBookItem.addEnchantment(this.getItem(1), new EnchantmentInstance(ModEnchants.GEMPIRE_ENCHANTMENTS.get(this.getEnchantPage()), level));
                                    this.setItem(0, ItemStack.EMPTY);
                                    this.setItem(2, ItemStack.EMPTY);
                                    decreaseExp(this.currentPlayer, xp, items.get(1), true);
                                }
                            } else if (item instanceof EnchantedBookItem) {
                                if (!this.isPrimary()) {
                                    EnchantedBookItem.addEnchantment(tool, new EnchantmentInstance(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()), level));
                                    this.setItem(0, ItemStack.EMPTY);
                                    this.setItem(2, ItemStack.EMPTY);
                                    decreaseExp(this.currentPlayer, xp, items.get(1), true);
                                } else {
                                    EnchantedBookItem.addEnchantment(tool, new EnchantmentInstance(ModEnchants.GEMPIRE_ENCHANTMENTS.get(this.getEnchantPage()), level));
                                    this.setItem(0, ItemStack.EMPTY);
                                    this.setItem(2, ItemStack.EMPTY);
                                    decreaseExp(this.currentPlayer, xp, items.get(1), true);
                                }
                            } else {
                                if (!this.isPrimary()) {
                                    tool.enchant(ModEnchants.VANILLA_ENCHANTMENTS.get(this.getEnchantPage()), level);
                                    this.setItem(0, ItemStack.EMPTY);
                                    this.setItem(2, ItemStack.EMPTY);
                                    decreaseExp(this.currentPlayer, xp, items.get(1), true);
                                } else {
                                    tool.enchant(ModEnchants.GEMPIRE_ENCHANTMENTS.get(this.getEnchantPage()), level);
                                    this.setItem(0, ItemStack.EMPTY);
                                    this.setItem(2, ItemStack.EMPTY);
                                    decreaseExp(this.currentPlayer, xp, items.get(1), true);
                                }
                            }
                        } else {
                            this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.need_lapis"));
                        }
                    } else {
                        this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.cant_enchant"));
                    }
                } else {
                    this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.cant_enchant_air"));
                }
            }
        }
        else{
            this.currentPlayer.sendSystemMessage(Component.translatable("messages.gempire.entity.player_need_xp"));
        }
    }

    public int getXP(int discount){
        int level = getEnchantLevelFromLapisCount(this.getItem(0).getCount(), this);
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

    public static int getEnchantLevelFromLapisCount(int count, EntityZircon gem){
        int maxEnchant = 0;
        if (gem.isPrimary()) {
            maxEnchant = 1;
        } else {
            maxEnchant = ModEnchants.VANILLA_ENCHANTMENTS.get(gem.getEnchantPage()).getMaxLevel();
        }
        int level = (int) Math.ceil(count * maxEnchant / 32);
        if (level >= maxEnchant) {
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

    public boolean getEnchantPageDefined() {
        return this.entityData.get(ENCHANT_PAGE_DEFINED);
    }

    public void setEnchantPageDefined(boolean enchantPageDefined) {
        this.entityData.set(ENCHANT_PAGE_DEFINED, enchantPageDefined);
    }

    public int generateHardness() {
        return 7;
    }

    @Override
    public int exitHoleSize() {
        return 3;
    }
}
