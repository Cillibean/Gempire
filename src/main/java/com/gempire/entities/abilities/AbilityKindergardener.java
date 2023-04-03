package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class AbilityKindergardener extends Ability implements IIdleAbility {

    public AbilityKindergardener(){
        this.ability = Abilities.KINDERGARTENER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.kindergardener");
    }

    @Override
    public void execute() {
        Level level = holder.getLevel();
    }
}
