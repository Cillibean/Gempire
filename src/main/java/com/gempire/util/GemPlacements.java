package com.gempire.util;

public enum GemPlacements {
    FOREHEAD(0),
    LEFT_EYE(1),
    RIGHT_EYE(2),
    LEFT_EAR(3),
    RIGHT_EAR(4),
    NOSE(5),
    LEFT_CHEEK(6),
    RIGHT_CHEEK(7),
    MOUTH(8),
    LEFT_SHOULDER(9),
    RIGHT_SHOULDER(10),
    CHEST(11),
    BACK(12),
    LEFT_HAND(13),
    RIGHT_HAND(14),
    LEFT_SIDE(15),
    RIGHT_SIDE(16),
    BELLY(17),
    LEFT_KNEE(18),
    RIGHT_KNEE(19),
    BACK_OF_HEAD(20),
    LEFT_EYE_DEFORMED(21),
    RIGHT_EYE_DEFORMED(22);
    public int id;
    private static GemPlacements[] vals = GemPlacements.values();
    GemPlacements(int id) {
        this.id = id;
    }
    public static GemPlacements getPlacement(int i) {
        return GemPlacements.vals[i];
    }
}
