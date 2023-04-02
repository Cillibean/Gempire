package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityPrismaticRefinery extends Ability {
    public AbilityPrismaticRefinery(){
        this.ability = Abilities.REFINERY;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.refinery");
    }
}