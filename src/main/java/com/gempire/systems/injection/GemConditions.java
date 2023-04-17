package com.gempire.systems.injection;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;

public class GemConditions {
    public ArrayList<Crux> cruxes;
    public String essences;
    public Item primer = Items.AIR;
    public float temperatureMin = .7f;
    public float temperatureMax = 1.4f;
    public double rarity = 1;
    public int chromaColour;

    public GemConditions(ArrayList<Crux> cruxes, String essences, Item primer, float temperatureMin, float temperatureMax){
        this.cruxes = cruxes;
        this.essences = essences;
        this.primer = primer;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public GemConditions(ArrayList<Crux> cruxes, String essences, Item primer, float temperatureMin, float temperatureMax, double rarity){
        this.cruxes = cruxes;
        this.essences = essences;
        this.primer = primer;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.rarity = 1 / rarity;
    }

    public GemConditions(ArrayList<Crux> cruxes, String essences, Item primer){
        this.cruxes = cruxes;
        this.essences = essences;
        this.primer = primer;
    }

    public GemConditions(ArrayList<Crux> cruxes, String essences){
        this.cruxes = cruxes;
        this.essences = essences;
    }
}
