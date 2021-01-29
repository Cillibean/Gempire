package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelMica;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.starter.EntityMica;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderRuby extends MobRenderer<EntityRuby, ModelRuby<EntityRuby>> {

    public RenderRuby(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelRuby<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    protected void preRenderCallback(EntityRuby entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityRuby entity) {
        //Temp
        return new ResourceLocation(Gempire.MODID+":textures/entity/ruby/ruby.png");
    }
}
