package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.ai.EntityAIGuard;
import com.gempire.util.GempireAbilities;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.network.chat.Component;

public class AbilityGuard extends Ability implements ITaskAbility {

    public AbilityGuard() {
        super(41, 2);
        this.ability = GempireAbilities.GUARD;
    }

    @Override
    public Goal goal() {
        return new EntityAIGuard(this.holder, 1D);
    }

    @Override
    public boolean targetTask() {
        return false;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.guard");
    }
}
