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


public class OutfitLayer<E extends EntityGem, M extends ModelGem<E>> extends GempireLayer<EntityGem, ModelGem<EntityGem>> {
    private IEntityRenderer<EntityGem, ModelGem<EntityGem>> gemRenderer;

    public OutfitLayer(IEntityRenderer<EntityGem, ModelGem<EntityGem>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        float[] outfitColors = SheepEntity.getDyeRgb(DyeColor.byId(gem.getOutfitColor()));
        IVertexBuilder builder = null;
        if(gem.hasOutfitPlacementVariant()){
            for(int i : gem.outfitPlacementVariants()){
                if(i == gem.getGemPlacement()) {
                    builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/outfits/outfit_" + gem.getGemPlacement() + "_0.png")));
                    break;
                }
                else{
                    builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/outfits/outfit_" + gem.getOutfitVariant() + ".png")));
                }
            }
        }
        else{
            builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(new ResourceLocation(gem.getModID() + ":textures/entity/" + this.getName(gem).toLowerCase() + "/outfits/outfit_" + gem.getOutfitVariant() + ".png")));
        }
        this.getEntityModel().setRotationAngles(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.getEntityModel().render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, outfitColors[0], outfitColors[1], outfitColors[2], 1.0F);
        /*if(gem instanceof EntityStarterGem){
            ModelStarterGem model = ((ModelStarterGem)this.getEntityModel());
            model.
        }*/
    }
}
