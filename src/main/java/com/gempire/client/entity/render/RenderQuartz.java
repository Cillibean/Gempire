package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityQuartz;
import com.gempire.entities.gems.EntityRuby;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderQuartz extends MobRenderer<EntityQuartz, ModelQuartz<EntityQuartz>> {

    public RenderQuartz(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new ModelQuartz<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new MarkingLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityQuartz entity) {
        return new ResourceLocation(Gempire.MODID + ":textures/entity/quartz/blank.png");
    }

    @Override
    protected void scale(EntityQuartz entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.05f, 1.15f, 1.05f);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
    @Override
    protected void renderNameTag(EntityQuartz entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
