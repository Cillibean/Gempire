package com.gempire.aura;

public class ClientAuraData {
    private static int playerAura;

    public static void set(int aura) {
        ClientAuraData.playerAura = aura;
    }

    public static int getPlayerAura() {
        return playerAura;
    }
}
