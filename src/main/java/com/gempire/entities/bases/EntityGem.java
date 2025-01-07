package com.gempire.entities.bases;

import com.gempire.config.GempireServerConfigs;
import com.gempire.container.GemUIContainer;
import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.*;
import com.gempire.entities.gems.*;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityNacre;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.events.GemPoofEvent;
import com.gempire.init.*;
import com.gempire.items.*;
import com.gempire.util.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.core.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public abstract class EntityGem extends PathfinderMob implements RangedAttackMob, Container, MenuProvider, ContainerListener {
    //public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final EntityDataAccessor<Boolean> HAS_CUSTOM_NAME = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> EMOTIONAL = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> SKIN_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HAIR_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SKIN_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SKIN_COLOR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> HAIR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> WING_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> WING_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> GEM_PLACEMENT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> GEM_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> OUTFIT_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> OUTFIT_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> INSIGNIA_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> INSIGNIA_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> VISOR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ABILITY_SLOTS = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<String> ABILITIES = SynchedEntityData.<String>defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Boolean> USES_AREA_ABILITIES = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> MARKING_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MARKING_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MARKING_2_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MARKING_2_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> REBEL = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> CRACKED = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> FACET = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> CUT = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> MASTERNAME = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> ASSIGNEDNAME = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> ASSIGNEDFACET = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> ASSIGNEDCUT = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static EntityDataAccessor<Integer> REBEL_HAIR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_OUTFIT_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_OUTFIT_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_INSIGNIA_COLOR = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_INSIGNIA_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> REBEL_VISOR_VARIANT = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Integer> HARDNESS = SynchedEntityData.<Integer>defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> CRACK_AMOUNT = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> ASSIGNED = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> SLUDGE_AMOUNT = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> SHATTER = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> LUMBERJACK = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> CURRENT_RECIPE = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RECIPE_AMOUNT = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> XSCALE = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> YSCALE = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> ZSCALE = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.FLOAT);

    public static final EntityDataAccessor<Integer> QUALITY = SynchedEntityData.defineId(EntityGem.class, EntityDataSerializers.INT);

    public static final EntityDataAccessor<Boolean> DISPLAY = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);


    public boolean isCut;
    public ArrayList<Ability> ABILITY_POWERS = new ArrayList<>();
    public ArrayList<UUID> OWNERS = new ArrayList<>();
    public UUID MASTER_OWNER;
    public UUID FOLLOW_ID;
    public UUID ASSIGNED_ID;
    public BlockPos GUARD_POS;
    public ArrayList<IIdleAbility> idlePowers = new ArrayList<>();
    private final ItemBasedSteering booster = new ItemBasedSteering(this.entityData, BOOST_TIME, SADDLED);
    public byte movementType = 1;
    public byte emotionMeter = 0;
    public boolean meltdown = false;
    public int meltdownCooldown = 0;
    public int initalSkinVariant = 0;
    public boolean setSkinVariantOnInitialSpawn = true;

    public int areaCounter = 100;
    public int maxAreaCounter = 100;
    public int focusCounter = 100;
    public int maxFocusCounter = 100;
    public int ticking;
    public int followCooldown = 0;
    public ArrayList<Item> inputList = new ArrayList<>();
    public ArrayList<Item> input2List = new ArrayList<>();
    public ArrayList<Item> outputList = new ArrayList<>();

    public int timeToCraft = 10;
    public boolean isCrafting;

    public boolean isHostile;

    public Item input;
    public int focusLevel = 2;

    public float rebelPoints = 0.1F;
    public int rebelTicks;

    public boolean followingGarnet;

    public int abilityTicks;
    int crackChance = 50;
    int shatterChance = 200;
    public boolean emerged;

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
    public boolean stopGoals = false;
    public int stopGoalsCooldown = 0;

    public BlockPos stopGoalsPos;
    public boolean enemyDying;
    public LivingEntity enemy;

    public EntityGem assignedOwner;
    private static final DynamicCommandExceptionType ERROR_STRUCTURE_INVALID = new DynamicCommandExceptionType((p_207534_) -> {
        return Component.translatable("commands.gempire.nounderstand", p_207534_);
    });

    private static final DynamicCommandExceptionType ERROR_BIOME_INVALID = new DynamicCommandExceptionType((p_207534_) -> {
        return Component.translatable("commands.gempire.nounderstand", p_207534_);
    });

    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.nounderstand"));

    public EntityGem(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        //this.dataManager.register(EntityGem.OWNER_ID, Optional.ofNullable(UUID.randomUUID()));
        this.entityData.define(EntityGem.HAS_CUSTOM_NAME, false);
        this.entityData.define(EntityGem.QUALITY, 1);
        this.entityData.define(EntityGem.EMOTIONAL, false);
        this.entityData.define(EntityGem.SKIN_COLOR, 0);
        this.entityData.define(EntityGem.HAIR_COLOR, 0);
        this.entityData.define(EntityGem.SKIN_VARIANT, 0);
        this.entityData.define(EntityGem.SKIN_COLOR_VARIANT, 0);
        this.entityData.define(EntityGem.HAIR_VARIANT, 0);
        this.entityData.define(EntityGem.WING_VARIANT, 0);
        this.entityData.define(EntityGem.WING_COLOR, 0);
        this.entityData.define(EntityGem.GEM_PLACEMENT, 0);
        this.entityData.define(EntityGem.GEM_COLOR, 0);
        this.entityData.define(EntityGem.OUTFIT_COLOR, 0);
        this.entityData.define(EntityGem.OUTFIT_VARIANT, 0);
        this.entityData.define(EntityGem.INSIGNIA_COLOR, 0);
        this.entityData.define(EntityGem.INSIGNIA_VARIANT, 0);
        this.entityData.define(EntityGem.VISOR_VARIANT, 0);
        this.entityData.define(EntityGem.ABILITY_SLOTS, 1);
        this.entityData.define(EntityGem.ABILITIES, "-1");
        this.entityData.define(EntityGem.USES_AREA_ABILITIES, false);
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
        this.entityData.define(EntityGem.MASTERNAME, " ");
        this.entityData.define(EntityGem.ASSIGNEDNAME, " ");
        this.entityData.define(EntityGem.ASSIGNEDFACET, " ");
        this.entityData.define(EntityGem.ASSIGNEDCUT, " ");
        this.entityData.define(EntityGem.REBEL_HAIR_VARIANT, 0);
        this.entityData.define(EntityGem.REBEL_OUTFIT_COLOR, 0);
        this.entityData.define(EntityGem.REBEL_OUTFIT_VARIANT, 0);
        this.entityData.define(EntityGem.REBEL_INSIGNIA_COLOR, 0);
        this.entityData.define(EntityGem.REBEL_INSIGNIA_VARIANT, 0);
        this.entityData.define(EntityGem.REBEL_VISOR_VARIANT, 0);
        this.entityData.define(EntityGem.HARDNESS, 0);
        this.entityData.define(EntityGem.CRACK_AMOUNT, 0);
        this.entityData.define(EntityGem.SLUDGE_AMOUNT, 0);
        this.entityData.define(EntityGem.ASSIGNED, false);
        this.entityData.define(EntityGem.SHATTER, false);
        this.entityData.define(EntityGem.CURRENT_RECIPE, 0);
        this.entityData.define(EntityGem.RECIPE_AMOUNT, 0);
        this.entityData.define(EntityGem.XSCALE, 0F);
        this.entityData.define(EntityGem.YSCALE, 0F);
        this.entityData.define(EntityGem.ZSCALE, 0F);
        this.entityData.define(EntityGem.LUMBERJACK, false);
        this.entityData.define(EntityGem.DISPLAY, false);
        this.FOLLOW_ID = UUID.randomUUID();
        this.ASSIGNED_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
        this.MASTER_OWNER = UUID.randomUUID();
        Arrays.fill(this.armorDropChances, 0);
        Arrays.fill(this.handDropChances, 0);
    }


    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setGemPlacement(this.generateGemPlacement());
        this.setSkinVariant(this.generateSkinVariant());
        if (this.setSkinVariantOnInitialSpawn) this.setSkinColorVariant(this.generateSkinColorVariant()); else this.setSkinColorVariant(this.initalSkinVariant);
        this.setHairVariant(this.generateHairVariant());
        this.setWingVariant(this.generateWingVariant());
        this.setSkinColor(this.generatePaletteColor(PaletteType.SKIN));
        this.setWingColor(this.generatePaletteColor(PaletteType.WING));
        this.setHairColor(this.generatePaletteColor(PaletteType.HAIR));
        this.setGemColor(this.generatePaletteColor(PaletteType.GEM));
        this.setOutfitVariant(this.generateOutfitVariant());
        this.setOutfitColor(this.generateOutfitColor());
        this.setInsigniaVariant(this.generateInsigniaVariant());
        this.setInsigniaColor(this.generateInsigniaColor());
        this.setVisorVariant(this.generateVisorVariant());
        this.setAbilitySlots(this.generateAbilitySlots());
        this.setAbilities(this.generateAbilities());
        this.setEmotional(this.generateIsEmotional());
        this.setAbilityPowers(this.findAbilities(this.getAbilities()));
        this.addAbilityGoals();
        this.applyAttributeAbilities();
        this.setFacet(this.generateFacet());
        this.setCut(this.generateCut());
        this.FOLLOW_ID = UUID.randomUUID();
        this.ASSIGNED_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
        this.MASTER_OWNER = UUID.randomUUID();
        this.setMarkingVariant(this.generateMarkingVariant());
        this.setMarkingColor(this.generatePaletteColor(PaletteType.MARKINGS));
        this.setMarking2Variant(this.generateMarking2Variant());
        this.setMarking2Color(this.generatePaletteColor(PaletteType.MARKINGS_2));
        if (this.hasCustomName()) this.setCustomName(this.getName()); else this.setCustomName(this.getNickname());
        this.rebelPoints = 0.5f;
        this.rebelTicks = 1;
        //this.generateScoutList();
        this.idlePowers = this.generateIdlePowers();
        if (this.spawnGem != null) this.spawnGem.remove(RemovalReason.DISCARDED);
        ItemStack stack = new ItemStack(this.getGemItem());
        ItemGem.saveData(stack, this);
        //setAssignedGem(((ItemGem) stack.getItem()).assigned_gem);
        System.out.println(this.getAssignedGem());
        this.setRebelHairVariant(this.generateHairVariant());
        this.setRebelOutfitVariant(this.generateOutfitVariant());
        this.setRebelOutfitColor(this.random.nextInt(16));
        this.setRebelVisorVariant(this.generateVisorVariant());
        this.setRebelInsigniaVariant(this.generateRebelInsigniaVariant());
        this.setRebelInsigniaColor(this.random.nextInt(16));
        this.setHardness(this.getHardness());
        this.setCrackAmount(this.getCrackAmount());
        this.setSludgeAmount(this.getSludgeAmount());
        this.setAssigned(this.getAssigned());
        this.setShatter(this.getShatter());
        this.setCurrentRecipe(this.getCurrentRecipe());
        this.setRecipeAmount(this.generateRecipeAmount());
        this.setXScale(this.generateXScale());
        this.setYScale(this.generateYScale());
        this.setZScale(this.generateZScale());
        this.setQuality(this.getQuality());
        this.setLumberjack(false);
        this.GUARD_POS = this.getOnPos().above();
        this.entityData.set(EntityGem.DISPLAY, false);
        //this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.OAK_LOG));
        //if (isArcher()) this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        AttributeModifier PRIME = new AttributeModifier(UUID.randomUUID(), "gempirePrimaryModifier", 5D, AttributeModifier.Operation.ADDITION);
        AttributeModifier PRIME_SPEED = new AttributeModifier(UUID.randomUUID(), "gempirePrimarySpeedModifier", .1D, AttributeModifier.Operation.ADDITION);
        AttributeModifier DEFECTIVE = new AttributeModifier(UUID.randomUUID(), "gempireDefectiveModifier", -5D, AttributeModifier.Operation.ADDITION);
        AttributeModifier DEFECTIVE_SPEED = new AttributeModifier(UUID.randomUUID(), "gempireDefectiveSpeedModifier", -.1D, AttributeModifier.Operation.ADDITION);
        if (this.isPrimary()) {
            System.out.println("prime modifiers");
                this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(PRIME);
                this.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(PRIME_SPEED);
                this.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(PRIME);
                this.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(PRIME);
                this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(PRIME);
        } else if (this.isDefective()) {
            System.out.println("off colour modifiers");
                this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(DEFECTIVE);
                this.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(DEFECTIVE_SPEED);
                this.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(DEFECTIVE);
                this.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(DEFECTIVE);
                this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(DEFECTIVE);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkNotRebel));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 1, true, false, this::isHostileAt));
    }

    public boolean checkRebel(LivingEntity entity) {
        if (!this.getRebelled() && !((EntityGem) entity).getOwned()) {
            return ((EntityGem) entity).getRebelled();
        } else {
            return false;
        }
    }

    public boolean checkSludged(LivingEntity entity) {
        return this.getSludgeAmount() >= 5;
    }

    public boolean checkNotSludged(LivingEntity entity) {
        return this.getSludgeAmount() < 5;
    }

    public boolean checkBothSludged(LivingEntity entity) {
        return this.getSludgeAmount() >= 5 && ((EntityGem) entity).getSludgeAmount() >= 5;
    }

    public boolean checkElseSludged(LivingEntity entity) {
        return ((EntityGem) entity).getSludgeAmount() >= 5;
    }

    public boolean wantsToAttack(LivingEntity p_21810_, LivingEntity p_21811_) {
        return true;
    }

    public boolean checkNotRebel(LivingEntity entity) {
        if (this.getRebelled()) {
            return !((EntityGem) entity).getRebelled();
        } else {
            return false;
        }
    }

    private boolean isHostileAt(LivingEntity entity) {
        return isHostile || getRebelled();
    }


    @Override
    public boolean canHoldItem(ItemStack stack) {
        if (this instanceof EntityPearl) return false;
        else if (this.isArcher()) return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof BowItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof DestabBase || stack.getItem() instanceof ItemShatterer;
        else if (this.isTinkerer()) return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof BowItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof ArmorItem || stack.getItem() instanceof DestabBase || stack.getItem() instanceof ItemShatterer;
        else if (this.isTorchBearer()) return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof DestabBase || stack.getItem() instanceof ItemShatterer || stack.getItem() == Items.TORCH;
        else if (this.isFarmer()) return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof DestabBase || stack.getItem() instanceof ItemShatterer || stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.CARROT || stack.getItem() == Items.POTATO || stack.getItem() == Items.BEETROOT_SEEDS;
        else if (this.canCraft()) {
            boolean bool = false;
            for (int i = 0; i < getRecipeAmount(); i++) if (stack.getItem() == this.getInputItem(i)) bool = true;
            return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof DestabBase || stack.getItem() instanceof ItemShatterer || bool;
        }
        else return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof DestabBase || stack.getItem() instanceof ItemShatterer;
    }

    public SoundEvent getInstrument() {
        return SoundEvents.NOTE_BLOCK_HARP.get();
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

    public float interactPitch() {
        return (float) (1 + random.nextFloat() * (1.5 - 1));
    }

    public float hurtPitch() {
        return (float) (0.25 + random.nextFloat() * (0.75 - 0.25));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("abilities", this.getAbilities());
        compound.putString("name", this.getName().getString());
        compound.putBoolean("display", this.entityData.get(EntityGem.DISPLAY));
        this.writeCrackShatter(compound);
        this.writeOwners(compound);
        this.writeIDs(compound);
        this.writeCraft(compound);
        this.writeScale(compound);
        this.writeColour(compound);
        this.writeVariant(compound);
        this.writeRebelColour(compound);
        this.writeRebelVariant(compound);
        this.writeFacetCut(compound);
        this.writeAbilityUtil(compound);
        this.writeUtil(compound);
        this.writeAssignedUtil(compound);
        compound.putString("masterName", getMasterName());
        if (this.canLocateStructures()) compound.putInt("structureTime", this.structureTime);
        if (this.canLocateStructures()) this.writeStructures(compound);
        ContainerHelper.saveAllItems(compound, this.items);
        System.out.println(compound);
    }

    public void writeOwners(CompoundTag compound) {
        for (int i = 0; i < this.OWNERS.size(); i++) {
            compound.putUUID("owner" + i, this.OWNERS.get(i));
        }
        compound.putInt("ownerAmount", this.OWNERS.size());
    }

    public void writeStructures(CompoundTag compound) {
        for (int i = 0; i < this.structures.size(); i++) {
            compound.putString("structure" + i, this.structures.get(i));
        }
        for (int i = 0; i < this.biomes.size(); i++) {
            compound.putString("biome" + i, this.biomes.get(i));
        }
        compound.putInt("structureAmount", this.structures.size());
        compound.putInt("biomeAmount", this.biomes.size());
    }

    public void writeUtil(CompoundTag compound) {
        String string;
        if (GUARD_POS != null) {
            string = movementType + "," + getSkinColorVariant() + "," +
                    getRebelled() + "," + getQuality() + "," +
                    getHostile() + "," + getAssigned() + "," +
                    rebelPoints + "," + rebelTicks + "," + GUARD_POS.asLong();
        }
        else {
            string = movementType + "," + getSkinColorVariant() + "," +
                    getRebelled() + "," + getQuality() + "," +
                    getHostile() + "," + getAssigned() + "," +
                    rebelPoints + "," + rebelTicks;
        }
        compound.putString("util", string);
    }

    public void readUtil(CompoundTag compound) {
        String[] strings = compound.getString("util").split(",");
        movementType = Byte.parseByte(strings[0]);
        setSkinColorVariant(Integer.parseInt(strings[1]));
        setRebelled(Boolean.parseBoolean(strings[2]));
        setQuality(Integer.parseInt(strings[3]));
        isHostile = Boolean.parseBoolean(strings[4]);
        setAssigned(Boolean.parseBoolean(strings[5]));
        rebelPoints = Float.parseFloat(strings[6]);
        rebelTicks = Integer.parseInt(strings[7]);
        GUARD_POS = BlockPos.of(Long.parseLong(strings[8]));
    }

    public void writeAbilityUtil(CompoundTag compound) {
        String string = getAbilitySlots() + "," + usesAreaAbilities() +  "," +
                abilityTicks + "," + emotionMeter + "," + isEmotional() + "," +
                focusLevel;
        compound.putString("ability", string);
    }

    public void readAbilityUtil(CompoundTag compound) {
        String[] strings = compound.getString("ability").split(",");
        setAbilitySlots(Integer.parseInt(strings[0]));
        setUsesAreaAbilities(Boolean.getBoolean(strings[1]));
        abilityTicks = Integer.parseInt(strings[2]);
        emotionMeter = Byte.parseByte(strings[3]);
        setEmotional(Boolean.parseBoolean(strings[4]));
        focusLevel = Integer.parseInt(strings[5]);
        this.idlePowers = this.generateIdlePowers();
        this.addAbilityGoals();
    }

    public void writeIDs(CompoundTag compound) {
        compound.putUUID("followID", FOLLOW_ID);
        compound.putUUID("assignedID", ASSIGNED_ID);
        compound.putUUID("master", MASTER_OWNER);
    }

    public void readIDs(CompoundTag compound) {
        FOLLOW_ID = compound.getUUID("followID");
        ASSIGNED_ID = compound.getUUID("assignedID");
        MASTER_OWNER = compound.getUUID("master");
    }

    public void writeCrackShatter(CompoundTag compound) {
        String string = getCracked() + "," + getShatter() +  "," +
                getCrackAmount() + "," + getSludgeAmount() + "," +
                getHardness();
        compound.putString("crackShatter", string);
    }

    public void readCrackShatter(CompoundTag compound) {
        String[] strings = compound.getString("crackShatter").split(",");
        setCracked(Boolean.parseBoolean(strings[0]));
        setShatter(Boolean.parseBoolean(strings[1]));
        setCrackAmount(Integer.parseInt(strings[2]));
        setSludgeAmount(Integer.parseInt(strings[3]));
        setHardness(Integer.parseInt(strings[4]));
    }

    public void writeCraft(CompoundTag compound) {
        String string = isCrafting + "," + ticking +  "," +
                getCurrentRecipe() + "," + getRecipeAmount();
        compound.putString("craft", string);
    }

    public void readCraft(CompoundTag compound) {
        String[] strings = compound.getString("craft").split(",");
        isCrafting = Boolean.parseBoolean(strings[0]);
        ticking = Integer.parseInt(strings[1]);
        setCurrentRecipe(Integer.parseInt(strings[2]));
        setRecipeAmount(Integer.parseInt(strings[3]));
    }

    public void writeFacetCut(CompoundTag compound) {
        String string = getFacet() + "," + getCut();
        compound.putString("facetCut", string);
    }

    public void readFacetCut(CompoundTag compound) {
        String[] strings = compound.getString("facetCut").split(",");
        setFacet(strings[0]);
        setCut(strings[1]);
    }

    public void writeScale(CompoundTag compound) {
        String string = getXScale() + "," + getYScale() + "," + getZScale();
        compound.putString("scale", string);
    }

    public void readScale(CompoundTag compound) {
        String[] strings = compound.getString("scale").split(",");
        setXScale(Float.parseFloat(strings[0]));
        setYScale(Float.parseFloat(strings[1]));
        setZScale(Float.parseFloat(strings[2]));
    }

    public void writeColour(CompoundTag compound) {
        String string = getSkinColor() + "," + getHairColor() + "," +
                getGemColor() + "," + getMarkingColor() + "," +
                getMarking2Color() + "," + getOutfitColor() + "," +
                getInsigniaColor() + "," + getWingColor();
        compound.putString("colour", string);
    }

    public void readColour(CompoundTag compound) {
        String[] strings = compound.getString("colour").split(",");
        setSkinColor(Integer.parseInt(strings[0]));
        setHairColor(Integer.parseInt(strings[1]));
        setGemColor(Integer.parseInt(strings[2]));
        setMarkingColor(Integer.parseInt(strings[3]));
        setMarking2Color(Integer.parseInt(strings[4]));
        setOutfitColor(Integer.parseInt(strings[5]));
        setInsigniaColor(Integer.parseInt(strings[6]));
        setWingColor(Integer.parseInt(strings[7]));
    }

    public void writeRebelColour(CompoundTag compound) {
        String string = getOutfitColor() + "," + getInsigniaColor();
        compound.putString("rebelColour", string);
    }

    public void readRebelColour(CompoundTag compound) {
        String[] strings = compound.getString("rebelColour").split(",");
        setRebelOutfitColor(Integer.parseInt(strings[0]));
        setRebelInsigniaColor(Integer.parseInt(strings[1]));
    }

    public void writeVariant(CompoundTag compound) {
        String string = getSkinVariant() + "," + getHairVariant() + "," +
                getGemPlacement() + "," + getMarkingVariant() + "," +
                getMarking2Variant() + "," + getOutfitVariant() + "," +
                getInsigniaVariant() + "," + getWingVariant() + "," +
                getVisorVariant();
        compound.putString("variant", string);
    }

    public void readVariant(CompoundTag compound) {
        String[] strings = compound.getString("variant").split(",");
        setSkinVariant(Integer.parseInt(strings[0]));
        setHairVariant(Integer.parseInt(strings[1]));
        setGemPlacement(Integer.parseInt(strings[2]));
        setMarkingVariant(Integer.parseInt(strings[3]));
        setMarking2Variant(Integer.parseInt(strings[4]));
        setOutfitVariant(Integer.parseInt(strings[5]));
        setInsigniaVariant(Integer.parseInt(strings[6]));
        setWingVariant(Integer.parseInt(strings[7]));
        setVisorVariant(Integer.parseInt(strings[8]));
    }

    public void writeRebelVariant(CompoundTag compound) {
        String string = getRebelHairVariant() + "," + getRebelOutfitVariant() + "," +
                getRebelInsigniaVariant() + "," + getRebelVisorVariant();
        compound.putString("rebelVariant", string);
    }

    public void readRebelVariant(CompoundTag compound) {
        String[] strings = compound.getString("rebelVariant").split(",");
        setRebelHairVariant(Integer.parseInt(strings[0]));
        setRebelOutfitVariant(Integer.parseInt(strings[1]));
        setRebelInsigniaVariant(Integer.parseInt(strings[2]));
        setRebelVisorVariant(Integer.parseInt(strings[3]));
    }

    public void writeAssignedUtil(CompoundTag compound) {
        compound.putString("assignedUtil", getAssignedUtil());
    }

    public void readAssignedUtil(CompoundTag compound) {
        String[] strings = compound.getString("assignedUtil").split(",");
        setAssignedUtil(strings[0], strings[1], strings[2]);
    }

    @Override
    public void load(CompoundTag compound) {
        boolean display = compound.getBoolean("display");
        System.out.println(compound);
        this.entityData.set(EntityGem.DISPLAY, display);
        System.out.println(display);
        if (!display) {
            this.setCustomName(Component.translatable(compound.getString("name")));
            this.setAbilityPowers(this.findAbilities(compound.getString("abilities")));
            this.setAbilities(compound.getString("abilities"));
            if (this.canLocateStructures()) this.structureTime = compound.getInt("structureTime");
            AttributeModifier PRIME = new AttributeModifier(UUID.randomUUID(), "gempirePrimaryModifier", 5D, AttributeModifier.Operation.ADDITION);
            AttributeModifier PRIME_SPEED = new AttributeModifier(UUID.randomUUID(), "gempirePrimarySpeedModifier", .2D, AttributeModifier.Operation.ADDITION);
            AttributeModifier DEFECTIVE = new AttributeModifier(UUID.randomUUID(), "gempireDefectiveModifier", -5D, AttributeModifier.Operation.ADDITION);
            AttributeModifier DEFECTIVE_SPEED = new AttributeModifier(UUID.randomUUID(), "gempireDefectiveSpeedModifier", -.1D, AttributeModifier.Operation.ADDITION);
            if (!(this instanceof EntityStarterGem)) {
                if (this.isPrimary() && !(this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(PRIME_SPEED)) && !(this.getAttribute(Attributes.MAX_HEALTH).hasModifier(PRIME))) {
                    System.out.println("prime modifiers");
                    this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(PRIME);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(PRIME_SPEED);
                    this.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(PRIME);
                    this.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(PRIME);
                    this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(PRIME);
                } else if (this.isDefective() && !(this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(DEFECTIVE_SPEED)) && !(this.getAttribute(Attributes.MAX_HEALTH).hasModifier(DEFECTIVE))) {
                    System.out.println("off colour modifiers");
                    this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(DEFECTIVE);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(DEFECTIVE_SPEED);
                    this.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(DEFECTIVE);
                    this.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(DEFECTIVE);
                    this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(DEFECTIVE);
                }
            }
            this.readAbilityUtil(compound);
            this.readScale(compound);
            this.readOwners(compound);
            this.readIDs(compound);
            this.readCraft(compound);
            this.readRebelVariant(compound);
            this.readRebelColour(compound);
            this.readVariant(compound);
            this.readColour(compound);
            this.readFacetCut(compound);
            this.readCrackShatter(compound);
            this.readUtil(compound);
            if (getAssigned()) {
                this.readAssignedUtil(compound);
            }
            this.setMasterName(compound.getString("masterName"));
            if (this.canLocateStructures()) this.readStructures(compound);
            ContainerHelper.loadAllItems(compound, this.items);
            if (this.spawnGem != null) {
                this.spawnGem.remove(RemovalReason.DISCARDED);
            }
        } else {
            this.readVariant(compound);
            this.readColour(compound);
            this.readScale(compound);
        }
        super.load(compound);
    }

    public void readOwners(CompoundTag compound) {
        this.OWNERS = new ArrayList<>();
        int n = compound.getInt("ownerAmount");
        for (int i = 0; i < n; i++) {
            this.OWNERS.add(compound.getUUID("owner" + i));
        }
    }

    public void readStructures(CompoundTag compound) {
        this.structures = new ArrayList<>();
        int n = compound.getInt("structureAmount");
        for (int i = 0; i < n; i++) {
            this.structures.add(compound.getString("structure" + i));
        }
        this.biomes = new ArrayList<>();
        int t = compound.getInt("biomeAmount");
        for (int i = 0; i < t; i++) {
            this.biomes.add(compound.getString("biome" + i));
        }
    }

    @Override
    public void aiStep() {
        if (!this.isFocused()) {
            this.focusCounter++;
            if (this.focusCounter > this.maxFocusCounter) {
                this.focusLevel = this.baseFocus();
                this.focusCounter = 0;
            }
        }
        if (this.canWalkOnFluids()) this.adjustForFluids();
        for (IIdleAbility power : this.getIdlePowers()) {
            if (this.focusCheck()) power.execute();
        }
        if (this.isSunBurnTick()) {
            if (this.getHealth() < this.getMaxHealth() && this.tickCount % 20 == 0) {
                this.heal(1.0F);
                this.level().addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 2, this.getZ(), 0, 0, 0F);
            }
        }

        if (this.usesAreaAbilities()) {
            if (this.tickCount % 100 == 0) {
                ArrayList<LivingEntity> entityl = new ArrayList<>(this.level().getEntitiesOfClass(LivingEntity.class, new AABB(this.getX(), this.getY(), this.getZ(), this.getX() + 1, this.getY() + 1, this.getZ() + 1)
                        .inflate(16, this.level().getMaxBuildHeight(), 16), (target) -> {
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

    public Item getInputItem(int i) {
        if (this.canCraft()) return inputList.get(i);
        else return Items.AIR;
    }

    public Item getInputItem2(int i) {
        if (this.canCraft()) return input2List.get(i);
        else return Items.AIR;
    }

    public Item getOutputItem(int i) {
        if (this.canCraft() && !outputList.isEmpty()) return outputList.get(i);
        else return Items.AIR;
    }

    public int getCurrentRecipe() {
        return this.entityData.get(EntityGem.CURRENT_RECIPE);
    }

    public void setCurrentRecipe(int i) {
        this.entityData.set(EntityGem.CURRENT_RECIPE, i);
    }

    public int getRecipeAmount() {
        return this.entityData.get(EntityGem.RECIPE_AMOUNT);
    }

    public void setRecipeAmount(int i) {
        this.entityData.set(EntityGem.RECIPE_AMOUNT, i);
    }

    public int generateRecipeAmount() {
        int i = 0;
        if (canCraft()) {
            /*for(Ability ability : this.getAbilityPowers()){
                if(ability instanceof ICraftingAbility){
                    i+= ((ICraftingAbility) ability).recipeAmount();
                }
            }
            return i;*/
            return inputList.size();
        }
        else return 0;
    }

    public int getTimetoCraft() {
        if (canCraft()) {
            if (isPrimary()) {
                return 5 * 20;
            } else if (isDefective()) {
                return 15 * 20;
            }
            return 10 * 20;
        }
        else return timeToCraft;
    }

    public boolean canCraft() {
        boolean flag = false;
        inputList.clear();
        outputList.clear();
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof ICraftingAbility){
                ((ICraftingAbility) ability).setup();
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide) {
            if (followingGarnet) {
                if (!this.focusCheck() || random.nextInt(100) == 1) {
                    followingGarnet = false;
                    followCooldown = 100;
                }
            }
            if (followCooldown != 0) {
                followCooldown--;
                followingGarnet = false;
            }
            if (stopGoals) {
                this.teleportTo(stopGoalsPos.getX() + 0.5, stopGoalsPos.getY(), stopGoalsPos.getZ() + 0.5);
                this.setDeltaMovement(0, 0, 0);
                if (stopGoalsCooldown == 0) {
                    stopGoals = false;
                    stopGoalsPos = null;
                } else {
                    stopGoalsCooldown--;
                }
            }
            if (meltdown) {
                if (meltdownCooldown == 0) {
                    meltdown = false;
                } else {
                    meltdownCooldown--;
                }
            }
            if (isCrafting) {
                if (!getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                    ticking++;
                    if (ticking >= getTimetoCraft()) {
                        popShitOut();
                    }
                } else {
                    isCrafting = false;
                    ticking = 0;
                    this.setCurrentRecipe(0);
                }
            }
            if (!getRebelled() && getOwned() && GempireServerConfigs.REBELLION.get()) {
                rebelTicks++;
                if (rebelTicks >= 20 * (10 * 60)) {
                    checkRebel();
                }
            }
            if (abilityTicks > 0) {
                abilityTicks--;
            }
            if (enemyDying) {
                if (enemy.getHealth() <= 0) {
                    if (enemy.getLastHurtByMob() == this) {
                        dropXP(enemy);
                        for (Ability ability : this.getAbilityPowers()) {
                            if (ability instanceof AbilityAbundance) {
                                dropXP(enemy);
                            }
                        }
                    }
                    enemy = null;
                    enemyDying = false;
                }
            }
        }
        super.tick();
    }

    private void checkRebel() {
        double rebel = (0 + random.nextDouble() * 500);
        if (rebel < rebelPoints) {
            rebel();
        }
        rebelTicks = 0;
    }

    public boolean justEmerged() {
        return emerged;
    }

    public Float getXScale(){
        return this.entityData.get(EntityGem.XSCALE);
    }

    public void setXScale(float value){
        this.entityData.set(EntityGem.XSCALE, value);
    }

    public Float getYScale(){
        return this.entityData.get(EntityGem.YSCALE);
    }

    public void setYScale(float value){
        this.entityData.set(EntityGem.YSCALE, value);
    }

    public Float getZScale(){
        return this.entityData.get(EntityGem.ZSCALE);
    }

    public void setZScale(float value){
        this.entityData.set(EntityGem.ZSCALE, value);
    }

    public String getMasterName(){
        return this.entityData.get(EntityGem.MASTERNAME);
    }

    public void setMasterName(String value){
        this.entityData.set(EntityGem.MASTERNAME, value);
    }

    public String getAssignedName(){
        return this.entityData.get(EntityGem.ASSIGNEDNAME);
    }

    public void setAssignedName(String value){
        this.entityData.set(EntityGem.ASSIGNEDNAME, value);
    }

    public String getAssignedFacet(){
        return this.entityData.get(EntityGem.ASSIGNEDFACET);
    }

    public void setAssignedFacet(String value){
        this.entityData.set(EntityGem.ASSIGNEDFACET, value);
    }

    public String getAssignedCut(){
        return this.entityData.get(EntityGem.ASSIGNEDCUT);
    }

    public void setAssignedCut(String value){
        this.entityData.set(EntityGem.ASSIGNEDCUT, value);
    }

    public Float generateXScale() {
        if (isPrimary()) {
            return baseXScale() + 0.15F;
        } else if (isDefective()) {
            Random r = new Random();
            return baseXScale() - r.nextFloat(.45F);
        } else {
            return baseXScale();
        }
    }

    public Float generateYScale() {
        if (isPrimary()) {
            return baseYScale() + 0.15F;
        } else if (isDefective()) {
            Random r = new Random();
            return baseYScale() - r.nextFloat(.45F);
        } else {
            return baseYScale();
        }
    }

    public Float generateZScale() {
        if (isPrimary()) {
            return baseZScale() + 0.15F;
        } else if (isDefective()) {
            Random r = new Random();
            return baseZScale() - r.nextFloat(.45F);
        } else {
            return baseZScale();
        }
    }

    public abstract Float baseXScale();
    public abstract Float baseYScale();
    public abstract Float baseZScale();

    @Override
    public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand) {
        if (!this.getRebelled() && !(this.getSludgeAmount() >= 5)) {
                if (player.level().isClientSide) {
                    return super.interactAt(player, vec, hand);
                }
                //This part of the code checks if the player has a blank hand
                if (!level().isClientSide) {
                    this.currentPlayer = player;
                    if (!player.isSpectator()) {
                        if (hand == InteractionHand.MAIN_HAND) {
                            if (player.getItemInHand(hand).isEmpty()) {
                                if (this.isOwner(player)) {
                                    if (!this.isDeadOrDying()) {
                                            if (player.isShiftKeyDown()) {
                                                this.cycleMovementAI(player);
                                                this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                                                if (OWNERS.size() <= 1 && this.MASTER_OWNER != player.getUUID()) {
                                                    MASTER_OWNER = player.getUUID();
                                                    setMasterName(String.valueOf(player.getName()));
                                                    System.out.println("Added master owner");
                                                }
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
                                    }
                                } else {
                                    //Test to see if the gem has an owner
                                    if (!this.getOwned()) {
                                        if (!this.isOwner(player)) {
                                            this.addOwner(player.getUUID());
                                            this.addMasterOwner(player.getUUID());
                                            setFollow(player.getUUID());
                                            player.sendSystemMessage(Component.literal("Claimed ").append(getName().getString()).append(" " + getFacetAndCut()));
                                            this.setMovementType((byte) 2);
                                            this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                                            return super.interactAt(player, vec, hand);
                                        }
                                    }
                                }
                            } else {
                                int count = 0;
                                boolean requiresCountSet = false;
                                if (!this.isDeadOrDying()) {
                                    if (this.isOwner(player)) {
                                        if (player.getMainHandItem().getItem() instanceof DyeItem dye) {
                                            if (player.isShiftKeyDown()) {
                                                if (canChangeInsigniaColorByDefault())
                                                    this.setInsigniaColor(dye.getDyeColor().getId());
                                            } else {
                                                if (canChangeUniformColorByDefault())
                                                    this.setOutfitColor(dye.getDyeColor().getId());
                                            }
                                        } else if (player.getMainHandItem().getItem() == Items.PAPER) {
                                            NetworkHooks.openScreen((ServerPlayer) player, this, buf -> buf.writeInt(this.getId()));
                                        } else if (player.getMainHandItem().getItem() == Items.BOOK) {
                                            if (this.canLocateStructures()) {
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
                                            if (this.isFarmer()) {
                                                announceCrops();
                                            }
                                        } else if (this.canCraft() && !this.level().isClientSide) {
                                            setRecipeAmount(generateRecipeAmount());
                                            System.out.println("recipe amount "+getRecipeAmount());
                                            for (int i = 0; i < getRecipeAmount(); i++) {
                                                if (player.getItemInHand(hand).getItem() == getInputItem(i)) {
                                                    if (!isCrafting) {
                                                        System.out.println(getInputItem(i).asItem());
                                                        if (getInputItem(i) != Items.AIR.asItem()) {
                                                            if (getInputItem2(i) != Items.AIR) {
                                                                if (consumeItemCheck(getInputItem2(i), 1)) {
                                                                    inputList.clear();
                                                                    setCurrentRecipe(i);
                                                                    if (this.isOwner(player)) {
                                                                        isCrafting = true;
                                                                        this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                                                                        System.out.println("player " + player.getMainHandItem());
                                                                        ItemStack stack = new ItemStack(player.getMainHandItem().getItem());
                                                                        System.out.println("stack " + stack.getCount());
                                                                        if (!player.isCreative())
                                                                            player.getMainHandItem().shrink(1);
                                                                        if (!this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                                                                            ItemStack gemStack = this.getItemBySlot(EquipmentSlot.MAINHAND);
                                                                            spawnAtLocation(gemStack);
                                                                        }
                                                                        this.setItemSlot(EquipmentSlot.MAINHAND, stack);
                                                                        break;
                                                                    }
                                                                    return super.interactAt(player, vec, hand);
                                                                }
                                                            } else {
                                                                inputList.clear();
                                                                setCurrentRecipe(i);

                                                                if (this.isOwner(player)) {
                                                                    isCrafting = true;
                                                                    this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                                                                    ItemStack stack = new ItemStack(player.getMainHandItem().getItem());
                                                                    stack.setCount(1);
                                                                    if (!player.isCreative()) {
                                                                        player.getMainHandItem().shrink(1);
                                                                    }
                                                                    if (!this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                                                                        ItemStack gemStack = this.getItemBySlot(EquipmentSlot.MAINHAND);
                                                                        spawnAtLocation(gemStack);
                                                                    }
                                                                    this.setItemSlot(EquipmentSlot.MAINHAND, stack);
                                                                    break;
                                                                }
                                                                return super.interactAt(player, vec, hand);
                                                            }
                                                        } else {
                                                            System.out.println("input item air check");
                                                            inputList.clear();
                                                            setCurrentRecipe(i);

                                                            if (this.isOwner(player)) {
                                                                isCrafting = true;
                                                                this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
                                                                if (!player.isCreative()) {
                                                                    player.getMainHandItem().shrink(1);
                                                                }
                                                                ItemStack stack = new ItemStack(player.getMainHandItem().getItem());
                                                                stack.setCount(1);
                                                                if (!this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                                                                    ItemStack gemStack = this.getItemBySlot(EquipmentSlot.MAINHAND);
                                                                    spawnAtLocation(gemStack);
                                                                }
                                                                this.setItemSlot(EquipmentSlot.MAINHAND, stack);
                                                                break;
                                                            }
                                                            return super.interactAt(player, vec, hand);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (this.canHoldItem(player.getMainHandItem()) && !isCrafting) {
                                            ItemStack stack = this.getItemBySlot(EquipmentSlot.MAINHAND);
                                            this.setItemSlot(EquipmentSlot.MAINHAND, player.getMainHandItem());
                                            player.setItemSlot(EquipmentSlot.MAINHAND, stack);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
        return super.interactAt(player, vec, hand);
    }


    public ArrayList<ItemStack> getArmor() {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        if (!this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            arrayList.add(this.getItemBySlot(EquipmentSlot.HEAD));
        }
        if (!this.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            arrayList.add(this.getItemBySlot(EquipmentSlot.CHEST));
            System.out.println("add chestplate");
        }
        if (!this.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            arrayList.add(this.getItemBySlot(EquipmentSlot.LEGS));
        }
        if (!this.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
            arrayList.add(this.getItemBySlot(EquipmentSlot.FEET));
        }
        System.out.println(arrayList);
        return arrayList;
    }


    /*
    0 is stay still
    1 is wander
    2 is follow
    3 is follow assigned
     */

    public abstract int generateHardness();

    public String getAssignedUtil() {
        StringBuilder builder = new StringBuilder();
        builder.append(getAssignedName());
        builder.append(",");
        builder.append(getAssignedFacet());
        builder.append(",");
        builder.append(getAssignedCut());
        return builder.toString();
    }

    public void setAssignedUtil(String name, String facet, String cut) {
        setAssignedName(name);
        setAssignedFacet(facet);
        setAssignedCut(cut);
    }

    public EntityGem getAssignedGem() {
        if (!this.level().isClientSide) {
            if (((ServerLevel)this.level()).getEntity(ASSIGNED_ID) instanceof EntityGem) {
                return (EntityGem) ((ServerLevel) this.level()).getEntity(ASSIGNED_ID);
            } else {
                System.out.println("not a gem");
                return null;
            }
        }
        return null;
    }

    public void setAssignedGem(EntityGem assigned) {
        if (assigned != null) {
            this.ASSIGNED_ID = assigned.getUUID();
        }
    }

    public void cycleMovementAI(Player player){
        if (!this.getRebelled() && !(this.getSludgeAmount() >= 5)) {
            //Cycles through the various movement types.
            this.navigation.stop();
            setFollow(player.getUUID());
            System.out.println("Assigned ID " + ASSIGNED_ID);
            System.out.println("assigned  " + getAssignedGem());
            this.GUARD_POS = this.getOnPos().above();
            if (getAssignedGem() != null ? this.getMovementType() < 3 : this.getMovementType() < 2) {
                this.addMovementType(1);
                switch (this.getMovementType()) {
                    case 1 -> player.sendSystemMessage(Component.translatable(this.getName().getString() + " will wander around"));
                    case 2 -> player.sendSystemMessage(Component.translatable(this.getName().getString() + " will now follow you"));
                    case 3 -> player.sendSystemMessage(Component.translatable(this.getName().getString() + " will now follow " + getAssignedGem().getName().getString() + " " + getAssignedGem().getFacetAndCut()));
                    default -> player.sendSystemMessage(Component.translatable(this.getName().getString() + " will now stay put"));
                }
            } else if (getAssignedGem() != null ? this.getMovementType() == 3 : this.getMovementType() == 2) {
                this.setMovementType((byte) 0);
                player.sendSystemMessage(Component.translatable(this.getName().getString() + " will now stay put"));
            } else if (getAssignedGem() == null && this.getMovementType() == 3 || !getAssignedGem().isAlive()) {
                this.setMovementType((byte) 0);
                player.sendSystemMessage(Component.translatable(this.getName().getString() + " will now stay put"));
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount){
        if (!this.level().isClientSide) {
            if (source.getEntity() instanceof LivingEntity && !(source.getEntity() instanceof Player)) {
                if (((LivingEntity) source.getEntity()).getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof DestabBase) {
                    this.hurt(this.damageSources().magic(), 20*getMaxHealth());
                    if (((LivingEntity) source.getEntity()).getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ItemBlueRejuvenator) {
                        this.setAbilities(this.generateAbilities());
                        if (this instanceof EntityZircon)
                        {
                            if (this.isPrimary()) {
                                ((EntityZircon) this).setEnchantPage(RandomSource.create().nextInt(ModEnchants.GEMPIRE_ENCHANTMENTS.size()));
                            } else {
                                ((EntityZircon) this).setEnchantPage(RandomSource.create().nextInt(ModEnchants.VANILLA_ENCHANTMENTS.size()));
                            }
                        }
                        else if (this instanceof EntityTourmaline) {
                            ((EntityTourmaline) this).setCrops(((EntityTourmaline) this).generateCrops());
                        }
                        else if (this instanceof EntityPeridot) {
                            ((EntityPeridot) this).generateMaterials();
                        }
                        this.rebelPoints += 2.5f;
                    } else if (((LivingEntity) source.getEntity()).getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ItemPinkRejuvenator) {
                        this.resetOwners();
                        this.setRebelled(false);
                        this.rebelPoints = 1F;
                    } else if (((LivingEntity) source.getEntity()).getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ItemYellowRejuvenator) {
                        if (!this.getRebelled()) {
                            this.setHairVariant(this.generateHairVariant());
                            this.setOutfitVariant(this.generateOutfitVariant());
                            this.setInsigniaVariant(this.generateInsigniaVariant());
                            this.rebelPoints += 0.5F;
                        }
                    } else if (((LivingEntity) source.getEntity()).getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ItemWhiteRejuvenator) {
                        this.setSkinColor(this.generatePaletteColor(PaletteType.SKIN));
                        this.setHairColor(this.generatePaletteColor(PaletteType.HAIR));
                        this.setGemColor(this.generatePaletteColor(PaletteType.GEM));
                        if (this.hasMarkings())
                        {
                            this.setMarking2Color(this.generatePaletteColor(PaletteType.MARKINGS_2));
                        }
                        if (this.hasMarkings())
                        {
                            this.setMarkingColor(this.generatePaletteColor(PaletteType.MARKINGS));
                        }
                        this.rebelPoints += 0.5F;
                    }
                    return super.hurt(source, amount);
                } else if (((LivingEntity) source.getEntity()).getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ItemShatterer) {
                    this.setShatter(true);
                    this.hurt(this.damageSources().magic(), 20*getMaxHealth());
                    for (UUID owner : OWNERS) {
                        Objects.requireNonNull(this.level().getPlayerByUUID(owner)).sendSystemMessage(Component.translatable(this.getName().getString() + " " + this.getFacetAndCut() + " has been shattered"));
                    }
                    return super.hurt(source, amount);
                }
            }
            float hardness = getHardness();
            System.out.println(this.getArmor().size());
            for (int i = 0; i < this.getArmor().size(); i++) {
                ItemStack armorItem = this.getArmor().get(i);
                System.out.println(armorItem.getItem());
                if (armorItem.isEnchanted()) {
                    if (armorItem.getEnchantmentLevel(ModEnchants.GEM_PROTECTION.get()) >= 1) {
                        if (armorItem.getItem() instanceof ArmorItem) {
                            EquipmentSlot slot = ((ArmorItem) armorItem.getItem()).getEquipmentSlot();
                            float level = armorItem.getEnchantmentLevel(ModEnchants.GEM_PROTECTION.get());
                            if (slot == EquipmentSlot.HEAD || slot == EquipmentSlot.FEET) {
                                hardness += level / 2;
                                System.out.println("head and feet");
                            } else if (slot == EquipmentSlot.LEGS) {
                                hardness += level;
                                System.out.println("legs");
                            } else if (slot == EquipmentSlot.CHEST) {
                                hardness += level * 1.5;
                                System.out.println("chest");
                            }
                        }
                    }
                }
            }
            if (!source.is(DamageTypes.MAGIC)) {
                if (source.is(DamageTypeTags.IS_EXPLOSION)) {
                    if (amount - getHealth() > ((hardness / 2) + 0.5) && (this.random.nextInt(shatterChance / 2) == 1)) {
                        setShatter(true);
                    }
                } else if (!(source.getEntity() instanceof Player)) {
                    if ((amount - getHealth()) > hardness) {
                        if ((this.random.nextInt(shatterChance) == 1) || getCrackAmount() == (getHardness() * 10)) {
                            setShatter(true);
                        } else if (this.random.nextInt(crackChance) == 1) {
                            setCracked(true);
                            shatterChance--;
                            setCrackAmount(getCrackAmount() * 2);
                            if (this.random.nextInt(10) == 10) {
                                crackChance--;
                            }
                        }
                    }
                } else if (source.getEntity() instanceof Player) {
                    Player player = (Player) source.getEntity();
                    if (isOwner(player.getUUID())) {
                        if (!this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                            ItemStack stack = this.getItemBySlot(EquipmentSlot.MAINHAND);
                            Objects.requireNonNull(this.spawnAtLocation(stack)).setExtendedLifetime();
                            this.setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                        }
                        if (!this.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
                            ItemStack stack = this.getItemBySlot(EquipmentSlot.OFFHAND);
                            Objects.requireNonNull(this.spawnAtLocation(stack)).setExtendedLifetime();
                            this.setItemSlot(EquipmentSlot.OFFHAND, Items.AIR.getDefaultInstance());
                        }
                    }
                    if (!(player.getMainHandItem().getItem() instanceof DestabBase)) {
                        if ((amount - getHealth()) > hardness) {
                            if ((this.random.nextInt(shatterChance) == 1)) {
                                setShatter(true);
                            } /*else if (getCrackAmount() == (getHardness() * 10)) {
                                setShatter(true);
                            }*/ else if (this.random.nextInt(crackChance) == 1) {
                                setCracked(true);
                                shatterChance--;
                                setCrackAmount(getCrackAmount() * 2);
                                if (this.random.nextInt(10) == 10) {
                                    crackChance--;
                                }
                            }
                        }
                    }
                }
            }
                if (this.isEmotional() && !source.is(DamageTypeTags.IS_EXPLOSION) && !source.is(DamageTypeTags.IS_FIRE) && GempireServerConfigs.OUTBURSTS.get()) {
                    if (this.emotionMeter <= this.EmotionThreshold()) {
                        if (this.EmotionThreshold() - this.emotionMeter < 5) {
                            this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getX(), this.getY() + 2, this.getZ(), 0, 0, 0);
                        }
                        this.emotionMeter++;
                    } else {
                        for (Ability power : this.getAbilityPowers()) {
                            if (power instanceof IEmotionalAbility) {
                                ((IEmotionalAbility) power).outburst();
                            }

                        }
                        this.meltdown = true;
                        this.meltdownCooldown = 100;
                        this.emotionMeter = 0;
                    }
                }
                if (source.getEntity() instanceof EntityCrawler || source.getEntity() instanceof EntityShambler || source.getEntity() instanceof EntityAbomination) {
                    setSludgeAmount(getSludgeAmount() + 1);
                }
        }
        return super.hurt(source, amount);
    }

    public void dropXP(LivingEntity entityIn) {
        if (!entityIn.level().isClientSide) {
            if (entityIn.level() instanceof ServerLevel) {
                int reward = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(entityIn, this.currentPlayer, entityIn.getExperienceReward());
                ExperienceOrb.award((ServerLevel) entityIn.level(), entityIn.position(), reward);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if(!entityIn.level().isClientSide){
            if (entityIn instanceof LivingEntity) {
                if (!enemyDying) {
                    enemyDying = true;
                    enemy = (LivingEntity) entityIn;
                }
                if(this.focusCheck()) for(Ability power : this.getAbilityPowers()){
                    if(power instanceof IMeleeAbility) {
                        ((IMeleeAbility)power).fight((LivingEntity) entityIn, this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    }
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
        if (this.isArcher()) {
            ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
            AbstractArrow abstractarrow = this.getArrow(itemstack, distanceFactor);
            if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem)
                abstractarrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrow);
            double d0 = target.getX() - this.getX();
            double d1 = target.getY(0.3333333333333333D) - abstractarrow.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            abstractarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
            //this.playSound(SoundEvents.SKELETO_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.level().addFreshEntity(abstractarrow);
        }
    }

    @Override
    public void die(DamageSource source){
        //When the Gem dies.
        /*float f = (this.random.nextFloat() - 0.5F) * 8.0F;
        float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
        float f2 = (this.random.nextFloat() - 0.5F) * 8.0F; these dont do anything*/
        this.level().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() , this.getY() + 2.0D, this.getZ(), 0.0D, 0.0D, 0.0D);
        if(!this.level().isClientSide){
            System.out.println("not clientside");
            this.playSound(ModSounds.POOF.get());
            GemPoofEvent event = new GemPoofEvent(this, this.blockPosition(), source);
            MinecraftForge.EVENT_BUS.post(event);
            if (!this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                ItemStack stack = this.getItemBySlot(EquipmentSlot.MAINHAND);
                Objects.requireNonNull(this.spawnAtLocation(stack)).setExtendedLifetime();
                this.setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
            }
            if (!this.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
                ItemStack stack = this.getItemBySlot(EquipmentSlot.OFFHAND);
                Objects.requireNonNull(this.spawnAtLocation(stack)).setExtendedLifetime();
                this.setItemSlot(EquipmentSlot.OFFHAND, Items.AIR.getDefaultInstance());
            }
            if (getShatter()){
                ItemStack stack = new ItemStack(this.getShardItem());
                Objects.requireNonNull(this.spawnAtLocation(stack)).setExtendedLifetime();
                for (UUID owner : OWNERS) {
                    Objects.requireNonNull(this.level().getPlayerByUUID(owner)).sendSystemMessage(Component.translatable(this.getName().getString() + " " + this.getFacetAndCut() + " has been shattered"));
                }
            } else if (getCracked()){
                ItemStack stack = new ItemStack(this.getGemItem());
                ItemGem.saveData(stack, this);
                Objects.requireNonNull(this.spawnAtLocation(stack)).setExtendedLifetime();
                for (UUID owner : OWNERS) {
                    Objects.requireNonNull(this.level().getPlayerByUUID(owner)).sendSystemMessage(Component.translatable(this.getName().getString() + " " + this.getFacetAndCut() + " has cracked"));
                }
            } else {
                ItemStack stack = new ItemStack(this.getGemItem());
                //ItemGem.saveData(stack, this);
                if (stack.getItem() != ModItems.PEBBLE_GEM.get() &&
                        stack.getItem() != ModItems.MICA_GEM.get() &&
                        stack.getItem() != ModItems.NACRE_GEM.get() &&
                        stack.getItem() != ModItems.SHALE_GEM.get()) {
                    this.getAttribute(Attributes.MAX_HEALTH).removeModifiers();
                    this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifiers();
                    this.getAttribute(Attributes.ATTACK_DAMAGE).removeModifiers();
                    this.getAttribute(Attributes.ATTACK_SPEED).removeModifiers();
                    this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifiers();
                }
                CompoundTag tag = new CompoundTag();
                save(tag);
                stack.setTag(tag);
                this.spawnAtLocation(stack).setExtendedLifetime();
            }
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.kill();
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
        RegistryObject<Item> gemm;
        ItemGem gem = null;
        String name;
        if(this instanceof EntityVaryingGem && !(this instanceof EntitySapphire)){
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

    public Item getShardItem() {
        RegistryObject<Item> shardd;
        Item shard = null;
        String name;
        if (getColor() <= 15) {
            name = Color.getColorName(getColor()) + "_shards";
        } else {
            name = "special_shards";
        }
        try {
            shardd = (RegistryObject<Item>) this.getItemRegister().getField(name.toUpperCase()).get(null);
            shard = shardd.get();
        } catch(Exception e){
            e.printStackTrace();
        }
        return shard;
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

    public boolean hasWings() {
        return false;
    }

    public boolean flocksTo(EntityGem gem) {
        return false;
    }

    public abstract int getColor();
    @Override
    public boolean removeWhenFarAway(double xix){
        return false;
    }

    //GETTERS AND SETTERS!!!

    public void setOwners(ArrayList<UUID> owners){
        this.OWNERS = owners;
    }

    public void addOwner(UUID ID){
        if (!getRebelled()) {
            this.OWNERS.add(ID);
        }
    }
    public void addMasterOwner(UUID ID)
    {
        MASTER_OWNER = ID;
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
        addMasterOwner(UUID.randomUUID());
    }

    public boolean getOwned(){
        return !this.OWNERS.isEmpty();
    }

    /*public UUID getOwnerID(){
        return this.dataManager.get(EntityGem.OWNER_ID).get();
    }*/

    public boolean isOwner(LivingEntity entity){
        for(UUID uuid : this.OWNERS){
            if(entity.getUUID().equals(uuid)){
                return true;
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
    public void popShitOut() {
        setRecipeAmount(generateRecipeAmount());
            ItemStack itemStack = new ItemStack(getOutputItem(getCurrentRecipe()));
            this.setItemSlot(EquipmentSlot.MAINHAND, itemStack);
            ticking = 0;
            this.playSound(getInstrument(), this.getSoundVolume(), (interactPitch()));
            isCrafting = false;
            setCurrentRecipe(0);
    }

    public int generatePaletteColor(PaletteType type) {
        if (type == PaletteType.MARKINGS) {
            if (!hasMarkings()) {
                return 0;
            }
        }
        if (type == PaletteType.MARKINGS_2) {
            if (!hasMarkings2()) {
                return 0;
            }
        }
        if (type == PaletteType.WING) {
            if (!hasWings()) {
                return 0;
            }
        }
        String locString = type.type + "_palette";
        System.out.println("[DEBUG] " + locString);
        ArrayList<Integer> colors = new ArrayList<>();
        ResourceLocation serverLoc = new ResourceLocation(this.getModID() + ":entity/" + this.getWholeGemName().toLowerCase() + "/palettes/" + locString + ".png");
        BufferedImage palette = null;
        try {
            palette = ImageIO.read(this.getServer().getServerResources().resourceManager().open(serverLoc));
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

    public Boolean getAssigned(){
        return this.entityData.get(EntityGem.ASSIGNED);
    }

    public void setAssigned(boolean value){
        this.entityData.set(EntityGem.ASSIGNED, value);
    }

    public Boolean getShatter(){
        return this.entityData.get(EntityGem.SHATTER);
    }

    public void setShatter(boolean value){
        this.entityData.set(EntityGem.SHATTER, value);
    }


    public Boolean getLumberjack(){
        return this.entityData.get(EntityGem.LUMBERJACK);
    }

    public void setLumberjack(boolean value){
        this.entityData.set(EntityGem.LUMBERJACK, value);
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

    public int getWingColor(){
        return this.entityData.get(EntityGem.WING_COLOR);
    }

    public void setWingColor(int value){
        this.entityData.set(EntityGem.WING_COLOR, value);
    }

    public int getWingVariant(){
        return this.entityData.get(EntityGem.WING_VARIANT);
    }

    public void setWingVariant(int value){
        this.entityData.set(EntityGem.WING_VARIANT, value);
    }

    public int generateWingVariant() {
        return 0;
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

    public abstract int generateHairVariant();

    public abstract int exitHoleSize();

    public int getRebelHairVariant(){
        return this.entityData.get(EntityGem.REBEL_HAIR_VARIANT);
    }

    public void setRebelHairVariant(int value){
        this.entityData.set(EntityGem.REBEL_HAIR_VARIANT, value);
    }

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
    public int getRebelOutfitColor(){
        return this.entityData.get(EntityGem.REBEL_OUTFIT_COLOR);
    }

    public void setRebelOutfitColor(int value){
        this.entityData.set(EntityGem.REBEL_OUTFIT_COLOR, value);
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
    public abstract int generateRebelInsigniaVariant();

    public void setInsigniaVariant(int value){
        this.entityData.set(EntityGem.INSIGNIA_VARIANT, value);
    }

    public int getInsigniaVariant(){
        return this.entityData.get(EntityGem.INSIGNIA_VARIANT);
    }

    public int getInsigniaColor(){
        return this.entityData.get(EntityGem.INSIGNIA_COLOR);
    }

    public void setInsigniaColor(int value){
        this.entityData.set(EntityGem.INSIGNIA_COLOR, value);
    }

    public void setRebelInsigniaVariant(int value){
        this.entityData.set(EntityGem.REBEL_INSIGNIA_VARIANT, value);
    }

    public int getRebelInsigniaVariant(){
        return this.entityData.get(EntityGem.REBEL_INSIGNIA_VARIANT);
    }

    public int getRebelInsigniaColor(){
        return this.entityData.get(EntityGem.REBEL_INSIGNIA_COLOR);
    }

    public void setRebelInsigniaColor(int value){
        this.entityData.set(EntityGem.REBEL_INSIGNIA_COLOR, value);
    }

    public int generateInsigniaColor(){
        return this.getSkinColorVariant();
    }

    public abstract int generateVisorVariant();

    public void setVisorVariant(int value){
        this.entityData.set(EntityGem.VISOR_VARIANT, value);
    }

    public int getVisorVariant(){
        return this.entityData.get(EntityGem.VISOR_VARIANT);
    }

    public void setRebelVisorVariant(int value){
        this.entityData.set(EntityGem.REBEL_VISOR_VARIANT, value);
    }

    public int getRebelVisorVariant(){
        return this.entityData.get(EntityGem.REBEL_VISOR_VARIANT);
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

    public void setQuality(int value){
        this.entityData.set(EntityGem.QUALITY, value);
    }

    public int getQuality(){
        return this.entityData.get(EntityGem.QUALITY);
    }

    public boolean isPrimary(){
        return this.entityData.get(EntityGem.QUALITY) == 2;
    }

    public boolean isDefective(){
        return this.entityData.get(EntityGem.QUALITY) == 0;
    }

    public abstract boolean canChangeUniformColorByDefault();

    public abstract boolean canChangeInsigniaColorByDefault();
    public Boolean getCracked(){
        return this.entityData.get(EntityGem.CRACKED);
    }

    public void setCracked(boolean cracked){
        this.entityData.set(EntityGem.CRACKED, cracked);
    }

    public void setHardness(int value){
        this.entityData.set(EntityGem.HARDNESS, value);
    }

    public int getHardness(){
        return this.entityData.get(EntityGem.HARDNESS);
    }

    public void setCrackAmount(int value){
        this.entityData.set(EntityGem.CRACK_AMOUNT, value);
    }

    public int getCrackAmount(){
        return this.entityData.get(EntityGem.CRACK_AMOUNT);
    }

    public void setSludgeAmount(int value){
        this.entityData.set(EntityGem.SLUDGE_AMOUNT, value);
    }

    public int getSludgeAmount(){
        return this.entityData.get(EntityGem.SLUDGE_AMOUNT);
    }

    public Boolean getRebelled(){
        return this.entityData.get(EntityGem.REBEL);
    }
    public Boolean getHostile(){
        return isHostile;
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
        return Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(this.getType())).toString().replaceAll("(?i)item", "").replaceAll(this.getModID(), "").replaceAll("gempire", "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }

    public String getWholeGemName(){
        return Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(this.getType())).toString().replaceAll("(?i)item", "").replaceAll(this.getModID(), "").replaceAll("gempire", "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
    }

    //ABILITY STUFF

    public ArrayList<Ability> findAbilities(String getab){
        ArrayList<Ability> powers = new ArrayList<>();
        if(!getab.isEmpty()) {
            String[] powerViolenceList = getab.split(",");
            for (String s : powerViolenceList) {
                Ability ability = ModAbilities.getAbility(s);
                if (ability != null) {
                    ability.assignAbility(this);
                    powers.add(ability);
                    if ((ability instanceof IEffectAbility || ability instanceof IAreaAbility) && !(ability instanceof IViolentAbility)) {
                        this.entityData.set(EntityGem.USES_AREA_ABILITIES, true);
                    }
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
                } else {
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
            return 5;
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
        ArrayList<Ability> abilitiesCurrent = new ArrayList<>();
        StringBuilder abilityList = new StringBuilder();
        ArrayList<Ability> abilities = this.possibleAbilities();
        ArrayList<Ability> getgo = this.definiteAbilities();
        if(getgo != null && getgo.size() > 0) {
            for (Ability ab1 : getgo) {
                if (remainingSlots > 0) {
                    if (remainingSlots == this.getAbilitySlots()) {
                        abilityList.append(ab1.getId()).append(",");
                        abilitiesCurrent.add(ab1);
                        remainingSlots--;
                    } else {
                        for (int n = 0; n < abilitiesCurrent.size(); n++) {
                            if (ab1.getId() != abilitiesCurrent.get(n).getId()) {
                                abilityList.append(ab1.getId()).append(",");
                                abilitiesCurrent.add(ab1);
                                remainingSlots--;
                            }
                        }
                    }
                } else {
                    return abilityList.toString();
                }
            }
        }
        while(!complete){
            double totalWeight = 0.0D;
            for(Ability i : abilities){
                totalWeight+=i.getWeight();
            }
            int idx = 0;
            for (double r = Math.random() * totalWeight; idx < abilities.size() - 1; idx++) {
                r -= abilities.get(idx).getWeight();
                if (r <= 0) break;
            }
            Ability weightedAbility = abilities.get(idx);
            abilityList.append(weightedAbility.getId()).append(",");
            if(this.possibleAbilities().size() + this.definiteAbilities().size() > this.getAbilitySlots()) abilities.remove(idx);
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

    public abstract ArrayList<Ability> possibleAbilities();
    public abstract ArrayList<Ability> definiteAbilities();

    public boolean usesAreaAbilities(){
        return this.entityData.get(EntityGem.USES_AREA_ABILITIES);
    }

    public void setUsesAreaAbilities(boolean bool){
        this.entityData.set(EntityGem.USES_AREA_ABILITIES, bool);
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

    protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
        return ProjectileUtil.getMobArrow(this, p_32156_, p_32157_);
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_32144_) {
        if (this.isArcher()) {
            return p_32144_ == Items.BOW;
        } else {
            return false;
        }
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
            if (ability instanceof AbilityVehicle) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean canWalkOnFluids(){
        return false;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if(canBeControlledByRider() && isVehicle()){
            LivingEntity passenger = getControllingPassenger();
            this.yRotO = getYRot();
            this.xRotO = getXRot();

            setYRot(passenger.getYRot());
            setXRot(passenger.getXRot() * 0.5f);
            setRot(getYRot(), getXRot());

            if (getStepHeight() < 1) {
                AttributeModifier HEIGHT = new AttributeModifier(UUID.randomUUID(), "gempirePrimaryModifier", 0.4D, AttributeModifier.Operation.ADDITION);
                this.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).addPermanentModifier(HEIGHT);
            }

            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;
            float x = passenger.xxa * 0.5F;
            float z = passenger.zza;
            //float y = passenger.yya;

            double speed = this.getControllingPassenger().zza;
            double speedSwim = this.isSwimming() ? 10 : 1;

            if (z <= 0)
                z *= 0.25f;

            double y = travelVector.y;
            Vec3 vector = new Vec3(x, y, z);
            if (this.horizontalCollision) {
                vector.add(0, 16, 0);
                //y += 100000;
            }
            System.out.println(vector);

            this.setSpeed(0.3f);
            super.travel(vector.multiply(speed,speed,speed).multiply(speedSwim, speedSwim, speedSwim));
        } else {
            super.travel(travelVector);
        }
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof LivingEntity) {
            return (LivingEntity)entity;
        }
        return null;
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        return entity instanceof Player;
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
                if (iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)
                        || iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                    this.setOnGround(true);
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

    public void addToInventory(ArrayList<ItemStack> list) {
        if (list != null) {
            for (ItemStack stack : list) {
                for (int i = 0; i < getItems().size(); i++) {
                    if (getItems().get(i).is(stack.getItem()) &&
                            getItems().get(i).getCount() < 64) {
                        getItems().get(i).setCount(getItems().get(i).getCount() + 1);
                        break;
                    }
                    if (getItems().get(i).is(Items.AIR)) {
                        NonNullList<ItemStack> array = getItems();
                        array.set(i, stack);
                        setItems(array);
                        break;
                    }
                }
            }
        }
    }

    public void addToInventoryList(List<ItemStack> list) {
        if (list != null) {
            for (ItemStack stack : list) {
                for (int i = 0; i < getItems().size(); i++) {
                    if (getItems().get(i).is(stack.getItem()) &&
                            getItems().get(i).getCount() < 64) {
                        getItems().get(i).setCount(getItems().get(i).getCount() + 1);
                        break;
                    }
                    if (getItems().get(i).is(Items.AIR)) {
                        NonNullList<ItemStack> array = getItems();
                        array.set(i, stack);
                        setItems(array);
                        break;
                    }
                }
            }
        }
    }

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

    public void fuse(EntityGem other) {
        System.out.println("fusion attempt");
        EntityFusion fusion = new EntityFusion(ModEntities.FUSION.get(), level());
        fusion.combineNBT(this.getPersistentData(), other.getPersistentData());
        fusion.setPos(this.position());
        fusion.setHealth(fusion.getMaxHealth());
        for (UUID owner : OWNERS) {
            level().getPlayerByUUID(owner).sendSystemMessage(Component.literal(this.getName() + " and " + other.getName() + " fused to make " + fusion.getName()));
        }
        for (UUID owner : other.OWNERS) {
            level().getPlayerByUUID(owner).sendSystemMessage(Component.literal(this.getName() + " and " + other.getName() + " fused to make " + fusion.getName()));
        }
        other.remove(RemovalReason.DISCARDED);
        remove(RemovalReason.DISCARDED);
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

    public boolean consumeItemCheck(Item item, int amount){
        for(int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 4; i++){
            if(this.getItem(i).getItem() == item) {
                if (this.getItem(i).getCount() >= amount) {
                    if (this.getItem(i).getCount() == amount) {
                        this.setItem(i, ItemStack.EMPTY);
                        return true;
                    } else {
                        this.setItem(i, new ItemStack(this.getItem(i).getItem(),
                                this.getItem(i).getCount() - amount));
                        //this.getItem(i).shrink(1);
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean isPopular() {
        return false;
    }

    public boolean requiresHydrationToFly() {
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

    public boolean itemCheckWithCount(Item item, int count){
        System.out.println("check with count");
        for(int i = 0; i < EntityGem.NUMBER_OF_SLOTS - 4; i++){
            if(this.getItem(i).getItem() == item && this.getItem(i).getCount() >= count){
                System.out.println("check passed");
                return true;
            }
        }
        return false;
    }

    public boolean canRecall(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityRecall){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean isArcher(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityArcher){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean isLumberjack(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityLumberjack){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean isTinkerer(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityTinkerer){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean isTorchBearer(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityTorchBearer){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean isFarmer(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityFarmer){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean canLure(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityLure){
                return flag = true;
            }
        }
        return flag;
    }

    public boolean doesIntimidate(){
        boolean flag = false;
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityIntimidation){
                return flag = true;
            }
        }
        return flag;
    }

    public void announceCrops() {
        if (isFarmer()) {
            for (UUID owner : OWNERS) {
                Player player = level().getPlayerByUUID(owner);
                String string = ((EntityTourmaline) this).getCrops();
                String[] strings = string.split(",");
                StringBuilder stringBuilder = new StringBuilder("This gem can farm: ");
                for (int i = 0; i < strings.length; i++) {
                    String string1 = strings[i];
                    String s1 = string1.substring(0, 1).toUpperCase();
                    string1 = s1 + string1.substring(1);
                    stringBuilder.append(string1);
                    if (i < strings.length - 1) {
                        stringBuilder.append(", ");
                    }
                }
                assert player != null;
                player.sendSystemMessage(Component.translatable(String.valueOf(stringBuilder)));
            }
        }
    }

    /*
    COMMAND STUFF
    */

    public static void decreaseExp(Player player, float amount, ItemStack stack, boolean isEnchant) {
        //if (isEnchant) player.onEnchantmentPerformed(stack, (int) amount);
        //else {
            if (player.totalExperience - amount <= 0) {
                player.experienceLevel = 0;
                player.experienceProgress = 0;
                player.totalExperience = 0;
                return;
            }
            player.totalExperience -= amount;
            if (player.experienceProgress * (float) player.getXpNeededForNextLevel() <= amount) {
                amount -= player.experienceProgress * (float) player.getXpNeededForNextLevel();
                player.experienceProgress = 1.0f;
                player.experienceLevel--;
            }
            while (player.getXpNeededForNextLevel() < amount) {
                amount -= player.getXpNeededForNextLevel();
                player.experienceLevel--;
            }
            player.experienceProgress -= amount / (float) player.getXpNeededForNextLevel();
        /*if (!player.level().isClientSide) {
            System.out.println(amount);
            player.giveExperienceLevels((int) -amount);
            if (player.experienceLevel < 0) {
                player.experienceLevel = 0;
                player.experienceProgress = 0.0F;
                player.totalExperience = 0;
            }
        }*/
        //}
    }

    public void runRecallCommand(ServerPlayer player) {
        if(this.consumeItemCheck(Items.ENDER_EYE, 1)) {
            int x,y,z;
            if  (player.getRespawnPosition() != null) {
                x = player.getRespawnPosition().getX();
                y = player.getRespawnPosition().getY();
                z = player.getRespawnPosition().getZ();
            } else {
                x = player.level().getSharedSpawnPos().getX();
                y = player.level().getSharedSpawnPos().getX();
                z = player.level().getSharedSpawnPos().getX();
            }
            if (player.isCreative() ? player.experienceLevel >= 0 : player.experienceLevel >= 20) {
                    player.teleportTo(x, y, z);
                    this.teleportTo(x, y, z);
                    player.sendSystemMessage(Component.translatable("commands.gempire.recall"));
                    decreaseExp(player, 20, null, false);
            } else {
                player.sendSystemMessage(Component.translatable(  "messages.gempire.entity.player_need_xp"));
            }
        }
        else{
            player.sendSystemMessage(Component.translatable("commands.gempire.noeye"));
        }
    }

    public void runFarmCommand(ServerPlayer player) {
        if(this instanceof EntityTourmaline) {
            System.out.println("is tourmaline");
            if (this.itemCheckWithCount(Items.DIRT, 64) && this.itemCheck(Items.WATER_BUCKET)
                    && this.itemCheck(Items.WATER_BUCKET) && this.itemCheckWithCount(Items.OAK_LOG, 4)
                    && this.itemCheckWithCount(Items.OAK_PLANKS, 56) && this.getItemBySlot(EquipmentSlot.MAINHAND).is(ItemTags.HOES))
                ((EntityTourmaline) this).setBuilding(true);
        } else {
            player.sendSystemMessage(Component.translatable("commands.gempire.nofarmresources"));
        }
    }

    public void runTerraformCommand(ServerPlayer player) {
        if(this instanceof EntityLapis) {
            ((EntityLapis) this).setTerraforming(true);
        }
    }

    public void runLumberjackCommand(ServerPlayer player) {
        if(isLumberjack()) {
            System.out.println("is lumberjack");
            if (this.getItemBySlot(EquipmentSlot.MAINHAND).is(ItemTags.AXES))
                setLumberjack(true);
        } else {
            player.sendSystemMessage(Component.translatable("commands.gempire.noaxe"));
        }
    }

    public void rebel() {
        this.resetOwners();
        this.setRebelled(true);
    }

    public static BlockPos findStructure(EntityGem gem, Feature<?> structure) {
        if(gem.level().isClientSide){
            return BlockPos.ZERO;
        }
        BlockPos blockpos1 = null;
        BlockPos blockpos = new BlockPos(gem.blockPosition());
        if(gem.structures.contains(structure.toString().replace("minecraft:", ""))) {
             blockpos1 = ((ServerLevel) gem.getCommandSenderWorld()).findNearestMapStructure(StructureTags.VILLAGE, blockpos, 100, false);
        }
        if (blockpos1 == null) {
            return BlockPos.ZERO;
        } else {
            return blockpos1;
        }
    }

    public void runFindCommand(CommandSourceStack stack, ServerPlayer player, ResourceOrTagKeyArgument.Result<Structure> structure, @Nullable ResourceOrTagKeyArgument.Result<Biome> biomeResource, boolean biome) throws CommandSyntaxException {
        if (biome) {
            System.out.println("find command");
            BlockPos blockpos = new BlockPos((int) stack.getPosition().x, (int) stack.getPosition().y, (int) stack.getPosition().z);
            Pair<BlockPos, Holder<Biome>> pair = stack.getLevel().findClosestBiome3d(biomeResource, blockpos, 6400, 32, 64);
            if (pair == null) {
                throw ERROR_BIOME_INVALID.create(biomeResource.asPrintable());
            } else {
                System.out.println(biomeResource.asPrintable());
                if(this.consumeItemCheck(Items.MAP, 1)) {
                    boolean done = false;
                    ItemStack map = MapItem.create(this.level(), pair.getFirst().getX(), pair.getFirst().getZ(), (byte) 0, true, true);
                    MapItemSavedData.addTargetDecoration(map, pair.getFirst(), "location", MapDecoration.Type.RED_X);
                    String name = biomeResource.asPrintable().replaceAll("minecraft:", "").replaceAll("_", " ").replaceAll("#", "").replaceAll("forge:", "").replaceAll(":", " ");
                    String[] name1 = name.split(" ");
                    StringBuilder name2 = new StringBuilder();
                    for (String s : name1) {
                        String s1 = s.substring(0, 1).toUpperCase();
                        String s2 = s1 + s.substring(1);
                        name2.append(s2).append(" ");
                        System.out.println(name2);
                        System.out.println(s2);
                    }
                    name = String.valueOf(name2);
                    map.setHoverName(Component.translatable(name).withStyle(ChatFormatting.GOLD));
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
                    player.sendSystemMessage(Component.translatable("commands.gempire.foundit"));
                }
                else{
                    player.sendSystemMessage(Component.translatable("commands.gempire.nomap"));
                }
            }
        } else {
            Registry<Structure> registry = stack.getLevel().registryAccess().registryOrThrow(Registries.STRUCTURE);
            HolderSet<Structure> holderset = getHolders(structure, registry).orElseThrow(() -> {
                return ERROR_STRUCTURE_INVALID.create(structure.asPrintable());
            });
            BlockPos blockpos = new BlockPos((int) stack.getPosition().x, (int) stack.getPosition().y, (int) stack.getPosition().z);
            ServerLevel serverlevel = player.serverLevel();
            Pair<BlockPos, Holder<Structure>> pair = serverlevel.getChunkSource().getGenerator().findNearestMapStructure(serverlevel, holderset, blockpos, 100, false);
            if (pair == null) {
                throw ERROR_STRUCTURE_INVALID.create(structure.asPrintable());
            } else {
                System.out.println(structure.asPrintable());
                if(this.consumeItemCheck(Items.MAP, 1)) {
                    boolean done = false;
                    ItemStack map = MapItem.create(this.level(), pair.getFirst().getX(), pair.getFirst().getZ(), (byte) 0, true, true);
                    MapItemSavedData.addTargetDecoration(map, pair.getFirst(), "location", MapDecoration.Type.RED_X);
                    String name = structure.asPrintable().replaceAll("minecraft:", "").replaceAll("_", " ").replaceAll("#", "").replaceAll("forge:", "").replaceAll(":", " ");
                    String[] name1 = name.split(" ");
                    StringBuilder name2 = new StringBuilder();
                    for (String s : name1) {
                        String s1 = s.substring(0, 1).toUpperCase();
                        String s2 = s1 + s.substring(1);
                        name2.append(s2).append(" ");
                        System.out.println(name2);
                        System.out.println(s2);
                    }
                    name = String.valueOf(name2);
                    map.setHoverName(Component.translatable(name).withStyle(ChatFormatting.GOLD));
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
                    player.sendSystemMessage(Component.translatable("commands.gempire.foundit"));
                }
                else{
                    player.sendSystemMessage(Component.translatable("commands.gempire.nomap"));
                }
            }
        }
    }

    private static Optional<? extends HolderSet.ListBacked<Structure>> getHolders(ResourceOrTagKeyArgument.Result<Structure> p_214484_, Registry<Structure> p_214485_) {
        return p_214484_.unwrap().map((p_214494_) -> {
            return p_214485_.getHolder(p_214494_).map((p_214491_) -> {
                return HolderSet.direct(p_214491_);
            });
        }, p_214485_::getTag);
    }
    public boolean canLocateStructures(){
        System.out.println("test can locate");
        for(Ability ability : this.getAbilityPowers()){
            if(ability instanceof AbilityScout){
                System.out.println("can locate");
                return true;
            }
        }
        return false;
    }
    public void generateScoutList(){
        //STRUCTURES
        ArrayList<ResourceLocation> rls = new ArrayList<>();
        Set<ResourceLocation> sussy = ForgeRegistries.FEATURES.getKeys();
        rls.addAll(sussy);
        ArrayList<ResourceLocation> resourceLocations = new ArrayList<>();
        while(resourceLocations.size() < 3){
            int m = this.random.nextInt(sussy.size());
            if(!resourceLocations.contains(rls.get(m))) resourceLocations.add(rls.get(m));
        }
        for(ResourceLocation key : resourceLocations){
            this.structures.add(ForgeRegistries.FEATURES.getValue(key).toString().replace("minecraft:", ""));
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
        return false;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//



    //TODO: CORRUPTION
    //TODO: FUSION



    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public String getModID(){
        String id[] = ForgeRegistries.ENTITY_TYPES.getKey(this.getType()).toString().split(":");
        return id[0];
    }

    public Class getItemRegister(){
        return ModItems.class;
    }
}