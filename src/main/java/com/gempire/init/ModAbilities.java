package com.gempire.init;

import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;

public class ModAbilities {

    public static void registerAbilities(){
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.NO_ABILITY, AbilityZilch.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.CRYOKINESIS, AbilityCryokinesis.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.PYROKINESIS, AbilityPyrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.PARALYSIS, AbilityParalysis.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.KNOCKBACK, AbilityKnockback.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.LUCK, AbilityLuck.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.FIRST_AID, AbilityFirstAid.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.HEALER, AbilityHealer.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.AMPHIBIAN, AbilityAmphibian.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.HYDROKINESIS, AbilityHydrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.JESTER, AbilityJester.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.TANK, AbilityTank.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.BEEFCAKE, AbilityBeefcake.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.POWERHOUSE, AbilityPowerhouse.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.UNHINGED, AbilityUnhinged.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.BERSERKER, AbilityBerserker.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.BEASTMASTER, AbilityBeastmaster.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.ACIDIC_SPIT, AbilityAcidicSaliva.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.DISARMING, AbilityDisarming.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.ABUNDANCE, AbilityAbundance.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.LOOTMASTER, AbilityLootmaster.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.STERN, AbilityStern.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.SPELUNKER, AbilitySpelunker.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.PROSPECTOR, AbilityProspector.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.TORCHBEARER, AbilityTorchBearer.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.GUARD, AbilityGuard.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.VEHICLE, AbilityVehicle.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.SCOUT, AbilityScout.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.KINDERGARTENER, AbilityKindergartener.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.DESIGNER, AbilityDesigner.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.NEGOTIATOR, AbilityNegotiator.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.RECALL, AbilityRecall.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.LURE, AbilityLure.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.FARMER, AbilityFarmer.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.AEROKINESIS, AbilityAerokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.RECYCLER, AbilityRecycler.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.ELECTROKINESIS, AbilityElectrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.FERROKINESIS, AbilityFerrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.MINING, AbilityMiningInspiration.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.ESSENCE_BREWER, AbilityBrewEssence.class);
        Ability.ABILITY_FROM_ABILITIES.put(Abilities.REFINERY, AbilityPrismaticRefinery.class);

        Ability.ABILITY_FROM_ABILITIES.put(Abilities.ANGLER, AbilityAngler.class);
    }
}
