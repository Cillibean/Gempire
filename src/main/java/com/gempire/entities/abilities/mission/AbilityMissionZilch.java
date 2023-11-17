package com.gempire.entities.abilities.mission;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.mission.base.MissionAbility;
import net.minecraft.network.chat.Component;

public class AbilityMissionZilch extends MissionAbility {

    public AbilityMissionZilch(){
        super(0, 10);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.vehicle");
    }
}
