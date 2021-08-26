package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public interface IAlchemyAbility {
    default int maxTime(){
        return 200;
    }
    Item input();
    Item output();
    default boolean doSpecialActionOnInput(@Nullable PlayerEntity player){
        return true;
    }
}
