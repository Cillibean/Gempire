package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAreaAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.List;
import java.util.UUID;

public class AbilityStern extends Ability implements IAreaAbility, IEffectAbility {

    public AbilityStern() {
        this.ability = Abilities.STERN;
    }

    @Override
    public void AOeffect() {

    }

    @Override
    public void AOeffect(Entity entity) {

    }

    @Override
    public void AOeffect(Entity entity, UUID id) {
        if (entity instanceof EntityGem) {
            if(((EntityGem)entity).getOwnerID() == id) ((EntityGem)entity).focusLevel = 1;
            System.out.println("Gem focused");
        }
    }


    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.NIGHT_VISION, 200, 1);
    }

    @Override
    public boolean playerOnly() {
        return false;
    }
}
