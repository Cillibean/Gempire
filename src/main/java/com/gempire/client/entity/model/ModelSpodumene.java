package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Spodumene - Undefined
 * Created using Tabula 8.0.0
 */

public class ModelSpodumene<T extends EntityGem> extends ModelGem<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(Gempire.MODID, "spodumene"), "main");
	public ModelPart armr;
	public ModelPart arml;
	public ModelPart legl;
	public ModelPart legr;
	public ModelPart body;
	public ModelPart head;

	public ModelSpodumene(ModelPart root) {
		head = root.getChild("head");
		body = root.getChild("body");
		arml = root.getChild("arml");
		armr = root.getChild("armr");
		legl = root.getChild("legl");
		legr = root.getChild("legr");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 2)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -4.0F, 0.0F));
		head.addOrReplaceChild("innerhead", CubeListBuilder.create().texOffs(40, 10)
						.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.1F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(44, 0)
						.addBox(-5.0F, -8.0F, 8.5F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.7853981633974483F, 0.0F, 0.0F));
		head.addOrReplaceChild("headgem", CubeListBuilder.create().texOffs(20, 37)
						.addBox(-3.0F, -9.2F, -4.4F, 6.0F, 6.0F, 4.0F,
								new CubeDeformation(-1.5F, -1.5f, -.8f)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("lefteargem", CubeListBuilder.create().texOffs(0, 37)
						.addBox(-4.2F, -6.9F, -2.5F, 4.0F, 6.0F, 6.0F,
								new CubeDeformation(-0.5F, -1.5F, -1.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.15707963267948966F, 0.0F, 0.0F));
		head.addOrReplaceChild("backofheadgem", CubeListBuilder.create().texOffs(41, 48).mirror(true)
						.addBox(-3.0F, -7.0F, 0.8F, 6.0F, 6.0F, 4.0F,
								new CubeDeformation(-1.5F, -1.5F, -1.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.0F, 0.0F, 0.06981317007977318F));
		head.addOrReplaceChild("righteargem", CubeListBuilder.create().texOffs(40, 36)
						.addBox(0.2F, -7.0F, -4.1F, 4.0F, 6.0F, 6.0F,
								new CubeDeformation(-0.5F, -1.5F, -1.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,-0.15707963267948966F, 0.0F, 0.0F));


		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 18)
						.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -4.0F, 0.0F));
		body.addOrReplaceChild("innerbody", CubeListBuilder.create().texOffs(42, 22)
						.addBox(-2.0F, 0.7F, -1.0F, 4.0F, 12.0F, 2.0F,
								new CubeDeformation( 0.1F, 0.3F, 0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("lowerbackgem", CubeListBuilder.create().texOffs(41, 58)
						.addBox(-3.3F, 7.5F, -1.7F, 6.0F, 6.0F, 4.0F,
								new CubeDeformation(-1.5F, -1.5F, -1.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.0F, 0.0F, -0.03490658503988659F));
		body.addOrReplaceChild("chestgem", CubeListBuilder.create().texOffs(20, 47)
						.addBox(-4.6F, 1.8F, -2.4F, 6.0F, 6.0F, 4.0F,
								new CubeDeformation(-1.5F, -1.5F, -1.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.0F, 0.0F, -0.3141592653589793F));
		body.addOrReplaceChild("backgem", CubeListBuilder.create().texOffs(0, 49)
						.addBox(-2.4F, -0.3F, -1.7F, 6.0F, 6.0F, 4.0F,
								new CubeDeformation(-1.5F, -1.5F, -1.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.0F, 0.0F, 0.1832595714594046F));
		body.addOrReplaceChild("navalgem", CubeListBuilder.create().texOffs(24, 0)
						.addBox(-0.7F, 7.8F, -2.4F, 6.0F, 6.0F, 4.0F,
								new CubeDeformation(-1.5F, -1.5F, -1.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,0.0F, 0.0F, 0.20943951023931953F));


		PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(34, 20)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.7F, 10.0F, 0.0F));
		legr.addOrReplaceChild("rightboot", CubeListBuilder.create().texOffs(29, 57)
						.addBox(-1.5F, 6.6F, -1.5F, 3.0F, 7.0F, 3.0F,
								new CubeDeformation(0.0F, 0.5F, 0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));


		PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(54, 22)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.7F, 10.0F, 0.0F));
		legl.addOrReplaceChild("leftboot", CubeListBuilder.create().texOffs(17, 57).mirror(true)
						.addBox(-1.4F, 6.6F, -1.5F, 3.0F, 7.0F, 3.0F,
								new CubeDeformation(0.0F, 0.5F, 0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));


		PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(0, 18)
						.addBox(-2.0F, -1.0F, -1.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, -3.0F, 0.0F));


		PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(26, 18)
						.addBox(0.0F, -1.0F, -1.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, -3.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 64, 80);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.head, this.legl, this.armr, this.legr, this.body, this.arml).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
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
}