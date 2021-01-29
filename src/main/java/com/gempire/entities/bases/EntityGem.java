package com.gempire.entities.bases;

import com.gempire.init.ModItems;
import com.gempire.items.ItemGem;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityGem extends CreatureEntity {
    public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final DataParameter<Integer> SKIN_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> HAIR_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> SKIN_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> HAIR_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_PLACEMENT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> OUTFIT_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> INSIGNIA_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);

    public boolean isOwned = false;
    public int movementType = 1;

    public EntityGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.dataManager.register(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        this.dataManager.register(EntityGem.SKIN_COLOR, 0);
        this.dataManager.register(EntityGem.HAIR_COLOR, 0);
        this.dataManager.register(EntityGem.SKIN_VARIANT, 0);
        this.dataManager.register(EntityGem.HAIR_VARIANT, 0);
        this.dataManager.register(EntityGem.GEM_PLACEMENT, 0);
        this.dataManager.register(EntityGem.GEM_COLOR, 0);
        this.dataManager.register(EntityGem.OUTFIT_COLOR, 0);
        this.dataManager.register(EntityGem.INSIGNIA_COLOR, 0);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setSkinColor(this.generateSkinColor());
        this.setHairColor(this.generateHairColor());
        this.setSkinVariant(this.generateSkinVariant());
        this.setHairVariant(this.generateHairVariant());
        this.setGemPlacement(this.generateGemPlacement());
        this.setGemColor(this.generateGemColor());
        this.setOutfitColor(this.generateOutfitColor());
        this.setInsigniaColor(this.generateInsigniaColor());
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("isOwned", this.getOwned());
        compound.putUniqueId("ownerID", this.getOwnerID());
        compound.putInt("movementType", this.getMovementType());
        compound.putInt("skinColor", this.getSkinColor());
        compound.putInt("hairColor", this.getHairColor());
        compound.putInt("skinVariant", this.getSkinVariant());
        compound.putInt("hairVariant", this.getHairVariant());
        compound.putInt("gemPlacement", this.getGemPlacement());
        compound.putInt("gemColor", this.getGemColor());
        compound.putInt("outfitColor", this.getOutfitColor());
        compound.putInt("insigniaColor", this.getInsigniaColor());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.setIsOwned(compound.getBoolean("isOwned"));
        this.setOwnerID(compound.getUniqueId("ownerID"));
        this.setMovementType(compound.getInt("movementType"));
        this.setSkinColor(compound.getInt("skinColor"));
        this.setHairColor(compound.getInt("hairColor"));
        this.setSkinVariant(compound.getInt("skinVariant"));
        this.setHairVariant(compound.getInt("hairVariant"));
        this.setGemPlacement(compound.getInt("gemPlacement"));
        this.setGemColor(compound.getInt("gemColor"));
        this.setOutfitColor(compound.getInt("outfitColor"));
        this.setInsigniaColor(compound.getInt("insigniaColor"));
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
        if(player.world.isRemote){
            return super.applyPlayerInteraction(player, vec, hand);
        }
        if(hand == Hand.MAIN_HAND && player.getHeldItemMainhand() == ItemStack.EMPTY) {
            if (this.isOwner(player)) {
                if (player.isSneaking()) {
                    player.sendMessage(new StringTextComponent(this.getGemPlacement() + ""), this.getUniqueID());
                    this.cycleMovementAI(player);
                }
            } else {
                this.setOwned(true, player.getUniqueID());
                this.setMovementType(2);
                player.sendMessage(new TranslationTextComponent("messages.gempire.entity.claimed"), this.getUniqueID());
                return super.applyPlayerInteraction(player, vec, hand);
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    public void cycleMovementAI(PlayerEntity player){
        //Cycles through the various movement types.
        this.navigator.clearPath();
        if(this.getMovementType() < 2){
            this.addMovementType(1);
            switch(this.getMovementType()){
                case 1:
                    player.sendMessage(new TranslationTextComponent("messages.gempire.entity.wander"), this.getUniqueID());
                    return;
                case 2:
                    player.sendMessage(new TranslationTextComponent("messages.gempire.entity.follow"), this.getUniqueID());
                    return;
                default:
                    player.sendMessage(new TranslationTextComponent("messages.gempire.entity.stay"), this.getUniqueID());
                    return;
            }
        }
        else if(this.getMovementType() == 2){
            this.setMovementType(0);
            player.sendMessage(new TranslationTextComponent("messages.gempire.entity.stay"), this.getUniqueID());
            return;
        }
    }

    @Override
    public void onDeath(DamageSource source){
        //When the Gem dies.
        if(!this.world.isRemote){
            BasicParticleType particle = ParticleTypes.EXPLOSION;
            this.world.addOptionalParticle(particle, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
            ItemStack stack = new ItemStack(this.getGemItem());
            ((ItemGem) stack.getItem()).setData(this, stack);
            ItemEntity item = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), stack);
            item.setNoDespawn();
            this.world.addEntity(item);
        }
        super.onDeath(source);
    }

    public Item getGemItem() {
        ItemGem gem = (ItemGem)ModItems.PEBBLE_GEM.get();
        try {
            gem = (ItemGem) ModItems.class.getField((this.getName().getString().replaceAll(".+?Entity", "") + "_gem").toUpperCase()).get(null);
        } catch(Exception e){
            e.printStackTrace();
        }
        return gem;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public boolean canDespawn(double xix){
        return false;
    }

    //GETTERS AND SETTERS!!!

    public void setIsOwned(boolean value){
        this.isOwned = value;
    }

    public void setOwned(boolean value, @Nullable UUID ID){
        this.isOwned = value;
        if(value) {
            this.dataManager.set(EntityGem.OWNER_ID, Optional.ofNullable(ID));
        } else{
            this.dataManager.set(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        }
    }

    public void setOwnerID(UUID ID){
        this.dataManager.set(EntityGem.OWNER_ID, Optional.ofNullable(ID));
    }

    public boolean getOwned(){
        return this.isOwned;
    }

    public UUID getOwnerID(){
        return this.dataManager.get(EntityGem.OWNER_ID).get();
    }

    public boolean isOwner(LivingEntity entity){
        if(this.getOwnerID().equals(entity.getUniqueID())){
            return true;
        }
        return false;
    }

    public int getMovementType(){
        //Gets the movement type variable.
        return this.movementType;
    }

    public void setMovementType(int value){
        //Sets the movement type variable.
        this.movementType = value;
    }

    public void addMovementType(int value){
        //Adds the value to the movement type variable.
        this.movementType += value;
    }

    public int getSkinColor(){
        return this.dataManager.get(EntityGem.SKIN_COLOR);
    }

    public void setSkinColor(int value){
        this.dataManager.set(EntityGem.SKIN_COLOR, value);
    }

    public abstract int generateSkinColor();

    public abstract int generateSkinVariant();

    public int getSkinVariant(){
        return this.dataManager.get(EntityGem.SKIN_VARIANT);
    }

    public void setSkinVariant(int value){
        this.dataManager.set(EntityGem.SKIN_VARIANT, value);
    }

    public int getGemPlacement(){
        return this.dataManager.get(EntityGem.GEM_PLACEMENT);
    }

    public GemPlacements getGemPlacementE(){
        return GemPlacements.getPlacement(this.getGemPlacement());
    }

    public void setGemPlacement(int value){
        this.dataManager.set(EntityGem.GEM_PLACEMENT, value);
    }

    public abstract GemPlacements[] getPlacements();

    public int generateGemPlacement(){
        return this.getPlacements()[this.rand.nextInt(this.getPlacements().length)].id;
    }

    public int getHairColor(){
        return this.dataManager.get(EntityGem.HAIR_COLOR);
    }

    public void setHairColor(int value){
        this.dataManager.set(EntityGem.HAIR_COLOR, value);
    }

    public abstract int generateHairColor();


    public int getHairVariant(){
        return this.dataManager.get(EntityGem.HAIR_VARIANT);
    }

    public void setHairVariant(int value){
        this.dataManager.set(EntityGem.HAIR_VARIANT, value);
    }

    public abstract int generateHairVariant();

    public int getGemColor(){
        return this.dataManager.get(EntityGem.GEM_COLOR);
    }

    public void setGemColor(int value){
        this.dataManager.set(EntityGem.GEM_COLOR, value);
    }

    public abstract int generateGemColor();

    public int getOutfitColor(){
        return this.dataManager.get(EntityGem.OUTFIT_COLOR);
    }

    public void setOutfitColor(int value){
        this.dataManager.set(EntityGem.OUTFIT_COLOR, value);
    }

    public int generateOutfitColor(){
        return 0x555F65;
    }

    public abstract int getOutfitVariant();

    public int getInsigniaColor(){
        return this.dataManager.get(EntityGem.INSIGNIA_COLOR);
    }

    public void setInsigniaColor(int value){
        this.dataManager.set(EntityGem.INSIGNIA_COLOR, value);
    }

    public abstract int generateInsigniaColor();
}
