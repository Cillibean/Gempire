package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityFerrokinesis extends Ability {

    public AbilityFerrokinesis() {
        this.ability = Abilities.FERROKINESIS;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.ferrokinesis");
    }
}
