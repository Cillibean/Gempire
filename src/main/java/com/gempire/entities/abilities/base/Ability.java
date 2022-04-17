package com.gempire.entities.abilities.base;

import com.gempire.entities.abilities.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

import java.util.HashMap;

public abstract class Ability {
    public EntityGem holder;
    public Abilities ability;
    public static HashMap<Abilities, Class<? extends Ability>> ABILITY_FROM_ABILITIES = new HashMap<>();

    public Ability assignAbility(EntityGem holder){
        this.holder = holder;
        return this;
    }

    public abstract Component getName();
}
