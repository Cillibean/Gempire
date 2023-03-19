package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAquamarine;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityAgate;
import com.gempire.entities.gems.EntityAquamarine;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderAquamarine extends MobRenderer<EntityAquamarine, ModelAquamarine<EntityAquamarine>> {

    public RenderAquamarine(EntityRendererProvider.Context renderManagerIn, ModelAquamarine<EntityAquamarine> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
        //TO-DO: Wing layer for Aquamarine, random wing variants
    }

    @Override
    protected void scale(EntityAquamarine entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(.6f, .65f, .6f);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityAquamarine entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/aquamarine/blank.png");
    }
    @Override
    protected void renderNameTag(EntityAquamarine entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}