package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Sapphire - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelSapphire<T extends EntityGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "sapphire"), "main");
    public ModelPart body;
    public ModelPart legr;
    public ModelPart legl;
    public ModelPart head;
    public ModelPart armr;
    public ModelPart arml;

    public ModelSapphire(ModelPart root) {
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

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16)
                        .addBox(-3.8F, 0.0F, -4F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0, 1.0F, 0));
        head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-3.8f, 0, -4f, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(34, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.8F, 9.0F, -2.5F));
        body.addOrReplaceChild("dress", CubeListBuilder.create().texOffs(13, 33)
                        .addBox(-4.75f, 14.5f, -4f, 10.0F, 9.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0, 0, 0));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(48, 24)
                        .addBox(0, 0.0F, 0, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.0F, 18.0F, -1.5F));

        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(32, 24)
                        .addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.5F, 18.0F, -1.5F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 38)
                        .addBox(0, 0.0F, 0, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4.0F, 9.0F, -1.5F));
        armr.addOrReplaceChild("armrstick", CubeListBuilder.create().texOffs(50, 14)
                        .addBox(.5f, .5F, .5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0, 0, 0));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(0, 32)
                        .addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-6.5F, 9.0F, -1.4F));
        arml.addOrReplaceChild("armlstick", CubeListBuilder.create().texOffs(34, 14)
                        .addBox(.5f, .5F, .5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0, 0, 0));

        return LayerDefinition.create(meshdefinition, 64, 50);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.arml.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.armr.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.legl.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.legr.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.9f * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.armr, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.arml, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.legr, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.legl, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
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
