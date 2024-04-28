package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class EntityAIAbominationAttackGoal extends MeleeAttackGoal {
    private EntityAbomination entity;

    public EntityAIAbominationAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((EntityAbomination) pMob);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (pDistToEnemySqr <= this.getAttackReachSqr(pEnemy) && this.getTicksUntilNextAttack() <= 0 && entity != null && pEnemy instanceof EntityGem) {
            if (((EntityGem) pEnemy).getSludgeAmount() >= 5) {
                stop();
            }
        }
        super.checkAndPerformAttack(pEnemy, pDistToEnemySqr);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
