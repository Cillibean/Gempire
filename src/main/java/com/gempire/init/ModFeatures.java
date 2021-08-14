package com.gempire.init;

import com.gempire.blocks.ChromaBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DecoratedPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.*;

public class ModFeatures {

    public static void addOres(final BiomeLoadingEvent event){
        addCrystal(event, ModBlocks.WHITE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.WHITE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.ORANGE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.ORANGE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.MAGENTA_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.YELLOW_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.YELLOW_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.LIME_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.LIME_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.PINK_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.PINK_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.GRAY_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.GRAY_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.CYAN_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.CYAN_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.PURPLE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.PURPLE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.BLUE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.BLUE_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.BROWN_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.BROWN_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.GREEN_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.GREEN_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.RED_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.RED_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);

        addCrystal(event, ModBlocks.BLACK_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.UP), 0, 60, 1);
        addCrystal(event, ModBlocks.BLACK_CHROMA_CRYSTAL.get().getDefaultState().with(ChromaBlock.FACING, Direction.DOWN), 0, 60, 1);
    }

    public static void addCrystal(final BiomeLoadingEvent event, BlockState state, int minHeight, int maxHeight, int amount){
        BlockState toPlace = state;

        ArrayList<BlockState> placeOnList = new ArrayList<>();
        BlockState onState = state.get(DirectionalBlock.FACING) == Direction.UP ? Blocks.STONE.getDefaultState() : Blocks.CAVE_AIR.getDefaultState();
        placeOnList.add(onState);
        List<BlockState> placeOn = placeOnList;

        ArrayList<BlockState> placeInList = new ArrayList<>();
        BlockState inState = Blocks.CAVE_AIR.getDefaultState();
        placeInList.add(inState);
        List<BlockState> placeIn = placeInList;

        ArrayList<BlockState> placeUnderList = new ArrayList<>();
        BlockState underState = state.get(DirectionalBlock.FACING) == Direction.UP ? Blocks.CAVE_AIR.getDefaultState() : Blocks.STONE.getDefaultState();
        placeUnderList.add(underState);
        List<BlockState> placeUnder = placeUnderList;

        BlockWithContextConfig config = new BlockWithContextConfig(toPlace, placeOn, placeIn, placeUnder);

        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.SIMPLE_BLOCK.withConfiguration(
                config)
                .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                .square().func_242731_b(amount)
        );
    }
}
