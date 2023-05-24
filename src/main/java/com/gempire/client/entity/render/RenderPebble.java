package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelObsidian;
import com.gempire.client.entity.model.ModelPebble;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityObsidian;
import com.gempire.entities.gems.starter.EntityPebble;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderPebble extends MobRenderer<EntityPebble, ModelPebble<EntityPebble>> {

    public RenderPebble(EntityRendererProvider.Context renderManagerIn, ModelPebble<EntityPebble> baseModel) {
        super(renderManagerIn, baseModel, .1f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new PebbleFaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    protected void scale(EntityPebble entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(entitylivingbaseIn.getXScale(), entitylivingbaseIn.getYScale(), entitylivingbaseIn.getZScale());
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPebble entity) {
        if(entity.getHairVariant() == 1) return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/blank_1.png");
        else if(entity.getHairVariant() == 2) return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/blank_2.png");
        else return new ResourceLocation(Gempire.MODID+":textures/entity/pebble/blank.png");
    }
    @Override
    protected void renderNameTag(EntityPebble entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entityIn.hasCustomName()) {
            super.renderNameTag(entityIn, entityIn.getCustomName(), matrixStackIn, bufferIn, packedLightIn);
        } else {
            super.renderNameTag(entityIn, Component.literal(entityIn.getNickname().getString()), matrixStackIn, bufferIn, packedLightIn);
        }
    }
}
