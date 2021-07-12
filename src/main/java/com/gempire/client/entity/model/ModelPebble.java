package com.gempire.client.entity.model;

import com.gempire.entities.bases.EntityStarterGem;
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
 * Pebble - TheBetaZeta
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelPebble<T extends EntityStarterGem> extends ModelGem<T> {
    public ModelRenderer body;
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

    public ModelPebble() {
        this.textureWidth = 74;
        this.textureHeight = 62;
        this.chestgem = new ModelRenderer(this, 0, 0);
        this.chestgem.setRotationPoint(2.0F, 1.0F, -2.0F);
        this.chestgem.setTextureOffset(0, 38).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(4F, -8.5F, 4.5F);
        this.head1.addBox(-4F, 0.5F, -5.5f, 8.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.headgem = new ModelRenderer(this, 0, 0);
        //this.headgem.setRotationPoint(2.0F, -1.0F, -1.0F);
        this.headgem.setTextureOffset(0, 46).addBox(-2F, -1F, -7.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eyegem2 = new ModelRenderer(this, 0, 0);
        //this.eyegem2.setRotationPoint(5.0F, 2.0F, -1.5F);
        this.eyegem2.setTextureOffset(32, 46).addBox(1.0F, 1.0F, -6.5F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 15);
        this.body.setRotationPoint(-4.0F, 9.5F, -3.2F);
        this.body.setTextureOffset(12, 15).addBox(0.0F, 0.0F, 0.0F, 8.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leggem2 = new ModelRenderer(this, 0, 0);
        this.leggem2.setRotationPoint(-0.5F, 2.0F, -0.5F);
        this.leggem2.setTextureOffset(16, 54).addBox(-0.01F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eyegem1 = new ModelRenderer(this, 0, 0);
        //this.eyegem1.setRotationPoint(-1.0F, 2.0F, -1.5F);
        this.eyegem1.setTextureOffset(16, 46).addBox(-5F, 3.0F, -7F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.setRotationPoint(5.0F, 9.0F, 1.0F);
        this.leg2.setTextureOffset(12, 29).addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.5F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Back_of_headgem = new ModelRenderer(this, 0, 0);
        //this.Back_of_headgem.setRotationPoint(-2.0F, 0.0F, 1.5F);
        this.Back_of_headgem.setTextureOffset(48, 51).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leggem1 = new ModelRenderer(this, 0, 0);
        this.leggem1.setRotationPoint(-0.5F, 2.0F, -0.5F);
        this.leggem1.setTextureOffset(0, 54).addBox(0.01F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.arm1 = new ModelRenderer(this, 0, 0);
        this.arm1.setRotationPoint(-3.0F, 0.0F, 1.0F);
        this.arm1.setTextureOffset(0, 15).addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        //this.head2.setRotationPoint(-2.0F, 0.0F, 0.0F);
        this.head2.setTextureOffset(30, 0).addBox(-6F, 0.0F, -6.0F, 12.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.backgem = new ModelRenderer(this, 0, 0);
        this.backgem.setRotationPoint(2.0F, 1.0F, 3.0F);
        this.backgem.setTextureOffset(32, 54).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.handgem1 = new ModelRenderer(this, 0, 0);
        this.handgem1.setRotationPoint(-0.5F, 5.0F, -0.5F);
        this.handgem1.setTextureOffset(48, 35).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.dress = new ModelRenderer(this, 0, 0);
        this.dress.setRotationPoint(-0.5F, 7.0F, -0.5F);
        this.dress.setTextureOffset(18, 32).addBox(0.0F, 0.0F, 0.0F, 9.0F, 7.5F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.setRotationPoint(0.0F, 9.0F, 1.0F);
        this.leg1.setTextureOffset(0, 29).addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.5F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.HandGem2 = new ModelRenderer(this, 0, 0);
        this.HandGem2.setRotationPoint(-0.5F, 5.0F, -0.5F);
        this.HandGem2.setTextureOffset(48, 43).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.arm2 = new ModelRenderer(this, 0, 0);
        this.arm2.setRotationPoint(8.0F, 0.0F, 1.0F);
        this.arm2.setTextureOffset(38, 15).addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.head3 = new ModelRenderer(this, 0, 0);
        //this.head3.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head3.setTextureOffset(43, 18).addBox(-4.0F, -1.0F, -5F, 8.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
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
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head1.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head1.rotateAngleY = netHeadYaw  * ((float)Math.PI / 180F);
        /*this.head2.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head2.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head3.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head3.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);*/
        this.arm1.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
        this.arm2.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
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
