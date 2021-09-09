package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelSpodumene;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntitySpodumene;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderSpodumene extends MobRenderer<EntitySpodumene, ModelSpodumene<EntitySpodumene>> {

    public RenderSpodumene(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelSpodumene<>(), .25f);
        this.addLayer(new SkinLayer(this));
        //this.addLayer(new FaceLayer(this));
        this.addLayer(new SpodumeneHairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntitySpodumene entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/spodumene/blank.png");
    }
    @Override
    protected void renderName(EntitySpodumene entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
