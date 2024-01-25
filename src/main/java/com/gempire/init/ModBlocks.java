package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.blocks.*;
import com.gempire.blocks.machine.*;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    //TODO: IMPLEMENT BLOCKS
    // PHOSPHORUS COLUMN - a wall-sized light block that comes in all colours that morganite makes
    // ICE STATUES - larimars statues
    // DIAMOND STAINED GLASS - a stained glass with a pattern that morganite makes
    // DESTAB FIELDS - hurt gems when touched

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gempire.MODID);

    public static final RegistryObject<Block> BOARD_BLOCK = BLOCKS.register("board_block", () ->
            new BoardBlock(BlockBehaviour.Properties
                    .of()
                    .mapColor(MapColor.METAL)
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.CHAIN)
                    .noOcclusion()
            )
    );
    public static final RegistryObject<Block> ALTAR = BLOCKS.register("altar", () ->
            new AltarBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)));

    public static final RegistryObject<Block> GALAXY_WARP = BLOCKS.register("galaxy_warp", () ->
            new GalaxyWarpBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)));
    public static final RegistryObject<LiquidBlock> PINK_ESSENCE_BLOCK = BLOCKS.register("pink_essence_block", () ->
            new PinkEssenceBlock(ModFluids.PINK_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> BLUE_ESSENCE_BLOCK = BLOCKS.register("blue_essence_block", () ->
            new BlueEssenceBlock(ModFluids.BLUE_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> YELLOW_ESSENCE_BLOCK = BLOCKS.register("yellow_essence_block", () ->
            new YellowEssenceBlock(ModFluids.YELLOW_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> WHITE_ESSENCE_BLOCK = BLOCKS.register("white_essence_block", () ->
            new WhiteEssenceBlock(ModFluids.WHITE_ESSENCE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<Block> CONGEALED_WHITE_ESSENCE_BLOCK = BLOCKS.register("congealed_white_essence_block", () ->
            new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)));

    public static final RegistryObject<Block> CONGEALED_PINK_ESSENCE_BLOCK = BLOCKS.register("congealed_pink_essence_block", () ->
            new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)));

    public static final RegistryObject<Block> CONGEALED_YELLOW_ESSENCE_BLOCK = BLOCKS.register("congealed_yellow_essence_block", () ->
            new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)));

    public static final RegistryObject<Block> CONGEALED_BLUE_ESSENCE_BLOCK = BLOCKS.register("congealed_blue_essence_block", () ->
            new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)));

    public static final RegistryObject<Block> TUNGSTEN_ORE = BLOCKS.register("tungsten_ore", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> DEEPSLATE_TUNGSTEN_ORE = BLOCKS.register("deepslate_tungsten_ore", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> TUNGSTEN_BLOCK = BLOCKS.register("tungsten_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> RAW_TUNGSTEN_BLOCK = BLOCKS.register("raw_tungsten_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));

    public static final RegistryObject<Block> PYRITE_BLOCK = BLOCKS.register("pyrite_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> GEODE_CRYSTAL_BLOCK = BLOCKS.register("geode_crystal_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    /*public static final RegistryObject<Block> CRYSTAL_CHEST = BLOCKS.register("crystal_chest", () ->
            new CrystalChestBlock(BlockBehaviour.Properties
                    .of().strength(2.5F)
                    .sound(SoundType.GLASS), () -> {
        return BlockEntityType.CHEST;
    }));*/

    public static final RegistryObject<Block> WHITE_CHROMA_CRYSTAL = BLOCKS.register("white_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
            , 0)
    );

    public static final RegistryObject<Block> ORANGE_CHROMA_CRYSTAL = BLOCKS.register("orange_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 1)
    );

    public static final RegistryObject<Block> MAGENTA_CHROMA_CRYSTAL = BLOCKS.register("magenta_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 2)
    );

    public static final RegistryObject<Block> LIGHT_BLUE_CHROMA_CRYSTAL = BLOCKS.register("light_blue_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 3)
    );

    public static final RegistryObject<Block> YELLOW_CHROMA_CRYSTAL = BLOCKS.register("yellow_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 4)
    );

    public static final RegistryObject<Block> LIME_CHROMA_CRYSTAL = BLOCKS.register("lime_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 5)
    );

    public static final RegistryObject<Block> PINK_CHROMA_CRYSTAL = BLOCKS.register("pink_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 6)
    );

    public static final RegistryObject<Block> GRAY_CHROMA_CRYSTAL = BLOCKS.register("gray_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 7)
    );

    public static final RegistryObject<Block> LIGHT_GRAY_CHROMA_CRYSTAL = BLOCKS.register("light_gray_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 8)
    );

    public static final RegistryObject<Block> CYAN_CHROMA_CRYSTAL = BLOCKS.register("cyan_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 9)
    );

    public static final RegistryObject<Block> PURPLE_CHROMA_CRYSTAL = BLOCKS.register("purple_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 10)
    );

    public static final RegistryObject<Block> BLUE_CHROMA_CRYSTAL = BLOCKS.register("blue_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 11)
    );

    public static final RegistryObject<Block> BROWN_CHROMA_CRYSTAL = BLOCKS.register("brown_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 12)
    );

    public static final RegistryObject<Block> GREEN_CHROMA_CRYSTAL = BLOCKS.register("green_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 13)
    );

    public static final RegistryObject<Block> RED_CHROMA_CRYSTAL = BLOCKS.register("red_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 14)
    );

    public static final RegistryObject<Block> BLACK_CHROMA_CRYSTAL = BLOCKS.register("black_chroma_crystal", () ->
            new ChromaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()

                    .lightLevel((state) -> {
                        return 3;
                    })
                    , 15)
    );

    public static final RegistryObject<Block> ICE_SPIKE = BLOCKS.register("ice_spike", () ->
            new IceSpikeBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.GLASS)

                    )
    );

    public static final RegistryObject<Block> PEDISTAL = BLOCKS.register("pedistal", () ->
            new PedistalBlock(BlockBehaviour.Properties.of().noOcclusion(),true)
            );

    public static final RegistryObject<Block> GEM_SEED_BLOCK = BLOCKS.register("gem_seed_block", () ->
            new GemSeedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.STONE)
            )
    );

    public static final RegistryObject<Block> POWER_CRYSTAL_BLOCK = BLOCKS.register("power_crystal_block", () ->
            new PowerCrystalBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.AMETHYST)
                    .noOcclusion()
            )
    );

    public static final RegistryObject<Block> POWER_CRYSTAL_BLOCK_TIER_2 = BLOCKS.register("power_crystal_block_tier_2", () ->
            new PowerCrystalBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.AMETHYST)
                    .lightLevel((state) -> {
                        return 10;
                    })
                    .noOcclusion()
            )
    );

    public static final RegistryObject<Block> TANK_BLOCK = BLOCKS.register("tank_block", () ->
            new TankBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.CHAIN)
                    .noOcclusion()
            )
    );

    public static final RegistryObject<Block> DRILL_BLOCK = BLOCKS.register("drill_block", () ->
            new DrillBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()
            )
    );

    public static final RegistryObject<Block> SHELL_BLOCK = BLOCKS.register("shell_block", () ->
            new ShellBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .instabreak()
            )
    );

    public static final RegistryObject<Block> INCUBATOR_BLOCK = BLOCKS.register("incubator_block", () ->
            new IncubatorBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_SOIL = BLOCKS.register("drained_blue_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.GRAVEL)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_SAND = BLOCKS.register("drained_blue_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.SAND)

            )
    );

    public static final RegistryObject<Block> PRISMATIC_BLOCK = BLOCKS.register("prismatic_block", () ->
            new Block(BlockBehaviour.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(4.6f, 4.6f)
                    .sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE = BLOCKS.register("drained_blue_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE_2 = BLOCKS.register("drained_blue_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_BLUE_STONE = BLOCKS.register("drained_blue_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_SOIL = BLOCKS.register("drained_grey_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.GRAVEL)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_SAND = BLOCKS.register("drained_grey_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.SAND)

            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE = BLOCKS.register("drained_grey_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE_2 = BLOCKS.register("drained_grey_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_GREY_STONE = BLOCKS.register("drained_grey_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_SOIL = BLOCKS.register("drained_purple_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.GRAVEL)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_SAND = BLOCKS.register("drained_purple_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.SAND)

            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE = BLOCKS.register("drained_purple_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(1.5f, 10f).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_2 = BLOCKS.register("drained_purple_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_PURPLE_STONE = BLOCKS.register("drained_purple_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_SOIL = BLOCKS.register("drained_red_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.GRAVEL)

            )
    );

    public static final RegistryObject<Block> DRAINED_RED_SAND = BLOCKS.register("drained_red_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.SAND)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_STONE = BLOCKS.register("drained_red_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_RED_STONE_2 = BLOCKS.register("drained_red_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_RED_STONE = BLOCKS.register("drained_red_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_SOIL = BLOCKS.register("drained_yellow_soil", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.GRAVEL)

            )
    );

    public static final RegistryObject<Block> DRAINED_SAND = BLOCKS.register("drained_sand", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.6f, 0.6f)
                    .sound(SoundType.SAND)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE = BLOCKS.register("drained_yellow_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    
            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_2 = BLOCKS.register("drained_yellow_stone_2", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );

    public static final RegistryObject<Block> DRAINED_BANDED_YELLOW_STONE = BLOCKS.register("drained_yellow_stone_bands", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );

    public static final RegistryObject<Block> DRAINED_LOG = BLOCKS.register("drained_log", () ->
            new RotatedPillarBlock(Block.Properties
                    .of()
                    .strength(1.5f, 0.8f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
            )
    );
    public static final RegistryObject<Block> DRAINED_LOG_CRACKED = BLOCKS.register("drained_log_cracked", () ->
            new RotatedPillarBlock(Block.Properties
                    .of()
                    .strength(1.5f, 0.8f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
            )
    );
    public static final RegistryObject<Block> DRAINED_ICE = BLOCKS.register("drained_ice", () ->
            new RotatedPillarBlock(Block.Properties
                    .of()
                    .strength(1.5f, 0.8f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> PRIMED_ICE = BLOCKS.register("primed_ice", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.ICE)));

    public static final RegistryObject<Block> PRIMED_PACKED_ICE = BLOCKS.register("primed_packed_ice", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)));

    public static final RegistryObject<Block> PRIMED_BLUE_ICE = BLOCKS.register("primed_blue_ice", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE)));

    public static final RegistryObject<Block> PRIMED_DRAINED_ICE = BLOCKS.register("primed_drained_ice", () ->
            new Block(BlockBehaviour.Properties.copy(ModBlocks.DRAINED_ICE.get())));

    public static final RegistryObject<Block> PRIMED_ICE_STATUE = BLOCKS.register("primed_ice_statue", () ->
            new LarimarStatueBlock(BlockBehaviour.Properties.copy(Blocks.ICE), 0));

    public static final RegistryObject<Block> PRIMED_PACKED_ICE_STATUE = BLOCKS.register("primed_packed_ice_statue", () ->
            new LarimarStatueBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE), 1));

    public static final RegistryObject<Block> PRIMED_BLUE_ICE_STATUE = BLOCKS.register("primed_blue_ice_statue", () ->
            new LarimarStatueBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE), 2));

    public static final RegistryObject<Block> PRIMED_DRAINED_ICE_STATUE = BLOCKS.register("primed_drained_ice_statue", () ->
            new LarimarStatueBlock(BlockBehaviour.Properties.copy(ModBlocks.DRAINED_ICE.get()), 3));

    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE = BLOCKS.register("drained_blue_polished_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_DARK = BLOCKS.register("drained_blue_polished_stone_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_DARK_SLAB = BLOCKS.register("drained_blue_polished_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_DARK_STAIRS = BLOCKS.register("drained_blue_polished_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_LIGHT = BLOCKS.register("drained_blue_polished_stone_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_LIGHT_SLAB = BLOCKS.register("drained_blue_polished_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_LIGHT_STAIRS = BLOCKS.register("drained_blue_polished_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_SLAB = BLOCKS.register("drained_blue_polished_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_POLISHED_STONE_STAIRS = BLOCKS.register("drained_blue_polished_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_POLISHED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_DARK_SLAB = BLOCKS.register("drained_blue_stone_brick_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_DARK_STAIRS = BLOCKS.register("drained_blue_stone_brick_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_STONE_BRICKS_DARK.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_DARK_WALL = BLOCKS.register("drained_blue_stone_brick_dark_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
                    .dynamicShape()
            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_LIGHT_SLAB = BLOCKS.register("drained_blue_stone_brick_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_LIGHT_STAIRS = BLOCKS.register("drained_blue_stone_brick_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_STONE_BRICKS_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_LIGHT_WALL = BLOCKS.register("drained_blue_stone_brick_light_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
                    .dynamicShape()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_SLAB = BLOCKS.register("drained_blue_stone_brick_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_STAIRS = BLOCKS.register("drained_blue_stone_brick_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICK_WALL = BLOCKS.register("drained_blue_stone_brick_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
                    .dynamicShape()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS = BLOCKS.register("drained_blue_stone_bricks", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS_LIGHT = BLOCKS.register("drained_blue_stone_bricks_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS_DARK = BLOCKS.register("drained_blue_stone_bricks_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_SLAB = BLOCKS.register("drained_blue_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_DARK_SLAB = BLOCKS.register("drained_blue_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_LIGHT_SLAB = BLOCKS.register("drained_blue_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_STAIRS = BLOCKS.register("drained_blue_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_LIGHT_STAIRS = BLOCKS.register("drained_blue_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BLUE_STONE_2.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );

    public static final RegistryObject<Block> DRAINED_BLUE_STONE_DARK_STAIRS = BLOCKS.register("drained_blue_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BANDED_BLUE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS_CHISELED = BLOCKS.register("drained_blue_stone_bricks_chiseled", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS_CRACKED = BLOCKS.register("drained_blue_stone_bricks_cracked", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS_CRACKED_LIGHT = BLOCKS.register("drained_blue_stone_bricks_cracked_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_STONE_BRICKS_CRACKED_DARK = BLOCKS.register("drained_blue_stone_bricks_cracked_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_GLAZED_TILE = BLOCKS.register("drained_blue_glazed_tile", () ->
            new GlazedTerracottaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_GLASS = BLOCKS.register("drained_blue_glass", () ->
            new GlassBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
            )
    );
    public static final RegistryObject<Block> DRAINED_BLUE_GLASS_PANE = BLOCKS.register("drained_blue_glass_pane", () ->
            new IronBarsBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );

    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE = BLOCKS.register("drained_red_polished_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_DARK = BLOCKS.register("drained_red_polished_stone_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_DARK_SLAB = BLOCKS.register("drained_red_polished_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_DARK_STAIRS = BLOCKS.register("drained_red_polished_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_LIGHT = BLOCKS.register("drained_red_polished_stone_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_LIGHT_SLAB = BLOCKS.register("drained_red_polished_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_LIGHT_STAIRS = BLOCKS.register("drained_red_polished_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_SLAB = BLOCKS.register("drained_red_polished_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_POLISHED_STONE_STAIRS = BLOCKS.register("drained_red_polished_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_POLISHED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_DARK_SLAB = BLOCKS.register("drained_red_stone_brick_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_DARK_STAIRS = BLOCKS.register("drained_red_stone_brick_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_STONE_BRICKS_DARK.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_DARK_WALL = BLOCKS.register("drained_red_stone_brick_dark_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_LIGHT_SLAB = BLOCKS.register("drained_red_stone_brick_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_LIGHT_STAIRS = BLOCKS.register("drained_red_stone_brick_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_STONE_BRICKS_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_LIGHT_WALL = BLOCKS.register("drained_red_stone_brick_light_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_SLAB = BLOCKS.register("drained_red_stone_brick_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_STAIRS = BLOCKS.register("drained_red_stone_brick_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICK_WALL = BLOCKS.register("drained_red_stone_brick_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS = BLOCKS.register("drained_red_stone_bricks", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS_LIGHT = BLOCKS.register("drained_red_stone_bricks_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS_DARK = BLOCKS.register("drained_red_stone_bricks_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_SLAB = BLOCKS.register("drained_red_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_DARK_SLAB = BLOCKS.register("drained_red_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_LIGHT_SLAB = BLOCKS.register("drained_red_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_STAIRS = BLOCKS.register("drained_red_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_LIGHT_STAIRS = BLOCKS.register("drained_red_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_RED_STONE_2.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );

    public static final RegistryObject<Block> DRAINED_RED_STONE_DARK_STAIRS = BLOCKS.register("drained_red_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BANDED_RED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS_CHISELED = BLOCKS.register("drained_red_stone_bricks_chiseled", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS_CRACKED = BLOCKS.register("drained_red_stone_bricks_cracked", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS_CRACKED_LIGHT = BLOCKS.register("drained_red_stone_bricks_cracked_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_STONE_BRICKS_CRACKED_DARK = BLOCKS.register("drained_red_stone_bricks_cracked_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_GLAZED_TILE = BLOCKS.register("drained_red_glazed_tile", () ->
            new GlazedTerracottaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_GLASS = BLOCKS.register("drained_red_glass", () ->
            new GlassBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );
    public static final RegistryObject<Block> DRAINED_RED_GLASS_PANE = BLOCKS.register("drained_red_glass_pane", () ->
            new IronBarsBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );

    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE = BLOCKS.register("drained_yellow_polished_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_DARK = BLOCKS.register("drained_yellow_polished_stone_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_DARK_SLAB = BLOCKS.register("drained_yellow_polished_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_DARK_STAIRS = BLOCKS.register("drained_yellow_polished_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_LIGHT = BLOCKS.register("drained_yellow_polished_stone_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_LIGHT_SLAB = BLOCKS.register("drained_yellow_polished_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_LIGHT_STAIRS = BLOCKS.register("drained_yellow_polished_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_SLAB = BLOCKS.register("drained_yellow_polished_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_POLISHED_STONE_STAIRS = BLOCKS.register("drained_yellow_polished_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_POLISHED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_DARK_SLAB = BLOCKS.register("drained_yellow_stone_brick_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_DARK_STAIRS = BLOCKS.register("drained_yellow_stone_brick_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_STONE_BRICKS_DARK.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_DARK_WALL = BLOCKS.register("drained_yellow_stone_brick_dark_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_LIGHT_SLAB = BLOCKS.register("drained_yellow_stone_brick_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_LIGHT_STAIRS = BLOCKS.register("drained_yellow_stone_brick_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_STONE_BRICKS_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_LIGHT_WALL = BLOCKS.register("drained_yellow_stone_brick_light_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_SLAB = BLOCKS.register("drained_yellow_stone_brick_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_STAIRS = BLOCKS.register("drained_yellow_stone_brick_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICK_WALL = BLOCKS.register("drained_yellow_stone_brick_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS = BLOCKS.register("drained_yellow_stone_bricks", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS_LIGHT = BLOCKS.register("drained_yellow_stone_bricks_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS_DARK = BLOCKS.register("drained_yellow_stone_bricks_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_SLAB = BLOCKS.register("drained_yellow_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_DARK_SLAB = BLOCKS.register("drained_yellow_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_LIGHT_SLAB = BLOCKS.register("drained_yellow_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_STAIRS = BLOCKS.register("drained_yellow_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_LIGHT_STAIRS = BLOCKS.register("drained_yellow_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_YELLOW_STONE_2.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );

    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_DARK_STAIRS = BLOCKS.register("drained_yellow_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BANDED_YELLOW_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS_CHISELED = BLOCKS.register("drained_yellow_stone_bricks_chiseled", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS_CRACKED = BLOCKS.register("drained_yellow_stone_bricks_cracked", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS_CRACKED_LIGHT = BLOCKS.register("drained_yellow_stone_bricks_cracked_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_STONE_BRICKS_CRACKED_DARK = BLOCKS.register("drained_yellow_stone_bricks_cracked_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_GLAZED_TILE = BLOCKS.register("drained_yellow_glazed_tile", () ->
            new GlazedTerracottaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_GLASS = BLOCKS.register("drained_yellow_glass", () ->
            new GlassBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );
    public static final RegistryObject<Block> DRAINED_YELLOW_GLASS_PANE = BLOCKS.register("drained_yellow_glass_pane", () ->
            new IronBarsBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );

    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE = BLOCKS.register("drained_purple_polished_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_DARK = BLOCKS.register("drained_purple_polished_stone_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_DARK_SLAB = BLOCKS.register("drained_purple_polished_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_DARK_STAIRS = BLOCKS.register("drained_purple_polished_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_LIGHT = BLOCKS.register("drained_purple_polished_stone_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_LIGHT_SLAB = BLOCKS.register("drained_purple_polished_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_LIGHT_STAIRS = BLOCKS.register("drained_purple_polished_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_SLAB = BLOCKS.register("drained_purple_polished_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_POLISHED_STONE_STAIRS = BLOCKS.register("drained_purple_polished_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_POLISHED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_DARK_SLAB = BLOCKS.register("drained_purple_stone_brick_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_DARK_STAIRS = BLOCKS.register("drained_purple_stone_brick_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_STONE_BRICKS_DARK.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_DARK_WALL = BLOCKS.register("drained_purple_stone_brick_dark_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_LIGHT_SLAB = BLOCKS.register("drained_purple_stone_brick_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_LIGHT_STAIRS = BLOCKS.register("drained_purple_stone_brick_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_STONE_BRICKS_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_LIGHT_WALL = BLOCKS.register("drained_purple_stone_brick_light_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_SLAB = BLOCKS.register("drained_purple_stone_brick_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_STAIRS = BLOCKS.register("drained_purple_stone_brick_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICK_WALL = BLOCKS.register("drained_purple_stone_brick_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS = BLOCKS.register("drained_purple_stone_bricks", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS_LIGHT = BLOCKS.register("drained_purple_stone_bricks_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS_DARK = BLOCKS.register("drained_purple_stone_bricks_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_SLAB = BLOCKS.register("drained_purple_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_DARK_SLAB = BLOCKS.register("drained_purple_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_LIGHT_SLAB = BLOCKS.register("drained_purple_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_STAIRS = BLOCKS.register("drained_purple_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_LIGHT_STAIRS = BLOCKS.register("drained_purple_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_PURPLE_STONE_2.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );

    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_DARK_STAIRS = BLOCKS.register("drained_purple_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BANDED_PURPLE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS_CHISELED = BLOCKS.register("drained_purple_stone_bricks_chiseled", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS_CRACKED = BLOCKS.register("drained_purple_stone_bricks_cracked", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS_CRACKED_LIGHT = BLOCKS.register("drained_purple_stone_bricks_cracked_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_STONE_BRICKS_CRACKED_DARK = BLOCKS.register("drained_purple_stone_bricks_cracked_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_GLAZED_TILE = BLOCKS.register("drained_purple_glazed_tile", () ->
            new GlazedTerracottaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_GLASS = BLOCKS.register("drained_purple_glass", () ->
            new GlassBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );
    public static final RegistryObject<Block> DRAINED_PURPLE_GLASS_PANE = BLOCKS.register("drained_purple_glass_pane", () ->
            new IronBarsBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );

    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE = BLOCKS.register("drained_grey_polished_stone", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_DARK = BLOCKS.register("drained_grey_polished_stone_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_DARK_SLAB = BLOCKS.register("drained_grey_polished_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_DARK_STAIRS = BLOCKS.register("drained_grey_polished_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_LIGHT = BLOCKS.register("drained_grey_polished_stone_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_LIGHT_SLAB = BLOCKS.register("drained_grey_polished_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_LIGHT_STAIRS = BLOCKS.register("drained_grey_polished_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_SLAB = BLOCKS.register("drained_grey_polished_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_POLISHED_STONE_STAIRS = BLOCKS.register("drained_grey_polished_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_POLISHED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_DARK_SLAB = BLOCKS.register("drained_grey_stone_brick_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_DARK_STAIRS = BLOCKS.register("drained_grey_stone_brick_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_STONE_BRICKS_DARK.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_DARK_WALL = BLOCKS.register("drained_grey_stone_brick_dark_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_LIGHT_SLAB = BLOCKS.register("drained_grey_stone_brick_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_LIGHT_STAIRS = BLOCKS.register("drained_grey_stone_brick_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_STONE_BRICKS_LIGHT.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_LIGHT_WALL = BLOCKS.register("drained_grey_stone_brick_light_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_SLAB = BLOCKS.register("drained_grey_stone_brick_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_STAIRS = BLOCKS.register("drained_grey_stone_brick_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICK_WALL = BLOCKS.register("drained_grey_stone_brick_wall", () ->
            new WallBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS = BLOCKS.register("drained_grey_stone_bricks", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS_LIGHT = BLOCKS.register("drained_grey_stone_bricks_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS_DARK = BLOCKS.register("drained_grey_stone_bricks_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_SLAB = BLOCKS.register("drained_grey_stone_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_DARK_SLAB = BLOCKS.register("drained_grey_stone_dark_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_LIGHT_SLAB = BLOCKS.register("drained_grey_stone_light_slab", () ->
            new SlabBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_STAIRS = BLOCKS.register("drained_grey_stone_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_LIGHT_STAIRS = BLOCKS.register("drained_grey_stone_light_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_GREY_STONE_2.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );

    public static final RegistryObject<Block> DRAINED_GREY_STONE_DARK_STAIRS = BLOCKS.register("drained_grey_stone_dark_stairs", () ->
            new StairBlock(() -> ModBlocks.DRAINED_BANDED_GREY_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .of()
                            .strength(1.5f)
                            .requiresCorrectToolForDrops())

    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS_CHISELED = BLOCKS.register("drained_grey_stone_bricks_chiseled", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS_CRACKED = BLOCKS.register("drained_grey_stone_bricks_cracked", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS_CRACKED_LIGHT = BLOCKS.register("drained_grey_stone_bricks_cracked_light", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_STONE_BRICKS_CRACKED_DARK = BLOCKS.register("drained_grey_stone_bricks_cracked_dark", () ->
            new DrainedBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_GLAZED_TILE = BLOCKS.register("drained_grey_glazed_tile", () ->
            new GlazedTerracottaBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f, 10f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_GLASS = BLOCKS.register("drained_grey_glass", () ->
            new GlassBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );
    public static final RegistryObject<Block> DRAINED_GREY_GLASS_PANE = BLOCKS.register("drained_grey_glass_pane", () ->
            new IronBarsBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );

    /*public static final RegistryObject<Block> WARP_CORE = BLOCKS.register("warp_core", () ->
            new Block(Block.Properties
                    .of()
                    .strength(3.0f, 4.0f)
                    .sound(SoundType.GLASS)
            )
    );*/

    public static final RegistryObject<Block> WARP_PAD = BLOCKS.register("warp_pad", () ->
            new WarpPadBlock(Block.Properties.of()
                    .strength(3.0f, 4.0f)
                    .sound(SoundType.GLASS)
            )
    );

    public static final RegistryObject<Block> STRAWBERRY_BLOCK = BLOCKS.register("strawberry_block", () ->
            new StrawberryBlock(Block.Properties.of()
                    .strength(3.0f, 4.0f)
                    .sound(SoundType.WART_BLOCK)
            )
    );

    public static final RegistryObject<Block> STRAWBERRY_STEM = BLOCKS.register("strawberry_stem", () ->
            new StemBlock((StemGrownBlock) STRAWBERRY_BLOCK.get(), ModItems.STRAWBERRY,
                    BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().randomTicks()
                            .instabreak().sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> ATTACHED_STRAWBERRY_STEM = BLOCKS.register("attached_strawberry_stem", () ->
            new AttachedStemBlock((StemGrownBlock) STRAWBERRY_BLOCK.get(), ModItems.STRAWBERRY,
                    BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().randomTicks()
                            .instabreak().sound(SoundType.HARD_CROP)));

    /*public static final RegistryObject<Block> DESTAB_WALL = BLOCKS.register("destab_wall", () ->
            new DestabWallBlock(Block.Properties
                    .of()
                    .strength(3.0f, 4.0f)
                    .sound(SoundType.GLASS)
                    .speedFactor(0.4F)
                    .noOcclusion()
            )
    );*/

    public static final RegistryObject<Block> RUINED_MARBLE_BLOCK = BLOCKS.register("ruined_marble_block", () ->
            new Block(Block.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> SMOOTH_RUINED_MARBLE_BLOCK = BLOCKS.register("smooth_ruined_marble_block", () ->
            new Block(Block.Properties
                    .of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> CHISELED_RUINED_MARBLE_BLOCK = BLOCKS.register("chiseled_ruined_marble_block", () ->
            new Block(Block.Properties
                    .of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_BRICK = BLOCKS.register("ruined_marble_brick", () ->
            new Block(Block.Properties
                    .of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_PILLAR = BLOCKS.register("ruined_marble_pillar", () ->
            new RotatedPillarBlock(Block.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> SMOOTH_RUINED_MARBLE_SLAB = BLOCKS.register("smooth_ruined_marble_slab", () ->
            new SlabBlock(Block.Properties
                    .of()
                    .strength(1.5f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_SLAB = BLOCKS.register("ruined_marble_slab", () ->
            new SlabBlock(Block.Properties
                    .of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 6f)
                    .sound(SoundType.STONE)
            )
    );
    public static final RegistryObject<Block> RUINED_MARBLE_STAIRS = BLOCKS.register("ruined_marble_stairs",
            () -> new StairBlock(() -> ModBlocks.RUINED_MARBLE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(1.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SMOOTH_RUINED_MARBLE_STAIRS = BLOCKS.register("smooth_ruined_marble_stairs",
            () -> new StairBlock(() -> ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(1.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CHROMA_CLUSTER_CROP = BLOCKS.register("chroma_cluster_crop", () ->
            new ChromaClusterCropBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).randomTicks().strength(3.0f, 6.0f).sound(SoundType.GLASS).noOcclusion()));
}
