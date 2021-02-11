package com.gempire.entities.abilities;

import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

public class AbilityPyrokinesis extends Ability{

    public AbilityPyrokinesis(CreatureEntity holder) {
        super(holder);
        AbilityPyrokinesis.ABILITY = Abilities.PYROKINESIS;
    }

    @Override
    public void Fight(Entity entityIn, double damage) {
        entityIn.setFire(5);
    }
}
