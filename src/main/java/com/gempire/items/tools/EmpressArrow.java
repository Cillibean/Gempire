package com.gempire.items.tools;

import com.gempire.init.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EmpressArrow extends AbstractArrow {
    private int duration = 200;

    public EmpressArrow(EntityType<? extends SpectralArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public EmpressArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityType.ARROW, p_37420_, p_37419_);
    }

    public EmpressArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityType.ARROW, p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    protected void doPostHurtEffects(LivingEntity p_37422_) {
        super.doPostHurtEffects(p_37422_);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.GLOWING, this.duration, 0);
        MobEffectInstance mobeffectinstance2 = new MobEffectInstance(ModEffects.PARALYSIS.get(), this.duration/2, 0);
        p_37422_.addEffect(mobeffectinstance, this.getEffectSource());
        p_37422_.addEffect(mobeffectinstance2, this.getEffectSource());
    }

    public void readAdditionalSaveData(CompoundTag p_37424_) {
        super.readAdditionalSaveData(p_37424_);
        if (p_37424_.contains("Duration")) {
            this.duration = p_37424_.getInt("Duration");
        }

    }

    public void addAdditionalSaveData(CompoundTag p_37426_) {
        super.addAdditionalSaveData(p_37426_);
        p_37426_.putInt("Duration", this.duration);
    }
}
