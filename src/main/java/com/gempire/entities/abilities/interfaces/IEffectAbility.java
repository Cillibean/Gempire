package com.gempire.entities.abilities.interfaces;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;

public interface IEffectAbility {
    MobEffectInstance effect();
    default Class<LivingEntity>[] applicableEntities(){
        return new Class[]{
                Player.class
        };
    }
    default boolean hasMultipleEffects(){
        return false;
    }
    default MobEffectInstance[] effects(){
        return new MobEffectInstance[]{};
    }
    default boolean isEntityApplicable(LivingEntity subject){
        Class<LivingEntity>[] classes = this.applicableEntities();
        for (Class<LivingEntity> tclass : classes) {
            if (tclass.isAssignableFrom(subject.getClass()) || subject.getClass().getCanonicalName().equals(tclass.getCanonicalName())) {
                return true;
            }
        }
        return false;
    }
}
