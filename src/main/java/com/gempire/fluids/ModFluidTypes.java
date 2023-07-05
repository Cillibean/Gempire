package com.gempire.fluids;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import io.netty.util.Attribute;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

import static net.minecraftforge.fluids.FluidInteractionRegistry.addInteraction;

public class ModFluidTypes {
    public static final ResourceLocation PINK_ESSENCE_STILL_RL = new ResourceLocation(Gempire.MODID, "blocks/pink_essence_still");
    public static final ResourceLocation PINK_ESSENCE_FLOWING_RL = new ResourceLocation(Gempire.MODID, "blocks/pink_essence_flowing");
    public static final ResourceLocation PINK_ESSENCE_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "blocks/pink_essence_overlay");
    public static final ResourceLocation BLUE_ESSENCE_STILL_RL = new ResourceLocation(Gempire.MODID, "blocks/blue_essence_still");
    public static final ResourceLocation BLUE_ESSENCE_FLOWING_RL = new ResourceLocation(Gempire.MODID, "blocks/blue_essence_flowing");
    public static final ResourceLocation BLUE_ESSENCE_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "blocks/blue_essence_overlay");
    public static final ResourceLocation YELLOW_ESSENCE_STILL_RL = new ResourceLocation(Gempire.MODID, "blocks/yellow_essence_still");
    public static final ResourceLocation YELLOW_ESSENCE_FLOWING_RL = new ResourceLocation(Gempire.MODID, "blocks/yellow_essence_flowing");
    public static final ResourceLocation YELLOW_ESSENCE_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "blocks/yellow_essence_overlay");
    public static final ResourceLocation WHITE_ESSENCE_STILL_RL = new ResourceLocation(Gempire.MODID, "blocks/white_essence_still");
    public static final ResourceLocation WHITE_ESSENCE_FLOWING_RL = new ResourceLocation(Gempire.MODID, "blocks/white_essence_flowing");
    public static final ResourceLocation WHITE_ESSENCE_OVERLAY_RL = new ResourceLocation(Gempire.MODID, "blocks/white_essence_overlay");
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Gempire.MODID);

    public static final RegistryObject<FluidType> PINK_ESSENCE_FLUID_TYPE = registerPink("pink_essence_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5));
    public static final RegistryObject<FluidType> BLUE_ESSENCE_FLUID_TYPE = registerBlue("blue_essence_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5));
    public static final RegistryObject<FluidType> YELLOW_ESSENCE_FLUID_TYPE = registerYellow("yellow_essence_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5));
    public static final RegistryObject<FluidType> WHITE_ESSENCE_FLUID_TYPE = registerWhite("white_essence_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5));



    private static RegistryObject<FluidType> registerPink(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(PINK_ESSENCE_STILL_RL, PINK_ESSENCE_FLOWING_RL, PINK_ESSENCE_OVERLAY_RL,
                0xFFFFFF, new Vector3f(1, 179f / 255f, 226f / 255f), properties));
    }

    private static RegistryObject<FluidType> registerBlue(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(BLUE_ESSENCE_STILL_RL, BLUE_ESSENCE_FLOWING_RL, BLUE_ESSENCE_OVERLAY_RL,
                0xFFFFFF, new Vector3f(179f / 255f, 215f / 255f, 1), properties));
    }

    private static RegistryObject<FluidType> registerYellow(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(YELLOW_ESSENCE_STILL_RL, YELLOW_ESSENCE_FLOWING_RL, YELLOW_ESSENCE_OVERLAY_RL,
                0xFFFFFF, new Vector3f(1, 249f / 255f, 179f / 255f), properties));
    }

    private static RegistryObject<FluidType> registerWhite(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WHITE_ESSENCE_STILL_RL, WHITE_ESSENCE_FLOWING_RL, WHITE_ESSENCE_OVERLAY_RL,
                0xFFFFFF, new Vector3f(242f / 255f, 242f / 255f, 242f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
