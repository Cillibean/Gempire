package com.gempire.util;

import com.gempire.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class ModItemGroup extends ItemGroup {

    private Supplier<ItemStack> displayStack;

    public static final ModItemGroup GEMSTONES = new ModItemGroup("gemstones", () -> new ItemStack(ModItems.RUBY_GEM.get()));

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