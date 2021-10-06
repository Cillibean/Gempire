package com.gempire.util;

public enum Abilities {
    NO_ABILITY(0, 10),
    KNOCKBACK(1, 6),
    PYROKINESIS(2, 3),
    PARALYSIS(3, 2),
    CRYOKINESIS(4, 3),
    LUCK(5, 2),
    TANK(6, 3),
    BEEFCAKE(7, 3),
    POWERHOUSE(8, 3),
    UNHINGED(9, 5),
    STERN(10, 3),
    FIRST_AID(11, 1),
    HEALER(12, 1),
    NEGOTIATOR(13, 5),
    SEEKER(14, 5),
    RECYCLER(15, 7),
    CRAFTER(16, 4),
    METALSMITH(17, 3),
    HYDROKINESIS(18, 5),
    AMPHIBIAN(19, 4),
    VEHICLE(20, 2),
    SCOUT(21, 6),
    COURIER(22, 5),
    SURVEYOR(23, 8),
    TECHNICIAN(24, 7),
    PROSPECTOR(25, 5),
    SPELUNKER(26, 4),
    TORCHBEARER(27, 5),
    DECOY(28, 3),
    JESTER(29, 4),
    ANGLER(30, 5),
    FARMER(31, 5),
    ENCHANTMENT(32, 2),
    ELECTROKINESIS(33, 3),
    AEROKINESIS(34, 3),
    AZUR_PYROKINESIS(35, 1),
    FRAGOKINESIS(36, 3),
    SHIELD(37, 3),
    BERSERKER(38, 4),
    BEASTMASTER(39, 3),
    CHARMER(40, 5),
    DISARMING(41, 3),
    LOOTMASTER(42, 5),
    AQUAPHILE(43, 4),
    TERRAFORMER(44, 3),
    WATER_WALKING(45, 2),
    ALCHEMIST(46, 4),
    GUARD(47, 2),
    ESSENCE_BREWER(48, 1);
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