package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityElectrokinesis extends Ability {

    //TODO: IMPLEMENT ELECTROKINESIS
    // need to have lightning target only one entity

    public AbilityElectrokinesis() {
        super(30, 1);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.electrokinesis");
    }
}
