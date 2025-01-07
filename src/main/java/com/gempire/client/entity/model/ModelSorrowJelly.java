package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityOverseer;
import com.gempire.entities.other.EntitySorrowJelly;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelSorrowJelly extends GeoModel<EntitySorrowJelly> {
    @Override
    public ResourceLocation getModelResource(EntitySorrowJelly object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/sorrow_jelly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySorrowJelly object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/sorrowjelly/sorrowjelly.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySorrowJelly animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/sorrow_jelly.animation.json");
    }


}