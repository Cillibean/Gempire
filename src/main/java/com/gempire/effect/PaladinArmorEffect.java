package com.gempire.effect;

import com.gempire.init.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PaladinArmorEffect extends MobEffect {

    int tick = 0;

    public PaladinArmorEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void removeAttributeModifiers(LivingEntity p_19436_, AttributeMap p_19437_, int p_19438_) {
        super.removeAttributeModifiers(p_19436_, p_19437_, p_19438_);
        if (p_19436_.getHealth() > p_19436_.getMaxHealth() && !p_19436_.hasEffect(ModEffects.PALADINS_PROTECTION.get())) {
            p_19436_.setHealth(p_19436_.getMaxHealth());
        }
    }

    @Override
    public void applyEffectTick(LivingEntity p_19467_, int p_19468_) {
        if (!p_19467_.level.isClientSide) {
            if (p_19467_.getHealth() < p_19467_.getMaxHealth()) {
                if (tick == 30) {
                    p_19467_.heal(1.0F);
                    tick = 0;
                } else {
                    tick++;
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
