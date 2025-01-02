package com.gempire.entities.gems;

import com.gempire.config.GempireServerConfigs;
import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.util.GemPlacements;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityTourmaline extends EntityVaryingGem {

    public static EntityDataAccessor<String> CROPS = SynchedEntityData.<String>defineId(EntityGem.class, EntityDataSerializers.STRING);
    public static EntityDataAccessor<Boolean> BUILDING = SynchedEntityData.<Boolean>defineId(EntityGem.class, EntityDataSerializers.BOOLEAN);

    HashMap<String, Item> cropMap = new HashMap<>();
    HashMap<Item, String> stringMap = new HashMap<>();

    public EntityTourmaline(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.entityData.define(EntityTourmaline.CROPS, "");
        this.entityData.define(EntityTourmaline.BUILDING, false);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("crops", getCrops());
        tag.putBoolean("building", isBuilding());
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        setCrops(tag.getString("crops"));
        setBuilding(tag.getBoolean("building"));
        super.load(tag);
    }

    @Override
    public Float baseXScale() {
        return 1F;
    }

    @Override
    public Float baseYScale() {
        return 1.1F;
    }

    @Override
    public Float baseZScale() {
        return 1F;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIStay(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkRebel));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityAbomination.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityShambler.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityCrawler.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 1, false, false, (p_234199_0_) -> p_234199_0_.getClassification(true) == MobCategory.MONSTER));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGemGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGemGoal(this));
        this.goalSelector.addGoal(1, new EntityAISludged(this, 0.6));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkBothSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 1, false, false, this::checkSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityGem.class, 6.0F, 1.0D, 1.2D, this::checkElseSludged));
        this.goalSelector.addGoal(1, new EntityAIFarm(this, 1.0D));
        this.goalSelector.addGoal(1, new EntityAIBuildFarm(this, 1.0D));
    }
    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_DIDGERIDOO.get();
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.TOP_OF_HEAD, GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.NOSE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR, GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY,
                GemPlacements.LEFT_SHOULDER, GemPlacements.RIGHT_SHOULDER, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM,
                GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_ANKLE, GemPlacements.RIGHT_ANKLE
        };
    }

    @Override
    public boolean hasMarkings() {
        return this.getSkinColorVariant() == 16;
    }

    @Override
    public boolean hasMarkings2() {
        return this.getSkinColorVariant() == 16;
    }

    @Override
    public int maxMarkings() {
        return 1;
    }

    @Override
    public int maxMarkings2() {
        return 1;
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(1);
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

    @Override
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "pumpkin";
                break;
            case 2:
                name = "magenta";
                break;
            case 3:
                name = "olenite";
                break;
            case 4:
                name = "canary";
                break;
            case 5:
                name = "verdelite";
                break;
            case 6:
                name = "rossmanite";
                break;
            case 7:
                name = "adachiite";
                break;
            case 8:
                name = "povondraite";
                break;
            case 9:
                name = "paraiba";
                break;
            case 10:
                name = "siberite";
                break;
            case 11:
                name = "indicolite";
                break;
            case 12:
                name = "dravite";
                break;
            case 13:
                name = "congo";
                break;
            case 14:
                name = "rubellite";
                break;
            case 15:
                name = "schorl";
                break;
            case 16:
                name = "watermelon";
                break;
            default:
                name = "achroite";
                break;
        }
        return name;
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return false;
    }

    @Override
    public int[] outfitPlacementVariants() {
        return new int[]{
                11, 17
        };
    }

    public ArrayList<Ability> possibleAbilities(){
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityZilch());
        arrayList.add(new AbilityTank());
        arrayList.add(new AbilityBeefcake());
        arrayList.add(new AbilityPowerhouse());
        arrayList.add(new AbilityUnhinged());
        arrayList.add(new AbilityBerserker());
        return arrayList;
    }
    public ArrayList<Ability> definiteAbilities(){
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityFarmer());
        arrayList.add(new AbilityPlantokinesis());
        return arrayList;
    }

    @Override
    public boolean generateIsEmotional() {
        return GempireServerConfigs.OUTBURSTS.get();
    }

    @Override
    public byte EmotionThreshold() {
        return 10;
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
                17
        };
    }

    public int generateOutfitVariant(){
        return this.random.nextInt(3);
    }

    public int generateInsigniaVariant(){
            return this.getOutfitVariant();
    }

    public int generateRebelInsigniaVariant() {
        return this.getRebelOutfitVariant();
    }

    public boolean isFocused() {
        return true;
    }

    @Override
    public int baseFocus() {
        return 7;
    }

    public int generateHardness() {
        return 7;
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(2);
    }

    @Override
    public int exitHoleSize() {
        return 3;
    }

    public void setBuilding(boolean value) {
        this.entityData.set(EntityTourmaline.BUILDING, value);
    }

    public boolean isBuilding() {
        return this.entityData.get(EntityTourmaline.BUILDING);
    }

    public void setCrops(String page){
        this.entityData.set(EntityTourmaline.CROPS, page);
    }

    public String getCrops(){
        return this.entityData.get(EntityTourmaline.CROPS);
    }

    public String generateCrops() {
        StringBuilder crops = new StringBuilder();
        int number;
        if (this.getQuality() == 0) {
            //def
            number=1;
        } else if (this.getQuality() == 1) {
            number = 2;
        } else {
            //prim
            number = 3;
        }

        for (int i = 0; i < number; i++) {
            int ran = random.nextInt(4);
            if (ran == 0) {
                if (crops.toString().contains("carrot")) {
                    ran = random.nextInt(3)+1;
                } else {
                    crops.append("carrot");
                }
            } else if (ran == 1) {
                if (crops.toString().contains("potato")) {
                    ran = random.nextInt(2)+2;
                } else {
                    crops.append("potato");
                }
            } else if (ran == 2) {
                if (!crops.toString().contains("wheat")) {
                    crops.append("wheat");
                }
            } else {
                crops.append("beetroot");
            }
            if (i < number-1) crops.append(",");
        }
        return crops.toString();
    }

    public ArrayList<Item> readCrops(){
        ArrayList<Item> list = new ArrayList<>();
        generateCropMap();
        String[] crops = getCrops().split(",");
        for (int i = 0; i < crops.length; i++) {
            list.add(cropMap.get(crops[i]));
        }
        return list;
    }

    public void generateCropMap() {
        cropMap.put("carrot", Items.CARROT);
        cropMap.put("beetroot", Items.BEETROOT_SEEDS);
        cropMap.put("wheat", Items.WHEAT_SEEDS);
        cropMap.put("potato", Items.POTATO);
    }
}
