package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityAgate;
import com.gempire.entities.gems.EntityJasper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderJasper extends MobRenderer<EntityJasper, ModelQuartz<EntityJasper>> {

    public RenderJasper(EntityRendererManager renderManagerIn) {
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
    public ResourceLocation getEntityTexture(EntityJasper entity) {
        return new ResourceLocation(Gempire.MODID + ":textures/entity/jasper/blank.png");
    }

    @Override
    protected void preRenderCallback(EntityJasper entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.05f, 1.15f, 1.05f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
    @Override
    protected void renderName(EntityJasper entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.translate(0, -1.7, 0);
        matrixStackIn.translate(0, entityIn.getBoundingBox().getYSize(), 0);
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
