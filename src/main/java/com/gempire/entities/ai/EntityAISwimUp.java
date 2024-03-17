package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.phys.Vec3;

public class EntityAISwimUp extends Goal {
    private final EntityGem drowned;
    private final double speedModifier;
    private final int seaLevel;
    private boolean stuck;

    public EntityAISwimUp(EntityGem p_32440_, double p_32441_, int p_32442_) {
        this.drowned = p_32440_;
        this.speedModifier = p_32441_;
        this.seaLevel = p_32442_;
    }

    public boolean canUse() {
        return !this.drowned.level().isDay() && this.drowned.isInWater() && this.drowned.getY() < (double)(this.seaLevel - 2);
    }

    public boolean canContinueToUse() {
        return this.canUse() && !this.stuck;
    }

    public void tick() {
        if (this.drowned.getY() < (double)(this.seaLevel - 1) && (this.drowned.getNavigation().isDone())) {
            Vec3 vec3 = DefaultRandomPos.getPosTowards(this.drowned, 4, 8, new Vec3(this.drowned.getX(), (double)(this.seaLevel - 1), this.drowned.getZ()), (double)((float)Math.PI / 2F));
            if (vec3 == null) {
                this.stuck = true;
                return;
            }

            this.drowned.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, this.speedModifier);
        }

    }

    public void start() {
        this.stuck = false;
    }

    public void stop() {

    }
}
