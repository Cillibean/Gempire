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
 * Nephrite - Segapop
 * Created using Tabula 8.0.0
 */

public class ModelNephrite<T extends EntityGem> extends ModelGem<T> {
    public ModelRenderer Hair;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer Skirt;
    public ModelRenderer LShoulder;
    public ModelRenderer RShoulder;
    public ModelRenderer PigtailL;
    public ModelRenderer PigtailR;
    public ModelRenderer Pigtail2L;
    public ModelRenderer Pigtail2R;
    public ModelRenderer Ponytail;
    public ModelRenderer HairPoof;
    public ModelRenderer Ponytail_1;
    public ModelRenderer Fuzz;
    public ModelRenderer HairL;

    public ModelNephrite() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 8, 16);
        this.Body.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Body.addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 4.0F, 0.0F, 0.0F, -0.2F);
        this.LegR = new ModelRenderer(this, 28, 32);
        this.LegR.setRotationPoint(1.5F, 11.0F, 1.0F);
        this.LegR.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RShoulder = new ModelRenderer(this, 36, 25);
        //this.RShoulder.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.RShoulder.addBox(0.0F, -3.0F, -2.0F, 5.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.LShoulder = new ModelRenderer(this, 36, 16);
        //this.LShoulder.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.LShoulder.addBox(-5.0F, -3.0F, -2.0F, 5.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Pigtail2L = new ModelRenderer(this, 54, 16);
        //this.Pigtail2L.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Pigtail2L.addBox(-8.5F, -5.5F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Pigtail2L, 0.0F, 0.0F, 0.296705972839036F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Skirt = new ModelRenderer(this, 0, 53);
        //this.Skirt.setRotationPoint(-8.0F, 8.0F, 1.8F);
        this.Skirt.addBox(-8.0F, 8.0F, 1.0F, 16.0F, 11.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Skirt, 0.08726646259971647F, 0.0F, 0.0F);
        this.HairPoof = new ModelRenderer(this, 32, 39);
        //this.HairPoof.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.HairPoof.addBox(-7.0F, -4.2F, -5.0F, 14.0F, 4.0F, 10.0F, -0.2F, 0.0F, 0.0F);
        this.PigtailL = new ModelRenderer(this, 32, 53);
        //this.PigtailL.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.PigtailL.addBox(-8.0F, -9.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.PigtailR = new ModelRenderer(this, 48, 53);
        //this.PigtailR.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.PigtailR.addBox(4.0F, -9.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Hair = new ModelRenderer(this, 32, 0);
        //this.Hair.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Hair.addBox(-4.0F, -8.0F, -4.2F, 8.0F, 8.0F, 8.0F, 0.5F, 0.5F, 0.5F);
        this.Ponytail = new ModelRenderer(this, 8, 32);
        //this.Ponytail.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Ponytail.addBox(-2.5F, -9.5F, 5.5F, 5.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Ponytail, 0.2617993877991494F, 0.0F, 0.0F);
        this.Fuzz = new ModelRenderer(this, 0, 0);
        //this.Fuzz.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.Fuzz.setTextureOffset(64, 0).addBox(-6.0F, -3.5F, -1.8F, 12.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Ponytail_1 = new ModelRenderer(this, 78, 50);
        //this.Ponytail_1.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Ponytail_1.addBox(-3.5F, -12.0F, 2.5F, 7.0F, 7.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.ArmL = new ModelRenderer(this, 0, 16);
        this.ArmL.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.ArmL.addBox(-2.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.ArmR = new ModelRenderer(this, 28, 16);
        this.ArmR.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.ArmR.addBox(0.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.HairL = new ModelRenderer(this, 0, 0);
        //this.HairL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HairL.setTextureOffset(88, 25).addBox(-4.0F, -8.9F, -4.1F, 8.0F, 8.0F, 12.0F, 0.6F, 0.6F, 0.6F);
        this.LegL = new ModelRenderer(this, 0, 32);
        this.LegL.setRotationPoint(-1.5F, 11.0F, 1.0F);
        this.LegL.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Pigtail2R = new ModelRenderer(this, 66, 16);
        //this.Pigtail2R.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Pigtail2R.addBox(5.5F, -5.5F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Pigtail2R, 0.0F, 0.0F, -0.296705972839036F);
        this.Head.addChild(this.Hair);
        this.Hair.addChild(this.PigtailL);
        this.Hair.addChild(this.PigtailR);
        this.Hair.addChild(this.Pigtail2L);
        this.Hair.addChild(this.Pigtail2R);
        this.Hair.addChild(this.Ponytail);
        this.Hair.addChild(this.HairPoof);
        this.Hair.addChild(this.Ponytail_1);
        this.Hair.addChild(this.Fuzz);
        this.Hair.addChild(this.HairL);
        this.Body.addChild(this.Skirt);
        this.ArmL.addChild(this.LShoulder);
        this.ArmR.addChild(this.RShoulder);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Body, this.LegR, this.Head, this.ArmL, this.ArmR, this.LegL).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.ArmL, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.ArmR, MathHelper.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.LegL, MathHelper.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.LegR, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
