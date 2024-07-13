package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.base.EntityFuchsiaPaladin;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelFuchsiaPaladin extends GeoModel<EntityFuchsiaPaladin> {
    @Override
    public ResourceLocation getModelResource(EntityFuchsiaPaladin object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityFuchsiaPaladin object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityFuchsiaPaladin animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}