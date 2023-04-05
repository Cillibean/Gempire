package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityLure extends Ability {

    public AbilityLure() {
        this.ability = Abilities.LURE;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.lure");
    }
}
