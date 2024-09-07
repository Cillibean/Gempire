package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.base.EntityAlabasterEmpress;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelAlabasterEmpress extends GeoModel<EntityAlabasterEmpress> {
    @Override
    public ResourceLocation getModelResource(EntityAlabasterEmpress object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/alabaster_empress.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityAlabasterEmpress object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/boss/empress/alabaster/base.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityAlabasterEmpress animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/alabaster_empress.animation.json");
    }
}