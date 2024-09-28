package com.gempire.entities.bosses.base;

import com.gempire.entities.ai.EmpressStrafeGoal;
import com.gempire.entities.ai.EmpressWanderGoal;
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
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityAlabasterEmpress extends EntityBoss implements FlyingAnimal {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public boolean beaming = false;
    public int beamcooldown;

    public EntityAlabasterEmpress(EntityType<? extends EntityAlabasterEmpress> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        beamcooldown = 0;
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

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new EmpressWanderGoal(this, 0.6D));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new EmpressStrafeGoal(this, 1.25D, 20, 10.0F));
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

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    public void tick() {
        beam();
        if (!level().isClientSide) {
            if (auraCryCooldown > 0) auraCryCooldown--;
            if (beamcooldown > 0) beamcooldown--;

            if (this.random.nextInt(20) == 1 && auraCryCooldown <= 0 && !beaming) auraCry();
            if (this.random.nextInt(20) == 1 && beamcooldown <= 0) beaming = true;
        }
        if (!beaming) {
            if (beamcooldown > -60) {
                beamcooldown--;
            } else {
                beamcooldown = 500;
            }
        }
        super.tick();
    }

    public void beam() {
        if (beaming && this.getTarget() != null) {
            System.out.println("BEAM");
            beaming = false;
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
}
