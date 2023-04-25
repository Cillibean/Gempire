package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.GeoExampleEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestModel extends AnimatedGeoModel<GeoExampleEntity> {
    @Override
    public ResourceLocation getModelResource(GeoExampleEntity object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/test.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoExampleEntity object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/test/test.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeoExampleEntity animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/run.animation.json");
    }
}
