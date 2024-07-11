package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityMantaShark;
import com.gempire.entities.other.EntityOpalMantaShark;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelOpalMantaShark extends GeoModel<EntityOpalMantaShark> {
    @Override
    public ResourceLocation getModelResource(EntityOpalMantaShark object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/mantashark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityOpalMantaShark object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/mantashark/opal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityOpalMantaShark animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/mantashark.animation.json");
    }
}