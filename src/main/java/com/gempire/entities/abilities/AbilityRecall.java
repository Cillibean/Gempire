package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import net.minecraft.network.chat.Component;

public class AbilityRecall extends Ability {

    public AbilityRecall() {
        super("recall", 3);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recall");
    }
}
