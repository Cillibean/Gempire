package com.gempire.client.block.model;

import com.gempire.Gempire;
import com.gempire.tileentities.BlueAltarTE;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class BlueAltarModel extends GeoModel<BlueAltarTE> {
    @Override
    public ResourceLocation getModelResource(BlueAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "geo/block/altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlueAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "textures/block/blue_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlueAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "animations/block/altar.animation.json");
    }
}
