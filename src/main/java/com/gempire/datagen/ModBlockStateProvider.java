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
        blockWithItem(ModBlocks.RAW_TUNGSTEN_BLOCK);

        blockWithItem(ModBlocks.PYRITE_BLOCK);
        blockWithItem(ModBlocks.GEODE_CRYSTAL_BLOCK);

        blockWithItem(ModBlocks.PRISMATIC_BLOCK);

        blockWithItem(ModBlocks.STRAWBERRY_BLOCK);

        blockWithItem(ModBlocks.DISTANT_PLANKS);
        blockWithItem(ModBlocks.KALEIDOSCOPE_PLANKS);

        stairsBlock((StairBlock) ModBlocks.DISTANT_STAIRS.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.DISTANT_SLAB.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()), blockTexture(ModBlocks.DISTANT_PLANKS.get()));

        stairsBlock((StairBlock) ModBlocks.KALEIDOSCOPE_STAIRS.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.KALEIDOSCOPE_SLAB.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.DISTANT_FENCE.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.DISTANT_FENCE_GATE.get(), blockTexture(ModBlocks.DISTANT_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.KALEIDOSCOPE_FENCE.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.KALEIDOSCOPE_FENCE_GATE.get(), blockTexture(ModBlocks.KALEIDOSCOPE_PLANKS.get()));
        /*
        doorBlockWithRenderType((DoorBlock)ModBlocks.DISTANT_DOOR.get(), modLoc("block/distant_door_bottom"), modLoc("block/distant_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.DISTANT_TRAPDOOR.get(), modLoc("block/distant_trapdoor"), true, "cutout");

         */

        blockItem(ModBlocks.DISTANT_STAIRS);
        blockItem(ModBlocks.DISTANT_SLAB);
        blockItem(ModBlocks.DISTANT_FENCE_GATE);
        //blockItem(ModBlocks.DISTANT_TRAPDOOR, "_bottom");

        blockItem(ModBlocks.KALEIDOSCOPE_STAIRS);
        blockItem(ModBlocks.KALEIDOSCOPE_SLAB);
        blockItem(ModBlocks.KALEIDOSCOPE_FENCE_GATE);


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
    }

    public void paneBlock(RegistryObject<Block> block, ResourceLocation texture, ResourceLocation texture1) {
        paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture1, "cutout");
        blockItem(block, "post");
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
