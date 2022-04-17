package com.gempire.entities.abilities.interfaces;

import net.minecraft.world.entity.LivingEntity;

public interface IRangedAbility {
    void attack(LivingEntity target, float distanceFactor);
}
