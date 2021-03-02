package com.gempire.util;

public enum Abilities {
    NO_ABILITY(0, 10),
    KNOCKBACK(1, 7),
    PYROKINESIS(2, 3),
    PARALYSIS(3, 2);
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