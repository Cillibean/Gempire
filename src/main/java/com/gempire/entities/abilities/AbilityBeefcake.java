package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityBeefcake extends Ability implements IAttributeAbility {

    public AbilityBeefcake(){
        this.ability = Abilities.BEEFCAKE;
    }

    @Override
    public Attribute attribute() {
        return Attributes.ARMOR;
    }

    @Override
    public double baseValue() {
        return this.holder.getAttributeValue(Attributes.ARMOR) + 8.0D;
    }
    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.beefcake");
    }
}
