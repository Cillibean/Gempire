package com.gempire.entities.bosses.base;

import com.gempire.entities.ai.GuardianStrafeGoal;
import com.gempire.entities.ai.PaladinLeechGoal;
import com.gempire.entities.bosses.EntityBoss;
import com.gempire.entities.projectiles.LifeLeechOrb;
import com.gempire.init.ModEffects;
import com.gempire.init.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
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
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityFuchsiaPaladin extends EntityBoss {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation CRY_ANIMATION = RawAnimation.begin().thenPlay("misc.aura_cry");
    private static final RawAnimation POUND_ANIMATION = RawAnimation.begin().thenPlay("attack.pound");
    public boolean leeching = false;
    public int leechCooldown;
    public boolean pound = false;
    public int poundCooldown;
    public int poundAnimDelay;
    public EntityFuchsiaPaladin(EntityType<? extends EntityFuchsiaPaladin> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        poundCooldown = 50;
        leechCooldown = 0;
        poundAnimDelay = 0;
        this.aura = new MobEffectInstance(ModEffects.PINK_AURA.get(), 500, 1, false, false, true);
        this.bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PINK, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 650.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 0, false, false, (entity) -> canAttack((LivingEntity) entity)));
        //this.goalSelector.addGoal(3, new PaladinLeechGoal(this, 1.25D));
        super.registerGoals();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("pound", poundCooldown);
        tag.putInt("leech", leechCooldown);
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        poundCooldown = tag.getInt("pound");
        leechCooldown = tag.getInt("leech");
        super.readAdditionalSaveData(tag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(DefaultAnimations.genericIdleController(this));
        controllerRegistrar.add(new AnimationController<>(this, "attack_controller", state -> PlayState.CONTINUE)
                .triggerableAnim("cry", CRY_ANIMATION)
                .triggerableAnim("pound", POUND_ANIMATION));
        controllerRegistrar.add(DefaultAnimations.genericWalkController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    public void tick() {
        pound();
        leech();
        if (!level().isClientSide) {
            if (auraCryCooldown > 0) auraCryCooldown--;
            if (poundCooldown > 0) poundCooldown--;
            if (poundAnimDelay > 0) poundAnimDelay--;
            if (leechCooldown > 0) leechCooldown--;

            if (this.random.nextInt(20) == 1 && auraCryCooldown <= 0 && !pound && !leeching && poundAnimDelay == 0) auraCry();
            if (this.random.nextInt(20) == 1 && poundCooldown <= 0 && !leeching) pound = true;
            if (this.random.nextInt(20) == 1 && leechCooldown <= 0 && !pound) leeching = true;
        }
        super.tick();
    }

    public void pound() {
        if (pound) {
            triggerAnim("attack_controller", "pound");
            poundCooldown = 400;
            poundAnimDelay = 40;
            pound = false;
            navigation.stop();
        } else if (poundAnimDelay == 0 && poundCooldown > 359) {
            this.level().explode(this, null, null, this.getX(), this.getY(), this.getZ(), 3, false, Level.ExplosionInteraction.NONE);
        }
    }

    public void leech() {
        if (leeching && !pound && !crying && getTarget() != null) {
            leechCooldown = 800;
            LifeLeechOrb acidSpit = new LifeLeechOrb(this.level(), this);
            acidSpit.setPos(this.getX(), this.getY()+3, this.getZ());
            double d4 = getTarget().getEyeY() - (double) 1.1F;
            double d1 = getTarget().getX() - this.getX();
            double d2 = d4 - acidSpit.getY();
            double d3 = getTarget().getZ() - this.getZ();
            float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
            acidSpit.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
            this.level().addFreshEntity(acidSpit);
            leeching = false;
        }
    }
}
