package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityVehicle extends Ability {

    public AbilityVehicle(){
        super(18, 2);
        this.ability = GempireAbilities.VEHICLE;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.vehicle");
    }
}
