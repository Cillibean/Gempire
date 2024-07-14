package com.gempire.entities.projectiles;

import com.gempire.entities.bosses.EntityBoss;
import com.gempire.entities.bosses.base.EntityFuchsiaPaladin;
import com.gempire.init.ModEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LifeReturnOrb extends AbstractHurtingProjectile {
    public LifeReturnOrb(EntityType<? extends LifeReturnOrb> entity, Level world) {
        super(entity, world);
    }

    public LifeReturnOrb(Level p_36903_, LivingEntity p_36904_) {
        super(ModEntities.LIFE_RETURN.get(), p_36903_);
        this.setOwner(p_36904_);
    }

    public boolean healing = false;

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (result.getType() == HitResult.Type.ENTITY) {
            if (!this.level().isClientSide) {
                if (result instanceof EntityHitResult) {
                    if (((EntityHitResult) result).getEntity() instanceof EntityBoss) {
                        EntityBoss boss = (EntityBoss) ((EntityHitResult) result).getEntity();
                        if (healing) boss.heal(80);
                        if (boss instanceof EntityFuchsiaPaladin) ((EntityFuchsiaPaladin) boss).leeching = false;
                        this.discard();
                    }
                }
            }

        }
    }


    public boolean isPickable() {
        return false;
    }

    public boolean hurt(DamageSource p_36910_, float p_36911_) {
        return false;
    }

    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.CHERRY_LEAVES;
    }

    protected boolean shouldBurn() {
        return false;
    }
}
