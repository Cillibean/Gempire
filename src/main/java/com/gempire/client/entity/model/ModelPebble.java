package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityStarterGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Pebble - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelPebble<T extends EntityStarterGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION_P = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "pebble"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_M = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "mica"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_S = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "shale"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_N = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "nacre"), "main");
    public ModelPart body;
    public ModelPart head;
    public ModelPart legr;
    public ModelPart armr;
    public ModelPart arml;
    public ModelPart legl;

    public ModelPebble(ModelPart root) {
        head = root.getChild("head");
        body = root.getChild("body");
        arml = root.getChild("arml");
        armr = root.getChild("armr");
        legl = root.getChild("legl");
        legr = root.getChild("legr");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-4F, 0.5F, -5.5f, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4F, -8.5F, 4.5F));
        head.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(30, 0)
                        .addBox(-6F, 0.0F, -6.0F, 12.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(43, 18)
                        .addBox(-4.0F, -1.0F, -5F, 8.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("headgem", CubeListBuilder.create().texOffs(0, 46)
                        .addBox(-2F, -1F, -7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("eyegeml", CubeListBuilder.create().texOffs(16, 46)
                        .addBox(-5F, 3.0F, -7F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("eyegemr", CubeListBuilder.create().texOffs(32, 46)
                        .addBox(1.0F, 1.0F, -6.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("backofheadgem", CubeListBuilder.create().texOffs(48, 51)
                        .addBox(-2.0F, 1.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 15)
                        .addBox(0.0F, 0.0F, 0.0F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.0F, 9.5F, -3.2F));
        body.addOrReplaceChild("dress", CubeListBuilder.create().texOffs(18, 32)
                        .addBox(0.0F, 0.0F, 0.0F, 9.0F, 7.5F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-0.5F, 7.0F, -0.5F));
        body.addOrReplaceChild("backgem", CubeListBuilder.create().texOffs(32, 54)
                        .addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 1.0F, 3.0F));
        body.addOrReplaceChild("chestgem", CubeListBuilder.create().texOffs(0, 38)
                        .addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 1.0F, -2.0F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(12, 29)
                        .addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.5F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(5.0F, 9.0F, 1.0F));
        legr.addOrReplaceChild("leggemr", CubeListBuilder.create().texOffs(16, 54)
                        .addBox(0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.5F, 2.0F, -0.5F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 29)
                        .addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.5F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 9.0F, 1.0F));
        legl.addOrReplaceChild("leggeml", CubeListBuilder.create().texOffs(0, 54)
                        .addBox(0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-0.5F, 2.0F, -0.5F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(38, 15)
                        .addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(8.0F, 0.0F, 1.0F));
        armr.addOrReplaceChild("handgemr", CubeListBuilder.create().texOffs(48, 43)
                        .addBox(0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.5F, 5.0F, -0.5F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(48, 35)
                        .addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, 0.0F, 1.0F));
        arml.addOrReplaceChild("handgeml", CubeListBuilder.create().texOffs(48, 43)
                        .addBox(0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-0.5F, 5.0F, -0.5F));


        return LayerDefinition.create(meshdefinition, 74, 62);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(legl, arml, body, legr, head, armr).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * .5f * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw  * ((float)Math.PI / 180F);
        /*this.head2.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head2.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head3.rotateAngleX = headPitch * .5f * ((float)Math.PI / 180F);
        this.head3.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);*/
        this.arml.xRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
        this.armr.xRot = Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.legl.xRot = Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
        this.legr.xRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount;
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
