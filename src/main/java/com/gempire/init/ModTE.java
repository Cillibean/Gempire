package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.TankTE;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTE {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Gempire.MODID);
    //public static final RegistryObject<TileEntityType<TestTE>> TEST_TE = TILE_ENTITIES.register("test_te", () -> TileEntityType.Builder.create(TestTE::new, MushBlocks.MYCELIUM_BRICK_BLOCK.get()).build(null));
    /*public static final RegistryObject<TileEntityType<MyceliumBrickTE>> MYCELIUM_BRICK_TE = TILE_ENTITIES.register(
            "mycelium_brick_te", () -> TileEntityType.Builder.create(MyceliumBrickTE::new,
                    MushBlocks.MYCELIUM_BRICK_BLOCK.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_1.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_2.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_3.get()).build(null));*/
    public static final RegistryObject<TileEntityType<GemSeedTE>> GEM_SEED_TE = TILE_ENTITIES.register(
            "gem_seed_te", () -> TileEntityType.Builder.create(GemSeedTE::new, ModBlocks.GEM_SEED_BLOCK.get()).build(null));

    /*public static final RegistryObject<TileEntityType<TestContainerTE>> TEST_CONTAINER_TE = TILE_ENTITIES.register(
            "test_container_te", () -> TileEntityType.Builder.create(TestContainerTE::new, ModBlocks.TEST_CONTAINER_BLOCK.get()).build(null));*/

    public static final RegistryObject<TileEntityType<InjectorTE>> INJECTOR_TE = TILE_ENTITIES.register(
            "injector_te", () -> TileEntityType.Builder.create(InjectorTE::new, ModBlocks.INJECTOR_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<TankTE>> TANK_TE = TILE_ENTITIES.register(
            "tank_te", () -> TileEntityType.Builder.create(TankTE::new, ModBlocks.TANK_BLOCK.get()).build(null));
}
