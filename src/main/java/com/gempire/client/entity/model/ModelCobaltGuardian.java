package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.bosses.base.EntityCobaltGuardian;
import net.minecraft.resources.ResourceLocation;
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