package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCobaltGuardian;
import com.gempire.entities.other.EntityCobaltGuardian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderCobaltGuardian extends GeoEntityRenderer<EntityCobaltGuardian> {

    public RenderCobaltGuardian(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelCobaltGuardian());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityCobaltGuardian animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
