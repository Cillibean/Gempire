package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Gempire.MODID);

    public static final RegistryObject<CreativeModeTab> GEMPIRE_GEMSTONES = CREATIVE_MODE_TABS.register("gempire_gemstones",
    () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.gempire_gemstones"))
            // Set icon of creative tab
            .icon(() -> new ItemStack(ModItems.RUBY_GEM.get()))
            .displayItems((params, populator) -> {
        populator.accept(ModItems.AQUAMARINE_GEM.get());
        populator.accept(ModItems.NEPHRITE_GEM.get());
        populator.accept(ModItems.BISMUTH_GEM.get());
        populator.accept(ModItems.NACRE_GEM.get());
        populator.accept(ModItems.PEBBLE_GEM.get());
        populator.accept(ModItems.SHALE_GEM.get());
        populator.accept(ModItems.MICA_GEM.get());
        populator.accept(ModItems.PERIDOT_GEM.get());
        populator.accept(ModItems.RUTILE_GEM.get());
        populator.accept(ModItems.BIXBITE_GEM.get());
        populator.accept(ModItems.EMERALD_GEM.get());
        populator.accept(ModItems.RUBY_GEM.get());
        populator.accept(ModItems.LAPIS_GEM.get());
        populator.accept(ModItems.LARIMAR_GEM.get());
        populator.accept(ModItems.MORGANITE_GEM.get());
        populator.accept(ModItems.OBSIDIAN_GEM.get());

        populator.accept(ModItems.PINK_SAPPHIRE_GEM.get());
        populator.accept(ModItems.ORANGE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.YELLOW_SAPPHIRE_GEM.get());
        populator.accept(ModItems.LIME_SAPPHIRE_GEM.get());
        populator.accept(ModItems.GREEN_SAPPHIRE_GEM.get());
        populator.accept(ModItems.CYAN_SAPPHIRE_GEM.get());
        populator.accept(ModItems.LIGHT_BLUE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.BLUE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.PURPLE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.MAGENTA_SAPPHIRE_GEM.get());
        populator.accept(ModItems.BROWN_SAPPHIRE_GEM.get());
        populator.accept(ModItems.LIGHT_GRAY_SAPPHIRE_GEM.get());
        populator.accept(ModItems.GRAY_SAPPHIRE_GEM.get());
        populator.accept(ModItems.BLACK_SAPPHIRE_GEM.get());

        populator.accept(ModItems.PINK_SAPPHIRE_GEM.get());
        populator.accept(ModItems.ORANGE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.YELLOW_SAPPHIRE_GEM.get());
        populator.accept(ModItems.LIME_SAPPHIRE_GEM.get());
        populator.accept(ModItems.GREEN_SAPPHIRE_GEM.get());
        populator.accept(ModItems.CYAN_SAPPHIRE_GEM.get());
        populator.accept(ModItems.LIGHT_BLUE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.BLUE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.PURPLE_SAPPHIRE_GEM.get());
        populator.accept(ModItems.MAGENTA_SAPPHIRE_GEM.get());
        populator.accept(ModItems.BROWN_SAPPHIRE_GEM.get());
        populator.accept(ModItems.LIGHT_GRAY_SAPPHIRE_GEM.get());
        populator.accept(ModItems.GRAY_SAPPHIRE_GEM.get());
        populator.accept(ModItems.BLACK_SAPPHIRE_GEM.get());
        populator.accept(ModItems.WHITE_SAPPHIRE_GEM.get());

        populator.accept(ModItems.PINK_ZIRCON_GEM.get());
        populator.accept(ModItems.RED_ZIRCON_GEM.get());
        populator.accept(ModItems.ORANGE_ZIRCON_GEM.get());
        populator.accept(ModItems.YELLOW_ZIRCON_GEM.get());
        populator.accept(ModItems.LIME_ZIRCON_GEM.get());
        populator.accept(ModItems.GREEN_ZIRCON_GEM.get());
        populator.accept(ModItems.CYAN_ZIRCON_GEM.get());
        populator.accept(ModItems.LIGHT_BLUE_ZIRCON_GEM.get());
        populator.accept(ModItems.BLUE_ZIRCON_GEM.get());
        populator.accept(ModItems.PURPLE_ZIRCON_GEM.get());
        populator.accept(ModItems.MAGENTA_ZIRCON_GEM.get());
        populator.accept(ModItems.BROWN_ZIRCON_GEM.get());
        populator.accept(ModItems.LIGHT_GRAY_ZIRCON_GEM.get());
        populator.accept(ModItems.GRAY_ZIRCON_GEM.get());
        populator.accept(ModItems.BLACK_ZIRCON_GEM.get());
        populator.accept(ModItems.WHITE_ZIRCON_GEM.get());

        populator.accept(ModItems.PINK_PEARL_GEM.get());
        populator.accept(ModItems.RED_PEARL_GEM.get());
        populator.accept(ModItems.ORANGE_PEARL_GEM.get());
        populator.accept(ModItems.YELLOW_PEARL_GEM.get());
        populator.accept(ModItems.LIME_PEARL_GEM.get());
        populator.accept(ModItems.GREEN_PEARL_GEM.get());
        populator.accept(ModItems.CYAN_PEARL_GEM.get());
        populator.accept(ModItems.LIGHT_BLUE_PEARL_GEM.get());
        populator.accept(ModItems.BLUE_PEARL_GEM.get());
        populator.accept(ModItems.PURPLE_PEARL_GEM.get());
        populator.accept(ModItems.MAGENTA_PEARL_GEM.get());
        populator.accept(ModItems.BROWN_PEARL_GEM.get());
        populator.accept(ModItems.LIGHT_GRAY_PEARL_GEM.get());
        populator.accept(ModItems.GRAY_PEARL_GEM.get());
        populator.accept(ModItems.BLACK_PEARL_GEM.get());
        populator.accept(ModItems.WHITE_PEARL_GEM.get());

        populator.accept(ModItems.PINK_SPINEL_GEM.get());
        populator.accept(ModItems.RED_SPINEL_GEM.get());
        populator.accept(ModItems.ORANGE_SPINEL_GEM.get());
        populator.accept(ModItems.YELLOW_SPINEL_GEM.get());
        populator.accept(ModItems.LIME_SPINEL_GEM.get());
        populator.accept(ModItems.GREEN_SPINEL_GEM.get());
        populator.accept(ModItems.CYAN_SPINEL_GEM.get());
        populator.accept(ModItems.LIGHT_BLUE_SPINEL_GEM.get());
        populator.accept(ModItems.BLUE_SPINEL_GEM.get());
        populator.accept(ModItems.PURPLE_SPINEL_GEM.get());
        populator.accept(ModItems.MAGENTA_SPINEL_GEM.get());
        populator.accept(ModItems.BROWN_SPINEL_GEM.get());
        populator.accept(ModItems.LIGHT_GRAY_SPINEL_GEM.get());
        populator.accept(ModItems.GRAY_SPINEL_GEM.get());
        populator.accept(ModItems.BLACK_SPINEL_GEM.get());
        populator.accept(ModItems.WHITE_SPINEL_GEM.get());

        populator.accept(ModItems.PINK_TOPAZ_GEM.get());
        populator.accept(ModItems.YELLOW_TOPAZ_GEM.get());
        populator.accept(ModItems.BLUE_TOPAZ_GEM.get());
        populator.accept(ModItems.WHITE_TOPAZ_GEM.get());

        populator.accept(ModItems.SPODUMENE_SPODUMENE_GEM.get());
        populator.accept(ModItems.KUNZITE_SPODUMENE_GEM.get());
        populator.accept(ModItems.HIDDENITE_SPODUMENE_GEM.get());
        populator.accept(ModItems.TRIPHANE_SPODUMENE_GEM.get());
        populator.accept(ModItems.BLUE_SPODUMENE_SPODUMENE_GEM.get());

        populator.accept(ModItems.ANDALUSITE_GARNET_GEM.get());
        populator.accept(ModItems.BEKILY_GARNET_GEM.get());
        populator.accept(ModItems.GRAPE_GARNET_GEM.get());
        populator.accept(ModItems.BLUE_GARNET_GEM.get());
        populator.accept(ModItems.GROSSULARITE_GARNET_GEM.get());
        populator.accept(ModItems.DEMANTOID_GARNET_GEM.get());
        populator.accept(ModItems.HESSONITE_GARNET_GEM.get());
        populator.accept(ModItems.IMPERIAL_GARNET_GEM.get());
        populator.accept(ModItems.KATOITE_GARNET_GEM.get());
        populator.accept(ModItems.IRIDESCENT_ANDRADITE_GARNET_GEM.get());
        populator.accept(ModItems.KIMZEYITE_GARNET_GEM.get());
        populator.accept(ModItems.LEUCO_GARNET_GEM.get());
        populator.accept(ModItems.MELANITE_GARNET_GEM.get());
        populator.accept(ModItems.PYROPE_GARNET_GEM.get());
        populator.accept(ModItems.RHODOLITE_GARNET_GEM.get());
        populator.accept(ModItems.TOPAZOLITE_GARNET_GEM.get());
        populator.accept(ModItems.UMBALITE_GARNET_GEM.get());

        populator.accept(ModItems.AMETHYST_QUARTZ_GEM.get());
        populator.accept(ModItems.ANGEL_AURA_QUARTZ_QUARTZ_GEM.get());
        populator.accept(ModItems.BLUE_AVENTURINE_QUARTZ_GEM.get());
        populator.accept(ModItems.CARNELIAN_QUARTZ_GEM.get());
        populator.accept(ModItems.CHALCEDONY_QUARTZ_GEM.get());
        populator.accept(ModItems.CHERT_QUARTZ_GEM.get());
        populator.accept(ModItems.MILKY_QUARTZ_QUARTZ_GEM.get());
        populator.accept(ModItems.FLINT_QUARTZ_GEM.get());
        populator.accept(ModItems.CITRINE_QUARTZ_GEM.get());
        populator.accept(ModItems.CHERRY_QUARTZ_QUARTZ_GEM.get());
        populator.accept(ModItems.DUMORTIERITE_QUARTZ_QUARTZ_GEM.get());
        populator.accept(ModItems.LACE_AMETHYST_QUARTZ_GEM.get());
        populator.accept(ModItems.HELIOTROPE_QUARTZ_GEM.get());
        populator.accept(ModItems.ONYX_QUARTZ_GEM.get());
        populator.accept(ModItems.PRASIOLITE_QUARTZ_GEM.get());
        populator.accept(ModItems.ROSE_QUARTZ_QUARTZ_GEM.get());
        populator.accept(ModItems.SMOKY_QUARTZ_QUARTZ_GEM.get());
        populator.accept(ModItems.TIGERS_EYE_QUARTZ_GEM.get());

        populator.accept(ModItems.BIGGS_JASPER_GEM.get());
        populator.accept(ModItems.BLACK_JASPER_GEM.get());
        populator.accept(ModItems.BLUE_SNAKESKIN_JASPER_GEM.get());
        populator.accept(ModItems.BUTTERFLY_JASPER_GEM.get());
        populator.accept(ModItems.GOLDEN_JASPER_GEM.get());
        populator.accept(ModItems.FLAME_JASPER_GEM.get());
        populator.accept(ModItems.KAMBABA_JASPER_GEM.get());
        populator.accept(ModItems.IMPERIAL_JASPER_GEM.get());
        populator.accept(ModItems.MATRIX_JASPER_GEM.get());
        populator.accept(ModItems.MOOKAITE_JASPER_GEM.get());
        populator.accept(ModItems.OCEAN_JASPER_GEM.get());
        populator.accept(ModItems.PICASSO_JASPER_GEM.get());
        populator.accept(ModItems.RAINFOREST_JASPER_GEM.get());
        populator.accept(ModItems.RIPPLE_JASPER_GEM.get());
        populator.accept(ModItems.ROYAL_PLUME_JASPER_GEM.get());
        populator.accept(ModItems.ZEBRA_JASPER_GEM.get());
        populator.accept(ModItems.RED_STRIPED_JASPER_GEM.get());

        populator.accept(ModItems.ACHROITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.ADACHIITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.CANARY_TOURMALINE_GEM.get());
        populator.accept(ModItems.WATERMELON_TOURMALINE_GEM.get());
        populator.accept(ModItems.CONGO_TOURMALINE_GEM.get());
        populator.accept(ModItems.DRAVITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.INDICOLITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.MAGENTA_TOURMALINE_GEM.get());
        populator.accept(ModItems.PARAIBA_TOURMALINE_GEM.get());
        populator.accept(ModItems.OLENITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.POVONDRAITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.PUMPKIN_TOURMALINE_GEM.get());
        populator.accept(ModItems.ROSSMANITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.SCHORL_TOURMALINE_GEM.get());
        populator.accept(ModItems.RUBELLITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.SIBERITE_TOURMALINE_GEM.get());
        populator.accept(ModItems.VERDELITE_TOURMALINE_GEM.get());

        populator.accept(ModItems.BLUE_LACE_AGATE_GEM.get());
        populator.accept(ModItems.BOTSWANA_AGATE_GEM.get());
        populator.accept(ModItems.DENDRITIC_AGATE_GEM.get());
        populator.accept(ModItems.GRAPE_AGATE_GEM.get());
        populator.accept(ModItems.IRIS_AGATE_GEM.get());
        populator.accept(ModItems.DRAGONS_VEIN_AGATE_GEM.get());
        populator.accept(ModItems.ELLENSBURG_BLUE_AGATE_GEM.get());
        populator.accept(ModItems.ROSE_AGATE_GEM.get());
        populator.accept(ModItems.ORCA_AGATE_GEM.get());
        populator.accept(ModItems.HOLLY_BLUE_AGATE_GEM.get());
        populator.accept(ModItems.LAKE_SUPERIOR_AGATE_GEM.get());
        populator.accept(ModItems.SAKURA_AGATE_GEM.get());
        populator.accept(ModItems.TAWNY_AGATE_GEM.get());
        populator.accept(ModItems.TREE_AGATE_GEM.get());
        populator.accept(ModItems.TURRITELLA_AGATE_GEM.get());
        populator.accept(ModItems.WATER_AGATE_GEM.get());
        populator.accept(ModItems.WINGATE_PASS_PLUME_AGATE_GEM.get());
    })
            .build()
        );

    public static final RegistryObject<CreativeModeTab> GEMPIRE_ITEMS = CREATIVE_MODE_TABS.register("gempire_items",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.gempire_items"))
            // Set icon of creative tab
            .icon(() -> new ItemStack(ModItems.PINK_CHROMA.get()))
            // Add default items to tab
            .displayItems((params, populator) -> {
        populator.accept(ModItems.RED_CHROMA.get());
        populator.accept(ModItems.ORANGE_CHROMA.get());
        populator.accept(ModItems.YELLOW_CHROMA.get());
        populator.accept(ModItems.LIME_CHROMA.get());
        populator.accept(ModItems.GREEN_CHROMA.get());
        populator.accept(ModItems.CYAN_CHROMA.get());
        populator.accept(ModItems.LIGHT_BLUE_CHROMA.get());
        populator.accept(ModItems.BLUE_CHROMA.get());
        populator.accept(ModItems.MAGENTA_CHROMA.get());
        populator.accept(ModItems.PURPLE_CHROMA.get());
        populator.accept(ModItems.PINK_CHROMA.get());
        populator.accept(ModItems.WHITE_CHROMA.get());
        populator.accept(ModItems.LIGHT_GRAY_CHROMA.get());
        populator.accept(ModItems.GRAY_CHROMA.get());
        populator.accept(ModItems.BLACK_CHROMA.get());
        populator.accept(ModItems.BROWN_CHROMA.get());
        populator.accept(ModItems.SPECIAL_CHROMA.get());

        populator.accept(ModItems.RED_SHARDS.get());
        populator.accept(ModItems.ORANGE_SHARDS.get());
        populator.accept(ModItems.YELLOW_SHARDS.get());
        populator.accept(ModItems.LIME_SHARDS.get());
        populator.accept(ModItems.GREEN_SHARDS.get());
        populator.accept(ModItems.CYAN_SHARDS.get());
        populator.accept(ModItems.LIGHT_BLUE_SHARDS.get());
        populator.accept(ModItems.BLUE_SHARDS.get());
        populator.accept(ModItems.MAGENTA_SHARDS.get());
        populator.accept(ModItems.PURPLE_SHARDS.get());
        populator.accept(ModItems.PINK_SHARDS.get());
        populator.accept(ModItems.WHITE_SHARDS.get());
        populator.accept(ModItems.LIGHT_GRAY_SHARDS.get());
        populator.accept(ModItems.GRAY_SHARDS.get());
        populator.accept(ModItems.BLACK_SHARDS.get());
        populator.accept(ModItems.BROWN_SHARDS.get());
        populator.accept(ModItems.SPECIAL_SHARDS.get());

        populator.accept(ModItems.FUSCHIA_SHARDS.get());
        populator.accept(ModItems.AMBER_SHARDS.get());
        populator.accept(ModItems.COBALT_SHARDS.get());
        populator.accept(ModItems.ALABASTER_SHARDS.get());

        populator.accept(ModItems.SHARE_CONTRACT.get());
        populator.accept(ModItems.TRANSFER_CONTRACT.get());

        populator.accept(ModItems.RAW_TUNGSTEN.get());
        populator.accept(ModItems.TUNGSTEN_INGOT.get());
        populator.accept(ModItems.TUNGSTEN_NUGGET.get());
        populator.accept(ModItems.GEM_SCRAP.get());
        populator.accept(ModItems.GEM_ALLOY.get());
        populator.accept(ModItems.PRISMATIC_INGOT.get());

        populator.accept(ModItems.GEM_SLUSH_BUCKET.get());

        populator.accept(ModItems.PINK_ESSENCE_BUCKET.get());
        populator.accept(ModItems.BLUE_ESSENCE_BUCKET.get());
        populator.accept(ModItems.YELLOW_ESSENCE_BUCKET.get());
        populator.accept(ModItems.WHITE_ESSENCE_BUCKET.get());
        populator.accept(ModItems.CONGEALED_PINK_ESSENCE.get());
        populator.accept(ModItems.CONGEALED_BLUE_ESSENCE.get());
        populator.accept(ModItems.CONGEALED_YELLOW_ESSENCE.get());
        populator.accept(ModItems.CONGEALED_WHITE_ESSENCE.get());

        populator.accept(ModItems.STRAWBERRY.get());

        populator.accept(ModItems.REMNANTS_MUSIC_DISC.get());
        populator.accept(ModItems.FRAGMENTS_MUSIC_DISC.get());

        populator.accept(ModItems.SLUDGE_GLOB.get());

        populator.accept(ModItems.CHROMA_CATALYST.get());
        populator.accept(ModItems.GILDED_LAPIS.get());
        populator.accept(ModItems.PRIME_BOOST.get());
        populator.accept(ModItems.PRIMED_SLATE.get());
        populator.accept(ModItems.SLATE.get());
        //populator.accept(ModItems.INJECTOR_PANEL.get());

        populator.accept(ModItems.INACTIVE_AQUAMARINE_BASE.get());
        populator.accept(ModItems.INACTIVE_NEPHRITE_BASE.get());
        populator.accept(ModItems.INACTIVE_BISMUTH_BASE.get());
        populator.accept(ModItems.INACTIVE_PERIDOT_BASE.get());
        populator.accept(ModItems.INACTIVE_RUTILE_BASE.get());
        populator.accept(ModItems.INACTIVE_BIXBITE_BASE.get());
        populator.accept(ModItems.INACTIVE_EMERALD_BASE.get());
        populator.accept(ModItems.INACTIVE_RUBY_BASE.get());
        populator.accept(ModItems.INACTIVE_LAPIS_BASE.get());
        populator.accept(ModItems.INACTIVE_LARIMAR_BASE.get());
        populator.accept(ModItems.INACTIVE_MORGANITE_BASE.get());
        populator.accept(ModItems.INACTIVE_OBSIDIAN_BASE.get());
        populator.accept(ModItems.INACTIVE_SAPPHIRE_BASE.get());
        populator.accept(ModItems.INACTIVE_SAPPHIRE_BASE.get());
        populator.accept(ModItems.INACTIVE_ZIRCON_BASE.get());
        populator.accept(ModItems.INACTIVE_SPINEL_BASE.get());
        populator.accept(ModItems.INACTIVE_TOPAZ_BASE.get());
        populator.accept(ModItems.INACTIVE_SPODUMENE_BASE.get());
        populator.accept(ModItems.INACTIVE_GARNET_BASE.get());
        populator.accept(ModItems.INACTIVE_QUARTZ_BASE.get());
        populator.accept(ModItems.INACTIVE_JASPER_BASE.get());
        populator.accept(ModItems.INACTIVE_TOURMALINE_BASE.get());
        populator.accept(ModItems.INACTIVE_AGATE_BASE.get());

        populator.accept(ModItems.INACTIVE_PALADIN_GEM.get());
        populator.accept(ModItems.INACTIVE_GUARDIAN_GEM.get());
        populator.accept(ModItems.INACTIVE_HUNTRESS_GEM.get());
        populator.accept(ModItems.INACTIVE_EMPRESS_GEM.get());
    })
            .build()
            );
    public static final RegistryObject<CreativeModeTab> GEMPIRE_BLOCKS = CREATIVE_MODE_TABS.register("gempire_blocks",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.gempire_blocks"))
            // Set icon of creative tab
            .icon(() -> new ItemStack(ModItems.DRAINED_RED_STONE_2.get()))
            // Add default items to tab
            .displayItems((params, populator) -> {
        populator.accept(ModBlocks.DRAINED_RED_SOIL.get());
        populator.accept(ModBlocks.DRAINED_RED_SAND.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_2.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BANDED_RED_STONE.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_SOIL.get());
        populator.accept(ModBlocks.DRAINED_SAND.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_2.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BANDED_YELLOW_STONE.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_SOIL.get());
        populator.accept(ModBlocks.DRAINED_BLUE_SAND.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_2.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BANDED_BLUE_STONE.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_SOIL.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_SAND.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_2.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BANDED_PURPLE_STONE.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_SOIL.get());
        populator.accept(ModBlocks.DRAINED_GREY_SAND.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_2.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BANDED_GREY_STONE.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_ICE.get());
        populator.accept(ModBlocks.DRAINED_LOG_CRACKED.get());
        populator.accept(ModBlocks.DRAINED_LOG.get());

        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_POLISHED_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_POLISHED_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_POLISHED_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_POLISHED_STONE_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_POLISHED_STONE_DARK_SLAB.get());

        populator.accept(ModBlocks.DRAINED_RED_GLAZED_TILE.get());
        populator.accept(ModBlocks.DRAINED_RED_GLASS.get());
        populator.accept(ModBlocks.DRAINED_RED_GLASS_PANE.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS_CHISELED.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_LIGHT_WALL.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_WALL.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS_DARK.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICKS_CRACKED_DARK.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_RED_STONE_BRICK_DARK_WALL.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_GLAZED_TILE.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_GLASS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_GLASS_PANE.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CHISELED.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_LIGHT_WALL.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_WALL.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_DARK.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICKS_CRACKED_DARK.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_YELLOW_STONE_BRICK_DARK_WALL.get());
        populator.accept(ModBlocks.DRAINED_BLUE_GLAZED_TILE.get());
        populator.accept(ModBlocks.DRAINED_BLUE_GLASS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_GLASS_PANE.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CHISELED.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_LIGHT_WALL.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_WALL.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS_DARK.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICKS_CRACKED_DARK.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_BLUE_STONE_BRICK_DARK_WALL.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_GLAZED_TILE.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_GLASS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_GLASS_PANE.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CHISELED.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_LIGHT_WALL.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_WALL.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_DARK.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICKS_CRACKED_DARK.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_PURPLE_STONE_BRICK_DARK_WALL.get());
        populator.accept(ModBlocks.DRAINED_GREY_GLAZED_TILE.get());
        populator.accept(ModBlocks.DRAINED_GREY_GLASS.get());
        populator.accept(ModBlocks.DRAINED_GREY_GLASS_PANE.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS_CHISELED.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED_LIGHT.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_LIGHT_WALL.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_WALL.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS_DARK.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICKS_CRACKED_DARK.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_STAIRS.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_SLAB.get());
        populator.accept(ModBlocks.DRAINED_GREY_STONE_BRICK_DARK_WALL.get());

        populator.accept(ModItems.RED_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.ORANGE_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.YELLOW_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.LIME_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.GREEN_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.CYAN_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.LIGHT_BLUE_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.BLUE_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.MAGENTA_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.PURPLE_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.PINK_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.WHITE_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.LIGHT_GRAY_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.GRAY_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.BLACK_CHROMA_CRYSTAL.get());
        populator.accept(ModItems.BROWN_CHROMA_CRYSTAL.get());

        populator.accept(ModItems.ICE_SPIKE.get());

        populator.accept(ModItems.POWER_CRYSTAL_BLOCK_ITEM.get());
        populator.accept(ModItems.POWER_CRYSTAL_BLOCK_TIER_2_ITEM.get());
        //populator.accept(ModItems.TANK_BLOCK_ITEM.get());
        //populator.accept(ModItems.DRILL_BLOCK_ITEM.get());
        populator.accept(ModItems.SHELL_BLOCK_ITEM.get());
        populator.accept(ModItems.INCUBATOR_BLOCK_ITEM.get());
        populator.accept(ModItems.PEDISTAL.get());

        populator.accept(ModItems.CONGEALED_PINK_ESSENCE_BLOCK.get());
        populator.accept(ModItems.CONGEALED_BLUE_ESSENCE_BLOCK.get());
        populator.accept(ModItems.CONGEALED_YELLOW_ESSENCE_BLOCK.get());
        populator.accept(ModItems.CONGEALED_WHITE_ESSENCE_BLOCK.get());

        populator.accept(ModItems.STRAWBERRY_BLOCK.get());

        populator.accept(ModBlocks.PRISMATIC_BLOCK.get());
        populator.accept(ModBlocks.TUNGSTEN_BLOCK.get());
        populator.accept(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
        populator.accept(ModBlocks.TUNGSTEN_ORE.get());
        populator.accept(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());

        populator.accept(ModBlocks.PYRITE_BLOCK.get());
        populator.accept(ModBlocks.GEODE_CRYSTAL_BLOCK.get());

        populator.accept(ModBlocks.RUINED_MARBLE_BLOCK.get());
        populator.accept(ModBlocks.RUINED_MARBLE_STAIRS.get());
        populator.accept(ModBlocks.RUINED_MARBLE_SLAB.get());
        populator.accept(ModBlocks.CHISELED_RUINED_MARBLE_BLOCK.get());
        populator.accept(ModBlocks.RUINED_MARBLE_BRICK.get());
        populator.accept(ModBlocks.RUINED_MARBLE_PILLAR.get());
        populator.accept(ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get());
        populator.accept(ModBlocks.SMOOTH_RUINED_MARBLE_STAIRS.get());
        populator.accept(ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get());

        populator.accept(ModBlocks.PRIMED_ICE.get());
        populator.accept(ModBlocks.PRIMED_PACKED_ICE.get());
        populator.accept(ModBlocks.PRIMED_BLUE_ICE.get());
        populator.accept(ModBlocks.PRIMED_DRAINED_ICE.get());
    })
            .build()
            );
    public static final RegistryObject<CreativeModeTab> GEMPIRE_TOOLS = CREATIVE_MODE_TABS.register("gempire_tools",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.gempire_tools"))
            // Set icon of creative tab
            .icon(() -> new ItemStack(ModItems.PRISMATIC_PICKAXE.get()))
            // Add default items to tab
            .displayItems((params, populator) -> {
        populator.accept(ModItems.PINK_DESTABILIZER.get());
        populator.accept(ModItems.BLUE_DESTABILIZER.get());
        populator.accept(ModItems.YELLOW_DESTABILIZER.get());
        populator.accept(ModItems.WHITE_DESTABILIZER.get());
        populator.accept(ModItems.PINK_REJUVENATOR.get());
        populator.accept(ModItems.BLUE_REJUVENATOR.get());
        populator.accept(ModItems.YELLOW_REJUVENATOR.get());
        populator.accept(ModItems.WHITE_REJUVENATOR.get());
        populator.accept(ModItems.CONFRACTOR.get());
        populator.accept(ModItems.GEM_WHISTLE.get());
        populator.accept(ModItems.PRISMATIC_AXE.get());
        populator.accept(ModItems.PRISMATIC_PICKAXE.get());
        populator.accept(ModItems.PRISMATIC_SWORD.get());
        populator.accept(ModItems.PRISMATIC_HOE.get());
        populator.accept(ModItems.PRISMATIC_SHOVEL.get());

        populator.accept(ModItems.PALADIN_AXE.get());
        populator.accept(ModItems.EMPRESS_BOW.get());
        populator.accept(ModItems.HUNTRESS_SWORD.get());
        populator.accept(ModItems.GUARDIAN_SHIELD.get());

        populator.accept(ModItems.PALADIN_HELMET.get());
        populator.accept(ModItems.PALADIN_CHESTPLATE.get());
        populator.accept(ModItems.PALADIN_LEGGINGS.get());
        populator.accept(ModItems.PALADIN_BOOTS.get());
        populator.accept(ModItems.GUARDIAN_HELMET.get());
        populator.accept(ModItems.GUARDIAN_CHESTPLATE.get());
        populator.accept(ModItems.GUARDIAN_LEGGINGS.get());
        populator.accept(ModItems.GUARDIAN_BOOTS.get());
        populator.accept(ModItems.HUNTRESS_HELMET.get());
        populator.accept(ModItems.HUNTRESS_CHESTPLATE.get());
        populator.accept(ModItems.HUNTRESS_LEGGINGS.get());
        populator.accept(ModItems.HUNTRESS_BOOTS.get());
        populator.accept(ModItems.EMPRESS_HELMET.get());
        populator.accept(ModItems.EMPRESS_CHESTPLATE.get());
        populator.accept(ModItems.EMPRESS_LEGGINGS.get());
        populator.accept(ModItems.EMPRESS_BOOTS.get());
    })
            .build()
        );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
