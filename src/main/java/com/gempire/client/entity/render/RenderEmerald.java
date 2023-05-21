package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelEmerald;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityEmerald;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderEmerald extends MobRenderer<EntityEmerald, ModelEmerald<EntityEmerald>> {

    public RenderEmerald(EntityRendererProvider.Context renderManagerIn, ModelEmerald<EntityEmerald> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new VisorLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    protected void scale(EntityEmerald entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(entitylivingbaseIn.getXScale(), entitylivingbaseIn.getYScale(), entitylivingbaseIn.getZScale());        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityEmerald entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/emerald/blank.png");
    }
    @Override
    protected void renderNameTag(EntityEmerald entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, Component.literal("<"+entityIn.getFacet()+" "+entityIn.getCut()+">"), matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.translate(0.0D, (double)(9.0F * 1.15F * 0.025F), 0.0D);
        if (entityIn.customName()) {
            super.renderNameTag(entityIn, entityIn.getCustomName(), matrixStackIn, bufferIn, packedLightIn);
        } else {
            super.renderNameTag(entityIn, Component.literal(entityIn.getNickname().getString()), matrixStackIn, bufferIn, packedLightIn);
        }
        super.shadowRadius = 0;
    }
}