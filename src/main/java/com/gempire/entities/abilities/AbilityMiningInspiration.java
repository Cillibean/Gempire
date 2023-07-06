package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AbilityMiningInspiration extends Ability implements IEffectAbility {

    public AbilityMiningInspiration() {
        super(50, 3);
        this.ability = GempireAbilities.MINING;
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.DIG_SPEED, 400, 0,false,false);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.mining");
    }
}
