package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.ai.attributes.Attribute;

public interface IAttributeAbility {
    Attribute attribute();
    double baseValue();
}
