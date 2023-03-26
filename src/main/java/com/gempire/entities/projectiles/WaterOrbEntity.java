package com.gempire.entities.projectiles;

import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;

public class WaterOrbEntity extends ThrowableItemProjectile {
    public WaterOrbEntity(EntityType<? extends WaterOrbEntity> entity, Level world) {
        super(entity, world);
    }

    public WaterOrbEntity(Level worldIn, LivingEntity throwerIn) {
        super(ModEntities.WATER_ORB.get(), throwerIn, worldIn);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.WATER_ORB.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.WATER_ORB.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }



    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(DamageSource.MAGIC, 1.0F);;
        this.kill();
    }

    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for(int i = 0; i < 7; ++i) {
                this.level.addParticle(ParticleTypes.DRIPPING_WATER, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }
}
