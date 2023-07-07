package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.chat.Component;

public class AbilityPowerhouse extends Ability implements IAttributeAbility {

    public AbilityPowerhouse(){
        super(8, 5);
    }

    @Override
    public Attribute attribute() {
        return Attributes.ATTACK_DAMAGE;
    }

    @Override
    public double baseValue() {
        return this.holder.getAttributeValue(this.attribute()) * 1.25D;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.powerhouse");
    }
}
