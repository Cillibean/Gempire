package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelObsidian;
import com.gempire.client.entity.model.ModelTopaz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityObsidian;
import com.gempire.entities.gems.EntityTopaz;
import com.gempire.entities.gems.starter.EntityMica;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderObsidian extends MobRenderer<EntityObsidian, ModelObsidian<EntityObsidian>> {

    public RenderObsidian(EntityRendererProvider.Context renderManagerIn, ModelObsidian<EntityObsidian> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new MarkingLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityObsidian entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/obsidian/blank.png");
    }

    @Override
    protected void renderNameTag(EntityObsidian entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, Component.literal("<"+entityIn.getFacet()+" "+entityIn.getCut()+">"), matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.translate(0.0D, (double)(9.0F * 1.15F * 0.025F), 0.0D);
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
