package com.gempire.entities.other;

import com.gempire.entities.ai.EntityAIAbominationAttackGoal;
import com.gempire.entities.ai.EntityAICrawlerAttackGoal;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.init.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityAbomination extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("animation.abomination.attack");
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("animation.abomination.idle");
    private static final RawAnimation WALK_ANIMATION = RawAnimation.begin().thenLoop("animation.abomination.walk");
    private static final RawAnimation HURT_ANIMATION = RawAnimation.begin().thenPlay("animation.abomination.hurt");

    public EntityAbomination(EntityType<? extends Monster> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, .5D);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkSludged));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.goalSelector.addGoal(1, new EntityAIAbominationAttackGoal(this, 1.2D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(DefaultAnimations.genericWalkIdleController(this));
        registrar.add(DefaultAnimations.genericAttackAnimation(this, ATTACK_ANIMATION));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ABOMINATION_LIVING.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return ModSounds.ABOMINATION_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ABOMINATION_DEATH.get();
    }
    public boolean checkSludged(LivingEntity entity) {
        return ((EntityGem) entity).getSludgeAmount() >= 5;
    }

    @Override
    public int getCurrentSwingDuration() {
        return 10;
    }

    @Override
    public boolean removeWhenFarAway(double xix){
        return false;
    }
}
