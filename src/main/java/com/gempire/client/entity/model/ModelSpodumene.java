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
 * Spodumene - Undefined
 * Created using Tabula 8.0.0
 */

public class ModelSpodumene<T extends EntityGem> extends ModelGem<T> {
    public ModelPart Hair;
    public ModelPart Right_Arm;
    public ModelPart Left_Arm;
    public ModelPart Left_Leg;
    public ModelPart Right_Leg;
    public ModelPart Inner_Head;
    public ModelPart Chest_Gem;
    public ModelPart innerbody;
    public ModelPart Back_Of_Head_Gem;
    public ModelPart Back_Gem;
    public ModelPart Right_Ear_Gem;
    public ModelPart Left_Ear_Gem;
    public ModelPart Naval_Gem;
    public ModelPart Lower_Back_Gem;
    public ModelPart Head_Gem;
    public ModelPart body;
    public ModelPart head;
    public ModelPart Left_Boot;
    public ModelPart Right_Boot;

    public ModelSpodumene() {
        this.texWidth = 64;
        this.texHeight = 80;
        this.head = new ModelPart(this, 0, 0);
        this.head.setPos(0.0F, -4.0F, 0.0F);
        this.head.texOffs(0, 2).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Lower_Back_Gem = new ModelPart(this, 0, 0);
        //this.Lower_Back_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Lower_Back_Gem.texOffs(41, 58).addBox(-3.3F, 7.5F, -1.7F, 6.0F, 6.0F, 4.0F, -1.5F, -1.5F, -1.0F);
        this.setRotateAngle(Lower_Back_Gem, 0.0F, 0.0F, -0.03490658503988659F);
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(0.0F, -4.0F, 0.0F);
        this.body.texOffs(8, 18).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 16.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Right_Leg = new ModelPart(this, 0, 0);
        this.Right_Leg.setPos(-1.7F, 10.0F, 0.0F);
        this.Right_Leg.texOffs(34, 20).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Inner_Head = new ModelPart(this, 0, 0);
        //this.Inner_Head.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Inner_Head.texOffs(40, 10).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.1F, 0.1F, 0.1F);
        this.Left_Leg = new ModelPart(this, 0, 0);
        this.Left_Leg.setPos(1.7F, 10.0F, 0.0F);
        this.Left_Leg.texOffs(54, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Chest_Gem = new ModelPart(this, 0, 0);
        this.Chest_Gem.mirror = true;
        //this.Chest_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Chest_Gem.texOffs(20, 47).addBox(-4.6F, 1.8F, -2.4F, 6.0F, 6.0F, 4.0F, -1.5F, -1.5F, -1.0F);
        this.setRotateAngle(Chest_Gem, 0.0F, 0.0F, -0.3141592653589793F);
        this.Back_Gem = new ModelPart(this, 0, 0);
        //this.Back_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Back_Gem.texOffs(0, 49).addBox(-2.4F, -0.3F, -1.7F, 6.0F, 6.0F, 4.0F, -1.5F, -1.5F, -1.0F);
        this.setRotateAngle(Back_Gem, 0.0F, 0.0F, 0.1832595714594046F);
        this.Right_Arm = new ModelPart(this, 0, 0);
        this.Right_Arm.setPos(-3.0F, -3.0F, 0.0F);
        this.Right_Arm.texOffs(0, 18).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 17.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.innerbody = new ModelPart(this, 0, 0);
        //this.innerbody.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.innerbody.texOffs(42, 22).addBox(-2.0F, 0.7F, -1.0F, 4.0F, 12.0F, 2.0F, 0.1F, 0.3F, 0.0F);
        this.Head_Gem = new ModelPart(this, 0, 0);
        //this.Head_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Head_Gem.texOffs(20, 37).addBox(-3.0F, -9.2F, -4.4F, 6.0F, 6.0F, 4.0F, -1.5F, -1.5F, -0.8F);
        this.Left_Boot = new ModelPart(this, 0, 0);
        this.Left_Boot.mirror = true;
        //this.Left_Boot.setRotationPoint(-1.8F, 10.0F, 0.0F);
        this.Left_Boot.texOffs(17, 57).addBox(-1.4F, 6.6F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, 0.5F, 0.0F);
        this.Right_Boot = new ModelPart(this, 0, 0);
        //this.Right_Boot.setRotationPoint(1.7F, 10.0F, 0.0F);
        this.Right_Boot.texOffs(29, 57).addBox(-1.5F, 6.6F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, 0.5F, 0.0F);
        this.Left_Ear_Gem = new ModelPart(this, 0, 0);
        //this.Left_Ear_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Left_Ear_Gem.texOffs(0, 37).addBox(-4.2F, -6.9F, -2.5F, 4.0F, 6.0F, 6.0F, -0.5F, -1.5F, -1.5F);
        this.setRotateAngle(Left_Ear_Gem, 0.15707963267948966F, 0.0F, 0.0F);
        this.Naval_Gem = new ModelPart(this, 0, 0);
        //this.Naval_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Naval_Gem.texOffs(24, 0).addBox(-0.7F, 7.8F, -2.4F, 6.0F, 6.0F, 4.0F, -1.5F, -1.5F, -1.0F);
        this.setRotateAngle(Naval_Gem, 0.0F, 0.0F, 0.20943951023931953F);
        this.Hair = new ModelPart(this, 0, 0);
        //this.Hair.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Hair.texOffs(44, 0).addBox(-5.0F, -8.0F, 8.5F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Hair, 0.7853981633974483F, 0.0F, 0.0F);
        this.Back_Of_Head_Gem = new ModelPart(this, 0, 0);
        this.Back_Of_Head_Gem.mirror = true;
        //this.Back_Of_Head_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Back_Of_Head_Gem.texOffs(41, 48).addBox(-3.0F, -7.0F, 0.8F, 6.0F, 6.0F, 4.0F, -1.5F, -1.5F, -1.0F);
        this.setRotateAngle(Back_Of_Head_Gem, 0.0F, 0.0F, 0.06981317007977318F);
        this.Left_Arm = new ModelPart(this, 0, 0);
        this.Left_Arm.setPos(3.0F, -3.0F, 0.0F);
        this.Left_Arm.texOffs(26, 18).addBox(0.0F, -1.0F, -1.0F, 2.0F, 17.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Right_Ear_Gem = new ModelPart(this, 0, 0);
        //this.Right_Ear_Gem.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Right_Ear_Gem.texOffs(40, 36).addBox(0.2F, -7.0F, -4.1F, 4.0F, 6.0F, 6.0F, -0.5F, -1.5F, -1.5F);
        this.setRotateAngle(Right_Ear_Gem, -0.15707963267948966F, 0.0F, 0.0F);
        this.head.addChild(this.Inner_Head);
        this.head.addChild(this.Hair);
        this.head.addChild(this.Back_Of_Head_Gem);
        this.head.addChild(this.Head_Gem);
        this.head.addChild(this.Left_Ear_Gem);
        this.head.addChild(this.Right_Ear_Gem);
        this.body.addChild(this.innerbody);
        this.body.addChild(this.Back_Gem);
        this.body.addChild(this.Lower_Back_Gem);
        this.body.addChild(this.Chest_Gem);
        this.body.addChild(this.Naval_Gem);
        this.Left_Leg.addChild(this.Left_Boot);
        this.Right_Leg.addChild(this.Right_Boot);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.head, this.Left_Leg, this.Right_Arm, this.Right_Leg, this.body, this.Left_Arm).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.Left_Arm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.Right_Arm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.Left_Leg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.Right_Leg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
