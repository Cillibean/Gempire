package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AbilityLuck extends Ability implements IEffectAbility {

    public AbilityLuck() {
        super(5, 2);
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.LUCK, 400, 0,false,false);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.luck");
    }
}
