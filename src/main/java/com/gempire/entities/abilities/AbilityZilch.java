package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityZilch extends Ability {

    public AbilityZilch() {
        this.ability = GempireAbilities.NO_ABILITY;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.zilch");
    }
}
