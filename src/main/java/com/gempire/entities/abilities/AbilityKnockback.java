package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;

public class AbilityKnockback extends Ability implements IMeleeAbility, IViolentAbility {

    public AbilityKnockback() {
        this.ability = Abilities.KNOCKBACK;
    }

    @Override
    public void fight(Entity entityIn, double damage) {
        CreatureEntity entity = null;
        if(entityIn instanceof CreatureEntity){
            entity = (CreatureEntity) entityIn;
        }
        else{
            return;
        }
        entity.applyKnockback(3F, 1, 1);
    }
}
