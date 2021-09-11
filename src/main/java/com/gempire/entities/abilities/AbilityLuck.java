package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityLuck extends Ability implements IEffectAbility {

    public AbilityLuck() {
        this.ability = Abilities.LUCK;
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.LUCK, 400, 3);
    }
    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.luck");
    }
}
