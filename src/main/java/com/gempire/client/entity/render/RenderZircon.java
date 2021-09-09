package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelSapphire;
import com.gempire.client.entity.model.ModelZircon;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.EntityZircon;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RenderZircon extends MobRenderer<EntityZircon, ModelZircon<EntityZircon>> {

    public RenderZircon(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelZircon<>(), .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new GemPlacementLayer(this));
        this.addLayer(new VisorLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityZircon entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/zircon/blank.png");
    }
    @Override
    protected void renderName(EntityZircon entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
