package com.gempire.entities.abilities;

import com.gempire.blocks.IceSpikeBlock;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;

import java.util.List;

public class AbilityCryokinesis extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility, ITargetAbility, IEmotionalAbility {

    public AbilityCryokinesis() {
        super("cryokinesis", 1);
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        IceShardEntity snowballentity = new IceShardEntity(this.holder.level, this.holder);
        double d0 = target.getEyeY() - (double) 1.1F;
        double d1 = target.getX() - this.holder.getX();
        double d2 = d0 - snowballentity.getY();
        double d3 = target.getZ() - this.holder.getZ();
        float f = Mth.sqrt((float)( d1 * d1 + d3 * d3)) * 0.2F;
        snowballentity.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
        this.holder.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.holder.getRandom().nextFloat() * 0.4F + 0.8F));
        this.holder.level.addFreshEntity(snowballentity);

        this.holder.enemy = target;
        this.holder.enemyDying = true;

        if(this.holder.getRandom().nextInt(5) == 0) {
            if(!(target.level.getBlockState(target.blockPosition().below()).getBlock() instanceof AirBlock) &&
                    !(target.level.getBlockState(target.blockPosition().below()).getBlock() instanceof BushBlock)&&
                    (target.level.getBlockState(target.blockPosition().above()).getBlock() instanceof AirBlock)) {
                float f5 = (target.level.random.nextFloat() - 0.5F) * 8.0F;
                float f1 = (target.level.random.nextFloat() - 0.5F) * 4.0F;
                float f2 = (target.level.random.nextFloat() - 0.5F) * 8.0F;
                target.level.addParticle(ParticleTypes.EXPLOSION, target.getX() + (double) f5, target.getY() + 2.0D + (double) f1, target.getZ() + (double) f2, 0.0D, 0.0D, 0.0D);
                this.holder.level.setBlockAndUpdate(target.blockPosition(), this.blockToSummon());
                ((IceSpikeBlock) this.holder.level.getBlockState(target.blockPosition()).getBlock()).setPlacedBy(this.holder.level, target.blockPosition(),
                        this.holder.level.getBlockState(target.blockPosition()), this.holder, new ItemStack(ModItems.ICE_SPIKE.get()));
            }
        }
    }

    @Override
    public Goal goal() {
        return new RangedAttackGoal((RangedAttackMob) this.holder, 1.25D, 20, 10.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public BlockState blockToSummon() {
        return ModBlocks.ICE_SPIKE.get().defaultBlockState().setValue(IceSpikeBlock.HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public int chanceToSummon() {
        return this.holder.isFocused() ? 1 : this.holder.focusLevel;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.cryokinesis");
    }

    @Override
    public void outburst() {
        if (!this.holder.level.isClientSide) {
             this.holder.level.setBlockAndUpdate(this.holder.getOnPos().above(), Blocks.SNOW.defaultBlockState());
             this.holder.stopGoalsCooldown = 100;
             this.holder.stopGoalsPos = this.holder.getOnPos().above();
             this.holder.stopGoals = true;
        }
    }
}
