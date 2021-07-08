package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.util.Abilities;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AbilityFirstAid extends Ability implements IEffectAbility {

    public AbilityFirstAid() {
        this.ability = Abilities.FIRST_AID;
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.INSTANT_HEALTH, 40, 1);
    }
}
