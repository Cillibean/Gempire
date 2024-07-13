package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAmberHuntress;
import com.gempire.entities.bosses.base.EntityAmberHuntress;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderAmberHuntress extends GeoEntityRenderer<EntityAmberHuntress> {

    public RenderAmberHuntress(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelAmberHuntress());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityAmberHuntress animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
