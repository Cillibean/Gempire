package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.blocks.ChromaBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Gempire.MODID)
public class ModFeatures {
    // CONFIGURED FEATURES
    public static final ConfiguredFeature<?, ?> CONFIGURED_WHITE_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WHITE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_WHITE_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WHITE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_ORANGE_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_ORANGE_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORANGE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_MAGENTA_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_MAGENTA_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LIGHT_BLUE_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LIGHT_BLUE_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_YELLOW_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.YELLOW_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_YELLOW_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.YELLOW_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LIME_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIME_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LIME_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIME_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_PINK_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PINK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_PINK_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PINK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_GRAY_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_GRAY_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LIGHT_GRAY_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LIGHT_GRAY_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_CYAN_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CYAN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_CYAN_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CYAN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_PURPLE_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_PURPLE_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PURPLE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_BLUE_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_BLUE_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_BROWN_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BROWN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_BROWN_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BROWN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_GREEN_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GREEN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_GREEN_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GREEN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_RED_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RED_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_RED_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RED_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_BLACK_CHROMA_CRYSTAL_UP = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLACK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 8))))));
    public static final ConfiguredFeature<?, ?> CONFIGURED_BLACK_CHROMA_CRYSTAL_DOWN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(1, 0, 0, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLACK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN)))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, 1, 8))))));

    // PLACED FEATURES
    public static final PlacedFeature PLACED_WHITE_CHROMA_CRYSTAL_UP = CONFIGURED_WHITE_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_WHITE_CHROMA_CRYSTAL_DOWN = CONFIGURED_WHITE_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_ORANGE_CHROMA_CRYSTAL_UP = CONFIGURED_ORANGE_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_ORANGE_CHROMA_CRYSTAL_DOWN = CONFIGURED_ORANGE_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_MAGENTA_CHROMA_CRYSTAL_UP = CONFIGURED_MAGENTA_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_MAGENTA_CHROMA_CRYSTAL_DOWN = CONFIGURED_MAGENTA_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_LIGHT_BLUE_CHROMA_CRYSTAL_UP = CONFIGURED_LIGHT_BLUE_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_LIGHT_BLUE_CHROMA_CRYSTAL_DOWN = CONFIGURED_LIGHT_BLUE_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_YELLOW_CHROMA_CRYSTAL_UP = CONFIGURED_YELLOW_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_YELLOW_CHROMA_CRYSTAL_DOWN = CONFIGURED_YELLOW_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_LIME_CHROMA_CRYSTAL_UP = CONFIGURED_LIME_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_LIME_CHROMA_CRYSTAL_DOWN = CONFIGURED_LIME_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_PINK_CHROMA_CRYSTAL_UP = CONFIGURED_PINK_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_PINK_CHROMA_CRYSTAL_DOWN = CONFIGURED_PINK_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_GRAY_CHROMA_CRYSTAL_UP = CONFIGURED_GRAY_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_GRAY_CHROMA_CRYSTAL_DOWN = CONFIGURED_GRAY_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_LIGHT_GRAY_CHROMA_CRYSTAL_UP = CONFIGURED_LIGHT_GRAY_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_LIGHT_GRAY_CHROMA_CRYSTAL_DOWN = CONFIGURED_LIGHT_GRAY_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_CYAN_CHROMA_CRYSTAL_UP = CONFIGURED_CYAN_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_CYAN_CHROMA_CRYSTAL_DOWN = CONFIGURED_CYAN_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_PURPLE_CHROMA_CRYSTAL_UP = CONFIGURED_PURPLE_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_PURPLE_CHROMA_CRYSTAL_DOWN = CONFIGURED_PURPLE_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_BLUE_CHROMA_CRYSTAL_UP = CONFIGURED_BLUE_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_BLUE_CHROMA_CRYSTAL_DOWN = CONFIGURED_BLUE_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_BROWN_CHROMA_CRYSTAL_UP = CONFIGURED_BROWN_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_BROWN_CHROMA_CRYSTAL_DOWN = CONFIGURED_BROWN_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_GREEN_CHROMA_CRYSTAL_UP = CONFIGURED_GREEN_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_GREEN_CHROMA_CRYSTAL_DOWN = CONFIGURED_GREEN_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_RED_CHROMA_CRYSTAL_UP = CONFIGURED_RED_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_RED_CHROMA_CRYSTAL_DOWN = CONFIGURED_RED_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_BLACK_CHROMA_CRYSTAL_UP = CONFIGURED_BLACK_CHROMA_CRYSTAL_UP.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());
    public static final PlacedFeature PLACED_BLACK_CHROMA_CRYSTAL_DOWN = CONFIGURED_BLACK_CHROMA_CRYSTAL_DOWN.placed(HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60), 8)), InSquarePlacement.spread(), CountPlacement.of(1), BiomeFilter.biome());

    public static void registerConfiguredFeatures() {
        register("white_chroma_crystal_up", CONFIGURED_WHITE_CHROMA_CRYSTAL_UP);
        register("white_chroma_crystal_down", CONFIGURED_WHITE_CHROMA_CRYSTAL_DOWN);
        register("orange_chroma_crystal_up", CONFIGURED_ORANGE_CHROMA_CRYSTAL_UP);
        register("orange_chroma_crystal_down", CONFIGURED_ORANGE_CHROMA_CRYSTAL_DOWN);
        register("magenta_chroma_crystal_up", CONFIGURED_MAGENTA_CHROMA_CRYSTAL_UP);
        register("magenta_chroma_crystal_down", CONFIGURED_MAGENTA_CHROMA_CRYSTAL_DOWN);
        register("light_blue_chroma_crystal_up", CONFIGURED_LIGHT_BLUE_CHROMA_CRYSTAL_UP);
        register("light_blue_chroma_crystal_down", CONFIGURED_LIGHT_BLUE_CHROMA_CRYSTAL_DOWN);
        register("yellow_chroma_crystal_up", CONFIGURED_YELLOW_CHROMA_CRYSTAL_UP);
        register("yellow_chroma_crystal_down", CONFIGURED_YELLOW_CHROMA_CRYSTAL_DOWN);
        register("lime_chroma_crystal_up", CONFIGURED_LIME_CHROMA_CRYSTAL_UP);
        register("lime_chroma_crystal_down", CONFIGURED_LIME_CHROMA_CRYSTAL_DOWN);
        register("pink_chroma_crystal_up", CONFIGURED_PINK_CHROMA_CRYSTAL_UP);
        register("pink_chroma_crystal_down", CONFIGURED_PINK_CHROMA_CRYSTAL_DOWN);
        register("gray_chroma_crystal_up", CONFIGURED_GRAY_CHROMA_CRYSTAL_UP);
        register("gray_chroma_crystal_down", CONFIGURED_GRAY_CHROMA_CRYSTAL_DOWN);
        register("light_gray_chroma_crystal_up", CONFIGURED_LIGHT_GRAY_CHROMA_CRYSTAL_UP);
        register("light_gray_chroma_crystal_down", CONFIGURED_LIGHT_GRAY_CHROMA_CRYSTAL_DOWN);
        register("cyan_chroma_crystal_up", CONFIGURED_CYAN_CHROMA_CRYSTAL_UP);
        register("cyan_chroma_crystal_down", CONFIGURED_CYAN_CHROMA_CRYSTAL_DOWN);
        register("purple_chroma_crystal_up", CONFIGURED_PURPLE_CHROMA_CRYSTAL_UP);
        register("purple_chroma_crystal_down", CONFIGURED_PURPLE_CHROMA_CRYSTAL_DOWN);
        register("blue_chroma_crystal_up", CONFIGURED_BLUE_CHROMA_CRYSTAL_UP);
        register("blue_chroma_crystal_down", CONFIGURED_BLUE_CHROMA_CRYSTAL_DOWN);
        register("brown_chroma_crystal_up", CONFIGURED_BROWN_CHROMA_CRYSTAL_UP);
        register("brown_chroma_crystal_down", CONFIGURED_BROWN_CHROMA_CRYSTAL_DOWN);
        register("green_chroma_crystal_up", CONFIGURED_GREEN_CHROMA_CRYSTAL_UP);
        register("green_chroma_crystal_down", CONFIGURED_GREEN_CHROMA_CRYSTAL_DOWN);
        register("red_chroma_crystal_up", CONFIGURED_RED_CHROMA_CRYSTAL_UP);
        register("red_chroma_crystal_down", CONFIGURED_RED_CHROMA_CRYSTAL_DOWN);
        register("black_chroma_crystal_up", CONFIGURED_BLACK_CHROMA_CRYSTAL_UP);
        register("black_chroma_crystal_down", CONFIGURED_BLACK_CHROMA_CRYSTAL_DOWN);
    }

    private static void register(String id, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gempire.MODID, id), configuredFeature);
    }

    public static void registerPlacedFeatures() {
        register("white_chroma_crystal_up", PLACED_WHITE_CHROMA_CRYSTAL_UP);
        register("white_chroma_crystal_down", PLACED_WHITE_CHROMA_CRYSTAL_DOWN);
        register("orange_chroma_crystal_up", PLACED_ORANGE_CHROMA_CRYSTAL_UP);
        register("orange_chroma_crystal_down", PLACED_ORANGE_CHROMA_CRYSTAL_DOWN);
        register("magenta_chroma_crystal_up", PLACED_MAGENTA_CHROMA_CRYSTAL_UP);
        register("magenta_chroma_crystal_down", PLACED_MAGENTA_CHROMA_CRYSTAL_DOWN);
        register("light_blue_chroma_crystal_up", PLACED_LIGHT_BLUE_CHROMA_CRYSTAL_UP);
        register("light_blue_chroma_crystal_down", PLACED_LIGHT_BLUE_CHROMA_CRYSTAL_DOWN);
        register("yellow_chroma_crystal_up", PLACED_YELLOW_CHROMA_CRYSTAL_UP);
        register("yellow_chroma_crystal_down", PLACED_YELLOW_CHROMA_CRYSTAL_DOWN);
        register("lime_chroma_crystal_up", PLACED_LIME_CHROMA_CRYSTAL_UP);
        register("lime_chroma_crystal_down", PLACED_LIME_CHROMA_CRYSTAL_DOWN);
        register("pink_chroma_crystal_up", PLACED_PINK_CHROMA_CRYSTAL_UP);
        register("pink_chroma_crystal_down", PLACED_PINK_CHROMA_CRYSTAL_DOWN);
        register("gray_chroma_crystal_up", PLACED_GRAY_CHROMA_CRYSTAL_UP);
        register("gray_chroma_crystal_down", PLACED_GRAY_CHROMA_CRYSTAL_DOWN);
        register("light_gray_chroma_crystal_up", PLACED_LIGHT_GRAY_CHROMA_CRYSTAL_UP);
        register("light_gray_chroma_crystal_down", PLACED_LIGHT_GRAY_CHROMA_CRYSTAL_DOWN);
        register("cyan_chroma_crystal_up", PLACED_CYAN_CHROMA_CRYSTAL_UP);
        register("cyan_chroma_crystal_down", PLACED_CYAN_CHROMA_CRYSTAL_DOWN);
        register("purple_chroma_crystal_up", PLACED_PURPLE_CHROMA_CRYSTAL_UP);
        register("purple_chroma_crystal_down", PLACED_PURPLE_CHROMA_CRYSTAL_DOWN);
        register("blue_chroma_crystal_up", PLACED_BLUE_CHROMA_CRYSTAL_UP);
        register("blue_chroma_crystal_down", PLACED_BLUE_CHROMA_CRYSTAL_DOWN);
        register("brown_chroma_crystal_up", PLACED_BROWN_CHROMA_CRYSTAL_UP);
        register("brown_chroma_crystal_down", PLACED_BROWN_CHROMA_CRYSTAL_DOWN);
        register("green_chroma_crystal_up", PLACED_GREEN_CHROMA_CRYSTAL_UP);
        register("green_chroma_crystal_down", PLACED_GREEN_CHROMA_CRYSTAL_DOWN);
        register("red_chroma_crystal_up", PLACED_RED_CHROMA_CRYSTAL_UP);
        register("red_chroma_crystal_down", PLACED_RED_CHROMA_CRYSTAL_DOWN);
        register("black_chroma_crystal_up", PLACED_BLACK_CHROMA_CRYSTAL_UP);
        register("black_chroma_crystal_down", PLACED_BLACK_CHROMA_CRYSTAL_DOWN);
    }

    private static void register(String id, PlacedFeature placedFeature) {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gempire.MODID, id), placedFeature);
    }

    public static void addOres(final BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NONE) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_WHITE_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_WHITE_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_ORANGE_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_ORANGE_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_MAGENTA_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_MAGENTA_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_LIGHT_BLUE_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_LIGHT_BLUE_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_YELLOW_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_YELLOW_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_LIME_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_LIME_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_PINK_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_PINK_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_GRAY_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_GRAY_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_LIGHT_GRAY_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_LIGHT_GRAY_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_CYAN_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_CYAN_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_PURPLE_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_PURPLE_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_BLUE_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_BLUE_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_BROWN_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_BROWN_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_GREEN_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_GREEN_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_RED_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_RED_CHROMA_CRYSTAL_DOWN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_BLACK_CHROMA_CRYSTAL_UP);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLACED_BLACK_CHROMA_CRYSTAL_DOWN);
        }
    }
}
