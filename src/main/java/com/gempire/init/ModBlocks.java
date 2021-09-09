package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gempire.MODID);

    public static final RegistryObject<Block> WHITE_CHROMA_CRYSTAL = BLOCKS.register("white_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
            , 0)
    );

    public static final RegistryObject<Block> ORANGE_CHROMA_CRYSTAL = BLOCKS.register("orange_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 1)
    );

    public static final RegistryObject<Block> MAGENTA_CHROMA_CRYSTAL = BLOCKS.register("magenta_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 2)
    );

    public static final RegistryObject<Block> LIGHT_BLUE_CHROMA_CRYSTAL = BLOCKS.register("light_blue_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 3)
    );

    public static final RegistryObject<Block> YELLOW_CHROMA_CRYSTAL = BLOCKS.register("yellow_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 4)
    );

    public static final RegistryObject<Block> LIME_CHROMA_CRYSTAL = BLOCKS.register("lime_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 5)
    );

    public static final RegistryObject<Block> PINK_CHROMA_CRYSTAL = BLOCKS.register("pink_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 6)
    );

    public static final RegistryObject<Block> GRAY_CHROMA_CRYSTAL = BLOCKS.register("gray_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 7)
    );

    public static final RegistryObject<Block> LIGHT_GRAY_CHROMA_CRYSTAL = BLOCKS.register("light_gray_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 8)
    );

    public static final RegistryObject<Block> CYAN_CHROMA_CRYSTAL = BLOCKS.register("cyan_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 9)
    );

    public static final RegistryObject<Block> PURPLE_CHROMA_CRYSTAL = BLOCKS.register("purple_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 10)
    );

    public static final RegistryObject<Block> BLUE_CHROMA_CRYSTAL = BLOCKS.register("blue_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 11)
    );

    public static final RegistryObject<Block> BROWN_CHROMA_CRYSTAL = BLOCKS.register("brown_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 12)
    );

    public static final RegistryObject<Block> GREEN_CHROMA_CRYSTAL = BLOCKS.register("green_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 13)
    );

    public static final RegistryObject<Block> RED_CHROMA_CRYSTAL = BLOCKS.register("red_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 14)
    );

    public static final RegistryObject<Block> BLACK_CHROMA_CRYSTAL = BLOCKS.register("black_chroma_crystal", () ->
            new ChromaBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setLightLevel((state) -> {
                        return 10;
                    })
                    , 15)
    );

    public static final RegistryObject<Block> ICE_SPIKE = BLOCKS.register("ice_spike", () ->
            new IceSpikeBlock(Block.Properties
                    .create(Material.ICE)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE))
    );


    public static RegistryObject<FlowingFluidBlock> PINK_ESSENCE_BLOCK = BLOCKS.register("pink_essence_block", () ->
            new FlowingFluidBlock(ModFluids.PINK_ESSENCE, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static RegistryObject<FlowingFluidBlock> BLUE_ESSENCE_BLOCK = BLOCKS.register("blue_essence_block", () ->
            new FlowingFluidBlock(ModFluids.BLUE_ESSENCE, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static RegistryObject<FlowingFluidBlock> YELLOW_ESSENCE_BLOCK = BLOCKS.register("yellow_essence_block", () ->
            new FlowingFluidBlock(ModFluids.YELLOW_ESSENCE, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static RegistryObject<FlowingFluidBlock> WHITE_ESSENCE_BLOCK = BLOCKS.register("white_essence_block", () ->
            new FlowingFluidBlock(ModFluids.WHITE_ESSENCE, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Block> GEM_SEED_BLOCK = BLOCKS.register("gem_seed_block", () ->
            new GemSeedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> POWER_CRYSTAL_BLOCK = BLOCKS.register("power_crystal_block", () ->
            new PowerCrystalBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> TANK_BLOCK = BLOCKS.register("tank_block", () ->
            new TankBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.CHAIN)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRILL_BLOCK = BLOCKS.register("drill_block", () ->
            new InjectorBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> SHELL_BLOCK = BLOCKS.register("shell_block", () ->
            new ShellBlock(Block.Properties
                    .create(Material.ROCK)
                    .hardnessAndResistance(3.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_SOIL = BLOCKS.register("drained_blue_soil", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.EARTH)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.GROUND)
                    .harvestLevel(1)
                    .harvestTool(ToolType.SHOVEL)
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE = BLOCKS.register("drained_blue_stone", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE_2 = BLOCKS.register("drained_blue_stone_2", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_BLUE_STONE = BLOCKS.register("drained_blue_stone_bands", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_SOIL = BLOCKS.register("drained_grey_soil", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.EARTH)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.GROUND)
                    .harvestLevel(1)
                    .harvestTool(ToolType.SHOVEL)
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE = BLOCKS.register("drained_grey_stone", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE_2 = BLOCKS.register("drained_grey_stone_2", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_GREY_STONE = BLOCKS.register("drained_grey_stone_bands", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_SOIL = BLOCKS.register("drained_purple_soil", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.EARTH)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.GROUND)
                    .harvestLevel(1)
                    .harvestTool(ToolType.SHOVEL)
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE = BLOCKS.register("drained_purple_stone", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_2 = BLOCKS.register("drained_purple_stone_2", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_PURPLE_STONE = BLOCKS.register("drained_purple_stone_bands", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_SAND = BLOCKS.register("drained_red_sand", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.SAND)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.SAND)
                    .harvestLevel(1)
                    .harvestTool(ToolType.SHOVEL)
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_STONE = BLOCKS.register("drained_red_stone", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_STONE_2 = BLOCKS.register("drained_red_stone_2", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_RED_STONE = BLOCKS.register("drained_red_stone_bands", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_SAND = BLOCKS.register("drained_sand", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.SAND)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.SAND)
                    .harvestLevel(1)
                    .harvestTool(ToolType.SHOVEL)
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE = BLOCKS.register("drained_yellow_stone", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_2 = BLOCKS.register("drained_yellow_stone_2", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_YELLOW_STONE = BLOCKS.register("drained_yellow_stone_bands", () ->
            new DrainedBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(10f, 10f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> WIRE_BLOCK = BLOCKS.register("wire_block", () ->
            new SixWayConnectorBlock(0.5f, Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(1f, 1f)
                    .sound(SoundType.SNOW)
                    .harvestLevel(0)
                    .harvestTool(ToolType.PICKAXE)
            )
    );

    /*public static final RegistryObject<Block> TEST_CONTAINER_BLOCK = BLOCKS.register("test_container_block", () ->
            new GUITestBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );*/

}
