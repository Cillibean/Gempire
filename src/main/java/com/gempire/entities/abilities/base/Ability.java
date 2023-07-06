package com.gempire.entities.abilities.base;

import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

import java.util.HashMap;

public abstract class Ability {
    public EntityGem holder;
    public Abilities ability;
    int id;
    int weight;
    public int cooldown;
    public static HashMap<Abilities, Class<? extends Ability>> ABILITY_FROM_ABILITIES = new HashMap<>();

    public Ability(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public Ability assignAbility(EntityGem holder){
        this.holder = holder;
        return this;
    }

    public int getId() {
        return this.id;
    }

    public int getWeight() {
        return this.weight;
    }

    public abstract Component getName();
}
