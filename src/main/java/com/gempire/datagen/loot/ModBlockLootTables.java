package com.gempire.datagen.loot;

import com.gempire.datagen.ModBlockStateProvider;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
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
        this.dropSelf(ModBlocks.DRAINED_ICE.get());
        this.dropSelf(ModBlocks.DRAINED_LOG_CRACKED.get());
        this.add(ModBlocks.DRAINED_LOG.get(), (p_124229_) -> {
            return createSingleItemTableWithSilkTouch(p_124229_, ModBlocks.DRAINED_LOG_CRACKED.get(), ConstantValue.exactly(1.0F));
        });

        //machines
        this.add(ModBlocks.SHELL_BLOCK.get(), (block -> createNameableBlockEntityTable(ModBlocks.SHELL_BLOCK.get())));
        this.dropSelf(ModBlocks.POWER_CRYSTAL_BLOCK.get());
        this.dropSelf(ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get());
        this.dropSelf(ModBlocks.DRILL_BLOCK.get());
        this.dropSelf(ModBlocks.PEDISTAL.get());
        this.dropSelfWithContents(ModBlocks.TANK_BLOCK.get());

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

        //marble
        this.dropSelf(ModBlocks.CHISELED_RUINED_MARBLE_BLOCK.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_BLOCK.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_PILLAR.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_STAIRS.get());
        this.dropSelf(ModBlocks.RUINED_MARBLE_BRICK.get());
        this.add(ModBlocks.RUINED_MARBLE_SLAB.get(), BlockLoot::createSlabItemTable);
        this.dropSelf(ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get());
        this.dropSelf(ModBlocks.SMOOTH_RUINED_MARBLE_STAIRS.get());
        this.add(ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get(), BlockLoot::createSlabItemTable);

        //no drop
        this.add(ModBlocks.ICE_SPIKE.get(), (block -> noDrop()));
        this.add(ModBlocks.CHROMA_CLUSTER_CROP.get(), (block -> noDrop()));
        this.add(ModBlocks.WARP_PAD.get(), (block -> noDrop()));
        this.add(ModBlocks.GEM_SEED_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.YELLOW_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.PINK_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.BLUE_ESSENCE_BLOCK.get(), (block -> noDrop()));
        this.add(ModBlocks.WHITE_ESSENCE_BLOCK.get(), (block -> noDrop()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected void dropSelfWithContents(Block block) {
        CopyNbtFunction.Builder nbtBuilder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);

        LootItem.Builder<?> itemLootPool = LootItem.lootTableItem(block);
        itemLootPool.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY));
        itemLootPool.apply(nbtBuilder);
        add(block, LootTable.lootTable().withPool(applyExplosionCondition(true, LootPool.lootPool()
                .name("main")
                .setRolls(ConstantValue.exactly(1))
                .add(itemLootPool)
        )));
    }

    private static <T extends ConditionUserBuilder<T>> T applyExplosionCondition(boolean explosionResistant, ConditionUserBuilder<T> condition) {
        return explosionResistant ? condition.unwrap() : condition.when(ExplosionCondition.survivesExplosion());
    }
}
