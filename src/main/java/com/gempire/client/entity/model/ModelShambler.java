package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelShambler extends GeoModel<EntityShambler> {
    @Override
    public ResourceLocation getModelResource(EntityShambler object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityShambler object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityShambler animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}