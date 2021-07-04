package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAgate;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityAgate;
import com.gempire.entities.gems.EntityJasper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderAgate extends MobRenderer<EntityAgate, ModelAgate<EntityAgate>> {

    public RenderAgate(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelAgate<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new QuartzMarkingLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new BootsLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityAgate entity) {
        return new ResourceLocation(Gempire.MODID + ":textures/entity/agate/blank.png");
    }

    @Override
    protected void preRenderCallback(EntityAgate entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.1f, 1.25f, 1.1f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
