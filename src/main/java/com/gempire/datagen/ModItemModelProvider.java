package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Gempire.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ANATASE_INGOT);
        simpleItem(ModItems.ANATASE_NUGGET);
        simpleItem(ModItems.RAW_ANATASE);
        simpleItem(ModItems.THULITE_INGOT);
        simpleItem(ModItems.THULITE_NUGGET);
        simpleItem(ModItems.RAW_THULITE);
        simpleItem(ModItems.ELECTRUM_INGOT);
        simpleItem(ModItems.ELECTRUM_NUGGET);
        simpleItem(ModItems.RAW_ELECTRUM);
        simpleItem(ModItems.PLATINUM_INGOT);
        simpleItem(ModItems.PLATINUM_NUGGET);
        simpleItem(ModItems.RAW_PLATINUM);
        simpleItem(ModItems.TUNGSTEN_INGOT);
        simpleItem(ModItems.TUNGSTEN_NUGGET);
        simpleItem(ModItems.RAW_TUNGSTEN);

        simpleItem(ModItems.PYRITE_INGOT);
        simpleItem(ModItems.PYRITE_NUGGET);
        simpleItem(ModItems.RAW_PYRITE);

        simpleItem(ModItems.PRISMATIC_INGOT);
        simpleItem(ModItems.GEM_ALLOY);
        simpleItem(ModItems.PRISMATIC_UPGRADE_SMITHING_TEMPLATE);

        handheldItem(ModItems.PRISMATIC_SWORD);
        handheldItem(ModItems.PRISMATIC_AXE);
        handheldItem(ModItems.PRISMATIC_PICKAXE);
        handheldItem(ModItems.PRISMATIC_HOE);
        handheldItem(ModItems.PRISMATIC_SHOVEL);

        simpleItem(ModItems.GUARDIAN_HELMET);
        simpleItem(ModItems.GUARDIAN_CHESTPLATE);
        simpleItem(ModItems.GUARDIAN_LEGGINGS);
        simpleItem(ModItems.GUARDIAN_BOOTS);

        simpleItem(ModItems.EMPRESS_HELMET);
        simpleItem(ModItems.EMPRESS_CHESTPLATE);
        simpleItem(ModItems.EMPRESS_LEGGINGS);
        simpleItem(ModItems.EMPRESS_BOOTS);

        simpleItem(ModItems.HUNTRESS_HELMET);
        simpleItem(ModItems.HUNTRESS_CHESTPLATE);
        simpleItem(ModItems.HUNTRESS_LEGGINGS);
        simpleItem(ModItems.HUNTRESS_BOOTS);

        simpleItem(ModItems.PALADIN_HELMET);
        simpleItem(ModItems.PALADIN_CHESTPLATE);
        simpleItem(ModItems.PALADIN_LEGGINGS);
        simpleItem(ModItems.PALADIN_BOOTS);

        simpleItem(ModItems.PALADIN_FLOWER);
        simpleItem(ModItems.GUARDIAN_TEAR);
        simpleItem(ModItems.HUNTRESS_DAGGER);
        simpleItem(ModItems.EMPRESS_STAR);

        simpleItem(ModItems.SHOCK_BERRY);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.SHADED_APPLE);

        simpleItem(ModItems.SLUDGE_GLOB);

        simpleItem(ModItems.BLUE_CRYSTAL_APPLE);
        simpleItem(ModItems.PURPLE_CRYSTAL_APPLE);
        simpleItem(ModItems.PINK_CRYSTAL_APPLE);

        simpleItem(ModItems.PINK_ESSENCE_BUCKET);
        simpleItem(ModItems.BLUE_ESSENCE_BUCKET);
        simpleItem(ModItems.YELLOW_ESSENCE_BUCKET);
        simpleItem(ModItems.WHITE_ESSENCE_BUCKET);

        simpleItem(ModItems.REMNANTS_MUSIC_DISC);
        simpleItem(ModItems.FRAGMENTS_MUSIC_DISC);

        simpleItem(ModItems.SPEED_BOOSTER);
        simpleItem(ModItems.PRIME_BOOST);
        simpleItem(ModItems.SLATE);
        simpleItem(ModItems.PRIMED_SLATE);

        simpleItem(ModItems.SPODUMENE_PIECE);

        simpleItem(ModItems.ACID_SPIT);
        simpleItem(ModItems.GUARDIAN_ORB);
        simpleItem(ModItems.WATER_ORB);

        simpleItem(ModItems.GEM_SLUSH_BUCKET);
        simpleItem(ModItems.GEM_SCRAP);
        simpleItem(ModItems.GEM_WHISTLE);

        simpleItem(ModItems.INJECTOR_PANEL);

        handheldItem(ModItems.CONFRACTOR);

        simpleItem(ModItems.CONFRACTOR_BODY);

        simpleItem(ModItems.CONFRACTOR_TIP);

        simpleItem(ModItems.CHROMA_CATALYST);

        handheldItem(ModItems.PINK_DESTABILIZER);
        handheldItem(ModItems.BLUE_DESTABILIZER);
        handheldItem(ModItems.YELLOW_DESTABILIZER);
        handheldItem(ModItems.WHITE_DESTABILIZER);

        simpleItem(ModItems.SHARE_CONTRACT);
        simpleItem(ModItems.TRANSFER_CONTRACT);
        //simpleItem(ModItems.ASSIGN_CONTRACT);

        simpleItem(ModItems.RED_CHROMA);
        simpleItem(ModItems.ORANGE_CHROMA);
        simpleItem(ModItems.YELLOW_CHROMA);
        simpleItem(ModItems.LIME_CHROMA);
        simpleItem(ModItems.GREEN_CHROMA);
        simpleItem(ModItems.LIGHT_BLUE_CHROMA);
        simpleItem(ModItems.BLUE_CHROMA);
        simpleItem(ModItems.PURPLE_CHROMA);
        simpleItem(ModItems.CYAN_CHROMA);
        simpleItem(ModItems.PINK_CHROMA);
        simpleItem(ModItems.WHITE_CHROMA);
        simpleItem(ModItems.LIGHT_GRAY_CHROMA);
        simpleItem(ModItems.GRAY_CHROMA);
        simpleItem(ModItems.BLACK_CHROMA);
        simpleItem(ModItems.BROWN_CHROMA);
        simpleItem(ModItems.MAGENTA_CHROMA);

        simpleItem(ModItems.RED_SHARDS);
        simpleItem(ModItems.ORANGE_SHARDS);
        simpleItem(ModItems.YELLOW_SHARDS);
        simpleItem(ModItems.LIME_SHARDS);
        simpleItem(ModItems.GREEN_SHARDS);
        simpleItem(ModItems.LIGHT_BLUE_SHARDS);
        simpleItem(ModItems.BLUE_SHARDS);
        simpleItem(ModItems.PURPLE_SHARDS);
        simpleItem(ModItems.CYAN_SHARDS);
        simpleItem(ModItems.PINK_SHARDS);
        simpleItem(ModItems.WHITE_SHARDS);
        simpleItem(ModItems.LIGHT_GRAY_SHARDS);
        simpleItem(ModItems.GRAY_SHARDS);
        simpleItem(ModItems.BLACK_SHARDS);
        simpleItem(ModItems.BROWN_SHARDS);
        simpleItem(ModItems.MAGENTA_SHARDS);

        simpleItem(ModItems.FUCHSIA_SHARDS);
        simpleItem(ModItems.COBALT_SHARDS);
        simpleItem(ModItems.AMBER_SHARDS);
        simpleItem(ModItems.ALABASTER_SHARDS);

        simpleItem(ModItems.RED_LATTICE);
        simpleItem(ModItems.ORANGE_LATTICE);
        simpleItem(ModItems.YELLOW_LATTICE);
        simpleItem(ModItems.LIME_LATTICE);
        simpleItem(ModItems.GREEN_LATTICE);
        simpleItem(ModItems.LIGHT_BLUE_LATTICE);
        simpleItem(ModItems.BLUE_LATTICE);
        simpleItem(ModItems.PURPLE_LATTICE);
        simpleItem(ModItems.CYAN_LATTICE);
        simpleItem(ModItems.PINK_LATTICE);
        simpleItem(ModItems.WHITE_LATTICE);
        simpleItem(ModItems.LIGHT_GRAY_LATTICE);
        simpleItem(ModItems.GRAY_LATTICE);
        simpleItem(ModItems.BLACK_LATTICE);
        simpleItem(ModItems.BROWN_LATTICE);
        simpleItem(ModItems.MAGENTA_LATTICE);

        saplingItem(ModBlocks.DISTANT_SAPLING);
        fenceItem(ModBlocks.DISTANT_FENCE, ModBlocks.DISTANT_PLANKS);
        simpleItem(ModItems.DISTANT_SIGN);
        simpleItem(ModItems.DISTANT_HANGING_SIGN);
        simpleItem(ModItems.DISTANT_DOOR);

        saplingItem(ModBlocks.VERDANT_PINE_SAPLING);
        fenceItem(ModBlocks.VERDANT_PINE_FENCE, ModBlocks.VERDANT_PINE_PLANKS);
        simpleItem(ModItems.VERDANT_PINE_SIGN);
        simpleItem(ModItems.VERDANT_PINE_HANGING_SIGN);
        simpleItem(ModItems.VERDANT_PINE_DOOR);

        saplingItem(ModBlocks.KALEIDOSCOPE_SAPLING);
        fenceItem(ModBlocks.KALEIDOSCOPE_FENCE, ModBlocks.KALEIDOSCOPE_PLANKS);
        simpleItem(ModItems.KALEIDOSCOPE_SIGN);
        simpleItem(ModItems.KALEIDOSCOPE_HANGING_SIGN);
        simpleItem(ModItems.KALEIDOSCOPE_DOOR);

        saplingItem(ModBlocks.SHADED_SAPLING);
        fenceItem(ModBlocks.SHADED_FENCE, ModBlocks.SHADED_PLANKS);
        simpleItem(ModItems.SHADED_SIGN);
        simpleItem(ModItems.SHADED_HANGING_SIGN);
        simpleItem(ModItems.SHADED_DOOR);

        saplingItem(ModBlocks.CRYSTAL_SAPLING);
        fenceItem(ModBlocks.CRYSTAL_FENCE, ModBlocks.CRYSTAL_PLANKS);
        simpleItem(ModItems.CRYSTAL_DOOR);
        simpleItem(ModItems.CRYSTAL_SIGN);
        simpleItem(ModItems.CRYSTAL_HANGING_SIGN);

        fenceItem(ModBlocks.ASTER_FENCE, ModBlocks.ASTER_PLANKS);
        simpleItem(ModItems.ASTER_DOOR);
        simpleItem(ModItems.ASTER_SIGN);
        simpleItem(ModItems.ASTER_HANGING_SIGN);

        wallItem(ModBlocks.PINK_SANDSTONE_WALL, ModBlocks.PINK_SANDSTONE);
        wallItem(ModBlocks.PEGMATITE_WALL, ModBlocks.PEGMATITE);
        wallItem(ModBlocks.RYOLITE_WALL, ModBlocks.RYOLITE_BLOCK);
        wallItem(ModBlocks.POLISHED_RYOLITE_WALL, ModBlocks.POLISHED_RYOLITE_BLOCK);
        wallItem(ModBlocks.SCHIST_WALL, ModBlocks.SCHIST_BLOCK);
        wallItem(ModBlocks.POLISHED_SCHIST_WALL, ModBlocks.POLISHED_SCHIST_BLOCK);
        wallItem(ModBlocks.COBBLED_PEGMATITE_WALL, ModBlocks.COBBLED_PEGMATITE);
        wallItem(ModBlocks.SELENITE_WALL, ModBlocks.SELENITE);
        wallItem(ModBlocks.POLISHED_SELENITE_WALL, ModBlocks.POLISHED_SELENITE);

        saplingItem(ModBlocks.THULITE_CLUSTER);
        saplingItem(ModBlocks.THULITE_TOWER);

        saplingItem(ModBlocks.WHITE_IRIS);
        saplingItem(ModBlocks.PINK_THISTLE);
        saplingItem(ModBlocks.PANSIE);
        saplingItem(ModBlocks.ORCHID);
        saplingItem(ModBlocks.NASTURTIUMS);
        saplingItem(ModBlocks.HYDRANGEA_BUSH_WHITE);
        saplingItem(ModBlocks.HYDRANGEA_BUSH_PINK);
        saplingItem(ModBlocks.HYDRANGEA_BUSH_BLUE);
        saplingItem(ModBlocks.HYDRANGEA_BUSH_GREEN);
        saplingItem(ModBlocks.HYDRANGEA_BUSH_PURPLE);
        saplingItem(ModBlocks.CHRYSANTHEMUM);
        saplingItem(ModBlocks.BLUE_BELLS);
        saplingItem(ModBlocks.BLUE_PUFFBALL);

        saplingItem(ModBlocks.AQUATIC_FIBRE);

        saplingItem(ModBlocks.SELENITE_CLUSTER);

        //simpleItem(ModItems.WARP_CORE);

        withExistingParent(ModItems.FUCHSIA_PALADIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.COBALT_GUARDIAN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.AMBER_HUNTRESS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.ALABASTER_EMPRESS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SPECTER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.FLEURIE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CRYSTAL_DEER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.HUNTER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.OVERSEER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SORROW_JELLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.MANTASHARK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.OPAL_MANTASHARK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        simpleItem(ModItems.CALMING_JELLY);
        simpleItem(ModItems.ASSIGN_CONTRACT);
        simpleItem(ModItems.SELENITE_CHUNK);

        simpleItem(ModItems.PRISMATIC_FLASK);
        simpleItem(ModItems.PRISMATIC_GLASS_SHARDS);
        simpleItem(ModItems.PRISMATIC_UPGRADE_SMITHING_TEMPLATE);
        simpleItem(ModItems.OPALIZED_TOOTH);
        simpleItem(ModItems.DRIED_COBALT_KELP);
        simpleItem(ModItems.COBALT_KELP);

        simpleItem(ModItems.RAW_MANTAWING);
        simpleItem(ModItems.COOKED_MANTAWING);
        simpleItem(ModItems.RAW_OPALIZED_MANTAWING);
        simpleItem(ModItems.COOKED_OPALIZED_MANTAWING);
        simpleItem(ModItems.RAW_CRYSTAL_SHANK);
        simpleItem(ModItems.COOKED_CRYSTAL_SHANK);
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gempire.MODID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), new ResourceLocation(Gempire.MODID,
                "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(Gempire.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(Gempire.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(Gempire.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }


    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Gempire.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gempire.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gempire.MODID,"item/" + item.getId().getPath()));
    }
}
