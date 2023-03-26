package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AbilityBerserker extends Ability implements IIdleAbility {

    public AbilityBerserker(){
        this.ability = Abilities.BERSERKER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.berserker");
    }

    @Override
    public void execute() {
        if (this.holder.getHealth() <= (this.holder.getMaxHealth()/100)*30){
            this.holder.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300,0));
            this.holder.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300,0));
        }
    }
}
