package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityAerokinesis extends Ability {

    public AbilityAerokinesis(){
        super(31, 1);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.aerokinesis");
    }
}
