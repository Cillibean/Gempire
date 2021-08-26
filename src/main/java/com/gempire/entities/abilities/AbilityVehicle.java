package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityVehicle extends Ability {

    public AbilityVehicle(){
        this.ability = Abilities.VEHICLE;
    }

    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.vehicle");
    }
}
