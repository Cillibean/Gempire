package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class AbilityBeastmaster extends Ability implements IIdleAbility {

    public AbilityBeastmaster() {
        super("beastmaster", 1);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.beastmaster");
    }

    @Override
    public void execute() {
        List<Wolf> list = this.holder.level().getEntitiesOfClass(Wolf.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        List<Wolf> listOwned = new ArrayList<>();
        for (Wolf wolf : list) {
            if (wolf.isTame() && wolf.getOwnerUUID() == holder.getUUID()) {
                listOwned.add(wolf);
            }
        }
        for (Wolf wolf : list) {
            if (!listOwned.contains(wolf) && listOwned.size() < 5) {
                if (!wolf.isTame()) {
                    if (holder.getOwned()) {
                            if (holder.abilityTicks == 0) {
                                holder.getNavigation().moveTo(wolf, 1);
                                holder.lookAt(wolf, 90F, 90F);
                                if (holder.distanceToSqr(wolf) < Math.pow(2, 1)) {
                                    wolf.setTame(true);
                                    wolf.setOwnerUUID(holder.getUUID());
                                    wolf.setInSittingPose(false);
                                    wolf.setOrderedToSit(false);
                                    wolf.getNavigation().stop();
                                    wolf.setTarget(null);
                                    holder.abilityTicks = 20 * 30;
                                    listOwned.add(wolf);
                                }
                            }
                    }
                } else if (holder.getRebelled()) {
                    if (holder.abilityTicks == 0) {
                        if (!wolf.isTame()) {
                            holder.getNavigation().moveTo(wolf, 1);
                            holder.lookAt(wolf, 90F, 90F);
                            if (holder.distanceToSqr(wolf) < Math.pow(2, 1)) {
                                List<Player> list2 = this.holder.level().getEntitiesOfClass(Player.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
                                if (!list2.isEmpty()) {
                                    wolf.setAggressive(true);
                                    wolf.setTarget(list2.get(0));
                                    listOwned.add(wolf);
                                    holder.abilityTicks = 20 * 30;
                                }
                            }
                        }
                    }
                }
            }
        }
        listOwned.removeIf(LivingEntity::isDeadOrDying);
    }
}
