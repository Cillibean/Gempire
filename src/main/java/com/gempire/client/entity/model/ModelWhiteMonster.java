package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityWhiteMonster;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelWhiteMonster extends GeoModel<EntityWhiteMonster> {
    @Override
    public ResourceLocation getModelResource(EntityWhiteMonster object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityWhiteMonster object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityWhiteMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}