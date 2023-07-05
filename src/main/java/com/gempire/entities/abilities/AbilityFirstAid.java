package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.GempireAbilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class AbilityFirstAid extends Ability implements IEffectAbility {

    public AbilityFirstAid() {
        this.ability = GempireAbilities.FIRST_AID;
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.HEAL, 40, 1,false,false);
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                EntityGem.class, Player.class
        };
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.first_aid");
    }
}
