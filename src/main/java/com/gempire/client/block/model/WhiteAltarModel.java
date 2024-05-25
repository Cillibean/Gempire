package com.gempire.client.block.model;

import com.gempire.Gempire;
import com.gempire.tileentities.WhiteAltarTE;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class WhiteAltarModel extends GeoModel<WhiteAltarTE> {
    @Override
    public ResourceLocation getModelResource(WhiteAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "geo/block/altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WhiteAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "textures/block/white_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WhiteAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "animations/block/altar.animation.json");
    }
}
