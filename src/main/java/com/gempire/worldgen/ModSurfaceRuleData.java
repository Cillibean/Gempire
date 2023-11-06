package com.gempire.worldgen;

import com.gempire.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource DRAINED_PURPLE_STONE = makeStateRule(ModBlocks.DRAINED_PURPLE_STONE.get());
    private static final SurfaceRules.RuleSource DRAINED_PURPLE_SOIL = makeStateRule(ModBlocks.DRAINED_PURPLE_SOIL.get());
    private static final SurfaceRules.RuleSource DRAINED_PURPLE_STONE_2 = makeStateRule(ModBlocks.DRAINED_PURPLE_STONE_2.get());
    //private static final SurfaceRules.RuleSource DRAINED_BANDED_PURPLE_STONE = makeStateRule(ModBlocks.DRAINED_BANDED_PURPLE_STONE.get());

    private static final SurfaceRules.RuleSource DRAINED_YELLOW_STONE = makeStateRule(ModBlocks.DRAINED_YELLOW_STONE.get());
    private static final SurfaceRules.RuleSource DRAINED_YELLOW_SOIL = makeStateRule(ModBlocks.DRAINED_YELLOW_SOIL.get());
    private static final SurfaceRules.RuleSource DRAINED_YELLOW_SAND = makeStateRule(ModBlocks.DRAINED_SAND.get());
    private static final SurfaceRules.RuleSource DRAINED_YELLOW_STONE_2 = makeStateRule(ModBlocks.DRAINED_YELLOW_STONE_2.get());
    private static final SurfaceRules.RuleSource DRAINED_GREY_STONE = makeStateRule(ModBlocks.DRAINED_GREY_STONE.get());
    private static final SurfaceRules.RuleSource DRAINED_GREY_SOIL = makeStateRule(ModBlocks.DRAINED_GREY_SOIL.get());
    private static final SurfaceRules.RuleSource DRAINED_GREY_STONE_2 = makeStateRule(ModBlocks.DRAINED_GREY_STONE_2.get());
    private static final SurfaceRules.RuleSource DRAINED_BLUE_STONE = makeStateRule(ModBlocks.DRAINED_BLUE_STONE.get());
    private static final SurfaceRules.RuleSource DRAINED_BLUE_SOIL = makeStateRule(ModBlocks.DRAINED_BLUE_SOIL.get());
    private static final SurfaceRules.RuleSource DRAINED_BLUE_STONE_2 = makeStateRule(ModBlocks.DRAINED_BLUE_STONE_2.get());
    private static final SurfaceRules.RuleSource DRAINED_RED_STONE = makeStateRule(ModBlocks.DRAINED_RED_STONE.get());
    private static final SurfaceRules.RuleSource DRAINED_RED_SAND = makeStateRule(ModBlocks.DRAINED_RED_SAND.get());
    private static final SurfaceRules.RuleSource DRAINED_RED_SOIL = makeStateRule(ModBlocks.DRAINED_RED_SOIL.get());
    private static final SurfaceRules.RuleSource DRAINED_RED_STONE_2 = makeStateRule(ModBlocks.DRAINED_RED_STONE_2.get());

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        SurfaceRules.ConditionSource stripedStoneLevel = SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(30), 0);
        SurfaceRules.ConditionSource stone2Level = SurfaceRules.yBlockCheck(VerticalAnchor.BOTTOM, 30);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.MASK_ISLAND), grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.STRAWBERRY_FIELDS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT))),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.PURPLE_KINDERGARTEN), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DRAINED_PURPLE_SOIL), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRAINED_PURPLE_SOIL), SurfaceRules.ifTrue(stone2Level, DRAINED_PURPLE_STONE_2), DRAINED_PURPLE_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GREY_KINDERGARTEN), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DRAINED_GREY_SOIL), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRAINED_GREY_SOIL), SurfaceRules.ifTrue(stone2Level, DRAINED_GREY_STONE_2), DRAINED_GREY_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.YELLOW_KINDERGARTEN), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DRAINED_YELLOW_SAND), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRAINED_YELLOW_SOIL), SurfaceRules.ifTrue(stone2Level, DRAINED_YELLOW_STONE_2), DRAINED_YELLOW_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLUE_KINDERGARTEN), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DRAINED_BLUE_SOIL), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRAINED_BLUE_SOIL), SurfaceRules.ifTrue(stone2Level, DRAINED_BLUE_STONE_2), DRAINED_BLUE_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.RED_KINDERGARTEN), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DRAINED_RED_SAND), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRAINED_RED_SOIL), SurfaceRules.ifTrue(stone2Level, DRAINED_RED_STONE_2), DRAINED_RED_STONE))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
