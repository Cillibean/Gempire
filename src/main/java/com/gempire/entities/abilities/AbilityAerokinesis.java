package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityAerokinesis extends Ability {

    public AbilityAerokinesis(){
        this.ability = GempireAbilities.AEROKINESIS;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.aerokinesis");
    }
}
