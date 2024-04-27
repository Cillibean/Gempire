package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Gempire.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TUNGSTEN_BLOCK.get(),
                        ModBlocks.TUNGSTEN_ORE.get(),
                        ModBlocks.RAW_TUNGSTEN_BLOCK.get(),
                        ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(),
                        ModBlocks.ANATASE_BLOCK.get(),
                        ModBlocks.ELECTRUM_BLOCK.get(),
                        ModBlocks.THULITE_BLOCK.get(),
                        ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.RED_LATTICE.get(),
                        ModBlocks.ORANGE_LATTICE.get(),
                        ModBlocks.YELLOW_LATTICE.get(),
                        ModBlocks.LIME_LATTICE.get(),
                        ModBlocks.GREEN_LATTICE.get(),
                        ModBlocks.CYAN_LATTICE.get(),
                        ModBlocks.LIGHT_BLUE_LATTICE.get(),
                        ModBlocks.BLUE_LATTICE.get(),
                        ModBlocks.PURPLE_LATTICE.get(),
                        ModBlocks.MAGENTA_LATTICE.get(),
                        ModBlocks.PINK_LATTICE.get(),
                        ModBlocks.WHITE_LATTICE.get(),
                        ModBlocks.LIGHT_GRAY_LATTICE.get(),
                        ModBlocks.GRAY_LATTICE.get(),
                        ModBlocks.BLACK_LATTICE.get(),
                        ModBlocks.BROWN_LATTICE.get(),
                        ModBlocks.PRISMATIC_BLOCK.get(),
                        ModBlocks.ANATASE_ORE.get(),
                        ModBlocks.ELECTRUM_ORE.get(),
                        ModBlocks.THULITE_ORE.get(),
                        ModBlocks.PLATINUM_ORE.get(),
                        ModBlocks.RED_CHROMA_CRYSTAL.get(),
                        ModBlocks.ORANGE_CHROMA_CRYSTAL.get(),
                        ModBlocks.YELLOW_CHROMA_CRYSTAL.get(),
                        ModBlocks.LIME_CHROMA_CRYSTAL.get(),
                        ModBlocks.GREEN_CHROMA_CRYSTAL.get(),
                        ModBlocks.CYAN_CHROMA_CRYSTAL.get(),
                        ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(),
                        ModBlocks.BLUE_CHROMA_CRYSTAL.get(),
                        ModBlocks.PURPLE_CHROMA_CRYSTAL.get(),
                        ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(),
                        ModBlocks.PINK_CHROMA_CRYSTAL.get(),
                        ModBlocks.WHITE_CHROMA_CRYSTAL.get(),
                        ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(),
                        ModBlocks.GRAY_CHROMA_CRYSTAL.get(),
                        ModBlocks.BLACK_CHROMA_CRYSTAL.get(),
                        ModBlocks.BROWN_CHROMA_CRYSTAL.get(),
                        ModBlocks.PYRITE_BLOCK.get(),
                        ModBlocks.GEODE_CRYSTAL_BLOCK.get(),
                        ModBlocks.PRIMED_ICE.get(),
                        ModBlocks.PRIMED_PACKED_ICE.get(),
                        ModBlocks.PRIMED_BLUE_ICE.get(),
                        ModBlocks.PRIMED_DRAINED_ICE.get(),
                        ModBlocks.PEGMATITE.get(),
                        ModBlocks.RED_DIAMOND_GLASS.get(),
                        ModBlocks.ORANGE_DIAMOND_GLASS.get(),
                        ModBlocks.YELLOW_DIAMOND_GLASS.get(),
                        ModBlocks.LIME_DIAMOND_GLASS.get(),
                        ModBlocks.GREEN_DIAMOND_GLASS.get(),
                        ModBlocks.CYAN_DIAMOND_GLASS.get(),
                        ModBlocks.LIGHT_BLUE_DIAMOND_GLASS.get(),
                        ModBlocks.BLUE_DIAMOND_GLASS.get(),
                        ModBlocks.PURPLE_DIAMOND_GLASS.get(),
                        ModBlocks.MAGENTA_DIAMOND_GLASS.get(),
                        ModBlocks.PINK_DIAMOND_GLASS.get(),
                        ModBlocks.WHITE_DIAMOND_GLASS.get(),
                        ModBlocks.LIGHT_GRAY_DIAMOND_GLASS.get(),
                        ModBlocks.GRAY_DIAMOND_GLASS.get(),
                        ModBlocks.BLACK_DIAMOND_GLASS.get(),
                        ModBlocks.BROWN_DIAMOND_GLASS.get(),
                        ModBlocks.PURIFIED_PRISMATIC_GLASS.get(),
                        ModBlocks.SHELL_BLOCK.get(),
                        ModBlocks.DRILL_BLOCK.get(),
                        ModBlocks.PEDISTAL.get(),
                        ModBlocks.POWER_CRYSTAL_BLOCK.get(),
                        ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get(),
                        ModBlocks.TANK_BLOCK.get(),
                        ModBlocks.DRAINED_ICE.get(),
                        ModBlocks.DRAINED_LOG.get(),
                        ModBlocks.DRAINED_LOG_CRACKED.get(),
                        ModBlocks.RUINED_MARBLE_BLOCK.get(),
                        ModBlocks.RUINED_MARBLE_BRICK.get(),
                        ModBlocks.RUINED_MARBLE_PILLAR.get(),
                        ModBlocks.RUINED_MARBLE_SLAB.get(),
                        ModBlocks.RUINED_MARBLE_STAIRS.get(),
                        ModBlocks.CHISELED_RUINED_MARBLE_BLOCK.get(),
                        ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get(),
                        ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get(),
                        ModBlocks.SMOOTH_RUINED_MARBLE_STAIRS.get(),
                        ModBlocks.DRAINED_PURPLE_STONE.get(),
                        ModBlocks.DRAINED_PURPLE_STONE_2.get(),
                        ModBlocks.DRAINED_BANDED_PURPLE_STONE.get(),
                        ModBlocks.DRAINED_BLUE_STONE.get(),
                        ModBlocks.DRAINED_BLUE_STONE_2.get(),
                        ModBlocks.DRAINED_BANDED_BLUE_STONE.get(),
                        ModBlocks.DRAINED_YELLOW_STONE.get(),
                        ModBlocks.DRAINED_YELLOW_STONE_2.get(),
                        ModBlocks.DRAINED_BANDED_YELLOW_STONE.get(),
                        ModBlocks.DRAINED_RED_STONE.get(),
                        ModBlocks.DRAINED_RED_STONE_2.get(),
                        ModBlocks.DRAINED_BANDED_RED_STONE.get(),
                        ModBlocks.DRAINED_GREY_STONE.get(),
                        ModBlocks.DRAINED_GREY_STONE_2.get(),
                        ModBlocks.DRAINED_BANDED_GREY_STONE.get()
                );

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.DRAINED_BLUE_SOIL.get(),
                        ModBlocks.DRAINED_PURPLE_SOIL.get(),
                        ModBlocks.DRAINED_RED_SOIL.get(),
                        ModBlocks.DRAINED_YELLOW_SOIL.get(),
                        ModBlocks.DRAINED_GREY_SOIL.get(),
                        ModBlocks.DRAINED_RED_SAND.get(),
                        ModBlocks.DRAINED_SAND.get(),
                        ModBlocks.DRAINED_GREY_SAND.get(),
                        ModBlocks.DRAINED_BLUE_SAND.get(),
                        ModBlocks.DESOLATE_GRASS.get(),
                        ModBlocks.DESOLATE_SOIL.get(),
                        ModBlocks.ABNORMAL_SAND.get()

                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TUNGSTEN_BLOCK.get(),
                        ModBlocks.TUNGSTEN_ORE.get(),
                        ModBlocks.RAW_TUNGSTEN_BLOCK.get(),
                        ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(),
                        ModBlocks.ANATASE_BLOCK.get(),
                        ModBlocks.ELECTRUM_BLOCK.get(),
                        ModBlocks.THULITE_BLOCK.get(),
                        ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.RED_LATTICE.get(),
                        ModBlocks.ORANGE_LATTICE.get(),
                        ModBlocks.YELLOW_LATTICE.get(),
                        ModBlocks.LIME_LATTICE.get(),
                        ModBlocks.GREEN_LATTICE.get(),
                        ModBlocks.CYAN_LATTICE.get(),
                        ModBlocks.LIGHT_BLUE_LATTICE.get(),
                        ModBlocks.BLUE_LATTICE.get(),
                        ModBlocks.PURPLE_LATTICE.get(),
                        ModBlocks.MAGENTA_LATTICE.get(),
                        ModBlocks.PINK_LATTICE.get(),
                        ModBlocks.WHITE_LATTICE.get(),
                        ModBlocks.LIGHT_GRAY_LATTICE.get(),
                        ModBlocks.GRAY_LATTICE.get(),
                        ModBlocks.BLACK_LATTICE.get(),
                        ModBlocks.BROWN_LATTICE.get()
                );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PRISMATIC_BLOCK.get());

        this.tag(ModTags.Blocks.NEEDS_PRISMATIC_TOOL)
                .add(ModBlocks.ANATASE_ORE.get(),
                        ModBlocks.ELECTRUM_ORE.get(),
                        ModBlocks.THULITE_ORE.get(),
                        ModBlocks.PLATINUM_ORE.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.DISTANT_FENCE.get(),
                        ModBlocks.KALEIDOSCOPE_FENCE.get(),
                        ModBlocks.SHADED_FENCE.get(),
                        ModBlocks.CRYSTAL_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.DISTANT_FENCE_GATE.get(),
                        ModBlocks.KALEIDOSCOPE_FENCE_GATE.get(),
                        ModBlocks.SHADED_FENCE_GATE.get(),
                        ModBlocks.CRYSTAL_FENCE_GATE.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.DISTANT_WOOD.get(),
                        ModBlocks.DISTANT_LOG.get(),
                        ModBlocks.STRIPPED_DISTANT_LOG.get(),
                        ModBlocks.STRIPPED_DISTANT_WOOD.get(),
                        ModBlocks.CRYSTAL_WOOD.get(),
                        ModBlocks.CRYSTAL_LOG.get(),
                        ModBlocks.STRIPPED_CRYSTAL_LOG.get(),
                        ModBlocks.STRIPPED_CRYSTAL_WOOD.get(),
                        ModBlocks.KALEIDOSCOPE_WOOD.get(),
                        ModBlocks.KALEIDOSCOPE_LOG.get(),
                        ModBlocks.STRIPPED_KALEIDOSCOPE_LOG.get(),
                        ModBlocks.STRIPPED_KALEIDOSCOPE_WOOD.get(),
                        ModBlocks.SHADED_WOOD.get(),
                        ModBlocks.SHADED_LOG.get(),
                        ModBlocks.STRIPPED_SHADED_LOG.get(),
                        ModBlocks.STRIPPED_SHADED_WOOD.get(),
                        ModBlocks.CRYSTAL_WOOD.get(),
                        ModBlocks.CRYSTAL_LOG.get(),
                        ModBlocks.STRIPPED_CRYSTAL_LOG.get(),
                        ModBlocks.STRIPPED_CRYSTAL_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.DISTANT_PLANKS.get(),
                        ModBlocks.KALEIDOSCOPE_PLANKS.get(),
                        ModBlocks.SHADED_PLANKS.get(),
                        ModBlocks.CRYSTAL_PLANKS.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.DISTANT_LEAVES.get(),
                        ModBlocks.CRYSTAL_LEAVES.get(),
                        ModBlocks.KALEIDOSCOPE_LEAVES.get(),
                        ModBlocks.SHADED_LEAVES.get()
                );

        this.tag(BlockTags.ICE)
                .add(ModBlocks.PRIMED_ICE.get(),
                        ModBlocks.PRIMED_PACKED_ICE.get(),
                        ModBlocks.PRIMED_BLUE_ICE.get(),
                        ModBlocks.PRIMED_DRAINED_ICE.get(),
                        ModBlocks.DRAINED_ICE.get()
                );

        this.tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.ANATASE_BLOCK.get(),
                        ModBlocks.THULITE_BLOCK.get(),
                        ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.ELECTRUM_BLOCK.get(),
                        ModBlocks.TUNGSTEN_BLOCK.get(),
                        ModBlocks.PRISMATIC_BLOCK.get()
                );

        this.tag(ModTags.Blocks.DESOLATE_ORE_REPLACEABLES)
                .add(ModBlocks.PEGMATITE.get());

        this.tag(BlockTags.DIRT).add(ModBlocks.DESOLATE_SOIL.get(), ModBlocks.DESOLATE_GRASS.get());

        this.tag(BlockTags.SAND).add(ModBlocks.ABNORMAL_SAND.get());
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
