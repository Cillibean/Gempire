package com.gempire.client.entity.model;

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
 * zircon - Undefined
 * Created using Tabula 8.0.0
 */

public class ModelZircon<T extends EntityGem> extends ModelGem<T> {
    public ModelPart Body;
    public ModelPart Hat1;
    public ModelPart Chest;
    public ModelPart RightArm;
    public ModelPart Head;
    public ModelPart RLegPuff;
    public ModelPart RightLeg;
    public ModelPart LLegPuff;
    public ModelPart LeftArm;
    public ModelPart Nose;
    public ModelPart EyeGlass;
    public ModelPart Hat2;
    public ModelPart RArmPuff;
    public ModelPart LArmPuff;
    public ModelPart LeftLeg;
    public ModelPart Shoulders;

    public ModelZircon() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.LeftArm = new ModelPart(this, 0, 0);
        this.LeftArm.setPos(-4.0F, 2.8F, 0.0F);
        this.LeftArm.texOffs(12, 28).addBox(-2.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RArmPuff = new ModelPart(this, 0, 0);
        this.RArmPuff.mirror = true;
        //this.RArmPuff.setRotationPoint(4.0F, 3.0F, 0.0F);
        this.RArmPuff.texOffs(0, 22).addBox(-0.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.RightArm = new ModelPart(this, 0, 0);
        this.RightArm.mirror = true;
        this.RightArm.setPos(4.0F, 2.8F, 0.0F);
        this.RightArm.texOffs(56, 10).addBox(0.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.EyeGlass = new ModelPart(this, 0, 17);
        //this.EyeGlass.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.EyeGlass.addBox(0.4F, -6.2F, -4.0F, 3.0F, 4.0F, 0.0F, -0.1F, -0.1F, 0.0F);
        this.Body = new ModelPart(this, 32, 12);
        this.Body.setPos(0.0F, 1.8F, 0.0F);
        this.Body.addBox(-3.0F, 0.1F, -1.9F, 6.0F, 10.0F, 4.0F, -0.1F, 0.1F, -0.1F);
        this.Nose = new ModelPart(this, 0, 0);
        //this.Nose.setRotationPoint(0.3F, 1.7F, 0.0F);
        this.Nose.addBox(-0.8F, -3.2F, -5.7F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RLegPuff = new ModelPart(this, 0, 0);
        this.RLegPuff.mirror = true;
        //this.RLegPuff.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.RLegPuff.texOffs(0, 51).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelPart(this, 0, 0);
        this.Head.setPos(0.0F, 1.8F, 0.0F);
        this.Head.addBox(-4.0F, -7.8F, -4.0F, 8.0F, 8.0F, 8.0F, -0.3F, -0.2F, -0.3F);
        this.RightLeg = new ModelPart(this, 0, 0);
        this.RightLeg.mirror = true;
        this.RightLeg.setPos(1.9F, 12.0F, 0.0F);
        this.RightLeg.texOffs(55, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.LArmPuff = new ModelPart(this, 0, 0);
        //this.LArmPuff.setRotationPoint(-4.0F, 3.0F, 0.0F);
        this.LArmPuff.texOffs(0, 28).addBox(-2.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.LeftLeg = new ModelPart(this, 0, 0);
        this.LeftLeg.setPos(-1.9F, 12.0F, 0.0F);
        this.LeftLeg.texOffs(20, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.LLegPuff = new ModelPart(this, 0, 0);
        //this.LLegPuff.setRotationPoint(-2.0F, 11.9F, 0.0F);
        this.LLegPuff.texOffs(0, 34).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Hat1 = new ModelPart(this, 0, 0);
        //this.Hat1.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Hat1.texOffs(28, 28).addBox(-4.0F, -9.5F, -6.0F, 8.0F, 4.0F, 10.0F, -0.3F, -0.2F, -0.3F);
        this.Chest = new ModelPart(this, 0, 0);
        this.Chest.setPos(0.0F, 1.8F, 0.0F);
        this.Chest.texOffs(32, 0).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Shoulders = new ModelPart(this, 0, 0);
        this.Shoulders.setPos(0.0F, 1.8F, 0.0F);
        this.Shoulders.texOffs(0, 43).addBox(-7.0F, 0.0F, -2.5F, 14.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Hat2 = new ModelPart(this, 0, 0);
        //this.Hat2.setRotationPoint(0.0F, 1.8F, 0.0F);
        this.Hat2.texOffs(32, 45).addBox(-4.0F, -12.6F, -4.0F, 8.0F, 5.0F, 8.0F, -0.3F, 0.0F, -0.3F);
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
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.LeftArm, this.RightArm, this.Body, this.Head, this.RightLeg, this.LeftLeg, this.Chest, this.Shoulders).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.LeftArm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.RightArm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.LeftLeg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.RightLeg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
