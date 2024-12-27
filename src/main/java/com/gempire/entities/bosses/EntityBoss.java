package com.gempire.entities.bosses;

import com.gempire.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.List;

public abstract class EntityBoss extends Monster implements GeoEntity {

    public ServerBossEvent bossEvent;
    public int auraCryCooldown;
    public MobEffectInstance aura;

    public BlockPos altarPos;
    protected EntityBoss(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        auraCryCooldown = 0;
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

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.POOF.get();
    }

    public void auraCry() {
        triggerAnim("misc_controller", "cry");
        navigation.stop();
        auraCryCooldown = 600;
        List<Player> list = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        for (Player player : list) {
            player.addEffect(aura);
        }
    }

    @Override
    public void aiStep() {
        if (altarPos != null) {
            if (getX() > altarPos.getX()+25 || getX() < altarPos.getX()-25) {
                if (getZ() > altarPos.getZ()+25 || getZ() < altarPos.getZ()-25) {
                    navigation.stop();
                    goalSelector.tickRunningGoals(false);
                    targetSelector.tickRunningGoals(false);
                    navigation.moveTo(altarPos.getX(), altarPos.getY()+1, altarPos.getZ(), 1D);
                }
            }
            if (this.getOnPos().getX() == altarPos.getX() && this.getOnPos().getZ() == altarPos.getZ()) {
                goalSelector.tickRunningGoals(true);
            }
        }
        super.aiStep();
    }

    @Override
    public void tick() {
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("auraCry", auraCryCooldown);
        tag.putLong("altarPos", altarPos.asLong());
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        if (!tag.isEmpty()) {
            auraCryCooldown = tag.getInt("auraCry");
            altarPos = BlockPos.of(tag.getLong("altarPos"));
        }
        super.readAdditionalSaveData(tag);
    }
}
