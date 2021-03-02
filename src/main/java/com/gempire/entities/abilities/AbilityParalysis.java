package com.gempire.entities.abilities;

import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Explosion;

import java.util.List;
import java.util.Random;

public class AbilityParalysis extends Ability{

    public AbilityParalysis(CreatureEntity holder) {
        super(holder);
        AbilityParalysis.ABILITY = Abilities.KNOCKBACK;
    }

    @Override
    public void Fight(Entity entityIn, double damage) {
        CreatureEntity entity = null;
        if(entityIn instanceof CreatureEntity){
            entity = (CreatureEntity) entityIn;
        }
        else{
            return;
        }
        Random rand = new Random();
        if (rand.nextInt(10) == 0) entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * 5, 99));
    }

    @Override
    public void EmotionalOutburst() {
        List<CreatureEntity> entities = this.holder.world.<CreatureEntity>getEntitiesWithinAABB(CreatureEntity.class, this.holder.getBoundingBox().grow(20.0D, 10.0D, 20.0D));
        for(CreatureEntity entity : entities){
            if(entity instanceof EntityGem){
                continue;
            }
            entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * 15, 99));
        }
    }
}
