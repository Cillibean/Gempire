package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.ai.EntityAIGuard;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityGuard extends Ability implements ITaskAbility {

    public AbilityGuard() {
        this.ability = Abilities.GUARD;
    }

    @Override
    public Goal goal() {
        return new EntityAIGuard((EntityGem)this.holder, 1D);
    }

    @Override
    public boolean targetTask() {
        return false;
    }
    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.guard");
    }
}
