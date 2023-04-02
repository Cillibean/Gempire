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

    public ParalysisEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide()) {
            if (!effectApplied) {
                prevx = pLivingEntity.getX();
                prevy = pLivingEntity.getY();
                prevz = pLivingEntity.getZ();
                effectApplied = true;
            } else {
                pLivingEntity.teleportTo(prevx, prevy, prevz);
                if (pLivingEntity.getHealth() > 1.0F) {
                    pLivingEntity.hurt(DamageSource.MAGIC, 1.0F);
                }
            }
            if (!pLivingEntity.hasEffect(this)) {
                effectApplied = false;
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
