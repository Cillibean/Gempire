package com.gempire.client.entity.render.layers;

import com.gempire.client.entity.model.ModelGem;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.entities.bases.AbstractQuartz;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class QuartzMarkingLayer<E extends AbstractQuartz, M extends ModelQuartz<E>> extends GempireLayer<AbstractQuartz, ModelQuartz<AbstractQuartz>> {
    private IEntityRenderer<AbstractQuartz, ModelQuartz<AbstractQuartz>> gemRenderer;

    public QuartzMarkingLayer(IEntityRenderer<AbstractQuartz, ModelQuartz<AbstractQuartz>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, AbstractQuartz gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(gem.hasMarkings()) {
            int skin = gem.getMarkingColor();
            float r = ((skin & 16711680) >> 16) / 255f;
            float g = ((skin & 65280) >> 8) / 255f;
            float b = ((skin & 255) >> 0) / 255f;
            IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/markings/" + gem.NameFromColor((byte) gem.getSkinColorVariant()) + "_marking_" + gem.getSkinVariant() + ".png")));
            this.getEntityModel().setRotationAngles(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.getEntityModel().render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        }
        if(gem.hasMarkings2()) {
            int skin = gem.getMarking2Color();
            float r = ((skin & 16711680) >> 16) / 255f;
            float g = ((skin & 65280) >> 8) / 255f;
            float b = ((skin & 255) >> 0) / 255f;
            IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/markings/" + gem.NameFromColor((byte) gem.getSkinColorVariant()) + "_marking_2_" + gem.getSkinVariant() + ".png")));
            this.getEntityModel().setRotationAngles(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.getEntityModel().render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        }
    }
}
