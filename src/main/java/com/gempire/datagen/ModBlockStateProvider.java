package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Gempire.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ANATASE_BLOCK);
        blockWithItem(ModBlocks.ANATASE_ORE);
        blockWithItem(ModBlocks.RAW_ANATASE_BLOCK);
        blockWithItem(ModBlocks.THULITE_BLOCK);
        blockWithItem(ModBlocks.THULITE_ORE);
        blockWithItem(ModBlocks.RAW_THULITE_BLOCK);
        blockWithItem(ModBlocks.ELECTRUM_BLOCK);
        blockWithItem(ModBlocks.ELECTRUM_ORE);
        blockWithItem(ModBlocks.RAW_ELECTRUM_BLOCK);
        blockWithItem(ModBlocks.PLATINUM_BLOCK);
        blockWithItem(ModBlocks.PLATINUM_ORE);
        blockWithItem(ModBlocks.RAW_PLATINUM_BLOCK);
        blockWithItem(ModBlocks.TUNGSTEN_BLOCK);
        blockWithItem(ModBlocks.TUNGSTEN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);
        blockWithItem(ModBlocks.PEGMATITE_TUNGSTEN_ORE);
        blockWithItem(ModBlocks.RAW_TUNGSTEN_BLOCK);

        blockWithItem(ModBlocks.PYRITE_BLOCK);
        blockWithItem(ModBlocks.PYRITE_ORE);
        blockWithItem(ModBlocks.RAW_PYRITE_BLOCK);

        blockWithItem(ModBlocks.GEODE_CRYSTAL_BLOCK);

        blockWithItem(ModBlocks.PRISMATIC_SNOW);

        blockWithItem(ModBlocks.PRISMATIC_BLOCK);

        blockWithItem(ModBlocks.STRAWBERRY_BLOCK);

        blockWithItem(ModBlocks.PEGMATITE);
        stairsBlock((StairBlock) ModBlocks.PEGMATITE_STAIRS.get(), blockTexture(ModBlocks.PEGMATITE.get()));
        slabBlock((SlabBlock) ModBlocks.PEGMATITE_SLAB.get(), blockTexture(ModBlocks.PEGMATITE.get()), blockTexture(ModBlocks.PEGMATITE.get()));
        wallBlock((WallBlock) ModBlocks.PEGMATITE_WALL.get(), blockTexture(ModBlocks.PEGMATITE.get()));
        blockItem(ModBlocks.PEGMATITE_STAIRS);
        blockItem(ModBlocks.PEGMATITE_SLAB);

        //blockWithItem(ModBlocks.COBBLED_PEGMATITE);
        //stairsBlock((StairBlock) ModBlocks.COBBLED_PEGMATITE_STAIRS.get(), blockTexture(ModBlocks.COBBLED_PEGMATITE.get()));
        //slabBlock((SlabBlock) ModBlocks.COBBLED_PEGMATITE_SLAB.get(), blockTexture(ModBlocks.COBBLED_PEGMATITE.get()), blockTexture(ModBlocks.COBBLED_PEGMATITE.get()));
        //wallBlock((WallBlock) ModBlocks.COBBLED_PEGMATITE_WALL.get(), blockTexture(ModBlocks.COBBLED_PEGMATITE.get()));
        //blockItem(ModBlocks.COBBLED_PEGMATITE_STAIRS);
        //blockItem(ModBlocks.COBBLED_PEGMATITE_SLAB);

        blockWithItem(ModBlocks.DISTANT_PLANKS);
        blockWithItem(ModBlocks.KALEIDOSCOPE_PLANKS);
        blockWithItem(ModBlocks.SHADED_PLANKS);
        blockWithItem(ModBlocks.CRYSTAL_PLANKS);

        stairsBlock((StairBlock) ModBlocks.DISTANT_STAIRS.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.DISTANT_SLAB.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()), blockTexture(ModBlocks.DISTANT_PLANKS.get()));

        stairsBlock((StairBlock) ModBlocks.KALEIDOSCOPE_STAIRS.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.KALEIDOSCOPE_SLAB.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));

        stairsBlock((StairBlock) ModBlocks.SHADED_STAIRS.get(), blockTexture(ModBlocks.SHADED_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.SHADED_SLAB.get(), blockTexture(ModBlocks.SHADED_PLANKS.get()), blockTexture(ModBlocks.SHADED_PLANKS.get()));

        stairsBlock((StairBlock) ModBlocks.CRYSTAL_STAIRS.get(), blockTexture(ModBlocks.CRYSTAL_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.CRYSTAL_SLAB.get(), blockTexture(ModBlocks.CRYSTAL_PLANKS.get()), blockTexture(ModBlocks.CRYSTAL_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.DISTANT_FENCE.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.DISTANT_FENCE_GATE.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.KALEIDOSCOPE_FENCE.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.KALEIDOSCOPE_FENCE_GATE.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.SHADED_FENCE.get(), blockTexture(ModBlocks.SHADED_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.SHADED_FENCE_GATE.get(), blockTexture(ModBlocks.SHADED_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.CRYSTAL_FENCE.get(), blockTexture(ModBlocks.CRYSTAL_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.CRYSTAL_FENCE_GATE.get(), blockTexture(ModBlocks.CRYSTAL_PLANKS.get()));

        blockItem(ModBlocks.DISTANT_STAIRS);
        blockItem(ModBlocks.DISTANT_SLAB);
        blockItem(ModBlocks.DISTANT_FENCE_GATE);

        doorBlockWithRenderType((DoorBlock)ModBlocks.DISTANT_DOOR.get(), modLoc("block/distant_door_bottom"), modLoc("block/distant_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.DISTANT_TRAPDOOR.get(), modLoc("block/distant_trapdoor"), true, "cutout");
        blockItem(ModBlocks.DISTANT_TRAPDOOR, "_bottom");

        blockItem(ModBlocks.KALEIDOSCOPE_STAIRS);
        blockItem(ModBlocks.KALEIDOSCOPE_SLAB);
        blockItem(ModBlocks.KALEIDOSCOPE_FENCE_GATE);

        doorBlockWithRenderType((DoorBlock)ModBlocks.KALEIDOSCOPE_DOOR.get(), modLoc("block/kaleidoscope_door_bottom"), modLoc("block/kaleidoscope_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.KALEIDOSCOPE_TRAPDOOR.get(), modLoc("block/kaleidoscope_trapdoor"), true, "cutout");
        blockItem(ModBlocks.KALEIDOSCOPE_TRAPDOOR, "_bottom");

        blockItem(ModBlocks.SHADED_STAIRS);
        blockItem(ModBlocks.SHADED_SLAB);
        blockItem(ModBlocks.SHADED_FENCE_GATE);

        doorBlockWithRenderType((DoorBlock)ModBlocks.SHADED_DOOR.get(), modLoc("block/shaded_door_bottom"), modLoc("block/shaded_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.SHADED_TRAPDOOR.get(), modLoc("block/shaded_trapdoor"), true, "cutout");
        blockItem(ModBlocks.SHADED_TRAPDOOR, "_bottom");

        blockItem(ModBlocks.CRYSTAL_STAIRS);
        blockItem(ModBlocks.CRYSTAL_SLAB);
        blockItem(ModBlocks.CRYSTAL_FENCE_GATE);

        doorBlockWithRenderType((DoorBlock)ModBlocks.CRYSTAL_DOOR.get(), modLoc("block/crystal_door_bottom"), modLoc("block/crystal_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.CRYSTAL_TRAPDOOR.get(), modLoc("block/crystal_trapdoor"), true, "cutout");
        blockItem(ModBlocks.CRYSTAL_TRAPDOOR, "_bottom");


        logBlock(((RotatedPillarBlock) ModBlocks.DISTANT_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.DISTANT_WOOD.get()), blockTexture(ModBlocks.DISTANT_LOG.get()), blockTexture(ModBlocks.DISTANT_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DISTANT_LOG.get(), new ResourceLocation(Gempire.MODID, "block/stripped_distant_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_distant_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DISTANT_WOOD.get(), new ResourceLocation(Gempire.MODID, "block/stripped_distant_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_distant_log"));

        blockItem(ModBlocks.DISTANT_LOG);
        blockItem(ModBlocks.DISTANT_WOOD);
        blockItem(ModBlocks.STRIPPED_DISTANT_LOG);
        blockItem(ModBlocks.STRIPPED_DISTANT_WOOD);

        leavesBlock(ModBlocks.DISTANT_LEAVES);
        saplingBlock(ModBlocks.DISTANT_SAPLING);

        signBlock(((StandingSignBlock) ModBlocks.DISTANT_SIGN.get()), ((WallSignBlock) ModBlocks.DISTANT_WALL_SIGN.get()),
                blockTexture(ModBlocks.DISTANT_PLANKS.get()));

        hangingSignBlock(ModBlocks.DISTANT_HANGING_SIGN.get(), ModBlocks.DISTANT_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.DISTANT_PLANKS.get()));


        logBlock(((RotatedPillarBlock) ModBlocks.KALEIDOSCOPE_LOG.get()));
        blockItem(ModBlocks.KALEIDOSCOPE_LOG);
        axisBlock(((RotatedPillarBlock) ModBlocks.KALEIDOSCOPE_WOOD.get()), blockTexture(ModBlocks.KALEIDOSCOPE_LOG.get()), blockTexture(ModBlocks.KALEIDOSCOPE_LOG.get()));
        blockItem(ModBlocks.KALEIDOSCOPE_WOOD);
        leavesBlock(ModBlocks.KALEIDOSCOPE_LEAVES);
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_KALEIDOSCOPE_LOG.get(), new ResourceLocation(Gempire.MODID, "block/stripped_kaleidoscope_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_kaleidoscope_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_KALEIDOSCOPE_WOOD.get(), new ResourceLocation(Gempire.MODID, "block/stripped_kaleidoscope_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_kaleidoscope_log"));

        saplingBlock(ModBlocks.KALEIDOSCOPE_SAPLING);

        blockItem(ModBlocks.STRIPPED_KALEIDOSCOPE_LOG);
        blockItem(ModBlocks.STRIPPED_KALEIDOSCOPE_WOOD);

        signBlock(((StandingSignBlock) ModBlocks.KALEIDOSCOPE_SIGN.get()), ((WallSignBlock) ModBlocks.KALEIDOSCOPE_WALL_SIGN.get()),
                blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));

        hangingSignBlock(ModBlocks.KALEIDOSCOPE_HANGING_SIGN.get(), ModBlocks.KALEIDOSCOPE_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));

        signBlock(((StandingSignBlock) ModBlocks.SHADED_SIGN.get()), ((WallSignBlock) ModBlocks.SHADED_WALL_SIGN.get()),
                blockTexture(ModBlocks.SHADED_PLANKS.get()));

        hangingSignBlock(ModBlocks.SHADED_HANGING_SIGN.get(), ModBlocks.SHADED_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.SHADED_PLANKS.get()));


        logBlock(((RotatedPillarBlock) ModBlocks.SHADED_LOG.get()));
        blockItem(ModBlocks.SHADED_LOG);
        axisBlock(((RotatedPillarBlock) ModBlocks.SHADED_WOOD.get()), blockTexture(ModBlocks.SHADED_LOG.get()), blockTexture(ModBlocks.SHADED_LOG.get()));
        blockItem(ModBlocks.SHADED_WOOD);
        leavesBlock(ModBlocks.SHADED_LEAVES);
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_SHADED_LOG.get(), new ResourceLocation(Gempire.MODID, "block/stripped_shaded_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_shaded_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_SHADED_WOOD.get(), new ResourceLocation(Gempire.MODID, "block/stripped_shaded_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_shaded_log"));

        saplingBlock(ModBlocks.SHADED_SAPLING);

        blockItem(ModBlocks.STRIPPED_SHADED_LOG);
        blockItem(ModBlocks.STRIPPED_SHADED_WOOD);


        logBlock(((RotatedPillarBlock) ModBlocks.CRYSTAL_LOG.get()));
        blockItem(ModBlocks.CRYSTAL_LOG);
        axisBlock(((RotatedPillarBlock) ModBlocks.CRYSTAL_WOOD.get()), blockTexture(ModBlocks.CRYSTAL_LOG.get()), blockTexture(ModBlocks.CRYSTAL_LOG.get()));
        blockItem(ModBlocks.CRYSTAL_WOOD);
        leavesBlock(ModBlocks.CRYSTAL_LEAVES);
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CRYSTAL_LOG.get(), new ResourceLocation(Gempire.MODID, "block/stripped_crystal_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_crystal_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CRYSTAL_WOOD.get(), new ResourceLocation(Gempire.MODID, "block/stripped_crystal_log"),
                new ResourceLocation(Gempire.MODID, "block/stripped_crystal_log"));

        saplingBlock(ModBlocks.CRYSTAL_SAPLING);

        blockItem(ModBlocks.STRIPPED_CRYSTAL_LOG);
        blockItem(ModBlocks.STRIPPED_CRYSTAL_WOOD);

        blockItem(ModBlocks.GALAXY_WARP);
        blockItem(ModBlocks.WARP_PAD);

        blockItem(ModBlocks.PRIMED_SELENITE);

        blockWithItem(ModBlocks.SELENITE);
        stairsBlock((StairBlock) ModBlocks.SELENITE_STAIRS.get(), blockTexture(ModBlocks.SELENITE.get()));
        slabBlock((SlabBlock) ModBlocks.SELENITE_SLAB.get(), blockTexture(ModBlocks.SELENITE.get()), blockTexture(ModBlocks.SELENITE.get()));
        wallBlock((WallBlock) ModBlocks.SELENITE_WALL.get(), blockTexture(ModBlocks.SELENITE.get()));
        blockItem(ModBlocks.SELENITE_STAIRS);
        blockItem(ModBlocks.SELENITE_SLAB);

        blockWithItem(ModBlocks.POLISHED_SELENITE);
        stairsBlock((StairBlock) ModBlocks.POLISHED_SELENITE_STAIRS.get(), blockTexture(ModBlocks.POLISHED_SELENITE.get()));
        slabBlock((SlabBlock) ModBlocks.POLISHED_SELENITE_SLAB.get(), blockTexture(ModBlocks.POLISHED_SELENITE.get()), blockTexture(ModBlocks.POLISHED_SELENITE.get()));
        wallBlock((WallBlock) ModBlocks.POLISHED_SELENITE_WALL.get(), blockTexture(ModBlocks.POLISHED_SELENITE.get()));
        blockItem(ModBlocks.POLISHED_SELENITE_STAIRS);
        blockItem(ModBlocks.POLISHED_SELENITE_SLAB);

        blockWithItem(ModBlocks.ROUGH_SELENITE);

        saplingBlock(ModBlocks.SELENITE_CLUSTER);

        paneBlock(ModBlocks.RED_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_red"), new ResourceLocation(Gempire.MODID, "block/lattice_red"));
        paneBlock(ModBlocks.ORANGE_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_orange"), new ResourceLocation(Gempire.MODID, "block/lattice_orange"));
        paneBlock(ModBlocks.YELLOW_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_yellow"), new ResourceLocation(Gempire.MODID, "block/lattice_yellow"));
        paneBlock(ModBlocks.LIME_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_lime"), new ResourceLocation(Gempire.MODID, "block/lattice_lime"));
        paneBlock(ModBlocks.GREEN_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_green"), new ResourceLocation(Gempire.MODID, "block/lattice_green"));
        paneBlock(ModBlocks.CYAN_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_cyan"), new ResourceLocation(Gempire.MODID, "block/lattice_cyan"));
        paneBlock(ModBlocks.LIGHT_BLUE_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_light_blue"), new ResourceLocation(Gempire.MODID, "block/lattice_light_blue"));
        paneBlock(ModBlocks.BLUE_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_blue"), new ResourceLocation(Gempire.MODID, "block/lattice_blue"));
        paneBlock(ModBlocks.PURPLE_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_purple"), new ResourceLocation(Gempire.MODID, "block/lattice_purple"));
        paneBlock(ModBlocks.MAGENTA_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_magenta"), new ResourceLocation(Gempire.MODID, "block/lattice_magenta"));
        paneBlock(ModBlocks.PINK_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_pink"), new ResourceLocation(Gempire.MODID, "block/lattice_pink"));
        paneBlock(ModBlocks.WHITE_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_white"), new ResourceLocation(Gempire.MODID, "block/lattice_white"));
        paneBlock(ModBlocks.LIGHT_GRAY_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_light_gray"), new ResourceLocation(Gempire.MODID, "block/lattice_light_gray"));
        paneBlock(ModBlocks.GRAY_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_gray"), new ResourceLocation(Gempire.MODID, "block/lattice_gray"));
        paneBlock(ModBlocks.BLACK_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_black"), new ResourceLocation(Gempire.MODID, "block/lattice_black"));
        paneBlock(ModBlocks.BROWN_LATTICE, new ResourceLocation(Gempire.MODID, "block/lattice_brown"), new ResourceLocation(Gempire.MODID, "block/lattice_brown"));

        /*
        blockWithItem(ModBlocks.PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.RED_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.ORANGE_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.YELLOW_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.LIME_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.GREEN_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.CYAN_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.LIGHT_BLUE_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.BLUE_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.PURPLE_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.MAGENTA_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.PINK_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.WHITE_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.LIGHT_GRAY_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.GRAY_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.BLACK_PHOSPHORUS_LAMP);
        blockWithItem(ModBlocks.BROWN_PHOSPHORUS_LAMP);
        */

        blockWithItem(ModBlocks.DESOLATE_SOIL);
        blockWithItem(ModBlocks.SCHIST);

        blockWithItem(ModBlocks.ABNORMAL_SAND);
        blockWithItem(ModBlocks.ABNORMAL_SANDSTONE);

        blockItem(ModBlocks.PINCULE);

        blockItem(ModBlocks.DESOLATE_GRASS);

        saplingBlock(ModBlocks.THULITE_TOWER);
        saplingBlock(ModBlocks.THULITE_CLUSTER);

        flowerBlock(ModBlocks.WHITE_IRIS);
        flowerBlock(ModBlocks.PINK_THISTLE);
        flowerBlock(ModBlocks.PANSIE);
        flowerBlock(ModBlocks.ORCHID);
        flowerBlock(ModBlocks.NASTURTIUMS);
        flowerBlock(ModBlocks.HYDRANGEA_BUSH_WHITE);
        flowerBlock(ModBlocks.HYDRANGEA_BUSH_PINK);
        flowerBlock(ModBlocks.HYDRANGEA_BUSH_BLUE);
        flowerBlock(ModBlocks.HYDRANGEA_BUSH_GREEN);
        flowerBlock(ModBlocks.HYDRANGEA_BUSH_PURPLE);
        flowerBlock(ModBlocks.CHRYSANTHEMUM);
        flowerBlock(ModBlocks.BLUE_BELLS);
        flowerBlock(ModBlocks.BLUE_PUFFBALL);
    }

    public void paneBlock(RegistryObject<Block> block, ResourceLocation texture, ResourceLocation texture1) {
        paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture1, "cutout");
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void flowerBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("gempire:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("gempire:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
