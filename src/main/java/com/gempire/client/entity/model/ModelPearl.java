package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Pearl - Segapop
 * Created using Tabula 8.0.0
 */

public class ModelPearl<T extends EntityGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "pearl"), "main");
    public ModelPart head;
    public ModelPart body;
    public ModelPart legl;
    public ModelPart legr;
    public ModelPart arml;
    public ModelPart armr;

    public ModelPearl(ModelPart root) {
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
                        .addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 5)
                        .addBox(-0.5F, -3.5F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0, 0.0F));
        head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(32, 0)
                        .addBox(-4.0F, -9.0F, -4.2F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 0, 0.0F));
        head.addOrReplaceChild("bunr", CubeListBuilder.create().texOffs(8, 33).mirror()
                        .addBox(4.5F, -8.5F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));
        head.addOrReplaceChild("bunl", CubeListBuilder.create().texOffs(8, 43)
                        .addBox(-2.0F, -10.5F, 4.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 17)
                        .addBox(-4.0F, 0.0F, -2.0F, 6.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.0F, -1.0F, 0.0F));
        body.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(36, 17)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 7.0F, -1.0F));
        body.addOrReplaceChild("shawl", CubeListBuilder.create().texOffs(32, 42)
                        .addBox(-4.0F, 0.0F, -2.0F, 11.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.5F, -1.0F, -0.5F));

        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(28, 32)
                        .addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.5F, 11.0F, 1.0F));

        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 32)
                        .addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.5F, 11.0F, 1.0F));

        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(28, 17)
                        .addBox(0.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, 0.0F, 0.0F));
        armr.addOrReplaceChild("rshoulder", CubeListBuilder.create().texOffs(16, 56)
                        .addBox(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(0, 17)
                        .addBox(-2.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, 0.0F, 0.0F));
        arml.addOrReplaceChild("lshoulder", CubeListBuilder.create().texOffs(0, 56)
                        .addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body, this.legl, this.armr, this.head, this.legr, this.arml).forEach((modelRenderer) -> {
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
