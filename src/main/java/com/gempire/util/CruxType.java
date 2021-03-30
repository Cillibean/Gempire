package com.gempire.util;

public enum CruxType {
    MINERAL(0),
    ORGANIC(1),
    INORGANIC(2),
    OTHER(3);
    public int id;
    private static CruxType[] vals = CruxType.values();
    CruxType(int id) {
        this.id = id;
    }
    public static CruxType getAbility(int i) {
        return CruxType.vals[i];
    }
}

//List of possible abilities
//Add the default abilities (keep track of this number)
//Add (weighted) possible abilities
//Return string with all abilities (default + pooled)