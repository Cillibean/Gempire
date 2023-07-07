package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AbilityNegotiator extends Ability implements IEffectAbility {

    public AbilityNegotiator() {
        super(13, 2);
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 400, 0, false, false);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.negotiator");
    }
}
