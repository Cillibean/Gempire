package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

public class AbilityAmphibian extends Ability implements IEffectAbility {

    public AbilityAmphibian(){
        this.ability = Abilities.AMPHIBIAN;
    }

    @Override
    public MobEffectInstance[] effects() {
        return new MobEffectInstance[]{
                new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 400, 0,false,false), new MobEffectInstance(MobEffects.WATER_BREATHING, 400, 0,false,false)
        };
    }

    @Override
    public boolean hasMultipleEffects() {
        return true;
    }

    @Override
    public MobEffectInstance effect() {
        return null;
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                Player.class
        };
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.amphibian");
    }
}
