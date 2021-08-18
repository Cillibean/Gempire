package com.gempire.entities.abilities.interfaces;

import net.minecraft.item.Item;

public interface IAlchemyAbility {
    default int maxTime(){
        return 200;
    }
    Item input();
    Item output();
    default boolean doSpecialActionOnInput(){
        return true;
    }
}
