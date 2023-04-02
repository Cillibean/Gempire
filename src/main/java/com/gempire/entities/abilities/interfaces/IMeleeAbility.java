package com.gempire.entities.abilities.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public interface IMeleeAbility {
    void fight(LivingEntity entityIn, double damage);
}
