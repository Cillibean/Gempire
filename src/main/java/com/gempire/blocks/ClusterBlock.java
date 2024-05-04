package com.gempire.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ClusterBlock extends Block {
    public ClusterBlock(Properties p_49795_) {
        super(p_49795_.noOcclusion());
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.below()).isFaceSturdy(worldIn, pos.below(), Direction.UP) && !(worldIn.getBlockState(pos.below()).getBlock() instanceof ClusterBlock);
    }

    public void onProjectileHit(Level p_152001_, BlockState p_152002_, BlockHitResult p_152003_, Projectile p_152004_) {
        if (!p_152001_.isClientSide) {
            BlockPos $$4 = p_152003_.getBlockPos();
            p_152001_.playSound((Player)null, $$4, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + p_152001_.random.nextFloat() * 1.2F);
            p_152001_.playSound((Player)null, $$4, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.5F + p_152001_.random.nextFloat() * 1.2F);
        }

    }
}
