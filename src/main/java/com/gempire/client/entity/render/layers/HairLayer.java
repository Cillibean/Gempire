package com.gempire.client.entity.render.layers;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGem;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class HairLayer<E extends EntityGem, M extends ModelGem<E>> extends GempireLayer<EntityGem, ModelGem<EntityGem>> {
    private RenderLayerParent<EntityGem, ModelGem<EntityGem>> gemRenderer;

    public HairLayer(RenderLayerParent<EntityGem, ModelGem<EntityGem>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        int hair = gem.getHairColor();
        float r = ((hair & 16711680) >> 16) / 255f;
        float g = ((hair & 65280) >> 8) / 255f;
        float b = ((hair & 255) >> 0) / 255f;
        VertexConsumer builder;
        if (gem.getRebelled()) {
            builder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(new ResourceLocation(gem.getModID()+":textures/entity/" + this.getName(gem).toLowerCase() + "/hair_" + gem.getRebelHairVariant() + ".png")));
        } else {
            builder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(new ResourceLocation(gem.getModID()+":textures/entity/" + this.getName(gem).toLowerCase() + "/hair_" + gem.getHairVariant() + ".png")));
        }
        this.getParentModel().setupAnim(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.getParentModel().renderToBuffer(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        /*if(gem instanceof EntityStarterGem){
            ModelStarterGem model = ((ModelStarterGem)this.getEntityModel());
            model.
        }*/
    }
}
