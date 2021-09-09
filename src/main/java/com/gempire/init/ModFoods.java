package com.gempire.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {
    public static final Food SPODUMENE_PIECE = (new Food.Builder()).setAlwaysEdible().fastToEat().hunger(0).saturation(0).effect(()->{
        return new EffectInstance(Effects.DOLPHINS_GRACE, 18000, 4);
    }, 1).effect(()->{
        return new EffectInstance(Effects.WATER_BREATHING, 18000, 4);
    }, 1).effect(()->{
        return new EffectInstance(Effects.HUNGER, 400);
    }, 0.005F).build();
}
