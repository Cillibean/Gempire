package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGildedHuntress;
import com.gempire.entities.bosses.prism.EntityGildedHuntress;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderGildedHuntress extends GeoEntityRenderer<EntityGildedHuntress> {

    public RenderGildedHuntress(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelGildedHuntress());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityGildedHuntress animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
