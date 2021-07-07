package com.gempire.client.entity.model;

import com.gempire.entities.bases.AbstractQuartz;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Quartz - TheBetaZeta
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelAgate<T extends AbstractQuartz> extends ModelGem<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer arm1;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer arm2;
    public ModelRenderer hair;
    public ModelRenderer mohawk;
    public ModelRenderer messyponytail;
    public ModelRenderer cherryponytail;
    public ModelRenderer agatehair;
    public ModelRenderer shoulderpuff1;
    public ModelRenderer shoulderpuff2;

    public ModelAgate() {
        this.textureWidth = 124;
        this.textureHeight = 64;
        this.hair = new ModelRenderer(this, 0, 0);
        this.hair.setRotationPoint(0F, 0.0F, 0F);
        this.hair.addBox(-3.0F, 0.0F, -5.0F, 8.0F, 8.0F, 8.0F, 0.6F, 0.6F, 0.6F);
        this.shoulderpuff1 = new ModelRenderer(this, 0, 0);
        this.shoulderpuff1.setRotationPoint(-1.0F, -0.5F, -0.5F);
        this.shoulderpuff1.setTextureOffset(0, 17).addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shoulderpuff1, 0.0F, 0.0F, 0.08726646259971647F);
        this.agatehair = new ModelRenderer(this, 0, 0);
        this.agatehair.setRotationPoint(12F, 2.0F, 0F);
        this.agatehair.setTextureOffset(0, 50).addBox(0.0F, 0.0F, 0.0F, 14.0F, 4.0F, 4.0F, 0.5F, -0.1F, -0.1F);
        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.setRotationPoint(0.3F, 13.9F, 1.0F);
        this.leg1.setTextureOffset(16, 36).addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.5F, 4.0F, 0.1F, 0.0F, 0.1F);
        this.messyponytail = new ModelRenderer(this, 0, 0);
        this.messyponytail.setRotationPoint(0.0F, -1.5F, 7.0F);
        this.messyponytail.setTextureOffset(33, 36).addBox(-3.0F, -1.5F, -5.0F, 8.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(messyponytail, 0.2617993877991494F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-4.8F, 0.6F, -3.1F);
        this.body.setTextureOffset(38, 16).addBox(0.0F, 0.0F, 0.0F, 10.0F, 14.0F, 6.0F, 0.1F, -0.1F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.setRotationPoint(5.7F, 13.9F, 1.0F);
        this.leg2.setTextureOffset(64, 36).addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.5F, 4.0F, 0.1F, 0.0F, 0.1F);
        this.mohawk = new ModelRenderer(this, 0, 0);
        this.mohawk.setRotationPoint(0, -2.0F, 0);
        this.mohawk.setTextureOffset(70, 40).addBox(0, 0.0F, 0, 6.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.arm1 = new ModelRenderer(this, 0, 0);
        this.arm1.setRotationPoint(-4.3F, 0.1F, 1.0F);
        this.arm1.setTextureOffset(22, 18).addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.2F, 0.0F, 0.2F);
        this.cherryponytail = new ModelRenderer(this, 0, 0);
        this.cherryponytail.setRotationPoint(-2, -1.5F, 3.5F);
        this.cherryponytail.setTextureOffset(64, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 9.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.shoulderpuff2 = new ModelRenderer(this, 0, 0);
        this.shoulderpuff2.setRotationPoint(-1.0F, 0.0F, -0.5F);
        this.shoulderpuff2.setTextureOffset(86, 17).addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shoulderpuff2, 0.0F, 0.0F, -0.08726646259971647F);
        this.arm2 = new ModelRenderer(this, 0, 0);
        this.arm2.setRotationPoint(10.3F, 0.1F, 1.0F);
        this.arm2.setTextureOffset(70, 18).addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.2F, 0.0F, 0.2F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(4, -8.1F, 4);
        this.head.setTextureOffset(32, 0).addBox(-3, 0.2f, -5, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.hair);
        this.arm1.addChild(this.shoulderpuff1);
        this.hair.addChild(this.agatehair);
        this.body.addChild(this.leg1);
        this.hair.addChild(this.messyponytail);
        this.body.addChild(this.leg2);
        this.head.addChild(this.mohawk);
        this.body.addChild(this.arm1);
        this.hair.addChild(this.cherryponytail);
        this.arm2.addChild(this.shoulderpuff2);
        this.body.addChild(this.arm2);
        this.body.addChild(this.head);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        //this.mohawk.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.arm1, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.arm2, MathHelper.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.leg1, MathHelper.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.leg2, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
