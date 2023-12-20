package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityAerokinesis extends Ability {

    //TODO: IMPLEMENT AEROKINESIS
    // blows things away i think - no damage dealt

    public AbilityAerokinesis(){
        super("aerokinesis", 1);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.aerokinesis");
    }
}
