package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.ai.EntityAIBowAttackGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class AbilityArcher extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility {

    public AbilityArcher() {
        super("archer", 2);
    }

    @Override
    public Goal goal() {
        return new EntityAIBowAttackGoal(this.holder, 1.25D, 20, 15.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.archer");
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {

    }
}
