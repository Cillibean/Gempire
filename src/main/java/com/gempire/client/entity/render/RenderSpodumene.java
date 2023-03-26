package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.model.ModelSpodumene;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntitySpinel;
import com.gempire.entities.gems.EntitySpodumene;
import com.gempire.entities.gems.EntityTopaz;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class RenderSpodumene extends MobRenderer<EntitySpodumene, ModelSpodumene<EntitySpodumene>> {

    public RenderSpodumene(EntityRendererProvider.Context renderManagerIn, ModelSpodumene<EntitySpodumene> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new GemPlacementLayer(this));
        this.addLayer(new SkinLayer(this));
        //this.addLayer(new FaceLayer(this));
        this.addLayer(new SpodumeneHairLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
    }

    @Override
    protected void scale(EntitySpodumene entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.3F, 1.4F, 1.3F);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpodumene entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/spodumene/blank.png");
    }
    @Override
    protected void renderNameTag(EntitySpodumene entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
