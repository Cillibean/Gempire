package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityScout extends Ability {

    public AbilityScout(){
        super(19, 6);
        this.ability = GempireAbilities.SCOUT;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.scout");
    }
}
