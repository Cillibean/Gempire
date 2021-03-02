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
    RIGHT_EYE_DEFORMED(22),
    TOP_OF_HEAD(23),
    LEFT_ANKLE(24),
    RIGHT_ANKLE(25),
    LEFT_PALM(26),
    RIGHT_PALM(27),
    LEFT_THIGH(28),
    RIGHT_THIGH(29),
    LEFT_ARM(30),
    RIGHT_ARM(31);
    public int id;
    private static GemPlacements[] vals = GemPlacements.values();
    GemPlacements(int id) {
        this.id = id;
    }
    public static GemPlacements getPlacement(int i) {
        return GemPlacements.vals[i];
    }
}
