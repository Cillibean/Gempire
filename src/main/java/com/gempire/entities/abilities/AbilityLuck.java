package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AbilityLuck extends Ability implements IEffectAbility {

    public AbilityLuck() {
        this.ability = Abilities.LUCK;
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.LUCK, 400, 0);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.luck");
    }
}
