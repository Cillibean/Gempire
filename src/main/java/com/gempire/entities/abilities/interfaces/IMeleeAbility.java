package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public interface IMeleeAbility {
    void fight(Entity entityIn, double damage);
}
