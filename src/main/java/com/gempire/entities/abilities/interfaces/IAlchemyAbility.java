package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

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
    default boolean doSpecialActionOnInput(@Nullable PlayerEntity player){
        return true;
    }
}
