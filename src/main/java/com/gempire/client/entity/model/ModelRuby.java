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
 * ModelRuby - pezzottaite
 * Created using Tabula 8.0.0
 */

public class ModelRuby<T extends EntityGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "ruby"), "main");
    public ModelPart head;
    public ModelPart body;
    public ModelPart armr;
    public ModelPart arml;
    public ModelPart legl;
    public ModelPart legr;

    public ModelRuby(ModelPart root) {
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
                        .addBox(-7.0F, -11.0F, -4.5F, 14.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 10.0F, 0.0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20)
                        .addBox(-4.0F, -4.5F, -2.5F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 14.5F, 0.0F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(42, 32)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 18.0F, 0.0F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 32)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-2.0F, 18.0F, 0.0F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 20)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-6.0F, 10.0F, 0.0F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(42, 20)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(6.0F, 10.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 42);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.arml, this.legl, this.legr, this.body, this.armr, this.head).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(this.head, headPitch * 0.9f * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(this.arml, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.armr, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.legl, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(this.legr, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
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
