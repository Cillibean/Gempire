package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.tileentities.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTE {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Gempire.MODID);
    //public static final RegistryObject<TileEntityType<TestTE>> TEST_TE = TILE_ENTITIES.register("test_te", () -> TileEntityType.Builder.create(TestTE::new, MushBlocks.MYCELIUM_BRICK_BLOCK.get()).build(null));
    /*public static final RegistryObject<TileEntityType<MyceliumBrickTE>> MYCELIUM_BRICK_TE = TILE_ENTITIES.register(
            "mycelium_brick_te", () -> TileEntityType.Builder.create(MyceliumBrickTE::new,
                    MushBlocks.MYCELIUM_BRICK_BLOCK.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_1.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_2.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_3.get()).build(null));*/
    public static final RegistryObject<BlockEntityType<GemSeedTE>> GEM_SEED_TE = TILE_ENTITIES.register(
            "gem_seed_te", () -> BlockEntityType.Builder.of(GemSeedTE::new, ModBlocks.GEM_SEED_BLOCK.get()).build(null));

    /*public static final RegistryObject<TileEntityType<TestContainerTE>> TEST_CONTAINER_TE = TILE_ENTITIES.register(
            "test_container_te", () -> TileEntityType.Builder.create(TestContainerTE::new, ModBlocks.TEST_CONTAINER_BLOCK.get()).build(null));*/

    public static final RegistryObject<BlockEntityType<InjectorTE>> INJECTOR_TE = TILE_ENTITIES.register(
            "injector_te", () -> BlockEntityType.Builder.of(InjectorTE::new, ModBlocks.TANK_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<WarpPadTE>> WARP_PAD_TE = TILE_ENTITIES.register(
            "warp_pad_te", () -> BlockEntityType.Builder.of(WarpPadTE::new, ModBlocks.WARP_PAD.get()).build(null));

    public static final RegistryObject<BlockEntityType<ShellTE>> SHELL_TE = TILE_ENTITIES.register(
            "shell_te", () -> BlockEntityType.Builder.of(ShellTE::new, ModBlocks.SHELL_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<IncubatorTE>> INCUBATOR_TE = TILE_ENTITIES.register(
            "incubator_te", () -> BlockEntityType.Builder.of(IncubatorTE::new, ModBlocks.INCUBATOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PedistalTE>> PEDISTAL_TE = TILE_ENTITIES.register(
            "pedistal_te", () -> BlockEntityType.Builder.of(PedistalTE::new, ModBlocks.PEDISTAL.get()).build(null));

}
