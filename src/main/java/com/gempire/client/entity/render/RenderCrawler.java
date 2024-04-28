package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCrawler;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class RenderCrawler extends GeoEntityRenderer<EntityCrawler> {

    public RenderCrawler(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelCrawler());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityCrawler animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/crawler/crawler.png");
    }

    @Override
    public float getMotionAnimThreshold(EntityCrawler animatable) {
        return 0.005F;
    }
}
