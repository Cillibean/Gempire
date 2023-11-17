package com.gempire.systems.board;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardUtil {
    public HashMap<String, ArrayList<Integer>> tierHash = new HashMap();

    public void createTierMap(String string, int rarity, int gems, int rewardMultiplier, int rewardRarity, int crack, int shatter) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(rarity);
        list.add(gems);
        list.add(rewardMultiplier);
        list.add(rewardRarity);
        list.add(crack);
        list.add(shatter);
        tierHash.put(string, list);
    }

    public void registerTiers() {
        createTierMap("common", 1, 1, 1, 1, 0, 0);
        createTierMap("uncommon", 2, 3, 2, 2, 30, 15);
        createTierMap("rare", 3, 5, 3, 3, 50, 30);
    }

    public Mission generateMission() {

        return null;
    }
}
