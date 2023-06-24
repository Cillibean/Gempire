package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class ModelPeridot<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "peridot"), "main");
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart LArm;
	private final ModelPart RArm;
	private final ModelPart LLeg;
	private final ModelPart RLeg;

	public ModelPeridot(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.LArm = root.getChild("LArm");
		this.RArm = root.getChild("RArm");
		this.LLeg = root.getChild("LLeg");
		this.RLeg = root.getChild("RLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -3.65F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Triangleleft = Head.addOrReplaceChild("Triangleleft", CubeListBuilder.create().texOffs(0, 84).addBox(0.0F, 0.0F, 0.0F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -7.2F, -3.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Trianglebase = Head.addOrReplaceChild("Trianglebase", CubeListBuilder.create().texOffs(0, 117).addBox(-3.5F, -9.0F, -4.0F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.9F, 7.5F));

		PartDefinition Trianglecenter = Head.addOrReplaceChild("Trianglecenter", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -10.7F, -3.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Squaredown = Head.addOrReplaceChild("Squaredown", CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -8.0F, -4.0F, 16.0F, 12.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.5F, 0.5F));

		PartDefinition Triangleright = Head.addOrReplaceChild("Triangleright", CubeListBuilder.create().texOffs(0, 103).addBox(0.0F, 0.0F, 0.0F, 10.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.7F, -3.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 4.0F, -2.0F));

		PartDefinition LArm = partdefinition.addOrReplaceChild("LArm", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 5.0F, 0.0F));

		PartDefinition shape21 = LArm.addOrReplaceChild("shape21", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -0.25F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -1.0F, -1.5F));

		PartDefinition RArm = partdefinition.addOrReplaceChild("RArm", CubeListBuilder.create().texOffs(40, 0).addBox(0.0F, -1.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 5.0F, 0.0F));

		PartDefinition shape22 = RArm.addOrReplaceChild("shape22", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -0.25F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, -1.5F));

		PartDefinition LLeg = partdefinition.addOrReplaceChild("LLeg", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 14.0F, 0.0F));

		PartDefinition RLeg = partdefinition.addOrReplaceChild("RLeg", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 14.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.LArm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.RArm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.LLeg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.RLeg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.LArm : this.RArm;
	}
}