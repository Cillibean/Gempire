package com.gempire.client.block.model;

import com.gempire.Gempire;
import com.gempire.tileentities.YellowAltarTE;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class YellowAltarModel extends GeoModel<YellowAltarTE> {
    @Override
    public ResourceLocation getModelResource(YellowAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "geo/block/altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(YellowAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "textures/block/yellow_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(YellowAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "animations/block/altar.animation.json");
    }
}
