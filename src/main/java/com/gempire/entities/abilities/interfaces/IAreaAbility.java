package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.potion.EffectInstance;

import java.util.UUID;

public interface IAreaAbility {
    void AOeffect();
    void AOeffect(Entity entity);
    void AOeffect(Entity entity, UUID id);
}
