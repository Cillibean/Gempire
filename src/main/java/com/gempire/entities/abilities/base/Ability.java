package com.gempire.entities.abilities.base;

import com.gempire.entities.abilities.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;

import java.util.HashMap;

public abstract class Ability {
    public CreatureEntity holder;
    public Abilities ability;
    public static HashMap<Abilities, Class<? extends Ability>> ABILITY_FROM_ABILITIES = new HashMap<>();

    public Ability assignAbility(CreatureEntity holder){
        this.holder = holder;
        return this;
    }
}
