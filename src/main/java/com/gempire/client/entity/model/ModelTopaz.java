package com.gempire.client.entity.model;

import com.gempire.entities.bases.EntityGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

/**
 * Topaz - TheBetaZeta
 * Created using Tabula 8.0.0
 */

public class ModelTopaz<T extends EntityGem> extends ModelGem<T> {
    public ModelPart body;
    public ModelPart head;
    public ModelPart legl;
    public ModelPart arml;
    public ModelPart armr;
    public ModelPart legr;
    public ModelPart gems;

    public ModelTopaz(ModelPart root) {
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

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 0)
                        .addBox(-4F, 0.0F, -3.5F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0, -17F, 0));
        head.addOrReplaceChild("gems", CubeListBuilder.create().texOffs(30, 46)
                        .addBox(0.0F, 0.0F, 0.0F, 12.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-6.8F, 2F, -3.0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 18)
                        .addBox(0.0F, 0.0F, 0.0F, 14.0F, 19.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-5.8F, -5.0F, -2.8F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(70, 45)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.2F, 14.0F, -3.2F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 45)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-7.8F, 14.0F, -3.2F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(70, 18)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(9F, -5.4F, -1.7F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(0, 18)
                        .addBox(0.0F, 0.0F, 0.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-14.2F, -5.4F, -1.7F));

        return LayerDefinition.create(meshdefinition, 94, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.legl, this.arml, this.body, this.legr, this.head, this.armr).forEach((modelRenderer) -> {
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
