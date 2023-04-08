package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModelQuartz<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION_Q = new ModelLayerLocation(
			new ResourceLocation(Gempire.MODID, "quartz"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_J = new ModelLayerLocation(
			new ResourceLocation(Gempire.MODID, "jasper"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_A = new ModelLayerLocation(
			new ResourceLocation(Gempire.MODID, "agate"), "main");	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arml;
	private final ModelPart armr;
	private final ModelPart legl;
	private final ModelPart legr;

	public ModelQuartz(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.arml = root.getChild("arml");
		this.armr = root.getChild("armr");
		this.legl = root.getChild("legl");
		this.legr = root.getChild("legr");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 16).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.8F, 0.6F, -3.1F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.2F, -4.0F, -4.3F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.2F, 0.0F));

		PartDefinition mohawk = head.addOrReplaceChild("mohawk", CubeListBuilder.create().texOffs(70, 40).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(2.8F, -5.8F, -6.3F));

		PartDefinition agatehair = head.addOrReplaceChild("agatehair", CubeListBuilder.create().texOffs(0, 50).addBox(-14.0F, 0.0F, 0.0F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -2.0F, -2.3F));

		PartDefinition messyponytail = head.addOrReplaceChild("messyponytail", CubeListBuilder.create().texOffs(33, 36).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(3.8F, -5.8F, 3.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cherryponytail = head.addOrReplaceChild("cherryponytail", CubeListBuilder.create().texOffs(64, 0).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.4F)), PartPose.offset(2.8F, -5.8F, 4.2F));

		PartDefinition agatebun = head.addOrReplaceChild("agatebun", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-7.0F, -7.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition agatebun2 = head.addOrReplaceChild("agatebun2", CubeListBuilder.create().texOffs(24, 0).addBox(3.0F, -7.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hood = head.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(96, 0).addBox(-7.0F, -10.0F, 0.0F, 14.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(3.8F, -4.1F, -4.3F));

		PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(70, 18).addBox(-0.3F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.1F, 0.7F, -0.1F));

		PartDefinition shoulderpuff1 = arml.addOrReplaceChild("shoulderpuff1", CubeListBuilder.create().texOffs(86, 17).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9F, -0.5F, -2.5F, 0.0F, 0.0F, -0.0873F));

		PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(22, 18).addBox(-3.7F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.7F, -0.1F));

		PartDefinition shoulderpuff2 = armr.addOrReplaceChild("shoulderpuff2", CubeListBuilder.create().texOffs(0, 17).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.5F, 0.0F, 0.0F, 0.0873F));

		PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(64, 36).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 14.5F, -2.1F));

		PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(16, 36).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.9F, 14.5F, -2.1F));

		return LayerDefinition.create(meshdefinition, 124, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.arml, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.armr, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.legl, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.legr, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		arml.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		armr.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		legl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		legr.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}