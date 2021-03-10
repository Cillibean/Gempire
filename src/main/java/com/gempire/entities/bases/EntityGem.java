package com.gempire.entities.bases;

import com.gempire.entities.abilities.Ability;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import com.gempire.items.ItemGem;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
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
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityGem extends CreatureEntity {
    public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final DataParameter<Boolean> OWNED = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> PRIMARY = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> DEFECTIVE = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> EMOTIONAL = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> SKIN_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> HAIR_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> SKIN_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> SKIN_COLOR_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> HAIR_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_PLACEMENT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> OUTFIT_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> INSIGNIA_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> ABILITY_SLOTS = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<String> ABILITIES = EntityDataManager.<String>createKey(EntityGem.class, DataSerializers.STRING);
    public static ArrayList<Ability> ABILITY_POWERS = new ArrayList<>();

    public byte movementType = 1;
    public byte emotionMeter = 0;
    public int initalSkinVariant = 0;
    public boolean setSkinVariantOnInitialSpawn = true;

    public EntityGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.dataManager.register(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        this.dataManager.register(EntityGem.OWNED, false);
        this.dataManager.register(EntityGem.PRIMARY, false);
        this.dataManager.register(EntityGem.DEFECTIVE, false);
        this.dataManager.register(EntityGem.EMOTIONAL, false);
        this.dataManager.register(EntityGem.SKIN_COLOR, 0);
        this.dataManager.register(EntityGem.HAIR_COLOR, 0);
        this.dataManager.register(EntityGem.SKIN_VARIANT, 0);
        this.dataManager.register(EntityGem.SKIN_COLOR_VARIANT, 0);
        this.dataManager.register(EntityGem.HAIR_VARIANT, 0);
        this.dataManager.register(EntityGem.GEM_PLACEMENT, 0);
        this.dataManager.register(EntityGem.GEM_COLOR, 0);
        this.dataManager.register(EntityGem.OUTFIT_COLOR, 0);
        this.dataManager.register(EntityGem.INSIGNIA_COLOR, 0);
        this.dataManager.register(EntityGem.ABILITY_SLOTS, 1);
        this.dataManager.register(EntityGem.ABILITIES, "-1");
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setSkinVariant(this.generateSkinVariant());
        if(this.setSkinVariantOnInitialSpawn) {
            this.setSkinColorVariant(this.generateSkinColorVariant());
        } else this.setSkinColorVariant(this.initalSkinVariant);
        this.setHairVariant(this.generateHairVariant());
        this.setGemPlacement(this.generateGemPlacement());
        this.setSkinColor(this.generateSkinColor());
        this.setHairColor(this.generateHairColor());
        this.setGemColor(this.generateGemColor());
        this.setOutfitColor(this.generateOutfitColor());
        this.setInsigniaColor(this.generateInsigniaColor());
        this.setAbilitySlots(this.generateAbilitySlots());
        this.setAbilites(this.generateAbilities());
        this.setEmotional(this.generateIsEmotional());
        this.setAbilityPowers(this.findAbilities(this.getAbilites()));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("abilities", this.getAbilites());
        compound.putBoolean("emotional", this.isEmotional());
        compound.putBoolean("isOwned", this.getOwned());
        compound.putUniqueId("ownerID", this.getOwnerID());
        compound.putByte("movementType", this.getMovementType());
        compound.putInt("skinColor", this.getSkinColor());
        compound.putInt("hairColor", this.getHairColor());
        compound.putInt("skinVariant", this.getSkinVariant());
        compound.putInt("skinColorVariant", this.getSkinColorVariant());
        compound.putInt("hairVariant", this.getHairVariant());
        compound.putInt("gemPlacement", this.getGemPlacement());
        compound.putInt("gemColor", this.getGemColor());
        compound.putInt("outfitColor", this.getOutfitColor());
        compound.putInt("insigniaColor", this.getInsigniaColor());
        compound.putInt("abilitySlots", this.getAbilitySlots());
        compound.putByte("emotionMeter", this.emotionMeter);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.setAbilites(compound.getString("abilities"));
        this.setEmotional(compound.getBoolean("emotional"));
        this.setIsOwned(compound.getBoolean("isOwned"));
        if(compound.contains("ownerID")) this.setOwnerID(compound.getUniqueId("ownerID"));
        this.setMovementType(compound.getByte("movementType"));
        this.setSkinColor(compound.getInt("skinColor"));
        this.setHairColor(compound.getInt("hairColor"));
        this.setSkinVariant(compound.getInt("skinVariant"));
        this.setSkinColorVariant(compound.getInt("skinColorVariant"));
        this.setHairVariant(compound.getInt("hairVariant"));
        this.setGemPlacement(compound.getInt("gemPlacement"));
        this.setGemColor(compound.getInt("gemColor"));
        this.setOutfitColor(compound.getInt("outfitColor"));
        this.setInsigniaColor(compound.getInt("insigniaColor"));
        this.setAbilitySlots(compound.getInt("abilitySlots"));
        this.emotionMeter = compound.getByte("emotionMeter");
        this.setMovementType(compound.getByte("movementType"));
        this.setAbilityPowers(this.findAbilities(compound.getString("abilities")));
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
        if(player.world.isRemote){
            return super.applyPlayerInteraction(player, vec, hand);
        }
        //This part of the code checks if the player has a blank hand
        if(hand == Hand.MAIN_HAND && player.getHeldItemMainhand() == ItemStack.EMPTY) {
            //player.sendMessage(new StringTextComponent(this.getAbilites()), this.getUniqueID());
            //player.sendMessage(new StringTextComponent(this.initalSkinVariant + ""), this.getUniqueID());
            //Test to see if player is the owner
            if (this.isOwner(player)) {
                if (player.isSneaking()) {
                    this.cycleMovementAI(player);
                }
            } else {
                //Test to see if the gem has an owner
                if(!this.getOwned()) {
                    this.setOwned(true, PlayerEntity.getUUID(player.getGameProfile()));
                    this.setMovementType((byte) 2);
                    player.sendMessage(new TranslationTextComponent("messages.gempire.entity.claimed"), this.getUniqueID());
                    return super.applyPlayerInteraction(player, vec, hand);
                }
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    /*
    0 is stay still
    1 is wander
    2 is follow
    3 is stay
     */
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
            this.setMovementType((byte) 0);
            player.sendMessage(new TranslationTextComponent("messages.gempire.entity.stay"), this.getUniqueID());
            return;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount){
        if(this.world.isRemote){
            return super.attackEntityFrom(source, amount);
        }
        if(this.isEmotional() && !source.isExplosion() && !source.isFireDamage()) {
            if(this.emotionMeter < 10 + this.rand.nextInt(9)){
                this.emotionMeter++;
            }
            else {
                for(Ability power : this.getAbilityPowers()){
                    power.EmotionalOutburst();
                }
                this.emotionMeter = 0;
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if(entityIn.world.isRemote){
            return super.attackEntityAsMob(entityIn);
        }
        for(Ability power : this.getAbilityPowers()){
            power.Fight(entityIn, this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        }
        return super.attackEntityAsMob(entityIn);
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

    public ArrayList<Ability> findAbilities(String getab){
        ArrayList<Abilities> abilities = new ArrayList<>();
        ArrayList<Ability> powers = new ArrayList<>();
        String[] powerViolenceList = getab.split(",");
        for(int i = 0; i < powerViolenceList.length; i++){
            abilities.add(Abilities.getAbility(Integer.valueOf(powerViolenceList[i])));
        }
        for (Abilities ability : abilities) {
            powers.add(Ability.GetAbilityFromAbilities(this, ability));
        }
        return powers;
    }

    public Item getGemItem() {
        RegistryObject<Item> gemm = ModItems.PEBBLE_GEM;
        //TODO: Make this code more efficient for variations
        ItemGem gem = null;
        String name = this.hasSkinColorVariant() ? Color.getColorName(this.getSkinColorVariant()) + "_" + this.getGemName().toLowerCase() + "_gem" : this.getGemName().toLowerCase() + "_gem";
        try {
            gemm = (RegistryObject<Item>) ModItems.class.getField(name.toUpperCase()).get(null);
            gem = (ItemGem) gemm.get();
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
        this.dataManager.set(EntityGem.OWNED, value);
    }

    public void setOwned(boolean value, @Nullable UUID ID){
        this.dataManager.set(EntityGem.OWNED, value);
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
        return this.dataManager.get(EntityGem.OWNED);
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

    public byte getMovementType(){
        //Gets the movement type variable.
        return this.movementType;
    }

    public void setMovementType(byte value){
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

    public int getAbilitySlots(){
        return this.dataManager.get(EntityGem.ABILITY_SLOTS);
    }

    public void setAbilitySlots(int value){
        this.dataManager.set(EntityGem.ABILITY_SLOTS, value);
    }

    public int generateAbilitySlots(){
        if(this.isPrimary()){
            return 6;
        }
        else if(this.isDefective()){
            return 1;
        }
        return 3;
    }

    public String getAbilites(){
        return this.dataManager.get(EntityGem.ABILITIES);
    }

    public void setAbilites(String value){
        this.dataManager.set(EntityGem.ABILITIES, value);
    }

    public String generateAbilities(){
        int remainingSlots = this.getAbilitySlots();
        boolean complete = false;
        String abilityList = "";
        Abilities[] abilities = this.possibleAbilities();
        Abilities[] getgo = this.definiteAbilities();
        if(getgo != null && getgo.length > 0) {
            for (Abilities ab1 : getgo) {
                if (remainingSlots > 0) {
                    abilityList += ab1.id + ",";
                    remainingSlots--;
                } else {
                    return abilityList;
                }
            }
        }
        while(!complete){
            double totalWeight = 0.0D;
            for(Abilities i : abilities){
                totalWeight+=i.weight;
            }
            int idx = 0;
            for(double r = Math.random() * totalWeight; idx < abilities.length - 1; idx++){
                r -= abilities[idx].weight;
                if(r<=0) break;
            }
            Abilities weightedAbility = abilities[idx];
            abilityList+=weightedAbility.id + ",";
            abilities = ArrayUtils.remove(abilities, idx);
            remainingSlots--;
            complete = remainingSlots > 0 ? false : true;
        }
        return abilityList;
    }

    public ArrayList<Ability> getAbilityPowers(){
        return this.ABILITY_POWERS;
    }

    public void setAbilityPowers(ArrayList<Ability> powers){
        this.ABILITY_POWERS = powers;
    }

    public abstract Abilities[] possibleAbilities();
    public abstract Abilities[] definiteAbilities();

    public int getSkinColorVariant(){
        return this.dataManager.get(EntityGem.SKIN_COLOR_VARIANT);
    }

    public void setSkinColorVariant(int value){
        this.dataManager.set(EntityGem.SKIN_COLOR_VARIANT, value);
    }

    public abstract int generateSkinColorVariant();

    public abstract boolean hasSkinColorVariant();

    public boolean isEmotional(){
        return this.dataManager.get(EntityGem.EMOTIONAL);
    }

    public void setEmotional(boolean value){
        this.dataManager.set(EntityGem.EMOTIONAL, value);
    }

    public abstract boolean generateIsEmotional();

    public boolean isPrimary(){
        return this.dataManager.get(EntityGem.PRIMARY);
    }

    public void setPrimary(boolean value){
        this.dataManager.set(EntityGem.PRIMARY, value);
    }

    public boolean isDefective(){
        return this.dataManager.get(EntityGem.DEFECTIVE);
    }

    public void setDefective(boolean value){
        this.dataManager.set(EntityGem.DEFECTIVE, value);
    }

    public abstract boolean canChangeUniformColorByDefault();

    public abstract boolean canChangeInsigniaColorByDefault();

    public String getGemName(){
        return this.getType().getRegistryName().toString().replaceAll("(?i)item", "").replaceAll("gempire", "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }
}
