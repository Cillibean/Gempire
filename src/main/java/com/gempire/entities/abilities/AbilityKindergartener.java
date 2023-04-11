package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;

public class AbilityKindergartener extends Ability implements IIdleAbility {

    public AbilityKindergartener(){
        this.ability = Abilities.KINDERGARTENER;
    }
    EntityGem gemToTame;
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.kindergardener");
    }

    @Override
    public void execute() {
        List<LivingEntity> list = this.holder.level.getEntitiesOfClass(LivingEntity.class, this.holder.getBoundingBox().inflate(30.0D, 15.0D, 30.0D));
        if (holder.getOwned()) {
            if (gemToTame == null || gemToTame.getOwned()) {
                for (LivingEntity entity : list) {
                    if (entity instanceof EntityGem) {
                        if (!((EntityGem) entity).getOwned()) {
                            gemToTame = (EntityGem) entity;
                        }
                    }
                }
            } else {
                holder.getNavigation().moveTo(gemToTame, 1);
                if (holder.distanceToSqr(gemToTame) < Math.pow(2, 1)) {
                    if (holder.getRebelled())
                    {
                        gemToTame.rebel();
                    }
                    {
                        gemToTame.OWNERS.addAll(holder.OWNERS);
                        gemToTame.playSound(gemToTame.getInstrument());
                        holder.playSound(holder.getInstrument());
                        gemToTame = null;
                    }
                }
            }
        }
    }
}
