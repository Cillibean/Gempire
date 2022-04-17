package com.gempire.client.entity.render.layers;

import com.gempire.client.entity.model.ModelGem;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class GempireLayer<E extends EntityGem, M extends ModelGem<E>> extends RenderLayer<E, M>  {

    public GempireLayer(RenderLayerParent<E, M> entityRendererIn) {
        super(entityRendererIn);
    }

    public String getName(EntityGem gem){
        return gem.getWholeGemName();
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGem entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}
