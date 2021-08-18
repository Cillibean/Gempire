package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityQuartz;
import com.gempire.entities.gems.EntityRuby;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderQuartz extends MobRenderer<EntityQuartz, ModelQuartz<EntityQuartz>> {

    public RenderQuartz(EntityRendererManager renderManagerIn) {
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
    public ResourceLocation getEntityTexture(EntityQuartz entity) {
        return new ResourceLocation(Gempire.MODID + ":textures/entity/quartz/blank.png");
    }

    @Override
    protected void preRenderCallback(EntityQuartz entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.05f, 1.15f, 1.05f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
    @Override
    protected void renderName(EntityQuartz entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
