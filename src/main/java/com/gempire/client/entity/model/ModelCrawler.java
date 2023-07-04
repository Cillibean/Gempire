package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityCrawler;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;;

public class ModelCrawler extends GeoModel<EntityCrawler> {
    @Override
    public ResourceLocation getModelResource(EntityCrawler object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/crawler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityCrawler object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/crawler/crawler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityCrawler animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/crawler.animation.json");
    }
}