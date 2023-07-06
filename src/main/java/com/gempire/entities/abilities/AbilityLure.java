package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityLure extends Ability {

    public AbilityLure() {
        super(25, 3);
        this.ability = GempireAbilities.LURE;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.lure");
    }
}
