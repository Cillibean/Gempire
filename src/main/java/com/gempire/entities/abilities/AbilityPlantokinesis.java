package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class AbilityPlantokinesis extends Ability implements IIdleAbility, IEmotionalAbility, IViolentAbility {

    public AbilityPlantokinesis() {
        super("plantokinesis", 3);
    }

    @Override
    public void outburst() {

    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.plantokinesis");
    }

    @Override
    public void execute() {

    }
}
