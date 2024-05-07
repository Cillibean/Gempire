package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelSorrowJelly;
import com.gempire.entities.other.EntityOverseer;
import com.gempire.entities.other.EntitySorrowJelly;
import com.gempire.entities.other.EntitySpecter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class RenderSorrowJelly extends GeoEntityRenderer<EntitySorrowJelly> {

    public RenderSorrowJelly(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelSorrowJelly());
        this.shadowRadius = 0.3F;
        //.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, EntitySorrowJelly animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        poseStack.scale(.5f, .5f, .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySorrowJelly animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/sorrowjelly/sorrowjelly.png");
    }

}