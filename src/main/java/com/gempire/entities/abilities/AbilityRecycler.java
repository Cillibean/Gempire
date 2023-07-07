package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityRecycler extends Ability {

    public AbilityRecycler() {
        super(15, 7);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recycler");
    }
}
