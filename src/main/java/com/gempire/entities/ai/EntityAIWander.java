package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class EntityAIWander extends WaterAvoidingRandomStrollGoal {
    public EntityGem gem;

    public EntityAIWander(EntityGem creatureIn, double speedIn) {
        super(creatureIn, speedIn);
        this.gem = creatureIn;
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
