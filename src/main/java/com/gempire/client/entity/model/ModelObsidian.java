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
 * ModelObsidian - Pezzottaite
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelObsidian<T extends EntityGem> extends ModelGem<T> {
    public ModelRenderer Head;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer Body;
    public ModelRenderer Waist;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;

    public ModelObsidian() {
        this.textureWidth = 112;
        this.textureHeight = 64;
        this.LeftLeg = new ModelRenderer(this, 82, 27);
        this.LeftLeg.setRotationPoint(-3.5F, 15.0F, 1.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 34, 26);
        this.LeftArm.setRotationPoint(10.0F, -3.0F, 1.0F);
        this.LeftArm.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 17.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.Body.addBox(-7.0F, 0.0F, -4.5F, 14.0F, 11.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 82, 40);
        this.RightLeg.setRotationPoint(3.5F, 15.0F, 1.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Waist = new ModelRenderer(this, 0, 20);
        this.Waist.setRotationPoint(0.0F, 8.0F, 1.0F);
        this.Waist.addBox(-5.5F, 0.0F, -3.0F, 11.0F, 7.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 46, 0);
        this.Head.setRotationPoint(4F, -3.0F, 0F);
        this.Head.addBox(-12.0F, -14.0F, -5.0F, 16.0F, 14.0F, 12.0F, 0.0F, 0.0F, 0.5F);
        this.RightArm = new ModelRenderer(this, 58, 26);
        this.RightArm.setRotationPoint(-10.0F, -3.0F, 1.0F);
        this.RightArm.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 17.0F, 6.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.LeftLeg, this.LeftArm, this.Body, this.RightLeg, this.Waist, this.Head, this.RightArm).forEach((modelRenderer) -> { 
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
