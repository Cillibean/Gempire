package com.gempire.entities.bases;

import com.gempire.Gempire;
import com.gempire.container.GemUIContainer;
import com.gempire.entities.abilities.AbilityRecall;
import com.gempire.entities.abilities.AbilityVehicle;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.interfaces.*;
import com.gempire.events.GemPoofEvent;
import com.gempire.init.ModItems;
import com.gempire.init.ModSounds;
import com.gempire.items.ItemGem;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.GemPlacements;
import com.gempire.util.PaletteType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public abstract class EntityGem extends PathfinderMob implements RangedAttackMob, ItemSteerable, Container, MenuProvider, ContainerListener {
    //public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final EntityDataAccessor<Boolean> HAS_CUSTOM_NAME = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> PRIMARY = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DEFECTIVE = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> EMOTIONAL = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> SKIN_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HAIR_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SKIN_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SKIN_COLOR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> HAIR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_HAIR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> GEM_PLACEMENT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> GEM_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> OUTFIT_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> OUTFIT_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_OUTFIT_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> INSIGNIA_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> INSIGNIA_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_INSIGNIA_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ABILITY_SLOTS = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<String> ABILITIES = SynchedEntityData.<String>defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Boolean> USES_AREA_ABILITIES = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> MARKING_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MARKING_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MARKING_2_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MARKING_2_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Boolean> HAS_VISOR = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> REBEL = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> CRACKED = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> FACET = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> CUT = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public boolean isCut;
    public ArrayList<Ability> ABILITY_POWERS = new ArrayList<>();
    public ArrayList<UUID> OWNERS = new ArrayList<>();
    public UUID FOLLOW_ID;
    public UUID ASSIGNED_ID;
    public EntityGem assignedGem;
    public int[] GUARD_POS = new int[3];
    public ArrayList<IIdleAbility> idlePowers = new ArrayList<>();

    private final ItemBasedSteering booster = new ItemBasedSteering(this.entityData, BOOST_TIME, SADDLED);

    public byte movementType = 1;
    public byte emotionMeter = 0;
    public int initalSkinVariant = 0;
    public boolean setSkinVariantOnInitialSpawn = true;

    public int areaCounter = 100;
    public int maxAreaCounter = 100;
    public int focusCounter = 100;
    public int maxFocusCounter = 100;
    public int ticking;
    public Item inputItem = Items.AIR;
    public Item outputItem = Items.AIR;

    public int timeToCraft = 10;
    public boolean isCrafting;

    public Item input;
    public int focusLevel = 2;

    public static final int NUMBER_OF_SLOTS = 33;
    public NonNullList<ItemStack> items = NonNullList.withSize(EntityGem.NUMBER_OF_SLOTS, ItemStack.EMPTY);

    public Player currentPlayer;

    public static final SimpleCommandExceptionType LOCATE_FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.faillocate"));
    public static final SimpleCommandExceptionType RECALL_FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.failrecall"));
    public int maxStructureTime = 5 * 20;
    public int structureTime = 0;
    public ArrayList<String> structures = new ArrayList<>();
    public ArrayList<String> biomes = new ArrayList<>();

    public ItemEntity spawnGem = null;
    public boolean chromaColourRequired;

    public EntityGem(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        //this.dataManager.register(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        this.entityData.define(EntityGem.HAS_CUSTOM_NAME, false);
        this.entityData.define(EntityGem.PRIMARY, false);
        this.entityData.define(EntityGem.DEFECTIVE, false);
        this.entityData.define(EntityGem.EMOTIONAL, false);
        this.entityData.define(EntityGem.SKIN_COLOR, 0);
        this.entityData.define(EntityGem.HAIR_COLOR, 0);
        this.entityData.define(EntityGem.SKIN_VARIANT, 0);
        this.entityData.define(EntityGem.SKIN_COLOR_VARIANT, 0);
        this.entityData.define(EntityGem.HAIR_VARIANT, 0);
        this.entityData.define(EntityGem.REBEL_HAIR_VARIANT, 0);
        this.entityData.define(EntityGem.GEM_PLACEMENT, 0);
        this.entityData.define(EntityGem.GEM_COLOR, 0);
        this.entityData.define(EntityGem.OUTFIT_COLOR, 0);
        this.entityData.define(EntityGem.OUTFIT_VARIANT, 0);
        this.entityData.define(EntityGem.REBEL_OUTFIT_VARIANT, 0);
        this.entityData.define(EntityGem.INSIGNIA_COLOR, 0);
        this.entityData.define(EntityGem.INSIGNIA_VARIANT, 0);
        this.entityData.define(EntityGem.REBEL_INSIGNIA_VARIANT, 0);
        this.entityData.define(EntityGem.ABILITY_SLOTS, 1);
        this.entityData.define(EntityGem.ABILITIES, "-1");
        this.entityData.define(EntityGem.USES_AREA_ABILITIES, false);
        this.entityData.define(EntityGem.HAS_VISOR, false);
        this.entityData.define(EntityGem.MARKING_COLOR, 0);
        this.entityData.define(EntityGem.MARKING_VARIANT, 0);
        this.entityData.define(EntityGem.MARKING_2_COLOR, 0);
        this.entityData.define(EntityGem.MARKING_2_VARIANT, 0);
        this.entityData.define(EntityGem.SADDLED, true);
        this.entityData.set(EntityGem.SADDLED, true);
        this.entityData.define(EntityGem.BOOST_TIME, 0);
        this.entityData.define(EntityGem.REBEL, false);
        this.entityData.set(EntityGem.REBEL, false);
        this.entityData.define(EntityGem.CRACKED, false);
        this.entityData.set(EntityGem.CRACKED, false);
        this.entityData.define(EntityGem.FACET, " ");
        this.entityData.define(EntityGem.CUT, " ");
        this.FOLLOW_ID = UUID.randomUUID();
        this.ASSIGNED_ID = UUID.randomUUID();
        Arrays.fill(this.armorDropChances, 0);
        Arrays.fill(this.handDropChances, 0);
    }


    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setGemPlacement(this.generateGemPlacement());
        this.setSkinVariant(this.generateSkinVariant());
        if(this.setSkinVariantOnInitialSpawn) {
            this.setSkinColorVariant(this.generateSkinColorVariant());
        } else this.setSkinColorVariant(this.initalSkinVariant);
        setAssignedGem(((ItemGem)this.getGemItem().getDefaultInstance().getItem()).assigned_gem);
        System.out.println(this.getAssignedGem());
        this.setHairVariant(this.generateHairVariant());
        this.setRebelHairVariant(this.generateHairVariant());
        this.setSkinColor(this.generatePaletteColor(PaletteType.SKIN));
        this.setHairColor(this.generatePaletteColor(PaletteType.HAIR));
        this.setGemColor(this.generatePaletteColor(PaletteType.GEM));
        this.setOutfitVariant(this.generateOutfitVariant());
        this.setRebelOutfitVariant(this.generateOutfitVariant());
        this.setOutfitColor(this.generateOutfitColor());
        this.setInsigniaVariant(this.generateInsigniaVariant());
        this.setRebelInsigniaVariant(this.generateInsigniaVariant());
        this.setInsigniaColor(this.generateInsigniaColor());
        this.setAbilitySlots(this.generateAbilitySlots());
        this.setAbilities(this.generateAbilities());
        this.setEmotional(this.generateIsEmotional());
        this.setAbilityPowers(this.findAbilities(this.getAbilities()));
        this.addAbilityGoals();
        this.applyAttributeAbilities();
        this.setFacet(this.generateFacet());
        this.setCut(this.generateCut());
        this.FOLLOW_ID = UUID.randomUUID();
        this.ASSIGNED_ID = UUID.randomUUID();
        this.setMarkingVariant(this.generateMarkingVariant());
        this.setMarkingColor(this.generatePaletteColor(PaletteType.MARKINGS));
        this.setMarking2Variant(this.generateMarking2Variant());
        this.setMarking2Color(this.generatePaletteColor(PaletteType.MARKINGS_2));
        this.setCustomName(this.getNickname());
        //this.generateScoutList();
        this.idlePowers = this.generateIdlePowers();
        if(this.spawnGem != null){
            this.spawnGem.remove(RemovalReason.DISCARDED);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
    @Override
    public boolean canHoldItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem ||  stack.getItem() instanceof DiggerItem;
    }

    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_HARP;
    }
    protected SoundEvent getAmbientSound() {
        return getInstrument();
    }

    protected SoundEvent getHurtSound(DamageSource p_30424_) {
        return getInstrument();
    }
    @Override
    protected void playHurtSound(DamageSource p_21160_) {
        this.playSound(getInstrument(), this.getSoundVolume(), this.hurtPitch());
    }
    public float interactPitch()
    {
        return (float) (1 + random.nextFloat() * (1.5 - 1));
    }
    public float hurtPitch()
    {
        return (float) (0.25 + random.nextFloat() * (0.75 - 0.25));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("abilities", this.getAbilities());
        compound.putBoolean("emotional", this.isEmotional());
        this.writeOwners(compound);
        compound.putUUID("followID", this.FOLLOW_ID);
        compound.putUUID("assignedID", this.ASSIGNED_ID);
        compound.putByte("movementType", this.getMovementType());
        compound.putInt("skinColorVariant", this.getSkinColorVariant());
        compound.putInt("skinColor", this.getSkinColor());
        compound.putInt("hairColor", this.getHairColor());
        compound.putInt("skinVariant", this.getSkinVariant());
        compound.putInt("hairVariant", this.getHairVariant());
        compound.putInt("rebelHairVariant", this.getRebelHairVariant());
        compound.putInt("CraftTicks", this.ticking);
        compound.putBoolean("isCrafting", this.isCrafting);
        compound.putInt("gemPlacement", this.getGemPlacement());
        compound.putInt("gemColor", this.getGemColor());
        compound.putInt("outfitColor", this.getOutfitColor());
        compound.putInt("outfitVariant", this.getOutfitVariant());
        compound.putInt("insigniaColor", this.getInsigniaColor());
        compound.putInt("rebelInsigniaVariant", this.getRebelInsigniaVariant());
        compound.putInt("insigniaVariant", this.getInsigniaVariant());
        compound.putInt("abilitySlots", this.getAbilitySlots());
        compound.putBoolean("usesAreaAbility", this.usesAreaAbilities());
        compound.putString("facet", this.getFacet());
        compound.putString("cut", this.getCut());
        compound.putBoolean("rebel", this.getRebelled());
        compound.putBoolean("cracked", this.getCracked());
        compound.putIntArray("guardPos", this.GUARD_POS);
        compound.putInt("focusLevel", this.focusLevel);
        compound.putByte("emotionMeter", this.emotionMeter);
        compound.putInt("markingVariant", this.getMarkingVariant());
        compound.putInt("markingColor", this.getMarkingColor());
        compound.putInt("marking2Variant", this.getMarking2Variant());
        compound.putInt("marking2Color", this.getMarking2Color());
        compound.putInt("structureTime", this.structureTime);
        this.writeStructures(compound);
        ContainerHelper.saveAllItems(compound, this.items);
    }

    public void writeOwners(CompoundTag compound){
        for(int i = 0; i < this.OWNERS.size(); i++){
            compound.putUUID("owner" + i, this.OWNERS.get(i));
        }
        compound.putInt("ownerAmount", this.OWNERS.size());
    }

    public void writeStructures(CompoundTag compound){
        for(int i = 0; i < this.structures.size(); i++){
            compound.putString("structure" + i, this.structures.get(i));
        }
        for(int i = 0; i < this.biomes.size(); i++){
            compound.putString("biome" + i, this.biomes.get(i));
        }
        compound.putInt("structureAmount", this.structures.size());
        compound.putInt("biomeAmount", this.biomes.size());
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.setAbilities(compound.getString("abilities"));
        this.setEmotional(compound.getBoolean("emotional"));
        this.readOwners(compound);
        if(compound.contains("followID"))this.FOLLOW_ID = compound.getUUID("followID");
        if(compound.contains("assignedID"))this.ASSIGNED_ID = compound.getUUID("assignedID");
        this.setMovementType(compound.getByte("movementType"));
        this.setSkinColorVariant(compound.getInt("skinColorVariant"));
        this.setSkinColor(compound.getInt("skinColor"));
        this.setHairColor(compound.getInt("hairColor"));
        this.setSkinVariant(compound.getInt("skinVariant"));
        this.setHairVariant(compound.getInt("hairVariant"));
        this.setRebelHairVariant(compound.getInt("rebelHairVariant"));
        this.setGemPlacement(compound.getInt("gemPlacement"));
        this.setGemColor(compound.getInt("gemColor"));
        this.ticking = compound.getInt("CraftTicks");
        this.isCrafting = compound.getBoolean("isCrafting");
        this.setOutfitColor(compound.getInt("outfitColor"));
        this.setOutfitVariant(compound.getInt("outfitVariant"));
        this.setRebelOutfitVariant(compound.getInt("rebelOutfitVariant"));
        this.setInsigniaColor(compound.getInt("insigniaColor"));
        this.setInsigniaVariant(compound.getInt("insigniaVariant"));
        this.setRebelInsigniaVariant(compound.getInt("rebelInsigniaVariant"));
        this.setAbilitySlots(compound.getInt("abilitySlots"));
        this.setFacet(compound.getString("facet"));
        this.setCut(compound.getString("cut"));
        this.emotionMeter = compound.getByte("emotionMeter");
        this.focusLevel = compound.getInt("focusLevel");
        this.setMovementType(compound.getByte("movementType"));
        this.setAbilityPowers(this.findAbilities(compound.getString("abilities")));
        this.GUARD_POS = compound.getIntArray("guardPos");
        this.addAbilityGoals();
        //this.applyAttributeAbilities();
        this.setMarkingVariant(compound.getInt("markingVariant"));
        this.setMarkingColor(compound.getInt("markingColor"));
        this.setMarking2Variant(compound.getInt("marking2Variant"));
        this.setMarking2Color(compound.getInt("marking2Color"));
        this.structureTime = compound.getInt("structureTime");
        this.idlePowers = this.generateIdlePowers();
        ContainerHelper.loadAllItems(compound, this.items);
        this.readStructures(compound);
        if(this.spawnGem != null){
            this.spawnGem.remove(RemovalReason.DISCARDED);
        }
    }

    public void readOwners(CompoundTag compound){
        this.OWNERS = new ArrayList<>();
        int n = compound.getInt("ownerAmount");
        for(int i = 0; i < n; i++){
            this.OWNERS.add(compound.getUUID("owner" + i));
        }
    }

    public void readStructures(CompoundTag compound){
        this.structures = new ArrayList<>();
        int n = compound.getInt("structureAmount");
        for(int i = 0; i < n; i++){
            this.structures.add(compound.getString("structure" + i));
        }
        this.biomes = new ArrayList<>();
        int t = compound.getInt("biomeAmount");
        for(int i = 0; i < t; i++){
            this.biomes.add(compound.getString("biome" + i));
        }
    }

    @Override
    public void aiStep() {
        if(!this.isFocused()){
            this.focusCounter++;
            if(this.focusCounter > this.maxFocusCounter){
                this.focusLevel = this.baseFocus();
                this.focusCounter = 0;
            }
        }
        if (this.canWalkOnFluids()) this.adjustForFluids();
        for(IIdleAbility power : this.getIdlePowers()){
            if(this.focusCheck()) power.execute();
        }
        if(this.isSunBurnTick()){
            if (this.getHealth() < this.getMaxHealth() && this.tickCount % 20 == 0) {
                this.heal(1.0F);
                this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 2, this.getZ(), 0,0,0F);
            }
        }

        if(this.usesAreaAbilities()) {
            if (this.tickCount % 100 == 0) {
                ArrayList<LivingEntity> entityl = new ArrayList<>(this.level.getEntitiesOfClass(LivingEntity.class, new AABB(this.getX(), this.getY(), this.getZ(), this.getX() + 1, this.getY() + 1, this.getZ() + 1)
                        .inflate(16, this.level.getMaxBuildHeight(), 16), (target) -> {
                    return target != this;
                }));
                ArrayList<Ability> abilities = this.getAbilityPowers();
                for (LivingEntity entity : entityl) {
                    boolean flagGem = entity instanceof EntityGem;
                    boolean flagPlayer = entity instanceof Player;
                    boolean flagOwner = this.isOwner(entity);
                    EntityGem gemEntity = flagGem ? (EntityGem) entity : null;
                    for (Ability ability : abilities) {
                        IEffectAbility effectAbility = ability instanceof IEffectAbility ? (IEffectAbility) ability : null;
                        IAreaAbility areaAbility = ability instanceof IAreaAbility ? (IAreaAbility) ability : null;
                        boolean flagArea = areaAbility != null;
                        boolean flagEffect = effectAbility != null;
                        boolean flagViolent = ability instanceof IViolentAbility;
                        if (!flagViolent) {
                            if (flagEffect) {
                                ArrayList<MobEffectInstance> effects = new ArrayList<>();
                                if (effectAbility.hasMultipleEffects()) {
                                    effects.addAll(Arrays.asList(effectAbility.effects()));
                                } else {
                                    effects.add(effectAbility.effect());
                                }
                                if (effectAbility.isEntityApplicable(entity)) {
                                    effects.forEach((effectInstance -> {
                                        if (flagGem) {
                                            if (EntityGem.sharesOwners(this, gemEntity)) {
                                                System.out.println("shares owner");
                                                entity.addEffect(effectInstance);
                                            }
                                        }
                                        if (this.isOwner(entity)) {
                                            entity.addEffect(effectInstance);
                                        }
                                    }));
                                }
                            }
                            if (flagArea) {
                                areaAbility.AOeffect();
                            }
                        }
                    }
                }
            }
        }

        super.aiStep();
    }

    public Item getInputItem()
    {
        return inputItem;
    }

    public Item getOutputItem()
    {
        return outputItem;
    }

    public int getTimetoCraft()
    {
        return timeToCraft;
    }
    @Override
    public void tick() {
        if (!this.level.isClientSide && isCrafting)
        {
            ticking++;
            if (ticking >= getTimetoCraft()) {
                popShitOut();
            }
        }
        super.tick();
    }
    @Override
    public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand) {
        if (!this.getRebelled()) {
        if(player.level.isClientSide){
            return super.interactAt(player, vec, hand);
        }
        //This part of the code checks if the player has a blank hand
        if(hand == InteractionHand.MAIN_HAND) {
            this.currentPlayer = player;
            if (player.getMainHandItem() == ItemStack.EMPTY) {
                if (this.isOwner(player)) {
                    if (player.isShiftKeyDown()) {
                        this.cycleMovementAI(player);
                        this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                    } else {
                        if (this.canOpenInventoryByDefault()) {
                            NetworkHooks.openScreen((ServerPlayer) player, this, buf -> buf.writeInt(this.getId()));
                            this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                        }
                        if (this.isRideable()) {
                            if (!this.isVehicle()) {
                                player.startRiding(this);
                                this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                            }
                        }
                    }
                } else {
                    //Test to see if the gem has an owner
                    if (!this.getOwned()) {
                        if (!this.isOwner(player)) {
                            this.addOwner(player.getUUID());
                            setFollow(player.getUUID());
                            if (getAssignedGem() != null) {
                                setAssignedId(getAssignedGem().getUUID());
                            }
                            this.setMovementType((byte) 2);
                            player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + "! " + getFacet() + ", " + getCut()));
                            this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                            return super.interactAt(player, vec, hand);
                        }
                    }
                }
            } else {
                if (this.isOwner(player)) {
                    if (player.getMainHandItem().getItem() instanceof DyeItem dye) {
                        if (player.isShiftKeyDown()) {
                            if (canChangeInsigniaColorByDefault()) this.setInsigniaColor(dye.getDyeColor().getId());
                        } else {
                            if (canChangeUniformColorByDefault()) this.setOutfitColor(dye.getDyeColor().getId());
                        }
                    } else if (player.getMainHandItem().getItem() == Items.PAPER) {
                        NetworkHooks.openScreen((ServerPlayer) player, this, buf -> buf.writeInt(this.getId()));
                    } else if (player.getMainHandItem().getItem() == Items.BOOK) {
                        StringBuilder list1 = new StringBuilder();
                        for (int i = 0; i < this.structures.size(); i++) {
                            if (i == this.structures.size() - 1) {
                                list1.append(this.structures.get(i));
                            } else {
                                list1.append(this.structures.get(i)).append(", ");
                            }
                        }
                        StringBuilder list2 = new StringBuilder();
                        for (int i = 0; i < this.biomes.size(); i++) {
                            if (i == this.biomes.size() - 1) {
                                list2.append(this.biomes.get(i));
                            } else {
                                list2.append(this.biomes.get(i)).append(", ");
                            }
                        }
                        player.sendSystemMessage(Component.translatable("Findable Structures: " + list1));
                        player.sendSystemMessage(Component.translatable("Findable Biomes: " + list2));
                    }
                }
            }
        }
        } else if (!this.getRebelled()) {
            player.sendSystemMessage(Component.translatable("messages.gempire.entity.rebel"));
        }
        if (player.getItemInHand(hand).getItem() == getInputItem() && !isCrafting && hand == InteractionHand.MAIN_HAND && getInputItem() != Items.AIR.asItem()) {
            if (this.isOwner(player)) {
                isCrafting = true;
                this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                if (!player.isCreative()) {
                    player.getMainHandItem().shrink(1);
                }
            }
        }
        return super.interactAt(player, vec, hand);
    }

    /*
    0 is stay still
    1 is wander
    2 is follow
    3 is follow assigned
     */

    public EntityGem getAssignedGem() {
        return assignedGem;
    }

    public void setAssignedGem(EntityGem assignedGem) {
        this.assignedGem = assignedGem;
    }

    public void cycleMovementAI(Player player){
        if (!this.getRebelled()) {
            //Cycles through the various movement types.
            this.navigation.stop();
            setFollow(player.getUUID());
            if (getAssignedGem() != null) {
                setAssignedId(getAssignedGem().getUUID());
            }
            System.out.println(assignedGem);
            if (getAssignedGem() != null ? this.getMovementType() < 3 : this.getMovementType() <2) {
                this.addMovementType(1);
                switch (this.getMovementType()) {
                    case 1 -> player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + " will wander around"));
                    case 2 -> player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + " will now follow you"));
                    case 3 -> player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + " will now follow " + assignedGem.getCapitalGemName() + " " + assignedGem.getFacetAndCut()));
                    default -> player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + " will now stay put"));
                }
            } else if (getAssignedGem() != null && getAssignedGem().isAlive() ? this.getMovementType() == 3 : this.getMovementType() == 2) {
                this.setMovementType((byte) 0);
                player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + " will now stay put"));
                this.GUARD_POS[0] = (int) this.getX();
                this.GUARD_POS[1] = (int) this.getY();
                this.GUARD_POS[2] = (int) this.getZ();
            } else if (getAssignedGem() == null && this.getMovementType() == 3)
            {
                this.setMovementType((byte) 0);
                player.sendSystemMessage(Component.translatable(this.getCapitalGemName() + " will now stay put"));
                this.GUARD_POS[0] = (int) this.getX();
                this.GUARD_POS[1] = (int) this.getY();
                this.GUARD_POS[2] = (int) this.getZ();
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount){
        if(this.level.isClientSide){
            return super.hurt(source, amount);
        }
        /*int crackChance = 5;
        if (amount - getHealth() > hardness && (this.random.nextInt(crackChance) + 1 == crackChance)) {
            setCracked(true);
            currentPlayer.sendSystemMessage(Component.translatable("cracked"));
        }*/
        if(this.isEmotional() && !source.isExplosion() && !source.isFire()) {
            if(this.emotionMeter <= this.EmotionThreshold()){
                if(this.EmotionThreshold() - this.emotionMeter < 5){
                    this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getX(), this.getY() + 2, this.getZ(),0,0,0);
                }
                this.emotionMeter++;
            }
            else {
                for(Ability power : this.getAbilityPowers()){
                    if(power instanceof IEmotionalAbility){
                        ((IEmotionalAbility)power).outburst();
                    }
                }
                this.emotionMeter = 0;
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if(!entityIn.level.isClientSide){
            if(this.focusCheck()) for(Ability power : this.getAbilityPowers()){
                if(power instanceof IMeleeAbility) {
                    ((IMeleeAbility)power).fight((LivingEntity) entityIn, this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                }
            }
        }
        return super.doHurtTarget(entityIn);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        for(Ability power : this.getAbilityPowers()){
            if(power instanceof IRangedAbility && this.focusCheck()){
                ((IRangedAbility)power).attack(target, distanceFactor);
            }
        }
    }

    @Override
    public void die(DamageSource source){
        //When the Gem dies.
        /*float f = (this.random.nextFloat() - 0.5F) * 8.0F;
        float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
        float f2 = (this.random.nextFloat() - 0.5F) * 8.0F; these dont do anything*/
        this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() , this.getY() + 2.0D, this.getZ(), 0.0D, 0.0D, 0.0D);
        if(!this.level.isClientSide){
            this.playSound(ModSounds.POOF.get());
            GemPoofEvent event = new GemPoofEvent(this, this.blockPosition(), source);
            MinecraftForge.EVENT_BUS.post(event);
            ItemStack stack = new ItemStack(this.getGemItem());
            ((ItemGem) stack.getItem()).setData(this, stack);
            ItemEntity item = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stack);
            item.setExtendedLifetime();
            this.kill();
            this.level.addFreshEntity(item);
        }
        super.die(source);
    }

    public Component getNickname(){
        if(this instanceof EntityVaryingGem){
            if(((EntityVaryingGem)this).UsesUniqueNames()) {
                return Component.translatable("nickname.gempire." + this.getWholeGemName() + "_" + this.getSkinColorVariant());
            }
        }
        return Component.translatable("entity.gempire." + this.getWholeGemName());
    }

    public Item getGemItem() {
        RegistryObject<Item> gemm = ModItems.PEBBLE_GEM;
        ItemGem gem = null;
        String name = "";
        if(this instanceof EntityVaryingGem){
            if(((EntityVaryingGem)this).UsesUniqueNames()) {
                name = ((EntityVaryingGem) this).NameFromColor((byte) this.getSkinColorVariant()) + "_" + this.getWholeGemName() + "_gem";
            }
            else{
                name = this.hasSkinColorVariant() ? Color.getColorName(this.getSkinColorVariant()) + "_" + this.getWholeGemName().toLowerCase() + "_gem" : this.getWholeGemName().toLowerCase() + "_gem";
            }
        }
        else{
            name = this.hasSkinColorVariant() ? Color.getColorName(this.getSkinColorVariant()) + "_" + this.getWholeGemName().toLowerCase() + "_gem" : this.getWholeGemName().toLowerCase() + "_gem";
        }
        try {
            gemm = (RegistryObject<Item>) this.getItemRegister().getField(name.toUpperCase()).get(null);
            gem = (ItemGem) gemm.get();
        } catch(Exception e){
            e.printStackTrace();
        }
        return gem;
    }

    public static boolean sharesOwners(EntityGem gem1, EntityGem gem2){
        for (int x = 0; x < gem1.OWNERS.size(); x++){
            for(int y = 0; y < gem2.OWNERS.size(); y++){
                if(gem1.OWNERS.get(x) == gem2.OWNERS.get(y)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public boolean removeWhenFarAway(double xix){
        return false;
    }

    //GETTERS AND SETTERS!!!

    public void setOwners(ArrayList<UUID> owners){
        if (!getRebelled()) {
            this.OWNERS = owners;
        }
    }

    public void addOwner(UUID ID){
        if (!getRebelled()) {
            this.OWNERS.add(ID);
        }
    }

    public void removeOwner(UUID ID){
        for(int i = 0; i < this.OWNERS.size(); i++){
            if(this.OWNERS.get(i).equals(ID)){
                this.OWNERS.remove(i);
                break;
            }
        }
    }

    public void resetOwners(){
        setOwners(new ArrayList<>());
    }

    public boolean getOwned(){
        return this.OWNERS.size() > 0;
    }

    /*public UUID getOwnerID(){
        return this.dataManager.get(EntityGem.OWNER_ID).get();
    }*/

    public boolean isOwner(LivingEntity entity){
        for(UUID uuid : this.OWNERS){
            if(entity.getUUID().equals(uuid)) return true;
        }
        return false;
    }

    public boolean isOwner(UUID id){
        for(UUID uuid : this.OWNERS){
            if(id.equals(uuid)) return true;
        }
        return false;
    }

    public void setFollow(UUID id){
        this.FOLLOW_ID = id;
    }

    public void setAssignedId(UUID id){
        this.ASSIGNED_ID = id;
    }

    public byte getMovementType(){
        //Gets the movement type variable.
        return this.movementType;
    }

    public void setMovementType(byte value){
        //Sets the movement type variable.f
        this.movementType = value;
    }

    public void addMovementType(int value){
        //Adds the value to the movement type variable.
        this.movementType += value;
    }

    public int getSkinColor(){
        return this.entityData.get(EntityGem.SKIN_COLOR);
    }

    public void setSkinColor(int value){
        this.entityData.set(EntityGem.SKIN_COLOR, value);
    }

    public int generatePaletteColor(PaletteType type){
        if(type == PaletteType.MARKINGS){
            if(!hasMarkings()){
                return 0;
            }
        }
        if(type == PaletteType.MARKINGS_2){
            if(!hasMarkings2()){
                return 0;
            }
        }
        String locString = type.type + "_palette";
        System.out.println("[DEBUG] " + locString);
        ArrayList<Integer> colors = new ArrayList<>();
        ResourceLocation loc = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/palettes/" + locString + ".png");
        BufferedImage palette = null;
        try {
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(loc).get().open());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if ((color >> 24) == 0x00) {
                    continue;
                }
                colors.add(color);
            }
        } catch (IOException e) {
            e.printStackTrace();
            colors.add(0x00000);
        }
        return Color.lerpHex(colors);
    }
    public void popShitOut()
    {
        ItemStack itemStack = new ItemStack(getOutputItem());
        ItemEntity itemEntity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), itemStack);
        this.level.addFreshEntity(itemEntity);
        this.currentPlayer.sendSystemMessage(Component.translatable("All done!"));
        ticking = 0;
        this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
        isCrafting = false;
    }

    public String generateFacet() {
        char a = (char) (this.random.nextInt(26) + 'a');
        char b = (char) (this.random.nextInt(26) + 'a');
        String facet1 = "" + a + b + this.random.nextInt(9) + this.random.nextInt(9);
        String facet = facet1.toUpperCase();
        return "Facet-" + facet;
    }

    public String getFacet(){
        return this.entityData.get(EntityGem.FACET);
    }

    public void setFacet(String value){
        this.entityData.set(EntityGem.FACET, value);
    }


    public String generateCut() {
        char a = (char) (this.random.nextInt(26) + 'a');
        char b = (char) (this.random.nextInt(26) + 'a');
        String cut1 = "" + a + b + this.random.nextInt(9);
        String cut = cut1.toUpperCase();
        String start;
        if (getIsCut()) {
            start = "Cut-";
        } else {
            start = "Cabochon-";
        }
        return start + cut;
    }

    public String getCut(){
        return this.entityData.get(EntityGem.CUT);
    }
    public boolean getIsCut()
    {
        return true;
    }
    public void setCut(String value){
        this.entityData.set(EntityGem.CUT, value);
    }

    public String getFacetAndCut() {
        return this.getFacet() + ", " + this.getCut();
    }

    public abstract int generateSkinVariant();

    public int getSkinVariant(){
        return this.entityData.get(EntityGem.SKIN_VARIANT);
    }

    public void setSkinVariant(int value){
        this.entityData.set(EntityGem.SKIN_VARIANT, value);
    }


    public int generateMarkingVariant(){
        return this.hasMarkings() ? this.random.nextInt(this.maxMarkings()) : 0;
    }

    public int generateMarking2Variant(){
        return this.hasMarkings2() ? this.random.nextInt(this.maxMarkings2()) : 0;
    }

    public boolean hasMarkings(){
        return false;
    }
    public boolean hasMarkings2(){
        return false;
    }
    public int maxMarkings(){
        return 1;
    }
    public int maxMarkings2(){
        return 1;
    }

    public void setMarkingColor(int color){
        this.entityData.set(EntityGem.MARKING_COLOR, color);
    }

    public int getMarkingColor(){
        return this.entityData.get(EntityGem.MARKING_COLOR);
    }
    public void setMarkingVariant(int value){
        this.entityData.set(EntityGem.MARKING_VARIANT, value);
    }

    public int getMarkingVariant(){
        return this.entityData.get(EntityGem.MARKING_VARIANT);
    }

    public void setMarking2Color(int color){
        this.entityData.set(EntityGem.MARKING_2_COLOR, color);
    }

    public int getMarking2Color(){
        return this.entityData.get(EntityGem.MARKING_2_COLOR);
    }
    public void setMarking2Variant(int value){
        this.entityData.set(EntityGem.MARKING_2_VARIANT, value);
    }

    public int getMarking2Variant(){
        return this.entityData.get(EntityGem.MARKING_2_VARIANT);
    }

    public int getGemPlacement(){
        return this.entityData.get(EntityGem.GEM_PLACEMENT);
    }

    public GemPlacements getGemPlacementE(){
        return GemPlacements.getPlacement(this.getGemPlacement());
    }

    public void setGemPlacement(int value){
        this.entityData.set(EntityGem.GEM_PLACEMENT, value);
    }

    public abstract GemPlacements[] getPlacements();

    public int generateGemPlacement(){
        return this.getPlacements()[this.random.nextInt(this.getPlacements().length)].id;
    }

    public int getHairColor(){
        return this.entityData.get(EntityGem.HAIR_COLOR);
    }

    public void setHairColor(int value){
        this.entityData.set(EntityGem.HAIR_COLOR, value);
    }

    public int getHairVariant(){
        return this.entityData.get(EntityGem.HAIR_VARIANT);
    }

    public void setHairVariant(int value){
        this.entityData.set(EntityGem.HAIR_VARIANT, value);
    }

    public int getRebelHairVariant(){
        return this.entityData.get(EntityGem.REBEL_HAIR_VARIANT);
    }

    public void setRebelHairVariant(int value){
        this.entityData.set(EntityGem.REBEL_HAIR_VARIANT, value);
    }

    public abstract int generateHairVariant();

    public int getGemColor(){
        return this.entityData.get(EntityGem.GEM_COLOR);
    }

    public void setGemColor(int value){
        this.entityData.set(EntityGem.GEM_COLOR, value);
    }

    public int getOutfitColor(){
        return this.entityData.get(EntityGem.OUTFIT_COLOR);
    }

    public void setOutfitColor(int value){
        this.entityData.set(EntityGem.OUTFIT_COLOR, value);
    }

    public int generateOutfitColor(){
        return this.getSkinColorVariant();
    }

    public abstract int generateOutfitVariant();

    public void setOutfitVariant(int value){
        this.entityData.set(EntityGem.OUTFIT_VARIANT, value);
    }

    public int getOutfitVariant(){
        return this.entityData.get(EntityGem.OUTFIT_VARIANT);
    }

    public void setRebelOutfitVariant(int value){
        this.entityData.set(EntityGem.REBEL_OUTFIT_VARIANT, value);
    }

    public int getRebelOutfitVariant(){
        return this.entityData.get(EntityGem.REBEL_OUTFIT_VARIANT);
    }

    public boolean hasOutfitPlacementVariant(){
        return false;
    }

    public int[] outfitPlacementVariants(){
        return new int[]{};
    }

    public abstract int generateInsigniaVariant();

    public void setInsigniaVariant(int value){
        this.entityData.set(EntityGem.INSIGNIA_VARIANT, value);
    }

    public int getInsigniaVariant(){
        return this.entityData.get(EntityGem.INSIGNIA_VARIANT);
    }

    public void setRebelInsigniaVariant(int value){
        this.entityData.set(EntityGem.REBEL_INSIGNIA_VARIANT, value);
    }

    public int getRebelInsigniaVariant(){
        return this.entityData.get(EntityGem.REBEL_INSIGNIA_VARIANT);
    }

    public int getInsigniaColor(){
        return this.entityData.get(EntityGem.INSIGNIA_COLOR);
    }

    public void setInsigniaColor(int value){
        this.entityData.set(EntityGem.INSIGNIA_COLOR, value);
    }

    public int generateInsigniaColor(){
        return this.getSkinColorVariant();
    }

    public int getSkinColorVariant(){
        return this.entityData.get(EntityGem.SKIN_COLOR_VARIANT);
    }

    public void setSkinColorVariant(int value){
        this.entityData.set(EntityGem.SKIN_COLOR_VARIANT, value);
    }

    public abstract int generateSkinColorVariant();

    public abstract boolean hasSkinColorVariant();

    public boolean isEmotional(){
        return this.entityData.get(EntityGem.EMOTIONAL);
    }

    public void setEmotional(boolean value){
        this.entityData.set(EntityGem.EMOTIONAL, value);
    }

    public abstract boolean generateIsEmotional();

    public abstract byte EmotionThreshold();

    public boolean isPrimary(){
        return this.entityData.get(EntityGem.PRIMARY);
    }

    public void setPrimary(boolean value){
        this.entityData.set(EntityGem.PRIMARY, value);
    }

    public boolean isDefective(){
        return this.entityData.get(EntityGem.DEFECTIVE);
    }

    public void setDefective(boolean value){
        this.entityData.set(EntityGem.DEFECTIVE, value);
    }

    public abstract boolean canChangeUniformColorByDefault();

    public abstract boolean canChangeInsigniaColorByDefault();
    public Boolean getCracked(){
        return this.entityData.get(EntityGem.CRACKED);
    }

    public void setCracked(boolean cracked){
        this.entityData.set(EntityGem.CRACKED, cracked);
    }


    public Boolean getRebelled(){
        return this.entityData.get(EntityGem.REBEL);
    }

    public void setRebelled(boolean rebelled){
        this.entityData.set(EntityGem.REBEL, rebelled);
    }

    public String getCapitalGemName() {
        String name = this.getGemName();
        String s1 = name.substring(0, 1).toUpperCase();
        return s1 + name.substring(1);
    }

    public String getGemName(){
        if(this instanceof EntityVaryingGem){
            if(((EntityVaryingGem)this).UsesUniqueNames()){
                return ((EntityVaryingGem)this).NameFromColor((byte) this.getSkinColorVariant());
            }
        }
        return Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(this.getType())).toString().replaceAll("(?i)item", "").replaceAll(this.getModID(), "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }

    public String getWholeGemName(){
        return Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(this.getType())).toString().replaceAll("(?i)item", "").replaceAll(this.getModID(), "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }

    //ABILITY STUFF

    public ArrayList<Ability> findAbilities(String getab){
        ArrayList<Abilities> abilities = new ArrayList<>();
        ArrayList<Ability> powers = new ArrayList<>();
        if(!getab.isEmpty()) {
            String[] powerViolenceList = getab.split(",");
            for (String s : powerViolenceList) {
                abilities.add(Abilities.getAbility(Integer.parseInt(s)));
            }
            for (Abilities ability : abilities) {
                //powers.add(Ability.getAbilityFromAbilities(ability).assignAbility(this));
                Class[] parameterType = new Class[0];
                Ability ability1;
                try {
                    ability1 = Ability.ABILITY_FROM_ABILITIES.get(ability).getConstructor(parameterType).newInstance().assignAbility(this);
                    powers.add(ability1);
                    if((ability1 instanceof IEffectAbility || ability1 instanceof IAreaAbility) && !(ability1 instanceof IViolentAbility)){
                        this.entityData.set(EntityGem.USES_AREA_ABILITIES, true);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            ArrayList<Ability> nulab = new ArrayList<>();
            nulab.add(new AbilityZilch().assignAbility(this));
            return nulab;
        }
        return powers;
    }
    public void addAbilityGoals(){
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof ITaskAbility){
                if(((ITaskAbility)ability).targetTask()){
                    this.targetSelector.addGoal(2, ((ITaskAbility)ability).goal());
                }
                else{
                    this.goalSelector.addGoal(2, ((ITaskAbility)ability).goal());
                }
            }
        }
    }

    public void applyAttributeAbilities(){
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof IAttributeAbility){
                Objects.requireNonNull(this.getAttribute(((IAttributeAbility) ability).attribute())).setBaseValue(((IAttributeAbility)ability).baseValue());
            }
        }
    }

    public int getAbilitySlots(){
        return this.entityData.get(EntityGem.ABILITY_SLOTS);
    }

    public void setAbilitySlots(int value){
        this.entityData.set(EntityGem.ABILITY_SLOTS, value);
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

    public String getAbilities(){
        return this.entityData.get(EntityGem.ABILITIES);
    }

    public void setAbilities(String value){
        this.entityData.set(EntityGem.ABILITIES, value);
    }

    public String generateAbilities(){
        int remainingSlots = this.getAbilitySlots();
        if(remainingSlots == 0){
            return "0";
        }
        boolean complete = false;
        StringBuilder abilityList = new StringBuilder();
        Abilities[] abilities = this.possibleAbilities();
        Abilities[] getgo = this.definiteAbilities();
        if(getgo != null && getgo.length > 0) {
            for (Abilities ab1 : getgo) {
                if (remainingSlots > 0) {
                    abilityList.append(ab1.id).append(",");
                    remainingSlots--;
                } else {
                    return abilityList.toString();
                }
            }
        }
        while(!complete){
            double totalWeight = 0.0D;
            for(Abilities i : abilities){
                totalWeight+=i.weight;
            }
            int idx = 0;
            for (double r = Math.random() * totalWeight; idx < abilities.length - 1; idx++) {
                r -= abilities[idx].weight;
                if (r <= 0) break;
            }
            Abilities weightedAbility = abilities[idx];
            abilityList.append(weightedAbility.id).append(",");
            if(this.possibleAbilities().length + this.definiteAbilities().length > this.getAbilitySlots()) abilities = ArrayUtils.remove(abilities, idx);
            remainingSlots--;
            complete = remainingSlots <= 0;
        }
        return abilityList.toString();
    }

    public ArrayList<Ability> getAbilityPowers(){
        return this.ABILITY_POWERS;
    }

    public void setAbilityPowers(ArrayList<Ability> powers){
        this.ABILITY_POWERS = powers;
    }

    public abstract Abilities[] possibleAbilities();
    public abstract Abilities[] definiteAbilities();

    public boolean usesAreaAbilities(){
        return this.entityData.get(EntityGem.USES_AREA_ABILITIES);
    }

    public ArrayList<IIdleAbility> getIdlePowers(){
        return this.idlePowers;
    }

    public ArrayList<IIdleAbility> generateIdlePowers(){
        ArrayList<IIdleAbility> powers = new ArrayList<>();
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof IIdleAbility){
                powers.add((IIdleAbility) ability);
            }
        }
        return powers;
    }

    public void setHasVisor(boolean value){
        this.entityData.set(EntityGem.HAS_VISOR, value);
    }

    public boolean hasVisor(){
        return this.entityData.get(EntityGem.HAS_VISOR);
    }

    public boolean hasVisorCosmeticOnly(){
        return false;
    }

    public boolean isFocused(){
        return false;
    }

    public boolean focusCheck(){
        return this.isFocused() || (this.focusLevel <= 1 || this.random.nextInt(this.focusLevel) == 0);
    }

    public int baseFocus(){
        return 2;
    }

    public boolean isRideable(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityVehicle){
                flag = true;
            }
        }
        return false;
    }

    public boolean canWalkOnFluids(){
        return false;
    }

    @Override
    public boolean boost() {
        return booster.boost(this.getRandom());
    }

    public void travelWithInput(Vec3 travelVec) {
        if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof Player){
            double speed = ((Player) this.getControllingPassenger()).zza;
            double speedSwim = this.isSwimming() ? 10 : 1;
            super.travel(travelVec.multiply(speed,speed,speed).multiply(speedSwim, speedSwim, speedSwim));
        }
        else{
            super.travel(travelVec);
        }
    }

    public void travel(Vec3 travelVector) {
        this.travel(this, this.booster, travelVector);
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof Player)) {
            return false;
        } else {
            return true;
        }
    }


    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double)(0.8F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    public void adjustForFluids() {
        if(this.canWalkOnFluids()) {
            if (this.isInWater() || this.isInLava()) {
                CollisionContext iselectioncontext = CollisionContext.of(this);
                if (iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)
                        || iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                    this.onGround = true;
                } else {
                    this.setDeltaMovement(this.getDeltaMovement().scale(.5D).add(0.0D, 0.05D, 0.0D));
                }
            }
        }
    }

    public boolean causeFallDamage(float p_148750_, float p_148751_, DamageSource p_148752_) {
        return false;
    }

    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) {
    }

    public void setHasCustomName(boolean value){
        this.entityData.set(EntityGem.HAS_CUSTOM_NAME, value);
    }

    public boolean customName(){
        return this.entityData.get(EntityGem.HAS_CUSTOM_NAME);
    }

    public int getLuck(){
        return 0;
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void startOpen(Player player) {
        player.openMenu(this);
    }

    @Override
    public void stopOpen(Player player) {
        player.closeContainer();
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return !(stack.getItem() instanceof ItemGem);
    }

    @Override
    public int getContainerSize() {
        return EntityGem.NUMBER_OF_SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.getItems().get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.getItems(), index, count);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        if(index > 26 && index < 31){
            switch(index){
                case 27:
                    this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                case 28:
                    this.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
                case 29:
                    this.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
                default:
                    this.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
            }
        }
        return null;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.getItems().set(index, stack);
        int ind = index;
        if(stack.getItem() instanceof ArmorItem && index > 26 && index < 31){
            switch(ind){
                case 27:
                    this.setItemSlot(EquipmentSlot.HEAD, stack);
                case 28:
                    this.setItemSlot(EquipmentSlot.CHEST, stack);
                case 29:
                    this.setItemSlot(EquipmentSlot.LEGS, stack);
                default:
                    this.setItemSlot(EquipmentSlot.FEET, stack);
            }
        }
    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new GemUIContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    @Override
    public void containerChanged(Container invBasic) {
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    public void clearContent() {

    }

    @Override
    public void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        super.actuallyHurt(damageSrc, damageAmount);
    }

    public boolean canOpenInventoryByDefault(){
        return true;
    }

    public boolean consumeItemCheck(Item item){
        for(int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 4; i++){
            if(this.getItem(i).getItem() == item){
                if(this.getItem(i).getCount() == 1){
                    this.setItem(i, ItemStack.EMPTY);
                    return true;
                }else {
                    this.setItem(i, new ItemStack(this.getItem(i).getItem(),
                            this.getItem(i).getCount() - 1));
                    //this.getStackInSlot(i).shrink(1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean itemCheck(Item item){
        for(int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 4; i++){
            if(this.getItem(i).getItem() == item){
                return true;
            }
        }
        return false;
    }

    /*
    COMMAND STUFF
    */

    public boolean canRecall(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityRecall){
                return flag = true;
            }
        }
        return flag;
    }

    public static void decreaseExp(Player player, float amount) {
        if (player.totalExperience - amount <= 0)
        {
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
            return;
        }
        player.totalExperience -= amount;
        if (player.experienceProgress * (float)player.getXpNeededForNextLevel() <= amount)
        {
            amount -= player.experienceProgress * (float)player.getXpNeededForNextLevel();
            player.experienceProgress = 1.0f;
            player.experienceLevel--;
        }
        while (player.getXpNeededForNextLevel() < amount)
        {
            amount -= player.getXpNeededForNextLevel();
            player.experienceLevel--;
        }
        player.experienceProgress -= amount / (float)player.getXpNeededForNextLevel();
    }

    public void runRecallCommand(ServerPlayer player) {
        if(this.consumeItemCheck(Items.ENDER_EYE)) {
            int x = player.getRespawnPosition().getX();
            int y = player.getRespawnPosition().getY();
            int z = player.getRespawnPosition().getZ();
            if (player.isCreative() ? player.experienceLevel >= 0 : player.experienceLevel >= 20) {
                    player.teleportTo(x, y, z);
                    this.teleportTo(x, y, z);
                    player.sendSystemMessage(Component.translatable("commands.gempire.recall"));
                    decreaseExp(player, 20);
            } else {
                player.sendSystemMessage(Component.translatable(  "messages.gempire.entity.player_need_xp"));
            }
        }
        else{
            player.sendSystemMessage(Component.translatable("commands.gempire.noeye"));
        }
    }

    public void rebel() {
        this.resetOwners();
        this.generateInsigniaColor();
        this.generateOutfitColor();
        this.generateOutfitVariant();
        this.generateHairVariant();
        this.generateInsigniaVariant();
        this.setRebelled(true);
    }

    /*public static BlockPos findStructure(EntityGem gem, StructureFeature<?> structure) {
        if(gem.level.isClientSide){
            return BlockPos.ZERO;
        }
        BlockPos blockpos1 = null;
        BlockPos blockpos = new BlockPos(gem.blockPosition());
        if(gem.structures.contains(structure.getRegistryName().toString().replace("minecraft:", ""))) {
             blockpos1 = ((ServerLevel) gem.getCommandSenderWorld()).findNearestMapFeature(structure, blockpos, 100, false);
        }
        if (blockpos1 == null) {
            return BlockPos.ZERO;
        } else {
            return blockpos1;
        }
    }
    public static BlockPos findBiome(EntityGem gem, ResourceLocation biomeResource) throws CommandSyntaxException {
        if(gem.level.isClientSide){
            return BlockPos.ZERO;
        }
        Biome biome = gem.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getOptional(biomeResource).orElseThrow(() -> {
            return LocateCommand.ERROR_BIOME_INVALID.create(biomeResource);
        });
        String s = biomeResource.toString();
        BlockPos blockpos = new BlockPos(gem.blockPosition());
        BlockPos blockpos1 = null;
        if(gem.biomes.contains(s.replace("minecraft:", ""))) {
            blockpos1 = ((ServerLevel) gem.getCommandSenderWorld()).findNearestBiome(biome, blockpos, 6400, 8);
        }
        if (blockpos1 == null) {
            return BlockPos.ZERO;
        } else {
            return blockpos1;
        }
    }
    public void runFindCommand(ServerPlayer player, @Nullable StructureFeature<?> structure, @Nullable ResourceLocation biomeResource, boolean biome)
            throws CommandSyntaxException {
        BlockPos pos = biome ? EntityGem.findBiome(this, biomeResource) : EntityGem.findStructure(this, structure);
        if(this.consumeItemCheck(Items.MAP)) {
            if (pos == BlockPos.ZERO) {
                if(biome) {
                    player.sendSystemMessage(Component.translatable("commands.gempire.norecbio"), UUID.randomUUID());
                }
                else{
                    player.sendSystemMessage(Component.translatable("commands.gempire.norecstruc"), UUID.randomUUID());
                }
                return;
            }
            boolean done = false;
            ItemStack map = MapItem.create(this.level, pos.getX(), pos.getZ(), (byte) 0, true, true);
            MapItemSavedData.addTargetDecoration(map, pos, "location", MapDecoration.Type.RED_X);
            String name = biome ? biomeResource.toString().replaceAll("minecraft:", "") :
                    structure.getFeatureName().replaceAll("minecraft:", "");
            map.setHoverName(Component.translatable(name));
            for (int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 6; i++) {
                if (this.getItem(i) == ItemStack.EMPTY) {
                    this.setItem(i, map);
                    done = true;
                    break;
                }
            }
            if (!done) {
                this.spawnAtLocation(map);
            }
            player.sendSystemMessage(Component.translatable("commands.gempire.foundit"), UUID.randomUUID());
        }
        else{
            player.sendSystemMessage(Component.translatable("commands.gempire.nomap"), UUID.randomUUID());
        }
    }
    public boolean canLocateStructures(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityScout){
                return flag = true;
            }
        }
        return flag;
    }
    public void generateScoutList(){
        //STRUCTURES
        ArrayList<ResourceLocation> rls = new ArrayList<>();
        Set<ResourceLocation> sussy = ForgeRegistries.STRUCTURE_FEATURES.getKeys();
        rls.addAll(sussy);
        ArrayList<ResourceLocation> resourceLocations = new ArrayList<>();
        while(resourceLocations.size() < 3){
            int m = this.random.nextInt(sussy.size());
            if(!resourceLocations.contains(rls.get(m))) resourceLocations.add(rls.get(m));
        }
        for(ResourceLocation key : resourceLocations){
            this.structures.add(ForgeRegistries.SRE_FEATURES.getValue(key).getRegistryName().toString().replace("minecraft:", ""));
        }
        //BIOMES
        ArrayList<ResourceLocation> rls1 = new ArrayList<>();
        Set<ResourceLocation> sus = ForgeRegistries.BIOMES.getKeys();
        rls1.addAll(sus);
        ArrayList<ResourceLocation> resourceLocations1 = new ArrayList<>();
        while(resourceLocations1.size() < 3){
            int m = this.random.nextInt(sus.size());
            if(!resourceLocations1.contains(rls1.get(m))) resourceLocations1.add(rls1.get(m));
        }
        for(ResourceLocation key : resourceLocations1){
            this.biomes.add(ForgeRegistries.BIOMES.getValue(key).toString().replace("minecraft:", ""));
        }
    }
    public boolean isOnStructureCooldown(){
        return true;
    }*/

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//



    //TODO: ADDON STUFF
    //TODO: CRACKING AND SHATTERING
    //TODO: CORRUPTION



    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public String getModID(){
        return Gempire.MODID;
    }

    public Class getItemRegister(){
        return ModItems.class;
    }
}