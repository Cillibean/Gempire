package com.gempire.entities.abilities.interfaces;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Random;

public interface ICraftingAbility {
    ArrayList<Item> input = new ArrayList<>();
    ArrayList<Item> input2 = new ArrayList<>();
    ArrayList<Item> output = new ArrayList<>();
    void setup();
    Random random = new Random();
}
