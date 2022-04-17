package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class AbilityZilch extends Ability {

    public AbilityZilch() {
        this.ability = Abilities.NO_ABILITY;
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.zilch");
    }
}
