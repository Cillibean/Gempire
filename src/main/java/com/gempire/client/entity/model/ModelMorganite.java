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

public class ModelMorganite<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "morganite"), "main");
	private final ModelPart Head;
	private final ModelPart LegL;
	private final ModelPart LegR;
	private final ModelPart ArmL;
	private final ModelPart ArmR;
	private final ModelPart Body;

	public ModelMorganite(ModelPart root) {
		this.Head = root.getChild("Head");
		this.LegL = root.getChild("LegL");
		this.LegR = root.getChild("LegR");
		this.ArmL = root.getChild("ArmL");
		this.ArmR = root.getChild("ArmR");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.7F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(24, 8).addBox(-5.0F, -8.5F, -3.7F, 10.0F, 16.0F, 8.0F, new CubeDeformation(-0.5F,0.5f,0.5f)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HairTails = Head.addOrReplaceChild("HairTails", CubeListBuilder.create().texOffs(24, 32).addBox(-5.0F, 8.5F, -3.7F, 10.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Bow_1 = Head.addOrReplaceChild("Bow_1", CubeListBuilder.create().texOffs(16, 58).addBox(-4.0F, -14.0F, 0.0F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(32, 46).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 17.0F, 1.0F));

		PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(40, 46).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 17.0F, 1.0F));

		PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create().texOffs(16, 42).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 10.0F, 0.0F));

		PartDefinition LSleeve = ArmL.addOrReplaceChild("LSleeve", CubeListBuilder.create().texOffs(0, 42).addBox(-3.0F, 3.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create().texOffs(24, 46).addBox(0.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 10.0F, 0.0F));

		PartDefinition RSleeve = ArmR.addOrReplaceChild("RSleeve", CubeListBuilder.create().texOffs(0, 53).addBox(-1.0F, 3.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 9.0F, -0.5F));

		PartDefinition Skirt = Body.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0F, 0.0F, -2.2F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(1.0F, 8.0F, 0.5F));

		PartDefinition Train = Body.addOrReplaceChild("Train", CubeListBuilder.create().texOffs(20, 2).addBox(-4.0F, 0.0F, -1.6F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 15.0F, 6.5F));

		PartDefinition Bow = Body.addOrReplaceChild("Bow", CubeListBuilder.create().texOffs(38, 0).addBox(-3.5F, 0.0F, -2.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.Head, headPitch * 0.9f * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.ArmL, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.ArmR, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.LegL, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.LegR, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
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
		LegL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.ArmL : this.ArmR;
	}
}