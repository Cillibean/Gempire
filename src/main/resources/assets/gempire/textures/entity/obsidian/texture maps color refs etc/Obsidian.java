package com.gempire.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Obsidian - Undefined
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class Obsidian<T extends Entity> extends EntityModel<T> {
    public ModelRenderer Body;
    public ModelRenderer Waist;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;
    public ModelRenderer Head;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;

    public Obsidian() {
        this.textureWidth = 104;
        this.textureHeight = 62;
        this.Head = new ModelRenderer(this, 46, 0);
        this.Head.setRotationPoint(-1.0F, -13.0F, -1.4F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 16.0F, 14.0F, 12.0F, 0.0F, 0.0F, 0.5F);
        this.RightArm = new ModelRenderer(this, 58, 25);
        this.RightArm.setRotationPoint(14.0F, 0.0F, 1.5F);
        this.RightArm.setTextureOffset(0, 1).addBox(0.0F, 0.0F, 0.0F, 6.0F, 17.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 82, 26);
        this.LeftLeg.setRotationPoint(0.0F, 7.0F, 1.0F);
        this.LeftLeg.setTextureOffset(0, 1).addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 82, 39);
        this.RightLeg.setRotationPoint(7.0F, 7.0F, 1.0F);
        this.RightLeg.setTextureOffset(0, 1).addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 34, 25);
        this.LeftArm.setRotationPoint(-6.0F, 0.0F, 1.5F);
        this.LeftArm.setTextureOffset(0, 1).addBox(0.0F, 0.0F, 0.0F, 6.0F, 17.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.Waist = new ModelRenderer(this, 0, 20);
        this.Waist.setRotationPoint(1.5F, 11.0F, 1.5F);
        this.Waist.addBox(0.0F, 0.0F, 0.0F, 11.0F, 7.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(-7.0F, -3.0F, -4.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 14.0F, 11.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.RightArm);
        this.Waist.addChild(this.LeftLeg);
        this.Waist.addChild(this.RightLeg);
        this.Body.addChild(this.LeftArm);
        this.Body.addChild(this.Waist);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
