package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityLapis;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class ModelLapis<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "lapis"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public ModelLapis(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition dress = body.addOrReplaceChild("dress", CubeListBuilder.create().texOffs(36, 0).addBox(-4.0F, 9.0F, -2.5F, 8.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bow1 = body.addOrReplaceChild("bow1", CubeListBuilder.create().texOffs(0, 65).addBox(1.0F, -1.0F, 1.5F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition bow2 = body.addOrReplaceChild("bow2", CubeListBuilder.create().texOffs(0, 65).addBox(-1.0F, -1.0F, 1.5F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(8, 68).addBox(-20.0F, -15.5F, 3.0F, 19.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 94).mirror().addBox(1.0F, -16.5F, 3.0F, 19.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition frontbow2 = body.addOrReplaceChild("frontbow2", CubeListBuilder.create().texOffs(0, 71).addBox(-1.0F, 7.0F, -4.6F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition frontbow1 = body.addOrReplaceChild("frontbow1", CubeListBuilder.create().texOffs(0, 71).addBox(1.0F, 7.0F, -4.6F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(32, 37).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hair2 = head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(0, 57).addBox(-6.0F, -3.0F, -4.0F, 12.0F, 3.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hair3 = head.addOrReplaceChild("hair3", CubeListBuilder.create().texOffs(0, 37).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hair4 = head.addOrReplaceChild("hair4", CubeListBuilder.create().texOffs(0, 59).addBox(-1.0F, 0.5F, 4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).addBox(0.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, 0.0F));

		PartDefinition left_arm_puff = left_arm.addOrReplaceChild("left_arm_puff", CubeListBuilder.create().texOffs(36, 20).addBox(-0.5F, 4.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 0.0F));

		PartDefinition right_arm_puff = right_arm.addOrReplaceChild("right_arm_puff", CubeListBuilder.create().texOffs(48, 20).addBox(-2.5F, 4.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(28, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leg_puff = left_leg.addOrReplaceChild("left_leg_puff", CubeListBuilder.create().texOffs(36, 28).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leg_puff = right_leg.addOrReplaceChild("right_leg_puff", CubeListBuilder.create().texOffs(48, 28).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!((EntityLapis) entityIn).flying) {
			this.setRotateAngle(body, 0, 0, 0);
			this.setRotateAngle(head, headPitch * 0.5f * ((float) Math.PI / 180F), netHeadYaw * .5f * ((float) Math.PI / 180F), 0);
			this.setRotateAngle(left_arm, Mth.cos(limbSwing * 0.5F + (float) Math.PI) * limbSwingAmount * 0.8f, 0, 0);
			this.setRotateAngle(right_arm, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
			this.setRotateAngle(left_leg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
			this.setRotateAngle(right_leg, Mth.cos(limbSwing * 0.5F + (float) Math.PI) * limbSwingAmount * 0.8f, 0, 0);
			left_leg.z = -1;
			right_leg.z = -1;
			left_leg.y = 12;
			right_leg.y = 12;
		} else {
			this.setRotateAngle(left_arm, 0, 0, 0);
			this.setRotateAngle(right_arm, 0, 0, 0);
			this.setRotateAngle(body, 90, 0, 0);
			this.setRotateAngle(left_leg, 90 + Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
			this.setRotateAngle(right_leg, 90 + Mth.cos(limbSwing * 0.5F + (float) Math.PI) * limbSwingAmount * 0.8f, 0, 0);
			left_leg.z = body.z+12;
			left_leg.y = body.y-3;
			right_leg.z = body.z+12;
			right_leg.y = body.y-3;
		}
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
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}
}