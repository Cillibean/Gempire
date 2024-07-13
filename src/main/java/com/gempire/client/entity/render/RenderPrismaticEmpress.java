package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPrismaticEmpress;
import com.gempire.entities.bosses.prism.EntityPrismaticEmpress;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderPrismaticEmpress extends GeoEntityRenderer<EntityPrismaticEmpress> {

    public RenderPrismaticEmpress(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelPrismaticEmpress());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityPrismaticEmpress animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
