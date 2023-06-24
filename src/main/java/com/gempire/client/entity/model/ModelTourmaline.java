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

public class ModelTourmaline<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "tourmaline"), "main");
	private final ModelPart head;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart body;
	private final ModelPart leftarmbase;
	private final ModelPart rightarmbase;

	public ModelTourmaline(ModelPart root) {
		this.head = root.getChild("head");
		this.rightleg = root.getChild("rightleg");
		this.leftleg = root.getChild("leftleg");
		this.body = root.getChild("body");
		this.leftarmbase = root.getChild("leftarmbase");
		this.rightarmbase = root.getChild("rightarmbase");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0F, -7.0F, -3.5F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(16, 47).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(3.6F, 17.0F, 0.5F));

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-3.6F, 17.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -6.0F, 16.0F, 16.0F, 13.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(24, 36).addBox(-5.5F, 16.0F, -3.5F, 11.0F, 3.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftarmbase = partdefinition.addOrReplaceChild("leftarmbase", CubeListBuilder.create().texOffs(32, 47).addBox(-1.0F, -0.5F, -2.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 2.0F, 0.0F));

		PartDefinition leftarm = leftarmbase.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(48, 47).addBox(-1.0F, -0.5F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition rightarmbase = partdefinition.addOrReplaceChild("rightarmbase", CubeListBuilder.create().texOffs(32, 47).addBox(-1.0F, -0.5F, -2.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 2.0F, 0.0F));

		PartDefinition rightarm = rightarmbase.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(32, 47).addBox(-3.0F, -0.5F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.leftarmbase, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.rightarmbase, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.leftleg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.rightleg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftarmbase.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightarmbase.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.leftarmbase : this.rightarmbase;
	}
}