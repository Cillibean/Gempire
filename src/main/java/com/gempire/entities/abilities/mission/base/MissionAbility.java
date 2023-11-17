package com.gempire.entities.abilities.mission.base;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.chat.Component;

public abstract class MissionAbility {
    public EntityGem holder;
    int id;
    int weight;

    public MissionAbility(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public MissionAbility assignAbility(EntityGem holder){
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
