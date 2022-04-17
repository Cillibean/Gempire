package com.gempire.entities.projectiles;

import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.protocol.Packet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class IceShardEntity extends ThrowableItemProjectile {

    public IceShardEntity(Level worldIn, LivingEntity throwerIn) {
        super(ModEntities.ICE_SHARD.get(), throwerIn, worldIn);
    }

    public IceShardEntity(EntityType<? extends ThrowableItemProjectile> type, Level worldIn) {
        super(type, worldIn);
    }

    public IceShardEntity(Level worldIn, double x, double y, double z) {
        super(ModEntities.ICE_SHARD.get(), x, y, z, worldIn);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.ICE_SHARD.get();
    }

    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        p_213868_1_.getEntity().hurt(DamageSource.MAGIC, 1.0F);
    }


    private ParticleOptions makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }


    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
