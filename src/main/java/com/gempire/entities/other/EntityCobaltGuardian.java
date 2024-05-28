package com.gempire.entities.other;

import com.gempire.entities.ai.EntityAIGuardianDash;
import com.gempire.entities.projectiles.AcidSpitEntity;
import com.gempire.entities.projectiles.GuardianProjectileEntity;
import com.gempire.init.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityCobaltGuardian extends Monster implements GeoEntity, RangedAttackMob {
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation CRY_ANIMATION = RawAnimation.begin().thenPlay("cry");
    private static final RawAnimation DASH_ANIMATION = RawAnimation.begin().thenPlay("dash");

    boolean crying = false;
    public boolean isDashing = false;
    public int auraCryCooldown;
    public int dashCooldown;
    public MobEffectInstance aura = new MobEffectInstance(ModEffects.BLUE_AURA.get(), 500, 1, false, false, true);

    public EntityCobaltGuardian(EntityType<? extends EntityCobaltGuardian> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        auraCryCooldown = 0;
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    protected PathNavigation createNavigation(Level p_186262_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_186262_);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return 0;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 650.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D)
                .add(Attributes.FLYING_SPEED, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new EntityAIGuardianDash(this, 10D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 0, false, false, (entity) -> canAttack((LivingEntity) entity)));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
    }



    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("auraCry", auraCryCooldown);
        tag.putInt("dash", dashCooldown);
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        auraCryCooldown = tag.getInt("auraCry");
        dashCooldown = tag.getInt("dash");
        super.readAdditionalSaveData(tag);
    }

    public void setCustomName(@Nullable Component p_31476_) {
        super.setCustomName(p_31476_);
        this.bossEvent.setName(this.getDisplayName());
    }

    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    public void stopSeenByPlayer(ServerPlayer p_31488_) {
        super.stopSeenByPlayer(p_31488_);
        this.bossEvent.removePlayer(p_31488_);
    }

    protected boolean canRide(Entity p_31508_) {
        return false;
    }

    public boolean canChangeDimensions() {
        return false;
    }

    public boolean getDashing() {
        return isDashing;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericIdleController(this));
        controllerRegistrar.add(new AnimationController<>(this, "cry_controller", state -> PlayState.CONTINUE)
                .triggerableAnim("cry", CRY_ANIMATION));
        controllerRegistrar.add(new AnimationController<>(this, "dash_controller", state -> PlayState.CONTINUE)
                .triggerableAnim("dash", DASH_ANIMATION));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void tick() {
        if (auraCryCooldown == 0) {
            if (!level().isClientSide) {
                if (random.nextInt(20) == 1) {
                    auraCry();
                }
            }
        } else {
            if (!level().isClientSide) {
                auraCryCooldown--;
                if (dashCooldown == 0) {
                    if (this.random.nextInt(20) == 1) {
                        isDashing = true;
                    }
                } else {
                    dashCooldown--;
                }

                List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.20000000298023224, -0.009999999776482582, 0.20000000298023224), EntitySelector.pushableBy(this));
                if (!list.isEmpty()) {
                    for (Entity entity : list) {
                        entity.hurt(this.damageSources().mobAttack(this), 4);
                    }
                }
            }
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    public void auraCry() {
        System.out.println("aura cry");
        triggerAnim("cry_controller", "cry");
        crying = true;
        navigation.stop();
        auraCryCooldown = 600;
        List<Player> list = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        for (Player player : list) {
            player.addEffect(aura);
        }
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        if (!crying && !getDashing()) {
            GuardianProjectileEntity acidSpit = new GuardianProjectileEntity(this.level(), this);
            double d0 = livingEntity.getEyeY() - (double) 1.1F;
            double d1 = livingEntity.getX() - this.getX();
            double d2 = d0 - acidSpit.getY();
            double d3 = livingEntity.getZ() - this.getZ();
            float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
            acidSpit.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
            this.level().addFreshEntity(acidSpit);
        }
    }
}
