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
    FIRST_AID(11, 2),
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
    ESCORT(22, 7),
    COURIER(23, 5),
    SURVEYOR(24, 8),
    TECHNICIAN(25, 7),
    PROSPECTOR(26, 5),
    SPELUNKER(27, 4),
    TORCHBEARER(28, 5),
    DECOY(29, 3),
    JESTER(30, 4),
    FISHERGEM(31, 5),
    FARMER(32, 5),
    ENCHANTMENT(33, 2),
    ELECTROKINESIS(34, 3),
    AEROKINESIS(35, 3),
    AZUR_PYROKINESIS(36, 1),
    FRAGOKINESIS(37, 3),
    SHIELD(38, 3),
    BERSERKER(39, 4),
    BEASTMASTER(40, 3),
    CHARMER(41, 5),
    DISARMING(42, 3),
    LOOTMASTER(43, 5),
    AQUAPHILE(44, 4),
    TERRAFORMER(45, 3),
    WATER_WALKING(46, 2),
    ALCHEMIST(47, 4);
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