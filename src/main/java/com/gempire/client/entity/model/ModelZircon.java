package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class ModelZircon<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "zircon"), "main");
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart Head;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;
	private final ModelPart LeftLeg;

	public ModelZircon(ModelPart root) {
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.Head = root.getChild("Head");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 12).addBox(-3.0F, 0.1F, -1.9F, 6.0F, 10.0F, 4.0F, new CubeDeformation(-0.1F, 0.1F, -0.1F)), PartPose.offset(0.0F, 1.8F, 0.0F));

		PartDefinition Shoulders = Body.addOrReplaceChild("Shoulders", CubeListBuilder.create().texOffs(0, 43).addBox(-7.0F, 0.0F, -2.5F, 14.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Chest = Body.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(12, 28).addBox(-2.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 2.8F, 0.0F));

		PartDefinition RArmPuff = RightArm.addOrReplaceChild("RArmPuff", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-2.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.2F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.8F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.3F, -0.2F, -0.3F)), PartPose.offset(0.0F, 1.8F, 0.0F));

		PartDefinition Hat1 = Head.addOrReplaceChild("Hat1", CubeListBuilder.create().texOffs(28, 28).addBox(-4.0F, -9.5F, -6.0F, 8.0F, 4.0F, 10.0F, new CubeDeformation(-0.3F, -0.2F, -0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Nose = Head.addOrReplaceChild("Nose", CubeListBuilder.create().texOffs(0, 0).addBox(-0.2F, -3.2F, -5.7F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3F, -0.1F, 0.0F));

		PartDefinition EyeGlass = Head.addOrReplaceChild("EyeGlass", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -6.2F, -4.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(-0.1F, -0.1F, 0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hat2 = Head.addOrReplaceChild("Hat2", CubeListBuilder.create().texOffs(32, 45).addBox(-4.0F, -12.6F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(-0.3F, 0.0F, -0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(20, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RLegPuff = RightLeg.addOrReplaceChild("RLegPuff", CubeListBuilder.create().texOffs(0, 34).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.1F, 0.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 10).addBox(0.0F, -0.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 2.8F, 0.0F));

		PartDefinition LArmPuff = LeftArm.addOrReplaceChild("LArmPuff", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 6.3F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.2F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(55, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LLegPuff = LeftLeg.addOrReplaceChild("LLegPuff", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-1.5F, 5.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -0.1F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.LeftArm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.RightArm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.LeftLeg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.RightLeg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
	}
}