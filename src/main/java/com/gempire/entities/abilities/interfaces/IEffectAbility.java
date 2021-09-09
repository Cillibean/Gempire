package com.gempire.entities.abilities.interfaces;

import net.minecraft.potion.EffectInstance;

public interface IEffectAbility {
    EffectInstance effect();
    boolean playerOnly();
    boolean gemAndPlayerOnly();
    default boolean hasMultipleEffects(){
        return false;
    }
    default EffectInstance[] effects(){
        return new EffectInstance[]{};
    }
}
