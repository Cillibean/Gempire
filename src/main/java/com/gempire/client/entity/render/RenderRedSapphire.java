package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelSapphire;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityRedSapphire;
import com.gempire.entities.gems.EntitySapphire;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RenderRedSapphire extends MobRenderer<EntityRedSapphire, ModelSapphire<EntityRedSapphire>> {

    public RenderRedSapphire(EntityRendererProvider.Context renderManagerIn, ModelSapphire<EntityRedSapphire> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRedSapphire entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/redsapphire/blank.png");
    }

    @Override
    protected void scale(EntityRedSapphire entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(.8f, .85f, .8f);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
    @Override
    protected void renderNameTag(EntityRedSapphire entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.scale(.77f, .77f, .77f);
        super.renderNameTag(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
