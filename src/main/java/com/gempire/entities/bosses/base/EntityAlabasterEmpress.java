package com.gempire.entities.bosses.base;

import com.gempire.entities.ai.EmpressLaserGoal;
import com.gempire.entities.ai.EmpressStrafeGoal;
import com.gempire.entities.ai.EmpressWanderGoal;
import com.gempire.entities.bosses.EntityBoss;
import com.gempire.init.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class EntityAlabasterEmpress extends EntityBoss implements FlyingAnimal {

    private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(Guardian.class, EntityDataSerializers.INT);
    protected static final int ATTACK_TIME = 80;
    private int clientSideAttackTime;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation LASER_ANIMATION = RawAnimation.begin().thenPlay("misc.laser");
    public static final RawAnimation FLY = RawAnimation.begin().thenLoop("move.fly");
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("move.walk");
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("misc.idle");

    public boolean shooting = false;
    public boolean beaming = false;
    public int orbcooldown;
    public int beamcooldown;
    public boolean beamDamageCheck;
    @Nullable
    private LivingEntity clientSideCachedAttackTarget;

    public EntityAlabasterEmpress(EntityType<? extends EntityAlabasterEmpress> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        beamcooldown = 0;
        beamDamageCheck = true;
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
        this.aura = new MobEffectInstance(ModEffects.WHITE_AURA.get(), 500, 1, false, false, true);
    }

    protected PathNavigation createNavigation(Level p_186262_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_186262_);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 700.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D)
                .add(Attributes.FLYING_SPEED, 1.0D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new EmpressLaserGoal(this));
        this.goalSelector.addGoal(5, new EmpressWanderGoal(this, 0.6D));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new EmpressStrafeGoal(this, 1.25D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 0, false, false, (entity) -> canAttack((LivingEntity) entity)));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("beam", beamcooldown);
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        beamcooldown = tag.getInt("beam");
        super.readAdditionalSaveData(tag);
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "misc_controller", state -> PlayState.CONTINUE)
                //.triggerableAnim("cry", CRY_ANIMATION)
                .triggerableAnim("laser", LASER_ANIMATION));
        controllerRegistrar.add(new AnimationController(this, "Fly/Walk/Idle", 0, (state) -> {
            return state.setAndContinue(state.isMoving() && this.isFlying() ? FLY : state.isMoving() ? WALK : IDLE);}));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    public void tick() {
        if (!level().isClientSide) {
            if (auraCryCooldown > 0) auraCryCooldown--;
            if (orbcooldown > 0) orbcooldown--;
            if (beamcooldown > 0) beamcooldown--;

            if (this.random.nextInt(20) == 1 && auraCryCooldown <= 0 && !beaming) auraCry();
            if (this.random.nextInt(20) == 1 && beamcooldown <= 0) beam();
            if (this.random.nextInt(20) == 1 && orbcooldown <= 0) shooting = true;
        }
        if (beamcooldown == -40 && this.getTarget() != null) {
            this.getTarget().hurt(this.damageSources().mobAttack(this), 4f);
        }

            if (beamcooldown > -80) {
                beamcooldown--;
            } else {
                beaming = false;
                beamcooldown = 500;
                beamDamageCheck=true;
            }
        super.tick();
    }

    public void beam() {
        beaming = true;
        if (this.getTarget() != null) {
            triggerAnim("misc_controller", "laser");
            if (this.hasLineOfSight(this.getTarget()) && beamDamageCheck) {
                beamDamageCheck = false;
            }
        }
    }

    public void auraCry() {
        //Play animation
        navigation.stop();
        auraCryCooldown = 600;
        List<Player> list = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        for (Player player : list) {
                player.addEffect(aura);
        }
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    public int getAttackDuration() {
        return 80;
    }

    public void setActiveAttackTarget(int p_32818_) {
        this.entityData.set(DATA_ID_ATTACK_TARGET, p_32818_);
    }

    public boolean hasActiveAttackTarget() {
        return this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
    }

    @Nullable
    public LivingEntity getActiveAttackTarget() {
        if (!this.hasActiveAttackTarget()) {
            return null;
        } else if (this.level().isClientSide) {
            if (this.clientSideCachedAttackTarget != null) {
                return this.clientSideCachedAttackTarget;
            } else {
                Entity entity = this.level().getEntity(this.entityData.get(DATA_ID_ATTACK_TARGET));
                if (entity instanceof LivingEntity) {
                    this.clientSideCachedAttackTarget = (LivingEntity)entity;
                    return this.clientSideCachedAttackTarget;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_32834_) {
        super.onSyncedDataUpdated(p_32834_);
        if (DATA_ID_ATTACK_TARGET.equals(p_32834_)) {
            this.clientSideCachedAttackTarget = null;
            this.clientSideAttackTime = 0;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.hasActiveAttackTarget()) {
            if (this.clientSideAttackTime < this.getAttackDuration()) {
                ++this.clientSideAttackTime;
            }
        }
    }
}
