package com.gempire.entities.other;

import com.gempire.init.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityFuchsiaPaladin extends Monster implements GeoAnimatable {
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PINK, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int ticks = 0;

    public int auraCryCooldown;
    public MobEffectInstance aura = new MobEffectInstance(ModEffects.PINK_AURA.get(), 500, 1, false, false, true);

    public EntityFuchsiaPaladin(EntityType<? extends EntityFuchsiaPaladin> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        auraCryCooldown = 0;
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
        super.registerGoals();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("auraCry", auraCryCooldown);
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        auraCryCooldown = tag.getInt("auraCry");
        super.readAdditionalSaveData(tag);
    }

    public void setCustomName(@Nullable Component p_31476_) {
        super.setCustomName(p_31476_);
        this.bossEvent.setName(this.getDisplayName());
    }

    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        this.bossEvent.addPlayer(p_31483_);
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }

    @Override
    public void tick() {
        if (auraCryCooldown == 0) {
            if (random.nextInt(20) == 1) {
                auraCry();
            }
        } else {
            if (!level().isClientSide) {
                auraCryCooldown--;
            }
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    public void auraCry() {
        System.out.println("aura cry");
        //Play sound
        //Play animation
        navigation.stop();
        auraCryCooldown = 600;
        List<Player> list = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        for (Player player : list) {
            player.addEffect(aura);
        }
    }
}
