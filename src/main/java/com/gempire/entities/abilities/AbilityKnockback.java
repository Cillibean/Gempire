package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityKnockback extends Ability implements IMeleeAbility, IViolentAbility {

    public AbilityKnockback() {
        this.ability = Abilities.KNOCKBACK;
    }

    @Override
    public void fight(Entity entityIn, double damage) {
        PathfinderMob entity = null;
        if(entityIn instanceof PathfinderMob){
            entity = (PathfinderMob) entityIn;
        }
        else{
            return;
        }
        entity.knockback(3F, 1, 1);
    }
    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.knockback");
    }
}
