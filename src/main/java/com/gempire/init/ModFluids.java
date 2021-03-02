package com.gempire.init;

import net.minecraft.fluid.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gempire.Gempire.MODID;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    //public static final RegistryObject<Fluid> TEST_FLUID = FLUIDS.register("test_fluid", () -> new TestFluid.Source());

    //public static final RegistryObject<FlowingFluid> TEST_FLUID_FLOWING = FLUIDS.register("test_fluid_flowing", () -> new TestFluid.Flowing());
}
