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

public class AbilityStern extends Ability implements IAreaAbility, IEffectAbility {

    public AbilityStern() {
        this.ability = Abilities.STERN;
    }

    @Override
    public void AOeffect() {
        List<Entity> owners = this.holder.world.getEntitiesWithinAABBExcludingEntity(this.holder, this.holder.getBoundingBox().grow(12));
        for (Entity entity : owners) {
            if (entity instanceof EntityGem) {
                ((EntityGem)entity).focusLevel = 1;
            }
            break;
        }
    }


    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.NIGHT_VISION, 200, 1);
    }
}
