package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.util.GempireAbilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AbilityAmphibian extends Ability implements IEffectAbility {

    public AbilityAmphibian(){
        this.ability = GempireAbilities.AMPHIBIAN;
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
