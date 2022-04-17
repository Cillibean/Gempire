package com.gempire.client.entity.model;

import com.gempire.entities.bases.AbstractQuartz;
import com.gempire.entities.bases.EntityGem;
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
 * Quartz - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelQuartz<T extends AbstractQuartz> extends ModelGem<T> {
    public ModelPart body;
    public ModelPart head;
    public ModelPart arm1;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart arm2;
    public ModelPart hair;
    public ModelPart mohawk;
    public ModelPart messyponytail;
    public ModelPart cherryponytail;
    public ModelPart agatehair;
    public ModelPart shoulderpuff1;
    public ModelPart shoulderpuff2;

    public ModelQuartz() {
        this.texWidth = 124;
        this.texHeight = 64;
        this.hair = new ModelPart(this, 0, 0);
        this.hair.setPos(0F, 0.0F, 0F);
        this.hair.addBox(-3.0F, 0.0F, -5.0F, 8.0F, 8.0F, 8.0F, 0.6F, 0.6F, 0.6F);
        this.shoulderpuff1 = new ModelPart(this, 0, 0);
        this.shoulderpuff1.setPos(-1.0F, -0.5F, -0.5F);
        this.shoulderpuff1.texOffs(0, 17).addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shoulderpuff1, 0.0F, 0.0F, 0.08726646259971647F);
        this.agatehair = new ModelPart(this, 0, 0);
        this.agatehair.setPos(0.0F, 2.0F, 0.0F);
        this.agatehair.texOffs(0, 50).addBox(-6F, 0.0F, -3F, 14.0F, 4.0F, 4.0F, 0.5F, -0.1F, -0.1F);
        this.leg1 = new ModelPart(this, 0, 0);
        this.leg1.setPos(0.3F, 13.9F, 1.0F);
        this.leg1.texOffs(16, 36).addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.5F, 4.0F, 0.1F, 0.0F, 0.1F);
        this.messyponytail = new ModelPart(this, 0, 0);
        this.messyponytail.setPos(0.0F, -1.5F, 7.0F);
        this.messyponytail.texOffs(33, 36).addBox(-3.0F, -1.5F, -5.0F, 8.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(messyponytail, 0.2617993877991494F, 0.0F, 0.0F);
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(-4.8F, 0.6F, -3.1F);
        this.body.texOffs(38, 16).addBox(0.0F, 0.0F, 0.0F, 10.0F, 14.0F, 6.0F, 0.1F, -0.1F, 0.0F);
        this.leg2 = new ModelPart(this, 0, 0);
        this.leg2.setPos(5.7F, 13.9F, 1.0F);
        this.leg2.texOffs(64, 36).addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.5F, 4.0F, 0.1F, 0.0F, 0.1F);
        this.mohawk = new ModelPart(this, 0, 0);
        this.mohawk.setPos(0, -2.0F, 0F);
        this.mohawk.texOffs(70, 40).addBox(-2F, 0.0F, -7.0F, 6.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.arm1 = new ModelPart(this, 0, 0);
        this.arm1.setPos(-4.3F, 0.1F, 1.0F);
        this.arm1.texOffs(22, 18).addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.2F, 0.0F, 0.2F);
        this.cherryponytail = new ModelPart(this, 0, 0);
        this.cherryponytail.setPos(-2, -1.5F, 3.5F);
        this.cherryponytail.texOffs(64, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 9.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.shoulderpuff2 = new ModelPart(this, 0, 0);
        this.shoulderpuff2.setPos(-1.0F, 0.0F, -0.5F);
        this.shoulderpuff2.texOffs(86, 17).addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shoulderpuff2, 0.0F, 0.0F, -0.08726646259971647F);
        this.arm2 = new ModelPart(this, 0, 0);
        this.arm2.setPos(10.3F, 0.1F, 1.0F);
        this.arm2.texOffs(70, 18).addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.2F, 0.0F, 0.2F);
        this.head = new ModelPart(this, 0, 0);
        this.head.setPos(4, -8.1F, 4);
        this.head.texOffs(32, 0).addBox(-3, 0.2f, -5, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.hair);
        this.arm1.addChild(this.shoulderpuff1);
        this.hair.addChild(this.agatehair);
        this.body.addChild(this.leg1);
        this.hair.addChild(this.messyponytail);
        this.body.addChild(this.leg2);
        this.head.addChild(this.mohawk);
        this.body.addChild(this.arm1);
        this.hair.addChild(this.cherryponytail);
        this.arm2.addChild(this.shoulderpuff2);
        this.body.addChild(this.arm2);
        this.body.addChild(this.head);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.arm1, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.arm2, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.leg1, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.leg2, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
