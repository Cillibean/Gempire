package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.blocks.*;
import com.gempire.blocks.machine.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gempire.MODID);

    public static final RegistryObject<Block> WHITE_CHROMA_CRYSTAL = BLOCKS.register("white_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .lightLevel((state) -> {
                        return 10;
                    })
            , 0)
    );

    public static final RegistryObject<Block> ORANGE_CHROMA_CRYSTAL = BLOCKS.register("orange_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 1)
    );

    public static final RegistryObject<Block> MAGENTA_CHROMA_CRYSTAL = BLOCKS.register("magenta_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 2)
    );

    public static final RegistryObject<Block> LIGHT_BLUE_CHROMA_CRYSTAL = BLOCKS.register("light_blue_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 3)
    );

    public static final RegistryObject<Block> YELLOW_CHROMA_CRYSTAL = BLOCKS.register("yellow_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 4)
    );

    public static final RegistryObject<Block> LIME_CHROMA_CRYSTAL = BLOCKS.register("lime_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 5)
    );

    public static final RegistryObject<Block> PINK_CHROMA_CRYSTAL = BLOCKS.register("pink_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 6)
    );

    public static final RegistryObject<Block> GRAY_CHROMA_CRYSTAL = BLOCKS.register("gray_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 7)
    );

    public static final RegistryObject<Block> LIGHT_GRAY_CHROMA_CRYSTAL = BLOCKS.register("light_gray_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 8)
    );

    public static final RegistryObject<Block> CYAN_CHROMA_CRYSTAL = BLOCKS.register("cyan_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 9)
    );

    public static final RegistryObject<Block> PURPLE_CHROMA_CRYSTAL = BLOCKS.register("purple_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 10)
    );

    public static final RegistryObject<Block> BLUE_CHROMA_CRYSTAL = BLOCKS.register("blue_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 11)
    );

    public static final RegistryObject<Block> BROWN_CHROMA_CRYSTAL = BLOCKS.register("brown_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 12)
    );

    public static final RegistryObject<Block> GREEN_CHROMA_CRYSTAL = BLOCKS.register("green_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 13)
    );

    public static final RegistryObject<Block> RED_CHROMA_CRYSTAL = BLOCKS.register("red_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 14)
    );

    public static final RegistryObject<Block> BLACK_CHROMA_CRYSTAL = BLOCKS.register("black_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
                    .lightLevel((state) -> {
                        return 10;
                    })
                    , 15)
    );

    public static final RegistryObject<Block> ICE_SPIKE = BLOCKS.register("ice_spike", () ->
            new IceSpikeBlock(BlockBehaviour.Properties
                    .of(Material.ICE)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    )
    );

    public static final RegistryObject<Block> FLOATANIUM_REACTOR = BLOCKS.register("floatanium_reactor", () ->
            new Block(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(5, 5)
                    .sound(SoundType.GLASS)
                    
            )
    );


    public static final RegistryObject<LiquidBlock> PINK_ESSENCE_BLOCK = BLOCKS.register("pink_essence_block",
            () -> new LiquidBlock(ModFluids.SOURCE_PINK_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> BLUE_ESSENCE_BLOCK = BLOCKS.register("blue_essence_block",
            () -> new LiquidBlock(ModFluids.SOURCE_BLUE_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> YELLOW_ESSENCE_BLOCK = BLOCKS.register("yellow_essence_block",
            () -> new LiquidBlock(ModFluids.SOURCE_YELLOW_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> WHITE_ESSENCE_BLOCK = BLOCKS.register("white_essence_block",
            () -> new LiquidBlock(ModFluids.SOURCE_WHITE_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<Block> GEM_SEED_BLOCK = BLOCKS.register("gem_seed_block", () ->
            new GemSeedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> POWER_CRYSTAL_BLOCK = BLOCKS.register("power_crystal_block", () ->
            new PowerCrystalBlock(1f, BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    
            )
    );

    public static final RegistryObject<Block> TANK_BLOCK = BLOCKS.register("tank_block", () ->
            new TankBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.CHAIN)
                    
            )
    );

    public static final RegistryObject<Block> DRILL_BLOCK = BLOCKS.register("drill_block", () ->
            new InjectorBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> SHELL_BLOCK = BLOCKS.register("shell_block", () ->
            new ShellBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_SOIL = BLOCKS.register("drained_blue_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.DIRT)
                    .strength(10f, 10f)
                    .sound(SoundType.GRAVEL)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE = BLOCKS.register("drained_blue_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE_2 = BLOCKS.register("drained_blue_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_BLUE_STONE = BLOCKS.register("drained_blue_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_SOIL = BLOCKS.register("drained_grey_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.DIRT)
                    .strength(10f, 10f)
                    .sound(SoundType.GRAVEL)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE = BLOCKS.register("drained_grey_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE_2 = BLOCKS.register("drained_grey_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_GREY_STONE = BLOCKS.register("drained_grey_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_SOIL = BLOCKS.register("drained_purple_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.DIRT)
                    .strength(10f, 10f)
                    .sound(SoundType.GRAVEL)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE = BLOCKS.register("drained_purple_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_2 = BLOCKS.register("drained_purple_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_PURPLE_STONE = BLOCKS.register("drained_purple_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_SAND = BLOCKS.register("drained_red_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.SAND)
                    .strength(10f, 10f)
                    .sound(SoundType.SAND)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_STONE = BLOCKS.register("drained_red_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_STONE_2 = BLOCKS.register("drained_red_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_RED_STONE = BLOCKS.register("drained_red_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_SAND = BLOCKS.register("drained_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.SAND)
                    .strength(10f, 10f)
                    .sound(SoundType.SAND)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE = BLOCKS.register("drained_yellow_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_2 = BLOCKS.register("drained_yellow_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_YELLOW_STONE = BLOCKS.register("drained_yellow_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> WARP_CORE = BLOCKS.register("warp_core", () ->
            new Block(Block.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 4.0f)
                    .sound(SoundType.GLASS)
            )
    );

    public static final RegistryObject<Block> WARP_PAD = BLOCKS.register("warp_pad", () ->
            new WarpPadBlock(Block.Properties
                    .of(Material.METAL)
                    .strength(3.0f, 4.0f)
                    .sound(SoundType.GLASS)
            )
    );

    public static final RegistryObject<Block> RUINED_MARBLE_BLOCK = BLOCKS.register("ruined_marble_block", () ->
            new Block(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> SMOOTH_RUINED_MARBLE_BLOCK = BLOCKS.register("smooth_ruined_marble_block", () ->
            new Block(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> CHISELED_RUINED_MARBLE_BLOCK = BLOCKS.register("chiseled_ruined_marble_block", () ->
            new Block(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_BRICK = BLOCKS.register("ruined_marble_brick", () ->
            new Block(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_PILLAR = BLOCKS.register("ruined_marble_pillar", () ->
            new RotatedPillarBlock(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> SMOOTH_RUINED_MARBLE_SLAB = BLOCKS.register("smooth_ruined_marble_slab", () ->
            new SlabBlock(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_SLAB = BLOCKS.register("ruined_marble_slab", () ->
            new SlabBlock(Block.Properties
                    .of(Material.STONE)
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_STAIRS = BLOCKS.register("ruined_marble_stairs",
            () -> new StairBlock(() -> ModBlocks.RUINED_MARBLE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_RUINED_MARBLE_STAIRS = BLOCKS.register("smooth_ruined_marble_stairs",
            () -> new StairBlock(() -> ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

    /*public static final RegistryObject<Block> TEST_CONTAINER_BLOCK = BLOCKS.register("test_container_block", () ->
            new GUITestBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    
            )
    );*/

}
