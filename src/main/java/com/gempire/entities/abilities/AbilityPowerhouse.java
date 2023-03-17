package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.chat.Component;

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
        return this.holder.getAttributeValue(this.attribute()) * 2D;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.powerhouse");
    }
}
