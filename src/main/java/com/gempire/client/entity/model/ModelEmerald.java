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

public class ModelEmerald<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "emerald"), "main");
	private final ModelPart LeftLeg;
	private final ModelPart RightArm;
	private final ModelPart Head;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;
	private final ModelPart Body;

	public ModelEmerald(ModelPart root) {
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightArm = root.getChild("RightArm");
		this.Head = root.getChild("Head");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(60, 34).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 11.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(80, 36).addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, 0.0F));

		PartDefinition RightArmBangle = RightArm.addOrReplaceChild("RightArmBangle", CubeListBuilder.create().texOffs(76, 50).addBox(-0.5F, 7.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightShoulderPad = RightArm.addOrReplaceChild("RightShoulderPad", CubeListBuilder.create().texOffs(28, 24).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.1F, -2.7F, -2.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition LeftPigtail = Head.addOrReplaceChild("LeftPigtail", CubeListBuilder.create().texOffs(48, 20).mirror().addBox(7.0F, -6.0F, -2.5F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6632F));

		PartDefinition RightPigtail = Head.addOrReplaceChild("RightPigtail", CubeListBuilder.create().texOffs(48, 20).addBox(-15.0F, -6.0F, -2.0F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6632F));

		PartDefinition LargeHair = Head.addOrReplaceChild("LargeHair", CubeListBuilder.create().texOffs(0, 41).addBox(-12.5F, -4.8F, -0.5F, 13.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.2217F, 0.3142F, 0.6981F));

		PartDefinition ShortHair = Head.addOrReplaceChild("ShortHair", CubeListBuilder.create().texOffs(0, 23).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BackBun = Head.addOrReplaceChild("BackBun", CubeListBuilder.create().texOffs(72, 26).addBox(-2.6F, -6.5F, 4.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition PuffyHair = Head.addOrReplaceChild("PuffyHair", CubeListBuilder.create().texOffs(32, 0).addBox(-8.0F, -10.0F, -4.0F, 16.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tiara = Head.addOrReplaceChild("Tiara", CubeListBuilder.create().texOffs(11, 18).addBox(-5.0F, -9.0F, -4.6F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(60, 62).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 11.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(72, 36).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.0F, 0.0F));

		PartDefinition LeftShoulderPad = LeftArm.addOrReplaceChild("LeftShoulderPad", CubeListBuilder.create().texOffs(28, 24).addBox(0.0F, 0.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -4.2F, -2.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LeftArmBangle = LeftArm.addOrReplaceChild("LeftArmBangle", CubeListBuilder.create().texOffs(76, 50).addBox(-2.5F, 7.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(52, 50).addBox(0.0F, 0.0F, 0.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, -3.0F));

		PartDefinition Waist = Body.addOrReplaceChild("Waist", CubeListBuilder.create().texOffs(39, 41).addBox(0.0F, 0.0F, 0.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 6.0F, 1.0F));

		PartDefinition Hips = Waist.addOrReplaceChild("Hips", CubeListBuilder.create().texOffs(36, 31).addBox(0.0F, 0.0F, 0.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, 0.0F));

		PartDefinition Neck = Body.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 94, 82);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(LeftArm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(RightArm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(LeftLeg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(RightLeg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
	}
}