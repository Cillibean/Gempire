package com.gempire.entities.abilities;

import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;

import javax.annotation.Nullable;

public abstract class Ability {
    public static Goal TASK;
    public static Abilities ABILITY;
    public CreatureEntity holder;

    public Ability(CreatureEntity holder){
        this.holder = holder;
    }

    public abstract void Fight(Entity entityIn, double damage);

    public abstract void EmotionalOutburst();

    public static Ability GetAbilityFromAbilities(EntityGem wielder, Abilities ability){
        switch (ability){
            case KNOCKBACK:
                return new AbilityKnockback(wielder);
            case PYROKINESIS:
                return new AbilityPyrokinesis(wielder);
            case PARALYSIS:
                return new AbilityParalysis(wielder);
            default:
                return new AbilityZilch(wielder);
        }
    }
}
