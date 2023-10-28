package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityAmberHuntress;
import com.gempire.entities.other.EntityFuchsiaPaladin;
import com.gempire.entities.other.EntityGildedHuntress;
import com.gempire.entities.other.EntityMirroredGuardian;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelGildedHuntress extends GeoModel<EntityGildedHuntress> {
    @Override
    public ResourceLocation getModelResource(EntityGildedHuntress object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/shambler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityGildedHuntress object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityGildedHuntress animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/shambler.animation.json");
    }
}