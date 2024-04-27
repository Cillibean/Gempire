package com.gempire.entities.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityPepo extends PathfinderMob {

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.<Integer>defineId(EntityPepo.class, EntityDataSerializers.INT);
    public UUID ownerUUID;

    public EntityPepo(EntityType<? extends PathfinderMob> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
        this.entityData.define(EntityPepo.VARIANT, 0);
        ownerUUID = UUID.randomUUID();
    }

    public int getVariant(){
        return this.entityData.get(EntityPepo.VARIANT);
    }

    public void setVariant(int value){
        this.entityData.set(EntityPepo.VARIANT, value);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        this.setVariant(this.random.nextInt(2));
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("variant", getVariant());
        tag.putUUID("owner", ownerUUID);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.setVariant(tag.getInt("variant"));
        //this.ownerUUID = tag.getUUID("owner");
    }

    @Override
    public void tick() {
        super.tick();
    }
}
