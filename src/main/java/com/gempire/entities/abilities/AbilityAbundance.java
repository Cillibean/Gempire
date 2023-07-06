package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityAbundance extends Ability implements IViolentAbility {

    public AbilityAbundance() {
        super(45, 4);
        this.ability = GempireAbilities.ABUNDANCE;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.abundance");
    }
}
