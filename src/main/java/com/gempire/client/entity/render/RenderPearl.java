package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPearl;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityQuartz;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderPearl extends MobRenderer<EntityPearl, ModelPearl<EntityPearl>> {

    public RenderPearl(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelPearl<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPearl entity) {
        return new ResourceLocation(Gempire.MODID + ":textures/entity/pearl/blank.png");
    }
    @Override
    protected void renderName(EntityPearl entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
