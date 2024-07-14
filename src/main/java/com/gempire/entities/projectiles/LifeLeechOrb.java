package com.gempire.entities.projectiles;

import com.gempire.init.ModEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class LifeLeechOrb extends AbstractHurtingProjectile {
    public LifeLeechOrb(EntityType<? extends LifeLeechOrb> entity, Level world) {
        super(entity, world);
    }

    public LifeLeechOrb(Level p_36903_, LivingEntity p_36904_) {
        super(ModEntities.LIFE_LEECH.get(), p_36903_);
        this.setOwner(p_36904_);
    }

    protected void onHit(HitResult result) {
        super.onHit(result);
            if (!this.level().isClientSide) {
                /*
                AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
                Entity entity = this.getOwner();
                if (entity instanceof LivingEntity) {
                    areaeffectcloud.setOwner((LivingEntity)entity);
                }

                areaeffectcloud.setParticle(ParticleTypes.DRAGON_BREATH);
                areaeffectcloud.setRadius(3.0F);
                areaeffectcloud.setDuration(600);
                areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
                areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));


                this.level().levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
                this.level().addFreshEntity(areaeffectcloud);
                */

                LifeReturnOrb acidSpit = new LifeReturnOrb(this.level(), (LivingEntity) this.getOwner());
                if (result instanceof EntityHitResult) {
                    acidSpit.healing = true;
                    ((EntityHitResult) result).getEntity().hurt(this.damageSources().mobAttack((LivingEntity) this.getOwner()), 8);
                }
                double d4 = this.getOwner().getEyeY() - (double) 1.1F;
                double d1 = this.getOwner().getX() - this.getX();
                double d2 = d4 - acidSpit.getY();
                double d3 = this.getOwner().getZ() - this.getZ();
                float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
                acidSpit.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
                this.level().addFreshEntity(acidSpit);

                this.discard();
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
