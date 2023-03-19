package com.gempire.client.entity.model;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityStarterGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModelPebble<T extends EntityStarterGem> extends ModelGem<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        public static final ModelLayerLocation LAYER_LOCATION_P = new ModelLayerLocation(
                new ResourceLocation(Gempire.MODID, "pebble"), "main");
        public static final ModelLayerLocation LAYER_LOCATION_M = new ModelLayerLocation(
                new ResourceLocation(Gempire.MODID, "mica"), "main");
        public static final ModelLayerLocation LAYER_LOCATION_S = new ModelLayerLocation(
                new ResourceLocation(Gempire.MODID, "shale"), "main");
        public static final ModelLayerLocation LAYER_LOCATION_N = new ModelLayerLocation(
                new ResourceLocation(Gempire.MODID, "nacre"), "main");	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart legr;
	private final ModelPart armr;
	private final ModelPart arml;
	private final ModelPart legl;

	public ModelPebble(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.legr = root.getChild("legr");
		this.armr = root.getChild("armr");
		this.arml = root.getChild("arml");
		this.legl = root.getChild("legl");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 15).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 9.5F, -3.2F));

		PartDefinition chestgem = body.addOrReplaceChild("chestgem", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, -2.0F));

		PartDefinition dress = body.addOrReplaceChild("dress", CubeListBuilder.create().texOffs(18, 32).addBox(-9.0F, 0.0F, 0.0F, 9.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 7.0F, -0.5F));

		PartDefinition backgem = body.addOrReplaceChild("backgem", CubeListBuilder.create().texOffs(32, 54).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, 3.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.2F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));

		PartDefinition head2 = head.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(30, 0).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -4.0F, -4.2F));

		PartDefinition head3 = head.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(43, 18).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -6.0F, -4.2F));

		PartDefinition eyegem1 = head.addOrReplaceChild("eyegem1", CubeListBuilder.create().texOffs(16, 46).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -2.0F, -5.7F));

		PartDefinition eyegem2 = head.addOrReplaceChild("eyegem2", CubeListBuilder.create().texOffs(32, 46).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, -5.7F));

		PartDefinition headgem = head.addOrReplaceChild("headgem", CubeListBuilder.create().texOffs(0, 46).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, -5.2F));

		PartDefinition Back_of_headgem = head.addOrReplaceChild("Back_of_headgem", CubeListBuilder.create().texOffs(48, 51).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.5F, 1.5F));

		PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(12, 29).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 18.5F, -2.2F));

		PartDefinition leggem2 = legr.addOrReplaceChild("leggem2", CubeListBuilder.create().texOffs(16, 54).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, -0.5F));

		PartDefinition armr = partdefinition.addOrReplaceChild("armr", CubeListBuilder.create().texOffs(38, 15).addBox(-3.0F, 0.0F, -1.2F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 9.5F, -1.0F));

		PartDefinition HandGem2 = armr.addOrReplaceChild("HandGem2", CubeListBuilder.create().texOffs(48, 43).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 5.0F, -1.7F));

		PartDefinition arml = partdefinition.addOrReplaceChild("arml", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 9.5F, -2.2F));

		PartDefinition handgem1 = arml.addOrReplaceChild("handgem1", CubeListBuilder.create().texOffs(48, 35).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 5.0F, -0.5F));

		PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 29).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 18.5F, -2.2F));

		PartDefinition leggem1 = legl.addOrReplaceChild("leggem1", CubeListBuilder.create().texOffs(0, 54).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, -0.5F));

		return LayerDefinition.create(meshdefinition, 74, 62);
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
		legr.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		armr.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arml.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		legl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}