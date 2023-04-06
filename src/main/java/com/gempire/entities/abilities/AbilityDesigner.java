package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityDesigner extends Ability {

    public AbilityDesigner(){
        this.ability = Abilities.DESIGNER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.designer");
    }
}
