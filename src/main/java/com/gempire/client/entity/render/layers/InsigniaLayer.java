package com.gempire.client.entity.render.layers;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGem;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class InsigniaLayer<E extends EntityGem, M extends ModelGem<E>> extends GempireLayer<EntityGem, ModelGem<EntityGem>> {
    private IEntityRenderer<EntityGem, ModelGem<EntityGem>> gemRenderer;

    public InsigniaLayer(IEntityRenderer<EntityGem, ModelGem<EntityGem>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        float[] outfitColors = SheepEntity.getDyeRgb(DyeColor.byId(gem.getInsigniaColor()));
        if(gem.getInsigniaVariant() < 99) {
            IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityTranslucent(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/outfits/insignia_" + gem.getInsigniaVariant() + ".png")));
            this.getEntityModel().setRotationAngles(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.getEntityModel().render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, outfitColors[0], outfitColors[1], outfitColors[2], 1.0F);
        }
        /*if(gem instanceof EntityStarterGem){
            ModelStarterGem model = ((ModelStarterGem)this.getEntityModel());
            model.
        }*/
    }
}
