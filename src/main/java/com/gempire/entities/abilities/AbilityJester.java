package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class AbilityJester extends Ability implements IIdleAbility {

    public AbilityJester() {
        this.ability = Abilities.JESTER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.jester");
    }

    @Override
    public void execute() {
        List<EntityGem> list = this.holder.level.getEntitiesOfClass(EntityGem.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        if (holder.getOwned()) {
            for (EntityGem entityGem : list) {
                if (entityGem != holder) {
                    if (holder.jesterTicks == 0) {
                        holder.getNavigation().moveTo(entityGem, 1);
                        holder.lookAt(entityGem, 90F, 90F);
                        if (holder.distanceToSqr(entityGem) < Math.pow(2, 1)) {
                            if (holder.getRebelled()) {
                                if (entityGem.rebelPoints <= 6) {
                                    entityGem.rebelPoints = 6;
                                    holder.playSound(SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(6));
                                    holder.jesterTicks = 20 * 60;
                                }
                            } else {
                                if (entityGem.rebelPoints <= 3) {
                                    entityGem.rebelPoints = 0.1F;
                                    holder.playSound(SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(1));
                                    holder.jesterTicks = 20 * 60;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
