package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityDisarming extends Ability{

    public AbilityDisarming() {
        this.ability = GempireAbilities.DISARMING;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.disarming");
    }

}
