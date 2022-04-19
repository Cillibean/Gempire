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
 * ModelObsidian - Pezzottaite
 * Created using Tabula 8.0.0
 */

public class ModelObsidian<T extends EntityGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "obsidian"), "main");
    public ModelPart head;
    public ModelPart armr;
    public ModelPart arml;
    public ModelPart body;
    public ModelPart legl;
    public ModelPart legr;

    public ModelObsidian(ModelPart root) {
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

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(46, 0)
                        .addBox(-12.0F, -14.0F, -5.0F, 16.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4F, -3.0F, 0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-7.0F, 0.0F, -4.5F, 14.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -3.0F, 1.0F));
        body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 20)
                        .addBox(-5.5F, 0.0F, -3.0F, 11.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 8.0F, 1.0F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(82, 40)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.5F, 15.0F, 1.0F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(82, 27)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.5F, 15.0F, 1.0F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(58, 26)
                        .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-10.0F, -3.0F, 1.0F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(34, 26)
                        .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(10.0F, -3.0F, 1.0F));


        return LayerDefinition.create(meshdefinition, 112, 64);
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
