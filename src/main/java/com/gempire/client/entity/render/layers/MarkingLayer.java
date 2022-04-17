package com.gempire.client.entity.render.layers;

import com.gempire.client.entity.model.ModelGem;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class MarkingLayer<E extends EntityGem, M extends ModelGem<E>> extends GempireLayer<EntityGem, ModelGem<EntityGem>> {
    private RenderLayerParent<EntityGem, ModelGem<EntityGem>> gemRenderer;

    public MarkingLayer(RenderLayerParent<EntityGem, ModelGem<EntityGem>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(gem.hasMarkings()) {
            int skin = gem.getMarkingColor();
            float r = ((skin & 16711680) >> 16) / 255f;
            float g = ((skin & 65280) >> 8) / 255f;
            float b = ((skin & 255) >> 0) / 255f;
            VertexConsumer builder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/markings/" + (gem instanceof EntityVaryingGem ? ((EntityVaryingGem)gem).NameFromColor((byte) gem.getSkinColorVariant()) : this.getName(gem)) + "_marking_" + gem.getSkinVariant() + ".png")));
            this.getParentModel().setupAnim(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.getParentModel().renderToBuffer(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        }
        if(gem.hasMarkings2()) {
            int skin = gem.getMarking2Color();
            float r = ((skin & 16711680) >> 16) / 255f;
            float g = ((skin & 65280) >> 8) / 255f;
            float b = ((skin & 255) >> 0) / 255f;
            VertexConsumer builder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/markings/" + (gem instanceof EntityVaryingGem ? ((EntityVaryingGem)gem).NameFromColor((byte) gem.getSkinColorVariant()) : this.getName(gem)) + "_marking_2_" + gem.getSkinVariant() + ".png")));
            this.getParentModel().setupAnim(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.getParentModel().renderToBuffer(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        }
    }
}
