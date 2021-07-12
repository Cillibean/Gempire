package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPebble;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.starter.EntityPebble;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPebble extends MobRenderer<EntityPebble, ModelPebble<EntityPebble>> {

    public RenderPebble(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPebble<>(), .1f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new PebbleFaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    protected void preRenderCallback(EntityPebble entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.25f, 0.25f, 0.25f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPebble entity) {
        if(entity.getHairVariant() == 1) return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/blank_1.png");
        else if(entity.getHairVariant() == 2) return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/blank_2.png");
        else return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/blank.png");
    }
}
