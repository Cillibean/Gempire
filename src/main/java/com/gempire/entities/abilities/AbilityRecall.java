package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;

public class AbilityRecall extends Ability {

    public AbilityRecall() {
        super(48, 3);
        this.ability = GempireAbilities.RECALL;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recall");
    }
}
