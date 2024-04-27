package com.gempire.proxy;

import com.gempire.Gempire;
import com.gempire.aura.AuraOverlay;
import com.gempire.client.entity.model.*;
import com.gempire.client.entity.render.*;
import com.gempire.client.screen.*;
import com.gempire.client.screen.warppad.WarpConfigScreen;
import com.gempire.client.screen.warppad.WarpSelectionScreen;
import com.gempire.client.ter.ShellTER;
import com.gempire.fluids.ModFluidTypes;
import com.gempire.init.*;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import com.gempire.items.tools.GuardianArmorRenderer;
import com.gempire.keybindings.KeyBindings;
import com.gempire.networking.WarpGuiKeyPressed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

import static net.minecraftforge.fluids.FluidInteractionRegistry.addInteraction;

public class ClientProxy {

    public static CreativeModeTab GEMPIRE_GEMSTONES;
    public static CreativeModeTab GEMPIRE_BLOCKS;
    public static CreativeModeTab GEMPIRE_ITEMS;
    public static CreativeModeTab GEMPIRE_TOOLS;
    @Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {
    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        //RenderingRegistry.registerEntityRenderingHandler(ModEntities.TEST.get(), RenderTestEntity::new);
        event.registerEntityRenderer(ModEntities.HUNTER.get(), m -> new RenderHunter(m, new ModelHunter<>(m.bakeLayer(ModelHunter.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.CRAWLER.get(), RenderCrawler::new);
        event.registerEntityRenderer(ModEntities.ABOMINATION.get(), RenderAbomination::new);
        event.registerEntityRenderer(ModEntities.SHAMBLER.get(), RenderShambler::new);
        event.registerEntityRenderer(ModEntities.ALABASTER_EMPRESS.get(), RenderAlabasterEmpress::new);
        event.registerEntityRenderer(ModEntities.AMBER_HUNTRESS.get(), RenderAmberHuntress::new);
        event.registerEntityRenderer(ModEntities.FUCHSIA_PALADIN.get(), RenderFuchsiaPaladin::new);
        /*
        event.registerEntityRenderer(ModEntities.PRISMATIC_EMPRESS.get(), RenderPrismaticEmpress::new);
        event.registerEntityRenderer(ModEntities.GILDED_HUNTRESS.get(), RenderGildedHuntress::new);
        event.registerEntityRenderer(ModEntities.MIRRORED_GUARDIAN.get(), RenderMirroredGuardian::new);
        event.registerEntityRenderer(ModEntities.IRIDESCENT_PALADIN.get(), RenderIridescentPaladin::new);
         */
        //event.registerEntityRenderer(ModEntities.PEPO.get(), RenderPepo::new);
        event.registerEntityRenderer(ModEntities.BEASTMASTER_WOLF.get(), RenderBeastmasterWolf::new);
        event.registerEntityRenderer(ModEntities.COBALT_GUARDIAN.get(), m -> new RenderCobaltGuardian(m, new ModelCobaltGuardian<>(m.bakeLayer(ModelCobaltGuardian.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.FUSION.get(), m -> new RenderFusion(m, new ModelFusion<>(m.bakeLayer(ModelFusion.LAYER_LOCATION))));
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
        event.registerEntityRenderer(ModEntities.SPECTER.get(), m -> new RenderSpecter(m, new ModelSpecter<>(m.bakeLayer(ModelSpecter.LAYER_LOCATION))));
        event.registerEntityRenderer(ModEntities.PEPO.get(), m -> new RenderPepo(m, new ModelPepo<>(m.bakeLayer(ModelPepo.LAYER_LOCATION))));

        event.registerEntityRenderer(ModEntities.ICE_SHARD.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ACID_SPIT.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.WATER_ORB.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ELECTROKINESIS_LIGHTNING.get(), ElectrokinesisLightningRenderer::new);
        event.registerEntityRenderer(ModEntities.HUNTRESS_LIGHTNING.get(), HuntressLightningRenderer::new);

        MenuScreens.register(ModContainers.INJECTOR_CONTAINER.get(), InjectorScreen::new);
        MenuScreens.register(ModContainers.GEM_UI_CONTAINER.get(), GemUIScreen::new);
        MenuScreens.register(ModContainers.FUSION_UI_CONTAINER.get(), FusionUIScreen::new);
        MenuScreens.register(ModContainers.SHELL_CONTAINER.get(), ShellScreen::new);
        MenuScreens.register(ModContainers.INCUBATOR_CONTAINER.get(), IncubatorScreen::new);
        MenuScreens.register(ModContainers.BOARD_CONTAINER.get(), BoardScreen::new);
        MenuScreens.register(ModContainers.PEARL_UI_CONTAINER.get(), PearlUIScreen::new);
        MenuScreens.register(ModContainers.PEARL_DEFECTIVE_UI_CONTAINER.get(), PearlUIScreenDefective::new);
        MenuScreens.register(ModContainers.ZIRCON_UI_CONTAINER.get(), ZirconUIScreen::new);

        MenuScreens.register(ModContainers.WARP_SELECTION.get(), WarpSelectionScreen::new);
        MenuScreens.register(ModContainers.WARP_CONFIG.get(), WarpConfigScreen::new);

        event.registerBlockEntityRenderer(ModTE.SHELL_TE.get(), ShellTER::new);

        Sheets.addWoodType(ModWoodTypes.DISTANT);
        Sheets.addWoodType(ModWoodTypes.CRYSTAL);
        Sheets.addWoodType(ModWoodTypes.KALEIDOSCOPE);
        Sheets.addWoodType(ModWoodTypes.SHADED);
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.SPODUMENE_PIECE.get());
        }
    }

    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();

        /*ItemBlockRenderTypes.setRenderLayer(ModFluids.PINK_ESSENCE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.PINK_ESSENCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WHITE_ESSENCE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WHITE_ESSENCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.YELLOW_ESSENCE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.YELLOW_ESSENCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.BLUE_ESSENCE_FLOWING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.BLUE_ESSENCE.get(), RenderType.translucent());*/

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
        event.registerLayerDefinition(ModelCobaltGuardian.LAYER_LOCATION, ModelCobaltGuardian::createBodyLayer);
        event.registerLayerDefinition(ModelSpecter.LAYER_LOCATION, ModelSpecter::createBodyLayer);
        event.registerLayerDefinition(ModelPepo.LAYER_LOCATION, ModelPepo::createBodyLayer);
        event.registerLayerDefinition(ModelHunter.LAYER_LOCATION, ModelHunter::createBodyLayer);
        event.registerLayerDefinition(ModelBeastmasterWolf.BEASTMASTER_WOLF, ModelBeastmasterWolf::createBodyLayer);
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
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModTE.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModTE.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.WARP_KEY);
        event.register(KeyBindings.FLIGHT_DESCENT_KEY);
    }

    @SubscribeEvent
    public static void onGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("aura", AuraOverlay.HUD_AURA);
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
