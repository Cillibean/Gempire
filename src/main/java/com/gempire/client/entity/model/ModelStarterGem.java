package com.gempire.client.entity.model;

import com.gempire.entities.bases.EntityStarterGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Pebble - TheBetaZeta
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelStarterGem<T extends EntityStarterGem> extends ModelGem<T> {
    /*public ModelRenderer body;
    public ModelRenderer Back_of_headgem;
    public ModelRenderer head1;
    public ModelRenderer leg2;
    public ModelRenderer arm2;
    public ModelRenderer arm1;
    public ModelRenderer dress;
    public ModelRenderer leg1;
    public ModelRenderer chestgem;
    public ModelRenderer backgem;
    public ModelRenderer head2;
    public ModelRenderer head3;
    public ModelRenderer eyegem1;
    public ModelRenderer eyegem2;
    public ModelRenderer headgem;
    public ModelRenderer leggem2;
    public ModelRenderer HandGem2;
    public ModelRenderer handgem1;
    public ModelRenderer leggem1;

    public ModelStarterGem() {
        this.textureWidth = 74;
        this.textureHeight = 52;

        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(4, 1, 0);
        this.head1.addBox(-4, 0F, -1f, 8.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);

        this.body = new ModelRenderer(this, 12, 0);
        this.body.setRotationPoint(0, 9F, 0);
        this.body.addBox(0F, 0F, 0F, 8.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);

        this.backgem = new ModelRenderer(this, 0, 38);
        this.backgem.setRotationPoint(0, 0F, 0);
        this.backgem.addBox(2.0F, 0.0F, 0.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);

        this.arm1 = new ModelRenderer(this, 0, 15);
        this.arm1.setRotationPoint(0, 8F, 0);
        this.arm1.addBox(-3.0F, 0.0F, 1.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.arm2 = new ModelRenderer(this, 38, 15);
        this.arm2.setRotationPoint(0, 8F, 0);
        this.arm2.addBox(8.0F, 0.0F, 1.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.leg1 = new ModelRenderer(this, 0, 29);
        this.leg1.setRotationPoint(0.0F, 18F, 0);
        this.leg1.addBox(0.0F, 0.0F, 1.0F, 3.0F, 6F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.leg2 = new ModelRenderer(this, 12, 29);
        this.leg2.setRotationPoint(0, 18F, 0);
        this.leg2.addBox(5.0F, 0.0F, 1.0F, 3.0F, 6F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.body.addChild(this.backgem);t
        his.body.addChild(this.dress);
        this.head1.addChild(this.head2);
        this.leg2.addChild(this.leggem2);
        this.head1.addChild(this.headgem);
        this.body.addChild(this.chestgem);
        this.head1.addChild(this.head3);
        this.arm2.addChild(this.HandGem2);
        this.body.addChild(this.head1);
        this.body.addChild(this.leg2);
        this.body.addChild(this.arm1);
        this.head1.addChild(this.eyegem1);
        this.leg1.addChild(this.leggem1);
        this.body.addChild(this.arm2);
        this.body.addChild(this.leg1);
        this.head1.addChild(this.eyegem2);
        this.arm1.addChild(this.handgem1);
        this.eyegem2 = new ModelRenderer(this, 0, 0);
        this.eyegem2.setRotationPoint(5.0F, 2.0F, -1.5F);
        this.eyegem2.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.handgem1 = new ModelRenderer(this, 0, 0);
        this.handgem1.setRotationPoint(-0.5F, 5.0F, -0.5F);
        this.handgem1.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eyegem1 = new ModelRenderer(this, 0, 0);
        this.eyegem1.setRotationPoint(-1.0F, 2.0F, -1.5F);
        this.eyegem1.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leggem1 = new ModelRenderer(this, 0, 0);
        this.leggem1.setRotationPoint(-0.5F, 2.0F, -0.5F);
        this.leggem1.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.HandGem2 = new ModelRenderer(this, 0, 0);
        this.HandGem2.setRotationPoint(-0.5F, 5.0F, -0.5F);
        this.HandGem2.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.head3 = new ModelRenderer(this, 0, 0);
        this.head3.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head3.setTextureOffset(43, 18).addBox(0.0F, 0.0F, 0.0F, 8.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.dress = new ModelRenderer(this, 0, 0);
        this.dress.setRotationPoint(-0.5F, 7.0F, -0.5F);
        this.dress.setTextureOffset(18, 32).addBox(0.0F, 0.0F, 0.0F, 9.0F, 7.5F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.setRotationPoint(-2.0F, 0.0F, 0.0F);
        this.head2.setTextureOffset(30, 0).addBox(0.0F, 0.0F, 0.0F, 12.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.leggem2 = new ModelRenderer(this, 0, 0);
        this.leggem2.setRotationPoint(-0.5F, 2.0F, -0.5F);
        this.leggem2.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.headgem = new ModelRenderer(this, 0, 0);
        this.headgem.setRotationPoint(2.0F, -1.0F, -1.0F);
        this.headgem.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.chestgem = new ModelRenderer(this, 0, 0);
        this.chestgem.setRotationPoint(2.0F, 1.0F, -2.0F);
        this.chestgem.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Back_of_headgem = new ModelRenderer(this, 0, 0);
        this.Back_of_headgem.setRotationPoint(-2.0F, 0.0F, 1.5F);
        this.Back_of_headgem.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.backgem.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head1.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head1.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.arm2.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
        this.arm1.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
    }
    */
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

    public ModelStarterGem() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.LeftEyeGem = new ModelRenderer(this, 0, 22);
        this.RightEyeGem1 = new ModelRenderer(this, 0, 22);
        this.RightEyeGem2 = new ModelRenderer(this, 0, 22);
        this.BackHeadGem = new ModelRenderer(this, 0, 22);
        this.ChestGem = new ModelRenderer(this, 0, 22);
        this.BackGem = new ModelRenderer(this, 0, 22);
        this.LeftHandGem = new ModelRenderer(this, 0, 22);
        this.RightHandGem = new ModelRenderer(this, 0, 22);
        this.LeftLegGem = new ModelRenderer(this, 0, 22);
        this.RightLegGem = new ModelRenderer(this, 0, 22);

        this.HeadMain = new ModelRenderer(this, 0, 0);
        this.HeadMain.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.HeadMain.addBox(-2.0F, 0, -1.5F, 4, 3, 3, 0.0F);

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

        this.HeadMain.addChild(this.LeftEyeGem);
        this.LeftEyeGem.setRotationPoint(0, 0, 0);
        this.LeftEyeGem.addBox(-1.25F, 16.0F, -2, 1, 1, 1, 0.0F);
        this.HeadMain.addChild(this.RightEyeGem1);
        this.RightEyeGem1.setRotationPoint(0,0,0);
        this.RightEyeGem1.addBox(0.25F, 16.0F, -2, 1, 1, 1, 0.0F);
        this.RightEyeGem2.setRotationPoint(0,0,0);
        this.RightEyeGem2.addBox(1.5F, 15.25F, -2, 1, 1, 1, 0.0F);
        this.HeadMain.addChild(this.BackHeadGem);
        this.BackHeadGem.setRotationPoint(0,0,0);
        this.BackHeadGem.addBox(-0.5F, 16.0F, 1f, 1, 1, 1, 0.0F);
        this.Body.addChild(this.ChestGem);
        this.ChestGem.setRotationPoint(0,0,0);
        this.ChestGem.addBox(-0.5F, 19.0F, -1.5F, 1, 1, 1, 0.0F);
        this.Body.addChild(this.BackGem);
        this.BackGem.setRotationPoint(0,0,0);
        this.BackGem.addBox(-0.5F, 19.0F, .5f, 1, 1, 1, 0.0F);
        this.LeftArm.addChild(this.LeftHandGem);
        this.LeftHandGem.setRotationPoint(0,0,0);
        this.LeftHandGem.addBox(-3.7F, 20.2F, -1.5F, 1, 1, 1, 0.0F);
        this.RightArm.addChild(this.RightHandGem);
        this.RightHandGem.setRotationPoint(0,0,0);
        this.RightHandGem.addBox(2.8F, 20.2F, -1.5F, 1, 1, 1, 0.0F);
        this.LeftLeg.addChild(this.LeftLegGem);
        this.LeftLegGem.setRotationPoint(0,0,0);
        this.LeftLegGem.addBox(-2.2F, 23.0F, -1.5F, 1, 1, 1, 0.0F);
        this.RightLeg.addChild(this.RightLegGem);
        this.RightLegGem.setRotationPoint(0,0,0);
        this.RightLegGem.addBox(1.2F, 23.0F, -1.5F, 1, 1, 1, 0.0F);

        //this.HeadMain.addChild(this.HeadSide);
        /*this.HeadSide = new ModelRenderer(this, 14, 0);
        this.HeadSide.setRotationPoint(0.0F, 14.5F, 0.0F);
        this.HeadSide.addBox(0.5F, 0.0F, 0.2F, 3, 3, 3, 0.0F)
        this.Dress = new ModelRenderer(this, 0, 16);
        this.Dress.setRotationPoint(-2.5F, 21.0F, 0.0F);
        this.Dress.addBox(0.0F, 0.0F, 0.0F, 5, 3, 3, 0.0F);;*/
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
        //this.HeadSide.render(matrixStack, buffer, packedLight, packedOverlay);
        this.HeadMain.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.HeadMain, headPitch * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.LeftArm, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount, 0, 0);
        this.setRotateAngle(this.RightArm, MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount, 0, 0);
        this.setRotateAngle(this.LeftLeg, MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount, 0, 0);
        this.setRotateAngle(this.RightLeg, MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount, 0, 0);
    }

    @Override
    public Iterable<ModelRenderer> getParts(){
        return ImmutableList.of(this.HeadMain, this.Body, this.LeftLeg, this.RightLeg, this.LeftArm, this.RightArm);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
