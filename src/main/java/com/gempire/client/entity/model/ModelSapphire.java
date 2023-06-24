package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Made with Blockbench 4.6.5
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


public class ModelSapphire<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "modelsapphire"), "main");
	private final ModelPart bone;
	private final ModelPart Head;
	private final ModelPart LegR;
	private final ModelPart Skirt;
	private final ModelPart LegL;
	private final ModelPart Body;
	private final ModelPart ArmL;
	private final ModelPart ArmR;
	private final ModelPart Bow;

	public ModelSapphire(ModelPart root) {
		this.bone = root.getChild("bone");
		this.Head = root.getChild("Head");
		this.LegR = root.getChild("LegR");
		this.Skirt = root.getChild("Skirt");
		this.LegL = root.getChild("LegL");
		this.Body = root.getChild("Body");
		this.ArmL = root.getChild("ArmL");
		this.ArmR = root.getChild("ArmR");
		this.Bow = root.getChild("Bow");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, 0.0F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HairBow = Head.addOrReplaceChild("HairBow", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5F, 4.0F));

		PartDefinition Boufant = Head.addOrReplaceChild("Boufant", CubeListBuilder.create().texOffs(38, 22).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.4F, 1F, 0.5F)), PartPose.offset(0.0F, -3.0F, 5.0F));

		PartDefinition Bangs = Head.addOrReplaceChild("Bangs", CubeListBuilder.create().texOffs(0, 38).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.5F, 0.5F, 0.3F)), PartPose.offset(4.0F, -6.9F, -5.8F));

		PartDefinition HairHornR = Head.addOrReplaceChild("HairHornR", CubeListBuilder.create().texOffs(12, 28).mirror().addBox(-3.0F, -10.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(-0.5F, -1.0F, 0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition HairHornL = Head.addOrReplaceChild("HairHornL", CubeListBuilder.create().texOffs(12, 28).addBox(-3.0F, -10.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(-0.5F, -1.0F, 0.0F)), PartPose.offsetAndRotation(-2.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition BunBack = Head.addOrReplaceChild("BunBack", CubeListBuilder.create().texOffs(13, 54).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(2.0F, -10.5F, 3.0F));

		PartDefinition BunL = Head.addOrReplaceChild("BunL", CubeListBuilder.create().texOffs(14, 46).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-3.0F, -10.5F, -1.0F));

		PartDefinition BunR = Head.addOrReplaceChild("BunR", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(7.0F, -10.5F, -1.0F));

		PartDefinition PigtailL = Head.addOrReplaceChild("PigtailL", CubeListBuilder.create().texOffs(26, 50).addBox(-14.0F, -2.0F, 0.3F, 15.0F, 4.0F, 4.0F, new CubeDeformation(3.0F, 0.3F, 0.3F)), PartPose.offsetAndRotation(-2.5F, -6.0F, 4.0F, 1.5708F, 0.0F, -1.5708F));

		PartDefinition PigtailR = Head.addOrReplaceChild("PigtailR", CubeListBuilder.create().texOffs(26, 50).addBox(-14.0F, 0.5F, -2.0F, 15.0F, 4.0F, 4.0F, new CubeDeformation(3.0F, 0.3F, 0.3F)), PartPose.offsetAndRotation(2.5F, -6.0F, 4.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition SideRingletL = Head.addOrReplaceChild("SideRingletL", CubeListBuilder.create().texOffs(15, 38).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F, 0.5F, 1.5F)), PartPose.offset(-4.5F, -5.0F, -2.0F));

		PartDefinition SideRingletR = Head.addOrReplaceChild("SideRingletR", CubeListBuilder.create().texOffs(15, 38).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F, 0.5F, 1.5F)), PartPose.offset(6.5F, -5.0F, -2.0F));

		PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(24, 28).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(-0.2F, 0.8F, -0.2F)), PartPose.offset(1.4F, 16.8F, 0.0F));

		PartDefinition PantlegR = LegR.addOrReplaceChild("PantlegR", CubeListBuilder.create().texOffs(52, 58).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F, 0.3F, 0.2F)), PartPose.offset(0.0F, 1.7F, 0.0F));

		PartDefinition Skirt = partdefinition.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(28, 31).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 11.0F, 8.0F, new CubeDeformation(-0.3F, -0.2F, 0F)), PartPose.offset(5.0F, 13.2F, -4.0F));

		PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(-0.2F, 0.8F, -0.2F)), PartPose.offset(-1.4F, 16.8F, 0.0F));

		PartDefinition PantlegL = LegL.addOrReplaceChild("PantlegL", CubeListBuilder.create().texOffs(40, 58).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F, 0.3F, 0.2F)), PartPose.offset(0.0F, 1.7F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(8, 16).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 8.5F, -2.0F));

		PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.1F, 0F, -0.1F)), PartPose.offsetAndRotation(-2.9F, 9.5F, 0.0F, 0.0F, 0.0F, 9.0F));

		PartDefinition ShoulderL = ArmL.addOrReplaceChild("ShoulderL", CubeListBuilder.create().texOffs(36, 16).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -1.5F, -1.5F));

		PartDefinition SleeveL = ArmL.addOrReplaceChild("SleeveL", CubeListBuilder.create().texOffs(4, 0).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(1F)), PartPose.offset(-1.0F, 3.5F, 0.0F));

		PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create().texOffs(28, 16).addBox(0.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9F, 9.5F, 0.0F, 0.0F, 0.0F, -9F));

		PartDefinition ShoulderR = ArmR.addOrReplaceChild("ShoulderR", CubeListBuilder.create().texOffs(48, 16).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.4F, -1.5F, -1.5F));

		PartDefinition SleeveR = ArmR.addOrReplaceChild("SleeveR", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(1F)), PartPose.offset(1.0F, 3.5F, 0.0F));

		PartDefinition Bow = partdefinition.addOrReplaceChild("Bow", CubeListBuilder.create().texOffs(0, 42).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(1.0F, 0F, 0F)), PartPose.offsetAndRotation(3.0F, 12.0F, 1.3F, 0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.ArmL, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0.1745f);
		this.setRotateAngle(this.ArmR, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0,  -0.1745f);
		this.setRotateAngle(this.LegL, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.LegR, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
	}

	public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Skirt.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Bow.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.ArmL : this.ArmR;
	}
}