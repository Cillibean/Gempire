package com.gempire.entities.projectiles;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class AcidSpitEntity extends ThrowableItemProjectile {
    public AcidSpitEntity(EntityType<? extends AcidSpitEntity> entity, Level world) {
        super(entity, world);
    }

    public AcidSpitEntity(Level worldIn, LivingEntity throwerIn) {
        super(ModEntities.ACID_SPIT.get(), throwerIn, worldIn);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.ACID_SPIT.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.ACID_SPIT.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (result.getEntity() instanceof EntityGem) {
            if (!((EntityGem) result.getEntity()).getOwned() || ((EntityGem) result.getEntity()).getRebelled()) {
                result.getEntity().hurt(result.getEntity().damageSources().magic(), 1.0F);;
                ((LivingEntity) result.getEntity()).addEffect(new MobEffectInstance(MobEffects.POISON,50,0));
            }
        }
        if (result.getEntity() instanceof LivingEntity hehe)
        {
            if (hehe.getMobType() == MobType.UNDEAD)
            {
                hehe.hurt(result.getEntity().damageSources().magic(), 1.0F);;
            }
            else
            {
                hehe.hurt(result.getEntity().damageSources().magic(), 1.0F);;
                hehe.addEffect(new MobEffectInstance(MobEffects.POISON,50,0));
            }
            this.kill();
        }
    }

    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for(int i = 0; i < 7; ++i) {
                this.level.addParticle(ParticleTypes.SPIT, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }
}
