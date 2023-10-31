package com.gempire.client.entity.render;

import com.gempire.client.entity.model.ModelBeastmasterWolf;
import com.gempire.client.entity.render.layers.BeastmasterWolfCollarLayer;
import com.gempire.entities.other.EntityBeastmasterWolf;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class RenderBeastmasterWolf extends MobRenderer<EntityBeastmasterWolf, ModelBeastmasterWolf<EntityBeastmasterWolf>> {
    private static final ResourceLocation WOLF_LOCATION = new ResourceLocation("textures/entity/wolf/wolf.png");
    private static final ResourceLocation WOLF_TAME_LOCATION = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
    private static final ResourceLocation WOLF_ANGRY_LOCATION = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

    public RenderBeastmasterWolf(EntityRendererProvider.Context p_174452_) {
        super(p_174452_, new ModelBeastmasterWolf<>(p_174452_.bakeLayer(ModelLayers.WOLF)), 0.5F);
        this.addLayer(new BeastmasterWolfCollarLayer(this));
    }

    protected float getBob(Wolf p_116528_, float p_116529_) {
        return p_116528_.getTailAngle();
    }

    public void render(EntityBeastmasterWolf p_116531_, float p_116532_, float p_116533_, PoseStack p_116534_, MultiBufferSource p_116535_, int p_116536_) {
        if (p_116531_.isWet()) {
            float f = p_116531_.getWetShade(p_116533_);
            this.model.setColor(f, f, f);
        }

        super.render(p_116531_, p_116532_, p_116533_, p_116534_, p_116535_, p_116536_);
        if (p_116531_.isWet()) {
            this.model.setColor(1.0F, 1.0F, 1.0F);
        }

    }

    public ResourceLocation getTextureLocation(EntityBeastmasterWolf p_116526_) {
        if (p_116526_.isTame()) {
            return WOLF_TAME_LOCATION;
        } else {
            return p_116526_.isAngry() ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
        }
    }
}
