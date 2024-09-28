package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAlabasterEmpress;
import com.gempire.entities.bosses.base.EntityAlabasterEmpress;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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

    @Override
    public void render(EntityAlabasterEmpress entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public boolean shouldRender(EntityAlabasterEmpress empress, Frustum p_114492_, double p_114493_, double p_114494_, double p_114495_) {
        if (super.shouldRender(empress, p_114492_, p_114493_, p_114494_, p_114495_)) {
            return true;
        } else {
            /*
            if (empress.hasActiveAttackTarget()) {
                LivingEntity livingentity = p_114836_.getActiveAttackTarget();
                if (livingentity != null) {
                    Vec3 vec3 = this.getPosition(livingentity, (double)livingentity.getBbHeight() * 0.5D, 1.0F);
                    Vec3 vec31 = this.getPosition(p_114836_, (double)p_114836_.getEyeHeight(), 1.0F);
                    return p_114837_.isVisible(new AABB(vec31.x, vec31.y, vec31.z, vec3.x, vec3.y, vec3.z));
                }
            }
*/
            return false;
        }
    }
}
