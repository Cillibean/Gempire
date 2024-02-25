package com.gempire.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class LarimarStatueBlock extends Block {
    int tier;
    int timer;

    public LarimarStatueBlock(Properties p_49795_, int tier) {
        super(p_49795_);
        this.tier = tier;
        timer = 2400;
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (timer != 0) {
            timer--;
            if (tier == 0) {
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(14.0D, 8.0D, 14.0D));
                for (LivingEntity entity : list) {
                    if (entity.getClassification(true) == MobCategory.MONSTER) {
                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                    }
                }
            }
            if (tier == 1) {
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(14.0D, 8.0D, 14.0D));
                for (LivingEntity entity : list) {
                    if (entity.getClassification(true) == MobCategory.MONSTER) {
                        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100,1));
                    }
                }
            }
            if (tier == 2) {
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(14.0D, 8.0D, 14.0D));
                for (LivingEntity entity : list) {
                    if (entity.getClassification(true) == MobCategory.MONSTER) {
                        entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100,1));
                    }
                }
            } else if (tier == 3) {
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(14.0D, 8.0D, 14.0D));
                for (LivingEntity entity : list) {
                    if (entity.getClassification(true) == MobCategory.MONSTER) {
                        entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                    }
                }
            }
        } else {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }
}
