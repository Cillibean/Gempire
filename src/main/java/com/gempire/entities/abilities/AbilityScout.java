package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityScout extends Ability {

    public AbilityScout(){
        this.ability = Abilities.SCOUT;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.scout");
    }
}
