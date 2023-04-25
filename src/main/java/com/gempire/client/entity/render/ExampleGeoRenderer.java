package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.TestModel;
import com.gempire.entities.other.GeoExampleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class ExampleGeoRenderer extends GeoEntityRenderer<GeoExampleEntity> {

    public ExampleGeoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TestModel());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(GeoExampleEntity animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/test/test.png");
    }

    @Override
    public RenderType getRenderType(GeoExampleEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8f, 0.8f, 0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
