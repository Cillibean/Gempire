package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LootingLevelEvent;

public class AbilityLootmaster extends Ability implements IViolentAbility, IMeleeAbility {

    public AbilityLootmaster() {
        super(37, 4);
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        if (entityIn.isDeadOrDying()) {
            if (entityIn.getLastHurtByMob() == this.holder) {
                if (this.holder.isPrimary())
                    new LootingLevelEvent(entityIn, this.holder.damageSources().mobAttack(this.holder), 4);
                else if (!this.holder.isDefective())
                    new LootingLevelEvent(entityIn, this.holder.damageSources().mobAttack(this.holder), 2);
            }
        }
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.lootmaster");
    }

}
