package com.gempire.entities.projectiles;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.protocol.Packet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class IceShardEntity extends ThrowableItemProjectile {
    public IceShardEntity(EntityType<? extends IceShardEntity> entity, Level world) {
        super(entity, world);
    }

    public IceShardEntity(Level worldIn, LivingEntity throwerIn) {
        super(ModEntities.ICE_SHARD.get(), throwerIn, worldIn);
    }

    public IceShardEntity(Level worldIn, double x, double y, double z) {
        super(ModEntities.ICE_SHARD.get(), x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ICE_SHARD.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.ICE_SHARD.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (result.getEntity() instanceof EntityGem) {
            if (!((EntityGem) result.getEntity()).getOwned() || ((EntityGem) result.getEntity()).getRebelled()) {
                result.getEntity().hurt(this.damageSources().magic(), 1.0F);
                this.kill();
            }
        } else {
            result.getEntity().hurt(this.damageSources().magic(), 1.0F);
            this.kill();
        }
    }

    private ParticleOptions makeParticle() {
        return ParticleTypes.ITEM_SNOWBALL;
    }


    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
