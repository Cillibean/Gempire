package com.gempire.entities.gems;

import com.gempire.container.PearlDefectiveUIContainer;
import com.gempire.container.PearlUIContainer;
import com.gempire.entities.ai.EntityAIFollowAssigned;
import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAISludged;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import org.checkerframework.checker.units.qual.C;

public class EntityPearl extends EntityVaryingGem {
    public static final int NUMBER_OF_SLOTS_PEARL = 58;
    public NonNullList<ItemStack> items1 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
    public NonNullList<ItemStack> items2 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
    public static EntityDataAccessor<Integer> PAGE = SynchedEntityData.<Integer>defineId(EntityPearl.class, EntityDataSerializers.INT);
    public EntityPearl(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.entityData.define(EntityPearl.PAGE, 0);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 1.5D);
    }

    @Override
    public Float baseXScale() {
        return 1F;
    }

    @Override
    public Float baseYScale() {
        return 1F;
    }

    @Override
    public Float baseZScale() {
        return 1F;
    }
    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_HARP;
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("page", this.getPage());
        this.saveItems(compound);
        System.out.println("add aditional save data");
        System.out.println(compound.getList("Items1", 10).get(0));
    }

    public void saveItems(CompoundTag compoundNBT){
        ListTag list1 = new ListTag();
        for(int i = 0; i < this.items1.size(); i++){
            list1.add(i, this.items1.get(i).save(new CompoundTag()));
        }
        ListTag list2 = new ListTag();
        for(int i = 0; i < this.items2.size(); i++){
            list2.add(i, this.items2.get(i).save(new CompoundTag()));
        }
        System.out.println(list1);
        System.out.println(list2);
        compoundNBT.put("Items1", list1);
        compoundNBT.put("Items2", list2);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.setPage(compound.getInt("page"));
        this.loadItems(compound);
    }

    public void loadItems(CompoundTag compoundNBT){
        ListTag list1 = compoundNBT.getList("Items1", 10);
        ListTag list2 = compoundNBT.getList("Items2", 10);
        NonNullList<ItemStack> newItems1 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
        NonNullList<ItemStack> newItems2 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
        for(int i = 0; i < list1.size(); ++i) {
            CompoundTag compoundnbt = list1.getCompound(i);
            newItems1.set(i, ItemStack.of(compoundnbt));
        }
        for(int i = 0; i < list2.size(); ++i) {
            CompoundTag compoundnbt = list2.getCompound(i);
            newItems2.set(i, ItemStack.of(compoundnbt));
        }
        System.out.println(newItems1);
        System.out.println(newItems2);
        this.items1 = newItems1;
        this.items2 = newItems2;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Mob.class, 6.0F, 1.0D, 1.2D, (mob)-> mob.getClassification(true)== MobCategory.MONSTER));
        this.goalSelector.addGoal(1, new EntityAISludged(this, 0.6));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkBothSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 1, false, false, this::checkSludged));
this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityGem.class, 6.0F, 1.0D, 1.2D, this::checkElseSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityCrawler.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityShambler.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityAbomination.class, 6.0F, 1.0D, 1.2D));    }

    @Override
    public int generateSkinVariant() {
        return this.getGemPlacement() == 5 ? 1 : 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.TOP_OF_HEAD, GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.NOSE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR, GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY,
                GemPlacements.LEFT_SHOULDER, GemPlacements.RIGHT_SHOULDER, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM,
                GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_ANKLE, GemPlacements.RIGHT_ANKLE, GemPlacements.LEFT_ARM, GemPlacements.RIGHT_ARM
        };
    }

    @Override
    public int[] NeglectedColors() {
        return new int[]{

        };
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(34);
    }

    @Override
    public int generateInsigniaColor() {
        return 0;
    }

    @Override
    public int generateOutfitColor() {
        return 0;
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return false;
    }

    public Abilities[] possibleAbilities(){
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }
    public Abilities[] definiteAbilities(){
        return new Abilities[]{

        };
    }

    public int generateSkinColorVariant() {
        return this.random.nextInt(16);
    }

    @Override
    public boolean UsesUniqueNames() {
        return false;
    }

    @Override
    public String NameFromColor(byte i) {
        return "";
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

    public boolean hasSkinColorVariant(){
        return true;
    }

    public int generateOutfitVariant(){
        return this.random.nextInt(52);
    }

    public int generateInsigniaVariant(){
        return this.random.nextInt(34);
    }

    public int generateRebelInsigniaVariant() {
        return this.random.nextInt(34);
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(2);
    }

    @Override
    public int baseFocus() {
        return 2;
    }

    @Override
    public boolean hasMarkings() {
        return false;
    }

    @Override
    public boolean hasMarkings2() {
        return false;
    }

    @Override
    public int maxMarkings() {
        return 1;
    }

    @Override
    public int maxMarkings2() {
        return 1;
    }

    public void setPage(int value){
        this.entityData.set(EntityPearl.PAGE, value);
    }

    public int getPage(){
        return this.entityData.get(EntityPearl.PAGE);
    }

    public void CyclePageForward(){
        if(this.getPage() == 1){
            this.setPage(0);
        }
        else{
            this.setPage(this.getPage() + 1);
        }
    }

    public void CyclePageBackwards(){
        if(this.getPage() == 0){
            this.setPage(1);
        }
        else{
            this.setPage(this.getPage() - 1);
        }
    }

    public void CycleHairForward(){
        if(this.getHairVariant() == this.getHairs() - 1){
            this.setHairVariant(0);
        }
        else{
            this.setHairVariant(this.getHairVariant() + 1);
        }
    }

    public void CycleHairBackwards(){
        if(this.getHairVariant() == 0){
            this.setHairVariant(this.getHairs() - 1);
        }
        else{
            this.setHairVariant(this.getHairVariant() - 1);
        }
    }

    public void CycleOutfitForward(){
        if(this.getOutfitVariant() == this.getOutfits() - 1){
            this.setOutfitVariant(0);
        }
        else{
            this.setOutfitVariant(this.getOutfitVariant() + 1);
        }
    }

    public void CycleOutfitBackwards(){
        if(this.getOutfitVariant() == 0){
            this.setOutfitVariant(this.getOutfits() - 1);
        }
        else{
            this.setOutfitVariant(this.getOutfitVariant() - 1);
        }
    }

    public void CycleInsigniaForward(){
        if(this.getInsigniaVariant() == this.getInsignias() - 1){
            this.setInsigniaVariant(0);
        }
        else{
            this.setInsigniaVariant(this.getInsigniaVariant() + 1);
        }
    }
    public boolean getIsCut()
    {
        return false;
    }

    public void CycleInsigniaBackwards(){
        if(this.getInsigniaVariant() == 0){
            this.setInsigniaVariant(this.getInsignias() - 1);
        }
        else{
            this.setInsigniaVariant(this.getInsigniaVariant() - 1);
        }
    }

    public int getHairs(){
        return 34;
    }

    public int getOutfits(){
        return 52;
    }

    public int getInsignias(){
        return 34;
    }

    @Override
    public boolean canOpenInventoryByDefault() {
        return true;
    }

    /*

    INVENTORY STUFF

    */

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return super.canPlaceItem(index, stack) && stack.getItem() != Blocks.SHULKER_BOX.asItem();
    }

    @Override
    public int getContainerSize() {
        return EntityPearl.NUMBER_OF_SLOTS_PEARL;
    }

    @Override
    public ItemStack getItem(int index) {
        ItemStack stack = ItemStack.EMPTY;
        if(this.getPage() == 0) {
            return this.getItems1().get(index);
        }
        else if(this.getPage() == 1){
            return this.getItems2().get(index);
        }
        return stack;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack stack = ItemStack.EMPTY;
        if(this.getPage() == 0) {
            return ContainerHelper.removeItem(this.getItems1(), index, count);
        }
        else if(this.getPage() == 1){
            return ContainerHelper.removeItem(this.getItems2(), index, count);
        }
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return null;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if(this.getPage() == 0) {
            this.getItems1().set(index, stack);
        }
        else if(this.getPage() == 1){
            this.getItems2().set(index, stack);
        }
        int ind = index;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        if (this.isDefective()) {
            return new PearlDefectiveUIContainer(p_createMenu_1_, p_createMenu_2_, this);
        } else {
            return new PearlUIContainer(p_createMenu_1_, p_createMenu_2_, this);
        }
    }

    public NonNullList<ItemStack> getItems1(){
        return this.items1;
    }

    public NonNullList<ItemStack> getItems2(){
        return this.items2;
    }

    public int generateHardness() {
        return 4;
    }

    @Override
    public int exitHoleSize() {
        return 0;
    }
}
