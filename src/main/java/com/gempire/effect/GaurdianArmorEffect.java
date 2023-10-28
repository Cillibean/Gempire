package com.gempire.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class GaurdianArmorEffect extends MobEffect {

    public GaurdianArmorEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void removeAttributeModifiers(LivingEntity p_19417_, AttributeMap p_19418_, int p_19419_) {
        p_19417_.setAbsorptionAmount(p_19417_.getAbsorptionAmount() - (float)(4 * (p_19419_ + 1)));
        super.removeAttributeModifiers(p_19417_, p_19418_, p_19419_);
    }

    public void addAttributeModifiers(LivingEntity p_19421_, AttributeMap p_19422_, int p_19423_) {
        p_19421_.setAbsorptionAmount(p_19421_.getAbsorptionAmount() + (float)(4 * (p_19423_ + 1)));
        super.addAttributeModifiers(p_19421_, p_19422_, p_19423_);
    }
}
