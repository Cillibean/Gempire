package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityFirstAid extends Ability implements IEffectAbility {

    public AbilityFirstAid() {
        this.ability = Abilities.FIRST_AID;
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.INSTANT_HEALTH, 40, 1);
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                EntityGem.class, PlayerEntity.class
        };
    }

    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.first_aid");
    }
}
