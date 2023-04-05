package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

public class AbilityAbundance extends Ability implements IMeleeAbility, IViolentAbility {

    public AbilityAbundance() {
        this.ability = Abilities.ABUNDANCE;
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        if (!entityIn.isAlive()) {
            entityIn.getExperienceReward();
            int reward = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(entityIn, this.holder.currentPlayer, entityIn.getExperienceReward());
            ExperienceOrb.award((ServerLevel)entityIn.level, entityIn.position(), reward);
        }
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.abundance");
    }
}
