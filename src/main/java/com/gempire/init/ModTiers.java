package com.gempire.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier PRISMATIC = new ForgeTier(5, 1741, 10.0F,
            4.0F, 15, ModTags.Blocks.NEEDS_PRISMATIC_TOOL,
            () -> Ingredient.of(ModItems.PRISMATIC_INGOT.get()));

    public static final ForgeTier SHARD = new ForgeTier(6, 1741, 10.0F,
            4.5F, 17, ModTags.Blocks.NEEDS_PRISMATIC_TOOL,
            () -> Ingredient.of(ModItems.SPECIAL_SHARDS.get()));
}
