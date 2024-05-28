package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAmberHuntress;
import com.gempire.client.entity.model.ModelCobaltGuardian;
import com.gempire.entities.other.EntityAmberHuntress;
import com.gempire.entities.other.EntityCobaltGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderCobaltGuardian extends GeoEntityRenderer<EntityCobaltGuardian> {

    public RenderCobaltGuardian(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelCobaltGuardian());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, EntityCobaltGuardian animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCobaltGuardian animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/boss/guardian/cobalt/base.png");
    }
}
