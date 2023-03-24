package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AbilityHealer extends Ability implements IEffectAbility {

    public AbilityHealer() {
        this.ability = Abilities.HEALER;
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.REGENERATION, 100, 0);
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                EntityGem.class, Player.class
        };
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.healer");
    }
}
