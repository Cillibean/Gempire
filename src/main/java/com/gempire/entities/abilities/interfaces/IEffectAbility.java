package com.gempire.entities.abilities.interfaces;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;

public interface IEffectAbility {
    EffectInstance effect();
    default Class<LivingEntity>[] applicableEntities(){
        return new Class[]{
                PlayerEntity.class
        };
    }
    default boolean hasMultipleEffects(){
        return false;
    }
    default EffectInstance[] effects(){
        return new EffectInstance[]{};
    }
    default boolean isEntityApplicable(LivingEntity subject){
        Class<LivingEntity>[] classes = this.applicableEntities();
        for(int i = 0; i < classes.length; i++) {
            Class tclass = classes[i];
            if(tclass.isAssignableFrom(subject.getClass()) || subject.getClass().getCanonicalName().equals(tclass.getCanonicalName())){
                return true;
            }
        }
        return false;
    }
}
