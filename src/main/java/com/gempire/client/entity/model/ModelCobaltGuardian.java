package com.gempire.client.entity.model;

import com.gempire.client.entity.animations.GuardianAnimation;
import com.gempire.entities.other.EntityCobaltGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelCobaltGuardian<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "guardian"), "main");
	private final ModelPart model;

	public ModelCobaltGuardian(ModelPart root) {
		this.model = root.getChild("model");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition model = partdefinition.addOrReplaceChild("model", CubeListBuilder.create().texOffs(152, 216).addBox(-16.0F, -12.0F, -18.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
		.texOffs(192, 0).addBox(-16.0F, -45.0F, -21.0F, 32.0F, 20.0F, 38.0F, new CubeDeformation(0.0F))
		.texOffs(150, 150).addBox(-21.0F, -25.0F, -23.0F, 42.0F, 24.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -41.0F, 0.0F));

		PartDefinition leftarmbottom = model.addOrReplaceChild("leftarmbottom", CubeListBuilder.create(), PartPose.offset(21.0F, -5.0F, 0.0F));

		PartDefinition leftarm3 = leftarmbottom.addOrReplaceChild("leftarm3", CubeListBuilder.create().texOffs(0, 258).mirror().addBox(-31.2703F, -60.9896F, -5.0F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(14.0F, 62.0F, 2.0F, 0.0F, -0.0873F, 0.2618F));

		PartDefinition cube_r1 = leftarm3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(192, 10).mirror().addBox(-2.3494F, -60.4896F, -3.2772F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.0F, 1.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition leftarmmiddle = model.addOrReplaceChild("leftarmmiddle", CubeListBuilder.create(), PartPose.offset(21.0F, -13.0F, 0.0F));

		PartDefinition leftarm2 = leftarmmiddle.addOrReplaceChild("leftarm2", CubeListBuilder.create().texOffs(44, 258).mirror().addBox(-20.2703F, -54.9896F, -5.0F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(21.0F, 51.0F, 1.0F));

		PartDefinition cube_r2 = leftarm2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(192, 20).mirror().addBox(-2.3494F, -60.4896F, -3.2772F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 7.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition leftarmtop = model.addOrReplaceChild("leftarmtop", CubeListBuilder.create(), PartPose.offset(21.0F, -20.0F, 0.0F));

		PartDefinition leftarm1 = leftarmtop.addOrReplaceChild("leftarm1", CubeListBuilder.create().texOffs(88, 258).mirror().addBox(-31.2703F, -60.9896F, -5.0F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(43.0F, 47.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r3 = leftarm1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 202).mirror().addBox(-2.3494F, -60.4896F, -3.2772F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.0F, 1.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition rightarmbottom = model.addOrReplaceChild("rightarmbottom", CubeListBuilder.create(), PartPose.offset(-21.0F, -5.0F, 0.0F));

		PartDefinition rightarm3 = rightarmbottom.addOrReplaceChild("rightarm3", CubeListBuilder.create().texOffs(132, 264).addBox(-0.7878F, -60.9896F, -6.3309F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 58.0F, 0.0F, 0.0F, -0.0873F, -0.2618F));

		PartDefinition cube_r4 = rightarm3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 192).addBox(0.8396F, -60.4896F, -9.9535F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 1.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition rightarmmiddle = model.addOrReplaceChild("rightarmmiddle", CubeListBuilder.create(), PartPose.offset(-21.0F, -13.0F, 0.0F));

		PartDefinition rightarm2 = rightarmmiddle.addOrReplaceChild("rightarm2", CubeListBuilder.create().texOffs(114, 208).addBox(-16.0F, -63.0F, -5.0F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 60.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r5 = rightarm2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(192, 0).addBox(-13.0F, -62.5F, -3.5F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 1.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition rightarmtop = model.addOrReplaceChild("rightarmtop", CubeListBuilder.create(), PartPose.offset(-21.0F, -20.0F, 0.0F));

		PartDefinition rightarm1 = rightarmtop.addOrReplaceChild("rightarm1", CubeListBuilder.create().texOffs(176, 264).addBox(-31.2703F, -60.9896F, -5.0F, 14.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 60.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r6 = rightarm1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 212).addBox(-27.3494F, -60.4896F, 1.7228F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 1.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition leftwing = model.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(-14.0F, -14.0F, 19.0F));

		PartDefinition leftwing2_r1 = leftwing.addOrReplaceChild("leftwing2_r1", CubeListBuilder.create().texOffs(0, 102).addBox(-96.0F, -9.0F, 0.0F, 96.0F, 45.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, 0.0F, 0.0F, 0.0436F, -0.2618F));

		PartDefinition leftwing1_r1 = leftwing.addOrReplaceChild("leftwing1_r1", CubeListBuilder.create().texOffs(0, 51).addBox(-96.0F, -30.0F, 0.0F, 96.0F, 51.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -10.0F, 0.0F, 0.0F, 0.0436F, 0.2618F));

		PartDefinition rightwing = model.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(14.0F, -14.0F, 19.0F));

		PartDefinition rightwing2_r1 = rightwing.addOrReplaceChild("rightwing2_r1", CubeListBuilder.create().texOffs(0, 147).mirror().addBox(0.0F, -9.0F, 0.0F, 96.0F, 45.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 4.0F, 0.0F, 0.0F, -0.0436F, 0.2618F));

		PartDefinition rightwing1_r1 = rightwing.addOrReplaceChild("rightwing1_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -30.0F, 0.0F, 96.0F, 51.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -10.0F, 0.0F, 0.0F, -0.0436F, -0.2618F));

		PartDefinition abdomengroup = model.addOrReplaceChild("abdomengroup", CubeListBuilder.create().texOffs(0, 192).addBox(-19.0F, 0.5048F, -18.701F, 38.0F, 28.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition abdomenlower = abdomengroup.addOrReplaceChild("abdomenlower", CubeListBuilder.create().texOffs(192, 58).addBox(-16.0F, -60.9896F, -33.2703F, 32.0F, 20.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 88.0F, 10.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition abdomen5_r1 = abdomenlower.addOrReplaceChild("abdomen5_r1", CubeListBuilder.create().texOffs(192, 61).addBox(10.7977F, -56.9896F, -36.7977F, 0.0F, 16.0F, 49.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition abdomen4_r1 = abdomenlower.addOrReplaceChild("abdomen4_r1", CubeListBuilder.create().texOffs(192, 77).addBox(-12.7977F, -56.9896F, -36.7977F, 0.0F, 16.0F, 49.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition headstuff = model.addOrReplaceChild("headstuff", CubeListBuilder.create(), PartPose.offset(1.0F, -32.0F, -21.0F));

		PartDefinition head_r1 = headstuff.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(248, 216).addBox(-18.0F, -23.0F, -18.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.0F, 21.0F, 0.8727F, -0.6109F, -0.6109F));

		PartDefinition right_antennagroup = headstuff.addOrReplaceChild("right_antennagroup", CubeListBuilder.create().texOffs(186, 142).addBox(-1.0F, -58.1037F, -14.2452F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 51.0F, -1.0F, -0.1745F, -0.1309F, 0.0F));

		PartDefinition ee2 = right_antennagroup.addOrReplaceChild("ee2", CubeListBuilder.create().texOffs(184, 30).addBox(-1.5F, -45.1966F, -45.9245F, 3.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition dd2 = ee2.addOrReplaceChild("dd2", CubeListBuilder.create().texOffs(182, 58).addBox(-2.0F, -17.7416F, -66.2693F, 4.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -8.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition left_antennagroup = headstuff.addOrReplaceChild("left_antennagroup", CubeListBuilder.create().texOffs(190, 142).addBox(-1.0F, -58.1037F, -14.2452F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 51.0F, -1.0F, -0.1745F, 0.1309F, 0.0F));

		PartDefinition ee = left_antennagroup.addOrReplaceChild("ee", CubeListBuilder.create().texOffs(184, 78).addBox(-1.5F, -45.1966F, -45.9245F, 3.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition dd = ee.addOrReplaceChild("dd", CubeListBuilder.create().texOffs(182, 68).addBox(-2.0F, -17.7416F, -66.2693F, 4.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -8.0F, -0.5672F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity instanceof EntityCobaltGuardian) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animate(((EntityCobaltGuardian) entity).idleAnimationState, GuardianAnimation.GUARDIAN_IDLE, ageInTicks);
			this.animate(((EntityCobaltGuardian) entity).cryAnimationState, GuardianAnimation.GUARDIAN_CRY, ageInTicks);
		}
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		model.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return model;
	}
}