package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;

import java.util.List;
import java.util.Random;

public class AbilityParalysis extends Ability implements IMeleeAbility, IEffectAbility, IEmotionalAbility, IViolentAbility {

    public AbilityParalysis() {
        this.ability = Abilities.PARALYSIS;
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 5, 99);
    }

    @Override
    public void fight(Entity entityIn, double damage) {
        PathfinderMob entity = null;
        if(entityIn instanceof PathfinderMob){
            entity = (PathfinderMob) entityIn;
        }
        else{
            return;
        }
        Random rand = new Random();
        if (rand.nextInt(10) == 0) entity.addEffect(this.effect());
    }

    @Override
    public void outburst() {
        List<PathfinderMob> entities = this.holder.level.<PathfinderMob>getEntitiesOfClass(PathfinderMob.class, this.holder.getBoundingBox().inflate(20.0D, 10.0D, 20.0D));
        for(PathfinderMob entity : entities){
            if(entity instanceof EntityGem || ((EntityGem)this.holder).isOwner(entity)){
                continue;
            }
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 15, 99));
        }
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.paralysis");
    }
}
