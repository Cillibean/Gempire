package com.gempire.entities.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class EntitySorrowJelly extends WaterAnimal implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(EntitySorrowJelly.class, EntityDataSerializers.FLOAT);

    public EntitySorrowJelly(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.entityData.define(EntitySorrowJelly.SCALE, .75f);
    }

    public float getScale(){
        return this.entityData.get(EntitySorrowJelly.SCALE);
    }

    public void setScale(float value){
        this.entityData.set(EntitySorrowJelly.SCALE, value);
    }

    public float generateScale() {
        Random r = new Random();
        return .75f + (r.nextFloat(.5F) - 0.25f);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        setScale(generateScale());
        System.out.println("scale "+getScale());
        return super.finalizeSpawn(accessor, instance, type, data, tag);
    }

    @Override
    public void load(CompoundTag tag) {
        setScale(tag.getFloat("scale"));
        super.load(tag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("scale", getScale());
        super.addAdditionalSaveData(tag);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 10));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(DefaultAnimations.genericSwimIdleController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


}
