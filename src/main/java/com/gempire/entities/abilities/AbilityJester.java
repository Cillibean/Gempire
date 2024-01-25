package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

import java.util.List;

public class AbilityJester extends Ability implements IIdleAbility {

    public AbilityJester() {
        super("jester", 4);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.jester");
    }

    @Override
    public void execute() {
        List<EntityGem> list = this.holder.level().getEntitiesOfClass(EntityGem.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        if (holder.getMovementType() == 1) {
            for (EntityGem entityGem : list) {
                if (entityGem != holder) {
                    if (holder.abilityTicks == 0) {
                        holder.getNavigation().moveTo(entityGem, 1);
                        holder.lookAt(entityGem, 90F, 90F);
                        if (holder.distanceToSqr(entityGem) < Math.pow(2, 1)) {
                            if (holder.getRebelled()) {
                                entityGem.rebelPoints += 1F;
                                holder.playSound(SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(6).get());
                                holder.abilityTicks = 20 * 60;
                            } else if (entityGem.getOwned()) {
                                if (entityGem.rebelPoints - 0.5f > 0) {
                                    entityGem.rebelPoints -= 0.5F;
                                    holder.playSound(SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(1).get());
                                    holder.abilityTicks = 20 * 60;
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}