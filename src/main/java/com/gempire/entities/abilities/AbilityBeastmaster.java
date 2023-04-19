package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class AbilityBeastmaster extends Ability implements IIdleAbility {

    public AbilityBeastmaster() {
        this.ability = Abilities.BEASTMASTER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.beastmaster");
    }

    @Override
    public void execute() {
        List<Wolf> list = this.holder.level.getEntitiesOfClass(Wolf.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        List<Wolf> tamedList = new ArrayList<Wolf>();
        for (Wolf wolf : list) {
            if (!tamedList.contains(wolf)) {
                if (holder.getOwned()) {
                    if (holder.currentPlayer != null) {
                        if (holder.abilityTicks == 0) {
                            holder.getNavigation().moveTo(wolf, 1);
                            holder.lookAt(wolf, 90F, 90F);
                            if (holder.distanceToSqr(wolf) < Math.pow(2, 1)) {
                                wolf.setTame(true);
                                wolf.setOwnerUUID(holder.getUUID());
                                wolf.setInSittingPose(false);
                                wolf.setOrderedToSit(false);
                                //wolf.setCollarColor((DyeColor) this.holder.getOutfitColor());
                                wolf.setJumping(false);
                                wolf.getNavigation().stop();
                                wolf.setTarget(holder.getTarget());
                                wolf.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
                                wolf.setHealth(20.0F);
                                wolf.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
                                holder.level.broadcastEntityEvent(wolf, (byte)7);
                                holder.abilityTicks = 20 * 30;
                                tamedList.add(wolf);
                            }
                        }
                    }
                }
            } else if (holder.getRebelled()) {
                if (holder.abilityTicks == 0) {
                    if (!wolf.isTame()) {
                        holder.getNavigation().moveTo(wolf, 1);
                        holder.lookAt(wolf, 90F, 90F);
                        if (holder.distanceToSqr(wolf) < Math.pow(2, 1)) {
                            List<Player> list2 = this.holder.level.getEntitiesOfClass(Player.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
                            if (!list2.isEmpty()) {
                                wolf.setAggressive(true);
                                wolf.setTarget(list2.get(0));
                                holder.abilityTicks = 20 * 30;
                            }
                        }
                    }
                }
            }
        }
    }
}
