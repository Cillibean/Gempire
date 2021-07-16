package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.util.Abilities;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AbilityHealer extends Ability implements IEffectAbility {

    public AbilityHealer() {
        this.ability = Abilities.HEALER;
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.REGENERATION, 200, 1);
    }

    @Override
    public boolean playerOnly() {
        return false;
    }
}
