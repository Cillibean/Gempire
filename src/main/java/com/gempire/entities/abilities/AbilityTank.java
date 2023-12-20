package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.chat.Component;

public class AbilityTank extends Ability implements IAttributeAbility {

    public AbilityTank(){
        super("tank", 3);
    }

    @Override
    public Attribute attribute() {
        return Attributes.MAX_HEALTH;
    }

    @Override
    public double baseValue() {
        return this.holder.getAttributeValue(Attributes.MAX_HEALTH) * 1.25D;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.tank");
    }
}
