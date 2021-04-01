package com.gempire.init;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gempire.Gempire.MODID;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static final ResourceLocation TEST_FLUID_STILL_LOCATION = new ResourceLocation("gempire:blocks/test_fluid_still");
    public static final ResourceLocation TEST_FLUID_FLOWING_LOCATION = new ResourceLocation("gempire:blocks/test_fluid_flowing");
    public static final ResourceLocation TEST_FLUID_OVERLAY_LOCATION = new ResourceLocation("gempire:blocks/test_fluid_overlay");

    public static final ResourceLocation PINK_ESSENCE_STILL_LOCATION = new ResourceLocation("gempire:blocks/pink_essence_still");
    public static final ResourceLocation PINK_ESSENCE_FLOWING_LOCATION = new ResourceLocation("gempire:blocks/pink_essence_flowing");

    public static final ResourceLocation BLUE_ESSENCE_STILL_LOCATION = new ResourceLocation("gempire:blocks/blue_essence_still");
    public static final ResourceLocation BLUE_ESSENCE_FLOWING_LOCATION = new ResourceLocation("gempire:blocks/blue_essence_flowing");

    public static final ResourceLocation YELLOW_ESSENCE_STILL_LOCATION = new ResourceLocation("gempire:blocks/yellow_essence_still");
    public static final ResourceLocation YELLOW_ESSENCE_FLOWING_LOCATION = new ResourceLocation("gempire:blocks/yellow_essence_flowing");

    public static final ResourceLocation WHITE_ESSENCE_STILL_LOCATION = new ResourceLocation("gempire:blocks/white_essence_still");
    public static final ResourceLocation WHITE_ESSENCE_FLOWING_LOCATION = new ResourceLocation("gempire:blocks/white_essence_flowing");

    public static RegistryObject<FlowingFluid> PINK_ESSENCE = FLUIDS.register("pink_essence", () ->
            new ForgeFlowingFluid.Source(ModFluids.PinkEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> PINK_ESSENCE_FLOWING = FLUIDS.register("pink_essence_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.PinkEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> BLUE_ESSENCE = FLUIDS.register("blue_essence", () ->
            new ForgeFlowingFluid.Source(ModFluids.BlueEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> BLUE_ESSENCE_FLOWING = FLUIDS.register("blue_essence_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.BlueEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> YELLOW_ESSENCE = FLUIDS.register("yellow_essence", () ->
            new ForgeFlowingFluid.Source(ModFluids.YellowEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> YELLOW_ESSENCE_FLOWING = FLUIDS.register("yellow_essence_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.YellowEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> WHITE_ESSENCE = FLUIDS.register("white_essence", () ->
            new ForgeFlowingFluid.Source(ModFluids.WhiteEssenceProperties())
    );
    public static RegistryObject<FlowingFluid> WHITE_ESSENCE_FLOWING = FLUIDS.register("white_essence_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.WhiteEssenceProperties())
    );

    /*public static RegistryObject<FlowingFluid> TEST_FLUID = FLUIDS.register("test_fluid", () ->
            new ForgeFlowingFluid.Source(ModFluids.TestFluidProperties())
    );
    public static RegistryObject<FlowingFluid> TEST_FLUID_FLOWING = FLUIDS.register("test_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.TestFluidProperties())
    );*/

    /*private static ForgeFlowingFluid.Properties TestFluidProperties()
    {
        return new ForgeFlowingFluid.Properties(ModFluids.TEST_FLUID, ModFluids.TEST_FLUID_FLOWING,
                FluidAttributes.builder(
                        ModFluids.TEST_FLUID_STILL_LOCATION,
                        ModFluids.TEST_FLUID_FLOWING_LOCATION)
                        .overlay(ModFluids.TEST_FLUID_OVERLAY_LOCATION)
                        .density(3000).viscosity(6000))
                .block(ModBlocks.TEST_FLUID_BLOCK);
    }*/

    private static ForgeFlowingFluid.Properties PinkEssenceProperties()
    {
        return new ForgeFlowingFluid.Properties(ModFluids.PINK_ESSENCE, ModFluids.PINK_ESSENCE_FLOWING,
                FluidAttributes.builder(
                        ModFluids.PINK_ESSENCE_STILL_LOCATION,
                        ModFluids.PINK_ESSENCE_FLOWING_LOCATION)
                        .density(3000).viscosity(6000).luminosity(15))
                .block(ModBlocks.PINK_ESSENCE_BLOCK)
                .bucket(ModItems.PINK_ESSENCE);
    }

    private static ForgeFlowingFluid.Properties BlueEssenceProperties()
    {
        return new ForgeFlowingFluid.Properties(ModFluids.BLUE_ESSENCE, ModFluids.BLUE_ESSENCE_FLOWING,
                FluidAttributes.builder(
                        ModFluids.BLUE_ESSENCE_STILL_LOCATION,
                        ModFluids.BLUE_ESSENCE_FLOWING_LOCATION)
                        .density(3000).viscosity(6000).luminosity(15))
                .block(ModBlocks.BLUE_ESSENCE_BLOCK)
                .bucket(ModItems.BLUE_ESSENCE);
    }

    private static ForgeFlowingFluid.Properties YellowEssenceProperties()
    {
        return new ForgeFlowingFluid.Properties(ModFluids.YELLOW_ESSENCE, ModFluids.YELLOW_ESSENCE_FLOWING,
                FluidAttributes.builder(
                        ModFluids.YELLOW_ESSENCE_STILL_LOCATION,
                        ModFluids.YELLOW_ESSENCE_FLOWING_LOCATION)
                        .density(3000).viscosity(6000).luminosity(15))
                .block(ModBlocks.YELLOW_ESSENCE_BLOCK)
                .bucket(ModItems.YELLOW_ESSENCE);
    }

    private static ForgeFlowingFluid.Properties WhiteEssenceProperties()
    {
        return new ForgeFlowingFluid.Properties(ModFluids.WHITE_ESSENCE, ModFluids.WHITE_ESSENCE_FLOWING,
                FluidAttributes.builder(
                        ModFluids.WHITE_ESSENCE_STILL_LOCATION,
                        ModFluids.WHITE_ESSENCE_FLOWING_LOCATION)
                        .density(3000).viscosity(6000).luminosity(15))
                .block(ModBlocks.WHITE_ESSENCE_BLOCK)
                .bucket(ModItems.WHITE_ESSENCE);
    }
}
