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
import net.minecraft.world.entity.player.Player;

public class AbilityAbundance extends Ability implements IViolentAbility {

    public AbilityAbundance() {
        this.ability = Abilities.ABUNDANCE;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.abundance");
    }
}
