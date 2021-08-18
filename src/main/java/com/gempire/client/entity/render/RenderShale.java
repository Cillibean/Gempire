package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPebble;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.starter.EntityShale;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderShale extends MobRenderer<EntityShale, ModelPebble<EntityShale>> {

    public RenderShale(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPebble<>(), .1f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new PebbleFaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    protected void preRenderCallback(EntityShale entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.3f, 0.3f, 0.3f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityShale entity) {
        if(entity.getHairVariant() == 1) return new ResourceLocation(Gempire.MODID+":textures/entity/shale/blank_1.png");
        else if(entity.getHairVariant() == 2) return new ResourceLocation(Gempire.MODID+":textures/entity/shale/blank_2.png");
        else return new ResourceLocation(Gempire.MODID+":textures/entity/shale/blank.png");
    }
    @Override
    protected void renderName(EntityShale entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
