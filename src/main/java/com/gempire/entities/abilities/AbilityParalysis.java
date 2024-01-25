package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Random;

public class AbilityParalysis extends Ability implements IMeleeAbility, IEffectAbility, IEmotionalAbility, IViolentAbility {

    public AbilityParalysis() {
        super("paralysis", 2);
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(ModEffects.PARALYSIS.get(), 20 * 3, 1, false, false);
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        PathfinderMob entity = null;
        if(entityIn instanceof PathfinderMob){
            entity = (PathfinderMob) entityIn;
        }
        else{
            return;
        }
        Random rand = new Random();
        if (rand.nextInt(5) == 0) entity.addEffect(this.effect());
    }

    @Override
    public void outburst() {
        List<PathfinderMob> entities = this.holder.level().<PathfinderMob>getEntitiesOfClass(PathfinderMob.class, this.holder.getBoundingBox().inflate(20.0D, 10.0D, 20.0D));
        for(PathfinderMob entity : entities){
            if(entity instanceof EntityGem || this.holder.isOwner(entity.getUUID())){
                continue;
            }
            entity.addEffect(effect());
        }
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.paralysis");
    }
}
