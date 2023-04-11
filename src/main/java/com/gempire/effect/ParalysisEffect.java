package com.gempire.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ParalysisEffect extends MobEffect {
    boolean effectApplied = false;
    double prevx = 0;
    double prevy = 0;
    double prevz = 0;
    LivingEntity entity;
    int duration;

    public ParalysisEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
            if (!entity.level.isClientSide()) {
                if (duration > 1) {
                    if (!effectApplied) {
                        prevx = entity.getX();
                        prevy = entity.getY();
                        prevz = entity.getZ();
                        effectApplied = true;
                    } else {
                        entity.teleportTo(prevx, prevy, prevz);
                        if (entity.getHealth() > 1.0F) {
                            entity.hurt(DamageSource.MAGIC, 1.0F);
                        }
                    }
                } else {
                    effectApplied = false;
                }
            }
        super.applyEffectTick(entity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        duration = pDuration;
        return true;
    }

}
