package com.gempire.util;

import com.gempire.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ModItemGroup extends CreativeModeTab {

    private Supplier<ItemStack> displayStack;

    public static final ModItemGroup GEMSTONES = new ModItemGroup("gemstones", () -> new ItemStack(ModItems.RUBY_GEM.get()));
    public static final ModItemGroup ITEMS = new ModItemGroup("gempire_items", () -> new ItemStack(ModItems.PINK_CHROMA.get()));
    public static final ModItemGroup BLOCKS = new ModItemGroup("gempire_blocks", () -> new ItemStack(ModItems.DRAINED_RED_STONE_2.get()));

    private ModItemGroup(String label, Supplier<ItemStack> displayStack) {
        super(label);
        this.displayStack = displayStack;
    }

    @Override
    public ItemStack makeIcon() { return displayStack.get(); }

    @Override
    public boolean canScroll() {
        return true;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}