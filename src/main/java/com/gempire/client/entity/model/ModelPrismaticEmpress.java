package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.prism.EntityPrismaticEmpress;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelPrismaticEmpress extends GeoModel<EntityPrismaticEmpress> {
    @Override
    public ResourceLocation getModelResource(EntityPrismaticEmpress object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityPrismaticEmpress object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityPrismaticEmpress animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}