package com.gempire.util;

public enum Abilities {
    NO_ABILITY(0, 10),
    KNOCKBACK(1, 6),
    PYROKINESIS(2, 3),
    PARALYSIS(3, 2),
    CRYOKINESIS(4, 1),
    LUCK(5, 2),
    TANK(6, 3),
    BEEFCAKE(7, 3),
    POWERHOUSE(8, 3),
    UNHINGED(9, 5),
    STERN(10, 3),
    FIRST_AID(11, 1),
    HEALER(12, 1),
    NEGOTIATOR(13, 5),
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
    DECOY(25, 3),
    JESTER(26, 4),
    ANGLER(27, 5),
    FARMER(28, 5),
    ENCHANTMENT(29, 2),
    ELECTROKINESIS(30, 1),
    AEROKINESIS(31, 1),
    FRAGOKINESIS(33, 1),
    BERSERKER(34, 2),
    BEASTMASTER(35, 2),
    CHARMER(36, 5),
    DISARMING(37, 3),
    LOOTMASTER(38, 5),
    AQUAPHILE(39, 4),
    TERRAFORMER(41, 3),
    ACIDIC_SPIT(40, 2),
    GUARD(42, 2),
    ESSENCE_BREWER(43, 1),
    BUILDER(44, 4),
    REFINERY(45, 4),
    ABUNDANCE(46, 3),
    ORGANIZATION(47, 5),
    FORTUNATE(48, 3);
    public int id;
    public int weight;
    private static Abilities[] vals = Abilities.values();
    Abilities(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }
    public static Abilities getAbility(int i) {
        return Abilities.vals[i];
    }
}

//List of possible abilities
//Add the default abilities (keep track of this number)
//Add (weighted) possible abilities
//Return string with all abilities (default + pooled)