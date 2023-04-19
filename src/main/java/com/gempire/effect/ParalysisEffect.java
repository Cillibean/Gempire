package com.gempire.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ParalysisEffect extends MobEffect {

    public ParalysisEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (!entity.level.isClientSide()) {
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();

            entity.teleportTo(x, y, z);
            entity.setDeltaMovement(0, 0, 0);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
