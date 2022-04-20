package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.projectiles.IceShardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class RenderIceShard extends ArrowRenderer<IceShardEntity> {

    public RenderIceShard(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(IceShardEntity entity) {
        return new ResourceLocation(Gempire.MODID+":textures/items/ice_shard.png");
    }
}