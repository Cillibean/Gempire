package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityAquamarine;
import com.gempire.entities.gems.EntityRutile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderRutile extends MobRenderer<EntityRutile, ModelRuby<EntityRutile>> {

    public RenderRutile(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelRuby<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    protected void preRenderCallback(EntityRutile entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(.8f, .85f, .8f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityRutile entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/rutile/blank.png");
    }
    @Override
    protected void renderName(EntityRutile entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}