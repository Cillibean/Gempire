package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityZilch extends Ability {

    public AbilityZilch() {
        super(0, 10);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.zilch");
    }
}
