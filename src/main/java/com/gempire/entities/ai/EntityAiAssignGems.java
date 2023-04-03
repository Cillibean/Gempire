package com.gempire.entities.ai;

import com.gempire.entities.abilities.AbilityKindergardener;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpodumene;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;

public class EntityAiAssignGems extends Goal {
    public EntityGem thisGem;
    public double speedIn;
    public EntityGem gemToTame;
    public EntityAiAssignGems(EntityGem entityIn, double speedIn) {
        this.speedIn = speedIn;
        this.thisGem = entityIn;
    }


    @Override
    public boolean canUse() {
        for(Ability ability : this.thisGem.getAbilityPowers()) {
            if (ability instanceof AbilityKindergardener) {
                List<LivingEntity> list = this.thisGem.level.getEntitiesOfClass(LivingEntity.class, this.thisGem.getBoundingBox().inflate(24.0D, 10.0D, 24.0D));
                for (LivingEntity gem : list) {
                    if (gem instanceof EntityGem) {
                        if (!((EntityGem) gem).getOwned() && !((EntityGem) gem).getRebelled()) {
                            this.gemToTame = (EntityGem) gem;
                        }
                    }
                }
            }
        }
        return this.gemToTame != null && this.thisGem.distanceToSqr(this.gemToTame) > Math.pow(3, 2);
    }

    @Override
    public boolean canContinueToUse() {
        return this.gemToTame != null && !this.gemToTame.isDeadOrDying() && !this.gemToTame.getOwned();
    }

    @Override
    public void start() {
        super.start();
        this.thisGem.getNavigation().moveTo(this.gemToTame.getX(), this.gemToTame.getY(), this.gemToTame.getZ(), this.speedIn);
    }

    @Override
    public void stop() {
        this.gemToTame = null;
        this.thisGem.getNavigation().stop();
    }
}
