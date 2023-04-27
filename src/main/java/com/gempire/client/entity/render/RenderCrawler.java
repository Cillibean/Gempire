package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCrawler;
import com.gempire.client.entity.model.TestModel;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.GeoExampleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

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
    public RenderType getRenderType(EntityCrawler animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8f, 0.8f, 0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
