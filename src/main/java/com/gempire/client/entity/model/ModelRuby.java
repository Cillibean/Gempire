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
 * ModelRuby - pezzottaite
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelRuby<T extends EntityGem> extends ModelGem<T> {
    public ModelRenderer gemHead;
    public ModelRenderer gemBody;
    public ModelRenderer gemRightArm;
    public ModelRenderer gemLeftArm;
    public ModelRenderer gemLeftLeg;
    public ModelRenderer gemRightLeg;

    public ModelRuby() {
        this.textureWidth = 64;
        this.textureHeight = 42;
        this.gemLeftArm = new ModelRenderer(this, 42, 20);
        this.gemLeftArm.setRotationPoint(6.0F, 10.0F, 0.0F);
        this.gemLeftArm.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.gemLeftLeg = new ModelRenderer(this, 0, 32);
        this.gemLeftLeg.setRotationPoint(-2.0F, 18.0F, 0.0F);
        this.gemLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.gemRightLeg = new ModelRenderer(this, 0, 32);
        this.gemRightLeg.setRotationPoint(2.0F, 18.0F, 0.0F);
        this.gemRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.gemBody = new ModelRenderer(this, 16, 20);
        this.gemBody.setRotationPoint(0.0F, 14.5F, 0.0F);
        this.gemBody.addBox(-4.0F, -4.5F, -2.5F, 8.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.gemRightArm = new ModelRenderer(this, 0, 20);
        this.gemRightArm.setRotationPoint(-6.0F, 10.0F, 0.0F);
        this.gemRightArm.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.gemHead = new ModelRenderer(this, 0, 0);
        this.gemHead.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.gemHead.addBox(-7.0F, -11.0F, -4.5F, 14.0F, 11.0F, 9.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.gemLeftArm, this.gemLeftLeg, this.gemRightLeg, this.gemBody, this.gemRightArm, this.gemHead).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.gemHead, headPitch * 0.9f * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.gemLeftArm, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.gemRightArm, MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.gemLeftLeg, MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.gemRightLeg, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
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
