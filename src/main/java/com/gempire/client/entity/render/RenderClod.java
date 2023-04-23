package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelClod;
import com.gempire.entities.gems.EntityAgate;
import com.gempire.entities.other.EntityClod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class RenderClod extends MobRenderer<EntityClod, ModelClod<EntityClod>> {

    public RenderClod(EntityRendererProvider.Context p_174304_, ModelClod<EntityClod> p_174305_) {
        super(p_174304_, p_174305_, .25F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityClod p_114482_) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clod/clod.png");
    }

    @Override
    protected void scale(EntityClod entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.1f, 1.1f, 1.1f);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
