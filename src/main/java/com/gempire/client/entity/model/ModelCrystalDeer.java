package com.gempire.client.entity.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.gempire.Gempire;
import com.gempire.entities.other.EntityCrystalDeer;
import com.gempire.entities.other.EntityOverseer;
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
import software.bernie.geckolib.model.GeoModel;

public class ModelCrystalDeer extends GeoModel<EntityCrystalDeer> {
	@Override
	public ResourceLocation getModelResource(EntityCrystalDeer object) {
		return new ResourceLocation(Gempire.MODID, "geo/entity/crystal_deer.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityCrystalDeer object) {
		return new ResourceLocation(Gempire.MODID, "textures/entity/deer/deer.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityCrystalDeer animatable) {
		return new ResourceLocation(Gempire.MODID, "animations/entity/crystal_deer.animation.json");
	}
}