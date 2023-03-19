package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.client.entity.model.ModelGem;
import com.gempire.entities.bases.EntityGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModelBismuth<T extends EntityGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "bismuth"), "main");
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart rightarm;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart leftarm;

	public ModelBismuth(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.rightarm = root.getChild("rightarm");
		this.rightleg = root.getChild("rightleg");
		this.leftleg = root.getChild("leftleg");
		this.leftarm = root.getChild("leftarm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -5.5F, 16.0F, 13.0F, 11.0F, new CubeDeformation(0.0F,0f,-.1f)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition chestgem = Body.addOrReplaceChild("chestgem", CubeListBuilder.create().texOffs(0, 26).addBox(-2.0F, 3.0F, -5.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F,0f,-.1f)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hips = Body.addOrReplaceChild("Hips", CubeListBuilder.create().texOffs(24, 24).addBox(-6.0F, 13.0F, -4.0F, 12.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition navalgem = Hips.addOrReplaceChild("navalgem", CubeListBuilder.create().texOffs(12, 26).addBox(-2.0F, 14.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, -8.2F, -6.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition HairBun = Head.addOrReplaceChild("HairBun", CubeListBuilder.create().texOffs(40, 69).addBox(-3.0F, -7.0F, 1.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-.5f,-.5f,-1f)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ShortHair = Head.addOrReplaceChild("ShortHair", CubeListBuilder.create().texOffs(0, 61).addBox(-5.0F, -9.2F, -7.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-.7f,-.6f,-.7f)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LongHair = Head.addOrReplaceChild("LongHair", CubeListBuilder.create().texOffs(0, 100).addBox(-5.0F, -8.5F, -7.0F, 10.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(35, 81).mirror().addBox(-6.5F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(-8.0F, -0.5F, 0.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 85).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(-.5F,0f,-.5f)), PartPose.offset(-3.5F, 15.0F, 0.0F));

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(-.5F,0f,-.5f)), PartPose.offset(3.5F, 15.0F, 0.0F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(32, 38).addBox(0.5F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(8.0F, -0.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotateAngle(Head, headPitch * 0.5f * ((float)Math.PI / 180F), netHeadYaw * .5f * ((float)Math.PI / 180F), 0);
		this.setRotateAngle(leftarm, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(rightarm, Mth.cos(limbSwing * 0.5F)  * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(leftleg, Mth.cos(limbSwing * 0.5F) * limbSwingAmount * 0.8f, 0, 0);
		this.setRotateAngle(rightleg, Mth.cos(limbSwing * 0.5F + (float)Math.PI) * limbSwingAmount * 0.8f, 0, 0);
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
		rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}