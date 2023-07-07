package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityGarnet;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.phys.AABB;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.UUID;

public class EntityAIWander extends WaterAvoidingRandomStrollGoal {
    public EntityGem gem;

    public EntityAIWander(EntityGem creatureIn, double speedIn) {
        super(creatureIn, speedIn);
        this.gem = creatureIn;
    }

    @Override
    public void start() {
        List<EntityGem> list = this.gem.level.getEntitiesOfClass(EntityGem.class, this.gem.getBoundingBox().inflate(7.0D, 5.0D, 7.0D));
        for (EntityGem garnet : list) {
            if (garnet.getOwned() && !garnet.getRebelled()) {
                for (UUID uuid: gem.OWNERS) {
                    if (garnet.isOwner(uuid)) {

                    }
                }
            }
        }
        super.start();
    }

    @Override
    public boolean canUse() {
        return this.gem.getMovementType() == 1 && super.canUse() && this.gem.getSludgeAmount() < 5;
    }

    @Override
    public boolean canContinueToUse() {
        return this.gem.getMovementType() == 1 && super.canContinueToUse() && !this.gem.getNavigation().isDone();
    }
}
