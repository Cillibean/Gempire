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
 * Sapphire - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelSapphire<T extends EntityGem> extends ModelGem<T> {
    public ModelPart body;
    public ModelPart leg2;
    public ModelPart leg1;
    public ModelPart arm2;
    public ModelPart arm1;
    public ModelPart head;
    public ModelPart hair;
    public ModelPart puff2;
    public ModelPart puff1;
    public ModelPart dress;

    public ModelSapphire() {
        this.texWidth = 64;
        this.texHeight = 50;
        this.leg2 = new ModelPart(this, 0, 0);
        this.leg2.setPos(1.0F, 18.0F, -1.5F);
        this.leg2.texOffs(48, 24).
                addBox(0, 0.0F, 0, 3.0F, 6.0F, 3.0F, -0.2F, 0.0F, -0.2F);
        this.puff2 = new ModelPart(this, 0, 0);
        this.puff2.setPos(4.0F, 9.0F, -1.5F);
        this.puff2.texOffs(0, 38).
                addBox(0, 0.0F, 0, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.puff1 = new ModelPart(this, 0, 0);
        this.puff1.setPos(-6.5F, 9.0F, -1.4F);
        this.puff1.texOffs(0, 32).
                addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.leg1 = new ModelPart(this, 0, 0);
        this.leg1.setPos(-3.5F, 18.0F, -1.5F);
        this.leg1.texOffs(32, 24).
                addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, -0.2F, 0.0F, -0.2F);
        this.hair = new ModelPart(this, 0, 0);
        this.hair.
                addBox(-3.8f, 0, -4f, 8.0F, 8.0F, 8.0F, 0.5F, 0.5F, 0.5F);
        this.arm2 = new ModelPart(this, 0, 0);
        //this.arm2.setRotationPoint(4.0F, 9.2F, -1.0F);
        this.arm2.texOffs(50, 14).
                addBox(.5f, .5F, .5F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelPart(this, 0, 0);
        this.head.setPos(0, 1.0F, 0);
        this.head.texOffs(0, 16).
                addBox(-3.8F, 0.0F, -4F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(-3.8F, 9.0F, -2.5F);
        this.body.texOffs(34, 0).
                addBox(0.0F, 0.0F, 0.0F, 8.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.arm1 = new ModelPart(this, 0, 0);
        //this.arm1.setRotationPoint(-5.5F, 9.0F, -1.0F);
        this.arm1.texOffs(34, 14).
                addBox(.5f, .5F, .5F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.dress = new ModelPart(this, 0, 0);
        this.dress.setPos(0, 0, 0);
        this.dress.texOffs(13, 33).
                addBox(-4.75f, 14.5f, -4f, 10.0F, 9.0F, 8.0F, 0F, 0.0F, 0);
        this.head.addChild(this.hair);
        this.puff2.addChild(this.arm2);
        this.puff1.addChild(this.arm1);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        //this.hair.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.puff1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        //this.arm1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        //this.arm2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.puff2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.dress.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.9f * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.puff2, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.puff1, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.leg2, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.leg1, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
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
