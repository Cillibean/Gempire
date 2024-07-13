package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.prism.EntityMirroredGuardian;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelMirroredGuardian extends GeoModel<EntityMirroredGuardian> {
    @Override
    public ResourceLocation getModelResource(EntityMirroredGuardian object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityMirroredGuardian object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityMirroredGuardian animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}