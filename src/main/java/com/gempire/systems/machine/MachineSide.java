package com.gempire.systems.machine;

public enum MachineSide {
    BOTTOM(0, 1),
    TOP(1, 0),
    BACK(2, 3),
    FRONT(3, 2),
    LEFT(4, 5),
    RIGHT(5, 4);
    public int id;
    public int opposite;
    private static MachineSide[] vals = MachineSide.values();
    MachineSide(int id, int opposite) {
        this.id = id;
        this.opposite = opposite;
    }
    public static MachineSide getSide(int i) {
        return MachineSide.vals[i];
    }
    public static MachineSide getOpposite(int i) {
        return MachineSide.vals[MachineSide.vals[i].opposite];
    }
}
