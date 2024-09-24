package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAlabasterEmpress;
import com.gempire.entities.bosses.base.EntityAlabasterEmpress;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderAlabasterEmpress extends GeoEntityRenderer<EntityAlabasterEmpress> {

    public RenderAlabasterEmpress(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelAlabasterEmpress());
        this.shadowRadius = 1.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityAlabasterEmpress animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/boss/empress/alabaster/base.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, EntityAlabasterEmpress animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        super.scaleModelForRender(3, 3, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
