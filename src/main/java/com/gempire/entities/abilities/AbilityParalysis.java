package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;

import java.util.List;
import java.util.Random;

public class AbilityParalysis extends Ability implements IMeleeAbility, IEffectAbility, IEmotionalAbility, IViolentAbility {

    public AbilityParalysis() {
        this.ability = Abilities.PARALYSIS;
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.SLOWNESS, 20 * 5, 99);
    }

    @Override
    public void fight(Entity entityIn, double damage) {
        CreatureEntity entity = null;
        if(entityIn instanceof CreatureEntity){
            entity = (CreatureEntity) entityIn;
        }
        else{
            return;
        }
        Random rand = new Random();
        if (rand.nextInt(10) == 0) entity.addPotionEffect(this.effect());
    }

    @Override
    public void outburst() {
        List<CreatureEntity> entities = this.holder.world.<CreatureEntity>getEntitiesWithinAABB(CreatureEntity.class, this.holder.getBoundingBox().grow(20.0D, 10.0D, 20.0D));
        for(CreatureEntity entity : entities){
            if(entity instanceof EntityGem){
                continue;
            }
            entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * 15, 99));
        }
    }

    @Override
    public boolean playerOnly() {
        return false;
    }
}
