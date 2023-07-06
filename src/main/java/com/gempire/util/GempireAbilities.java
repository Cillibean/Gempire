package com.gempire.util;

import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;

import java.util.ArrayList;

public enum GempireAbilities implements Abilities {
    NO_ABILITY(0, 10),
    KNOCKBACK(1, 5),
    PYROKINESIS(2, 1),
    PARALYSIS(3, 2),
    CRYOKINESIS(4, 1),
    LUCK(5, 2),
    TANK(6, 3),
    BEEFCAKE(7, 5),
    POWERHOUSE(8, 5),
    UNHINGED(9, 5),
    STERN(10, 3),
    FIRST_AID(11, 1),
    HEALER(12, 1),
    NEGOTIATOR(13, 2),
    FERROKINESIS(14, 5),
    RECYCLER(15, 7),
    HYDROKINESIS(16, 1),
    AMPHIBIAN(17, 4),
    VEHICLE(18, 2),
    SCOUT(19, 6),
    COURIER(20, 5),
    TECHNICIAN(21, 7),
    PROSPECTOR(22, 5),
    SPELUNKER(23, 4),
    TORCHBEARER(24, 5),
    LURE(25, 3),
    JESTER(26, 4),
    ANGLER(27, 5),
    FARMER(28, 5),
    ENCHANTMENT(29, 2),
    ELECTROKINESIS(30, 1),
    AEROKINESIS(31, 1),
    FRAGOKINESIS(32, 1),
    BERSERKER(33, 5),
    BEASTMASTER(34, 1),
    CHARMER(35, 5),
    DISARMING(36, 3),
    LOOTMASTER(37, 4),
    AQUAPHILE(38, 4),
    ACIDIC_SPIT(39, 2),
    TERRAFORMER(40, 3),
    GUARD(41, 2),
    ESSENCE_BREWER(42, 1),
    BUILDER(43, 4),
    REFINERY(44, 4),
    ABUNDANCE(45, 4),
    DESIGNER(46, 5),
    FORTUNATE(47, 3),
    RECALL(48,3),
    KINDERGARTENER(49,3),
    MINING(50,3),
    ARCHER(51,2),
    TINKERER(52,4),
    SALVAGING(53,4);

    public final int id;
    public final int weight;
    public static GempireAbilities[] vals = GempireAbilities.values();
    public static ArrayList<Ability> abilities = new ArrayList<>();
    GempireAbilities(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }
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

//List of possible abilities
//Add the default abilities (keep track of this number)
//Add (weighted) possible abilities
//Return string with all abilities (default + pooled)