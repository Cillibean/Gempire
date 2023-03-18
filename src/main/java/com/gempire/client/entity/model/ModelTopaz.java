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

public class ModelTopaz<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart LeftLeg;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart RightLeg;
	private final ModelPart gems;

	public ModelTopaz(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.RightLeg = root.getChild("RightLeg");
		this.gems = root.getChild("gems");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(24, 18).addBox(-14.0F, 0.0F, 0.0F, 14.0F, 19.0F, 9.0F, new CubeDeformation(1.2f,1f,1.2f)), PartPose.offset(7.0F, -6.0F, -4.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(28, 0).addBox(-4.0F, -9.0F, -3.5F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F,-0.10f,0f)), PartPose.offset(0.0F, -7.9F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 45).addBox(1.3F, 0.0F, -2.7F, 6.0F, 10.0F, 6.0F, new CubeDeformation(-0.5f,0f,-0.5f)), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -3.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.4f,0.8f,0.8f)), PartPose.offset(8.6F, -6.2F, 0.5F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(70, 18).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.4f,0.8f,0.8f)), PartPose.offset(-8.6F, -6.2F, 0.5F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(70, 45).addBox(-7.7F, 0.0F, -2.7F, 6.0F, 10.0F, 6.0F, new CubeDeformation(-0.5f,0f,-0.5f)), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition gems = partdefinition.addOrReplaceChild("gems", CubeListBuilder.create().texOffs(30, 46).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 6.0F, 6.0F, new CubeDeformation(-0.8F,-1f,-1f)), PartPose.offset(6.0F, -15.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 94, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.Head, headPitch * 0.9f * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.LeftArm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.RightArm, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.LeftLeg, Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.RightLeg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount * 0.8f, 0, 0);
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
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		gems.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}