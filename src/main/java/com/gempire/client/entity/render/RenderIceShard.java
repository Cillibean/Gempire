package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.projectiles.IceShardEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class RenderIceShard extends EntityRenderer<IceShardEntity> {
    private final ItemRenderer itemRenderer;

    public RenderIceShard(EntityRendererManager manager, ItemRenderer itemRenderer) {
        super(manager);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(IceShardEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entity.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entity) < 12.25D)) {
            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90));
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(-(entity.ticksExisted + partialTicks) * 30 % 360));
            matrixStackIn.translate(-0.03125, -0.09375, 0);
            this.itemRenderer.renderItem(entity.getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.pop();
            super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(IceShardEntity entity) {
        return PlayerContainer.LOCATION_BLOCKS_TEXTURE;
    }
}