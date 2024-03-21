package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCobaltGuardian;
import com.gempire.client.entity.model.ModelHunter;
import com.gempire.entities.other.EntityCobaltGuardian;
import com.gempire.entities.other.EntityHunter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class RenderHunter extends MobRenderer<EntityHunter, ModelHunter<EntityHunter>> {


    private static final ResourceLocation SKELETON_LOCATION = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
        return SKELETON_LOCATION;
    }

    public RenderHunter(EntityRendererProvider.Context renderManager, ModelHunter<EntityHunter> model) {
        super(renderManager, model, 1f);
        this.shadowRadius = 0.3F;
        this.addLayer(new ItemInHandLayer(this, renderManager.getItemInHandRenderer()));
    }

    @Override
    protected void scale(EntityHunter entity, PoseStack stack, float f) {
        stack.scale(1f, 1f, 1f);
        super.scale(entity, stack, f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHunter animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/hunter/base.png");
    }
}
