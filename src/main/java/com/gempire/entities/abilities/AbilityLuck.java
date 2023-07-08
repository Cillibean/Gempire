package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.PathfinderMob;

import java.util.List;

public class AbilityLuck extends Ability implements IEffectAbility, IEmotionalAbility {

    public AbilityLuck() {
        super(5, 2);
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.LUCK, 400, 0,false,false);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.luck");
    }

    @Override
    public void outburst() {
        List<PathfinderMob> entities = this.holder.level.<PathfinderMob>getEntitiesOfClass(PathfinderMob.class, this.holder.getBoundingBox().inflate(20.0D, 10.0D, 20.0D));
        for(PathfinderMob entity : entities){
            if(entity instanceof EntityGem || this.holder.isOwner(entity.getUUID())){
                continue;
            }
            entity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100, 0,false,false));
        }
    }
}
