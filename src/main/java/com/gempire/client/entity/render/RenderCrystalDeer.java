package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAbomination;
import com.gempire.client.entity.model.ModelCrystalDeer;
import com.gempire.client.entity.model.ModelEmerald;
import com.gempire.client.entity.render.layers.*;
import com.gempire.entities.gems.EntityEmerald;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrystalDeer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.UUID;

public class RenderCrystalDeer extends GeoEntityRenderer<EntityCrystalDeer> {

    public RenderCrystalDeer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelCrystalDeer());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, EntityCrystalDeer animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        poseStack.scale(.75f, .75f, .75f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCrystalDeer animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/deer/deer.png");
    }


}