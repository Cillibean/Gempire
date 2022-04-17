package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
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
 * Nephrite - Segapop
 * Created using Tabula 8.0.0
 */

public class ModelNephrite<T extends EntityGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "nephrite"), "main");

    public ModelPart head;
    public ModelPart body;
    public ModelPart arml;
    public ModelPart armr;
    public ModelPart legl;
    public ModelPart legr;

    public ModelNephrite(ModelPart root) {
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

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0,0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(32, 0)
                        .addBox(-4.0F, -8.0F, -4.2F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("pigtail2l", CubeListBuilder.create().texOffs(54, 16)
                        .addBox(-8.5F, -5.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.296705972839036F));
        head.addOrReplaceChild("hairpoof", CubeListBuilder.create().texOffs(32, 39)
                        .addBox(-7.0F, -4.2F, -5.0F, 14.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("pigtaill", CubeListBuilder.create().texOffs(32, 53)
                        .addBox(-8.0F, -9.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("pigtailr", CubeListBuilder.create().texOffs(48, 53)
                        .addBox(4.0F, -9.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("ponytail", CubeListBuilder.create().texOffs(8, 32)
                        .addBox(-2.5F, -9.5F, 5.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.2617993877991494F, 0.0F, 0.0F));
        head.addOrReplaceChild("fuzz", CubeListBuilder.create().texOffs(64, 0)
                        .addBox(-6.0F, -3.5F, -1.8F, 12.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("ponytail1", CubeListBuilder.create().texOffs(78, 50)
                        .addBox(-3.5F, -12.0F, 2.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("hairl", CubeListBuilder.create().texOffs(88, 25)
                        .addBox(-4.0F, -8.9F, -4.1F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.6F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("pigtail2r", CubeListBuilder.create().texOffs(66, 16)
                        .addBox(5.5F, -5.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F,0.0F, 0.0F, -0.296705972839036F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16)
                        .addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        body.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(0, 53)
                        .addBox(-8.0F, 8.0F, 1.0F, 16.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.08726646259971647F, 0.0F, 0.0F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(28, 32)
                        .addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.5F, 11.0F, 1.0F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 32)
                        .addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.5F, 11.0F, 1.0F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(28, 16)
                        .addBox(0.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, 0.0F, 0.0F));
        armr.addOrReplaceChild("rshoulder", CubeListBuilder.create().texOffs(36, 25)
                        .addBox(0.0F, -3.0F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, 0.0F, 0.0F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(0, 16)
                        .addBox(-2.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, 0.0F, 0.0F));
        arml.addOrReplaceChild("lshoulder", CubeListBuilder.create().texOffs(36, 16)
                        .addBox(-5.0F, -3.0F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(legl, arml, body, legr, head, armr).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(arml, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(armr, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(legl, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(legr, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
