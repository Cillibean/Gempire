package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityLure extends Ability {

    public AbilityLure() {
        super("lure", 3);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.lure");
    }
}
