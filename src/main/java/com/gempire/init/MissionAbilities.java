package com.gempire.init;

import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.mission.AbilityMissionZilch;
import com.gempire.entities.abilities.mission.base.MissionAbility;

import java.util.ArrayList;

public class MissionAbilities {
    public static ArrayList<MissionAbility> abilities = new ArrayList<>();
    public static MissionAbility getAbility(int i) {
        for (MissionAbility ability : abilities) {
            if (ability.getId() == i) {
                return ability;
            }
        }
        return null;
    }

    public static void registerAbilities() {
        abilities.add(new AbilityMissionZilch());
    }


}
