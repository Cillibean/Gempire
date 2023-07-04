package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityElectrokinesis extends Ability {

    public AbilityElectrokinesis() {
        this.ability = GempireAbilities.ELECTROKINESIS;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.electrokinesis");
    }
}
