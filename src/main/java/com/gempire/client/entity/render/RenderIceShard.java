package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.projectiles.IceShardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class RenderIceShard extends EntityRenderer<IceShardEntity> {
    private final ItemRenderer itemRenderer;

    public RenderIceShard(EntityRenderDispatcher manager, ItemRenderer itemRenderer) {
        super(manager);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(IceShardEntity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25D)) {
            matrixStackIn.pushPose();
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90));
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-(entity.tickCount + partialTicks) * 30 % 360));
            matrixStackIn.translate(-0.03125, -0.09375, 0);
            this.itemRenderer.renderStatic(entity.getItem(), ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.popPose();
            super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(IceShardEntity entity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}