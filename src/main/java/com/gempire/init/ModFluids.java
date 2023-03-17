package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.tileentities.TankTE;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Gempire.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_PINK_ESSENCE = FLUIDS.register("pink_essence_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.PINK_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_PINK_ESSENCE = FLUIDS.register("pink_essence_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.PINK_ESSENCE_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties PINK_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.PINK_ESSENCE_FLUID_TYPE, SOURCE_PINK_ESSENCE, FLOWING_PINK_ESSENCE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.PINK_ESSENCE_BLOCK)
            .bucket(ModItems.PINK_ESSENCE);

    public static final RegistryObject<FlowingFluid> SOURCE_YELLOW_ESSENCE = FLUIDS.register("yellow_essence_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.YELLOW_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_YELLOW_ESSENCE = FLUIDS.register("yellow_essence_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.YELLOW_ESSENCE_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties YELLOW_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.YELLOW_ESSENCE_FLUID_TYPE, SOURCE_YELLOW_ESSENCE, FLOWING_YELLOW_ESSENCE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.YELLOW_ESSENCE_BLOCK)
            .bucket(ModItems.YELLOW_ESSENCE);

    public static final RegistryObject<FlowingFluid> SOURCE_BLUE_ESSENCE = FLUIDS.register("blue_essence_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.BLUE_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BLUE_ESSENCE = FLUIDS.register("blue_essence_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.BLUE_ESSENCE_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties BLUE_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.BLUE_ESSENCE_FLUID_TYPE, SOURCE_BLUE_ESSENCE, FLOWING_BLUE_ESSENCE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.BLUE_ESSENCE_BLOCK)
            .bucket(ModItems.BLUE_ESSENCE);

    public static final RegistryObject<FlowingFluid> SOURCE_WHITE_ESSENCE = FLUIDS.register("white_essence_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.WHITE_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_WHITE_ESSENCE = FLUIDS.register("white_essence_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.WHITE_ESSENCE_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties WHITE_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.WHITE_ESSENCE_FLUID_TYPE, SOURCE_WHITE_ESSENCE, FLOWING_WHITE_ESSENCE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.WHITE_ESSENCE_BLOCK)
            .bucket(ModItems.WHITE_ESSENCE);

    public static void registerFluidBuckets(){
        TankTE.FLUID_BUCKETS.put(ModFluids.SOURCE_WHITE_ESSENCE.get(), ModItems.WHITE_ESSENCE.get());
        TankTE.FLUID_BUCKETS.put(ModFluids.SOURCE_YELLOW_ESSENCE.get(), ModItems.YELLOW_ESSENCE.get());
        TankTE.FLUID_BUCKETS.put(ModFluids.SOURCE_BLUE_ESSENCE.get(), ModItems.BLUE_ESSENCE.get());
        TankTE.FLUID_BUCKETS.put(ModFluids.SOURCE_PINK_ESSENCE.get(), ModItems.PINK_ESSENCE.get());
        TankTE.FLUID_BUCKETS.put(Fluids.WATER, Items.WATER_BUCKET);
        TankTE.FLUID_BUCKETS.put(Fluids.LAVA, Items.LAVA_BUCKET);
    }
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}