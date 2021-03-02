package com.gempire.entities.abilities;

import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;

public class AbilityZilch extends Ability{

    public AbilityZilch(CreatureEntity holder) {
        super(holder);
        AbilityZilch.ABILITY = Abilities.NO_ABILITY;
    }

    @Override
    public void Fight(Entity entityIn, double damage) {

    }

    @Override
    public void EmotionalOutburst() {

    }
}
