package com.gempire.entities.abilities;

import com.gempire.Gempire;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityZilch extends Ability {

    public AbilityZilch() {
        this.ability = Abilities.NO_ABILITY;
    }

    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.zilch");
    }
}
