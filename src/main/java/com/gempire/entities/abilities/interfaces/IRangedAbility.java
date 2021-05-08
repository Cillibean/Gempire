package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.LivingEntity;

public interface IRangedAbility {
    void attack(LivingEntity target, float distanceFactor);
}
