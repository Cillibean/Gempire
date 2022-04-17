package com.gempire.client.entity.model;

import com.gempire.entities.TestEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.entity.Entity;

/**
 * ModelStarterGem - Cilli
 * Created using Tabula 7.1.0
 */
public class ModelTest<T extends TestEntity> extends EntityModel<T> {
    public ModelPart HeadMain;
    public ModelPart HeadSide;
    public ModelPart Body;
    public ModelPart LeftArm;
    public ModelPart RightArm;
    public ModelPart LeftLeg;
    public ModelPart RightLeg;
    public ModelPart Dress;
    public ModelPart LeftEyeGem;
    public ModelPart RightEyeGem1;
    public ModelPart RightEyeGem2;
    public ModelPart BackHeadGem;
    public ModelPart LeftHandGem;
    public ModelPart RightHandGem;
    public ModelPart ChestGem;
    public ModelPart BackGem;
    public ModelPart LeftLegGem;
    public ModelPart RightLegGem;

    public ModelTest() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.LeftEyeGem = new ModelPart(this, 0, 22);
        this.LeftEyeGem.setPos(-1.25F, 16.0F, -0.5F);
        this.LeftEyeGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightEyeGem1 = new ModelPart(this, 4, 22);
        this.RightEyeGem1.setPos(0.25F, 16.0F, -0.5F);
        this.RightEyeGem1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightEyeGem2 = new ModelPart(this, 8, 22);
        this.RightEyeGem2.setPos(1.5F, 15.25F, -0.3F);
        this.RightEyeGem2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.BackHeadGem = new ModelPart(this, 12, 22);
        this.BackHeadGem.setPos(-0.5F, 16.0F, 2.5F);
        this.BackHeadGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.ChestGem = new ModelPart(this, 16, 22);
        this.ChestGem.setPos(-0.5F, 19.0F, 0.0F);
        this.ChestGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.BackGem = new ModelPart(this, 20, 22);
        this.BackGem.setPos(-0.5F, 19.0F, 2.0F);
        this.BackGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftHandGem = new ModelPart(this, 24, 22);
        this.LeftHandGem.setPos(-3.7F, 20.2F, 0.3F);
        this.LeftHandGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightHandGem = new ModelPart(this, 28, 22);
        this.RightHandGem.setPos(2.8F, 20.2F, 0.3F);
        this.RightHandGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftLegGem = new ModelPart(this, 0, 24);
        this.LeftLegGem.setPos(-2.2F, 23.0F, 0.3F);
        this.LeftLegGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.RightLegGem = new ModelPart(this, 4, 24);
        this.RightLegGem.setPos(1.2F, 23.0F, 0.3F);
        this.RightLegGem.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);

        this.HeadMain = new ModelPart(this, 0, 0);
        this.HeadMain.setPos(-2.0F, 15.0F, 0.0F);
        this.HeadMain.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3, 0.0F);

        this.HeadSide = new ModelPart(this, 14, 0);
        this.HeadSide.setPos(0.5F, 14.5F, 0.2F);
        this.HeadSide.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);

        this.Body = new ModelPart(this, 0, 6);
        this.Body.setPos(-1.5F, 18.0F, 0.5F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2, 0.0F);

        this.LeftArm = new ModelPart(this, 0, 11);
        this.LeftArm.setPos(-3.5F, 18.0F, 0.5F);
        this.LeftArm.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);

        this.RightArm = new ModelPart(this, 0, 11);
        this.RightArm.setPos(1.5F, 18.0F, 0.5F);
        this.RightArm.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);

        this.Dress = new ModelPart(this, 0, 16);
        this.Dress.setPos(-2.5F, 21.0F, 0.0F);
        this.Dress.addBox(0.0F, 0.0F, 0.0F, 5, 3, 3, 0.0F);

        this.LeftLeg = new ModelPart(this, 8, 11);
        this.LeftLeg.setPos(-2.0F, 21.0F, 0.5F);
        this.LeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);

        this.RightLeg = new ModelPart(this, 8, 11);
        this.RightLeg.setPos(0.0F, 21.0F, 0.5F);
        this.RightLeg.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        this.HeadMain.render(matrixStack, buffer, packedLight, packedOverlay);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay);
        this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        this.LeftEyeGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.RightEyeGem1.render(matrixStack, buffer, packedLight, packedOverlay);
        this.RightEyeGem2.render(matrixStack, buffer, packedLight, packedOverlay);
        this.BackHeadGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.LeftHandGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.RightHandGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.ChestGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.BackGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.LeftLegGem.render(matrixStack, buffer, packedLight, packedOverlay);
        this.RightLegGem.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
