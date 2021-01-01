package com.gempire.entities.gems;

import com.gempire.init.ModItems;
import com.gempire.items.ItemGem;
import net.minecraft.entity.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityGem extends CreatureEntity {
    public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final DataParameter<Integer> SKIN_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);

    //public Item droppedGemstone;

    public boolean isOwned = false;
    public int movementType = 1;

    public EntityGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.dataManager.register(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        this.dataManager.register(EntityGem.SKIN_COLOR, 0);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setSkinColor(this.generateSkinColor());
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("isOwned", this.getOwned());
        compound.putUniqueId("ownerID", this.getOwnerID());
        compound.putInt("movementType", this.getMovementType());
        compound.putInt("skinColor", this.getSkinColor());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        //this.setOwned(compound.getBoolean("isOwned"), compound.getUniqueId("ownerID"));
        this.setOwnerID(compound.getUniqueId("ownerID"));
        this.setMovementType(compound.getInt("movementType"));
        if(compound.contains("skinColor")) {
            if (compound.getInt("skinColor") == 0) {
                this.setSkinColor(this.generateSkinColor());
            } else {
                this.setSkinColor(compound.getInt("skinColor"));
            }
        }else{
            this.setSkinColor(this.generateSkinColor());
        }
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
        if(player.world.isRemote){
            return super.applyPlayerInteraction(player, vec, hand);
        }
        if(hand == Hand.MAIN_HAND && player.getHeldItemMainhand() == ItemStack.EMPTY){
            if(!player.isSneaking()){
                if(!this.getOwned()) {
                    this.setOwned(true, player.getUniqueID());
                    this.setMovementType(2);
                    player.sendMessage(new TranslationTextComponent("messages.gempire.entity.claimed"), this.getUniqueID());
                    return super.applyPlayerInteraction(player, vec, hand);
                }
            }
            else {
                if(this.isOwner(player)){
                    this.cycleMovementAI(player);
                }
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
}
