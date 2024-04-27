package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelSpecter;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.other.EntitySpecter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSpecter extends MobRenderer<EntitySpecter, ModelSpecter<EntitySpecter>> {

    public RenderSpecter(EntityRendererProvider.Context renderManagerIn, ModelSpecter<EntitySpecter> baseModel) {
        super(renderManagerIn, baseModel, 1f);
        this.addLayer(new SpecterEyesLayer<>(this));
    }

    @Override
    protected void scale(EntitySpecter entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpecter entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/specter/specter.png");
    }
}
