package com.gempire.util;

public enum PaletteType {
    GEM("gem"),
    SKIN("skin"),
    HAIR("hair"),
    MARKINGS("marking"),
    MARKINGS_2("marking_2");

    public String type;
    private static PaletteType[] vals = PaletteType.values();
    PaletteType(String id) {
        this.type = id;
    }
    public String getType(){
        return this.type;
    }
    public static PaletteType getPaletteType(int i) {
        return PaletteType.vals[i];
    }
}
