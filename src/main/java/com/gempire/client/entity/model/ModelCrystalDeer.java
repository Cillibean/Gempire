package com.gempire.client.entity.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelCrystalDeer<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "crystal_deer"), "main");
	private final ModelPart model;

	public ModelCrystalDeer(ModelPart root) {
		this.model = root.getChild("model");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition model = partdefinition.addOrReplaceChild("model", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = model.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-4.0F, 17.0F, 9.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(40, 62).addBox(-4.0F, -1.0F, 1.0F, 8.0F, 26.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -36.0F, 7.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition neck = model.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -33.0F, -13.0F));

		PartDefinition cube_r2 = neck.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 35).addBox(0.0F, -35.0F, -23.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(72, 62).addBox(-4.0F, -37.0F, -20.0F, 8.0F, 16.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 24.0F, 14.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(72, 84).addBox(-4.0F, -10.9052F, -6.1342F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(86, 35).addBox(-3.0F, -10.9052F, -14.1342F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -16.0F, 1.0F, 14.0F, 16.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(22, 22).addBox(-7.0F, -16.0F, 2.0F, 14.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(53, 2).addBox(0.0F, -16.0F, -14.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(53, 6).addBox(-2.0F, -16.0F, -12.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 4.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(106, 101).addBox(0.0F, -8.0F, 0.0F, 0.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -8.0F, 1.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(106, 101).addBox(0.0F, -8.0F, 0.0F, 0.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -8.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-11, 117).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-11, 117).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -16.0F, 0.0F, 0.0F, 16.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -10.0F, -4.0F, 0.1309F, -0.1745F, 0.0F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -16.0F, 0.0F, 0.0F, 16.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -10.0F, -4.0F, 0.1309F, 0.1745F, 0.0F));

		PartDefinition backleft = model.addOrReplaceChild("backleft", CubeListBuilder.create().texOffs(16, 88).addBox(-2.0F, 8.0F, 3.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(98, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -32.0F, 7.0F));

		PartDefinition backright = model.addOrReplaceChild("backright", CubeListBuilder.create().texOffs(0, 88).addBox(-2.0F, 8.0F, 3.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -32.0F, 7.0F));

		PartDefinition frontleft = model.addOrReplaceChild("frontleft", CubeListBuilder.create().texOffs(48, 96).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -24.0F, -13.0F));

		PartDefinition frontright = model.addOrReplaceChild("frontright", CubeListBuilder.create().texOffs(32, 96).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -24.0F, -13.0F));

		PartDefinition body = model.addOrReplaceChild("body", CubeListBuilder.create().texOffs(48, 38).addBox(-6.0F, -13.9658F, -7.4779F, 12.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(50, 0).addBox(-4.0F, -13.9658F, 6.5221F, 8.0F, 10.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(72, 8).addBox(0.0F, -14.0F, -11.0F, 0.0F, 14.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(64, 84).mirror().addBox(0.0F, -19.0F, 0.0F, 0.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(53, 6).addBox(-2.0F, -19.0F, 2.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(53, 0).addBox(-2.0F, -18.0F, 8.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(53, 0).addBox(-2.0F, -16.0F, 14.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.0F, -7.0F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		model.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}