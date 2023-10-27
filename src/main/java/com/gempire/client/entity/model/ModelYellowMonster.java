package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityWhiteMonster;
import com.gempire.entities.other.EntityYellowMonster;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelYellowMonster extends GeoModel<EntityYellowMonster> {
    @Override
    public ResourceLocation getModelResource(EntityYellowMonster object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityYellowMonster object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityYellowMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}