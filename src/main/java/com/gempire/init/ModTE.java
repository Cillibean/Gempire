package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTE {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Gempire.MODID);
    //public static final RegistryObject<TileEntityType<TestTE>> TEST_TE = TILE_ENTITIES.register("test_te", () -> TileEntityType.Builder.create(TestTE::new, MushBlocks.MYCELIUM_BRICK_BLOCK.get()).build(null));
    /*public static final RegistryObject<TileEntityType<MyceliumBrickTE>> MYCELIUM_BRICK_TE = TILE_ENTITIES.register(
            "mycelium_brick_te", () -> TileEntityType.Builder.create(MyceliumBrickTE::new,
                    MushBlocks.MYCELIUM_BRICK_BLOCK.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_1.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_2.get(),
                    MushBlocks.MYCELIUM_BRICK_BLOCK_3.get()).build(null));*/
}
