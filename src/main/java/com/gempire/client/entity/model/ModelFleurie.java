package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityFleurie;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

;import javax.swing.text.html.parser.Entity;

public class ModelFleurie extends GeoModel<EntityFleurie> {
    @Override
    public ResourceLocation getModelResource(EntityFleurie object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/fleurie.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityFleurie object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/fleurie/fleurie_"+ object.getVariant()+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityFleurie animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/fleurie.animation.json");
    }
}