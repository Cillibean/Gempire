package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityBlueMonster;
import com.gempire.entities.other.EntityWhiteMonster;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelBlueMonster extends GeoModel<EntityBlueMonster> {
    @Override
    public ResourceLocation getModelResource(EntityBlueMonster object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityBlueMonster object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityBlueMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}