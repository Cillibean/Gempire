package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCobaltGuardian;
import com.gempire.entities.other.EntityCobaltGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderCobaltGuardian extends MobRenderer<EntityCobaltGuardian, ModelCobaltGuardian<EntityCobaltGuardian>> {

    public RenderCobaltGuardian(EntityRendererProvider.Context renderManager, ModelCobaltGuardian<EntityCobaltGuardian> model) {
        super(renderManager, model, 1f);
        this.shadowRadius = 0.3F;
    }

    @Override
    protected void scale(EntityCobaltGuardian entity, PoseStack stack, float f) {
        stack.scale(1f, 1f, 1f);
        super.scale(entity, stack, f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCobaltGuardian animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/boss/guardian/cobalt/base.png");
    }
}
