package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPepo;
import com.gempire.client.entity.model.ModelSpecter;
import com.gempire.client.entity.render.layers.SpecterEyesLayer;
import com.gempire.entities.other.EntityPepo;
import com.gempire.entities.other.EntitySpecter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

public class RenderPepo extends MobRenderer<EntityPepo, ModelPepo<EntityPepo>> {

    public RenderPepo(EntityRendererProvider.Context renderManagerIn, ModelPepo<EntityPepo> baseModel) {
        super(renderManagerIn, baseModel, 1f);
    }

    @Override
    protected void scale(EntityPepo entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.rotateAround(Axis.ZP.rotationDegrees(180), 0, -0.75f, 0);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPepo entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/pepo/pepo_" + entity.getVariant() + ".png");
    }
}
