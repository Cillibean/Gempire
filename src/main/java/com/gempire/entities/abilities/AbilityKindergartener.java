package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class AbilityKindergartener extends Ability implements IIdleAbility {

    public AbilityKindergartener(){
        super(49, 3);
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
                holder.lookAt(gemToTame,90F,90F);
                if (holder.distanceToSqr(gemToTame) < Math.pow(2, 1)) {
                    if (holder.getRebelled() && !gemToTame.getRebelled())
                    {
                        gemToTame.rebel();
                        gemToTame = null;
                    }
                    else if (!gemToTame.getRebelled())
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
