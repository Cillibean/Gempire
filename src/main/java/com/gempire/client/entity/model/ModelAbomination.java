package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelAbomination extends AnimatedGeoModel<EntityAbomination> {
    @Override
    public ResourceLocation getModelResource(EntityAbomination object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/abomination.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityAbomination object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/abomination/abomination.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityAbomination animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/abomination.animation.json");
    }
}