package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.fluids.BaseFluidType;
import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");

    public static final ResourceLocation PINK_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "block/pink_essence");
    public static final ResourceLocation YELLOW_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "block/yellow_essence");
    public static final ResourceLocation BLUE_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "block/blue_essence");
    public static final ResourceLocation WHITE_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "block/white_essence");


    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Gempire.MODID);

    public static final RegistryObject<FluidType> PINK_ESSENCE_FLUID_TYPE = register("pink_essence",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> YELLOW_ESSENCE_FLUID_TYPE = register("yellow_essence",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> BLUE_ESSENCE_FLUID_TYPE = register("blue_essence",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> WHITE_ESSENCE_FLUID_TYPE = register("white_essence",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, PINK_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}