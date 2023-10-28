package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAlabasterEmpress;
import com.gempire.entities.other.EntityAlabasterEmpress;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderAlabasterEmpress extends GeoEntityRenderer<EntityAlabasterEmpress> {

    public RenderAlabasterEmpress(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelAlabasterEmpress());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityAlabasterEmpress animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
