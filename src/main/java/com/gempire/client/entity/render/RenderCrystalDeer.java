package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCrystalDeer;
import com.gempire.client.entity.model.ModelEmerald;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityEmerald;
import com.gempire.entities.other.EntityCrystalDeer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class RenderCrystalDeer extends MobRenderer<EntityCrystalDeer, ModelCrystalDeer<EntityCrystalDeer>> {

    public RenderCrystalDeer(EntityRendererProvider.Context renderManagerIn, ModelCrystalDeer<EntityCrystalDeer> baseModel) {
        super(renderManagerIn, baseModel, .25f);
    }

    @Override
    protected void scale(EntityCrystalDeer entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.5f, 0.5f, 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCrystalDeer entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/deer/deer.png");
    }
}