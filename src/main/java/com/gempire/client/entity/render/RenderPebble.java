package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPebble;
import com.gempire.client.entity.render.layers.SkinLayer;
import com.gempire.client.entity.render.layers.StarterGemPlacementLayer;
import com.gempire.entities.gems.starter.EntityPebble;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPebble extends MobRenderer<EntityPebble, ModelPebble<EntityPebble>> {

    public RenderPebble(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPebble<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new StarterGemPlacementLayer(this));
    }

    @Override
    protected void preRenderCallback(EntityPebble entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPebble entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/pebble.png");
    }
}
