package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.tileentities.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTE {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Gempire.MODID);
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
            "injector_te", () -> BlockEntityType.Builder.of(InjectorTE::new, ModBlocks.DRILL_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<TankTE>> TANK_TE = TILE_ENTITIES.register(
            "tank_te", () -> BlockEntityType.Builder.of(TankTE::new, ModBlocks.TANK_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PowerCrystalTE>> POWER_CRYSTAL_TE = TILE_ENTITIES.register(
            "power_crystal_te", () -> BlockEntityType.Builder.of(PowerCrystalTE::new, ModBlocks.POWER_CRYSTAL_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<ShellTE>> SHELL_TE = TILE_ENTITIES.register(
            "shell_te", () -> BlockEntityType.Builder.of(ShellTE::new, ModBlocks.SHELL_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<WireTE>> WIRE_TE = TILE_ENTITIES.register(
            "wire_te", () -> BlockEntityType.Builder.of(WireTE::new, ModBlocks.WIRE_BLOCK.get()).build(null));
}
