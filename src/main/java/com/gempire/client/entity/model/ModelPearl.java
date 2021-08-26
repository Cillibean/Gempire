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
 * Pearl - Segapop
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelPearl<T extends EntityGem> extends ModelGem<T> {
    public ModelRenderer Hair;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer Nose;
    public ModelRenderer Shawl;
    public ModelRenderer Skirt;
    public ModelRenderer LShoulder;
    public ModelRenderer RShoulder;
    public ModelRenderer BunL;
    public ModelRenderer BunR;
    public ModelRenderer Ponytail;

    public ModelPearl() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.BunR = new ModelRenderer(this, 8, 33);
        this.BunR.mirror = true;
        this.BunR.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.BunR.addBox(4.5F, -8.5F, -2.5F, 2.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Nose = new ModelRenderer(this, 0, 5);
        this.Nose.setRotationPoint(0.0F, 0, 0.0F);
        this.Nose.addBox(-0.5F, -3.5F, -6.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RShoulder = new ModelRenderer(this, 16, 56);
        this.RShoulder.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.RShoulder.addBox(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 8, 17);
        this.Body.setRotationPoint(1.0F, -1.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 6.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.LegL = new ModelRenderer(this, 0, 32);
        this.LegL.setRotationPoint(-1.5F, 11.0F, 1.0F);
        this.LegL.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.LShoulder = new ModelRenderer(this, 0, 56);
        this.LShoulder.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.LShoulder.addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Shawl = new ModelRenderer(this, 32, 42);
        this.Shawl.setRotationPoint(-1.5F, -1.0F, -0.5F);
        this.Shawl.addBox(-4.0F, 0.0F, -2.0F, 11.0F, 13.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Ponytail = new ModelRenderer(this, 8, 43);
        this.Ponytail.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Ponytail.addBox(-2.0F, -10.5F, 4.0F, 4.0F, 9.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.ArmR = new ModelRenderer(this, 28, 17);
        this.ArmR.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.ArmR.addBox(0.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Head.addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Skirt = new ModelRenderer(this, 36, 17);
        this.Skirt.setRotationPoint(0.0F, 7.0F, -1.0F);
        this.Skirt.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 17.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.BunL = new ModelRenderer(this, 8, 33);
        this.BunL.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.BunL.addBox(-6.5F, -8.5F, -2.5F, 2.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Hair = new ModelRenderer(this, 32, 0);
        this.Hair.setRotationPoint(0.0F, 0, 0.0F);
        this.Hair.addBox(-4.0F, -9.0F, -4.2F, 8.0F, 9.0F, 8.0F, 0.5F, 0.5F, 0.5F);
        this.LegR = new ModelRenderer(this, 28, 32);
        this.LegR.setRotationPoint(1.5F, 11.0F, 1.0F);
        this.LegR.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.ArmL = new ModelRenderer(this, 0, 17);
        this.ArmL.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.ArmL.addBox(-2.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Head.addChild(this.Hair);
        this.Head.addChild(this.Nose);
        this.Hair.addChild(this.BunR);
        this.Hair.addChild(this.BunL);
        this.Hair.addChild(this.Ponytail);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.RShoulder, this.Body, this.LegL, this.LShoulder, this.Shawl, this.ArmR, this.Head, this.Skirt, this.LegR, this.ArmL).forEach((modelRenderer) -> {
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
