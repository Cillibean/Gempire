package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelNephrite;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityNephrite;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderNephrite extends MobRenderer<EntityNephrite, ModelNephrite<EntityNephrite>> {

    public RenderNephrite(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelNephrite<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityNephrite entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/nephrite/blank.png");
    }
    @Override
    protected void renderName(EntityNephrite entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
