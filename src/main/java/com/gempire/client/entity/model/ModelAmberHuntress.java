package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.base.EntityAmberHuntress;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelAmberHuntress extends GeoModel<EntityAmberHuntress> {
    @Override
    public ResourceLocation getModelResource(EntityAmberHuntress object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/amber_huntress.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityAmberHuntress object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/boss/huntress/amber/base.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityAmberHuntress animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/amber_huntress.animation.json");
    }
}