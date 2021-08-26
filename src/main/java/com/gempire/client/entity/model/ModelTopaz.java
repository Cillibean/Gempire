package com.gempire.client.entity.model;

import com.gempire.entities.bases.EntityGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Topaz - TheBetaZeta
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelTopaz<T extends EntityGem> extends ModelGem<T> {
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer LeftLeg;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;
    public ModelRenderer RightLeg;
    public ModelRenderer gems;

    public ModelTopaz() {
        this.textureWidth = 94;
        this.textureHeight = 64;
        this.LeftLeg = new ModelRenderer(this, 0, 0);
        this.LeftLeg.setRotationPoint(-7.3F, 14.0F, -2.7F);
        this.LeftLeg.setTextureOffset(0, 45).addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, -0.5F, 0.0F, -0.5F);
        this.LeftArm = new ModelRenderer(this, 0, 0);
        this.LeftArm.setRotationPoint(-14.6F, -6.2F, -2.5F);
        this.LeftArm.setTextureOffset(0, 18).addBox(0.0F, 0.0F, 0.0F, 6.0F, 21.0F, 6.0F, 0.4F, 0.8F, 0.8F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(-7.0F, -6.0F, -4.0F);
        this.Body.setTextureOffset(24, 18).addBox(0.0F, 0.0F, 0.0F, 14.0F, 19.0F, 9.0F, 1.2F, 1.0F, 1.2F);
        this.RightLeg = new ModelRenderer(this, 0, 0);
        this.RightLeg.setRotationPoint(1.7F, 14.0F, -2.7F);
        this.RightLeg.setTextureOffset(70, 45).addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, -0.5F, 0.0F, -0.5F);
        this.gems = new ModelRenderer(this, 0, 0);
        this.gems.setRotationPoint(-6.0F, 3F, -2.0F);
        this.gems.setTextureOffset(30, 45).addBox(0.0F, 0.0F, 0.0F, 12.0F, 6.0F, 6.0F, -0.8F, -1.0F, -1.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0, -16.9F, 0);
        this.Head.setTextureOffset(28, 0).addBox(-4F, 0.0F, -3.5F, 8.0F, 10.0F, 8.0F, 0.0F, -0.1F, 0.0F);
        this.RightArm = new ModelRenderer(this, 0, 0);
        this.RightArm.setRotationPoint(8.6F, -6.2F, -2.5F);
        this.RightArm.setTextureOffset(70, 18).addBox(0.0F, 0.0F, 0.0F, 6.0F, 21.0F, 6.0F, 0.4F, 0.8F, 0.8F);
        this.Head.addChild(this.gems);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.LeftLeg, this.LeftArm, this.Body, this.RightLeg, this.Head, this.RightArm).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.LeftArm, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.RightArm, MathHelper.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.LeftLeg, MathHelper.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.RightLeg, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
