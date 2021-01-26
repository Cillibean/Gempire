package com.gempire.client.entity.render.layers;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGem;
import com.gempire.client.entity.model.ModelStarterGem;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityStarterGem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StarterGemPlacementLayer<E extends EntityStarterGem, M extends ModelStarterGem<E>> extends GempireLayer<EntityStarterGem, ModelStarterGem<EntityStarterGem>> {
    private IEntityRenderer<EntityStarterGem, ModelStarterGem<EntityStarterGem>> gemRenderer;
    //TODO Temp

    public StarterGemPlacementLayer(IEntityRenderer<EntityStarterGem, ModelStarterGem<EntityStarterGem>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityStarterGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        int skin = gem.getSkinColor();
        ModelStarterGem model = (ModelStarterGem)this.getEntityModel();
        float r = ((skin & 16711680) >> 16) / 255f;
        float g = ((skin & 65280) >> 8) / 255f;
        float b = ((skin & 255) >> 0) / 255f;
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(new ResourceLocation(Gempire.MODID+":textures/entity/" + this.getName(gem).toLowerCase() + "/gem_1.png")));
        this.getEntityModel().setRotationAngles(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        switch (gem.getGemPlacementE()){
            case BACK_OF_HEAD:
                model.BackHeadGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case LEFT_EYE:
                model.LeftEyeGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case RIGHT_EYE:
                model.RightEyeGem1.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case BACK:
                model.BackGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case CHEST:
                model.ChestGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case LEFT_HAND:
                model.LeftHandGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case RIGHT_HAND:
                model.RightHandGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case LEFT_KNEE:
                model.LeftLegGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            case RIGHT_KNEE:
                model.RightLegGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
                break;
            default:
                model.BackHeadGem.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        }
    }
}
