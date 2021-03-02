package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gempire.MODID);

    /*public static final RegistryObject<Block> TEST_FLUID_BLOCK = BLOCKS.register("test_fluid", () -> new TestFluidBlock(
            ModFluids.TEST_FLUID_FLOWING, AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()
    ));
    /*public static final RegistryObject<Block> SHARD_BLOCK = BLOCKS.register("shard_block", () ->
            new TestBlock(Block.Properties
                    .create(Material.PLANTS)
                    .hardnessAndResistance(1.0f, 2.0f)
                    .sound(SoundType.NETHER_WART)
                    .harvestLevel(1)
                    .harvestTool(ToolType.HOE)
            )
    );
    public static final RegistryObject<Block> MYCELIUM_BRICK_BLOCK = BLOCKS.register("mycelium_brick_block", () ->
            new MyceliumBrickBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );
    public static final RegistryObject<Block> MYCELIUM_BRICK_BLOCK_1 = BLOCKS.register("mycelium_brick_block_1", () ->
            new MyceliumBrickBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );
    public static final RegistryObject<Block> MYCELIUM_BRICK_BLOCK_2 = BLOCKS.register("mycelium_brick_block_2", () ->
            new MyceliumBrickBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );
    public static final RegistryObject<Block> MYCELIUM_BRICK_BLOCK_3 = BLOCKS.register("mycelium_brick_block_3", () ->
            new MyceliumBrickBlock(Block.Properties
                    .create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.STONE)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
            )
    );*/
}
