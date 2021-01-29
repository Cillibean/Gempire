package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelShale;
import com.gempire.client.entity.render.layers.SkinLayer;
import com.gempire.client.entity.render.layers.StarterGemPlacementLayer;
import com.gempire.entities.gems.starter.EntityShale;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderShale extends MobRenderer<EntityShale, ModelShale<EntityShale>> {

    public RenderShale(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelShale<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new StarterGemPlacementLayer(this));
    }

    @Override
    protected void preRenderCallback(EntityShale entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityShale entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/shale/shale.png");
    }
}
