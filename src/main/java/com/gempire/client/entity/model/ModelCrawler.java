package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityCrawler;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelCrawler extends AnimatedGeoModel<EntityCrawler> {
    @Override
    public ResourceLocation getModelResource(EntityCrawler object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/crawler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityCrawler object) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationResource(EntityCrawler animatable) {
        return null;
    }
}