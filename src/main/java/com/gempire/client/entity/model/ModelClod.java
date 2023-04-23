package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelClod<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "clod"), "main");
    private final ModelPart main;

    public ModelClod(ModelPart root) {
        this.main = root.getChild("main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(30, 17).addBox(-4.0F, -23.0F, -9.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(37, 0).addBox(-2.0F, -11.5F, -5.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition ModelCrystal1_r1 = main.addOrReplaceChild("ModelCrystal1_r1", CubeListBuilder.create().texOffs(37, 0).addBox(-7.0F, -10.5F, 3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -2.5F, 0.9553F, 0.5236F, 0.6155F));

        PartDefinition ModelMudTrail_r1 = main.addOrReplaceChild("ModelMudTrail_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.0F, 1.0F, 12.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition ModelRightArm_r1 = main.addOrReplaceChild("ModelRightArm_r1", CubeListBuilder.create().texOffs(30, 33).addBox(-9.0F, -14.0F, -8.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(5.0F, -14.0F, -8.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition ModelTorso_r1 = main.addOrReplaceChild("ModelTorso_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-5.0F, -15.0F, 1.0F, 10.0F, 17.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}