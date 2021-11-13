package com.gempire.entities.gems;

import com.gempire.container.GemUIContainer;
import com.gempire.container.PearlUIContainer;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class EntityPearl extends EntityVaryingGem {
    public static final int NUMBER_OF_SLOTS_PEARL = 58;
    public NonNullList<ItemStack> items1 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
    public NonNullList<ItemStack> items2 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
    public NonNullList<ItemStack> items3 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
    public NonNullList<ItemStack> items4 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
    public static DataParameter<Integer> PAGE = EntityDataManager.<Integer>createKey(EntityPearl.class, DataSerializers.VARINT);

    public EntityPearl(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.dataManager.register(EntityPearl.PAGE, 0);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.5D);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("page", this.getPage());
        this.saveItems(compound);
    }

    public void saveItems(CompoundNBT compoundNBT){
        ListNBT list1 = new ListNBT();
        for(int i = 0; i < this.items1.size(); i++){
            list1.add(i, this.items1.get(i).write(new CompoundNBT()));
        }
        ListNBT list2 = new ListNBT();
        for(int i = 0; i < this.items2.size(); i++){
            list2.add(i, this.items2.get(i).write(new CompoundNBT()));
        }
        ListNBT list3 = new ListNBT();
        for(int i = 0; i < this.items3.size(); i++){
            list3.add(i, this.items3.get(i).write(new CompoundNBT()));
        }
        ListNBT list4 = new ListNBT();
        for(int i = 0; i < this.items4.size(); i++){
            list4.add(i, this.items4.get(i).write(new CompoundNBT()));
        }
        compoundNBT.put("Items1", list1);
        compoundNBT.put("Items2", list2);
        compoundNBT.put("Items3", list3);
        compoundNBT.put("Items4", list4);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.setPage(compound.getInt("page"));
        this.loadItems(compound);
    }

    public void loadItems(CompoundNBT compoundNBT){
        ListNBT list1 = compoundNBT.getList("Items1", 10);
        ListNBT list2 = compoundNBT.getList("Items2", 10);
        ListNBT list3 = compoundNBT.getList("Items3", 10);
        ListNBT list4 = compoundNBT.getList("Items4", 10);
        NonNullList<ItemStack> newItems1 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
        NonNullList<ItemStack> newItems2 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
        NonNullList<ItemStack> newItems3 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
        NonNullList<ItemStack> newItems4 = NonNullList.withSize(EntityPearl.NUMBER_OF_SLOTS_PEARL, ItemStack.EMPTY);
        for(int i = 0; i < list1.size(); ++i) {
            CompoundNBT compoundnbt = list1.getCompound(i);
            newItems1.set(i, ItemStack.read(compoundnbt));
        }
        for(int i = 0; i < list2.size(); ++i) {
            CompoundNBT compoundnbt = list2.getCompound(i);
            newItems2.set(i, ItemStack.read(compoundnbt));
        }
        for(int i = 0; i < list3.size(); ++i) {
            CompoundNBT compoundnbt = list3.getCompound(i);
            newItems3.set(i, ItemStack.read(compoundnbt));
        }
        for(int i = 0; i < list4.size(); ++i) {
            CompoundNBT compoundnbt = list4.getCompound(i);
            newItems4.set(i, ItemStack.read(compoundnbt));
        }
        this.items1 = newItems1;
        this.items2 = newItems2;
        this.items3 = newItems3;
        this.items4 = newItems4;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new SwimGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
    }

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
        return this.rand.nextInt(34);
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
        return this.rand.nextInt(16);
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
        return this.rand.nextInt(52);
    }

    public int generateInsigniaVariant(){
        return this.rand.nextInt(34);
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
        this.dataManager.set(EntityPearl.PAGE, value);
    }

    public int getPage(){
        return this.dataManager.get(EntityPearl.PAGE);
    }

    public void CyclePageForward(){
        if(this.getPage() == 3){
            this.setPage(0);
        }
        else{
            this.setPage(this.getPage() + 1);
        }
    }

    public void CyclePageBackwards(){
        if(this.getPage() == 0){
            this.setPage(3);
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
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return super.isItemValidForSlot(index, stack) && stack.getItem() != Blocks.SHULKER_BOX.asItem();
    }

    @Override
    public int getSizeInventory() {
        return EntityPearl.NUMBER_OF_SLOTS_PEARL;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        ItemStack stack = ItemStack.EMPTY;
        if(this.getPage() == 0) {
            return this.getItems1().get(index - 36);
        }
        else if(this.getPage() == 1){
            return this.getItems2().get(index - 36);
        }
        else if(this.getPage() == 2){
            return this.getItems3().get(index - 36);
        }
        else if(this.getPage() == 3){
            return this.getItems4().get(index - 36);
        }
        return stack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = ItemStack.EMPTY;
        if(this.getPage() == 0) {
            return ItemStackHelper.getAndSplit(this.getItems1(), index - 36, count);
        }
        else if(this.getPage() == 1){
            return ItemStackHelper.getAndSplit(this.getItems2(), index - 36, count);
        }
        else if(this.getPage() == 2){
            return ItemStackHelper.getAndSplit(this.getItems3(), index - 36, count);
        }
        else if(this.getPage() == 3){
            return ItemStackHelper.getAndSplit(this.getItems4(), index - 36, count);
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if(this.getPage() == 0) {
            this.getItems1().set(index - 36, stack);
        }
        else if(this.getPage() == 1){
            this.getItems2().set(index - 36, stack);
        }
        else if(this.getPage() == 2){
            this.getItems3().set(index - 36, stack);
        }
        else if(this.getPage() == 3){
            this.getItems4().set(index - 36, stack);
        }
        int ind = index - 36;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new PearlUIContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    public NonNullList<ItemStack> getItems1(){
        return this.items1;
    }

    public NonNullList<ItemStack> getItems2(){
        return this.items2;
    }

    public NonNullList<ItemStack> getItems3(){
        return this.items3;
    }

    public NonNullList<ItemStack> getItems4(){
        return this.items4;
    }
}
