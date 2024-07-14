package com.gempire.entities.bosses.base;

import com.gempire.entities.bosses.EntityBoss;
import com.gempire.init.ModEffects;
import com.gempire.init.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityFuchsiaPaladin extends EntityBoss {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    boolean pound = false;
    public int poundCooldown;
    public EntityFuchsiaPaladin(EntityType<? extends EntityFuchsiaPaladin> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        poundCooldown = 50;
        this.aura = new MobEffectInstance(ModEffects.PINK_AURA.get(), 500, 1, false, false, true);
        this.bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PINK, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6500.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D)
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
        super.registerGoals();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("pound", poundCooldown);
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        poundCooldown = tag.getInt("pound");
        super.readAdditionalSaveData(tag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    public void tick() {
        pound();
        if (!level().isClientSide) {
            if (auraCryCooldown > 0) auraCryCooldown--;
            if (poundCooldown > 0) poundCooldown--;

            if (this.random.nextInt(20) == 1 && auraCryCooldown <= 0 && !pound) auraCry();
            if (this.random.nextInt(20) == 1 && poundCooldown <= 0) pound = true;
        }
        super.tick();
    }

    public void pound() {
        if (pound) {
            poundCooldown = 400;
            pound = false;
            navigation.stop();
            this.level().explode(this, null, null, this.getX(), this.getY(), this.getZ(), 3, false, Level.ExplosionInteraction.NONE);
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
}
