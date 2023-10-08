package com.gempire.dimensions;

import com.gempire.Gempire;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class GempireDimensions {

    public static final ResourceKey<Level> DESOLATE_LANDS_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Gempire.MODID, "desolate_lands"));

    public static final ResourceKey<DimensionType> DESOLATE_LANDS_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, DESOLATE_LANDS_KEY.registry());

}
