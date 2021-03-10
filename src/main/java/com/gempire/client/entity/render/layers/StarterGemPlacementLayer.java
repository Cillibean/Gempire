package com.gempire.client.entity.render.layers;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGem;
import com.gempire.client.entity.model.ModelStarterGem;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityStarterGem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StarterGemPlacementLayer<E extends EntityStarterGem, M extends ModelStarterGem<E>> extends GempireLayer<EntityStarterGem, ModelStarterGem<EntityStarterGem>> {
    private IEntityRenderer<EntityStarterGem, ModelStarterGem<EntityStarterGem>> gemRenderer;

    public StarterGemPlacementLayer(IEntityRenderer<EntityStarterGem, ModelStarterGem<EntityStarterGem>> entityRendererIn) {
        super(entityRendererIn);
        this.gemRenderer = entityRendererIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityStarterGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        //int skin = gem.getSkinColor();
        //ModelStarterGem model = (ModelStarterGem)this.getEntityModel();
        //model.placement = gem.getGemPlacementE();
    }
}
