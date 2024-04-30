package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityAmberHuntress;
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
import software.bernie.geckolib.model.GeoModel;

public class ModelCobaltGuardian extends GeoModel<EntityCobaltGuardian> {
	@Override
	public ResourceLocation getModelResource(EntityCobaltGuardian object) {
		return new ResourceLocation(Gempire.MODID, "geo/entity/cobalt_guardian.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityCobaltGuardian object) {
		return new ResourceLocation(Gempire.MODID, "textures/entity/boss/guardian/cobalt/base.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityCobaltGuardian animatable) {
		return new ResourceLocation(Gempire.MODID, "animations/entity/cobalt_guardian.animation.json");
	}
}