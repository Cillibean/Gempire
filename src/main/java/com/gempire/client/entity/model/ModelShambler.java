package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelShambler extends AnimatedGeoModel<EntityShambler> {
    @Override
    public ResourceLocation getModelResource(EntityShambler object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/crawler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityShambler object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/crawler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityShambler animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/crawler.animation.json");
    }
}