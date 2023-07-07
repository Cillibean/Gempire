package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModAbilities {

    public static ArrayList<Ability> abilities = new ArrayList<>();
    public static Ability getAbility(int i) {
        for (Ability ability : abilities) {
            if (ability.getId() == i) {
                return ability;
            }
        }
        return null;
    }

    public static void registerAbilities() {
        abilities.add(new AbilityZilch());
        abilities.add(new AbilityKnockback());
        abilities.add(new AbilityPyrokinesis());
        abilities.add(new AbilityParalysis());
        abilities.add(new AbilityCryokinesis());
        abilities.add(new AbilityLuck());
        abilities.add(new AbilityTank());
        abilities.add(new AbilityBeefcake());
        abilities.add(new AbilityPowerhouse());
        abilities.add(new AbilityUnhinged());
        abilities.add(new AbilityStern());
        abilities.add(new AbilityFirstAid());
        abilities.add(new AbilityHealer());
        abilities.add(new AbilityNegotiator());
        abilities.add(new AbilityRecycler());
        abilities.add(new AbilityHydrokinesis());
        abilities.add(new AbilityAmphibian());
        abilities.add(new AbilityVehicle());
        abilities.add(new AbilityScout());
        abilities.add(new AbilityProspector());
        abilities.add(new AbilitySpelunker());
        abilities.add(new AbilityTorchBearer());
        abilities.add(new AbilityLure());
        abilities.add(new AbilityJester());
        abilities.add(new AbilityAngler());
        abilities.add(new AbilityFarmer());
        abilities.add(new AbilityElectrokinesis());
        abilities.add(new AbilityAerokinesis());
        abilities.add(new AbilityBerserker());
        abilities.add(new AbilityBeastmaster());
        abilities.add(new AbilityDisarming());
        abilities.add(new AbilityLootmaster());
        abilities.add(new AbilityHydration());
        abilities.add(new AbilityAcidicSaliva());
        abilities.add(new AbilityGuard());
        abilities.add(new AbilityBrewEssence());
        abilities.add(new AbilityPrismaticRefinery());
        abilities.add(new AbilityAbundance());
        abilities.add(new AbilityDesigner());
        abilities.add(new AbilityRecall());
        abilities.add(new AbilityKindergartener());
        abilities.add(new AbilityMiningInspiration());
        abilities.add(new AbilityArcher());
        abilities.add(new AbilityTinkerer());
        abilities.add(new AbilitySalvaging());
    }
}
