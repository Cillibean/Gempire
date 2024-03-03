package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.gems.EntityTourmaline;
import net.minecraft.network.chat.Component;

public class AbilityFarmer extends Ability implements IIdleAbility {

    public AbilityFarmer(){
        super("farmer", 5);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.farmer");
    }

    @Override
    public void execute() {
        if (holder instanceof EntityTourmaline) {
            if (!holder.getRebelled()) {
                if (holder.abilityTicks >= 30) {
                    holder.abilityTicks = 0;
                }
            }
        }
    }
}
