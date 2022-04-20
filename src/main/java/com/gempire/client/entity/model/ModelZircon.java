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
 * zircon - Undefined
 * Created using Tabula 8.0.0
 */

public class ModelZircon<T extends EntityGem> extends ModelGem<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Gempire.MODID, "zircon"), "main");
    public ModelPart body;
    public ModelPart armr;
    public ModelPart head;
    public ModelPart legr;
    public ModelPart arml;
    public ModelPart legl;

    public ModelZircon(ModelPart root) {
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
                        .addBox(-4.0F, -7.8F, -4.0F, 8.0F, 8.0F, 8.0F,
                                new CubeDeformation(-.3F, -.2f, -.3f)),
                PartPose.offset(0.0F, 1.8F, 0.0F));
        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-0.8F, -3.2F, -5.7F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("eyeglass", CubeListBuilder.create().texOffs(0, 17)
                        .addBox(0.4F, -6.2F, -4.0F, 3.0F, 4.0F, 0.0F,
                                new CubeDeformation(-0.1F, -0.1f, 0)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("hat1", CubeListBuilder.create().texOffs(28, 28)
                        .addBox(-4.0F, -9.5F, -6.0F, 8.0F, 4.0F, 10.0F,
                                new CubeDeformation(-0.3F, -0.2F, -0.3F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(32, 45)
                        .addBox(-4.0F, -12.6F, -4.0F, 8.0F, 5.0F, 8.0F,
                                new CubeDeformation(-0.3F, 0.0F, -0.3F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 12)
                        .addBox(-3.0F, 0.1F, -1.9F, 6.0F, 10.0F, 4.0F,
                                new CubeDeformation(-.1F, .1f, -.1f)),
                PartPose.offset(0.0F, 1.8F, 0.0F));
        body.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(32, 0)
                        .addBox(-4.0F, 0.0F, -2.5F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 1.8F, 0.0F));
        body.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(0, 43)
                        .addBox(-7.0F, 0.0F, -2.5F, 14.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 1.8F, 0.0F));


        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(55, 24).mirror(true)
                        .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.9F, 12.0F, 0.0F));
        legr.addOrReplaceChild("legpuffr", CubeListBuilder.create().texOffs(0, 51).mirror(true)
                        .addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0F, 0.0F));


        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(20, 28)
                        .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.9F, 12.0F, 0.0F));
        legl.addOrReplaceChild("legpuffl", CubeListBuilder.create().texOffs(0, 34)
                        .addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0F, 0.0F));


        PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(56, 10).mirror(true)
                        .addBox(0.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4.0F, 2.8F, 0.0F));
        armr.addOrReplaceChild("armpuffr", CubeListBuilder.create().texOffs(0, 22).mirror(true)
                        .addBox(-0.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0F, 0.0F));


        PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(12, 28)
                        .addBox(-2.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.0F, 2.8F, 0.0F));
        arml.addOrReplaceChild("armpuffl", CubeListBuilder.create().texOffs(0, 28)
                        .addBox(-2.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.arml, this.armr, this.body, this.head, this.legr, this.legl).forEach((modelRenderer) -> {
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
