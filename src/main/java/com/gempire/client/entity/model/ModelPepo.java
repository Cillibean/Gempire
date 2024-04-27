package com.gempire.client.entity.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.gempire.entities.other.EntityPepo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModelPepo<T extends EntityPepo> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "pepo"), "main");
	private final ModelPart bb_main;

	public ModelPepo(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 20.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-5.0F, 8.0F, -3.5F, 10.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(34, 19).addBox(-5.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(34, 19).mirror().addBox(1.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 4).addBox(-9.0F, 9.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 4).mirror().addBox(5.0F, 9.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(1.0F, 22.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-3.0F, 22.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 35).addBox(3.0F, 8.0F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).mirror().addBox(-11.0F, 8.0F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leafLeft_r1 = bb_main.addOrReplaceChild("leafLeft_r1", CubeListBuilder.create().texOffs(8, 35).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition leafRight_r1 = bb_main.addOrReplaceChild("leafRight_r1", CubeListBuilder.create().texOffs(8, 35).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition stemFlipped_r1 = bb_main.addOrReplaceChild("stemFlipped_r1", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-3.0F, -4.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 32.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition stem_r1 = bb_main.addOrReplaceChild("stem_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 32.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition left_armThorns4_r1 = bb_main.addOrReplaceChild("left_armThorns4_r1", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-4.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 14.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right_armThorns4_r1 = bb_main.addOrReplaceChild("right_armThorns4_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 14.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_armThorns1_r1 = bb_main.addOrReplaceChild("left_armThorns1_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 14.5F, 0.0F, 0.0F, 2.3562F, 0.0F));

		PartDefinition left_armThorns2_r1 = bb_main.addOrReplaceChild("left_armThorns2_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 14.5F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition right_armThorns2_r1 = bb_main.addOrReplaceChild("right_armThorns2_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 14.5F, 0.0F, 0.0F, 2.3562F, 0.0F));

		PartDefinition right_armThorns1_r1 = bb_main.addOrReplaceChild("right_armThorns1_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 14.5F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}