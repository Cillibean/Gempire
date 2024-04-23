package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelNephrite;
import com.gempire.client.entity.model.ModelPearl;
import com.gempire.client.entity.model.ModelQuartz;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityNephrite;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityQuartz;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import java.util.UUID;

public class RenderPearl extends MobRenderer<EntityPearl, ModelPearl<EntityPearl>> {

    public RenderPearl(EntityRendererProvider.Context renderManagerIn, ModelPearl<EntityPearl> baseModel) {
        super(renderManagerIn, baseModel, .25f);
        this.addLayer(new SkinLayer(this));
        this.addLayer(new FaceLayer(this));
        this.addLayer(new OutfitLayer(this));
        this.addLayer(new VisorLayer(this));
        this.addLayer(new HairLayer(this));
        this.addLayer(new InsigniaLayer(this));
        this.addLayer(new GemPlacementLayer(this));
        this.addLayer(new ItemInHandLayer(this, renderManagerIn.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPearl entity) {
        return new ResourceLocation(Gempire.MODID + ":textures/entity/pearl/blank.png");
    }

    @Override
    protected void scale(EntityPearl entitylivingbaseIn, PoseStack matrixStackIn, float p_115316_) {
        matrixStackIn.scale(entitylivingbaseIn.getXScale(), entitylivingbaseIn.getYScale(), entitylivingbaseIn.getZScale());
        super.scale(entitylivingbaseIn, matrixStackIn, p_115316_);
    }
    @Override
    protected void renderNameTag(EntityPearl entityIn, Component displayNameIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entityIn.ASSIGNED_ID != UUID.fromString("00000000-0000-0000-0000-000000000000") && entityIn.ASSIGNED_ID != null && entityIn.getAssigned()) {
            matrixStackIn.translate(0.0D, (double) 3*(9.0F * 1.15F * 0.025F), 0.0D);
            if (entityIn.hasCustomName()) {
                super.renderNameTag(entityIn, entityIn.getCustomName(), matrixStackIn, bufferIn, packedLightIn);
            } else {
                super.renderNameTag(entityIn, Component.literal(entityIn.getNickname().getString()), matrixStackIn, bufferIn, packedLightIn);
            }
            matrixStackIn.translate(0.0D, (double) (-(9.0F * 1.15F * 0.025F)), 0.0D);
            super.renderNameTag(entityIn, Component.literal("<" + entityIn.getFacet() + " " + entityIn.getCut() + ">"), matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.translate(0.0D, (double) (-(9.0F * 1.15F * 0.025F)), 0.0D);
            //super.renderNameTag(entityIn, entityIn.level().getPlayerByUUID(entityIn.MASTER_OWNER).getName(), matrixStackIn, bufferIn, packedLightIn);
            //matrixStackIn.translate(0.0D, (double) (-(9.0F * 1.15F * 0.025F)), 0.0D);
           super.renderNameTag(entityIn, Component.literal(entityIn.getAssignedName() + " <" + entityIn.getAssignedFacet() + " " + entityIn.getAssignedCut() + ">").withStyle(ChatFormatting.GREEN), matrixStackIn, bufferIn, packedLightIn);
            super.shadowRadius = 0;
        } else {
            matrixStackIn.translate(0.0D, (double) (9.0F * 1.15F * 0.025F), 0.0D);
            matrixStackIn.translate(0.0D, (double) (9.0F * 1.15F * 0.025F), 0.0D);
            if (entityIn.hasCustomName()) {
                super.renderNameTag(entityIn, entityIn.getCustomName(), matrixStackIn, bufferIn, packedLightIn);
            } else {
                super.renderNameTag(entityIn, Component.literal(entityIn.getNickname().getString()), matrixStackIn, bufferIn, packedLightIn);
            }
            matrixStackIn.translate(0.0D, (double) (-(9.0F * 1.15F * 0.025F)), 0.0D);
            super.renderNameTag(entityIn, Component.literal("<" + entityIn.getFacet() + " " + entityIn.getCut() + ">"), matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.translate(0.0D, (double) (-(9.0F * 1.15F * 0.025F)), 0.0D);
            if (entityIn.getOwned()) {
                super.renderNameTag(entityIn, entityIn.level().getPlayerByUUID(entityIn.MASTER_OWNER).getDisplayName(), matrixStackIn, bufferIn, packedLightIn);
            }
        }
    }
}
