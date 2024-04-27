package com.gempire.client.entity.render.layers;

import com.gempire.client.entity.model.ModelSpecter;
import com.gempire.entities.other.EntitySpecter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class SpecterEyesLayer<E extends EntitySpecter, M extends EntityModel<E>> extends RenderLayer<EntitySpecter, ModelSpecter<EntitySpecter>> {
    private RenderLayerParent<EntitySpecter, ModelSpecter<EntitySpecter>> gemRenderer;

    public SpecterEyesLayer(RenderLayerParent<EntitySpecter, ModelSpecter<EntitySpecter>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntitySpecter gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer builder = bufferIn.getBuffer(RenderType.entityTranslucentEmissive(new ResourceLocation("gempire:textures/entity/specter/eyes.png")));
        this.getParentModel().setupAnim(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.getParentModel().renderToBuffer(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1.0F);
        /*if(gem instanceof EntityStarterGem){
            ModelStarterGem model = ((ModelStarterGem)this.getEntityModel());
            model.
        }*/
    }
}
