package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.items.ItemGem;
import com.gempire.items.TestItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Gempire.MODID);
    /*
    public static final RegistryObject<Item> MUSH_SHARD = ITEMS.register("mush_shard", () ->
            new TestItem(new Item.Properties().group(ItemGroup.MATERIALS))
    );
    public static final RegistryObject<Item> SHARD_BLOCK_ITEM = ITEMS.register("shard_block", () ->
            new BlockItem(
                    MushBlocks.SHARD_BLOCK.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Item> HYPHAE = ITEMS.register("hyphae", () ->
            new TestItem(new Item.Properties().group(ItemGroup.MATERIALS))
    );
    public static final RegistryObject<Item> MYCELIUM_BRICK = ITEMS.register("mycelium_brick", () ->
            new TestItem(new Item.Properties().group(ItemGroup.MATERIALS))
    );
    public static final RegistryObject<Item> MOLDY_BREAD = ITEMS.register("moldy_bread", () ->
            new Food.Builder(new Item.Properties().group(ItemGroup.MATERIALS))
    );
    public static final RegistryObject<Item> MYCELIUM_BRICK_BLOCK_ITEM = ITEMS.register("mycelium_brick_block", () ->
            new BlockItem(
                    MushBlocks.MYCELIUM_BRICK_BLOCK.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Item> MYCELIUM_BRICK_BLOCK_1_ITEM = ITEMS.register("mycelium_brick_block_1", () ->
            new BlockItem(
                    MushBlocks.MYCELIUM_BRICK_BLOCK_1.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Item> MYCELIUM_BRICK_BLOCK_2_ITEM = ITEMS.register("mycelium_brick_block_2", () ->
            new BlockItem(
                    MushBlocks.MYCELIUM_BRICK_BLOCK_2.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Item> MYCELIUM_BRICK_BLOCK_3_ITEM = ITEMS.register("mycelium_brick_block_3", () ->
            new BlockItem(
                    MushBlocks.MYCELIUM_BRICK_BLOCK_3.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    */

    public static final RegistryObject<Item> PINK_ESSENCE = ITEMS.register("pink_essence", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> BLUE_ESSENCE = ITEMS.register("blue_essence", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> YELLOW_ESSENCE = ITEMS.register("yellow_essence", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> WHITE_ESSENCE = ITEMS.register("white_essence", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> SAPPHIRE_GEM = ITEMS.register("sapphire_gem", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> RUBY_GEM = ITEMS.register("ruby_gem", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> PEBBLE_GEM = ITEMS.register("pebble_gem", () ->
            new ItemGem(new Item.Properties().group(ItemGroup.MATERIALS).maxStackSize(1).isImmuneToFire()));
}
