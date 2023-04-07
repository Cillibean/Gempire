package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityFarmer extends Ability {

    public AbilityFarmer(){
        this.ability = Abilities.FARMER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.farmer");
    }
}
