package com.gempire.entities.other;

import com.gempire.entities.ai.SpecterFindPlayer;
import com.gempire.entities.ai.SpecterRunWhenLookedAt;
import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EntitySpecter extends Monster {

    public EntitySpecter(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    public void registerGoals() {
        super.registerGoals();
        //this.goalSelector.addGoal(0, new SpecterRunWhenLookedAt(this));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new SpecterFindPlayer(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkPlayerDistance));
    }

    public boolean checkPlayerDistance(LivingEntity entity) {
        AABB aabb = this.getBoundingBox().inflate(2);
        List<Player> playerList = this.level().getEntitiesOfClass(Player.class, aabb);
        return playerList.contains(entity);
    }

    public boolean isLookingAtMe(Player p_32535_) {
        Vec3 vec3 = p_32535_.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(this.getX() - p_32535_.getX(), this.getEyeY() - p_32535_.getEyeY(), this.getZ() - p_32535_.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? p_32535_.hasLineOfSight(this) : false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return ModSounds.SPECTER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        if (this.random.nextInt(2) == 0) {
            return ModSounds.SPECTER_DEATH1.get();
        }
        return ModSounds.SPECTER_DEATH2.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SPECTER_AMBIENT.get();
    }
}
