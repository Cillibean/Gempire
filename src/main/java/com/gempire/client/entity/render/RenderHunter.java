package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGildedHuntress;
import com.gempire.client.entity.model.ModelHunter;
import com.gempire.entities.bosses.prism.EntityGildedHuntress;
import com.gempire.entities.other.EntityHunter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class RenderHunter extends GeoEntityRenderer<EntityHunter> {

    public RenderHunter(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelHunter());
        this.shadowRadius = 0.3F;

        addRenderLayer(new BlockAndItemGeoLayer<>(this));
    }


    @Override
    public ResourceLocation getTextureLocation(EntityHunter animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/hunter/hunter.png");
    }
}
