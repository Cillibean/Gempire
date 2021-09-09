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
 * zircon - Undefined
 * Created using Tabula 8.0.0
 */

public class ModelZircon<T extends EntityGem> extends ModelGem<T> {
    public ModelRenderer Body;
    public ModelRenderer Hat1;
    public ModelRenderer Chest;
    public ModelRenderer RightArm;
    public ModelRenderer Head;
    public ModelRenderer RLegPuff;
    public ModelRenderer RightLeg;
    public ModelRenderer LLegPuff;
    public ModelRenderer LeftArm;
    public ModelRenderer Nose;
    public ModelRenderer EyeGlass;
    public ModelRenderer Hat2;
    public ModelRenderer RArmPuff;
    public ModelRenderer LArmPuff;
    public ModelRenderer LeftLeg;
    public ModelRenderer Shoulders;

    public ModelZircon() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new ModelRenderer(this, 0, 0);
        this.LeftArm.setRotationPoint(-4.0F, 2.8F, 0.0F);
        this.LeftArm.setTextureOffset(12, 28).addBox(-2.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RArmPuff = new ModelRenderer(this, 0, 0);
        this.RArmPuff.mirror = true;
        //this.RArmPuff.setRotationPoint(4.0F, 3.0F, 0.0F);
        this.RArmPuff.setTextureOffset(0, 22).addBox(-0.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.RightArm = new ModelRenderer(this, 0, 0);
        this.RightArm.mirror = true;
        this.RightArm.setRotationPoint(4.0F, 2.8F, 0.0F);
        this.RightArm.setTextureOffset(56, 10).addBox(0.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.EyeGlass = new ModelRenderer(this, 0, 17);
        //this.EyeGlass.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.EyeGlass.addBox(0.4F, -6.2F, -4.0F, 3.0F, 4.0F, 0.0F, -0.1F, -0.1F, 0.0F);
        this.Body = new ModelRenderer(this, 32, 12);
        this.Body.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Body.addBox(-3.0F, 0.1F, -1.9F, 6.0F, 10.0F, 4.0F, -0.1F, 0.1F, -0.1F);
        this.Nose = new ModelRenderer(this, 0, 0);
        //this.Nose.setRotationPoint(0.3F, 1.7F, 0.0F);
        this.Nose.addBox(-0.8F, -3.2F, -5.7F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RLegPuff = new ModelRenderer(this, 0, 0);
        this.RLegPuff.mirror = true;
        //this.RLegPuff.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.RLegPuff.setTextureOffset(0, 51).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Head.addBox(-4.0F, -7.8F, -4.0F, 8.0F, 8.0F, 8.0F, -0.3F, -0.2F, -0.3F);
        this.RightLeg = new ModelRenderer(this, 0, 0);
        this.RightLeg.mirror = true;
        this.RightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.RightLeg.setTextureOffset(55, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.LArmPuff = new ModelRenderer(this, 0, 0);
        //this.LArmPuff.setRotationPoint(-4.0F, 3.0F, 0.0F);
        this.LArmPuff.setTextureOffset(0, 28).addBox(-2.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 0);
        this.LeftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.LeftLeg.setTextureOffset(20, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.LLegPuff = new ModelRenderer(this, 0, 0);
        //this.LLegPuff.setRotationPoint(-2.0F, 11.9F, 0.0F);
        this.LLegPuff.setTextureOffset(0, 34).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Hat1 = new ModelRenderer(this, 0, 0);
        //this.Hat1.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Hat1.setTextureOffset(28, 28).addBox(-4.0F, -9.5F, -6.0F, 8.0F, 4.0F, 10.0F, -0.3F, -0.2F, -0.3F);
        this.Chest = new ModelRenderer(this, 0, 0);
        this.Chest.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Chest.setTextureOffset(32, 0).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Shoulders = new ModelRenderer(this, 0, 0);
        this.Shoulders.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Shoulders.setTextureOffset(0, 43).addBox(-7.0F, 0.0F, -2.5F, 14.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Hat2 = new ModelRenderer(this, 0, 0);
        //this.Hat2.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Hat2.setTextureOffset(32, 45).addBox(-4.0F, -12.6F, -4.0F, 8.0F, 5.0F, 8.0F, -0.3F, 0.0F, -0.3F);
        this.Head.addChild(this.Nose);
        this.Head.addChild(this.Hat1);
        this.Head.addChild(this.Hat2);
        this.Head.addChild(this.EyeGlass);
        this.RightArm.addChild(this.RArmPuff);
        this.LeftArm.addChild(this.LArmPuff);
        this.LeftLeg.addChild(this.LLegPuff);
        this.RightLeg.addChild(this.RLegPuff);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.LeftArm, this.RightArm, this.Body, this.Head, this.RightLeg, this.LeftLeg, this.Chest, this.Shoulders).forEach((modelRenderer) -> {
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
