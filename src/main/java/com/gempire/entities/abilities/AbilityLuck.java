package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AbilityLuck extends Ability implements IEffectAbility {

    public AbilityLuck() {
        this.ability = Abilities.LUCK;
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.LUCK, 200, 3);
    }

    @Override
    public boolean playerOnly() {
        return true;
    }
}
