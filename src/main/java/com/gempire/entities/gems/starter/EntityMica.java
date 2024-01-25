package com.gempire.entities.gems.starter;

import com.gempire.entities.abilities.AbilityZilch;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.EntityStarterGem;
import com.gempire.util.GemPlacements;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

import java.util.ArrayList;

public class EntityMica extends EntityStarterGem {
    public boolean hopperGoal = false;
    public int ticksDoingHopperGoal = 0;
    public int maxTicksDoingHopperGoal = 200;
    public boolean playing = false;

    public EntityMica(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public Float baseXScale() {
        return .2F;
    }

    @Override
    public Float baseYScale() {
        return .2F;
    }

    @Override
    public Float baseZScale() {
        return .2F;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 0);
    }
    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.hopperGoal = compound.getBoolean("hopperGoal");
        setPlaying(compound.getBoolean("isPlaying"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("hopperGoal", this.hopperGoal);
        compound.putBoolean("isPlaying", getPlaying());
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(this.level().isClientSide){
            return super.hurt(source, amount);
        }
        else{
            if(source.is(DamageTypeTags.IS_PROJECTILE)){
                setPlaying(true);
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public int getColor() {
        return 4;
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
    public void tick() {
        super.tick();
        if(this.hopperGoal){
            this.ticksDoingHopperGoal++;
            if(this.ticksDoingHopperGoal > this.maxTicksDoingHopperGoal){
                this.hopperGoal = false;
                this.ticksDoingHopperGoal = 0;
            }
        }
        if(tickCount % 200 == 0){
            setPlaying(false);
        }
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Cat.class, 3.0F, 1.25D, 1.0D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Ocelot.class, 3.0F, 1.25D, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(8, new EntityAIStay(this, 1.0D));
        this.goalSelector.addGoal(8, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(10, new EntityAIMakePowerCrystal(this, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Mob.class, 6.0F, 1.0D, 1.2D, (mob)-> mob.getClassification(true)== MobCategory.MONSTER));
    }

    @Override
    public GemPlacements[] getPlacements() {
        GemPlacements[] placement = new GemPlacements[]{
                GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.BACK, GemPlacements.CHEST,
                GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.FOREHEAD
        };
        return placement;
    }

    public ArrayList<Ability> possibleAbilities(){
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityZilch());
        return arrayList;
    }

    public void setPlaying(boolean value){
        playing = value;
    }

    public boolean getPlaying(){
        return playing;
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(1);
    }
}
