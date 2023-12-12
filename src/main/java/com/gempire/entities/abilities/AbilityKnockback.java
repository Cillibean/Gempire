package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.network.chat.Component;

public class AbilityKnockback extends Ability implements IMeleeAbility, IViolentAbility {

    public AbilityKnockback() {
        super("knockback", 5);
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
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
        return Component.translatable("ability.gempire.knockback");
    }
}
