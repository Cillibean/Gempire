package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import net.minecraft.network.chat.Component;

public class AbilityAbundance extends Ability implements IViolentAbility {

    public AbilityAbundance() {
        super("abundance", 4);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.abundance");
    }
}
