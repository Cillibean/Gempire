package com.gempire.proxy;

import com.gempire.Gempire;
import com.gempire.client.entity.model.*;
import com.gempire.client.entity.render.*;
import com.gempire.client.screen.*;
import com.gempire.client.screen.warppad.WarpConfigScreen;
import com.gempire.client.screen.warppad.WarpSelectionScreen;
import com.gempire.client.ter.ShellTER;
import com.gempire.entities.bases.EntityGem;
import com.gempire.fluids.ModFluidTypes;
import com.gempire.init.*;
import com.gempire.keybindings.KeyBindings;
import com.gempire.networking.WarpGuiKeyPressed;
import com.gempire.systems.warping.WarpPadData;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.systems.warping.WarpPadInfoHolder;
import com.gempire.systems.warping.WarpSelectionMenu;
import com.gempire.tileentities.WarpPadTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;
import java.util.UUID;

import static net.minecraftforge.fluids.FluidInteractionRegistry.addInteraction;

public class ClientProxy {
    @Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {
    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        //RenderingRegistry.registerEntityRenderingHandler(ModEntities.TEST.get(), RenderTestEntity::new);
        event.registerEntityRenderer(ModEntities.CRAWLER.get(), RenderCrawler::new);
        event.registerEntityRenderer(ModEntities.ABOMINATION.get(), RenderAbomination::new);
        event.registerEntityRenderer(ModEntities.SHAMBLER.get(), RenderShambler::new);
        event.registerEntityRenderer(ModEntities.PEBBLE.get(), m -> new RenderPebble(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_P))));
        event.registerEntityRenderer(ModEntities.MICA.get(), m -> new RenderMica(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_M))));
        event.registerEntityRenderer(ModEntities.SHALE.get(), m -> new RenderShale(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_S))));
        event.registerEntityRenderer(ModEntities.NACRE.get(), m -> new RenderNacre(m, new ModelPebble<>(m.bakeLayer(ModelPebble.LAYER_LOCATION_N))));
        event.registerEntityRenderer(ModEntities.RUBY.get(), m -> new RenderRuby(m, new ModelRuby<>(m.bakeLayer(ModelRuby.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.SAPPHIRE.get(), m -> new RenderSapphire(m, new ModelSapphire<>(m.bakeLayer(ModelSapphire.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.QUARTZ.get(), m -> new RenderQuartz(m, new ModelQuartz<>(m.bakeLayer(ModelQuartz.LAYER_LOCATION_Q))));
        event.registerEntityRenderer(ModEntities.JASPER.get(), m -> new RenderJasper(m, new ModelQuartz<>(m.bakeLayer(ModelQuartz.LAYER_LOCATION_J))));
        event.registerEntityRenderer(ModEntities.AGATE.get(), m -> new RenderAgate(m, new ModelQuartz<>(m.bakeLayer(ModelQuartz.LAYER_LOCATION_A))));
        event.registerEntityRenderer(ModEntities.TOPAZ.get(), m -> new RenderTopaz(m, new ModelTopaz<>(m.bakeLayer(ModelTopaz.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.OBSIDIAN.get(), m -> new RenderObsidian(m, new ModelObsidian<>(m.bakeLayer(ModelObsidian.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.PEARL.get(), m -> new RenderPearl(m, new ModelPearl<>(m.bakeLayer(ModelPearl.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.NEPHRITE.get(), m -> new RenderNephrite(m, new ModelNephrite<>(m.bakeLayer(ModelNephrite.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.SPODUMENE.get(), m -> new RenderSpodumene(m, new ModelSpodumene<>(m.bakeLayer(ModelSpodumene.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.ZIRCON.get(), m -> new RenderZircon(m, new ModelZircon<>(m.bakeLayer(ModelZircon.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.AQUAMARINE.get(), m -> new RenderAquamarine(m, new ModelAquamarine<>(m.bakeLayer(ModelAquamarine.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.BISMUTH.get(), m -> new RenderBismuth(m, new ModelBismuth<>(m.bakeLayer(ModelBismuth.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.BIXBITE.get(), m -> new RenderBixbite(m, new ModelBixbite<>(m.bakeLayer(ModelBixbite.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.GARNET.get(), m -> new RenderGarnet(m, new ModelGarnet<>(m.bakeLayer(ModelGarnet.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.EMERALD.get(), m -> new RenderEmerald(m, new ModelEmerald<>(m.bakeLayer(ModelEmerald.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.LAPIS.get(), m -> new RenderLapis(m, new ModelLapis<>(m.bakeLayer(ModelLapis.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.LARIMAR.get(), m -> new RenderLarimar(m, new ModelLarimar<>(m.bakeLayer(ModelLarimar.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.MORGANITE.get(), m -> new RenderMorganite(m, new ModelMorganite<>(m.bakeLayer(ModelMorganite.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.PERIDOT.get(), m -> new RenderPeridot(m, new ModelPeridot<>(m.bakeLayer(ModelPeridot.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.RUTILE.get(), m -> new RenderRutile(m, new ModelRutile<>(m.bakeLayer(ModelRutile.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.SPINEL.get(), m -> new RenderSpinel(m, new ModelSpinel<>(m.bakeLayer(ModelSpinel.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.TOURMALINE.get(), m -> new RenderTourmaline(m, new ModelTourmaline<>(m.bakeLayer(ModelTourmaline.LAYER_LOCATION))));

        event.registerEntityRenderer(ModEntities.ICE_SHARD.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ACID_SPIT.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.WATER_ORB.get(), ThrownItemRenderer::new);

        MenuScreens.register(ModContainers.INJECTOR_CONTAINER.get(), InjectorScreen::new);
        MenuScreens.register(ModContainers.GEM_UI_CONTAINER.get(), GemUIScreen::new);
        MenuScreens.register(ModContainers.SHELL_CONTAINER.get(), ShellScreen::new);
        MenuScreens.register(ModContainers.PEARL_UI_CONTAINER.get(), PearlUIScreen::new);
        MenuScreens.register(ModContainers.PEARL_DEFECTIVE_UI_CONTAINER.get(), PearlUIScreenDefective::new);
        MenuScreens.register(ModContainers.ZIRCON_UI_CONTAINER.get(), ZirconUIScreen::new);

        MenuScreens.register(ModContainers.WARP_SELECTION.get(), WarpSelectionScreen::new);
        MenuScreens.register(ModContainers.WARP_CONFIG.get(), WarpConfigScreen::new);

        event.registerBlockEntityRenderer(ModTE.SHELL_TE.get(), ShellTER::new);
    }

    @SubscribeEvent
    public void buildContents(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(Gempire.MODID, "gemstones"),
                builder -> builder.title(Component.translatable("item_group." + Gempire.MODID + ".gemstones"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(ModItems.RUBY_GEM.get()))
                        // Add default items to tab
                        .displayItems((enabledFeatures, populator, operatorEnabled) -> {
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
                            }));
            event.registerCreativeModeTab(new ResourceLocation(Gempire.MODID, "gempire_items"), builder ->
                    // Set name of tab to display
                    builder.title(Component.translatable("item_group." + Gempire.MODID + ".gempire_items"))
                            // Set icon of creative tab
                            .icon(() -> new ItemStack(ModItems.PINK_CHROMA.get()))
                            // Add default items to tab
                            .displayItems((enabledFlags, populator, hasPermissions) -> {
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

                                populator.accept(ModItems.PINK_DESTABILIZER.get());
                                populator.accept(ModItems.BLUE_DESTABILIZER.get());
                                populator.accept(ModItems.YELLOW_DESTABILIZER.get());
                                populator.accept(ModItems.WHITE_DESTABILIZER.get());
                                populator.accept(ModItems.PINK_REJUVENATOR.get());
                                populator.accept(ModItems.BLUE_REJUVENATOR.get());
                                populator.accept(ModItems.YELLOW_REJUVENATOR.get());
                                populator.accept(ModItems.WHITE_REJUVENATOR.get());
                                populator.accept(ModItems.CONFRACTOR.get());
                                populator.accept(ModItems.CONFRACTOR_TIP.get());
                                populator.accept(ModItems.CONFRACTOR_BODY.get());
                                populator.accept(ModItems.GEM_WHISTLE.get());

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

                                populator.accept(ModItems.SLUDGE_GLOB.get());

                                populator.accept(ModItems.CHROMA_CATALYST.get());
                                populator.accept(ModItems.GILDED_LAPIS.get());
                                populator.accept(ModItems.PRIME_BOOST.get());
                                populator.accept(ModItems.INJECTOR_PANEL.get());
                            })
            );
            event.registerCreativeModeTab(new ResourceLocation(Gempire.MODID, "gempire_blocks"), builder ->
                    // Set name of tab to display
                    builder.title(Component.translatable("item_group." + Gempire.MODID + ".gempire_blocks"))
                            // Set icon of creative tab
                            .icon(() -> new ItemStack(ModItems.DRAINED_RED_STONE_2.get()))
                            // Add default items to tab
                            .displayItems((enabledFlags, populator, hasPermissions) -> {
                                populator.accept(ModBlocks.DRAINED_BANDED_BLUE_STONE.get());
                                populator.accept(ModBlocks.DRAINED_BANDED_YELLOW_STONE.get());
                                populator.accept(ModBlocks.DRAINED_BANDED_PURPLE_STONE.get());
                                populator.accept(ModBlocks.DRAINED_BANDED_RED_STONE.get());
                                populator.accept(ModBlocks.DRAINED_BANDED_GREY_STONE.get());
                                populator.accept(ModBlocks.DRAINED_BLUE_STONE.get());
                                populator.accept(ModBlocks.DRAINED_YELLOW_STONE.get());
                                populator.accept(ModBlocks.DRAINED_PURPLE_STONE.get());
                                populator.accept(ModBlocks.DRAINED_RED_STONE.get());
                                populator.accept(ModBlocks.DRAINED_GREY_STONE.get());
                                populator.accept(ModBlocks.DRAINED_BLUE_SOIL.get());
                                populator.accept(ModBlocks.DRAINED_YELLOW_SOIL.get());
                                populator.accept(ModBlocks.DRAINED_PURPLE_SOIL.get());
                                populator.accept(ModBlocks.DRAINED_RED_SOIL.get());
                                populator.accept(ModBlocks.DRAINED_GREY_SOIL.get());
                                populator.accept(ModBlocks.DRAINED_BLUE_STONE_2.get());
                                populator.accept(ModBlocks.DRAINED_YELLOW_STONE_2.get());
                                populator.accept(ModBlocks.DRAINED_PURPLE_STONE_2.get());
                                populator.accept(ModBlocks.DRAINED_RED_STONE_2.get());
                                populator.accept(ModBlocks.DRAINED_GREY_STONE_2.get());
                                populator.accept(ModBlocks.DRAINED_RED_SAND.get());
                                populator.accept(ModBlocks.DRAINED_SAND.get());
                                populator.accept(ModBlocks.DRAINED_ICE.get());
                                populator.accept(ModBlocks.DRAINED_LOG_CRACKED.get());
                                populator.accept(ModBlocks.DRAINED_LOG.get());

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

                                populator.accept(ModItems.TANK_BLOCK_ITEM.get());
                                populator.accept(ModItems.POWER_CRYSTAL_BLOCK_ITEM.get());
                                populator.accept(ModItems.POWER_CRYSTAL_BLOCK_TIER_2_ITEM.get());
                                populator.accept(ModItems.DRILL_BLOCK_ITEM.get());
                                populator.accept(ModItems.SHELL_BLOCK_ITEM.get());
                                populator.accept(ModItems.PEDISTAL.get());

                                populator.accept(ModItems.CONGEALED_PINK_ESSENCE_BLOCK.get());
                                populator.accept(ModItems.CONGEALED_BLUE_ESSENCE_BLOCK.get());
                                populator.accept(ModItems.CONGEALED_YELLOW_ESSENCE_BLOCK.get());
                                populator.accept(ModItems.CONGEALED_WHITE_ESSENCE_BLOCK.get());

                                populator.accept(ModBlocks.PRISMATIC_BLOCK.get());
                                populator.accept(ModBlocks.TUNGSTEN_BLOCK.get());
                                populator.accept(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
                                populator.accept(ModBlocks.TUNGSTEN_ORE.get());
                                populator.accept(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());

                                populator.accept(ModBlocks.CHISELED_RUINED_MARBLE_BLOCK.get());
                                populator.accept(ModBlocks.RUINED_MARBLE_BLOCK.get());
                                populator.accept(ModBlocks.RUINED_MARBLE_PILLAR.get());
                                populator.accept(ModBlocks.RUINED_MARBLE_STAIRS.get());
                                populator.accept(ModBlocks.RUINED_MARBLE_BRICK.get());
                                populator.accept(ModBlocks.RUINED_MARBLE_SLAB.get());
                                populator.accept(ModBlocks.SMOOTH_RUINED_MARBLE_BLOCK.get());
                                populator.accept(ModBlocks.SMOOTH_RUINED_MARBLE_STAIRS.get());
                                populator.accept(ModBlocks.SMOOTH_RUINED_MARBLE_SLAB.get());
                            })
            );
        }
        @SubscribeEvent
        public void buildContents(CreativeModeTabEvent.BuildContents event) {
            // Add to ingredients tab
            if (event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS) {
                event.accept(ModItems.SPODUMENE_PIECE.get());
            }
    }

    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();

        addInteraction(ModFluidTypes.WHITE_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.WATER_TYPE.get(),
                ModBlocks.CONGEALED_WHITE_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.WHITE_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.LAVA_TYPE.get(),
                ModBlocks.CONGEALED_WHITE_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.PINK_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.WATER_TYPE.get(),
                ModBlocks.CONGEALED_PINK_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.PINK_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.LAVA_TYPE.get(),
                ModBlocks.CONGEALED_PINK_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.BLUE_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.WATER_TYPE.get(),
                ModBlocks.CONGEALED_BLUE_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.BLUE_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.LAVA_TYPE.get(),
                ModBlocks.CONGEALED_BLUE_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.YELLOW_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.WATER_TYPE.get(),
                ModBlocks.CONGEALED_YELLOW_ESSENCE_BLOCK.get().defaultBlockState()
        ));
        addInteraction(ModFluidTypes.YELLOW_ESSENCE_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.LAVA_TYPE.get(),
                ModBlocks.CONGEALED_YELLOW_ESSENCE_BLOCK.get().defaultBlockState()
        ));
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_P, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_M, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_S, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelPebble.LAYER_LOCATION_N, ModelPebble::createBodyLayer);
        event.registerLayerDefinition(ModelQuartz.LAYER_LOCATION_Q, ModelQuartz::createBodyLayer);
        event.registerLayerDefinition(ModelQuartz.LAYER_LOCATION_J, ModelQuartz::createBodyLayer);
        event.registerLayerDefinition(ModelQuartz.LAYER_LOCATION_A, ModelQuartz::createBodyLayer);
        event.registerLayerDefinition(ModelRuby.LAYER_LOCATION, ModelRuby::createBodyLayer);
        event.registerLayerDefinition(ModelSapphire.LAYER_LOCATION, ModelSapphire::createBodyLayer);
        event.registerLayerDefinition(ModelSpodumene.LAYER_LOCATION, ModelSpodumene::createBodyLayer);
        event.registerLayerDefinition(ModelNephrite.LAYER_LOCATION, ModelNephrite::createBodyLayer);
        event.registerLayerDefinition(ModelPearl.LAYER_LOCATION, ModelPearl::createBodyLayer);
        event.registerLayerDefinition(ModelObsidian.LAYER_LOCATION, ModelObsidian::createBodyLayer);
        event.registerLayerDefinition(ModelZircon.LAYER_LOCATION, ModelZircon::createBodyLayer);
        event.registerLayerDefinition(ModelLarimar.LAYER_LOCATION, ModelLarimar::createBodyLayer);
        event.registerLayerDefinition(ModelBixbite.LAYER_LOCATION, ModelBixbite::createBodyLayer);
        event.registerLayerDefinition(ModelTopaz.LAYER_LOCATION, ModelTopaz::createBodyLayer);
        event.registerLayerDefinition(ModelTourmaline.LAYER_LOCATION, ModelTourmaline::createBodyLayer);
        event.registerLayerDefinition(ModelAquamarine.LAYER_LOCATION, ModelAquamarine::createBodyLayer);
        event.registerLayerDefinition(ModelEmerald.LAYER_LOCATION, ModelEmerald::createBodyLayer);
        event.registerLayerDefinition(ModelLapis.LAYER_LOCATION, ModelLapis::createBodyLayer);
        event.registerLayerDefinition(ModelSpinel.LAYER_LOCATION, ModelSpinel::createBodyLayer);
        event.registerLayerDefinition(ModelGarnet.LAYER_LOCATION, ModelGarnet::createBodyLayer);
        event.registerLayerDefinition(ModelRutile.LAYER_LOCATION, ModelRutile::createBodyLayer);
        event.registerLayerDefinition(ModelMorganite.LAYER_LOCATION, ModelMorganite::createBodyLayer);
        event.registerLayerDefinition(ModelPeridot.LAYER_LOCATION, ModelPeridot::createBodyLayer);
        event.registerLayerDefinition(ModelBismuth.LAYER_LOCATION, ModelBismuth::createBodyLayer);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.WARP_KEY);
    }
}
    @Mod.EventBusSubscriber(modid = Gempire.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBindings.WARP_KEY.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                System.out.println("warp attempt");
                /*if (WarpPadTE.TestForWarpPad(player)) {
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Warped!"));
                } else if (!WarpPadTE.TestForWarpPad(player)) {
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal("This is not a warp pad!"));
                }*/
                ModPacketHandler.INSTANCE.sendToServer(new WarpGuiKeyPressed(player.getOnPos()));
                }
            }
        }

    }
