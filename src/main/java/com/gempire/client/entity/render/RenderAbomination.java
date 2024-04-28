package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAbomination;
import com.gempire.client.entity.model.ModelCrawler;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class RenderAbomination extends GeoEntityRenderer<EntityAbomination> {

    public RenderAbomination(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelAbomination());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityAbomination animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/abomination/abomination.png");
    }

    @Override
    public float getMotionAnimThreshold(EntityAbomination animatable) {
        return 0.005F;
    }
}
