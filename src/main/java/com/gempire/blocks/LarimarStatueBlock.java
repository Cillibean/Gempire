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
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        super.tick(state, level, pos, source);
        if (timer != 0) {
            timer--;
            if (tier == 0) {

            }
            if (tier == 1) {

            }
            if (tier == 2) {
                AABB aabb = new AABB(pos);
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb.inflate(14.0D, 8.0D, 14.0D));
                for (LivingEntity entity : list) {
                    if (entity.getClassification(true) == MobCategory.MONSTER) {
                        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100,1));
                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                    }
                }
            } else if (tier == 3) {
                AABB aabb = new AABB(pos);
                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb.inflate(14.0D, 8.0D, 14.0D));
                for (LivingEntity entity : list) {
                    if (entity instanceof Player)
                    //if (entity.getClassification(true) == MobCategory.MONSTER) {
                        entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100,1));
                    //}
                }
            }
        } else {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }
}
