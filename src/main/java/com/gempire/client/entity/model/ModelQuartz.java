package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bases.AbstractQuartz;
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
 * Quartz - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelQuartz<T extends AbstractQuartz> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION_Q = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "quartz"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_J = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "jasper"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_A = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "agate"), "main");
    public ModelPart body;
    public ModelPart head;
    public ModelPart arml;
    public ModelPart legl;
    public ModelPart legr;
    public ModelPart armr;


    public ModelQuartz(ModelPart root) {
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

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0)
                        .addBox(-3, 0.2f, -5, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4, -8.1F, 4));
        head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-3.0F, 0.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)),
                PartPose.offset(0F, 0.0F, 0F));
        head.addOrReplaceChild("agatehair", CubeListBuilder.create().texOffs(0, 50)
                        .addBox(-6F, 0.0F, -3F, 14.0F, 4.0F, 4.0F,
                                new CubeDeformation(0.5f, -.1f, -.1f)),
                PartPose.offset(0.0F, 2.0F, 0.0F));
        head.addOrReplaceChild("messyponytail", CubeListBuilder.create().texOffs(33, 36)
                        .addBox(-3.0F, -1.5F, -5.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2617993877991494F, 0.0F, 0.0F));
        head.addOrReplaceChild("mohawk", CubeListBuilder.create().texOffs(70, 40)
                        .addBox(-2F, 0.0F, -7.0F, 6.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0, -2.0F, 0F));
        head.addOrReplaceChild("cherryponytail", CubeListBuilder.create().texOffs(64, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-2, -1.5F, 3.5F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 16)
                        .addBox(0.0F, 0.0F, 0.0F, 10.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.8F, 0.6F, -3.1F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(64, 36)
                        .addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.5F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(5.7F, 13.9F, 1.0F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(16, 36)
                        .addBox(0.0F, 0.0F, 0.0F, 4.0F, 9.5F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.3F, 13.9F, 1.0F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(70, 18)
                        .addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F,
                                new CubeDeformation(0.2F, 0, .2f)),
                PartPose.offset(10.3F, 0.1F, 1.0F));
        armr.addOrReplaceChild("shoulderpuffr", CubeListBuilder.create().texOffs(86, 17)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 0.0F, -0.5F, 0.0F, 0.0F, -0.08726646259971647F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(22, 18)
                        .addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F,
                                new CubeDeformation(0.2F, 0, .2f)),
                PartPose.offset(-4.3F, 0.1F, 1.0F));
        arml.addOrReplaceChild("shoulderpuffl", CubeListBuilder.create().texOffs(0, 17)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, -0.5F, -0.5F, 0.0F, 0.0F, 0.08726646259971647F));


        return LayerDefinition.create(meshdefinition, 124, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(head, armr, arml, legl, legr, body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.arml, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.armr, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.legl, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.legr, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
