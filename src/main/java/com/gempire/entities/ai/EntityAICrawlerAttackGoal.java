package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityCrawler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class EntityAICrawlerAttackGoal extends MeleeAttackGoal {
    private EntityCrawler entity;
    private int animTickLength = 20;

    public EntityAICrawlerAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((EntityCrawler) pMob);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (pDistToEnemySqr <= this.getAttackReachSqr(pEnemy) && this.getTicksUntilNextAttack() <= 0) {
            if(entity != null) {
                if (pEnemy instanceof EntityGem) {
                    if (((EntityGem) pEnemy).getSludgeAmount() < 5) {

                    } else {
                        stop();
                    }
                } else {

                }
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
