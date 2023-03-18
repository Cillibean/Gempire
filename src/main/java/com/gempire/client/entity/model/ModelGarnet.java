package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.client.entity.model.ModelGem;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModelGarnet<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "garnet"), "main");
	private final ModelPart Head;
	private final ModelPart ArmLeft;
	private final ModelPart ArmRight;
	private final ModelPart LegLeft;
	private final ModelPart LegRight;
	private final ModelPart Body;

	public ModelGarnet(ModelPart root) {
		this.Head = root.getChild("Head");
		this.ArmLeft = root.getChild("ArmLeft");
		this.ArmRight = root.getChild("ArmRight");
		this.LegLeft = root.getChild("LegLeft");
		this.LegRight = root.getChild("LegRight");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition Hair5 = Head.addOrReplaceChild("Hair5", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hair3 = Hair5.addOrReplaceChild("Hair3", CubeListBuilder.create().texOffs(0, 73).addBox(-3.5F, -9.0F, 6.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(-0.7F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Hair4 = Hair5.addOrReplaceChild("Hair4", CubeListBuilder.create().texOffs(68, 43).addBox(-6.0F, -11.1F, -4.9F, 12.0F, 16.0F, 10.0F, new CubeDeformation(-0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hair1 = Hair5.addOrReplaceChild("Hair1", CubeListBuilder.create().texOffs(0, 26).addBox(-6.0F, -12.0F, -4.0F, 12.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hair2 = Hair5.addOrReplaceChild("Hair2", CubeListBuilder.create().texOffs(28, 80).addBox(-5.0F, -16.0F, -4.0F, 10.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ArmLeft = partdefinition.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(80, 0).addBox(-2.0F, -1.3F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offset(-4.5F, -3.0F, 0.0F));

		PartDefinition ArmPuffLeft = ArmLeft.addOrReplaceChild("ArmPuffLeft", CubeListBuilder.create().texOffs(116, 6).addBox(-2.9F, 6.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ShoulderPuffLeft = ArmLeft.addOrReplaceChild("ShoulderPuffLeft", CubeListBuilder.create().texOffs(27, 15).addBox(-4.5F, -2.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition ArmRight = partdefinition.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(108, 0).mirror().addBox(0.0F, -1.3F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(4.5F, -3.0F, 0.0F));

		PartDefinition ArmPuffRight = ArmRight.addOrReplaceChild("ArmPuffRight", CubeListBuilder.create().texOffs(113, 13).addBox(-0.4F, 6.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ShoulderPuffRight = ArmRight.addOrReplaceChild("ShoulderPuffRight", CubeListBuilder.create().texOffs(88, 32).mirror().addBox(-0.5F, -2.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition LegLeft = partdefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(56, 43).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 15.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(2.5F, 8.7F, 0.0F));

		PartDefinition LegPuffLeft = LegLeft.addOrReplaceChild("LegPuffLeft", CubeListBuilder.create().texOffs(88, 20).addBox(-2.3F, -3.2F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LegRight = partdefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(44, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 15.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(-2.5F, 8.7F, 0.0F));

		PartDefinition LegPuffRight = LegRight.addOrReplaceChild("LegPuffRight", CubeListBuilder.create().texOffs(108, 20).mirror().addBox(-2.7F, -3.2F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(88, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 9.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -4.5F, 0.0F));

		PartDefinition Chest = Body.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Neck = Body.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition Hips = Body.addOrReplaceChild("Hips", CubeListBuilder.create().texOffs(84, 13).addBox(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition Dress = Body.addOrReplaceChild("Dress", CubeListBuilder.create().texOffs(44, 15).addBox(-5.5F, 9.9F, -5.5F, 11.0F, 17.0F, 11.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition Cape = Body.addOrReplaceChild("Cape", CubeListBuilder.create().texOffs(1, 108).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 18.0F, 0.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.1047F, 0.0F, 0.0F));

		PartDefinition CapeTop = Body.addOrReplaceChild("CapeTop", CubeListBuilder.create().texOffs(32, 0).addBox(-8.0F, -4.0F, -2.3F, 16.0F, 7.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -0.4F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(ArmLeft, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(ArmRight, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(LegLeft, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(LegRight, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}