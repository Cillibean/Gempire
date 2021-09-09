package com.gempire.systems.injection;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class GemConditions {
    public ArrayList<Crux> cruxes;
    public String essences;
    public Item primer = Items.AIR;
    public int tier = 1;
    public float temperatureMin = .7f;
    public float temperatureMax = 1.4f;
    public double rarity = 1;

    public GemConditions(ArrayList<Crux> cruxes, String essences, Item primer, int tier, float temperatureMin, float temperatureMax){
        this.cruxes = cruxes;
        this.essences = essences;
        this.primer = primer;
        this.tier = tier;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public GemConditions(ArrayList<Crux> cruxes, String essences, Item primer, int tier, float temperatureMin, float temperatureMax, double rarity){
        this.cruxes = cruxes;
        this.essences = essences;
        this.primer = primer;
        this.tier = tier;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.rarity = 1 / rarity;
    }

    public GemConditions(ArrayList<Crux> cruxes, String essences, Item primer, int tier){
        this.cruxes = cruxes;
        this.essences = essences;
        this.primer = primer;
        this.tier = tier;
    }

    public GemConditions(ArrayList<Crux> cruxes, String essences, int tier){
        this.cruxes = cruxes;
        this.essences = essences;
        this.tier = tier;
    }
}
