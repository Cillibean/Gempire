package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityIntimidation extends Ability {

    public AbilityIntimidation() {
        super("intimidation", 2);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.intimidation");
    }
}
