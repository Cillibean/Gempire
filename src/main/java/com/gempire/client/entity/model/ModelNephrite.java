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

public class ModelNephrite<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "nephrite"), "main");
	private final ModelPart LegR;
	private final ModelPart Head;
	private final ModelPart ArmR;
	private final ModelPart ArmL;
	private final ModelPart LegL;
	private final ModelPart Body;

	public ModelNephrite(ModelPart root) {
		this.LegR = root.getChild("LegR");
		this.Head = root.getChild("Head");
		this.ArmR = root.getChild("ArmR");
		this.ArmL = root.getChild("ArmL");
		this.LegL = root.getChild("LegL");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 11.0F, 1.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.2F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition PigtailL = Hair.addOrReplaceChild("PigtailL", CubeListBuilder.create().texOffs(32, 53).addBox(4.0F, -9.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition PigtailR = Hair.addOrReplaceChild("PigtailR", CubeListBuilder.create().texOffs(48, 53).addBox(-8.0F, -9.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Pigtail2L = Hair.addOrReplaceChild("Pigtail2L", CubeListBuilder.create().texOffs(54, 16).addBox(5.5F, -5.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2967F));

		PartDefinition Pigtail2R = Hair.addOrReplaceChild("Pigtail2R", CubeListBuilder.create().texOffs(66, 16).addBox(-8.5F, -5.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2967F));

		PartDefinition Ponytail = Hair.addOrReplaceChild("Ponytail", CubeListBuilder.create().texOffs(8, 32).addBox(-2.5F, -9.5F, 5.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition HairPoof = Hair.addOrReplaceChild("HairPoof", CubeListBuilder.create().texOffs(32, 39).addBox(-7.0F, -4.2F, -4.5F, 14.0F, 4.0F, 10.0F, new CubeDeformation(-0.20F,0F,0f)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Ponytail_1 = Hair.addOrReplaceChild("Ponytail_1", CubeListBuilder.create().texOffs(78, 50).addBox(-3.5F, -12.0F, 2.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Fuzz = Hair.addOrReplaceChild("Fuzz", CubeListBuilder.create().texOffs(64, 0).addBox(-6.0F, -3.5F, -1.8F, 12.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HairL = Hair.addOrReplaceChild("HairL", CubeListBuilder.create().texOffs(88, 25).addBox(-4.0F, -8.9F, -4.1F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition RShoulder = ArmR.addOrReplaceChild("RShoulder", CubeListBuilder.create().texOffs(36, 16).addBox(-5.0F, -3.0F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create().texOffs(28, 16).addBox(0.0F, -1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition LShoulder = ArmL.addOrReplaceChild("LShoulder", CubeListBuilder.create().texOffs(36, 25).addBox(0.0F, -3.0F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(28, 32).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 11.0F, 1.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 4.0F, new CubeDeformation(0.0F,0f,-0.2f)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Skirt = Body.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(0, 53).addBox(-8.0F, 8.0F, 1.0F, 16.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotateAngle(Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
        this.setRotateAngle(ArmL, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(ArmR, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(LegL, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
        this.setRotateAngle(LegR, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		LegR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ArmL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}