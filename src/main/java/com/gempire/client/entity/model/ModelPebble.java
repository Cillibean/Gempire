package com.gempire.client.entity.model;

import com.gempire.entities.bases.EntityStarterGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Pebble - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelPebble<T extends EntityStarterGem> extends ModelGem<T> {
    public ModelPart body;
    public ModelPart Back_of_headgem;
    public ModelPart head1;
    public ModelPart leg2;
    public ModelPart arm2;
    public ModelPart arm1;
    public ModelPart dress;
    public ModelPart leg1;
    public ModelPart chestgem;
    public ModelPart backgem;
    public ModelPart head2;
    public ModelPart head3;
    public ModelPart eyegem1;
    public ModelPart eyegem2;
    public ModelPart headgem;
    public ModelPart leggem2;
    public ModelPart HandGem2;
    public ModelPart handgem1;
    public ModelPart leggem1;

    public ModelPebble() {
        this.texWidth = 74;
        this.texHeight = 62;
        this.chestgem = new ModelPart(this, 0, 0);
        this.chestgem.setPos(2.0F, 1.0F, -2.0F);
        this.chestgem.texOffs(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.head1 = new ModelPart(this, 0, 0);
        this.head1.setPos(4F, -8.5F, 4.5F);
        this.head1.addBox(-4F, 0.5F, -5.5f, 8.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.headgem = new ModelPart(this, 0, 0);
        //this.headgem.setRotationPoint(2.0F, -1.0F, -1.0F);
        this.headgem.texOffs(0, 46).addBox(-2F, -1F, -7.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eyegem2 = new ModelPart(this, 0, 0);
        //this.eyegem2.setRotationPoint(5.0F, 2.0F, -1.5F);
        this.eyegem2.texOffs(32, 46).addBox(1.0F, 1.0F, -6.5F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelPart(this, 0, 15);
        this.body.setPos(-4.0F, 9.5F, -3.2F);
        this.body.texOffs(12, 15).addBox(0.0F, 0.0F, 0.0F, 8.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leggem2 = new ModelPart(this, 0, 0);
        this.leggem2.setPos(-0.5F, 2.0F, -0.5F);
        this.leggem2.texOffs(16, 54).addBox(-0.01F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eyegem1 = new ModelPart(this, 0, 0);
        //this.eyegem1.setRotationPoint(-1.0F, 2.0F, -1.5F);
        this.eyegem1.texOffs(16, 46).addBox(-5F, 3.0F, -7F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leg2 = new ModelPart(this, 0, 0);
        this.leg2.setPos(5.0F, 9.0F, 1.0F);
        this.leg2.texOffs(12, 29).addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.5F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Back_of_headgem = new ModelPart(this, 0, 0);
        //this.Back_of_headgem.setRotationPoint(-2.0F, 0.0F, 1.5F);
        this.Back_of_headgem.texOffs(48, 51).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leggem1 = new ModelPart(this, 0, 0);
        this.leggem1.setPos(-0.5F, 2.0F, -0.5F);
        this.leggem1.texOffs(0, 54).addBox(0.01F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.arm1 = new ModelPart(this, 0, 0);
        this.arm1.setPos(-3.0F, 0.0F, 1.0F);
        this.arm1.texOffs(0, 15).addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelPart(this, 0, 0);
        //this.head2.setRotationPoint(-2.0F, 0.0F, 0.0F);
        this.head2.texOffs(30, 0).addBox(-6F, 0.0F, -6.0F, 12.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.backgem = new ModelPart(this, 0, 0);
        this.backgem.setPos(2.0F, 1.0F, 3.0F);
        this.backgem.texOffs(32, 54).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.handgem1 = new ModelPart(this, 0, 0);
        this.handgem1.setPos(-0.5F, 5.0F, -0.5F);
        this.handgem1.texOffs(48, 35).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.dress = new ModelPart(this, 0, 0);
        this.dress.setPos(-0.5F, 7.0F, -0.5F);
        this.dress.texOffs(18, 32).addBox(0.0F, 0.0F, 0.0F, 9.0F, 7.5F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.leg1 = new ModelPart(this, 0, 0);
        this.leg1.setPos(0.0F, 9.0F, 1.0F);
        this.leg1.texOffs(0, 29).addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.5F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.HandGem2 = new ModelPart(this, 0, 0);
        this.HandGem2.setPos(-0.5F, 5.0F, -0.5F);
        this.HandGem2.texOffs(48, 43).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.arm2 = new ModelPart(this, 0, 0);
        this.arm2.setPos(8.0F, 0.0F, 1.0F);
        this.arm2.texOffs(38, 15).addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.head3 = new ModelPart(this, 0, 0);
        //this.head3.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head3.texOffs(43, 18).addBox(-4.0F, -1.0F, -5F, 8.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.chestgem);
        this.body.addChild(this.head1);
        this.head1.addChild(this.headgem);
        this.head1.addChild(this.eyegem2);
        this.leg2.addChild(this.leggem2);
        this.head1.addChild(this.eyegem1);
        this.body.addChild(this.leg2);
        this.leg1.addChild(this.leggem1);
        this.body.addChild(this.arm1);
        this.head1.addChild(this.head2);
        this.body.addChild(this.backgem);
        this.arm1.addChild(this.handgem1);
        this.body.addChild(this.dress);
        this.body.addChild(this.leg1);
        this.arm2.addChild(this.HandGem2);
        this.body.addChild(this.arm2);
        this.head1.addChild(this.head3);
        this.head1.addChild(this.Back_of_headgem);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head1.xRot = headPitch * .5f * ((float)Math.PI / 180F);
        this.head1.yRot = netHeadYaw  * ((float)Math.PI / 180F);
        /*this.head2.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head2.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head3.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head3.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);*/
        this.arm1.xRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
        this.arm2.xRot = Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.leg1.xRot = Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
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
