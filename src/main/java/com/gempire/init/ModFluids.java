package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.fluids.*;
import com.gempire.util.ModItemGroup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Gempire.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Gempire.MODID);

    public static final RegistryObject<FlowingFluid> PINK_ESSENCE = FLUIDS.register("pink_essence_source", () -> new PinkEssenceFluid.Source(ModFluids.PINK_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> PINK_ESSENCE_FLOWING = FLUIDS.register("pink_essence_flowing", () -> new PinkEssenceFluid.Flowing(ModFluids.PINK_ESSENCE_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties PINK_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.PINK_ESSENCE_FLUID_TYPE, PINK_ESSENCE, PINK_ESSENCE_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.PINK_ESSENCE_BLOCK)
            .bucket(ModItems.PINK_ESSENCE_BUCKET);

    public static final RegistryObject<FlowingFluid> BLUE_ESSENCE = FLUIDS.register("blue_essence_source", () -> new BlueEssenceFluid.Source(ModFluids.BLUE_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> BLUE_ESSENCE_FLOWING = FLUIDS.register("blue_essence_flowing", () -> new BlueEssenceFluid.Flowing(ModFluids.BLUE_ESSENCE_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties BLUE_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.BLUE_ESSENCE_FLUID_TYPE, BLUE_ESSENCE, BLUE_ESSENCE_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.BLUE_ESSENCE_BLOCK)
            .bucket(ModItems.BLUE_ESSENCE_BUCKET);

    public static final RegistryObject<FlowingFluid> YELLOW_ESSENCE = FLUIDS.register("yellow_essence_source", () -> new YellowEssenceFluid.Source(ModFluids.YELLOW_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> YELLOW_ESSENCE_FLOWING = FLUIDS.register("yellow_essence_flowing", () -> new YellowEssenceFluid.Flowing(ModFluids.YELLOW_ESSENCE_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties YELLOW_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.YELLOW_ESSENCE_FLUID_TYPE, YELLOW_ESSENCE, YELLOW_ESSENCE_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.YELLOW_ESSENCE_BLOCK)
            .bucket(ModItems.YELLOW_ESSENCE_BUCKET);

    public static final RegistryObject<FlowingFluid> WHITE_ESSENCE = FLUIDS.register("white_essence_source", () -> new WhiteEssenceFluid.Source(ModFluids.WHITE_ESSENCE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WHITE_ESSENCE_FLOWING = FLUIDS.register("white_essence_flowing", () -> new WhiteEssenceFluid.Flowing(ModFluids.WHITE_ESSENCE_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties WHITE_ESSENCE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.WHITE_ESSENCE_FLUID_TYPE, WHITE_ESSENCE, WHITE_ESSENCE_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.WHITE_ESSENCE_BLOCK)
            .bucket(ModItems.WHITE_ESSENCE_BUCKET);

}