package com.gempire.entities.bases;

import com.gempire.Gempire;
import com.gempire.container.GemUIContainer;
import com.gempire.entities.abilities.AbilityScout;
import com.gempire.entities.abilities.AbilityVehicle;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.interfaces.*;
import com.gempire.events.GemPoofEvent;
import com.gempire.init.ModItems;
import com.gempire.items.ItemGem;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.GemPlacements;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.command.impl.LocateBiomeCommand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public abstract class EntityGem extends CreatureEntity implements IRangedAttackMob, IRideable, IInventory, INamedContainerProvider, IInventoryChangedListener {
    //public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final DataParameter<Boolean> HAS_CUSTOM_NAME = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> PRIMARY = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> DEFECTIVE = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> EMOTIONAL = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> SKIN_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> HAIR_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> SKIN_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> SKIN_COLOR_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Integer> HAIR_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_PLACEMENT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Integer> OUTFIT_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Integer> OUTFIT_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Integer> INSIGNIA_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Integer> INSIGNIA_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> ABILITY_SLOTS = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<String> ABILITIES = EntityDataManager.<String>createKey(EntityGem.class, DataSerializers.STRING);
    public static final DataParameter<Boolean> USES_AREA_ABILITIES = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> MARKING_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> MARKING_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> MARKING_2_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> MARKING_2_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Boolean> HAS_VISOR = EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
    public ArrayList<Ability> ABILITY_POWERS = new ArrayList<>();
    public ArrayList<UUID> OWNERS = new ArrayList<>();
    public UUID FOLLOW_ID;
    public int[] GUARD_POS = new int[3];
    public ArrayList<IIdleAbility> idlePowers = new ArrayList<>();

    private final BoostHelper booster = new BoostHelper(this.dataManager, BOOST_TIME, SADDLED);

    public byte movementType = 1;
    public byte emotionMeter = 0;
    public int initalSkinVariant = 0;
    public boolean setSkinVariantOnInitialSpawn = true;

    public int areaCounter = 100;
    public int maxAreaCounter = 100;
    public int focusCounter = 100;
    public int maxFocusCounter = 100;

    public int focusLevel = 2;

    public static final int NUMBER_OF_SLOTS = 33;
    public NonNullList<ItemStack> items = NonNullList.withSize(EntityGem.NUMBER_OF_SLOTS, ItemStack.EMPTY);

    public int maxBrewingTime = 1;
    public Item inputItem = Items.AIR;
    public Item outputItem = Items.AIR;
    public int brewingTicks = 0;
    public static DataParameter<Integer> BREWING_PROGRESS = EntityDataManager.createKey(EntityGem.class, DataSerializers.VARINT);
    public boolean brewing = false;

    public PlayerEntity currentPlayer;

    public static final SimpleCommandExceptionType LOCATE_FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.gempire.faillocate"));
    public int maxStructureTime = 5 * 20;
    public int structureTime = 0;
    public ArrayList<String> structures = new ArrayList<>();
    public ArrayList<String> biomes = new ArrayList<>();

    public ItemEntity spawnGem = null;

    public EntityGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        //this.dataManager.register(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        this.dataManager.register(EntityGem.HAS_CUSTOM_NAME, false);
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
        this.dataManager.register(EntityGem.OUTFIT_VARIANT, 0);
        this.dataManager.register(EntityGem.INSIGNIA_COLOR, 0);
        this.dataManager.register(EntityGem.INSIGNIA_VARIANT, 0);
        this.dataManager.register(EntityGem.ABILITY_SLOTS, 1);
        this.dataManager.register(EntityGem.ABILITIES, "-1");
        this.dataManager.register(EntityGem.USES_AREA_ABILITIES, false);
        this.dataManager.register(EntityGem.HAS_VISOR, false);
        this.dataManager.register(EntityGem.MARKING_COLOR, 0);
        this.dataManager.register(EntityGem.MARKING_VARIANT, 0);
        this.dataManager.register(EntityGem.MARKING_2_COLOR, 0);
        this.dataManager.register(EntityGem.MARKING_2_VARIANT, 0);
        this.dataManager.register(EntityGem.SADDLED, true);
        this.dataManager.set(EntityGem.SADDLED, true);
        this.dataManager.register(EntityGem.BOOST_TIME, 0);
        this.dataManager.register(EntityGem.BREWING_PROGRESS, 0);
        this.FOLLOW_ID = UUID.randomUUID();
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setGemPlacement(this.generateGemPlacement());
        this.setSkinVariant(this.generateSkinVariant());
        if(this.setSkinVariantOnInitialSpawn) {
            this.setSkinColorVariant(this.generateSkinColorVariant());
        } else this.setSkinColorVariant(this.initalSkinVariant);
        this.setHairVariant(this.generateHairVariant());
        this.setSkinColor(this.generateSkinColor());
        this.setHairColor(this.generateHairColor());
        this.setGemColor(this.generateGemColor());
        this.setOutfitVariant(this.generateOutfitVariant());
        this.setOutfitColor(this.generateOutfitColor());
        this.setInsigniaVariant(this.generateInsigniaVariant());
        this.setInsigniaColor(this.generateInsigniaColor());
        this.setAbilitySlots(this.generateAbilitySlots());
        this.setAbilites(this.generateAbilities());
        this.setEmotional(this.generateIsEmotional());
        this.setAbilityPowers(this.findAbilities(this.getAbilites()));
        this.addAbilityGoals();
        this.applyAttributeAbilities();
        this.applyAlchemyPowers();
        this.FOLLOW_ID = UUID.randomUUID();
        this.setMarkingVariant(this.generateMarkingVariant());
        this.setMarkingColor(this.generateMarkingColor());
        this.setMarking2Variant(this.generateMarking2Variant());
        this.setMarking2Color(this.generateMarking2Color());
        this.setCustomName(this.getNickname());
        this.generateScoutList();
        this.idlePowers = this.generateIdlePowers();
        if(this.spawnGem != null){
            this.spawnGem.remove();
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean canEquipItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem ||  stack.getItem() instanceof ToolItem;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("abilities", this.getAbilites());
        compound.putBoolean("emotional", this.isEmotional());
        this.writeOwners(compound);
        compound.putUniqueId("followID", this.FOLLOW_ID);
        compound.putByte("movementType", this.getMovementType());
        compound.putInt("skinColorVariant", this.getSkinColorVariant());
        compound.putInt("skinColor", this.getSkinColor());
        compound.putInt("hairColor", this.getHairColor());
        compound.putInt("skinVariant", this.getSkinVariant());
        compound.putInt("hairVariant", this.getHairVariant());
        compound.putInt("gemPlacement", this.getGemPlacement());
        compound.putInt("gemColor", this.getGemColor());
        compound.putInt("outfitColor", this.getOutfitColor());
        compound.putInt("outfitVariant", this.getOutfitVariant());
        compound.putInt("insigniaColor", this.getInsigniaColor());
        compound.putInt("insigniaVariant", this.getInsigniaVariant());
        compound.putInt("abilitySlots", this.getAbilitySlots());
        compound.putBoolean("usesAreaAbility", this.usesAreaAbilities());
        compound.putIntArray("guardPos", this.GUARD_POS);
        compound.putInt("focusLevel", this.focusLevel);
        compound.putByte("emotionMeter", this.emotionMeter);
        compound.putInt("markingVariant", this.getMarkingVariant());
        compound.putInt("markingColor", this.getMarkingColor());
        compound.putInt("marking2Variant", this.getMarking2Variant());
        compound.putInt("marking2Color", this.getMarking2Color());
        compound.putInt("brewingTicks", this.brewingTicks);
        compound.putInt("brewProgress", this.getBrewProgress());
        compound.putBoolean("brewing", this.brewing);
        compound.putInt("structureTime", this.structureTime);
        this.writeStructures(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
    }

    public void writeOwners(CompoundNBT compound){
        for(int i = 0; i < this.OWNERS.size(); i++){
            compound.putUniqueId("owner" + i, this.OWNERS.get(i));
        }
        compound.putInt("ownerAmount", this.OWNERS.size());
    }

    public void writeStructures(CompoundNBT compound){
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
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.setAbilites(compound.getString("abilities"));
        this.setEmotional(compound.getBoolean("emotional"));
        this.readOwners(compound);
        if(compound.contains("followID"))this.FOLLOW_ID = compound.getUniqueId("followID");
        this.setMovementType(compound.getByte("movementType"));
        this.setSkinColorVariant(compound.getInt("skinColorVariant"));
        this.setSkinColor(compound.getInt("skinColor"));
        this.setHairColor(compound.getInt("hairColor"));
        this.setSkinVariant(compound.getInt("skinVariant"));
        this.setHairVariant(compound.getInt("hairVariant"));
        this.setGemPlacement(compound.getInt("gemPlacement"));
        this.setGemColor(compound.getInt("gemColor"));
        this.setOutfitColor(compound.getInt("outfitColor"));
        this.setOutfitVariant(compound.getInt("outfitVariant"));
        this.setInsigniaColor(compound.getInt("insigniaColor"));
        this.setInsigniaVariant(compound.getInt("insigniaVariant"));
        this.setAbilitySlots(compound.getInt("abilitySlots"));
        this.emotionMeter = compound.getByte("emotionMeter");
        this.focusLevel = compound.getInt("focusLevel");
        this.setMovementType(compound.getByte("movementType"));
        this.setAbilityPowers(this.findAbilities(compound.getString("abilities")));
        this.GUARD_POS = compound.getIntArray("guardPos");
        this.addAbilityGoals();
        //this.applyAttributeAbilities();
        this.applyAlchemyPowers();
        this.setMarkingVariant(compound.getInt("markingVariant"));
        this.setMarkingColor(compound.getInt("markingColor"));
        this.setMarking2Variant(compound.getInt("marking2Variant"));
        this.setMarking2Color(compound.getInt("marking2Color"));
        this.brewingTicks = compound.getInt("brewingTicks");
        this.brewing = compound.getBoolean("brewing");
        this.setBrewProgress(compound.getInt("brewProgress"));
        this.structureTime = compound.getInt("structureTime");
        this.idlePowers = this.generateIdlePowers();
        ItemStackHelper.loadAllItems(compound, this.items);
        this.readStructures(compound);
        if(this.spawnGem != null){
            this.spawnGem.remove();
        }
    }

    public void readOwners(CompoundNBT compound){
        this.OWNERS = new ArrayList<>();
        int n = compound.getInt("ownerAmount");
        for(int i = 0; i < n; i++){
            this.OWNERS.add(compound.getUniqueId("owner" + i));
        }
    }

    public void readStructures(CompoundNBT compound){
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
    public void livingTick() {
        if(!this.isFocused()){
            this.focusCounter++;
            if(this.focusCounter > this.maxFocusCounter){
                this.focusLevel = this.baseFocus();
                this.focusCounter = 0;
            }
        }
        if (this.canWalkOnFluids()) this.adjustForFluids();
        if(this.inputItem != Items.AIR && this.outputItem != Items.AIR && this.brewing){
            if(this.getStackInSlot(68) == ItemStack.EMPTY) this.brewingTicks++;
            this.setBrewProgress((int)Math.floor(11 * this.brewingTicks / this.maxBrewingTime));
            //System.out.println("Progress: " + this.getBrewProgress());
            if(this.brewingTicks > this.maxBrewingTime && this.getStackInSlot(67).getItem() == this.inputItem){
                this.setInventorySlotContents(67, ItemStack.EMPTY);
                this.setInventorySlotContents(68, new ItemStack(this.outputItem));
                this.brewingTicks = 0;
                this.setBrewProgress(0);
                this.brewing = false;
            }
        }
        for(IIdleAbility power : this.getIdlePowers()){
            if(this.focusCheck()) power.execute();
        }
        if(this.isInDaylight()){
            if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 20 == 0) {
                this.heal(1.0F);
                this.world.addParticle(ParticleTypes.HEART, this.getPosX(), this.getPosY() + 2, this.getPosZ(), 0,0,0F);
            }
        }

        if(this.usesAreaAbilities()) {
            if (this.ticksExisted % 100 == 0) {
                ArrayList<LivingEntity> entityl = new ArrayList<>(this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.getPosX(), this.getPosY(), this.getPosZ(), this.getPosX() + 1, this.getPosY() + 1, this.getPosZ() + 1)
                        .grow(16, this.world.getHeight(), 16), (target) -> {
                    return target != this;
                }));
                ArrayList<Ability> abilities = this.getAbilityPowers();
                for (int e = 0; e < entityl.size(); e++) {
                    LivingEntity entity = entityl.get(e);
                    boolean flagGem = entity instanceof EntityGem;
                    boolean flagPlayer = entity instanceof PlayerEntity;
                    boolean flagOwner = this.isOwner(entity);
                    EntityGem gemEntity = flagGem ? (EntityGem) entity : null;
                    for (int a = 0; a < abilities.size(); a++) {
                        Ability ability = abilities.get(a);
                        IEffectAbility effectAbility = ability instanceof IEffectAbility ? (IEffectAbility) ability : null;
                        IAreaAbility areaAbility = ability instanceof IAreaAbility ? (IAreaAbility) ability : null;
                        boolean flagArea = areaAbility != null;
                        boolean flagEffect = effectAbility != null;
                        boolean flagViolent = ability instanceof IViolentAbility;
                        if (!flagViolent) {
                            if (flagEffect) {
                                ArrayList<EffectInstance> effects = new ArrayList<>();
                                if (effectAbility.hasMultipleEffects()) {
                                    for (EffectInstance effect : effectAbility.effects()) {
                                        effects.add(effect);
                                    }
                                } else {
                                    effects.add(effectAbility.effect());
                                }
                                if (effectAbility.isEntityApplicable(entity)) {
                                    effects.forEach((effectInstance -> {
                                        if (flagGem) {
                                            if (EntityGem.sharesOwners(this, gemEntity)) {
                                                System.out.println("shares owner");
                                                entity.addPotionEffect(effectInstance);
                                            }
                                        }
                                        if (this.isOwner(entity)) {
                                            entity.addPotionEffect(effectInstance);
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

        super.livingTick();
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
        if(player.world.isRemote){
            return super.applyPlayerInteraction(player, vec, hand);
        }
        //This part of the code checks if the player has a blank hand
        if(hand == Hand.MAIN_HAND) {
            this.currentPlayer = player;
            if(player.getHeldItemMainhand() == ItemStack.EMPTY) {
                if (this.isOwner(player)) {
                    if (player.isSneaking()) {
                        this.cycleMovementAI(player);
                    }
                    else {
                        if(this.canOpenInventoryByDefault()) {
                            NetworkHooks.openGui((ServerPlayerEntity) player, this, buf -> buf.writeInt(this.getEntityId()));
                        }
                        if (this.isRideable()) {
                            if (!this.isBeingRidden()) {
                                player.startRiding(this);
                            }
                        }
                    }
                }
                else {
                    //Test to see if the gem has an owner
                    if (!this.getOwned()) {
                        if(!this.isOwner(player)) {
                            this.addOwner(PlayerEntity.getUUID(player.getGameProfile()));
                            this.FOLLOW_ID = player.getUUID(player.getGameProfile());
                            this.setMovementType((byte) 2);
                            player.sendMessage(new TranslationTextComponent("messages.gempire.entity.claimed"), this.getUniqueID());
                            return super.applyPlayerInteraction(player, vec, hand);
                        }
                    }
                }
            }
            else {
                if(this.isOwner(player)) {
                    if (player.getHeldItemMainhand().getItem() instanceof DyeItem) {
                        DyeItem dye = (DyeItem) player.getHeldItemMainhand().getItem();
                        if (player.isSneaking()) {
                            this.setInsigniaColor(dye.getDyeColor().getId());
                        } else {
                            this.setOutfitColor(dye.getDyeColor().getId());
                        }
                    }
                    else if (player.getHeldItemMainhand().getItem() == Items.PAPER){
                        NetworkHooks.openGui((ServerPlayerEntity) player, this, buf -> buf.writeInt(this.getEntityId()));
                    }
                    else if(player.getHeldItemMainhand().getItem() == Items.BOOK){
                        String list1 = "";
                        for(int i = 0; i < this.structures.size(); i++){
                            if(i == this.structures.size() - 1){
                                list1 += this.structures.get(i);
                            }
                            else{
                                list1 += this.structures.get(i) + ", ";
                            }
                        }
                        String list2 = "";
                        for(int i = 0; i < this.biomes.size(); i++){
                            if(i == this.biomes.size() - 1){
                                list2 += this.biomes.get(i);
                            }
                            else{
                                list2 += this.biomes.get(i) + ", ";
                            }
                        }
                        player.sendMessage(new StringTextComponent("Findable Structures: " + list1), UUID.randomUUID());
                        player.sendMessage(new StringTextComponent("Findable Biomes: " + list2), UUID.randomUUID());
                    }
                }
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    /*
    0 is stay still
    1 is wander
    2 is follow
     */

    public void cycleMovementAI(PlayerEntity player){
        //Cycles through the various movement types.
        this.navigator.clearPath();
        this.FOLLOW_ID = player.getUUID(player.getGameProfile());
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
            this.GUARD_POS[0] = (int) this.getPosX();
            this.GUARD_POS[1] = (int) this.getPosY();
            this.GUARD_POS[2] = (int) this.getPosZ();
            return;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount){
        if(this.world.isRemote){
            return super.attackEntityFrom(source, amount);
        }
        if(this.isEmotional() && !source.isExplosion() && !source.isFireDamage()) {
            if(this.emotionMeter <= this.EmotionThreshold()){
                if(this.EmotionThreshold() - this.emotionMeter < 5){
                    this.world.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getPosX(), this.getPosY() + 2, this.getPosZ(),0,0,0);
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
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if(!entityIn.world.isRemote){
            if(this.focusCheck()) for(Ability power : this.getAbilityPowers()){
                if(power instanceof IMeleeAbility) {
                    ((IMeleeAbility)power).fight(entityIn, this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                }
            }
        }
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        for(Ability power : this.getAbilityPowers()){
            if(power instanceof IRangedAbility && this.canEntityBeSeen(target) && this.focusCheck()){
                ((IRangedAbility)power).attack(target, distanceFactor);
            }
        }
    }

    @Override
    public void onDeath(DamageSource source){
        //When the Gem dies.
        float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
        float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
        float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
        this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX() , this.getPosY() + 2.0D, this.getPosZ(), 0.0D, 0.0D, 0.0D);
        if(!this.world.isRemote){
            GemPoofEvent event = new GemPoofEvent(this, this.getPosition(), source);
            MinecraftForge.EVENT_BUS.post(event);
            ItemStack stack = new ItemStack(this.getGemItem());
            ((ItemGem) stack.getItem()).setData(this, stack);
            ItemEntity item = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), stack);
            item.setNoDespawn();
            this.world.addEntity(item);
        }
        super.onDeath(source);
    }

    public TranslationTextComponent getNickname(){
        if(this instanceof EntityVaryingGem){
            if(((EntityVaryingGem)this).UsesUniqueNames()) {
                return new TranslationTextComponent("nickname.gempire." + this.getWholeGemName() + "_" + this.getSkinColorVariant());
            }
        }
        return new TranslationTextComponent("entity.gempire." + this.getWholeGemName());
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
    public boolean canDespawn(double xix){
        return false;
    }

    //GETTERS AND SETTERS!!!

    public void addOwner(UUID ID){
        this.OWNERS.add(ID);
    }

    public void removeOwner(UUID ID){
        for(int i = 0; i < this.OWNERS.size(); i++){
            if(this.OWNERS.get(i).equals(ID)){
                this.OWNERS.remove(i);
                break;
            }
        }
    }

    public boolean getOwned(){
        return this.OWNERS.size() > 0;
    }

    /*public UUID getOwnerID(){
        return this.dataManager.get(EntityGem.OWNER_ID).get();
    }*/

    public boolean isOwner(LivingEntity entity){
        for(UUID uuid : this.OWNERS){
            if(entity instanceof PlayerEntity){
                if(((PlayerEntity)entity).getUUID(((PlayerEntity) entity).getGameProfile()).equals(uuid)) return true;
            }
            else {
                if (entity.getUniqueID().equals(uuid)) return true;
            }
        }
        return false;
    }

    public boolean isOwner(UUID id){
        for(UUID uuid : this.OWNERS){
            if(id.equals(uuid)) return true;
        }
        return false;
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
        return this.dataManager.get(EntityGem.SKIN_COLOR);
    }

    public void setSkinColor(int value){
        this.dataManager.set(EntityGem.SKIN_COLOR, value);
    }

    public int generateSkinColor(){
        ArrayList<Integer> skins = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/palettes/skin_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                skins.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
            skins.add(0x00000);
        }
        return Color.lerpHex(skins);
    }

    public abstract int generateSkinVariant();

    public int getSkinVariant(){
        return this.dataManager.get(EntityGem.SKIN_VARIANT);
    }

    public void setSkinVariant(int value){
        this.dataManager.set(EntityGem.SKIN_VARIANT, value);
    }


    public int generateMarkingVariant(){
        return this.hasMarkings() ? this.rand.nextInt(this.maxMarkings()) : 0;
    }

    public int generateMarking2Variant(){
        return this.hasMarkings2() ? this.rand.nextInt(this.maxMarkings2()) : 0;
    }

    public int generateMarkingColor(){
        ArrayList<Integer> markings = new ArrayList<>();
        if(this.hasMarkings()) {
            ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/palettes/marking_palette.png");
            BufferedImage palette = null;
            try {
                palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
                System.out.println("Palette Read!");
                for (int x = 0; x < palette.getWidth(); x++) {
                    int color = palette.getRGB(x, this.getSkinColorVariant());
                    if ((color >> 24) == 0x00) {
                        continue;
                    }
                    markings.add(color);
                }
            } catch (IOException e) {
                e.printStackTrace();
                markings.add(0x00000);
            }
        }
        return Color.lerpHex(markings);
    }

    public int generateMarking2Color(){
        ArrayList<Integer> markings = new ArrayList<>();
        if(this.hasMarkings2()) {
            ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/palettes/marking_2_palette.png");
            BufferedImage palette = null;
            try {
                palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
                System.out.println("Palette Read!");
                for (int x = 0; x < palette.getWidth(); x++) {
                    int color = palette.getRGB(x, this.getSkinColorVariant());
                    if ((color >> 24) == 0x00) {
                        continue;
                    }
                    markings.add(color);
                }
            } catch (IOException e) {
                e.printStackTrace();
                markings.add(0x00000);
            }
        }
        return Color.lerpHex(markings);
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
        this.dataManager.set(EntityGem.MARKING_COLOR, color);
    }

    public int getMarkingColor(){
        return this.dataManager.get(EntityGem.MARKING_COLOR);
    }
    public void setMarkingVariant(int value){
        this.dataManager.set(EntityGem.MARKING_VARIANT, value);
    }

    public int getMarkingVariant(){
        return this.dataManager.get(EntityGem.MARKING_VARIANT);
    }

    public void setMarking2Color(int color){
        this.dataManager.set(EntityGem.MARKING_2_COLOR, color);
    }

    public int getMarking2Color(){
        return this.dataManager.get(EntityGem.MARKING_2_COLOR);
    }
    public void setMarking2Variant(int value){
        this.dataManager.set(EntityGem.MARKING_2_VARIANT, value);
    }

    public int getMarking2Variant(){
        return this.dataManager.get(EntityGem.MARKING_2_VARIANT);
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

    public int generateHairColor(){
        ArrayList<Integer> skins = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/palettes/hair_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                skins.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
            skins.add(0x00000);
        }
        return Color.lerpHex(skins);
    }

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

    public int generateGemColor(){
        ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/palettes/gem_palette.png");
        BufferedImage palette = null;
        int color = 0;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            color = palette.getRGB(0, this.getSkinColorVariant());
        }
        catch (IOException e){
            e.printStackTrace();
            color = 0x00000;
        }
        return color;
    }

    public int getOutfitColor(){
        return this.dataManager.get(EntityGem.OUTFIT_COLOR);
    }

    public void setOutfitColor(int value){
        this.dataManager.set(EntityGem.OUTFIT_COLOR, value);
    }

    public int generateOutfitColor(){
        return this.getSkinColorVariant();
    }

    public abstract int generateOutfitVariant();

    public void setOutfitVariant(int value){
        this.dataManager.set(EntityGem.OUTFIT_VARIANT, value);
    }

    public int getOutfitVariant(){
        return this.dataManager.get(EntityGem.OUTFIT_VARIANT);
    }

    public boolean hasOutfitPlacementVariant(){
        return false;
    }

    public int[] outfitPlacementVariants(){
        return new int[]{};
    }

    public abstract int generateInsigniaVariant();

    public void setInsigniaVariant(int value){
        this.dataManager.set(EntityGem.INSIGNIA_VARIANT, value);
    }

    public int getInsigniaVariant(){
        return this.dataManager.get(EntityGem.INSIGNIA_VARIANT);
    }

    public int getInsigniaColor(){
        return this.dataManager.get(EntityGem.INSIGNIA_COLOR);
    }

    public void setInsigniaColor(int value){
        this.dataManager.set(EntityGem.INSIGNIA_COLOR, value);
    }

    public int generateInsigniaColor(){
        return this.getSkinColorVariant();
    }

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

    public abstract byte EmotionThreshold();

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
        if(this instanceof EntityVaryingGem){
            if(((EntityVaryingGem)this).UsesUniqueNames()){
                return ((EntityVaryingGem)this).NameFromColor((byte) this.getSkinColorVariant());
            }
        }
        return this.getType().getRegistryName().toString().replaceAll("(?i)item", "").replaceAll(this.getModID(), "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }

    public String getWholeGemName(){
        return this.getType().getRegistryName().toString().replaceAll("(?i)item", "").replaceAll(this.getModID(), "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }

    //ABILITY STUFF

    @SuppressWarnings("non-varargs")
    public ArrayList<Ability> findAbilities(String getab){
        ArrayList<Abilities> abilities = new ArrayList<>();
        ArrayList<Ability> powers = new ArrayList<>();
        if(!getab.isEmpty()) {
            String[] powerViolenceList = getab.split(",");
            for (int i = 0; i < powerViolenceList.length; i++) {
                abilities.add(Abilities.getAbility(Integer.valueOf(powerViolenceList[i])));
            }
            for (Abilities ability : abilities) {
                //powers.add(Ability.getAbilityFromAbilities(ability).assignAbility(this));
                Class[] parameterType = null;
                Ability ability1 = null;
                try {
                    ability1 = Ability.ABILITY_FROM_ABILITIES.get(ability).getConstructor(parameterType).newInstance(null).assignAbility(this);
                    powers.add(ability1);
                    if((ability1 instanceof IEffectAbility || ability1 instanceof IAreaAbility) && !(ability1 instanceof IViolentAbility)){
                        this.dataManager.set(EntityGem.USES_AREA_ABILITIES, true);
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
                this.getAttribute(((IAttributeAbility)ability).attribute()).setBaseValue(((IAttributeAbility)ability).baseValue());
            }
        }
    }

    public void applyAlchemyPowers() {
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof IAlchemyAbility){
                IAlchemyAbility power = (IAlchemyAbility)ability;
                this.maxBrewingTime = power.maxTime();
                this.inputItem = power.input();
                this.outputItem = power.output();
            }
        }
    }

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
        if(remainingSlots == 0){
            return "0";
        }
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
            for (double r = Math.random() * totalWeight; idx < abilities.length - 1; idx++) {
                r -= abilities[idx].weight;
                if (r <= 0) break;
            }
            Abilities weightedAbility = abilities[idx];
            abilityList+=weightedAbility.id + ",";
            if(this.possibleAbilities().length + this.definiteAbilities().length > this.getAbilitySlots()) abilities = ArrayUtils.remove(abilities, idx);
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

    public boolean usesAreaAbilities(){
        return this.dataManager.get(EntityGem.USES_AREA_ABILITIES);
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
        this.dataManager.set(EntityGem.HAS_VISOR, value);
    }

    public boolean hasVisor(){
        return this.dataManager.get(EntityGem.HAS_VISOR);
    }

    public boolean hasVisorCosmeticOnly(){
        return false;
    }

    public boolean isFocused(){
        return false;
    }

    public boolean focusCheck(){
        return this.isFocused() || (this.focusLevel <=1 ? true : this.rand.nextInt(this.focusLevel) == 0);
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
        return booster.boost(this.getRNG());
    }

    public void travelTowards(Vector3d travelVec) {
        if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof PlayerEntity){
            double speed = ((PlayerEntity) this.getControllingPassenger()).moveForward;
            double speedSwim = this.isSwimming() ? 10 : 1;
            super.travel(travelVec.mul(speed,speed,speed).mul(speedSwim, speedSwim, speedSwim));
        }
        else{
            super.travel(travelVec);
        }
    }

    public void travel(Vector3d travelVector) {
        this.ride(this, this.booster, travelVector);
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeSteered() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            return true;
        }
    }


    public Vector3d func_241205_ce_() {
        return new Vector3d(0.0D, (double)(0.8F * this.getEyeHeight()), (double)(this.getWidth() * 0.4F));
    }

    public float getMountedSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    public void adjustForFluids() {
        if(this.canWalkOnFluids()) {
            if (this.isInWater() || this.isInLava()) {
                ISelectionContext iselectioncontext = ISelectionContext.forEntity(this);
                if (iselectioncontext.func_216378_a(FlowingFluidBlock.LAVA_COLLISION_SHAPE, this.getPosition(), true) && !this.world.getFluidState(this.getPosition().up()).isTagged(FluidTags.LAVA)
                || iselectioncontext.func_216378_a(FlowingFluidBlock.LAVA_COLLISION_SHAPE, this.getPosition(), true) && !this.world.getFluidState(this.getPosition().up()).isTagged(FluidTags.WATER)) {
                    this.onGround = true;
                } else {
                    this.setMotion(this.getMotion().scale(.5D).add(0.0D, 0.05D, 0.0D));
                }
            }
        }
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        this.doBlockCollisions();
        if (this.isInLava()) {
            this.fallDistance = 0.0F;
        } else {
            super.updateFallState(y, onGroundIn, state, pos);
        }
    }

    public void setHasCustomName(boolean value){
        this.dataManager.set(EntityGem.HAS_CUSTOM_NAME, value);
    }

    public boolean customName(){
        return this.dataManager.get(EntityGem.HAS_CUSTOM_NAME);
    }

    public int getLuck(){
        return 0;
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openInventory(PlayerEntity player) {
        player.openContainer(this);
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        player.closeScreen();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return !(stack.getItem() instanceof ItemGem);
    }

    @Override
    public int getSizeInventory() {
        return EntityGem.NUMBER_OF_SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.getItems().get(index - 36);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.getItems(), index - 36, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if(index - 36 > 26 && index - 36 < 31){
            switch(index){
                case 27:
                    this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                case 28:
                    this.setItemStackToSlot(EquipmentSlotType.CHEST, ItemStack.EMPTY);
                case 29:
                    this.setItemStackToSlot(EquipmentSlotType.LEGS, ItemStack.EMPTY);
                default:
                    this.setItemStackToSlot(EquipmentSlotType.FEET, ItemStack.EMPTY);
            }
        }
        if(index == 68){
            this.brewingTicks = 0;
            this.setBrewProgress(0);
        }
        else if(index == 67){
            this.brewingTicks = 0;
            this.setBrewProgress(0);
            this.brewing = false;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.getItems().set(index - 36, stack);
        int ind = index - 36;
        if(stack.getItem() instanceof ArmorItem && index - 36 > 26 && index < 31){
            switch(ind){
                case 27:
                    this.setItemStackToSlot(EquipmentSlotType.HEAD, stack);
                case 28:
                    this.setItemStackToSlot(EquipmentSlotType.CHEST, stack);
                case 29:
                    this.setItemStackToSlot(EquipmentSlotType.LEGS, stack);
                default:
                    this.setItemStackToSlot(EquipmentSlotType.FEET, stack);
            }
        }
        if(index == 67 || index == 68){
            for(Ability ability : this.getAbilityPowers()){
                if(ability instanceof IAlchemyAbility && this.currentPlayer != null && !this.brewing){
                    IAlchemyAbility power = (IAlchemyAbility)ability;
                    if(this.getStackInSlot(67).getItem() == power.input()) {
                        this.outputItem = power.output();
                        this.brewing = power.consume() != Items.AIR ? this.consumeItemCheck(power.consume()) && power.doSpecialActionOnInput(this.currentPlayer) :
                                power.doSpecialActionOnInput(this.currentPlayer);
                    }
                }
            }
        }
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new GemUIContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    @Override
    public void onInventoryChanged(IInventory invBasic) {
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    public void clear() {

    }

    @Override
    public void damageEntity(DamageSource damageSrc, float damageAmount) {
        super.damageEntity(damageSrc, damageAmount);
    }

    public int getBrewProgress(){
        return this.dataManager.get(EntityGem.BREWING_PROGRESS);
    }

    public void setBrewProgress(int value){
        this.dataManager.set(EntityGem.BREWING_PROGRESS, value);
    }

    public boolean canOpenInventoryByDefault(){
        return false;
    }

    public boolean consumeItemCheck(Item item){
        for(int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 4; i++){
            if(this.getStackInSlot(i + 36).getItem() == item){
                if(this.getStackInSlot(i + 36).getCount() == 1){
                    this.setInventorySlotContents(i + 36, ItemStack.EMPTY);
                    return true;
                }else {
                    this.setInventorySlotContents(i + 36, new ItemStack(this.getStackInSlot(i + 36).getItem(),
                            this.getStackInSlot(i + 36).getCount() - 1));
                    //this.getStackInSlot(i + 36).shrink(1);
                    return true;
                }
            }
        }
        return false;
    }

    /*

    COMMAND STUFF

    */

    public static BlockPos findStructure(EntityGem gem, Structure<?> structure) {
        if(gem.world.isRemote){
            return BlockPos.ZERO;
        }
        BlockPos blockpos1 = null;
        BlockPos blockpos = new BlockPos(gem.getPosition());
        if(gem.structures.contains(structure.getRegistryName().toString().replace("minecraft:", ""))) {
             blockpos1 = ((ServerWorld) gem.getEntityWorld()).func_241117_a_(structure, blockpos, 100, false);
        }
        if (blockpos1 == null) {
            return BlockPos.ZERO;
        } else {
            return blockpos1;
        }
    }

    public static BlockPos findBiome(EntityGem gem, ResourceLocation biomeResource) throws CommandSyntaxException {
        if(gem.world.isRemote){
            return BlockPos.ZERO;
        }
        Biome biome = gem.getServer().func_244267_aX().getRegistry(Registry.BIOME_KEY).getOptional(biomeResource).orElseThrow(() -> {
            return LocateBiomeCommand.field_241044_a_.create(biomeResource);
        });
        String s = biomeResource.toString();
        BlockPos blockpos = new BlockPos(gem.getPosition());
        BlockPos blockpos1 = null;
        if(gem.biomes.contains(s.replace("minecraft:", ""))) {
            blockpos1 = ((ServerWorld) gem.getEntityWorld()).func_241116_a_(biome, blockpos, 6400, 8);
        }
        if (blockpos1 == null) {
            return BlockPos.ZERO;
        } else {
            return blockpos1;
        }
    }

    public void runFindCommand(ServerPlayerEntity player, @Nullable Structure<?> structure, @Nullable ResourceLocation biomeResource, boolean biome)
            throws CommandSyntaxException {
        BlockPos pos = biome ? EntityGem.findBiome(this, biomeResource) : EntityGem.findStructure(this, structure);
        if(this.consumeItemCheck(Items.MAP)) {
            if (pos == BlockPos.ZERO) {
                if(biome) {
                    player.sendMessage(new TranslationTextComponent("commands.gempire.norecbio"), UUID.randomUUID());
                }
                else{
                    player.sendMessage(new TranslationTextComponent("commands.gempire.norecstruc"), UUID.randomUUID());
                }
                return;
            }
            boolean done = false;
            ItemStack map = FilledMapItem.setupNewMap(this.world, pos.getX(), pos.getZ(), (byte) 0, true, true);
            MapData.addTargetDecoration(map, pos, "location", MapDecoration.Type.RED_X);
            String name = biome ? biomeResource.toString().replaceAll("minecraft:", "") :
                    structure.getStructureName().replaceAll("minecraft:", "");
            map.setDisplayName(new StringTextComponent(name));
            for (int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 6; i++) {
                if (this.getStackInSlot(i + 36) == ItemStack.EMPTY) {
                    this.setInventorySlotContents(i + 36, map);
                    done = true;
                    break;
                }
            }
            if (!done) {
                this.entityDropItem(map);
            }
            player.sendMessage(new TranslationTextComponent("commands.gempire.foundit"), UUID.randomUUID());
        }
        else{
            player.sendMessage(new TranslationTextComponent("commands.gempire.nomap"), UUID.randomUUID());
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
        Set<ResourceLocation> sussy = net.minecraftforge.registries.ForgeRegistries.STRUCTURE_FEATURES.getKeys();
        rls.addAll(sussy);
        ArrayList<ResourceLocation> resourceLocations = new ArrayList<>();
        while(resourceLocations.size() < 3){
            int m = this.rand.nextInt(sussy.size());
            if(!resourceLocations.contains(rls.get(m))) resourceLocations.add(rls.get(m));
        }
        for(ResourceLocation key : resourceLocations){
            this.structures.add(net.minecraftforge.registries.ForgeRegistries.STRUCTURE_FEATURES.getValue(key).getRegistryName().toString().replace("minecraft:", ""));
        }

        //BIOMES

        ArrayList<ResourceLocation> rls1 = new ArrayList<>();
        Set<ResourceLocation> sus = ForgeRegistries.BIOMES.getKeys();
        rls1.addAll(sus);
        ArrayList<ResourceLocation> resourceLocations1 = new ArrayList<>();
        while(resourceLocations1.size() < 3){
            int m = this.rand.nextInt(sus.size());
            if(!resourceLocations1.contains(rls1.get(m))) resourceLocations1.add(rls1.get(m));
        }
        for(ResourceLocation key : resourceLocations1){
            this.biomes.add(net.minecraftforge.registries.ForgeRegistries.BIOMES.getValue(key).getRegistryName().toString().replace("minecraft:", ""));
        }
    }

    public boolean isOnStructureCooldown(){
        return true;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//



    //TODO: ADDON STUFF



    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public String getModID(){
        return Gempire.MODID;
    }

    public Class getItemRegister(){
        return ModItems.class;
    }
}
