package com.gempire.datagen.loot;

import com.gempire.datagen.ModBlockStateProvider;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.nbt.NbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = MatchTool.toolMatches(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().of(new ItemLike[]{Items.SHEARS})).or(MatchTool.toolMatches(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).invert();

    public ModBlockLootTables() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //drained blocks
        this.dropSelf(ModBlocks.DRAINED_BANDED_BLUE_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BANDED_YELLOW_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BANDED_PURPLE_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BANDED_RED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BANDED_GREY_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_SOIL.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_SOIL.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_SOIL.get());
        this.dropSelf(ModBlocks.DRAINED_RED_SOIL.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_SOIL.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_2.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_2.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_2.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_2.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_2.get());
        this.dropSelf(ModBlocks.DRAINED_RED_SAND.get());
        this.dropSelf(ModBlocks.DRAINED_SAND.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_SAND.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_SAND.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_SAND.get());
        this.dropSelf(ModBlocks.DRAINED_ICE.get());
        this.dropSelf(ModBlocks.DRAINED_LOG_CRACKED.get());
        this.add(ModBlocks.DRAINED_LOG.get(), (p_124229_) -> {
            return createSingleItemTableWithSilkTouch(p_124229_, ModBlocks.DRAINED_LOG_CRACKED.get(), ConstantValue.exactly(1.0F));
        });
        this.dropSelf(ModBlocks.DRAINED_BLUE_POLISHED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_POLISHED_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_BLUE_POLISHED_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_POLISHED_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CHISELED.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICK_WALL.get());
        this.add(ModBlocks.DRAINED_BLUE_STONE_BRICK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_STONE_BRICK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_WALL.get());
        this.add(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_WALL.get());
        this.add(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_BLUE_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_BLUE_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_BLUE_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_BLUE_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_BLUE_GLAZED_TILE.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_BLUE_GLASS.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_BLUE_GLASS_PANE.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_POLISHED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CHISELED.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICK_WALL.get());
        this.add(ModBlocks.DRAINED_YELLOW_STONE_BRICK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_STONE_BRICK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_WALL.get());
        this.add(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_WALL.get());
        this.add(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_YELLOW_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_YELLOW_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_YELLOW_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_YELLOW_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_YELLOW_GLAZED_TILE.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_YELLOW_GLASS.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_YELLOW_GLASS_PANE.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_POLISHED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CHISELED.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICK_WALL.get());
        this.add(ModBlocks.DRAINED_PURPLE_STONE_BRICK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_STONE_BRICK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_WALL.get());
        this.add(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_WALL.get());
        this.add(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_PURPLE_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_PURPLE_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_PURPLE_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_PURPLE_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_PURPLE_GLAZED_TILE.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_PURPLE_GLASS.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_PURPLE_GLASS_PANE.get());
        this.dropSelf(ModBlocks.DRAINED_RED_POLISHED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_RED_POLISHED_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_RED_POLISHED_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_POLISHED_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS_CHISELED.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICK_WALL.get());
        this.add(ModBlocks.DRAINED_RED_STONE_BRICK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_STONE_BRICK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_WALL.get());
        this.add(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_WALL.get());
        this.add(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_RED_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_RED_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_RED_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_RED_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_RED_GLAZED_TILE.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_RED_GLASS.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_RED_GLASS_PANE.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_POLISHED_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_POLISHED_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_GREY_POLISHED_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_POLISHED_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS_CHISELED.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICK_WALL.get());
        this.add(ModBlocks.DRAINED_GREY_STONE_BRICK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_STONE_BRICK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED_DARK.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_WALL.get());
        this.add(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED_LIGHT.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_STAIRS.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_WALL.get());
        this.add(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE.get());
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_STAIRS.get());
        this.add(ModBlocks.DRAINED_GREY_STONE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_STONE_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_DARK_STAIRS.get());
        this.add(ModBlocks.DRAINED_GREY_STONE_DARK_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_STONE_DARK_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_STONE_LIGHT_STAIRS.get());
        this.add(ModBlocks.DRAINED_GREY_STONE_LIGHT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DRAINED_GREY_STONE_LIGHT_SLAB.get())));
        this.dropSelf(ModBlocks.DRAINED_GREY_GLAZED_TILE.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_GREY_GLASS.get());
        this.dropWhenSilkTouch(ModBlocks.DRAINED_GREY_GLASS_PANE.get());

        //machines
        this.add(ModBlocks.SHELL_BLOCK.get(), (block -> createNameableBlockEntityTable(ModBlocks.SHELL_BLOCK.get())));
        this.dropSelf(ModBlocks.POWER_CRYSTAL_BLOCK.get());
        this.dropSelf(ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get());
        this.dropSelf(ModBlocks.DRILL_BLOCK.get());
        this.dropSelf(ModBlocks.PEDISTAL.get());
        this.dropSelfWithContents(ModBlocks.TANK_BLOCK.get());
        this.dropSelfWithContents(ModBlocks.INCUBATOR_BLOCK.get());
        this.dropSelf(ModBlocks.WARP_PAD.get());
        this.dropSelf(ModBlocks.BOARD_BLOCK.get());

        //chroma
        this.add(ModBlocks.RED_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.RED_CHROMA_CRYSTAL.get(), ModItems.RED_CHROMA.get())));
        this.add(ModBlocks.ORANGE_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.ORANGE_CHROMA_CRYSTAL.get(), ModItems.ORANGE_CHROMA.get())));
        this.add(ModBlocks.YELLOW_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.YELLOW_CHROMA_CRYSTAL.get(), ModItems.YELLOW_CHROMA.get())));
        this.add(ModBlocks.LIME_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.LIME_CHROMA_CRYSTAL.get(), ModItems.LIME_CHROMA.get())));
        this.add(ModBlocks.GREEN_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.GREEN_CHROMA_CRYSTAL.get(), ModItems.GREEN_CHROMA.get())));
        this.add(ModBlocks.CYAN_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.CYAN_CHROMA_CRYSTAL.get(), ModItems.CYAN_CHROMA.get())));
        this.add(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.LIGHT_BLUE_CHROMA_CRYSTAL.get(), ModItems.LIGHT_BLUE_CHROMA.get())));
        this.add(ModBlocks.BLUE_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.BLUE_CHROMA_CRYSTAL.get(), ModItems.BLUE_CHROMA.get())));
        this.add(ModBlocks.PURPLE_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.PURPLE_CHROMA_CRYSTAL.get(), ModItems.PURPLE_CHROMA.get())));
        this.add(ModBlocks.PINK_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.PINK_CHROMA_CRYSTAL.get(), ModItems.PINK_CHROMA.get())));
        this.add(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.MAGENTA_CHROMA_CRYSTAL.get(), ModItems.MAGENTA_CHROMA.get())));
        this.add(ModBlocks.BROWN_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.BROWN_CHROMA_CRYSTAL.get(), ModItems.BROWN_CHROMA.get())));
        this.add(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.LIGHT_GRAY_CHROMA_CRYSTAL.get(), ModItems.LIGHT_GRAY_CHROMA.get())));
        this.add(ModBlocks.GRAY_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.GRAY_CHROMA_CRYSTAL.get(), ModItems.GRAY_CHROMA.get())));
        this.add(ModBlocks.WHITE_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.WHITE_CHROMA_CRYSTAL.get(), ModItems.WHITE_CHROMA.get())));
        this.add(ModBlocks.BLACK_CHROMA_CRYSTAL.get(), (block -> createOreDrop(ModBlocks.BLACK_CHROMA_CRYSTAL.get(), ModItems.BLACK_CHROMA.get())));

        //minerals
        this.dropSelf(ModBlocks.PRISMATIC_BLOCK.get());
        this.dropSelf(ModBlocks.TUNGSTEN_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
        this.add(ModBlocks.TUNGSTEN_ORE.get(), (block -> createOreDrop(ModBlocks.TUNGSTEN_ORE.get(), ModItems.RAW_TUNGSTEN.get())));
        this.add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), (block -> createOreDrop(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), ModItems.RAW_TUNGSTEN.get())));
        this.add(ModBlocks.PEGMATITE_TUNGSTEN_ORE.get(), (block -> createOreDrop(ModBlocks.PEGMATITE_TUNGSTEN_ORE.get(), ModItems.RAW_TUNGSTEN.get())));
        this.dropSelf(ModBlocks.THULITE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_THULITE_BLOCK.get());
        this.add(ModBlocks.THULITE_ORE.get(), (block -> createOreDrop(ModBlocks.THULITE_ORE.get(), ModItems.RAW_THULITE.get())));
        this.dropSelf(ModBlocks.ANATASE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_ANATASE_BLOCK.get());
        this.add(ModBlocks.ANATASE_ORE.get(), (block -> createOreDrop(ModBlocks.ANATASE_ORE.get(), ModItems.RAW_ANATASE.get())));
        this.dropSelf(ModBlocks.ELECTRUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_ELECTRUM_BLOCK.get());
        this.add(ModBlocks.ELECTRUM_ORE.get(), (block -> createOreDrop(ModBlocks.ELECTRUM_ORE.get(), ModItems.RAW_ELECTRUM.get())));
        this.dropSelf(ModBlocks.PLATINUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_PLATINUM_BLOCK.get());
        this.add(ModBlocks.PLATINUM_ORE.get(), (block -> createOreDrop(ModBlocks.PLATINUM_ORE.get(), ModItems.RAW_PLATINUM.get())));
        this.dropSelf(ModBlocks.PYRITE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_PYRITE_BLOCK.get());
        this.add(ModBlocks.PYRITE_ORE.get(), (block -> createOreDrop(ModBlocks.PYRITE_ORE.get(), ModItems.RAW_PYRITE.get())));
        this.dropSelf(ModBlocks.GEODE_CRYSTAL_BLOCK.get());
        this.add(ModBlocks.SELENITE_CLUSTER.get(), (block -> createOreDrop(ModBlocks.SELENITE_CLUSTER.get(), ModItems.SELENITE_CHUNK.get())));

        //desolate
        this.dropSelf(ModBlocks.ROUGH_SELENITE.get());
        this.dropSelf(ModBlocks.PRIMED_SELENITE.get());
        this.dropSelf(ModBlocks.SELENITE.get());
        this.dropSelf(ModBlocks.SELENITE_WALL.get());
        this.add(ModBlocks.SELENITE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.SELENITE_SLAB.get())));
        this.dropSelf(ModBlocks.SELENITE_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_SELENITE.get());
        this.dropSelf(ModBlocks.POLISHED_SELENITE_WALL.get());
        this.add(ModBlocks.POLISHED_SELENITE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.POLISHED_SELENITE_SLAB.get())));
        this.dropSelf(ModBlocks.POLISHED_SELENITE_STAIRS.get());
        this.dropSelf(ModBlocks.PEGMATITE.get());
        this.dropSelf(ModBlocks.PEGMATITE_WALL.get());
        this.add(ModBlocks.PEGMATITE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.PEGMATITE_SLAB.get())));
        this.dropSelf(ModBlocks.PEGMATITE_STAIRS.get());
        this.dropSelf(ModBlocks.COBBLED_PEGMATITE.get());
        this.dropSelf(ModBlocks.COBBLED_PEGMATITE_WALL.get());
        this.add(ModBlocks.COBBLED_PEGMATITE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.COBBLED_PEGMATITE_SLAB.get())));
        this.dropSelf(ModBlocks.COBBLED_PEGMATITE_STAIRS.get());
        this.dropSelf(ModBlocks.MACADAM.get());
        this.dropSelf(ModBlocks.SCHIST.get());
        this.otherWhenSilkTouch(ModBlocks.DESOLATE_GRASS.get(), ModBlocks.DESOLATE_SOIL.get());
        this.dropSelf(ModBlocks.DESOLATE_SOIL.get());
        this.dropSelf(ModBlocks.ABNORMAL_SAND.get());
        this.dropSelf(ModBlocks.ABNORMAL_SANDSTONE.get());
        this.dropSelf(ModBlocks.PINCULE.get());
        this.add(ModBlocks.PRISMATIC_SNOW.get(), (block -> createSingleItemTableWithSilkTouch(ModBlocks.PRISMATIC_SNOW.get(), Items.SNOWBALL)));
        this.add(ModBlocks.COBALT_KELP.get(), (block -> createSingleItemTable(ModItems.COBALT_KELP.get())));
        this.add(ModBlocks.COBALT_KELP_PLANT.get(), (block -> createSingleItemTable(ModItems.COBALT_KELP.get())));
        this.dropSelf(ModBlocks.AQUATIC_FIBRE.get());
        this.dropSelf(ModBlocks.TALL_AQUATIC_FIBRE.get());
        this.dropSelf(ModBlocks.ALIEN_FLOWER.get());
        this.dropSelf(ModBlocks.WHITE_IRIS.get());
        this.dropSelf(ModBlocks.PANSIE.get());
        this.dropSelf(ModBlocks.ORCHID.get());
        this.dropSelf(ModBlocks.PINK_THISTLE.get());
        this.dropSelf(ModBlocks.NASTURTIUMS.get());
        this.dropSelf(ModBlocks.HYDRANGEA_BUSH_BLUE.get());
        this.dropSelf(ModBlocks.HYDRANGEA_BUSH_GREEN.get());
        this.dropSelf(ModBlocks.HYDRANGEA_BUSH_PINK.get());
        this.dropSelf(ModBlocks.HYDRANGEA_BUSH_PURPLE.get());
        this.dropSelf(ModBlocks.HYDRANGEA_BUSH_WHITE.get());
        this.dropSelf(ModBlocks.CROCOSMIA.get());
        this.dropSelf(ModBlocks.CHRYSANTHEMUM.get());
        this.dropSelf(ModBlocks.BLUE_BELLS.get());
        this.dropSelf(ModBlocks.BLUE_PUFFBALL.get());
        this.dropSelf(ModBlocks.CLOVERS.get());
        this.dropSelf(ModBlocks.THULITE_TOWER.get());
        this.dropSelf(ModBlocks.THULITE_CLUSTER.get());
        this.dropSelf(ModBlocks.PRIMED_ICE.get());
        this.dropSelf(ModBlocks.PRIMED_PACKED_ICE.get());
        this.dropSelf(ModBlocks.PRIMED_BLUE_ICE.get());
        this.dropSelf(ModBlocks.PRIMED_DRAINED_ICE.get());
        this.add(ModBlocks.SHOCK_BERRY_BUSH.get(), (block -> createSingleItemTable(ModItems.SHOCK_BERRY.get())));
        this.dropSelf(ModBlocks.STRAWBERRY_BLOCK.get());

        //wood
        this.dropSelf(ModBlocks.CRYSTAL_LOG.get());
        this.dropSelf(ModBlocks.CRYSTAL_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_CRYSTAL_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_CRYSTAL_WOOD.get());
        this.dropSelf(ModBlocks.CRYSTAL_SAPLING.get());
        this.dropSelf(ModBlocks.CRYSTAL_PLANKS.get());
        this.dropSelf(ModBlocks.CRYSTAL_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CRYSTAL_FENCE.get());
        this.dropSelf(ModBlocks.CRYSTAL_STAIRS.get());
        this.dropSelf(ModBlocks.CRYSTAL_TRAPDOOR.get());
        this.add(ModBlocks.CRYSTAL_SLAB.get(), (block -> createSlabItemTable(ModBlocks.CRYSTAL_SLAB.get())));
        this.add(ModBlocks.CRYSTAL_DOOR.get(), (block -> createDoorTable(ModBlocks.CRYSTAL_DOOR.get())));
        this.add(ModBlocks.CRYSTAL_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.CRYSTAL_HANGING_SIGN.get())));
        this.add(ModBlocks.CRYSTAL_SIGN.get(), (block -> createSingleItemTable(ModItems.CRYSTAL_SIGN.get())));
        this.add(ModBlocks.CRYSTAL_WALL_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.CRYSTAL_HANGING_SIGN.get())));
        this.add(ModBlocks.CRYSTAL_WALL_SIGN.get(), (block -> createSingleItemTable(ModItems.CRYSTAL_SIGN.get())));
        this.dropSelf(ModBlocks.DISTANT_LOG.get());
        this.dropSelf(ModBlocks.DISTANT_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_DISTANT_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_DISTANT_WOOD.get());
        this.dropSelf(ModBlocks.DISTANT_SAPLING.get());
        this.dropSelf(ModBlocks.DISTANT_PLANKS.get());
        this.dropSelf(ModBlocks.DISTANT_FENCE_GATE.get());
        this.dropSelf(ModBlocks.DISTANT_FENCE.get());
        this.dropSelf(ModBlocks.DISTANT_STAIRS.get());
        this.dropSelf(ModBlocks.DISTANT_TRAPDOOR.get());
        this.add(ModBlocks.DISTANT_LEAVES.get(), (block -> createLeavesDrops(ModBlocks.DISTANT_LEAVES.get(), ModBlocks.DISTANT_SAPLING.get())));
        this.add(ModBlocks.DISTANT_SLAB.get(), (block -> createSlabItemTable(ModBlocks.DISTANT_SLAB.get())));
        this.add(ModBlocks.DISTANT_DOOR.get(), (block -> createDoorTable(ModBlocks.DISTANT_DOOR.get())));
        this.add(ModBlocks.DISTANT_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.DISTANT_HANGING_SIGN.get())));
        this.add(ModBlocks.DISTANT_SIGN.get(), (block -> createSingleItemTable(ModItems.DISTANT_SIGN.get())));
        this.add(ModBlocks.DISTANT_WALL_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.DISTANT_HANGING_SIGN.get())));
        this.add(ModBlocks.DISTANT_WALL_SIGN.get(), (block -> createSingleItemTable(ModItems.DISTANT_SIGN.get())));
        this.dropSelf(ModBlocks.KALEIDOSCOPE_LOG.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_KALEIDOSCOPE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_KALEIDOSCOPE_WOOD.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_SAPLING.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_PLANKS.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_FENCE.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_STAIRS.get());
        this.dropSelf(ModBlocks.KALEIDOSCOPE_TRAPDOOR.get());
        this.add(ModBlocks.KALEIDOSCOPE_LEAVES.get(), (block -> createLeavesDrops(ModBlocks.KALEIDOSCOPE_LEAVES.get(), ModBlocks.KALEIDOSCOPE_SAPLING.get())));
        this.add(ModBlocks.KALEIDOSCOPE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.KALEIDOSCOPE_SLAB.get())));
        this.add(ModBlocks.KALEIDOSCOPE_DOOR.get(), (block -> createDoorTable(ModBlocks.KALEIDOSCOPE_DOOR.get())));
        this.add(ModBlocks.KALEIDOSCOPE_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.KALEIDOSCOPE_HANGING_SIGN.get())));
        this.add(ModBlocks.KALEIDOSCOPE_SIGN.get(), (block -> createSingleItemTable(ModItems.KALEIDOSCOPE_SIGN.get())));
        this.add(ModBlocks.KALEIDOSCOPE_WALL_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.KALEIDOSCOPE_HANGING_SIGN.get())));
        this.add(ModBlocks.KALEIDOSCOPE_WALL_SIGN.get(), (block -> createSingleItemTable(ModItems.KALEIDOSCOPE_SIGN.get())));
        this.dropSelf(ModBlocks.SHADED_LOG.get());
        this.dropSelf(ModBlocks.SHADED_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_SHADED_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_SHADED_WOOD.get());
        this.dropSelf(ModBlocks.SHADED_SAPLING.get());
        this.dropSelf(ModBlocks.SHADED_PLANKS.get());
        this.dropSelf(ModBlocks.SHADED_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SHADED_FENCE.get());
        this.dropSelf(ModBlocks.SHADED_STAIRS.get());
        this.dropSelf(ModBlocks.SHADED_TRAPDOOR.get());
        this.add(ModBlocks.SHADED_LEAVES.get(), (block -> createShadedLeavesDrops(ModBlocks.SHADED_LEAVES.get(), ModBlocks.SHADED_SAPLING.get())));
        this.add(ModBlocks.SHADED_SLAB.get(), (block -> createSlabItemTable(ModBlocks.SHADED_SLAB.get())));
        this.add(ModBlocks.SHADED_DOOR.get(), (block -> createDoorTable(ModBlocks.SHADED_DOOR.get())));
        this.add(ModBlocks.SHADED_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.SHADED_HANGING_SIGN.get())));
        this.add(ModBlocks.SHADED_SIGN.get(), (block -> createSingleItemTable(ModItems.SHADED_SIGN.get())));
        this.add(ModBlocks.SHADED_WALL_HANGING_SIGN.get(), (block -> createSingleItemTable(ModItems.SHADED_HANGING_SIGN.get())));
        this.add(ModBlocks.SHADED_WALL_SIGN.get(), (block -> createSingleItemTable(ModItems.SHADED_SIGN.get())));


        //marble
        this.dropSelf(ModBlocks.CHISELED_RUINED_MARBLE_BLOCK.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_BLOCK.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_PILLAR.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_STAIRS.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_BRICK.get());
        this.add(ModBlocks.RUINED_MARBLE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.RUINED_MARBLE_SLAB.get())));
        this.dropSelf(ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get());
        this.dropSelf(ModBlocks.SMOOTH_RUINED_MARBLE_STAIRS.get());
        this.add(ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get(), (block -> createSlabItemTable(ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get())));

        //deco
        this.dropSelf(ModBlocks.RED_LATTICE.get());
        this.dropSelf(ModBlocks.ORANGE_LATTICE.get());
        this.dropSelf(ModBlocks.YELLOW_LATTICE.get());
        this.dropSelf(ModBlocks.LIME_LATTICE.get());
        this.dropSelf(ModBlocks.GREEN_LATTICE.get());
        this.dropSelf(ModBlocks.CYAN_LATTICE.get());
        this.dropSelf(ModBlocks.LIGHT_BLUE_LATTICE.get());
        this.dropSelf(ModBlocks.BLUE_LATTICE.get());
        this.dropSelf(ModBlocks.PURPLE_LATTICE.get());
        this.dropSelf(ModBlocks.PINK_LATTICE.get());
        this.dropSelf(ModBlocks.MAGENTA_LATTICE.get());
        this.dropSelf(ModBlocks.BROWN_LATTICE.get());
        this.dropSelf(ModBlocks.LIGHT_GRAY_LATTICE.get());
        this.dropSelf(ModBlocks.GRAY_LATTICE.get());
        this.dropSelf(ModBlocks.WHITE_LATTICE.get());
        this.dropSelf(ModBlocks.BLACK_LATTICE.get());
        this.dropSelf(ModBlocks.PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.RED_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.ORANGE_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.YELLOW_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.LIME_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.GREEN_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.CYAN_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.LIGHT_BLUE_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.BLUE_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.PURPLE_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.PINK_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.MAGENTA_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.BROWN_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.LIGHT_GRAY_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.GRAY_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.BLACK_PHOSPHORUS_LAMP.get());
        this.dropSelf(ModBlocks.RED_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.ORANGE_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.YELLOW_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.LIME_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.GREEN_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.CYAN_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.LIGHT_BLUE_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.BLUE_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.PURPLE_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.PINK_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.MAGENTA_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.BROWN_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.LIGHT_GRAY_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.GRAY_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.WHITE_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.BLACK_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.PRISMATIC_DIAMOND_GLASS.get());
        this.dropSelf(ModBlocks.PURIFIED_PRISMATIC_GLASS.get());

        //no drop
        this.add(ModBlocks.ICE_SPIKE.get(), (block -> noDrop()));
        this.add(ModBlocks.CHROMA_CLUSTER_CROP.get(), (block -> noDrop()));
        this.add(ModBlocks.GALAXY_WARP.get(), (block -> noDrop()));
        this.add(ModBlocks.CRACKED_GALAXY_WARP.get(), (block -> noDrop()));
        this.add(ModBlocks.GEM_SEED_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.YELLOW_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.PINK_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.BLUE_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.WHITE_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.PINK_ALTAR.get(), (block -> noDrop()));
        this.add(ModBlocks.BLUE_ALTAR.get(), (block -> noDrop()));
        this.add(ModBlocks.YELLOW_ALTAR.get(), (block -> noDrop()));
        this.add(ModBlocks.WHITE_ALTAR.get(), (block -> noDrop()));
        this.add(ModBlocks.BUDDING_SELENITE.get(), (block -> noDrop()));
        this.add(ModBlocks.CRYSTAL_LEAVES.get(), (block -> noDrop()));
        this.add(ModBlocks.PRIMED_ICE_STATUE.get(), (block -> noDrop()));
        this.add(ModBlocks.PRIMED_PACKED_ICE_STATUE.get(), (block -> noDrop()));
        this.add(ModBlocks.PRIMED_BLUE_ICE_STATUE.get(), (block -> noDrop()));
        this.add(ModBlocks.PRIMED_DRAINED_ICE_STATUE.get(), (block -> noDrop()));
        this.add(ModBlocks.STRAWBERRY_STEM.get(), (block -> noDrop()));
        this.add(ModBlocks.ATTACHED_STRAWBERRY_STEM.get(), (block -> noDrop()));
        this.add(ModBlocks.PINK_DESTAB_WALL.get(), (block -> noDrop()));
        this.add(ModBlocks.BLUE_DESTAB_WALL.get(), (block -> noDrop()));
        this.add(ModBlocks.YELLOW_DESTAB_WALL.get(), (block -> noDrop()));
        this.add(ModBlocks.WHITE_DESTAB_WALL.get(), (block -> noDrop()));

        //else
        this.dropSelf(ModBlocks.CONGEALED_YELLOW_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.CONGEALED_PINK_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.CONGEALED_BLUE_ESSENCE_BLOCK.get());
        this.dropSelf(ModBlocks.CONGEALED_WHITE_ESSENCE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> list = ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
        //list.remove(ModBlocks.TANK_BLOCK.get());
        return list;
    }

    protected void dropSelfWithContents(Block block) {
        CopyNbtFunction.Builder nbtBuilder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);

        LootItem.Builder<?> itemLootPool = LootItem.lootTableItem(block);
        itemLootPool.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY));
        itemLootPool.apply(nbtBuilder);
        itemLootPool.apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                .copy("inventory", "BlockEntityTag.inventory", CopyNbtFunction.MergeStrategy.REPLACE)
                .copy("pinkTank", "BlockEntityTag.pinkTank", CopyNbtFunction.MergeStrategy.REPLACE)
                .copy("blueTank", "BlockEntityTag.blueTank", CopyNbtFunction.MergeStrategy.REPLACE)
                .copy("yellowTank", "BlockEntityTag.yellowTank", CopyNbtFunction.MergeStrategy.REPLACE)
                .copy("whiteTank", "BlockEntityTag.whiteTank", CopyNbtFunction.MergeStrategy.REPLACE));
        //itemLootPool.apply(SetContainerContents.setContents().withEntry(DynamicLootEntry.dynamicEntry(new ResourceLocation("minecraft", "contents"))));
        add(block, LootTable.lootTable().withPool(applyExplosionCondition(true, LootPool.lootPool()
                .name("main")
                .setRolls(ConstantValue.exactly(1))
                .add(itemLootPool)
        )));
    }

    protected LootTable.Builder createSlabItemTable(Block p_251313_) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(p_251313_, LootItem.lootTableItem(p_251313_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_251313_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected LootTable.Builder createShadedLeavesDrops(Block p_249535_, Block p_251505_, float... p_250753_) {
        return this.createLeavesDrops(p_249535_, p_251505_, p_250753_).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(p_249535_, LootItem.lootTableItem(ModItems.SHADED_APPLE.get()))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F}))));
    }

    private static <T extends ConditionUserBuilder<T>> T applyExplosionCondition(boolean explosionResistant, ConditionUserBuilder<T> condition) {
        return explosionResistant ? condition.unwrap() : condition.when(ExplosionCondition.survivesExplosion());
    }
}
