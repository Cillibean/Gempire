package com.gempire.fluids;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class ModFluidTags {
    public static final TagKey<Fluid> PINK_ESSENCE = create("pink_essence");
    public static final TagKey<Fluid> BLUE_ESSENCE = create("blue_essence");
    public static final TagKey<Fluid> YELLOW_ESSENCE = create("yellow_essence");
    public static final TagKey<Fluid> WHITE_ESSENCE = create("white_essence");


    private ModFluidTags() {
    }

    private static TagKey<Fluid> create(String p_203851_) {
        return TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(p_203851_));
    }

    public static TagKey<Fluid> create(ResourceLocation name) {
        return TagKey.create(Registry.FLUID_REGISTRY, name);
    }
}
