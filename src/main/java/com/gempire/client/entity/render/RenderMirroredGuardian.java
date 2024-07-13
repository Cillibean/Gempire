package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelMirroredGuardian;
import com.gempire.entities.bosses.prism.EntityMirroredGuardian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderMirroredGuardian extends GeoEntityRenderer<EntityMirroredGuardian> {

    public RenderMirroredGuardian(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelMirroredGuardian());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityMirroredGuardian animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
