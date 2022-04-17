package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityScout extends Ability {

    public AbilityScout(){
        this.ability = Abilities.SCOUT;
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.scout");
    }
}
