package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityVehicle extends Ability {

    public AbilityVehicle(){
        this.ability = Abilities.VEHICLE;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.vehicle");
    }
}
