package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.blocks.GemSeedBlock;
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

    /*public static RegistryObject<FlowingFluidBlock> TEST_FLUID_BLOCK = BLOCKS.register("test_fluid_block", () ->
                    new FlowingFluidBlock(ModFluids.TEST_FLUID, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
*/
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
}
