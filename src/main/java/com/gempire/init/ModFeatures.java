package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.blocks.ChromaBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Gempire.MODID)
public class ModFeatures {

    public static void addOres(final BiomeLoadingEvent event){
        addCrystal(event, ModBlocks.WHITE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.WHITE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.ORANGE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.ORANGE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.YELLOW_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.YELLOW_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.LIME_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.LIME_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.PINK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.PINK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.CYAN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.CYAN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.PURPLE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.PURPLE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.BLUE_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.BROWN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.BROWN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.GREEN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.GREEN_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.RED_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.RED_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.BLACK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.BLACK_CHROMA_CRYSTAL.get().defaultBlockState().setValue(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);
    }

    public static void addCrystal(final BiomeLoadingEvent event, BlockState state, int minHeight, int maxHeight, int amount){
        BlockState toPlace = state;
        BlockStateProvider p = new BlockStateProvider() {
            @Override
            protected BlockStateProviderType<?> type() {
                return BlockStateProviderType.SIMPLE_STATE_PROVIDER;
            }

            @Override
            public BlockState getState(Random p_68750_, BlockPos p_68751_) {
                return toPlace;
            }
        };
        HeightProvider hp = new HeightProvider() {
            @Override
            public int sample(Random p_161977_, WorldGenerationContext p_161978_) {
                return maxHeight;
            }

            @Override
            public HeightProviderType<?> getType() {
                return HeightProviderType.BIASED_TO_BOTTOM;
            }
        };

        ArrayList<BlockState> placeOnList = new ArrayList<>();
        BlockState onState = state.getValue(DirectionalBlock.FACING) == Direction.UP ? Blocks.STONE.defaultBlockState() : Blocks.CAVE_AIR.defaultBlockState();
        placeOnList.add(onState);
        List<BlockState> placeOn = placeOnList;

        ArrayList<BlockState> placeInList = new ArrayList<>();
        BlockState inState = Blocks.CAVE_AIR.defaultBlockState();
        placeInList.add(inState);
        List<BlockState> placeIn = placeInList;

        ArrayList<BlockState> placeUnderList = new ArrayList<>();
        BlockState underState = state.getValue(DirectionalBlock.FACING) == Direction.UP ? Blocks.CAVE_AIR.defaultBlockState() : Blocks.STONE.defaultBlockState();
        placeUnderList.add(underState);
        List<BlockState> placeUnder = placeUnderList;

        SimpleBlockConfiguration config = new SimpleBlockConfiguration(p, placeOn, placeIn, placeUnder);

        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Feature.SIMPLE_BLOCK.configured(
                config)
                .decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(hp)))
                .squared().count(amount)
        );
    }
}
