package com.gempire.client.entity.model;

import com.gempire.entities.TestEntity;
import com.gempire.entities.gems.EntityPebble;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * ModelStarterGem - Cilli
 * Created using Tabula 7.1.0
 */
public class ModelPebble<T extends EntityPebble> extends ModelGem<T> {
    public ModelRenderer HeadMain;
    public ModelRenderer HeadSide;
    public ModelRenderer Body;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;
    public ModelRenderer Dress;
    public ModelRenderer LeftEyeGem;
    public ModelRenderer RightEyeGem1;
    public ModelRenderer RightEyeGem2;
    public ModelRenderer BackHeadGem;
    public ModelRenderer LeftHandGem;
    public ModelRenderer RightHandGem;
    public ModelRenderer ChestGem;
    public ModelRenderer BackGem;
    public ModelRenderer LeftLegGem;
    public ModelRenderer RightLegGem;

    public ModelPebble() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        /*this.LeftEyeGem = new ModelRenderer(this, 0, 22);
        this.LeftEyeGem.setRotationPoint(-1.25F, 16.0F, -0.5F);
        this.LeftEyeGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightEyeGem1 = new ModelRenderer(this, 4, 22);
        this.RightEyeGem1.setRotationPoint(0.25F, 16.0F, -0.5F);
        this.RightEyeGem1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightEyeGem2 = new ModelRenderer(this, 8, 22);
        this.RightEyeGem2.setRotationPoint(1.5F, 15.25F, -0.3F);
        this.RightEyeGem2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.BackHeadGem = new ModelRenderer(this, 12, 22);
        this.BackHeadGem.setRotationPoint(-0.5F, 16.0F, 2.5F);
        this.BackHeadGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.ChestGem = new ModelRenderer(this, 16, 22);
        this.ChestGem.setRotationPoint(-0.5F, 19.0F, 0.0F);
        this.ChestGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.BackGem = new ModelRenderer(this, 20, 22);
        this.BackGem.setRotationPoint(-0.5F, 19.0F, 2.0F);
        this.BackGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftHandGem = new ModelRenderer(this, 24, 22);
        this.LeftHandGem.setRotationPoint(-3.7F, 20.2F, 0.3F);
        this.LeftHandGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightHandGem = new ModelRenderer(this, 28, 22);
        this.RightHandGem.setRotationPoint(2.8F, 20.2F, 0.3F);
        this.RightHandGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftLegGem = new ModelRenderer(this, 0, 24);
        this.LeftLegGem.setRotationPoint(-2.2F, 23.0F, 0.3F);
        this.LeftLegGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightLegGem = new ModelRenderer(this, 4, 24);
        this.RightLegGem.setRotationPoint(1.2F, 23.0F, 0.3F);
        this.RightLegGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Dress = new ModelRenderer(this, 0, 16);
        this.Dress.setRotationPoint(-2.5F, 21.0F, 0.0F);
        this.Dress.addBox(0.0F, 0.0F, 0.0F, 5, 3, 3, 0.0F);*/

        this.HeadMain = new ModelRenderer(this, 0, 0);
        this.HeadMain.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.HeadMain.addBox(-2.0F, 0, -1.5F, 4, 3, 3, 0.0F);

        /*this.HeadSide = new ModelRenderer(this, 14, 0);
        this.HeadSide.setRotationPoint(0.0F, 14.5F, 0.0F);
        this.HeadSide.addBox(0.5F, 0.0F, 0.2F, 3, 3, 3, 0.0F);*/

        this.Body = new ModelRenderer(this, 0, 6);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-2.0F, 0, -1.0F, 4, 3, 2, 0.0F);

        this.LeftArm = new ModelRenderer(this, 0, 11);
        this.LeftArm.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.LeftArm.addBox(-4F, 0, -1.0F, 2, 3, 2, 0.0F);

        this.RightArm = new ModelRenderer(this, 0, 11);
        this.RightArm.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.RightArm.addBox(2F, 0, -1.0F, 2, 3, 2, 0.0F);

        this.LeftLeg = new ModelRenderer(this, 8, 11);
        this.LeftLeg.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0, -1F, 2, 3, 2, 0.0F);

        this.RightLeg = new ModelRenderer(this, 8, 11);
        this.RightLeg.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.RightLeg.addBox(0.0F, 0, -1.0F, 2, 3, 2, 0.0F);

        //this.HeadMain.addChild(this.HeadSide);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        //this.LeftEyeGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.RightEyeGem1.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.RightEyeGem2.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.BackHeadGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.LeftHandGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.RightHandGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.ChestGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.BackGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.LeftLegGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //this.RightLegGem.render(matrixStack, buffer, packedLight, packedOverlay);
        //int skin = (EntityPebble)T.getSkinColor();
        this.HeadMain.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        //this.HeadSide.render(matrixStack, buffer, packedLight, packedOverlay);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.HeadMain.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.HeadMain.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
        this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
    }
}
