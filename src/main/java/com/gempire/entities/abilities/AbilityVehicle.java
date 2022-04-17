package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityVehicle extends Ability {

    public AbilityVehicle(){
        this.ability = Abilities.VEHICLE;
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.vehicle");
    }
}
