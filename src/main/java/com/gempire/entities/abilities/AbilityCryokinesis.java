package com.gempire.entities.abilities;

import com.gempire.blocks.IceSpikeBlock;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITargetAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityCryokinesis extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility, ITargetAbility {

    public AbilityCryokinesis() {
        this.ability = Abilities.CRYOKINESIS;
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        SnowballEntity snowballentity = new SnowballEntity(this.holder.world, this.holder);
        double d0 = target.getPosYEye() - (double) 1.1F;
        double d1 = target.getPosX() - this.holder.getPosX();
        double d2 = d0 - snowballentity.getPosY();
        double d3 = target.getPosZ() - this.holder.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
        this.holder.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.holder.getRNG().nextFloat() * 0.4F + 0.8F));
        this.holder.world.addEntity(snowballentity);


        if(this.holder.getRNG().nextInt(5) == 0) {
            if(!(target.world.getBlockState(target.getPosition().down()).getBlock() instanceof AirBlock) &&
                    !(target.world.getBlockState(target.getPosition().down()).getBlock() instanceof BushBlock)&&
                    (target.world.getBlockState(target.getPosition().up()).getBlock() instanceof AirBlock)) {
                float f5 = (target.world.rand.nextFloat() - 0.5F) * 8.0F;
                float f1 = (target.world.rand.nextFloat() - 0.5F) * 4.0F;
                float f2 = (target.world.rand.nextFloat() - 0.5F) * 8.0F;
                target.world.addParticle(ParticleTypes.EXPLOSION, target.getPosX() + (double) f5, target.getPosY() + 2.0D + (double) f1, target.getPosZ() + (double) f2, 0.0D, 0.0D, 0.0D);
                this.holder.world.setBlockState(target.getPosition(), this.blockToSummon());
                ((IceSpikeBlock) this.holder.world.getBlockState(target.getPosition()).getBlock()).onBlockPlacedBy(this.holder.world, target.getPosition(),
                        this.holder.world.getBlockState(target.getPosition()), this.holder, new ItemStack(ModItems.ICE_SPIKE.get()));
            }
        }
    }

    @Override
    public Goal goal() {
        return new RangedAttackGoal((IRangedAttackMob) this.holder, 1.25D, 20, 10.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public BlockState blockToSummon() {
        return ModBlocks.ICE_SPIKE.get().getDefaultState().with(IceSpikeBlock.HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public int chanceToSummon() {
        return ((EntityGem)this.holder).isFocused() ? 1 : ((EntityGem)this.holder).focusLevel;
    }
    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.cryokinesis");
    }
}
