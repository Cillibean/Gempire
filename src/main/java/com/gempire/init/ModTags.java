package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Services;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SPELUNKER_VALUABLES
                = tag("spelunker_valuables");

        public static final TagKey<Block> NEEDS_PRISMATIC_TOOL
                = tag("needs_prismatic_tool");

        public static final TagKey<Block> PROSPECTOR_VALUABLES
                = tag("prospector_valuables");

        public static final TagKey<Block> DESOLATE_ORE_REPLACEABLES
                = tag("desolate_ore_replaceables");

        public static final TagKey<Block> EMPRESS_LANDS_ON
                = tag("empress_lands_on");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Gempire.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Gempire.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
