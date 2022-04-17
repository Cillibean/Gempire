package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.model.ModelSapphire;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntitySapphire;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderSapphire extends MobRenderer<EntitySapphire, ModelSapphire<EntitySapphire>> {

    public RenderSapphire(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new ModelSapphire<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySapphire entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/sapphire/blank.png");
    }

    @Override
    protected void scale(EntitySapphire entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(.8f, .85f, .8f);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
    @Override
    protected void renderNameTag(EntitySapphire entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
