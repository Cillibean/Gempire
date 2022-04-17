package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityUnhinged extends Ability implements IAttributeAbility {

    public AbilityUnhinged(){
        this.ability = Abilities.UNHINGED;
    }

    @Override
    public Attribute attribute() {
        return Attributes.ATTACK_SPEED;
    }

    @Override
    public double baseValue() {
        return this.holder.getAttributeValue(Attributes.ATTACK_SPEED) * 8D;
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.unhinged");
    }
}
