package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.model.ModelSapphire;
import com.gempire.client.entity.model.ModelZircon;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.EntityTopaz;
import com.gempire.entities.gems.EntityTourmaline;
import com.gempire.entities.gems.EntityZircon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderZircon extends MobRenderer<EntityZircon, ModelZircon<EntityZircon>> {

    public RenderZircon(EntityRendererProvider.Context renderManagerIn, ModelZircon<EntityZircon> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new GemPlacementLayer(this));
        this.addLayer(new VisorLayer(this));
    }

    @Override
    protected void scale(EntityZircon entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.2F, 1.2F, 1.2F);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityZircon entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/zircon/blank.png");
    }
    @Override
    protected void renderNameTag(EntityZircon entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, Component.literal("<"+entityIn.getFacet()+" "+entityIn.getCut()+">"), matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.translate(0.0D, (double)(9.0F * 1.15F * 0.025F), 0.0D);
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
