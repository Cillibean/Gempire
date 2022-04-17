package com.gempire.entities.abilities.interfaces;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;

public interface IAlchemyAbility {
    default int maxTime(){
        return 200;
    }
    Item input();
    Item output();
    default Item consume(){
        return Items.AIR;
    }
    default boolean doSpecialActionOnInput(@Nullable Player player){
        return true;
    }
}
