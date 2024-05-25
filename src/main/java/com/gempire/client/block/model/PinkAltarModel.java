package com.gempire.client.block.model;

import com.gempire.Gempire;
import com.gempire.client.block.render.PinkAltarRenderer;
import com.gempire.tileentities.PinkAltarTE;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class PinkAltarModel extends GeoModel<PinkAltarTE> {
    @Override
    public ResourceLocation getModelResource(PinkAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "geo/block/altar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PinkAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "textures/block/pink_altar.json.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PinkAltarTE geoAnimatable) {
        return new ResourceLocation(Gempire.MODID, "animations/block/altar.animation.json");
    }
}
