package com.gempire.blocks;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.Tags;

public class ChromaClusterCropBlock extends CropBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);

    public ChromaClusterCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
        return p_52302_.is(Tags.Blocks.STONE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_255715_, BlockPos p_52259_, BlockState p_52260_, boolean p_52261_) {
        return false;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CHROMA_CATALYST.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 6;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        float f = CropBlock.getGrowthSpeed(this, level, pos);
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
                System.out.println("grow pre");
                int i = state.getValue(AGE);
                if (i < 5) {
                    System.out.println("age up");
                    level.setBlock(pos, state.setValue(AGE, i + 1), 2);
                    System.out.println(i);
                } else {
                    System.out.println("grow block");
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    BlockPos blockpos = pos.relative(direction);
                    BlockState blockstate = level.getBlockState(blockpos.below());
                    Block block = blockstate.getBlock();
                    if (level.isEmptyBlock(blockpos) && (block == Blocks.STONE || block == Blocks.ANDESITE || block == Blocks.GRANITE || block == Blocks.DIORITE )) {
                        switch (random.nextInt(16)) {
                            case 0 -> level.setBlockAndUpdate(blockpos, ModBlocks.RED_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 1 -> level.setBlockAndUpdate(blockpos, ModBlocks.ORANGE_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 2 -> level.setBlockAndUpdate(blockpos, ModBlocks.YELLOW_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 3 -> level.setBlockAndUpdate(blockpos, ModBlocks.LIME_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 4 -> level.setBlockAndUpdate(blockpos, ModBlocks.GREEN_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 5 -> level.setBlockAndUpdate(blockpos, ModBlocks.CYAN_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 6 -> level.setBlockAndUpdate(blockpos, ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 7 -> level.setBlockAndUpdate(blockpos, ModBlocks.BLUE_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 8 -> level.setBlockAndUpdate(blockpos, ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 9 -> level.setBlockAndUpdate(blockpos, ModBlocks.PURPLE_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 10 -> level.setBlockAndUpdate(blockpos, ModBlocks.PINK_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 11 -> level.setBlockAndUpdate(blockpos, ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 12 -> level.setBlockAndUpdate(blockpos, ModBlocks.GRAY_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 13 -> level.setBlockAndUpdate(blockpos, ModBlocks.BLACK_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 14 -> level.setBlockAndUpdate(blockpos, ModBlocks.WHITE_CHROMA_CRYSTAL.get().defaultBlockState());
                            case 15 -> level.setBlockAndUpdate(blockpos, ModBlocks.BROWN_CHROMA_CRYSTAL.get().defaultBlockState());
                        }
                    }
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
            }
    }
}
