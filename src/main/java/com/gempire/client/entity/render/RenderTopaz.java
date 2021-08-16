package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.model.ModelTopaz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntityTopaz;
import com.gempire.entities.gems.starter.EntityShale;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderTopaz extends MobRenderer<EntityTopaz, ModelTopaz<EntityTopaz>> {

    public RenderTopaz(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTopaz<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
        this.addLayer(new VisorLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityTopaz entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/topaz/blank.png");
    }
    @Override
    protected void renderName(EntityTopaz entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.translate(0, -1.7, 0);
        matrixStackIn.translate(0, entityIn.getBoundingBox().getYSize(), 0);
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
