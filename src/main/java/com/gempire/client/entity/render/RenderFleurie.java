package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAbomination;
import com.gempire.client.entity.model.ModelFleurie;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityFleurie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderFleurie extends GeoEntityRenderer<EntityFleurie> {

    public RenderFleurie(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelFleurie());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityFleurie animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/fleurie/fleurie_"+animatable.getVariant()+".png");
    }
}
