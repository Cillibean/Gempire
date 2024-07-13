package com.gempire.entities.bosses.base;

import com.gempire.entities.projectiles.HuntressLightning;
import com.gempire.init.ModEffects;
import com.gempire.init.ModEntities;
import com.gempire.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityAmberHuntress extends Monster implements GeoEntity {
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation CRY_ANIMATION = RawAnimation.begin().thenPlay("cry");

    boolean crying = false;
    public int auraCryCooldown;
    boolean pound = false;
    public int poundCooldown;
    boolean lightningAOE = false;
    public int lightningAOECooldown;
    public MobEffectInstance aura = new MobEffectInstance(ModEffects.YELLOW_AURA.get(), 500, 1, false, false, false);

    public EntityAmberHuntress(EntityType<? extends EntityAmberHuntress> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        auraCryCooldown = 0;
        poundCooldown = 50;
        lightningAOECooldown = 0;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        //this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        //this.goalSelector.addGoal(2, new EntityAIGuardianDash(this, 1.1D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 0, false, false, (entity) -> canAttack((LivingEntity) entity)));
        //this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
    }



    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("auraCry", auraCryCooldown);
        tag.putInt("pound", poundCooldown);
        tag.putInt("lightningAOE", lightningAOECooldown);
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        auraCryCooldown = tag.getInt("auraCry");
        poundCooldown = tag.getInt("pound");
        lightningAOECooldown = tag.getInt("lightningAOE");
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

    //public boolean getDashing() {
        //return isDashing;
    //}


    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.POOF.get();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericIdleController(this));
        //controllerRegistrar.add(new AnimationController<>(this, "cry_controller", state -> PlayState.CONTINUE)
                //.triggerableAnim("cry", CRY_ANIMATION));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void tick() {
        lightningAOE();
        if (!level().isClientSide) {
            if (auraCryCooldown > 0) auraCryCooldown--;
            if (lightningAOECooldown > 0) lightningAOECooldown--;
            if (poundCooldown > 0) poundCooldown--;

            if (this.random.nextInt(20) == 1 && auraCryCooldown <= 0 && !lightningAOE && !pound) auraCry();
            if (this.random.nextInt(20) == 1 && lightningAOECooldown <= 0 && !pound) lightningAOE = true;
            if (poundCooldown <= 0 && !lightningAOE) pound();

            List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.20000000298023224, -0.009999999776482582, 0.20000000298023224), EntitySelector.pushableBy(this));
            if (!list.isEmpty()) {
                for (Entity entity : list) {
                    entity.hurt(this.damageSources().mobAttack(this), 4);
                }
            }
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    public void lightningAOE() {
        if (lightningAOE && this.getTarget() != null) {
            lightningAOECooldown = 300;
            lightningAOE = false;
            navigation.stop();
            ArrayList<BlockPos> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new BlockPos((int) (this.getX() - 8 + this.random.nextInt(16)), (int) (this.getY()), (int) (this.getZ() - 8 + this.random.nextInt(16))));
            }
            for (BlockPos pos : list) {
                HuntressLightning lightning = ModEntities.HUNTRESS_LIGHTNING.get().create(level());
                lightning.setPos(pos.getX(), pos.getY(), pos.getZ());
                lightning.setUUID(Mth.createInsecureUUID(level().random));
                level().addFreshEntity(lightning);
            }
        }
    }

    public void pound() {
        if (pound) {
            System.out.println("pound");
            poundCooldown = 200;
            pound = false;
            navigation.stop();
            this.level().explode(this, null, null, this.getX(), this.getY(), this.getZ(), 5, false, Level.ExplosionInteraction.NONE);
        }
    }

    public void auraCry() {
        //triggerAnim("cry_controller", "cry");
        navigation.stop();
        auraCryCooldown = 600;
        List<Player> list = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        for (Player player : list) {
            player.addEffect(aura);
        }
    }
}
