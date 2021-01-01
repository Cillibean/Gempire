package com.gempire.entities.ai;

import com.gempire.entities.gems.EntityGem;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

public class EntityAIWander extends WaterAvoidingRandomWalkingGoal {
    public EntityGem gem;

    public EntityAIWander(EntityGem creatureIn, double speedIn) {
        super(creatureIn, speedIn);
        this.gem = creatureIn;
    }

    @Override
    public boolean shouldExecute() {
        return this.gem.getMovementType() == 1 && super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.gem.getMovementType() == 1 && super.shouldContinueExecuting() && !this.gem.getNavigator().noPath();
    }
}
