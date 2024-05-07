package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityOverseer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelOverseer extends GeoModel<EntityOverseer> {
    @Override
    public ResourceLocation getModelResource(EntityOverseer object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/overseer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityOverseer object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/overseer/overseer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityOverseer animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/overseer.animation.json");
    }
}