package com.gempire.entities.abilities;

import com.gempire.blocks.IceSpikeBlock;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class AbilityFragokinesis extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility, IIdleAbility {
    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        if (cooldown <= 0) {
            if (!target.level.isClientSide) {
                Explosion.BlockInteraction explosion$blockinteraction = Explosion.BlockInteraction.NONE;
                target.level.explode(target, target.getX(), target.getY(), target.getZ(), 1f, explosion$blockinteraction);
                cooldown = 200;
            }
        }
    }

    @Override
    public Goal goal() {
        return new RangedAttackGoal(this.holder, 1.25D, 20, 10.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.fragokinesis");
    }

    @Override
    public void execute() {
        cooldown--;
    }
}
