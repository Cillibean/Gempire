package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import com.gempire.init.ModItems;
import net.minecraft.data.tags.ItemTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, Gempire.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.ANATASE_INGOT.get(),
                        ModItems.THULITE_INGOT.get(),
                        ModItems.PLATINUM_INGOT.get(),
                        ModItems.ELECTRUM_INGOT.get(),
                        ModItems.TUNGSTEN_INGOT.get(),
                        ModItems.PRISMATIC_INGOT.get()
                );

        this.tag(ItemTags.FLOWERS)
                .add(ModItems.ALIEN_FLOWER.get(),
                        ModItems.WHITE_IRIS.get(),
                        ModItems.PINK_THISTLE.get(),
                        ModItems.PANSIE.get(),
                        ModItems.ORCHID.get(),
                        ModItems.NASTURTIUMS.get(),
                        ModItems.HYDRANGEA_BUSH_WHITE.get(),
                        ModItems.HYDRANGEA_BUSH_BLUE.get(),
                        ModItems.HYDRANGEA_BUSH_GREEN.get(),
                        ModItems.HYDRANGEA_BUSH_PINK.get(),
                        ModItems.HYDRANGEA_BUSH_PURPLE.get(),
                        ModItems.CROCOSMIA.get(),
                        ModItems.CHRYSANTHEMUM.get(),
                        ModItems.BLUE_PUFFBALL.get(),
                        ModItems.BLUE_BELLS.get());

        this.tag(ItemTags.AXES)
                .add(ModItems.PRISMATIC_AXE.get(),
                        ModItems.PALADIN_AXE.get());

        this.tag(ItemTags.PICKAXES)
                .add(ModItems.PRISMATIC_PICKAXE.get());

        this.tag(ItemTags.HOES)
                .add(ModItems.PRISMATIC_HOE.get());

        this.tag(ItemTags.SHOVELS)
                .add(ModItems.PRISMATIC_SHOVEL.get());

        this.tag(ItemTags.SWORDS)
                .add(ModItems.PRISMATIC_SWORD.get(),
                        ModItems.HUNTRESS_SWORD.get());

        this.tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.PRISMATIC_INGOT.get());

        this.tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModItems.PEGMATITE.get());

        this.tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModItems.PEGMATITE.get());

        this.tag(ItemTags.SAPLINGS)
                .add(ModItems.DISTANT_SAPLING.get(),
                        ModItems.CRYSTAL_SAPLING.get());

        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.FRAGMENTS_MUSIC_DISC.get(),
                        ModItems.REMNANTS_MUSIC_DISC.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModItems.DISTANT_WOOD.get())
                .add(ModItems.DISTANT_LOG.get())
                .add(ModItems.STRIPPED_DISTANT_LOG.get())
                .add(ModItems.STRIPPED_DISTANT_WOOD.get())
                .add(ModItems.CRYSTAL_WOOD.get())
                .add(ModItems.CRYSTAL_LOG.get())
                .add(ModItems.STRIPPED_CRYSTAL_LOG.get())
                .add(ModItems.STRIPPED_CRYSTAL_WOOD.get());
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
