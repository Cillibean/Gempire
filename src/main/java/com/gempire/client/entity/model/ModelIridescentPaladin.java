package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.prism.EntityIridescentPaladin;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelIridescentPaladin extends GeoModel<EntityIridescentPaladin> {
    @Override
    public ResourceLocation getModelResource(EntityIridescentPaladin object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityIridescentPaladin object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityIridescentPaladin animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}