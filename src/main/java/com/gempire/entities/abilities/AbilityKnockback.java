package com.gempire.entities.abilities;

import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

public class AbilityKnockback extends Ability{

    public AbilityKnockback(CreatureEntity holder) {
        super(holder);
        AbilityKnockback.ABILITY = Abilities.KNOCKBACK;
    }

    @Override
    public void Fight(Entity entityIn, double damage) {
        CreatureEntity entity = null;
        if(entityIn instanceof CreatureEntity){
            entity = (CreatureEntity) entityIn;
        }
        else{
            return;
        }
        entity.applyKnockback(1.5F, 1, 1);
    }
}
