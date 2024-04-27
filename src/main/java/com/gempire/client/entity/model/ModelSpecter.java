package com.gempire.client.entity.model;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Monster;

import javax.swing.text.html.parser.Entity;

public class ModelSpecter<T extends Monster> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "specter"), "main");
	private final ModelPart model;

	private final ModelPart head_r1;
	private final ModelPart rightarm;
	private final ModelPart rightleg;
	private final ModelPart leftarm;
	private final ModelPart leftleg;

	public ModelSpecter(ModelPart root) {
		this.model = root.getChild("model");
		this.head_r1 = root.getChild("head_r1");
		this.rightarm = root.getChild("rightarm");
		this.rightleg = root.getChild("rightleg");
		this.leftarm = root.getChild("leftarm");
		this.leftleg = root.getChild("leftleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition model = partdefinition.addOrReplaceChild("model", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 1.0F));

		PartDefinition body = model.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -23.0F, 0.0F));

		PartDefinition upperbody = body.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(7, 12).addBox(-9.0F, -6.0F, 0.0F, 18.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition head_r1 = upperbody.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(9, 0).addBox(-7.0F, -12.0F, 0.0F, 15.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition rightarm = upperbody.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 17).addBox(-9.0F, 0.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition midright = rightarm.addOrReplaceChild("midright", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 10.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition lowright = midright.addOrReplaceChild("lowright", CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition leftarm = upperbody.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(5.0F, 0.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition midleft = leftarm.addOrReplaceChild("midleft", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(5.0F, 0.0F, 0.0F, 4.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition lowleft = midleft.addOrReplaceChild("lowleft", CubeListBuilder.create().texOffs(0, 38).mirror().addBox(5.0F, 0.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition lowerbody = body.addOrReplaceChild("lowerbody", CubeListBuilder.create().texOffs(7, 17).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition rightleg = lowerbody.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(7, 25).mirror().addBox(-5.0F, 0.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition lowerright = rightleg.addOrReplaceChild("lowerright", CubeListBuilder.create().texOffs(7, 35).mirror().addBox(-5.0F, 0.0F, 0.0F, 4.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition leftleg = lowerbody.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(7, 25).addBox(1.0F, 0.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition lowerleft = leftleg.addOrReplaceChild("lowerleft", CubeListBuilder.create().texOffs(7, 35).addBox(1.0F, 0.0F, 0.0F, 4.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 48);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(this.head_r1, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(this.leftarm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.rightarm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.leftleg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(this.rightleg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
	}

	public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		model.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}