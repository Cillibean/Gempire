package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityMantaShark;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelMantaShark extends GeoModel<EntityMantaShark> {
    @Override
    public ResourceLocation getModelResource(EntityMantaShark object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/mantashark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityMantaShark object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/mantashark/base.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityMantaShark animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/mantashark.animation.json");
    }
}