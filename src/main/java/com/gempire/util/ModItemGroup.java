package com.gempire.util;

import com.gempire.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class ModItemGroup extends ItemGroup {

    private Supplier<ItemStack> displayStack;

    public static final ModItemGroup GEMSTONES = new ModItemGroup("gemstones", () -> new ItemStack(ModItems.RUBY_GEM.get()));
    public static final ModItemGroup ITEMS = new ModItemGroup("items", () -> new ItemStack(ModItems.PINK_ESSENCE.get()));
    public static final ModItemGroup BLOCKS = new ModItemGroup("blocks", () -> new ItemStack(ModItems.GEM_SEED_BLOCK_ITEM.get()));

    private ModItemGroup(String label, Supplier<ItemStack> displayStack) {
        super(label);
        this.displayStack = displayStack;
    }

    @Override
    public ItemStack createIcon() { return displayStack.get(); }

    @Override
    public boolean hasScrollbar() {
        return true;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}