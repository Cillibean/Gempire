package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityRecycler extends Ability {

    public AbilityRecycler() {
        this.ability = Abilities.RECYCLER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recycler");
    }
}
