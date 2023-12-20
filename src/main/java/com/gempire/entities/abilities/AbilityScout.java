package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityScout extends Ability {

    public AbilityScout(){
        super("scout", 6);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.scout");
    }
}
