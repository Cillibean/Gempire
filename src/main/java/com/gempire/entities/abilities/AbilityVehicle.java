package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityVehicle extends Ability {

    //TODO: FIX VEHICLE TO WORK WITH LAPIS

    public AbilityVehicle(){
        super(18, 2);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.vehicle");
    }
}
