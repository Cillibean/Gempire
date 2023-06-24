package com.gempire.entities.abilities;

import com.gempire.blocks.IceSpikeBlock;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITargetAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.ai.EntityAIBowAttackGoal;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class AbilityArcher extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility {

    public AbilityArcher() {
        this.ability = Abilities.ARCHER;
    }

    @Override
    public Goal goal() {
        return new EntityAIBowAttackGoal(this.holder, 1.25D, 20, 15.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.archer");
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {

    }
}
