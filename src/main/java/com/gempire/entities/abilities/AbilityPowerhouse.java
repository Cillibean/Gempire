package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.util.Abilities;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;

public class AbilityPowerhouse extends Ability implements IAttributeAbility {

    public AbilityPowerhouse(){
        this.ability = Abilities.POWERHOUSE;
    }

    @Override
    public Attribute attribute() {
        return Attributes.ATTACK_DAMAGE;
    }

    @Override
    public double baseValue() {
        return this.holder.getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.5D;
    }
}
