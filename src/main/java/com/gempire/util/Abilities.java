package com.gempire.util;

public enum Abilities {
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
    TINKERER(52,4);

    public final int id;
    public final int weight;
    private static final Abilities[] vals = Abilities.values();
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