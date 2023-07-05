package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.GempireAbilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModAbilities {

    public static void registerAbilities(){
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.NO_ABILITY, AbilityZilch.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.CRYOKINESIS, AbilityCryokinesis.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.PYROKINESIS, AbilityPyrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.PARALYSIS, AbilityParalysis.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.KNOCKBACK, AbilityKnockback.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.LUCK, AbilityLuck.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.FIRST_AID, AbilityFirstAid.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.HEALER, AbilityHealer.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.AMPHIBIAN, AbilityAmphibian.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.HYDROKINESIS, AbilityHydrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.JESTER, AbilityJester.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.TANK, AbilityTank.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.BEEFCAKE, AbilityBeefcake.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.POWERHOUSE, AbilityPowerhouse.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.UNHINGED, AbilityUnhinged.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.BERSERKER, AbilityBerserker.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.BEASTMASTER, AbilityBeastmaster.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.ACIDIC_SPIT, AbilityAcidicSaliva.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.DISARMING, AbilityDisarming.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.ABUNDANCE, AbilityAbundance.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.LOOTMASTER, AbilityLootmaster.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.STERN, AbilityStern.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.SPELUNKER, AbilitySpelunker.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.PROSPECTOR, AbilityProspector.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.TORCHBEARER, AbilityTorchBearer.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.GUARD, AbilityGuard.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.VEHICLE, AbilityVehicle.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.SCOUT, AbilityScout.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.KINDERGARTENER, AbilityKindergartener.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.NEGOTIATOR, AbilityNegotiator.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.RECALL, AbilityRecall.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.LURE, AbilityLure.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.FARMER, AbilityFarmer.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.AEROKINESIS, AbilityAerokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.RECYCLER, AbilityRecycler.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.ELECTROKINESIS, AbilityElectrokinesis.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.MINING, AbilityMiningInspiration.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.ARCHER, AbilityArcher.class);

        //crafting abilities
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.DESIGNER, AbilityDesigner.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.ESSENCE_BREWER, AbilityBrewEssence.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.REFINERY, AbilityPrismaticRefinery.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.TINKERER, AbilityTinkerer.class);
        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.SALVAGING, AbilitySalvaging.class);

        Ability.ABILITY_FROM_ABILITIES.put(GempireAbilities.ANGLER, AbilityAngler.class);
    }

    public static final DeferredRegister<Ability> ABILITY_DEFERRED_REGISTER = DeferredRegister.create(
            new ResourceLocation(Gempire.MODID, "abilities"), Gempire.MODID);

    public static RegistryObject<Ability> NO_ABILITY = ABILITY_DEFERRED_REGISTER.register("no_ability", () ->
            new AbilityZilch());

    public static RegistryObject<Ability> CRYOKINESIS = ABILITY_DEFERRED_REGISTER.register("cryokinesis", () ->
            new AbilityCryokinesis());

}
