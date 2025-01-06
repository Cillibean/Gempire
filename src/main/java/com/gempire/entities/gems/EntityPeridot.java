package com.gempire.entities.gems;

import com.gempire.config.GempireServerConfigs;
import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.util.GemPlacements;
import com.gempire.util.PeridotRepairResources;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityPeridot extends EntityGem {
    public EntityPeridot(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    public boolean hopperGoal = false;
    public int ticksDoingHopperGoal = 0;
    public int maxTicksDoingHopperGoal = 200;

    ArrayList<Item> warpList = new ArrayList<>();

    HashMap<Item, Integer> warpMap = new HashMap<>();

    ArrayList<Integer> warpMaterials = new ArrayList<>();
    ArrayList<Integer> warpMaterialAmounts = new ArrayList<>();

    ArrayList<Integer> warpCompleted = new ArrayList<>();

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    public Float baseXScale() {
        return .95F;
    }

    @Override
    public Float baseYScale() {
        return 1.15F;
    }

    @Override
    public Float baseZScale() {
        return .95F;
    }
    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_BIT.get();
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(4, new EntityAIExitHole(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIStay(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        //this.goalSelector.addGoal(1, new EntityAiAssignGems(this,4));
        this.goalSelector.addGoal(9, new EntityAIGalaxyWarp(this, 1.0D));
        this.goalSelector.addGoal(10, new EntityAIMakePowerCrystal2(this, 1.0D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Mob.class, 6.0F, 1.0D, 1.2D, (mob)-> mob.getClassification(true)== MobCategory.MONSTER));
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
        compound.putBoolean("hopperGoal", this.hopperGoal);
        compound.putString("warpMaterials", getWarpMaterials());
        compound.putString("warpAmounts", getWarpAmounts());
        compound.putString("warpCompleted", getCompleted());
    }
    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.hopperGoal = compound.getBoolean("hopperGoal");
        setWarpMaterials(compound.getString("warpMaterials"));
        setWarpAmounts(compound.getString("warpAmounts"));
        setCompleted(compound.getString("warpCompleted"));
    }

    public void generateMaterials() {
        PeridotRepairResources.register();
        warpList = PeridotRepairResources.list;
        warpMap = PeridotRepairResources.map;
        warpMaterials.clear();
        warpMaterialAmounts.clear();
        for (int i = 0; i < 4; i++) {
            int item = random.nextInt(warpList.size());
            warpMaterials.add(item);
            int amount = warpMap.get(warpList.get(item));
            int finalAmount = amount + random.nextInt(2*(amount/4));
            warpMaterialAmounts.add(finalAmount);
        }
        System.out.println(warpMaterials);
        System.out.println(warpMaterialAmounts);
    }

    public String getCompleted() {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < warpCompleted.size(); i++) {
            builder.append(warpCompleted.get(i));
            if (i < warpCompleted.size() - 1) {
                builder.append(",");
            }
        }
        if (!builder.isEmpty()) {
            return builder.toString();
        }
        return "";
    }

    public String getWarpMaterials() {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < warpMaterials.size(); i++) {
            builder.append(warpMaterials.get(i));
            if (i < warpMaterials.size() - 1) {
                builder.append(",");
            }
        }
        if (!builder.isEmpty()) {
            return builder.toString();
        }
        return "";
    }

    public String getWarpAmounts() {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < warpMaterialAmounts.size(); i++) {
            builder.append(warpMaterialAmounts.get(i));
            if (i < warpMaterialAmounts.size() - 1) {
                builder.append(",");
            }
        }
        if (!builder.isEmpty()) {
            return builder.toString();
        }
        return "";
    }

    public void setWarpAmounts(String value) {
        String[] strings = value.split(",");
        warpMaterialAmounts.clear();
        for (String string : strings) {
            warpMaterialAmounts.add(Integer.parseInt(string));
        }
    }

    public void setCompleted(String value) {
        if (!value.isEmpty()) {
            String[] strings = value.split(",");
            warpCompleted.clear();
            for (String string : strings) {
                warpCompleted.add(Integer.parseInt(string));
            }
        }
    }

    public void setWarpMaterials(String value) {
        String[] strings = value.split(",");
        warpMaterials.clear();
        for (String string : strings) {
            warpMaterials.add(Integer.parseInt(string));
        }
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[] {
                GemPlacements.FOREHEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.NOSE, GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY, GemPlacements.LEFT_SHOULDER,
                GemPlacements.RIGHT_SHOULDER, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM, GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_ANKLE, GemPlacements.RIGHT_ANKLE };
    }
    @Override
    public void tick() {
        super.tick();
        if(this.hopperGoal){
            this.ticksDoingHopperGoal++;
            if(this.ticksDoingHopperGoal > this.maxTicksDoingHopperGoal){
                this.hopperGoal = false;
                this.ticksDoingHopperGoal = 0;
            }
        }
    }
    @Override
    public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand){
        if(player.level().isClientSide){
            return super.interactAt(player, vec, hand);
        }
        if(this.isOwner(player)){
            if(player.getItemInHand(hand).getItem() instanceof PickaxeItem){
                this.hopperGoal = true;
            }
        }
        return super.interactAt(player, vec, hand);
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(3);
    }

    @Override
    public int generateInsigniaColor() {
        return 5;
    }

    @Override
    public int generateOutfitColor() {
        return 13;
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
        return arrayList;
    }
    public ArrayList<Ability> definiteAbilities(){
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityKindergartener());
        arrayList.add(new AbilitySalvaging());
        return arrayList;
    }

    public int generateSkinColorVariant() {
        return 0;
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
        return true;
    }

    public boolean hasSkinColorVariant(){
        return false;
    }

    public int generateOutfitVariant(){
        return this.random.nextInt(4);
    }

    public int generateInsigniaVariant(){
            return this.getOutfitVariant();
    }

    public int generateRebelInsigniaVariant() {
        return this.getRebelOutfitVariant();
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(2);
    }

    @Override
    public int baseFocus() {
        return 7;
    }


    public int generateHardness() {
        return 7;
    }



    @Override
    public int getColor() {
        return 5;
    }

    @Override
    public int exitHoleSize() {
        return 3;
    }
}
