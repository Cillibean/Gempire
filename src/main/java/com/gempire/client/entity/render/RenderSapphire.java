package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelRuby;
import com.gempire.client.entity.model.ModelSapphire;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntitySapphire;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderSapphire extends MobRenderer<EntitySapphire, ModelSapphire<EntitySapphire>> {

    public RenderSapphire(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelSapphire<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntitySapphire entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/sapphire/blank.png");
    }

    @Override
    protected void preRenderCallback(EntitySapphire entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(.8f, .85f, .8f);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
